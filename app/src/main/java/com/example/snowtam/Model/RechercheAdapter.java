package com.example.snowtam.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snowtam.Accueil;
import com.example.snowtam.R;

import java.util.ArrayList;
import java.util.List;

public class RechercheAdapter  extends ArrayAdapter<Recherche> {

    public RechercheAdapter(Context context,ArrayList<Recherche> recherches) {
        super(context,0, recherches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recherche recherche = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recherche,parent,false);
        }
        TextView date = (TextView) convertView.findViewById(R.id.TextDate);
        TextView codes = (TextView) convertView.findViewById(R.id.TextRecherche);

        date.setText(recherche.getDate());
        codes.setText(recherche.getCodes());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Accueil.class);
                i.putExtra("recherches",recherche.getCodes());
                getContext().startActivity(i);
            }
        });
        return convertView;
    }


}
