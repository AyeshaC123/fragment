package com.example.fragment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BoundaryView extends AppCompatActivity {
    private DBHelper dbHelper;
    private String beaconId;
    private String tableName;

    private TextView textViewBeaconID, textViewBeaconName, textViewLocation, textViewPaths, textViewTableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);

        textViewBeaconID = findViewById(R.id.textViewBeaconID);
        textViewBeaconName = findViewById(R.id.textViewBeaconName);
        textViewLocation = findViewById(R.id.textViewTextLocation);
        textViewPaths = findViewById(R.id.textViewTextPaths);
        textViewTableName = findViewById(R.id.textViewTableName);

        dbHelper = new DBHelper(this);
        // Retrieve the data from the intent
        beaconId = getIntent().getStringExtra("beaconId");
        String beaconName = getIntent().getStringExtra("beaconName");
        String location = getIntent().getStringExtra("location");
        String paths = getIntent().getStringExtra("paths");
        tableName = getIntent().getStringExtra("tableName");

        // Set the text in the TextViews
        textViewBeaconID.setText("Beacon ID: " + beaconId);
        textViewBeaconName.setText("Beacon Name: " + beaconName);
        textViewLocation.setText("Location: " + location);
        textViewPaths.setText("Paths: " + paths);
        textViewTableName.setText("Floor: " + tableName);
    }






}
