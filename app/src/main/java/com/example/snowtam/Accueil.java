package com.example.snowtam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.nav_bot);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        ArrayList<String> aero = new ArrayList<>();
        aero.add("ENSB");
        aero.add("ENVA");
        aero.add("ENBO");
        aero.add("BGUK");

        Button valide =  (Button) findViewById(R.id.buttonValidate);
        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this,Affsnowtam.class);
                i.putExtra("Liste",aero);
                i.putExtra("id",0);
                startActivity(i);

            }
        });

    }
}