package com.kristianjones.powersavingmodechecker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

/*
This app will do the following:
1) Check whether the user is in a power saving mode.
2) Notify the user they are in power saving mode.
3) When notification pressed, send the user to the power saving settings menu.

 */

public class MainActivity extends AppCompatActivity {

    Boolean powerSaveMode;

    DialogFragment dialogFragment;

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
            dialogFragment = new StartDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"StartDialogFragment");
        } else {
            powerSaveBoolText.setText(getString(R.string.powerSaveModeFalse));
        }

    }

    public static class StartDialogFragment extends DialogFragment{
        @NotNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.requestPowerModeOff)
                    .setPositiveButton(R.string.goToSettings, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                        public void onClick(DialogInterface dialog, int id) {
                            // ACTION_BATTERY_SAVER_SETTINGS - send user to power saving mode
                            Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}