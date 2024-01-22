package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Beacon> {
    // Constructor
    public CustomListAdapter(Context context, ArrayList<Beacon> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Beacon beacon = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        // Lookup view for data population
        TextView textViewItem = convertView.findViewById(R.id.textViewItem);
        Button buttonView = convertView.findViewById(R.id.buttonView);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        // Populate the data into the template view using the data object
        textViewItem.setText("ID: " + beacon.getId());

        // Attach click listener to View button
        buttonView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BoundaryView.class);
            intent.putExtra("beaconId", beacon.getId());
            intent.putExtra("beaconName", beacon.getName());
            intent.putExtra("location", beacon.getLocation());
            intent.putExtra("paths", beacon.getPaths());
            intent.putExtra("tableName", beacon.getFloor());
            getContext().startActivity(intent);
        });
//EDIT
        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EditBeacon.class);
            intent.putExtra("beaconId", beacon.getId());
            intent.putExtra("beaconName", beacon.getName());
            intent.putExtra("location", beacon.getLocation());
            intent.putExtra("paths", beacon.getPaths());
            intent.putExtra("tableName", beacon.getFloor());
            getContext().startActivity(intent);
        });


        buttonDelete.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(getContext());
            boolean isDeleted = dbHelper.deleteBeacon(beacon.getId(), beacon.getFloor()); // Implement deleteBeacon in DBHelper
            if (isDeleted) {
                remove(beacon); // Remove the beacon from the adapter's dataset
                notifyDataSetChanged(); // Notify the adapter to refresh the list view
                Toast.makeText(getContext(), "Beacon deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to delete the beacon", Toast.LENGTH_SHORT).show();
            }
        });



        // Return the completed view to render on screen
        return convertView;
    }
}
