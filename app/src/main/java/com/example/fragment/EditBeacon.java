package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBeacon extends AppCompatActivity {
    private EditText editTextId, editTextName, editTextLocation, editTextPaths, editTextFloor;
    private DBHelper dbHelper;
    private String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_beacon);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Initialize EditTexts
        editTextId = findViewById(R.id.editTextBeaconID);
        editTextName = findViewById(R.id.editTextBeaconName);
        editTextLocation = findViewById(R.id.editTextBeaconLocation);
        editTextPaths = findViewById(R.id.editTextBeaconPaths);
        editTextFloor = findViewById(R.id.editTextBeaconFloor);

        // Receive data from intent and populate fields
        Intent intent = getIntent();
        editTextId.setText(intent.getStringExtra("beaconId"));
        editTextName.setText(intent.getStringExtra("beaconName"));
        editTextLocation.setText(intent.getStringExtra("location"));
        editTextPaths.setText(intent.getStringExtra("paths"));
        editTextFloor.setText(intent.getStringExtra("tableName"));
        tableName = intent.getStringExtra("tableName");

        // Save button logic
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveBeacon());
    }

    private void saveBeacon() {
        // Get values from EditTexts
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String location = editTextLocation.getText().toString();
        String paths = editTextPaths.getText().toString();
        String newFloor = editTextFloor.getText().toString(); // Retrieve the new floor value

        // Update the beacon in the database
        // Pass both the old floor (tableName) and new floor (newFloor)
        boolean isUpdated = dbHelper.updateBeacon(id, name, location, paths, tableName, newFloor);

        if (isUpdated) {
            Toast.makeText(this, "Beacon updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update beacon", Toast.LENGTH_SHORT).show();
        }

        // Close the activity
        finish();
    }

}
