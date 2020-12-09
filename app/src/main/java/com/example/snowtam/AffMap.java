package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snowtam.Model.Geometry;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AffMap extends AppCompatActivity {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_map);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geometry geo = (Geometry) getIntent().getSerializableExtra("coord");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(geo.getCoordinates().get(1), geo.getCoordinates().get(0));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in airport"));
        mMap.setMinZoomPreference(14.0f);
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}