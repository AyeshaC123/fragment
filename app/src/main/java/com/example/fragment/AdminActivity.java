package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.admin);


            // Home Button
            Button homeButton = findViewById(R.id.buttonHome);
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to MainActivity
                    Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clears the activity stack
                    startActivity(intent);
                }
            });

            // Back Button

            Button backButton = findViewById(R.id.buttonBack);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish this activity and return to the previous one
                    finish();
                }
            });

                    // Admin Button
                    Button addBeacon = findViewById(R.id.buttonAddBeacon);
                    addBeacon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Start AdminActivity
                            Intent intent = new Intent(com.example.fragment.AdminActivity.this, AddBeacon.class);
                            startActivity(intent);
                        }
                    });

            // Admin Button
            Button addConfigure = findViewById(R.id.buttonConfigureDatabase);
            addConfigure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start AdminActivity
                    Intent intent = new Intent(com.example.fragment.AdminActivity.this, ConfigureDB.class);
                    startActivity(intent);
                }
            });
                }
            }






