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

            //String test = "\"V0620/20 NOTAMN \\nQ) ENOR/QPICH/I/BO/A/000/999/6011N01106E005 A) ENGM B) 2012030001 C) 2012302359\\nE) [US DOD PROCEDURAL NOTAM] STANDARD INSTRUMENT ARRIVAL (STAR)\\n AMENDMENT RNAV STAR RWY 19L/R WEST. CHANGE GRAPHIC HEADING FROM\\n AGNIL TO GM432 FROM 354� TO READ 154�. CHANGE OUTBOUND HOLDING\\n HEADING AT RIPAM FROM 321� TO READ 231�. DAFIF DATA UPDATED TO\\n CORRECT HEADINGS.\\nCREATED: 02 Dec 2020 21:01:00 \\nSOURCE: KQZC\"";
            //String test = "SWEN0565 ENGM 12040725 \n(SNOWTAM 0565\nA) ENGM\nB) 12040725 C) 01L\nF) 2/5/5 G) XX/6/6 H) 5/4/4\nB) 12040724 C) 01R\nF) 5/5/5 G) 6/6/6 H) 4/4/4\nN) C C1 C2 C3 C4 U U1 U2 U3/67  ALL REMAINING TWYS/5\nR) APRON B SOUTH/CLSD  APRON GA 311/319\nAPRON GA 320/329 APRON GA 330/338 APRON GA NORTH\nAPRON MIL 1/3 APRON MIL 4/10 APRON NEW HANGAR\nAPRON NORWEGIAN APRON SAS/67  APRON B NORTH\nAPRON WF/6  ALL REMAINING APRONS/5\nT) RWY 01L\nCONTAMINATION/100/100/100/PERCENT.\nSLIPPERY PORTIONS ON CENTRAL APRON. FRICTION 3 ON\nTAXIWAYS.\nRWY 01R\nOBSERVATION TIME RWY 01R 202012040724\nCONTAMINATION/100/100/100/PERCENT.\nSLIPPERY PORTIONS ON CENTRAL APRON. SLIPPERY\nPORTIONS ON GA APRONS. FRICTION 3 ON TAXIWAYS.)\nCREATED: 04 Dec 2020 07:27:00 \nSOURCE: EUECYIYN";



        };
        final Response.ErrorListener errorListener = error -> {
            Log.e("Erreur","erreur");
        };
        SnowTam.getSnowtam(v.getContext(),airport,rep,errorListener);
        return v;
    }
}