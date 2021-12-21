/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0303_widgetsandadapters.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import labs.dadm.l0303_widgetsandadapters.R;
import labs.dadm.l0303_widgetsandadapters.Utils;
import labs.dadm.l0303_widgetsandadapters.adapters.ProvincesRecyclerAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // Set the title for the Activity
        setTitle(R.string.recyclerview);

        // **********************************************
        // RecyclerView with Vertical LinearLayoutManager
        // **********************************************

        // Reference to the RecyclerView
        RecyclerView recycler = findViewById(R.id.rvProvincesVertical);
        // Its size is fixed, so it can be optimised
        recycler.setHasFixedSize(true);

        // Create the adapter that generates the Views from the data array
        // to be displayed in the RecyclerView
        ProvincesRecyclerAdapter adapter = new ProvincesRecyclerAdapter(
                R.layout.layout_province_list, Utils.getProvincesArray(
                RecyclerViewActivity.this));
        // Set the adapter to the RecyclerView
        recycler.setAdapter(adapter);

        // Create the LayoutManager in charge of positioning elements within the RecyclerView:
        // linear and vertical in this case
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                RecyclerViewActivity.this, RecyclerView.VERTICAL, false);
        // Set the manager to the RecyclerView
        recycler.setLayoutManager(manager);

        // ************************************************
        // RecyclerView with Horizontal LinearLayoutManager
        // ************************************************

        // Reference to the RecyclerView
        recycler = findViewById(R.id.rvProvincesHorizontal);
        // Its size is fixed, so it can be optimised
        recycler.setHasFixedSize(true);

        // Set the adapter to the RecyclerView as it is the same as the previous one
        recycler.setAdapter(adapter);

        // Create the LayoutManager in charge of positioning elements within the RecyclerView:
        // linear and horizontal in this case
        manager = new LinearLayoutManager(
                RecyclerViewActivity.this, RecyclerView.HORIZONTAL, false);
        // Set the manager to the RecyclerView
        recycler.setLayoutManager(manager);

        // Create a SnapHelper to ensure the when the RecyclerView stops scrolling
        // the current item is fully displayed on the screen
        final SnapHelper snap = new LinearSnapHelper();
        // Attach the SnapHelper to the RecyclerView
        snap.attachToRecyclerView(recycler);

        // ********************************************
        // RecyclerView with Vertical GridLayoutManager
        // ********************************************

        // Reference to the RecyclerView
        recycler = findViewById(R.id.rvProvincesGrid);
        // Its size is fixed, so it can be optimised
        recycler.setHasFixedSize(true);

        // Create the adapter that generates the Views from the data array
        // to be displayed in the RecyclerView
        adapter = new ProvincesRecyclerAdapter(
                R.layout.layout_province_grid, Utils.getProvincesArray(
                RecyclerViewActivity.this));
        // Set the adapter to the RecyclerView
        recycler.setAdapter(adapter);

        // Create the LayoutManager in charge of positioning elements within the RecyclerView:
        // grid and vertical in this case
        manager = new GridLayoutManager(
                RecyclerViewActivity.this,
                3,
                RecyclerView.VERTICAL,
                false);
        // Set the manager to the RecyclerView
        recycler.setLayoutManager(manager);

    }
}
