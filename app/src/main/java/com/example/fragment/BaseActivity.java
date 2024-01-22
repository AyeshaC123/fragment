package com.example.fragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.PopupMenu; // Import PopupMenu
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Common setup for activities that extend this base activity
    }

    protected void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // In BaseActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            View menuItemView = findViewById(R.id.toolbar); // Use the toolbar as anchor
            PopupMenu popupMenu = new PopupMenu(this, menuItemView);
            inflatePopupMenu(popupMenu); // Inflate menu (default or specific)
            popupMenu.setOnMenuItemClickListener(this::onPopupMenuItemClick);
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showSettingsMenu(MenuItem item) {
        View menuItemView = findViewById(R.id.toolbar); // Use the toolbar as anchor
        PopupMenu popupMenu = new PopupMenu(this, menuItemView);
        popupMenu.inflate(R.menu.popup); // Ensure this menu contains the items you want
        popupMenu.setOnMenuItemClickListener(item1 -> {
            return onPopupMenuItemClick(item1);
        });
        popupMenu.show();
    }

    // In BaseActivity
    protected void inflatePopupMenu(PopupMenu popupMenu) {
        popupMenu.inflate(R.menu.popup); // default_popup_menu contains common items
    }


    protected abstract boolean onPopupMenuItemClick(MenuItem item);
}
