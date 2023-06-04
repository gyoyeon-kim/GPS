package com.cookandroid.kmap_test;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.cookandroid.kmap_test.R;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;

import java.util.ArrayList;
import java.util.HashMap;

import com.cookandroid.kmap_test.contract.EditContract;
import com.cookandroid.kmap_test.presenter.EditPresenter;
import com.cookandroid.kmap_test.view.TimeBoxView;

public class EditActivity extends AppCompatActivity implements EditContract.View {
    public static final int RESULT_OK_ADD = 1;
    public static final int RESULT_OK_EDIT = 2;
    public static final int RESULT_OK_DELETE = 3;

    private EditPresenter editPresenter;

    private String[] structureArray = {
            "A1", "A2", "A8", "A9", "B1", "B2", "B3",
            "B4", "B8", "C1", "C2", "C4", "C6", "C7", "C9",
            "C12", "C13", "D1", "D2", "D5", "D6", "D7",
            "D8", "D9", "D10", "D11", "D15", "D17", "D18"
    };

    ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();

    TextView titleView;

    EditText classTitle, classPlace, professorName;
    TimeBoxView timeBox;
    Button addTimeBtn, addStructureBtn;

    LinearLayout backBtn;
    LinearLayout addBtn;
    LinearLayout deleteBtn;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        context = this;


        editPresenter = new EditPresenter(this);
        titleView = findViewById(R.id.title);
        classTitle = findViewById(R.id.class_title);
        classPlace = findViewById(R.id.class_place);
        professorName = findViewById(R.id.professor_name);
        timeBox = findViewById(R.id.time_box);
        addTimeBtn = findViewById(R.id.add_time);
        backBtn = findViewById(R.id.back);
        addBtn = findViewById(R.id.add);
        deleteBtn = findViewById(R.id.delete);

        addStructureBtn=findViewById(R.id.structure);


        addStructureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(EditActivity.this);
                dlg.setTitle("강의동 선택");
                dlg.setItems(structureArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedValue = structureArray[which];
                        addStructureBtn.setText("[" + selectedValue + "]");
                        classPlace.setText("[" + selectedValue + "]");
                    }
                });
                dlg.setPositiveButton("닫기", null);
                dlg.show();
            }
        });



        addTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.clickAddTimeBtn();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.submit(allSchedules,timeBox.getSchedules());
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.clickDeleteBtn();
            }
        });

        init();
    }

    private void init() {
        allSchedules = (ArrayList<Schedule>) getIntent().getSerializableExtra("allSchedules");
        if (isEditMode()) {
            ArrayList<Schedule> itemSchedules = (ArrayList<Schedule>) getIntent().getSerializableExtra("schedules");
            editPresenter.prepare(true, itemSchedules);
        } else {
            editPresenter.prepare(false, null);
        }
    }

    private boolean isEditMode() {
        int mode = getIntent().getIntExtra("mode", -1);
        if (mode == timetable.REQUEST_EDIT) return true;
        return false;
    }



    @Override
    public void restoreViews(ArrayList<Schedule> schedules) { //저장하기(수업,장소,교수이름,시간, *건물추가 필요*)
        for(Schedule schedule : schedules) {
            classTitle.setText(schedule.getClassTitle());
            classPlace.setText(schedule.getClassPlace());
            professorName.setText(schedule.getProfessorName());
            timeBox.add(schedule);
        }
    }

    @Override
    public void createTimeView(Schedule schedule) {
        timeBox.add(schedule);
    }

    private ArrayList<Schedule> addMetaData(ArrayList<Schedule> resultSchedule){ //시간표에 표시되는 정보[시간은 범위로 표시] (*건물추가 필요*)
        for(Schedule schedule : resultSchedule){
            schedule.setClassTitle(classTitle.getText().toString());
            schedule.setClassPlace(classPlace.getText().toString());
            schedule.setProfessorName(professorName.getText().toString());
        }
        return resultSchedule;
    }

    @Override
    public void setResult(ArrayList<Schedule> resultSchedule) {
        Intent i = new Intent();
        if(resultSchedule == null){
            i.putExtra("idx",getIntent().getIntExtra("idx",-1));
            setResult(RESULT_OK_DELETE,i);
            finish();
            return;
        }
        resultSchedule = addMetaData(resultSchedule);
        i.putExtra("schedules", resultSchedule);
        if(isEditMode()){
            i.putExtra("idx",getIntent().getIntExtra("idx",-1));
            setResult(RESULT_OK_EDIT, i);
        }
        else{
            setResult(RESULT_OK_ADD, i);
        }
        finish();
    }

    @Override
    public void hideDeleteBtn() {
        deleteBtn.setVisibility(View.GONE);
    }//삭제버튼 숨기기

    @Override
    public void showDeleteBtn() {
        deleteBtn.setVisibility(View.VISIBLE);
    }//삭제버튼 보여주기

    @Override
    public void setActivityTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void showToastMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }


}
