package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivityMI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomi);

    }
}
//Research:  12/1/23
//1. Sign up for estimote cloud account, create your own
//2.  Claim ownership of the beacons. You cannot program beacons if you do not claim ownership.
//3. Study tutorial one by one.
//4. Sign up in the forum.
//
//App:
//1. Make the homebutton for VI and MI direct to MI and VI, not ot main activity
//2. Figure out a way to reduce background noise for speech recognition for app. High priority.