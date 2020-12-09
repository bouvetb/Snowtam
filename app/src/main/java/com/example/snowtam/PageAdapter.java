package com.example.snowtam;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.volley.Response;
import com.example.snowtam.Model.DataSearchSnow;
import com.example.snowtam.Model.SnowTam;

public class PageAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_2, R.string.tab_text_1};
    private final Context mContext;
    private final String airport;
    private final String NameAirport;
    public PageAdapter(@NonNull FragmentManager fm, Context mContext,String airport,String NameAirport) {
        super(fm);
        this.mContext = mContext;
        this.airport = airport;
        Log.e("airport",airport);
        this.NameAirport =  NameAirport;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0 :

                return Snowtamdecode.newInstance(airport,this.NameAirport);
            case 1 :
                return Snowtamcode.newInstance(airport);
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
