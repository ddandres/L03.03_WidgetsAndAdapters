/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.l0303_widgetsandadapters.R;
import com.example.android.l0303_widgetsandadapters.Utils;
import com.example.android.l0303_widgetsandadapters.adapters.ProvincesAdapter;

public class AdapterViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the type of AdapterView to be used: LinearLayout or GridLayout
        int typeOfView = getIntent().getIntExtra(Utils.TYPE_OF_ADAPTERVIEW, 0);

        // Get the selected Activity layout and title,
        // the layout to display each item on the AdapterView,
        // and the reference to the AdaterView (ListView/GridView)
        int mainLayoutRes = R.layout.activity_list_view;
        int titleRes = R.string.listview;
        int resourceRes = R.layout.layout_province_list;
        int adapterViewRes = R.id.lvProvinces;
        switch (typeOfView) {
            case Utils.LISTVIEW:
                mainLayoutRes = R.layout.activity_list_view;
                titleRes = R.string.listview;
                resourceRes = R.layout.layout_province_list;
                adapterViewRes = R.id.lvProvinces;
                break;
            case Utils.GRIDVIEW:
                mainLayoutRes = R.layout.activity_grid_view;
                titleRes = R.string.gridview;
                resourceRes = R.layout.layout_province_grid;
                adapterViewRes = R.id.gvProvinces;
                break;
        }
        // Set the content view for the Activity
        setContentView(mainLayoutRes);

        // Set the title for the Activity
        setTitle(titleRes);

        // Create the adapter that generates the Views from the data array to be displayed in the ListView
        final ProvincesAdapter adapter = new ProvincesAdapter(
                AdapterViewActivity.this,
                resourceRes,
                Utils.getProvincesArray(AdapterViewActivity.this));

        // Reference to the ListView
        AdapterView adapterView = findViewById(adapterViewRes);
        // Set the adapter to the ListView
        adapterView.setAdapter(adapter);

        // This listener will be executed when any element within the ListView is clicked
        adapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Display a message with the name of the province clicked
                Toast.makeText(
                        AdapterViewActivity.this,
                        adapter.getItem(position).getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        // This listener will be executed when any element within the ListView is long clicked
        adapterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the element that has been long clicked from the array
                adapter.remove(adapter.getItem(position));
                return true;
            }
        });
    }

}
