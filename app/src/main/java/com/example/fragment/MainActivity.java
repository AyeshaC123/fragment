package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        // Fragment transaction
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new Fragment1())
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // This ensures that the onActivityResult in the Fragment gets called
    }

    @Override
    protected boolean onPopupMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_visually_impaired) {
            startActivity(new Intent(this, VisuallyImpairedActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_mobility_impaired) {
            startActivity(new Intent(this, MobilityImpairedActivity.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            showSettingsMenu(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSettingsMenu(MenuItem item) {
        View menuItemView = findViewById(R.id.toolbar); // Use the toolbar as anchor
        PopupMenu popupMenu = new PopupMenu(this, menuItemView);
        popupMenu.inflate(R.menu.popup); // Ensure this menu contains the items you want
        popupMenu.setOnMenuItemClickListener(this::onPopupMenuItemClick);
        popupMenu.show();
    }
}
