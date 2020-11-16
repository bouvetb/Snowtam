package com.example.snowtam.Model;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class SnowTam {
    private SnowTam() {
    }

    public static void getSnowtam(Context context, String airports, Response.Listener<DataSearchSnow> respone, Response.ErrorListener errorListener){
        final String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=ba305370-27e7-11eb-8a5c-67774224e6a0&format=json&locations="+airports;
        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<DataSearchSnow> gsonRequest = new GsonRequest<>(url, DataSearchSnow.class, null,respone,errorListener);
        queue.add(gsonRequest);
    }
}