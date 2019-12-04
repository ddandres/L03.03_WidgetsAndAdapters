/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.android.l0303_widgetsandadapters.pojo.Community;
import com.example.android.l0303_widgetsandadapters.pojo.Province;

import java.util.ArrayList;

public class Utils {

    // Ordered array that indexes all the provinces within a community
    // as returned by the arrays defined as resources
    private static final int[][] comunitiesAndProvinces = {
            {3, 11, 15, 19, 21, 24, 31, 39},
            {22, 43, 49},
            {5},
            {23},
            {26, 42},
            {12},
            {1, 14, 16, 20, 44},
            {6, 9, 27, 35, 37, 38, 40, 46, 48},
            {8, 17, 28, 41},
            {2, 13, 45},
            {7, 10},
            {0, 29, 34, 36},
            {25},
            {30},
            {32},
            {33},
            {4, 18, 47}
    };


    // Constant used as key for extra parameters of Intents
    public static final String TYPE_OF_ADAPTERVIEW = "TypeOfAdapterView";

    // Constants identifying the type of View to display the information of Spanish provinces
    public static final int LISTVIEW = 0;
    public static final int GRIDVIEW = 1;

    /*
        Returns the array of Spanish Provinces to be displayed in the List/GridView
     */
    public static ArrayList<Province> getProvincesArray(Context context) {
        ArrayList<Province> array = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.provinces);
        String[] plates = context.getResources().getStringArray(R.array.plates);
        TypedArray flags = context.getResources().obtainTypedArray(R.array.flags);
        Province province;
        for (int i = 0; i < names.length; i++) {
            province = new Province(names[i], flags.getResourceId(i, R.drawable.valencia), plates[i]);
            array.add(province);
        }
        flags.recycle();
        return array;
    }

    /*
        Returns an array of Spanish Communities
     */
    public static ArrayList<Community> getCommunities(Context context) {
        ArrayList<Community> array = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.communities);
        TypedArray flags = context.getResources().obtainTypedArray(R.array.communities_flags);
        Community community;
        for (int i = 0; i < names.length; i++) {
            community = new Community(names[i], flags.getResourceId(i, R.drawable.com_valenciana));
            array.add(community);
        }
        flags.recycle();
        return array;
    }

    /*
        Returns an array of arrays of Provinces with information of Spanish Provinces.
        First array has an element for each Spanish community.
        Second array contains a Province for each province of that community.
     */
    public static ArrayList<ArrayList<Province>> getProvincesPerCommunity(Context context) {
        ArrayList<ArrayList<Province>> communities = new ArrayList<>();
        ArrayList<Province> provinces;
        String[] names = context.getResources().getStringArray(R.array.provinces);
        String[] plates = context.getResources().getStringArray(R.array.plates);
        TypedArray flags = context.getResources().obtainTypedArray(R.array.flags);
        Province province;
        int index;
        for (int i = 0; i < comunitiesAndProvinces.length; i++) {
            provinces = new ArrayList<>();
            for (int j = 0; j < comunitiesAndProvinces[i].length; j++) {
                index = comunitiesAndProvinces[i][j];
                province = new Province(
                        names[index], flags.getResourceId(index, R.drawable.valencia), plates[index]);
                provinces.add(province);
            }
            communities.add(provinces);
        }

        flags.recycle();
        return communities;
    }

}
