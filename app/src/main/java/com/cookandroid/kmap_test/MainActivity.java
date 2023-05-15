package com.cookandroid.kmap_test;

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {
    private View custom_balloon;
    private View custom_balloon_a1,custom_balloon_a2,custom_balloon_a8,custom_balloon_a9;
    private View custom_balloon_b1,custom_balloon_b2,custom_balloon_b3,custom_balloon_b4,custom_balloon_b8;
    private View custom_balloon_c1,custom_balloon_c2,custom_balloon_c4,custom_balloon_c6,custom_balloon_c7,custom_balloon_c9,custom_balloon_c12,custom_balloon_c13;
    private View custom_balloon_d2,custom_balloon_d5,custom_balloon_d6,custom_balloon_d7,custom_balloon_d8,custom_balloon_d9,custom_balloon_d10,custom_balloon_d11,custom_balloon_d12,custom_balloon_d15,custom_balloon_d17,custom_balloon_d18;
    private Context context;

    

    public class MapCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final LayoutInflater inflater;

        public MapCalloutBalloonAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            View view = inflater.inflate(R.layout.custom_balloon, null);
            View view2 = inflater.inflate(R.layout.custom_balloon_a2, null);

            // custom balloon view 설정
            if (poiItem != null) {
                TextView titleTextView = view.findViewById(R.id.title);
                titleTextView.setText(poiItem.getItemName());
            }

            return view;
        }


        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return custom_balloon;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.edit_text);
        Button searchButton = findViewById(R.id.button_search);


        // Custom Balloon View 설정
        MapCalloutBalloonAdapter adapter = new MapCalloutBalloonAdapter(context);
        custom_balloon = adapter.getCalloutBalloon(null);
        custom_balloon_a2 = adapter.getCalloutBalloon(null);
        custom_balloon_d2 = adapter.getCalloutBalloon(null);
        custom_balloon_d5 = adapter.getCalloutBalloon(null);
        custom_balloon_d6 = adapter.getCalloutBalloon(null);
        custom_balloon_d7 = adapter.getCalloutBalloon(null);
        custom_balloon_d8 = adapter.getCalloutBalloon(null);
        custom_balloon_d9 = adapter.getCalloutBalloon(null);
        custom_balloon_d10 = adapter.getCalloutBalloon(null);
        custom_balloon_d11 = adapter.getCalloutBalloon(null);
        custom_balloon_d15 = adapter.getCalloutBalloon(null);
        custom_balloon_d17 = adapter.getCalloutBalloon(null);
        custom_balloon_d18 = adapter.getCalloutBalloon(null);
        custom_balloon_a1 = adapter.getCalloutBalloon(null);
        custom_balloon_a2 = adapter.getCalloutBalloon(null);
        custom_balloon_a8 = adapter.getCalloutBalloon(null);
        custom_balloon_a9 = adapter.getCalloutBalloon(null);
        custom_balloon_b1 = adapter.getCalloutBalloon(null);
        custom_balloon_b2 = adapter.getCalloutBalloon(null);
        custom_balloon_b3 = adapter.getCalloutBalloon(null);
        custom_balloon_b4 = adapter.getCalloutBalloon(null);
        custom_balloon_b8 = adapter.getCalloutBalloon(null);
        custom_balloon_c1 = adapter.getCalloutBalloon(null);
        custom_balloon_c2 = adapter.getCalloutBalloon(null);
        custom_balloon_c4 = adapter.getCalloutBalloon(null);
        custom_balloon_c6 = adapter.getCalloutBalloon(null);
        custom_balloon_c7 = adapter.getCalloutBalloon(null);
        custom_balloon_c9 = adapter.getCalloutBalloon(null);
        custom_balloon_c12 = adapter.getCalloutBalloon(null);
        custom_balloon_c13 = adapter.getCalloutBalloon(null);




        TextView titleTextView = custom_balloon.findViewById(R.id.title);
        if (titleTextView != null) {
            titleTextView.setText("Custom Marker");
        }


        // MapPOIItem 객체 생성
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("창업보육센터(D1)");
        customMarker.setTag(0);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91417202183079, 128.80229606106218));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker));
        customMarker.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker));

        // 지도에 마커 추가
        MapView mapView = findViewById(R.id.map_view);
        mapView.addPOIItem(customMarker);

        Button btn = custom_balloon.findViewById(R.id.custom_balloon_btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        TextView titleTextView2 = custom_balloon_a2.findViewById(R.id.title);
        if (titleTextView2 != null) {
            titleTextView2.setText("Custom Marker");
        }

        MapPOIItem customMarker1 = new MapPOIItem();
        customMarker1.setItemName("교양관(A2)");
        customMarker1.setTag(0);
        customMarker1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91177841661396, 128.8067195852139));
        customMarker1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker1.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker1.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker1));
        customMarker1.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker1));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker1);

        Button btn2 = custom_balloon_a2.findViewById(R.id.custom_balloon_btn_a2_guide);
        if (btn2 != null) {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D2 = custom_balloon_d2.findViewById(R.id.title);
        if (titleTextView2 != null) {
            titleTextView2.setText("Custom Marker");
        }

        MapPOIItem customMarker_D2 = new MapPOIItem();
        customMarker_D2.setItemName("공학관(D2)");
        customMarker_D2.setTag(0);
        customMarker_D2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91358070953851, 128.8023684925233));
        customMarker_D2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D2.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D2.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D2));
        customMarker_D2.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D2));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D2);

        Button btn_D2 = custom_balloon_d2.findViewById(R.id.custom_balloon_btn_d2_guide);
        if (btn_D2 != null) {
            btn_D2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D5 = custom_balloon_d5.findViewById(R.id.title);
        if (titleTextView_D5 != null) {
            titleTextView_D5.setText("Custom Marker");
        }

        MapPOIItem customMarker_D5 = new MapPOIItem();
        customMarker_D5.setItemName("성도미니코관(D5)");
        customMarker_D5.setTag(0);
        customMarker_D5.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91254031510512, 128.80399258946068));
        customMarker_D5.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D5.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D5.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D2));
        customMarker_D5.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D2));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D5);

        Button btn_D5 = custom_balloon_d5.findViewById(R.id.custom_balloon_btn_d5_guide);
        if (btn_D5 != null) {
            btn_D5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D6 = custom_balloon_d6.findViewById(R.id.title);
        if (titleTextView_D6 != null) {
            titleTextView_D6.setText("Custom Marker");
        }

        MapPOIItem customMarker_D6 = new MapPOIItem();
        customMarker_D6.setItemName("성이시도르관(D6)");
        customMarker_D6.setTag(0);
        customMarker_D6.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91261815630141, 128.8046589773263));
        customMarker_D6.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D6.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D6.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D6));
        customMarker_D6.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D6));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D2);

        Button btn_D6 = custom_balloon_d6.findViewById(R.id.custom_balloon_btn_d6_guide);
        if (btn_D6 != null) {
            btn_D6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D7 = custom_balloon_d7.findViewById(R.id.title);
        if (titleTextView_D7 != null) {
            titleTextView_D7.setText("Custom Marker");
        }

        MapPOIItem customMarker_D7 = new MapPOIItem();
        customMarker_D7.setItemName("제1약학관(D7)");
        customMarker_D7.setTag(0);
        customMarker_D7.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91202884525934, 128.8047480510831));
        customMarker_D7.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D7.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D7.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D7));
        customMarker_D7.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D7));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D7);

        Button btn_D7 = custom_balloon_d7.findViewById(R.id.custom_balloon_btn_d7_guide);
        if (btn_D7 != null) {
            btn_D7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D8 = custom_balloon_d8.findViewById(R.id.title);
        if (titleTextView_D8 != null) {
            titleTextView_D8.setText("Custom Marker");
        }

        MapPOIItem customMarker_D8 = new MapPOIItem();
        customMarker_D8.setItemName("정보통신관(D8)");
        customMarker_D8.setTag(0);
        customMarker_D8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911803287449615, 128.80536600165212));
        customMarker_D8.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D8.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D8.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D8));
        customMarker_D8.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D8));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D8);

        Button btn_D8 = custom_balloon_d8.findViewById(R.id.custom_balloon_btn_d8_guide);
        if (btn_D8 != null) {
            btn_D8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D9 = custom_balloon_d9.findViewById(R.id.title);
        if (titleTextView_D9 != null) {
            titleTextView_D9.setText("Custom Marker");
        }

        MapPOIItem customMarker_D9 = new MapPOIItem();
        customMarker_D9.setItemName("성토마스관(D9)");
        customMarker_D9.setTag(0);
        customMarker_D9.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.91131620355209, 128.80599738766466));
        customMarker_D9.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D9.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D9.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D9));
        customMarker_D9.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D9));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D9);

        Button btn_D9 = custom_balloon_d9.findViewById(R.id.custom_balloon_btn_d9_guide);
        if (btn_D9 != null) {
            btn_D9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D10 = custom_balloon_d10.findViewById(R.id.title);
        if (titleTextView_D10 != null) {
            titleTextView_D10.setText("Custom Marker");
        }

        MapPOIItem customMarker_D10 = new MapPOIItem();
        customMarker_D10.setItemName("제2약학관(D10)");
        customMarker_D10.setTag(0);
        customMarker_D10.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91215118255399, 128.80410282962066));
        customMarker_D10.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D10.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D10.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D10));
        customMarker_D10.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D10));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D10);

        Button btn_D10 = custom_balloon_d10.findViewById(R.id.custom_balloon_btn_d10_guide);
        if (btn_D10 != null) {
            btn_D10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D11 = custom_balloon_d11.findViewById(R.id.title);
        if (titleTextView_D11 != null) {
            titleTextView_D11.setText("Custom Marker");
        }

        MapPOIItem customMarker_D11 = new MapPOIItem();
        customMarker_D11.setItemName("성마르타관(D11)");
        customMarker_D11.setTag(0);
        customMarker_D11.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911081799603565, 128.80480684275736));
        customMarker_D11.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D11.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D11.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D11));
        customMarker_D11.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D11));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D11);

        Button btn_D11 = custom_balloon_d11.findViewById(R.id.custom_balloon_btn_d11_guide);
        if (btn_D11 != null) {
            btn_D11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }



        TextView titleTextView_D15 = custom_balloon_d15.findViewById(R.id.title);
        if (titleTextView_D15 != null) {
            titleTextView_D15.setText("Custom Marker");
        }

        MapPOIItem customMarker_D15 = new MapPOIItem();
        customMarker_D15.setItemName("성카타리나관(D15)");
        customMarker_D15.setTag(0);
        customMarker_D15.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.909782909664145, 128.80519270752905));
        customMarker_D15.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D15.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D15.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D15));
        customMarker_D15.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D15));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D15);

        Button btn_D15 = custom_balloon_d15.findViewById(R.id.custom_balloon_btn_d15_guide);
        if (btn_D15 != null) {
            btn_D15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D17 = custom_balloon_d17.findViewById(R.id.title);
        if (titleTextView_D17 != null) {
            titleTextView_D17.setText("Custom Marker");
        }

        MapPOIItem customMarker_D17 = new MapPOIItem();
        customMarker_D17.setItemName("성체치릴아관(D17)");
        customMarker_D17.setTag(0);
        customMarker_D17.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91000881119508, 128.80694794882763));
        customMarker_D17.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D17.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D17.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D17));
        customMarker_D17.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D17));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D17);

        Button btn_D17 = custom_balloon_d17.findViewById(R.id.custom_balloon_btn_d17_guide);
        if (btn_D17 != null) {
            btn_D17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_D18 = custom_balloon_d18.findViewById(R.id.title);
        if (titleTextView_D18 != null) {
            titleTextView_D18.setText("Custom Marker");
        }

        MapPOIItem customMarker_D18 = new MapPOIItem();
        customMarker_D18.setItemName("성안나관(D18)");
        customMarker_D18.setTag(0);
        customMarker_D18.setMapPoint(MapPoint.mapPointWithGeoCoord(35.909196334305186, 128.80644764111554));
        customMarker_D18.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_D18.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_D18.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_D18));
        customMarker_D18.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_D18));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_D18);

        Button btn_D18 = custom_balloon_d18.findViewById(R.id.custom_balloon_btn_d18_guide);
        if (btn_D18 != null) {
            btn_D18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_A1 = custom_balloon_a1.findViewById(R.id.title);
        if (titleTextView_A1 != null) {
            titleTextView_A1.setText("Custom Marker");
        }

        MapPOIItem customMarker_A1 = new MapPOIItem();
        customMarker_A1.setItemName("본관(A1)");
        customMarker_A1.setTag(0);
        customMarker_A1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.911198621945424, 128.80782238644915));
        customMarker_A1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_A1.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_A1.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_A1));
        customMarker_A1.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_A1));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_A1);

        Button btn_A1 = custom_balloon_a1.findViewById(R.id.custom_balloon_btn_a1_guide);
        if (btn_A1 != null) {
            btn_A1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }



        TextView titleTextView_A8 = custom_balloon_a8.findViewById(R.id.title);
        if (titleTextView_A8 != null) {
            titleTextView_A8.setText("Custom Marker");
        }

        MapPOIItem customMarker_A8 = new MapPOIItem();
        customMarker_A8.setItemName("중앙도서관(A8)");
        customMarker_A8.setTag(0);
        customMarker_A8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91307055462935, 128.8063335837257));
        customMarker_A8.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_A8.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_A8.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_A8));
        customMarker_A8.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_A8));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_A8);

        Button btn_A8 = custom_balloon_a8.findViewById(R.id.custom_balloon_btn_a8_guide);
        if (btn_A8 != null) {
            btn_A8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_A9 = custom_balloon_a9.findViewById(R.id.title);
        if (titleTextView_A9 != null) {
            titleTextView_A9.setText("Custom Marker");
        }

        MapPOIItem customMarker_A9 = new MapPOIItem();
        customMarker_A9.setItemName("종합강의동(A9)");
        customMarker_A9.setTag(0);
        customMarker_A9.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91285292189562, 128.80522647021226));
        customMarker_A9.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_A9.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_A9.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_A9));
        customMarker_A9.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_A9));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_A9);

        Button btn_A9 = custom_balloon_a9.findViewById(R.id.custom_balloon_btn_a9_guide);
        if (btn_A9 != null) {
            btn_A9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_B1 = custom_balloon_b1.findViewById(R.id.title);
        if (titleTextView_B1 != null) {
            titleTextView_B1.setText("Custom Marker");
        }

        MapPOIItem customMarker_B1 = new MapPOIItem();
        customMarker_B1.setItemName("취창업관(B1)");
        customMarker_B1.setTag(0);
        customMarker_B1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91046676604076, 128.80899924597327));
        customMarker_B1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_B1.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_B1.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_B1));
        customMarker_B1.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_B1));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_B1);

        Button btn_B1 = custom_balloon_b1.findViewById(R.id.custom_balloon_btn_b1_guide);
        if (btn_B1 != null) {
            btn_B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_B2 = custom_balloon_b2.findViewById(R.id.title);
        if (titleTextView_B2 != null) {
            titleTextView_B2.setText("Custom Marker");
        }

        MapPOIItem customMarker_B2 = new MapPOIItem();
        customMarker_B2.setItemName("제르멩관(B2)");
        customMarker_B2.setTag(0);
        customMarker_B2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9107229905766, 128.80933738230345));
        customMarker_B2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_B2.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_B2.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_B2));
        customMarker_B2.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_B2));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_B2);

        Button btn_B2 = custom_balloon_b2.findViewById(R.id.custom_balloon_btn_b2_guide);
        if (btn_B2 != null) {
            btn_B2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_B3 = custom_balloon_b3.findViewById(R.id.title);
        if (titleTextView_B3 != null) {
            titleTextView_B3.setText("Custom Marker");
        }

        MapPOIItem customMarker_B3 = new MapPOIItem();
        customMarker_B3.setItemName("성당(B3)");
        customMarker_B3.setTag(0);
        customMarker_B3.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.90989312837552, 128.80969231798457));
        customMarker_B3.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_B3.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_B3.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_B3));
        customMarker_B3.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_B3));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_B3);

        Button btn_B3 = custom_balloon_b3.findViewById(R.id.custom_balloon_btn_b3_guide);
        if (btn_B3 != null) {
            btn_B3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_B4 = custom_balloon_b4.findViewById(R.id.title);
        if (titleTextView_B4 != null) {
            titleTextView_B4.setText("Custom Marker");
        }

        MapPOIItem customMarker_B4 = new MapPOIItem();
        customMarker_B4.setItemName("국제관(B4)");
        customMarker_B4.setTag(0);
        customMarker_B4.setMapPoint(MapPoint.mapPointWithGeoCoord(35.90949034078319, 128.8107077242248));
        customMarker_B4.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_B4.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_B4.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_B4));
        customMarker_B4.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_B4));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_B4);

        Button btn_B4 = custom_balloon_b4.findViewById(R.id.custom_balloon_btn_b4_guide);
        if (btn_B4 != null) {
            btn_B4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_B8 = custom_balloon_b1.findViewById(R.id.title);
        if (titleTextView_B8 != null) {
            titleTextView_B8.setText("Custom Marker");
        }

        MapPOIItem customMarker_B8 = new MapPOIItem();
        customMarker_B8.setItemName("산학협력관(B8)");
        customMarker_B8.setTag(0);
        customMarker_B8.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91046676604076, 128.80899924597327));
        customMarker_B8.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_B8.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_B8.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_B8));
        customMarker_B8.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_B8));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_B1);

        Button btn_B8 = custom_balloon_b8.findViewById(R.id.custom_balloon_btn_b8_guide);
        if (btn_B8 != null) {
            btn_B8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C1 = custom_balloon_c1.findViewById(R.id.title);
        if (titleTextView_C1 != null) {
            titleTextView_C1.setText("Custom Marker");
        }

        MapPOIItem customMarker_C1 = new MapPOIItem();
        customMarker_C1.setItemName("성토마스모어(C1)");
        customMarker_C1.setTag(0);
        customMarker_C1.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9127904210839, 128.81087156032328));
        customMarker_C1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C1.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C1.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C1));
        customMarker_C1.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C1));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C1);

        Button btn_C1 = custom_balloon_c1.findViewById(R.id.custom_balloon_btn_c1_guide);
        if (btn_C1 != null) {
            btn_C1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C2 = custom_balloon_c1.findViewById(R.id.title);
        if (titleTextView_C2 != null) {
            titleTextView_C2.setText("Custom Marker");
        }

        MapPOIItem customMarker_C2 = new MapPOIItem();
        customMarker_C2.setItemName("성라이문도관(C2)");
        customMarker_C2.setTag(0);
        customMarker_C2.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91320569912806, 128.81111918428113));
        customMarker_C2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C2.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C2.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C2));
        customMarker_C2.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C2));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C2);

        Button btn_C2 = custom_balloon_c2.findViewById(R.id.custom_balloon_btn_c2_guide);
        if (btn_C2 != null) {
            btn_C2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C4 = custom_balloon_c4.findViewById(R.id.title);
        if (titleTextView_C4 != null) {
            titleTextView_C4.setText("Custom Marker");
        }

        MapPOIItem customMarker_C4 = new MapPOIItem();
        customMarker_C4.setItemName("무용관,성비토관(C4)");
        customMarker_C4.setTag(0);
        customMarker_C4.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91478504395566, 128.8127503242304));
        customMarker_C4.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C4.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C4.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C4));
        customMarker_C4.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C4));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C4);

        Button btn_C4 = custom_balloon_c4.findViewById(R.id.custom_balloon_btn_c4_guide);
        if (btn_C4 != null) {
            btn_C4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C6 = custom_balloon_c6.findViewById(R.id.title);
        if (titleTextView_C6 != null) {
            titleTextView_C6.setText("Custom Marker");
        }

        MapPOIItem customMarker_C6 = new MapPOIItem();
        customMarker_C6.setItemName("신학관(C6)");
        customMarker_C6.setTag(0);
        customMarker_C6.setMapPoint(MapPoint.mapPointWithGeoCoord(35.915392191407214, 128.81192783884129));
        customMarker_C6.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C6.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C6.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C6));
        customMarker_C6.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C6));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C6);

        Button btn_C6 = custom_balloon_c6.findViewById(R.id.custom_balloon_btn_c6_guide);
        if (btn_C6 != null) {
            btn_C6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C7 = custom_balloon_c1.findViewById(R.id.title);
        if (titleTextView_C7 != null) {
            titleTextView_C7.setText("Custom Marker");
        }

        MapPOIItem customMarker_C7 = new MapPOIItem();
        customMarker_C7.setItemName("성예로니모관(C7)");
        customMarker_C7.setTag(0);
        customMarker_C7.setMapPoint(MapPoint.mapPointWithGeoCoord( 35.913759710682264, 128.80993548366703));
        customMarker_C7.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C7.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C7.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C7));
        customMarker_C7.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C7));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C7);

        Button btn_C7 = custom_balloon_c7.findViewById(R.id.custom_balloon_btn_c7_guide);
        if (btn_C7 != null) {
            btn_C7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C9 = custom_balloon_c9.findViewById(R.id.title);
        if (titleTextView_C9 != null) {
            titleTextView_C9.setText("Custom Marker");
        }

        MapPOIItem customMarker_C9 = new MapPOIItem();
        customMarker_C9.setItemName("성요한보스코관(C9)");
        customMarker_C9.setTag(0);
        customMarker_C9.setMapPoint(MapPoint.mapPointWithGeoCoord(35.9127904210839, 128.81087156032328));
        customMarker_C9.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C9.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C9.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C9));
        customMarker_C9.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C9));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C9);

        Button btn_C9 = custom_balloon_c1.findViewById(R.id.custom_balloon_btn_c9_guide);
        if (btn_C9 != null) {
            btn_C9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C12 = custom_balloon_c12.findViewById(R.id.title);
        if (titleTextView_C12 != null) {
            titleTextView_C12.setText("Custom Marker");
        }

        MapPOIItem customMarker_C12 = new MapPOIItem();
        customMarker_C12.setItemName("성야고보관(C12)");
        customMarker_C12.setTag(0);
        customMarker_C12.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91458278174892, 128.80763908906465));
        customMarker_C12.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C12.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C12.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C12));
        customMarker_C12.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C12));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C12);

        Button btn_C12 = custom_balloon_c12.findViewById(R.id.custom_balloon_btn_c12_guide);
        if (btn_C12 != null) {
            btn_C12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

        TextView titleTextView_C13 = custom_balloon_c13.findViewById(R.id.title);
        if (titleTextView_C13 != null) {
            titleTextView_C13.setText("Custom Marker");
        }

        MapPOIItem customMarker_C13 = new MapPOIItem();
        customMarker_C13.setItemName("성마태오관(C13)");
        customMarker_C13.setTag(0);
        customMarker_C13.setMapPoint(MapPoint.mapPointWithGeoCoord(35.91506562055649, 128.80699097561347));
        customMarker_C13.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        customMarker_C13.setCustomImageResourceId(R.drawable.custom_marker_red);

        // Custom Balloon View 설정
        customMarker_C13.setCustomCalloutBalloon(adapter.getCalloutBalloon(customMarker_C13));
        customMarker_C13.setCustomPressedCalloutBalloon(adapter.getPressedCalloutBalloon(customMarker_C13));

        // 지도에 마커 추가
        mapView.addPOIItem(customMarker_C13);

        Button btn_C13 = custom_balloon_c13.findViewById(R.id.custom_balloon_btn_c13_guide);
        if (btn_C13 != null) {
            btn_C13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                }
            });
        }

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
                    }
                }

                if (!markerFound) {
                    Toast.makeText(getApplicationContext(),  "건물이 존재하지 않습니다. 다시 입력해주세요. ", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

}
