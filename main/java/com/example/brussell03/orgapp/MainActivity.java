package com.example.brussell03.orgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout; //This is how you add in a layout
import android.widget.Button; //This is how you would add in a widget
import android.graphics.Color; //Put this in to have color
import android.widget.EditText;
import android.content.res.Resources;  //Needs for finding devices resources
import android.util.TypedValue; //Need for doing width
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity /*implements MenuBarFragment.MenuBarListener*/ {

    private static final String TAG = "briansMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout mainMenuLayout = findViewById(R.id.mainMenuLayout);

        /*TextView dateLbl = new TextView(this);
        dateLbl.setText("");
        dateLbl.setTextColor(Color.WHITE);

        RelativeLayout.LayoutParams dateLblDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        dateLblDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dateLblDetails.setMargins(0, 20, 0, 0);
        mainMenuLayout.addView(dateLbl, dateLblDetails);

        setContentView(mainMenuLayout);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
}