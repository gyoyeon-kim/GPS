package com.cookandroid.kmap_test;

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {
    private View custom_balloon;
    private View custom_balloon_a2;
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

        // Custom Balloon View 설정
        MapCalloutBalloonAdapter adapter = new MapCalloutBalloonAdapter(context);
        custom_balloon = adapter.getCalloutBalloon(null);
        custom_balloon_a2 = adapter.getCalloutBalloon(null);

        TextView titleTextView = custom_balloon.findViewById(R.id.title);
        if (titleTextView != null) {
            titleTextView.setText("Custom Marker0");
        }


        // MapPOIItem 객체 생성
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("Custom Marker1");
        customMarker.setTag(0);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.565643773252724, 126.97797346299939));
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

                    Intent intent = new Intent(getApplicationContext(), guide_a2.class);
                    startActivity(intent);
                }
            });
        }

    }
}
