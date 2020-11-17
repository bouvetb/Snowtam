package com.example.snowtam.Model;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

public class SnowTam {
    private SnowTam() {
    }

    public static void getSnowtam(Context context, String airports, Response.Listener<DataSearchSnow[]> respone, Response.ErrorListener errorListener){
        final String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=d8da49d0-28b8-11eb-bdf4-c10e3ab13326&format=json&locations="+airports;
        Log.e("url",url);
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<DataSearchSnow[]> gsonRequest = new GsonRequest<DataSearchSnow[]>(url, DataSearchSnow[].class, null,respone,errorListener);
        queue.add(gsonRequest);

    }
    public static void getAirport(Context context,String airport, Response.Listener<DataSearchAirport[]> response, Response.ErrorListener errorListener){
        final String url = "https://applications.icao.int/dataservices/api/indicators-list?api_key=d8da49d0-28b8-11eb-bdf4-c10e3ab13326&airports="+airport+"&format=json";
        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<DataSearchAirport[]> gsonRequest = new GsonRequest<DataSearchAirport[]>(url, DataSearchAirport[].class, null,response,errorListener);
        queue.add(gsonRequest);


    }
}