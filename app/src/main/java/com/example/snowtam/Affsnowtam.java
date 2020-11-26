package com.example.snowtam;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Response;
import com.example.snowtam.Model.DataSearchAirport;
import com.example.snowtam.Model.DataSearchSnow;
import com.example.snowtam.Model.Geometry;
import com.example.snowtam.Model.SnowTam;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Affsnowtam extends AppCompatActivity {
    Geometry coord ;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affsnowtam);
        TextView tv = (TextView) findViewById(R.id.title);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.nav_bot);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        ArrayList<String> aero = getIntent().getStringArrayListExtra("List");
        int Index = getIntent().getIntExtra("id",0);
        final Response.Listener<DataSearchAirport[]> rep =response -> {
            tv.setText(response[0].getAirportName());
            this.coord = response[0].getGeometry();
        };
        final Response.ErrorListener errorListener= error -> {
            Log.e("Error", "searchAirport onErrorResponse: " + error.getMessage());
        };
        //SnowTam.getAirport(this, aero.get(Index),rep,errorListener);



        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),this,aero.get(Index));
        Nsviewpager viewPager =(Nsviewpager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        viewPager.setEnableSwipe(false);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        viewPager.setOnTouchListener(new OnSwypeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(Index != aero.size()-1){
                    Intent i = new Intent(Affsnowtam.this,Affsnowtam.class);
                    i.putExtra("List",aero);
                    i.putExtra("id",Index+1);
                    startActivity(i);
                }else {
                    Toast.makeText(Affsnowtam.this,"Impossible",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if(Index !=0){
                    Intent i = new Intent(Affsnowtam.this,Affsnowtam.class);
                    i.putExtra("List",aero);
                    i.putExtra("id",Index-1);
                    startActivity(i);
                }else{
                    Toast.makeText(Affsnowtam.this,"Impossible",Toast.LENGTH_LONG).show();
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setEnableSwipe(false);
                Intent intent = new Intent(view.getContext(),Affariport.class);
                intent.putExtra("coord",coord);
                startActivity(intent);


            }
        });
    }
}