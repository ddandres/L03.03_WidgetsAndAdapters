/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.l0303_widgetsandadapters.R;

public class AutocompleteTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete_text_view);

        // Set the title for the Activity
        setTitle(R.string.autocomplete);

        // Adapter that provides a View (android.R.layout.simple_dropdown_item_1line)
        // for each data object within an array (R.array.provinces)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AutocompleteTextViewActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.provinces));

        final AutoCompleteTextView actvProvince = findViewById(R.id.actvProvince);
        // Associates the adapter with the AutocompleteTextView
        actvProvince.setAdapter(adapter);
    }
}
