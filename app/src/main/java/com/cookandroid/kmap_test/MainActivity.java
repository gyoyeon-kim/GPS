package com.cookandroid.kmap_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.cookandroid.kmap_test.databinding.ActivityMainBinding;
import com.cookandroid.kmap_test.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;  // 뷰 바인딩
    private MapView mapView;
    private MarkerEventListener eventListener;// 카카오 지도 뷰
    private double destinationLatitude;  // 도착지 위도
    private double destinationLongitude; // 도착치 경도

    private LocationManager locationManager;
    private LocationListener locationListener;

    String user_latitude = "latitude";
    String user_longitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(binding.getRoot());
        mapView = binding.mapView;   // 카카오 지도 뷰
        EditText editText = findViewById(R.id.edit_text);
        Button searchButton = findViewById(R.id.button_search);


        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.INTERNET);

        int permission2 = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        int permission3 = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permission == PackageManager.PERMISSION_DENIED || permission2 == PackageManager.PERMISSION_DENIED || permission3 == PackageManager.PERMISSION_DENIED) {
            // 마쉬멜로우 이상버전부터 권한을 물어봄
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 권한 체크(READ_PHONE_STATE의 requestCode를 1000으로 세팅
                requestPermissions(
                        new String[]{android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1000);
            }
            return;
        }


        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        eventListener = new MarkerEventListener(this); // MarkerEventListener 객체 생성
        mapView.setCalloutBalloonAdapter(new CustomBalloonAdapter(getLayoutInflater()));  // 커스텀 말풍선 등록
        mapView.setPOIItemEventListener(eventListener);  // 마커 클릭 이벤트 리스너 등록
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);



        // 서울시청에 마커 추가
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("본관(A1)");   // 마커 이름
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911198621945424, 128.80782238644915));   // 좌표
        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker);


        MapPOIItem marker_a2 = new MapPOIItem();
        marker_a2.setItemName("교양관(A2)");   // 마커 이름
        marker_a2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91177841661396, 128.8067195852139));   // 좌표
        marker_a2.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_a2.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_a2.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_a2.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_a2.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_a2.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_a2);

        MapPOIItem marker_a8 = new MapPOIItem();
        marker_a8.setItemName("중앙도서관(A8)");   // 마커 이름
        marker_a8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91307055462935, 128.8063335837257));   // 좌표
        marker_a8.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_a8.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_a8.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_a8.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_a8.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_a8.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_a8);

        MapPOIItem marker_a9 = new MapPOIItem();
        marker_a9.setItemName("종합강의동(A9)");   // 마커 이름
        marker_a9.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91285292189562, 128.80522647021226));   // 좌표
        marker_a9.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_a9.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_a9.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_a9.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_a9.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_a9.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_a9);

        MapPOIItem marker_b1 = new MapPOIItem();
        marker_b1.setItemName("취창업관(B1)");   // 마커 이름
        marker_b1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91046676604076, 128.80899924597327));   // 좌표
        marker_b1.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_b1.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_b1.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_b1.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_b1.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_b1.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_b1);

        MapPOIItem marker_b2 = new MapPOIItem();
        marker_b2.setItemName("제르멩관(B2)");   // 마커 이름
        marker_b2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9107229905766, 128.80933738230345));   // 좌표
        marker_b2.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_b2.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_b2.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_b2.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_b2.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_b2.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_b2);

        MapPOIItem marker_b3 = new MapPOIItem();
        marker_b3.setItemName("성당(B3)");   // 마커 이름
        marker_b3.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.90989312837552, 128.80969231798457));   // 좌표
        marker_b3.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_b3.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_b3.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_b3.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_b3.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_b3.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_b3);

        MapPOIItem marker_b4 = new MapPOIItem();
        marker_b4.setItemName("국제관(B4)");   // 마커 이름
        marker_b4.setMapPoint(MapPoint.mapPointWithGeoCoord(35.90949034078319, 128.8107077242248));   // 좌표
        marker_b4.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_b4.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_b4.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_b4.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_b4.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_b4.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_b4);

        MapPOIItem marker_b8 = new MapPOIItem();
        marker_b8.setItemName("산학협력관(B8)");   // 마커 이름
        marker_b8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91046676604076, 128.80899924597327));   // 좌표
        marker_b8.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_b8.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_b8.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_b8.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_b8.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_b8.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_b8);

        MapPOIItem marker_c1 = new MapPOIItem();
        marker_c1.setItemName("성토마스모어(C1)");   // 마커 이름
        marker_c1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9127904210839, 128.81087156032328));   // 좌표
        marker_c1.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c1.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c1.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c1.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c1.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c1.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c1);

        MapPOIItem marker_c2 = new MapPOIItem();
        marker_c2.setItemName("성라이문도관(C2)");   // 마커 이름
        marker_c2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91320569912806, 128.81111918428113));   // 좌표
        marker_c2.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c2.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c2.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c2.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c2.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c2.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c2);

        MapPOIItem marker_c4 = new MapPOIItem();
        marker_c4.setItemName("성비토관(C4)");   // 마커 이름
        marker_c4.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91478504395566, 128.8127503242304));   // 좌표
        marker_c4.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c4.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c4.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c4.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c4.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c4.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c4);

        MapPOIItem marker_c6 = new MapPOIItem();
        marker_c6.setItemName("신학관(C6)");   // 마커 이름
        marker_c6.setMapPoint(MapPoint.mapPointWithGeoCoord(35.915392191407214, 128.81192783884129));   // 좌표
        marker_c6.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c6.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c6.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c6.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c6.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c6.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c6);

        MapPOIItem marker_c7 = new MapPOIItem();
        marker_c7.setItemName("성예로니모관(C7)");   // 마커 이름
        marker_c7.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.913759710682264, 128.80993548366703));   // 좌표
        marker_c7.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c7.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c7.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c7.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c7.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c7.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c7);

        MapPOIItem marker_c9 = new MapPOIItem();
        marker_c9.setItemName("성요한보스코관(C9)");   // 마커 이름
        marker_c9.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9127904210839, 128.81087156032328));   // 좌표
        marker_c9.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c9.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c9.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c9.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c9.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c9.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c9);

        MapPOIItem marker_c12 = new MapPOIItem();
        marker_c12.setItemName("성야고보관(C12)");   // 마커 이름
        marker_c12.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91458278174892, 128.80763908906465));   // 좌표
        marker_c12.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c12.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c12.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c12.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c12.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c12.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c12);

        MapPOIItem marker_c13 = new MapPOIItem();
        marker_c13.setItemName("성마태오관(C13)");   // 마커 이름
        marker_c13.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91506562055649, 128.80699097561347));   // 좌표
        marker_c13.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_c13.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_c13.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_c13.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_c13.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_c13.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_c13);

        MapPOIItem marker_d1 = new MapPOIItem();
        marker_d1.setItemName("창업보육센터(D1)");   // 마커 이름
        marker_d1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91417202183079, 128.80229606106218));   // 좌표
        marker_d1.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d1.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d1.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d1.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d1.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d1.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d1);

        MapPOIItem marker_d2 = new MapPOIItem();
        marker_d2.setItemName("공학관(D2)");   // 마커 이름
        marker_d2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91358070953851, 128.8023684925233));   // 좌표
        marker_d2.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d2.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d2.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d2.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d2.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d2.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d2);

        MapPOIItem marker_d5 = new MapPOIItem();
        marker_d5.setItemName("성도미니코관(D5)");   // 마커 이름
        marker_d5.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91254031510512, 128.80399258946068));   // 좌표
        marker_d5.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d5.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d5.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d5.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d5.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d5.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d5);

        MapPOIItem marker_d6 = new MapPOIItem();
        marker_d6.setItemName("성이시도르관(D6)");   // 마커 이름
        marker_d6.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91261815630141, 128.8046589773263));   // 좌표
        marker_d6.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d6.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d6.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d6.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d6.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d6.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d6);

        MapPOIItem marker_d7 = new MapPOIItem();
        marker_d7.setItemName("제1약학관(D7)");   // 마커 이름
        marker_d7.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91202884525934, 128.8047480510831));   // 좌표
        marker_d7.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d7.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d7.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d7.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d7.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d7.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d7);

        MapPOIItem marker_d8 = new MapPOIItem();
        marker_d8.setItemName("정보통신관 (D8)");   // 마커 이름
        marker_d8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911803287449615, 128.80536600165212));   // 좌표
        marker_d8.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d8.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d8.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d8.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d8.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d8.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d8);

        MapPOIItem marker_d9 = new MapPOIItem();
        marker_d9.setItemName("성토마스관(D9)");   // 마커 이름
        marker_d9.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.91131620355209, 128.80599738766466));   // 좌표
        marker_d9.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d9.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d9.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d9.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d9.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d9.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d9);

        MapPOIItem marker_d10 = new MapPOIItem();
        marker_d10.setItemName("제2약학관(D10)");   // 마커 이름
        marker_d10.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91215118255399, 128.80410282962066));   // 좌표
        marker_d10.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d10.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d10.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d10.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d10.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d10.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d10);

        MapPOIItem marker_d11 = new MapPOIItem();
        marker_d11.setItemName("성마르타관(D11)");   // 마커 이름
        marker_d11.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911081799603565, 128.80480684275736));   // 좌표
        marker_d11.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d11.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d11.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d11.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d11.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d11.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d11);


        MapPOIItem marker_d15 = new MapPOIItem();
        marker_d15.setItemName("성카타리나관(D15)");   // 마커 이름
        marker_d15.setMapPoint(MapPoint.mapPointWithGeoCoord(35.909782909664145, 128.80519270752905));   // 좌표
        marker_d15.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d15.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d15.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d15.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d15.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d15.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d15);

        MapPOIItem marker_d17 = new MapPOIItem();
        marker_d17.setItemName("성체칠리아관(D17)");   // 마커 이름
        marker_d17.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91000881119508, 128.80694794882763));   // 좌표
        marker_d17.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d17.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d17.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d17.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d17.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d17.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d17);

        MapPOIItem marker_d18 = new MapPOIItem();
        marker_d18.setItemName("성안나관(D18)");   // 마커 이름
        marker_d18.setMapPoint(MapPoint.mapPointWithGeoCoord(35.909196334305186, 128.80644764111554));   // 좌표
        marker_d18.setMarkerType(MapPOIItem.MarkerType.CustomImage);          // 마커 모양 (커스텀)
        marker_d18.setCustomImageResourceId(R.drawable.custom_marker_red);               // 커스텀 마커 이미지
        marker_d18.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);  // 클릭 시 마커 모양 (커스텀)
        marker_d18.setCustomSelectedImageResourceId(R.drawable.custom_marker_red);       // 클릭 시 커스텀 마커 이미지
        marker_d18.setCustomImageAutoscale(true);      // 커스텀 마커 이미지 크기 자동 조정
        marker_d18.setCustomImageAnchor(0.5f, 1.0f);    // 마커 이미지 기준점
        mapView.addPOIItem(marker_d18);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 입력된 마커 이름 가져오기
                String name = editText.getText().toString().trim();

                MapView mapView = findViewById(R.id.map_view);
                MapPOIItem[] markers = mapView.getPOIItems();

                boolean markerFound = false;
                for (MapPOIItem marker : markers) {
                    if (marker.getItemName().toLowerCase().contains(name.toLowerCase())) {
                        mapView.selectPOIItem(marker, true);
                        mapView.setMapCenterPoint(marker.getMapPoint(), true);
                        markerFound = true;
                        break; // 마커를 찾았으면 루프 종료
                    }
                }

                if (!markerFound) {
                    Toast.makeText(getApplicationContext(), "입력하신 " + name + "에 해당하는 건물이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



    public MapView getMapView() {
        return mapView;
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        // READ_PHONE_STATE의 권한 체크 결과를 불러옴
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if (requestCode == 1000) {
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            // 권한 체크에 동의를 하지 않으면 안드로이드 종료
            if (check_result == false) {
                finish();
            }
        }
    }


    // 커스텀 말풍선 클래스
    class CustomBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;
        private final TextView name;


        public CustomBalloonAdapter(LayoutInflater inflater) {
            mCalloutBalloon = inflater.inflate(R.layout.balloon_layout, null);
            name = mCalloutBalloon.findViewById(R.id.ball_tv_name);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {

            if (poiItem.getItemName().equals("본관(A1)")) {
                name.setText("본관(A1)");
            }

            else if (poiItem.getItemName().equals("교양관(A2)")) {
                name.setText("교양관(A2)");
            }

            else if (poiItem.getItemName().equals("중앙도서관(A8)")) {
                name.setText("중앙도서관(A8)");
            }

            else if (poiItem.getItemName().equals("종합강의동(A9)")) {
                name.setText("종합강의동(A9)");
            }

            else if (poiItem.getItemName().equals("취창업관(B1)")) {
                name.setText("취창업관(B1)");
            }

            else if (poiItem.getItemName().equals("제르멩관(B2)")) {
                name.setText("제르멩관(B2)");
            }

            else if (poiItem.getItemName().equals("성당(B3)")) {
                name.setText("성당(B3)");
            }

            else if (poiItem.getItemName().equals("국제관(B4)")) {
                name.setText("국제관(B4)");
            }

            else if (poiItem.getItemName().equals("산학협력관(B8)")) {
                name.setText("산학협력관(B8)");
            }

            else if (poiItem.getItemName().equals("성토마스모어(C1)")) {
                name.setText("성토마스모어(C1)");
            }

            else if (poiItem.getItemName().equals("성라이문도관(C2)")) {
                name.setText("성라이문도관(C2)");
            }

            else if (poiItem.getItemName().equals("성비토관(C4)")) {
                name.setText("성비토관(C4)");
            }

            else if (poiItem.getItemName().equals("신학관(C6)")) {
                name.setText("신학관(C6)");
            }

            else if (poiItem.getItemName().equals("성예로니모관(C7)")) {
                name.setText("성예로니모관(C7)");
            }

            else if (poiItem.getItemName().equals("성요한보스코관(C9)")) {
                name.setText("성요한보스코관(C9)");
            }

            else if (poiItem.getItemName().equals("성야고보관(C12)")) {
                name.setText("성야고보관(C12)");
            }

            else if (poiItem.getItemName().equals("성마태오관(C13)")) {
                name.setText("성마태오관(C13)");
            }

            else if (poiItem.getItemName().equals("창업보육센터(D1)")) {
                name.setText("창업보육센터(D1)");
            }

            else if (poiItem.getItemName().equals("공학관(D2)")) {
                name.setText("공학관(D2)");
            }

            else if (poiItem.getItemName().equals("성도미니코관(D5)")) {
                name.setText("성도미니코관(D5)");
            }

            else if (poiItem.getItemName().equals("성이시도르관(D6)")) {
                name.setText("성이시도르관(D6)");
            }

            else if (poiItem.getItemName().equals("제1약학관(D7)")) {
                name.setText("제1약학관(D7)");
            }

            else if (poiItem.getItemName().equals("정보통신관(D8)")) {
                name.setText("정보통신관(D8)");
            }

            else if (poiItem.getItemName().equals("정보통신관 (D8)")) {
                name.setText("정보통신관 (D8)");
            }

            else if (poiItem.getItemName().equals("성토마스관(D9)")) {
                name.setText("성토마스관(D9)");
            }

            else if (poiItem.getItemName().equals("제2약학관(D10)")) {
                name.setText("제2약학관(D10)");
            }

            else if (poiItem.getItemName().equals("성마르타관(D11)")) {
                name.setText("성마르타관(D11)");
            }

            else if (poiItem.getItemName().equals("성카타리나관(D15)")) {
                name.setText("성카타리나관(D15)");
            }

            else if (poiItem.getItemName().equals("성체칠리아관(D17)")) {
                name.setText("성체칠리아관(D17)");
            }

            else if (poiItem.getItemName().equals("성안나관(D18)")) {
                name.setText("성안나관(D18)");
            }

            // 마커 클릭 시 나오는 말풍선
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            // 말풍선 클릭 시
            return mCalloutBalloon;
        }
    }

    public class MarkerEventListener implements MapView.POIItemEventListener {
        private Context context;

        public MarkerEventListener(Context context) {
            this.context = context;
        }

        @Override
        public void onPOIItemSelected(MapView mapView, MapPOIItem poiItem) {
            // 마커 클릭 시
        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem poiItem) {
            // 말풍선 클릭 시 (Deprecated)
        }

        public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem poiItem, MapPoint mapPoint) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem poiItem, MapPOIItem.CalloutBalloonButtonType buttonType) {
            // 말풍선 클릭 시
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            String[] itemList = {"길찾기", "상세정보", "취소"};
            builder.setTitle(poiItem.getItemName());
            builder.setItems(itemList, (dialog, which) -> {
                if(poiItem.getItemName().equals("본관(A1)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.911198621945424;   // 서울시청의 도착지 위도 설정
                            destinationLongitude = 128.80782238644915; // 서울시청의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_a1.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("교양관(A2)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91177841661396;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8067195852139; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_a2.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("중앙도서관(A8)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91307055462935;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8063335837257; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_a8.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("종합강의동(A9)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91285292189562;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80522647021226; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_a9.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("취창업관(B1)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91046676604076;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80899924597327; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_b1.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("제르멩관(B2)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.9107229905766;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80933738230345; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_b2.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성당(B3)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.90989312837552;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80969231798457; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_b3.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("국제관(B4)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.90949034078319;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8107077242248; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_b4.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("산학협력관(B8)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91046676604076;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80899924597327; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_b8.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성토마스모어(C1)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.9127904210839;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.81087156032328; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c1.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성라이문도관(C2)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91320569912806;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.81111918428113; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c2.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성비토관(C4)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91478504395566;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8127503242304; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c4.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("신학관(C6)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.915392191407214;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.81192783884129; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c6.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성예로니모관(C7)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.913759710682264;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80993548366703; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c7.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성요한보스코관(C9)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.9127904210839;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.81087156032328; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c9.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성야고보관(C12)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91458278174892;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80763908906465; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c12.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성마태오관(C13)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91506562055649;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80699097561347; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_c13.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("창업보육센터(D1)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91417202183079;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80229606106218; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d1.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("공학관(D2)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91358070953851;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8023684925233; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d2.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성도미니코관(D5)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91254031510512;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80399258946068; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d5.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성이시도르관(D6)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91261815630141;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8046589773263; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d6.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("제1약학관(D7)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91202884525934;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.8047480510831; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d7.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("정보통신관 (D8)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.911803287449615;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80536600165212; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d8.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성토마스관(D9)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91131620355209;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80599738766466; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d9.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("제2약학관(D10)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91215118255399;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80410282962066; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d10.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성마르타관(D11)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.911081799603565;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80480684275736; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d11.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성카타리나관(D15)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.909782909664145;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80519270752905; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d15.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성체칠리아관(D17)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.91000881119508;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80694794882763; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d17.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }

                else if(poiItem.getItemName().equals("성안나관(D18)")) {
                    switch (which) {
                        case 0:
                            destinationLatitude = 35.909196334305186;   // 새로운 마커의 도착지 위도 설정
                            destinationLongitude = 128.80644764111554; // 새로운 마커의 도착지 경도 설정
                            startNavigation();
                            break;
                        case 1:
                            Intent intent = new Intent(MainActivity.this, guide_d18.class);
                            startActivity(intent);
                            break;
                        case 2:
                            dialog.dismiss();   // 대화상자 닫기
                            break;
                    }

                }






            });



            builder.show();
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
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
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




