package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

public class MobilityImpairedActivity extends BaseActivity {
    private EditText editTextPageName;
    private Button buttonNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobility_impaired);
        setupToolbar(); // Setup toolbar from BaseActivity

        editTextPageName = findViewById(R.id.editTextPageName);
        buttonNavigate = findViewById(R.id.buttonNavigate);

        Button homeButton = findViewById(R.id.buttonHome);
        homeButton.setOnClickListener(v -> finish());

        Button buttonExploration = findViewById(R.id.buttonExploration);
        buttonExploration.setOnClickListener(v -> {
            Intent intent = new Intent(MobilityImpairedActivity.this, Exploration.class);
            startActivity(intent);
        });

        Button buttonNavigation = findViewById(R.id.buttonNavigation);
        buttonNavigation.setOnClickListener(v -> {
            Intent intent = new Intent(MobilityImpairedActivity.this, Navigation.class);
            startActivity(intent);
        });

        buttonNavigate.setOnClickListener(v -> {
            String pageName = editTextPageName.getText().toString();
            navigateToPage(pageName);
        });
    }

    private void navigateToPage(String pageName) {
        Intent intent = null;

        if(pageName.equalsIgnoreCase("Exploration")) {
            intent = new Intent(this, Exploration.class);
        } else if(pageName.equalsIgnoreCase("Navigation")) {
            intent = new Intent(this, Navigation.class);
        } else if(pageName.equalsIgnoreCase("Admin")) {
            intent = new Intent(this, AdminActivity.class);
        } else if(pageName.equalsIgnoreCase("Info")) {
            intent = new Intent(this, InfoActivityVI.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void inflatePopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.visually_impaired_popup_menu);
    }

    @Override
    protected boolean onPopupMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_admin) {
            startActivity(new Intent(this, AdminActivity.class));
            return true;
        } else if (id == R.id.menu_info) {
            startActivity(new Intent(this, InfoActivityVI.class));
            return true;
        }
        return false;
    }
}
