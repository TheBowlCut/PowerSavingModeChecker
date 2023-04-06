package com.kristianjones.powersavingmodechecker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
This app will do the following:
1) Check whether the user is in a power saving mode.
2) Notify the user they are in power saving mode.
3) When notification pressed, send the user to the power saving settings menu.

 */

public class MainActivity extends AppCompatActivity {

    Boolean powerSaveMode;

    PowerManager powerManager;

    TextView powerSaveBoolText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        powerSaveBoolText = findViewById(R.id.powerSaveBoolText);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void checkStatus (View view) {

        powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        powerSaveMode = powerManager.isPowerSaveMode();

        if(powerSaveMode){
            powerSaveBoolText.setText(getString(R.string.powerSaveModeTrue));
        } else {
            powerSaveBoolText.setText(getString(R.string.powerSaveModeFalse));
        }

    }
}