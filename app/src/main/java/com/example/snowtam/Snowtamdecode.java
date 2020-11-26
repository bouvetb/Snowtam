package com.example.snowtam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.snowtam.Model.DataSearchSnow;
import com.example.snowtam.Model.SnowTam;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Snowtamdecode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Snowtamdecode extends Fragment {
    private static String airport;


    public Snowtamdecode() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Snowtamdecode newInstance(String airport) {
        Snowtamdecode fragment = new Snowtamdecode();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Snowtamdecode.airport = airport;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snowtamdecode, container, false);
        TextView tv = (TextView) v.findViewById(R.id.snowtamdecode);
        final Response.Listener<DataSearchSnow[]> rep = response -> {
            tv.setText(response[0].getAll());
        };
        final Response.ErrorListener errorListener = error -> {
            Log.e("Erreur","erreur");
        };
        //SnowTam.getSnowtam(v.getContext(),airport,rep,errorListener);
        return v;
    }
}