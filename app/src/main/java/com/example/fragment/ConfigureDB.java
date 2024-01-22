package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ConfigureDB extends AppCompatActivity {

    ListView listViewDatabase;
    DBHelper dbHelper;
    Button buttonAddBeacon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure);

        listViewDatabase = findViewById(R.id.listViewDatabase);
        dbHelper = new DBHelper(this);

        buttonAddBeacon = findViewById(R.id.buttonAddBeacon);
        buttonAddBeacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigureDB.this, Setting.class);
                startActivity(intent);
            }
        });

        updateListView();

        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity and return to the previous one
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView(); // This will refresh the list
    }


    private void updateListView() {
        // Retrieve beacon data from Floor1 and Floor2
        ArrayList<Beacon> dataListFloor1 = dbHelper.getAllData(DBHelper.TABLE_FLOOR1);
        ArrayList<Beacon> dataListFloor2 = dbHelper.getAllData(DBHelper.TABLE_FLOOR2);

        // Combine the two lists
        ArrayList<Beacon> combinedDataList = new ArrayList<>();
        combinedDataList.addAll(dataListFloor1);
        combinedDataList.addAll(dataListFloor2);

        // Use the combined list for the CustomListAdapter
        CustomListAdapter adapter = new CustomListAdapter(this, combinedDataList);
        listViewDatabase.setAdapter(adapter);
    }
}
