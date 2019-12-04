/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.l0303_widgetsandadapters.R;
import com.example.android.l0303_widgetsandadapters.Utils;
import com.example.android.l0303_widgetsandadapters.adapters.ProvincesExpandableAdapter;
import com.example.android.l0303_widgetsandadapters.pojo.Province;

public class ExpandableListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        // Set the title for the Activity
        setTitle(R.string.expandable);

        // Reference to the ExpandableListView
        final ExpandableListView expandable = findViewById(R.id.elvProvinces);
        // Create the adapter that generates the Views from the data array
        // to be displayed in the ExpandableListView
        final ProvincesExpandableAdapter adapter = new ProvincesExpandableAdapter(
                Utils.getCommunities(ExpandableListViewActivity.this),
                Utils.getProvincesPerCommunity(ExpandableListViewActivity.this)
        );
        // Set the adapter to the ExpandableListView
        expandable.setAdapter(adapter);

        // This listener will be executed when any child within the ExpandableListView is clicked
        expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(
                    ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Get the information (Province) from the element clicked
                final Province province = (Province) adapter.getChild(groupPosition, childPosition);
                // Display a message with the name of the province clicked
                Toast.makeText(
                        ExpandableListViewActivity.this,
                        province.getName(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
