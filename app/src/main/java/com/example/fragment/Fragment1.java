package com.example.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

public class Fragment1 extends Fragment {

    private static final int REQUEST_CODE_SPEECH_INPUT = 100;

    private EditText editTextPageName;
    private Button buttonNavigate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        editTextPageName = view.findViewById(R.id.editTextPageName);
        buttonNavigate = view.findViewById(R.id.buttonNavigate);
        ImageView speakNowImageView = view.findViewById(R.id.imageView);



        speakNowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        return view;
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

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == getActivity().RESULT_OK && data != null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String recognizedText = results.get(0).toLowerCase(); // get the most probable result and convert it to lower case

            // Check for the keywords in the recognized text
            if (recognizedText.contains("visually impaired")) {
                Intent intent = new Intent(getActivity(), VisuallyImpairedActivity.class);
                startActivity(intent);
            } else if (recognizedText.contains("mobility impaired")) {
                Intent intent = new Intent(getActivity(), MobilityImpairedActivity.class);
                startActivity(intent);
            }
            // Add more conditions for other keywords if necessary
        }
    }






    }








