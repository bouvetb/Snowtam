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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Snowtamdecode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Snowtamdecode extends Fragment {
    private static String airport;
    private static String NameAirport;


    public Snowtamdecode() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Snowtamdecode newInstance(String airport,String NameAirport) {
        Snowtamdecode fragment = new Snowtamdecode();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Snowtamdecode.airport = airport;
        Snowtamdecode.NameAirport =  NameAirport;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    public String conditionsTiers(String tiers)
    {
        String retour;
        switch (tiers)
        {
            case "0" :
                retour = getString(R.string.CLEAR_AND_DRY);
                break;
            case "1" :
                retour = getString(R.string.DAMP);
                break;
            case "2" :
                retour = getString(R.string.WET);
                break;
            case "3" :
                retour = getString(R.string.RIME);
                break;
            case "4" :
                retour = getString(R.string.DRY);
                break;
            case "5" :
                retour = getString(R.string.WET_SNOW);
                break;
            case "6" :
                retour = getString(R.string.SLUSH);
                break;
            case "7" :
                retour = getString(R.string.ICE);
                break;
            case "8" :
                retour = getString(R.string.COMPACTED);
                break;
            case "9" :
                retour = getString(R.string.FROZEN);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + tiers);
        }

        return retour;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snowtamdecode, container, false);
        TextView tv = (TextView) v.findViewById(R.id.snowtamdecode);


        final Response.Listener<DataSearchSnow[]> rep = response -> {

            DataSearchSnow snow = null;
            int y =0;
            while(snow == null){
                if(response[y].getEntity().equals("")){
                    snow = response[y];
                }
                y++;
            }
            String texte = snow.getAll();


            String [] tab = {getString(R.string.infoNom),getString(R.string.infoDate),getString(R.string.infoPiste),getString(R.string.infoLongueur),getString(R.string.infoLargeur),getString(R.string.infoCondition)};
            String [] info = {"","","","","",""};

            for(int i=65;i<=70;i++)//de A a F
            {
                int j = i;
                char lettre = (char)i;
                int premier = texte.indexOf(lettre+") ")+2;
                int deuxieme;

                do
                {
                    j++;
                    char lettreArret = (char)(j);
                    deuxieme = texte.indexOf(lettreArret+")");
                }while(deuxieme == -1);

                i = j-1;
                String sub = texte.substring(premier, deuxieme);
                info[(int)lettre - 65] = sub;
                //System.out.println(sub);
            }
                    /*
        for(int i=0;i<=info.length;i++)
        {
            System.out.println(tab[i] + " = " + info[i]);

        }
        */
            //System.out.println(info[1]);
            String sdate = info[1];
            Date date = null;
            try {
                 date = new SimpleDateFormat("MMddHHmm").parse(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //System.out.println(date);
            String piste = info[2];
            piste = piste.replace("L",getString(R.string.PisteLeft));
            piste = piste.replace("R",getString(R.string.PisteRight));
            //System.out.println(piste);
            String largeur = info[4];
            largeur = largeur.replace("L",getString(R.string.PisteLeft));
            largeur = largeur.replace("R",getString(R.string.PisteRight));

            String condition = info[5];
            String tier1 = condition.substring(0,1);
            String tier2 = condition.substring(2,3);
            String tier3 = condition.substring(4,5);
            tier1 = conditionsTiers(tier1);
            tier2 = conditionsTiers(tier2);
            tier3 = conditionsTiers(tier3);


            tv.setText(tab[0]+" : "+ this.NameAirport +"\n"+ //a
                            tab[1]+" : "+ date +"\n"+ //b
                            tab[2]+" : "+ piste +"\n"+ //c
                            tab[3]+" : "+ info[3] +"m\n"+ //d
                            tab[4]+" : "+ largeur +"m\n"+ //e
                            tab[5]+" : "+ getString(R.string.PremierTiers) + tier1 + "/" + getString(R.string.DeuxiemeTiers) + tier2 + "/"+  getString(R.string.TroisiemeTiers) + tier3 //f
                    );
            

        };
        final Response.ErrorListener errorListener = error -> {
            Log.e("Erreur","erreur");
        };
        SnowTam.getSnowtam(v.getContext(),airport,rep,errorListener);
        return v;
    }
}