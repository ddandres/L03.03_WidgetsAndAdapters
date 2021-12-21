/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0303_widgetsandadapters.activities;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import labs.dadm.l0303_widgetsandadapters.R;
import labs.dadm.l0303_widgetsandadapters.Utils;
import labs.dadm.l0303_widgetsandadapters.adapters.ProvincesAdapter;

public class AdapterViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the type of AdapterView to be used: LinearLayout or GridLayout
        int typeOfView = getIntent().getIntExtra(Utils.TYPE_OF_ADAPTERVIEW, Utils.LISTVIEW);

        // Get the selected Activity layout and title,
        // the layout to display each item on the AdapterView,
        // and the reference to the AdapterView (ListView/GridView)
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

        // Create the adapter that generates the Views from the data array
        // to be displayed in the ListView/GridView
        final ProvincesAdapter adapter = new ProvincesAdapter(
                AdapterViewActivity.this,
                resourceRes,
                Utils.getProvincesArray(AdapterViewActivity.this));

        // Reference to the ListView/GridView
        final AdapterView<Adapter> adapterView = findViewById(adapterViewRes);
        // Set the adapter to the ListView/GridView
        adapterView.setAdapter(adapter);

        // This listener will be executed when any element within the ListView/GridView is clicked
        adapterView.setOnItemClickListener((parent, view, position, id) -> {
            // Display a message with the name of the province clicked
            Toast.makeText(
                    AdapterViewActivity.this,
                    adapter.getItem(position).getName(),
                    Toast.LENGTH_SHORT).show();
        });

        // This listener will be executed when any element within the ListView/GridView is long clicked
        adapterView.setOnItemLongClickListener((parent, view, position, id) -> {
            // Remove the element that has been long clicked from the array
            adapter.remove(adapter.getItem(position));
            return true;
        });
    }

}
