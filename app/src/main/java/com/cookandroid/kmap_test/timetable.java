package com.cookandroid.kmap_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.kmap_test.presenter.EditPresenter;
import com.github.tlaabs.timetableview.*;
import java.util.ArrayList;
import com.cookandroid.kmap_test.contract.MainContract;
import com.cookandroid.kmap_test.model.PrefManager;
import com.cookandroid.kmap_test.presenter.MainPresenter;
import com.cookandroid.kmap_test.search;


public class timetable extends AppCompatActivity implements MainContract.View {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_ADD = 1;
    public static final int REQUEST_EDIT = 2;

    private LinearLayout addBtn;
    private MainContract.UserActions mainPresenter;

    private EditPresenter editPresenter;
    private Context context;

    private TimetableView timetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        context = this;
        mainPresenter = new MainPresenter(this);
        mainPresenter.setPrefManager(PrefManager.getInstance());

        timetable = findViewById(R.id.timetable);
        timetable.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<com.github.tlaabs.timetableview.Schedule> schedules) {
                AlertDialog.Builder builder = new AlertDialog.Builder(timetable.this);
                builder.setTitle("Selected Sticker");
                final String[] selectArray = new String[]{"수정하기", "길찾기","삭제하기"};

                // Set the items for the dialog using selectArray
                builder.setItems(selectArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        // Handle the item selection based on the position
                        String selectedOption = selectArray[position];
                        switch (selectedOption) {
                            case "수정하기":
                                mainPresenter.selectSticker(idx, schedules); // Handle 수정하기 action
                                break;
                            case "길찾기":
                                String selectedValue = ""; // 여기에 selectedValue 값을 저장하는 코드 작성
                                Intent intent = new Intent(timetable.this, search.class);
                                intent.putExtra("selectedValue", selectedValue); // selectedValue 값을 인텐트에 추가
                                startActivity(intent); // Start search activity
                                break;
                            case "삭제하기":
                                timetable.remove(idx);
                                break;
                        }
                    }
                });

                // Set the positive button for closing the dialog
                builder.setPositiveButton("닫기", null);

                // Show the dialog
                builder.show();
            }

        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.addMenuClick();
            }
        });

        mainPresenter.prepare();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_ADD:
                if(resultCode == EditActivity.RESULT_OK_ADD){
                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
                    timetable.add(item);
                }
                break;
            case REQUEST_EDIT:
                if(resultCode == EditActivity.RESULT_OK_EDIT){
                    int idx = data.getIntExtra("idx",-1);
                    ArrayList<Schedule> item = (ArrayList<Schedule>)data.getSerializableExtra("schedules");
                    timetable.edit(idx,item);
                }
                else if(resultCode == EditActivity.RESULT_OK_DELETE){
                    int idx = data.getIntExtra("idx",-1);
                    timetable.remove(idx);
                }
                break;
        }
        mainPresenter.save(timetable.createSaveData());
    }

    @Override
    public void startEditActivityForAdd() {
        Intent i = new Intent(context, EditActivity.class);
        i.putExtra("allSchedules",timetable.getAllSchedulesInStickers());
        startActivityForResult(i, REQUEST_ADD);
    }

    @Override
    public void startEditActivityForEdit(int idx, ArrayList<Schedule> schedules) {
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("idx",idx);
        i.putExtra("mode", REQUEST_EDIT);
        i.putExtra("allSchedules", timetable.getAllSchedulesInStickersExceptIdx(idx));
        i.putExtra("schedules", schedules);
        startActivityForResult(i, REQUEST_EDIT);
    }

    @Override
    public void restoreTimetable(String data) {
        timetable.load(data);
    }

    @Override
    public void setDayHighlight(int day) {
        if(day > 0) timetable.setHeaderHighlight(day);
    }
}
