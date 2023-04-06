package com.kristianjones.powersavingmodechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
This app will do the following:
1) Check whether the user is in a power saving mode.
2) Notify the user they are in power saving mode.
3) When notification pressed, send the user to the power saving settings menu.

 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}