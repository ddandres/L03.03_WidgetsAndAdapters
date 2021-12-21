/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0303_widgetsandadapters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import labs.dadm.l0303_widgetsandadapters.R;
import labs.dadm.l0303_widgetsandadapters.pojo.Province;

// Custom adapter to generate the Views required to display the information
// of Spanish provinces in a ListView/GridView.
public class ProvincesAdapter extends ArrayAdapter<Province> {

    // Holds a reference to the layout that should be used to create the required Views
    final private int layout;

    public ProvincesAdapter(
            @NonNull Context context, int resource, @NonNull ArrayList<Province> objects) {
        super(context, resource, objects);
        this.layout = resource;
    }

    // Gets the View to display the data corresponding the Spanish province indexed by position.
    // Views are recycled, so only those fitting into the screen are actually created.
    // A Holder pattern is used to speed up retrieving the reference to elements within each View.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Holder
        ProvinceHolder holder;
        // View to be reused, if possible
        View view = convertView;
        // Create the View if it has not been created yet
        if (view == null) {
            // Get a LayoutInflater
            final LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create a View from the selected layout
            view = inflater.inflate(layout, parent, false);
            // Get a reference for each element within the View
            // The Holder will keep them
            holder = new ProvinceHolder();
            holder.flag = view.findViewById(R.id.ivProvinceFlag);
            holder.name = view.findViewById(R.id.tvProvinceName);
            holder.plate = view.findViewById(R.id.tvProvincePlate);
            // Associate the Holder with the View
            view.setTag(holder);
        }

        // Retrieve the Holder associated with the View (necessary for reused Views)
        holder = (ProvinceHolder) view.getTag();
        // Get the data for the province at the given position in the array
        final Province data = getItem(position);
        // Fill each element within the View with the required information
        holder.flag.setImageResource(data.getFlag());
        holder.name.setText(data.getName());
        holder.plate.setText(data.getPlate());
        // Return the updated View
        return view;
    }

    // Private class that holds a reference to all the elements within the Views
    private static class ProvinceHolder {
        ImageView flag;
        TextView name;
        TextView plate;
    }
}
