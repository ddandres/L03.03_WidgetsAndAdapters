/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.l0303_widgetsandadapters.R;
import com.example.android.l0303_widgetsandadapters.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayAdaptersActivity(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bAutocomplete:
                intent = new Intent(MainActivity.this, AutocompleteTextViewActivity.class);
                break;
            case R.id.bSpinner:
                intent = new Intent(MainActivity.this, SpinnerActivity.class);
                break;
            case R.id.bList:
                intent = new Intent(MainActivity.this, AdapterViewActivity.class);
                intent.putExtra(Utils.TYPE_OF_ADAPTERVIEW, Utils.LISTVIEW);
                break;
            case R.id.bGrid:
                intent = new Intent(MainActivity.this, AdapterViewActivity.class);
                intent.putExtra(Utils.TYPE_OF_ADAPTERVIEW, Utils.GRIDVIEW);
                break;
            case R.id.bRecycler:
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                break;
            case R.id.bExpandable:
                intent = new Intent(MainActivity.this, ExpandableListViewActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
