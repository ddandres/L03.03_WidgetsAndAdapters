/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.l0303_widgetsandadapters.R;
import com.example.android.l0303_widgetsandadapters.pojo.Province;

import java.util.ArrayList;

public class ProvincesRecyclerAdapter extends RecyclerView.Adapter<ProvincesRecyclerAdapter.ViewHolder> {

    // Holds a reference to the layout that should be used to create the required Views
    private int resource;

    // Holds a reference to the data array
    private ArrayList<Province> data;

    public ProvincesRecyclerAdapter(int resource, ArrayList<Province> array) {
        this.resource = resource;
        this.data = array;
    }

    /*
        Creates the View from the provided layout resource and attaches it to a ViewHolder
        to easily access all the elements within the View.
     */
    @NonNull
    @Override
    public ProvincesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ViewHolder(view);
    }

    /*
        Fills the elements within the View with the required data from the array.
     */
    @Override
    public void onBindViewHolder(@NonNull ProvincesRecyclerAdapter.ViewHolder holder, int position) {
        final Province province = this.data.get(position);
        holder.tvName.setText(province.getName());
        holder.tvPlate.setText(province.getPlate());
        holder.ivFlag.setImageResource(province.getFlag());
    }

    /*
        Returns the number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return this.data.size();
    }

    /*
        Private class that holds a reference to all the elements within the Views
        and sets a listener to react to any click on the View
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPlate;
        ImageView ivFlag;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProvinceName);
            tvPlate = itemView.findViewById(R.id.tvProvincePlate);
            ivFlag = itemView.findViewById(R.id.ivProvinceFlag);

            // This listener will be executed when the View is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Display a message with the name of the province clicked
                    Toast.makeText(itemView.getContext(), tvName.getText(), Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
