/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package com.example.android.l0303_widgetsandadapters.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.l0303_widgetsandadapters.R;
import com.example.android.l0303_widgetsandadapters.pojo.Community;
import com.example.android.l0303_widgetsandadapters.pojo.Province;

import java.util.ArrayList;

/*
    Custom adapter to generate the Views required to display the information
    of Spanish communities and their provinces in an ExpandableListView.
 */

public class ProvincesExpandableAdapter extends BaseExpandableListAdapter {

    // Array with information for each community
    private ArrayList<Community> communities;
    // Array of Arrays with information for each province.
    // This position of elements in the first array matches that of the communities in other array.
    // The second array contains information for each province in that community.
    private ArrayList<ArrayList<Province>> provinces;

    public ProvincesExpandableAdapter(
            ArrayList<Community> communities, ArrayList<ArrayList<Province>> provinces) {
        this.communities = communities;
        this.provinces = provinces;
    }

    /*
        Returns the number of communities.
     */
    @Override
    public int getGroupCount() {
        return this.communities.size();
    }

    /*
        Returns the number of provinces for a given community.
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.provinces.get(groupPosition).size();
    }

    /*
        Returns the coomunity at the given position.
     */
    @Override
    public Object getGroup(int groupPosition) {
        return communities.get(groupPosition);
    }

    /*
        Returns the province at a given position within a community also stated by its position.
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return provinces.get(groupPosition).get(childPosition);
    }

    /*
        Returns the community Id, which must be unique.
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /*
        Returns the province Id, which must be unique within its community.
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /*
        Returns whether the same Id always refers to the same object.
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /*
        Gets the View to display the data corresponding the Spanish community indexed by position.
        Views are recycled, so only those fitting into the screen are actually created.
        A Holder pattern is used to speed up retrieving the reference to elements within each View.
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // Holder
        CommunityHolder holder;
        // View to be reused, if possible
        View view = convertView;
        // Create the View if it has not been created yet
        if (view == null) {
            // Get a LayoutInflater
            final LayoutInflater inflater =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create a View from the layout for communities
            view = inflater.inflate(R.layout.layout_group_expandable, parent, false);

            // Get a reference for each element within the View
            // The Holder will keep them
            holder = new CommunityHolder();
            holder.tvName = view.findViewById(R.id.tvCommunityName);
            holder.ivFlag = view.findViewById(R.id.ivCommunityFlag);
            // Associate the Holder with the View
            view.setTag(holder);
        }

        // Retrieve the Holder associated with the View (necessary for reused Views)
        holder = (CommunityHolder) view.getTag();
        // Get the data for the community at the given position in the array
        final Community community = communities.get(groupPosition);
        // Fill each element within the View with the required information
        holder.tvName.setText(community.getName());
        holder.ivFlag.setImageResource(community.getFlag());
        // Return the updated View
        return view;
    }

    /*
        Gets the View to display the data corresponding the Spanish province indexed by position
        within a given community also indexed by position.
        Views are recycled, so only those fitting into the screen are actually created.
        A Holder pattern is used to speed up retrieving the reference to elements within each View.
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Holder
        ProvinceHolder holder;
        // View to be reused, if possible
        View view = convertView;
        // Create the View if it has not been created yet
        if (view == null) {
            // Get a LayoutInflater
            final LayoutInflater inflater =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create a View from the layout for provinces
            view = inflater.inflate(R.layout.layout_province_list, parent, false);

            // As the province should appear as a list within a list,
            // this layout must include a padding on the left
            // Get the actual value of this left padding according to Android attributes
            TypedArray data = parent.getContext().obtainStyledAttributes(
                    new int[]{android.R.attr.expandableListPreferredChildPaddingLeft});
            // Increase the left padding in that value
            view.setPadding(
                    data.getDimensionPixelOffset(0, -1) + view.getPaddingLeft(),
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom());
            data.recycle();

            // Get a reference for each element within the View
            // The Holder will keep them
            holder = new ProvinceHolder();
            holder.tvName = view.findViewById(R.id.tvProvinceName);
            holder.ivFlag = view.findViewById(R.id.ivProvinceFlag);
            holder.tvPlate = view.findViewById(R.id.tvProvincePlate);
            // Associate the Holder with the View
            view.setTag(holder);
        }

        // Retrieve the Holder associated with the View (necessary for reused Views)
        holder = (ProvinceHolder) view.getTag();
        // Get the data for the province at the given position in the array within a community
        final Province province = provinces.get(groupPosition).get(childPosition);
        // Fill each element within the View with the required information
        holder.tvName.setText(province.getName());
        holder.ivFlag.setImageResource(province.getFlag());
        holder.tvPlate.setText(province.getPlate());
        // Return the updated View
        return view;
    }

    /*
        Returns whether the provinces are selectable.
        If so, they can have dividers between them
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // Private class that holds a reference to all the elements within the Views for communities
    private static class CommunityHolder {
        TextView tvName;
        ImageView ivFlag;
    }

    // Private class that holds a reference to all the elements within the Views for provinces
    private static class ProvinceHolder {
        TextView tvName;
        ImageView ivFlag;
        TextView tvPlate;
    }
}
