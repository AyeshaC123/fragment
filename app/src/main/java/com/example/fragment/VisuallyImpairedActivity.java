package com.example.fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android .view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.speech.RecognizerIntent;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Locale;




public class VisuallyImpairedActivity extends BaseActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 100;
    private EditText editTextPageName;
    private Button buttonNavigate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visually_impaired);
        setupToolbar(); // Setup toolbar from BaseActivity


        Button homeButton = findViewById(R.id.buttonHome);
        homeButton.setOnClickListener(v -> finish());


        editTextPageName = findViewById(R.id.editTextPageName);
        buttonNavigate = findViewById(R.id.buttonNavigate);
        ImageView speakNowImageView = findViewById(R.id.imageView);

        speakNowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });




        Button buttonExploration = findViewById(R.id.buttonExploration);
        buttonExploration.setOnClickListener(v -> {
            Intent intent = new Intent(VisuallyImpairedActivity.this, Exploration.class);
            startActivity(intent);
        });



        Button buttonNavigation = findViewById(R.id.buttonNavigation);
        buttonNavigation.setOnClickListener(v -> {
            Intent intent = new Intent(VisuallyImpairedActivity.this, Navigation.class);
            startActivity(intent);
        });


    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");


        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            // Handle error
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String recognizedText = results.get(0).toLowerCase(); // get the most probable result and convert it to lower case

            // Check for the keywords in the recognized text
            if (recognizedText.contains("admin")) {
                Intent intent = new Intent(VisuallyImpairedActivity.this, AdminActivity.class);
                startActivity(intent);
            } else if (recognizedText.contains("info")) {
                Intent intent = new Intent(VisuallyImpairedActivity.this, InfoActivityVI.class);
                startActivity(intent);
            } else if (recognizedText.contains("exploration")) {
                Intent intent = new Intent(VisuallyImpairedActivity.this, Exploration.class);
                startActivity(intent);
            } else if (recognizedText.contains("navigation")) {
                Intent intent = new Intent(VisuallyImpairedActivity.this, Navigation.class);
                startActivity(intent);
            }
            // Add more conditions for other keywords if necessary
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

