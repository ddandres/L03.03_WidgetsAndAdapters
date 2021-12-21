/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0303_widgetsandadapters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import labs.dadm.l0303_widgetsandadapters.R;
import labs.dadm.l0303_widgetsandadapters.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View.OnClickListener listener = v -> displayAdaptersActivity(v.getId());
        findViewById(R.id.bAutocomplete).setOnClickListener(listener);
        findViewById(R.id.bSpinner).setOnClickListener(listener);
        findViewById(R.id.bList).setOnClickListener(listener);
        findViewById(R.id.bGrid).setOnClickListener(listener);
        findViewById(R.id.bRecycler).setOnClickListener(listener);
        findViewById(R.id.bExpandable).setOnClickListener(listener);
    }

    public void displayAdaptersActivity(int buttonCLicked) {
        Intent intent = null;

        if (buttonCLicked == R.id.bAutocomplete) {
            intent = new Intent(MainActivity.this, AutocompleteTextViewActivity.class);
        } else if (buttonCLicked == R.id.bSpinner) {
            intent = new Intent(MainActivity.this, SpinnerActivity.class);
        } else if (buttonCLicked == R.id.bList) {
            intent = new Intent(MainActivity.this, AdapterViewActivity.class);
            intent.putExtra(Utils.TYPE_OF_ADAPTERVIEW, Utils.LISTVIEW);
        } else if (buttonCLicked == R.id.bGrid) {
            intent = new Intent(MainActivity.this, AdapterViewActivity.class);
            intent.putExtra(Utils.TYPE_OF_ADAPTERVIEW, Utils.GRIDVIEW);
        } else if (buttonCLicked == R.id.bRecycler) {
            intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        } else if (buttonCLicked == R.id.bExpandable) {
            intent = new Intent(MainActivity.this, ExpandableListViewActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
