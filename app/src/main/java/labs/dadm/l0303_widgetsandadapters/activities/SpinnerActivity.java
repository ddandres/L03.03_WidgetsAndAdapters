/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0303_widgetsandadapters.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import labs.dadm.l0303_widgetsandadapters.R;

public class SpinnerActivity extends AppCompatActivity {

    // Adapter to link data to View
    ArrayAdapter<String> adapter;
    // EditText to get a String to be added to the Spinner
    EditText etprovince;
    // Dynamic Spinner
    Spinner spDynamic;
    // Button to delete the selected item in the dynamic Spinner
    Button bDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // Set the title for the Activity
        setTitle(R.string.spinner);

        etprovince = findViewById(R.id.etProvince);
        bDelete = findViewById(R.id.bDelete);

        // Static Spinner
        final Spinner spStatic = findViewById(R.id.spStatic);
        // Listener to be activated when an item is selected in the Spinner
        spStatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Displays the currently selected item
                Toast.makeText(
                        SpinnerActivity.this,
                        parent.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Reference to the dynamic Spinner
        spDynamic = findViewById(R.id.spDynamic);
        // Adapter that provides a View (android.R.layout.simple_spinner_dropdown_item)
        // for each data object within an array (ArrayList)
        adapter = new ArrayAdapter<>(
                SpinnerActivity.this,
                android.R.layout.simple_spinner_item,
                new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.provinces))));
        // Set the layout to create the dropdown View
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Associate the adapter to the dynamic SPinner
        spDynamic.setAdapter(adapter);

        // Listener to be activated when an item is selected in the Spinner
        spDynamic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Enable the delete Button because there is an item selected
                bDelete.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Disable the delete Button because there is not any item selected
                bDelete.setEnabled(false);
            }
        });

        // This method adds a String to the Spinner
        findViewById(R.id.bAdd).setOnClickListener(v -> {
            // Get the text from an EditText
            final String province = etprovince.getText().toString();
            if (!province.isEmpty()) {
                // If the text is not empty, then add it to the adapter
                adapter.add(province);
                // Clear the EditText
                etprovince.setText("");
            }
        });

        // This method removes the selected item from the Spinner
        findViewById(R.id.bDelete).setOnClickListener(v -> {
            // Get the text corresponding to the selected item
            final String province = (String) spDynamic.getSelectedItem();
            // Remove that item from the adapter
            adapter.remove(province);
        });
    }
}
