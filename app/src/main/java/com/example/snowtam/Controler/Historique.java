package com.example.snowtam.Controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.snowtam.Model.Injection;
import com.example.snowtam.Model.Recherche;
import com.example.snowtam.Model.RechercheAdapter;
import com.example.snowtam.Model.RechercheVM;
import com.example.snowtam.Model.VmFactory;
import com.example.snowtam.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Historique extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RechercheVM rechercheVM;
    private RechercheAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.nav_bot);
        bottomNavigationView.setSelectedItemId(R.id.action_landscape);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals(getString(R.string.Help)))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Historique.this);
                    builder.setTitle(getString(R.string.Help));
                    builder.setMessage(getString(R.string.help));
                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                if(item.getTitle().equals(getString(R.string.Accueil))){
                    Intent i = new Intent(Historique.this, Accueil.class);
                    startActivity(i);
                }
                if(item.getTitle().equals(getString(R.string.Historic))){
                    Intent i = new Intent(Historique.this,Historique.class);
                    startActivity(i);
                }
                return false;
            }
        });
        this.configureVM();
        ArrayList<Recherche> recherches = new ArrayList<Recherche>();
        listView = (ListView) findViewById(R.id.list_recherches);
        adapter = new RechercheAdapter(this,recherches);
        listView.setAdapter(adapter);
        this.rechercheVM.getRecherches().observe(this,this::updateList);




    }

    private void updateList(List<Recherche> recherches) {
        if(adapter == null){
            adapter = new RechercheAdapter(this,new ArrayList<>(recherches));
            listView.setAdapter(adapter);
        }
        else{
            adapter.clear();
            adapter.addAll(recherches);
            adapter.notifyDataSetChanged();
        }
    }

    private void configureVM(){
        VmFactory vmFactory = Injection.provideVmFactory(this);
        this.rechercheVM = new ViewModelProvider(this,vmFactory).get(RechercheVM.class);

    }
}