package com.example.snowtam.Controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.snowtam.Model.Injection;
import com.example.snowtam.Model.Recherche;
import com.example.snowtam.Model.RechercheVM;
import com.example.snowtam.Model.VmFactory;
import com.example.snowtam.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RechercheVM rechercheVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.nav_bot);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals(getString(R.string.Help)))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Accueil.this);
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
                if(item.getTitle().equals(getString(R.string.Historic))){
                    Intent i = new Intent(Accueil.this, Historique.class);
                    startActivity(i);
                }
                return false;
            }
        });


        Button Add = (Button) findViewById(R.id.buttonAdd);
        ListView list = (ListView) findViewById(R.id.listCodeIOCA);
        EditText CodeIOCA = (EditText) findViewById(R.id.editTextIOCA);
        InputFilter[] filters = new InputFilter[1];
        CodeIOCA.setFilters(new InputFilter[] { new InputFilter.AllCaps(), new InputFilter.LengthFilter(4)});

        ArrayList<String> arrayList;
        if(getIntent().getStringExtra("recherches") == null) {
            arrayList = new ArrayList<String>();
        }else{
            Gson gson = new Gson();
            arrayList = (ArrayList<String>) gson.fromJson(getIntent().getStringExtra("recherches"),ArrayList.class);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        Add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!CodeIOCA.getText().toString().equals(""))
                {
                    arrayList.add(CodeIOCA.getText().toString());
                    adapter.notifyDataSetChanged();
                    CodeIOCA.setText("");
                }
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });




        Button validate = (Button) findViewById(R.id.buttonValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, Affsnowtam.class);
                i.putExtra("List",arrayList);
                i.putExtra("id",0);
                Gson gson = new Gson();
                java.util.Date date = new java.util.Date();

                Recherche r = new Recherche(date.toString(),gson.toJson(arrayList));
                rechercheVM.createRecherche(r);



                startActivity(i);
            }
        });
        this.configureVM();


    }

    private void configureVM(){
        VmFactory vmFactory = Injection.provideVmFactory(this);
        this.rechercheVM = new ViewModelProvider(this,vmFactory).get(RechercheVM.class);

    }
}