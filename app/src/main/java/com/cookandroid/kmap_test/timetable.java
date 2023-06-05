package com.cookandroid.kmap_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cookandroid.kmap_test.presenter.EditPresenter;
import com.github.tlaabs.timetableview.*;
import java.util.ArrayList;
import java.util.List;

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

    private double destinationLatitude;  // 도착지 위도
    private double destinationLongitude; // 도착치 경도

    private LocationManager locationManager;
    private LocationListener locationListener;

    String user_latitude = "latitude";
    String user_longitude = "";

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

                String Title = schedules.get(0).getClassTitle();

                AlertDialog.Builder builder = new AlertDialog.Builder(timetable.this);
                builder.setTitle(Title);
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
                                String place = schedules.get(0).getClassPlace();


                                if (place.contains("[A1]")) {
                                    destinationLatitude = 35.911198621945424;
                                    destinationLongitude = 128.80782238644915;
                                    startNavigation();
                                }
                                else if (place.contains("[A2]")) {
                                    destinationLatitude = 35.91177841661396;
                                    destinationLongitude = 128.8067195852139;
                                    startNavigation();
                                }

                                else if (place.contains("[A8]")) {
                                    destinationLatitude = 35.91307055462935;
                                    destinationLongitude = 128.8063335837257;
                                    startNavigation();
                                }

                                else if (place.contains("[A9]")) {
                                    destinationLatitude = 35.91285292189562;
                                    destinationLongitude = 128.80522647021226;
                                    startNavigation();
                                }

                                else if (place.contains("[B1]")) {
                                    destinationLatitude = 35.91046676604076;
                                    destinationLongitude = 128.80899924597327;
                                    startNavigation();
                                }

                                else if (place.contains("[B2]")) {
                                    destinationLatitude = 35.9107229905766;
                                    destinationLongitude = 128.80933738230345;
                                    startNavigation();
                                }

                                else if (place.contains("[B3]")) {
                                    destinationLatitude = 35.90989312837552;
                                    destinationLongitude = 128.80969231798457;
                                    startNavigation();
                                }

                                else if (place.contains("[B4]")) {
                                    destinationLatitude = 35.90949034078319;
                                    destinationLongitude = 128.8107077242248;
                                    startNavigation();
                                }

                                else if (place.contains("[B8]")) {
                                    destinationLatitude = 35.91046676604076;
                                    destinationLongitude = 128.80899924597327;
                                    startNavigation();
                                }

                                else if (place.contains("[C1]")) {
                                    destinationLatitude = 35.9127904210839;
                                    destinationLongitude = 128.81087156032328;
                                    startNavigation();
                                }

                                else if (place.contains("[C2]")) {
                                    destinationLatitude = 35.91320569912806;
                                    destinationLongitude = 128.81111918428113;
                                    startNavigation();
                                }

                                else if (place.contains("[C4]")) {
                                    destinationLatitude = 35.91478504395566;
                                    destinationLongitude = 128.8127503242304;
                                    startNavigation();
                                }

                                else if (place.contains("[C6]")) {
                                    destinationLatitude = 35.915392191407214;
                                    destinationLongitude = 128.81192783884129;
                                    startNavigation();
                                }

                                else if (place.contains("[C7]")) {
                                    destinationLatitude = 35.913759710682264;
                                    destinationLongitude = 128.80993548366703;
                                    startNavigation();
                                }

                                else if (place.contains("[C9]")) {
                                    destinationLatitude = 35.9127904210839;
                                    destinationLongitude = 128.81087156032328;
                                    startNavigation();
                                }

                                else if (place.contains("[C12]")) {
                                    destinationLatitude = 35.91458278174892;
                                    destinationLongitude = 128.80763908906465;
                                    startNavigation();
                                }

                                else if (place.contains("[C13]")) {
                                    destinationLatitude = 35.91506562055649;
                                    destinationLongitude = 128.80699097561347;
                                    startNavigation();
                                }

                                else if (place.contains("[D1]")) {
                                    destinationLatitude = 35.91417202183079;
                                    destinationLongitude = 128.80229606106218;
                                    startNavigation();
                                }

                                else if (place.contains("[D2]")) {
                                    destinationLatitude = 35.91358070953851;
                                    destinationLongitude = 128.8023684925233;
                                    startNavigation();
                                }

                                else if (place.contains("[D5]")) {
                                    destinationLatitude = 35.91254031510512;
                                    destinationLongitude = 128.80399258946068;
                                    startNavigation();
                                }

                                else if (place.contains("[D6]")) {
                                    destinationLatitude = 35.91261815630141;
                                    destinationLongitude = 128.8046589773263;
                                    startNavigation();
                                }

                                else if (place.contains("[D7]")) {
                                    destinationLatitude = 35.91202884525934;
                                    destinationLongitude = 128.8047480510831;
                                    startNavigation();
                                }

                                else if (place.contains("[D8]")) {
                                    destinationLatitude = 35.911803287449615;
                                    destinationLongitude = 128.80536600165212;
                                    startNavigation();
                                }

                                else if (place.contains("[D9]")) {
                                    destinationLatitude = 35.91131620355209;
                                    destinationLongitude = 128.80599738766466;
                                    startNavigation();
                                }

                                else if (place.contains("[D10]")) {
                                    destinationLatitude = 35.91215118255399;
                                    destinationLongitude = 128.80410282962066;
                                    startNavigation();
                                }

                                else if (place.contains("[D11]")) {
                                    destinationLatitude = 35.911081799603565;
                                    destinationLongitude = 128.80480684275736;
                                    startNavigation();
                                }

                                else if (place.contains("[D15]")) {
                                    destinationLatitude = 35.909782909664145;
                                    destinationLongitude = 128.80519270752905;
                                    startNavigation();
                                }

                                else if (place.contains("[D17]")) {
                                    destinationLatitude = 35.91000881119508;
                                    destinationLongitude = 128.80694794882763;
                                    startNavigation();
                                }

                                else if (place.contains("[D18]")) {
                                    destinationLatitude = 35.909196334305186;
                                    destinationLongitude = 128.80644764111554;
                                    startNavigation();
                                }


                                else {
                                    Log.d("타임테이블", "유효하지 않은 인덱스");
                                }

                                break;

                            case "삭제하기":
                                timetable.remove(idx);
                                mainPresenter.save(timetable.createSaveData());
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


    private void startNavigation() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                // 현재 위치 정보를 가져오는 로직을 추가하세요
                double latitude = location.getLatitude();   // 현재 위치의 위도
                double longitude = location.getLongitude(); // 현재 위치의 경도


            }
        };

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(timetable.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location gpsLocation = null;
            try {
                gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            if (gpsLocation != null) {
                String user_latitude = String.valueOf(gpsLocation.getLatitude());
                String user_longitude = String.valueOf(gpsLocation.getLongitude());

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000,
                        1,
                        gpsLocationListener);
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        1000,
                        1,
                        gpsLocationListener);
            }

            String url2 = "kakaomap://route?sp=" + user_latitude + "," + user_longitude + "&ep=" + destinationLatitude+","+destinationLongitude+"&by=FOOT";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

            if (list == null || list.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=net.daum.android.map")));
            } else {
                startActivity(intent);
            }
        }
    }





    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.
            String provider = location.getProvider();  // 위치정보
            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            double altitude = location.getAltitude(); // 고도
            user_latitude = String.valueOf(latitude);
            user_longitude = String.valueOf(longitude);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {

        }
    };


}
