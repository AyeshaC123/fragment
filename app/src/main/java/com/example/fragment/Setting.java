package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

public class Setting extends AppCompatActivity {

    EditText editTextBeaconID, editTextBeaconName, editTextLocation, editTextPaths;
    Button buttonSubmit, buttonViewDatabase;

    RadioGroup radioGroupFloor;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // Initialize EditTexts

        editTextBeaconID = findViewById(R.id.editTextBeaconID);
        editTextBeaconName = findViewById(R.id.editTextBeaconName);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextPaths = findViewById(R.id.editTextPaths);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonViewDatabase = findViewById(R.id.buttonViewDatabase);
        radioGroupFloor = findViewById(R.id.radioGroupFloor);

        dbHelper = new DBHelper(this);

        buttonSubmit.setOnClickListener(v -> {

            String beaconId = editTextBeaconID.getText().toString();
            String beaconName = editTextBeaconName.getText().toString();
            String location = editTextLocation.getText().toString();
            String paths = editTextPaths.getText().toString();


            // Determine which floor is selected
            int selectedFloorId = radioGroupFloor.getCheckedRadioButtonId();
            boolean isInserted;
            if (selectedFloorId == R.id.radioButtonFloor1) {
                // Insert data into Floor 1 table
                isInserted = dbHelper.insertDataFloor1(beaconId, beaconName, location, paths);
            } else {
                // Insert data into Floor 2 table
                isInserted = dbHelper.insertDataFloor2(beaconId, beaconName, location, paths);
            }


            // Clear the EditTexts after insertion
            editTextBeaconID.setText("");
            editTextBeaconName.setText("");
            editTextLocation.setText("");
            editTextPaths.setText("");

        });

        buttonViewDatabase.setOnClickListener(v -> {
            // Create an intent to start
            Intent intent = new Intent(Setting.this, ConfigureDB.class);
            startActivity(intent);
        });

    }



}
