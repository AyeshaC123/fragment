package com.example.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddBeacon extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listViewBeacons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbeacon);

        dbHelper = new DBHelper(this);
        listViewBeacons = findViewById(R.id.listViewBeacons);

        Button retrieveBeaconsButton = findViewById(R.id.buttonRetrieveBeacons);
        retrieveBeaconsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveBeacons();
            }
        });
        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity and return to the previous one
                finish();
            }
        });
    }



    private void retrieveBeacons() {
        // Retrieve beacons from the database
        ArrayList<Beacon> beacons = dbHelper.getAllData(DBHelper.TABLE_FLOOR1);
        beacons.addAll(dbHelper.getAllData(DBHelper.TABLE_FLOOR2));

        // Create an ArrayAdapter to display the beacons in the ListView
        ArrayAdapter<Beacon> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                beacons);
        listViewBeacons.setAdapter(adapter);
    }
}
