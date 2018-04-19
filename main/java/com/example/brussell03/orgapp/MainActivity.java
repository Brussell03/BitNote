package com.example.brussell03.orgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout; //This is how you add in a layout
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuBarFragment.MenuBarListener {

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<String> fail = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<? extends ArrayList<String>> groupItemNames = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            groups = data.getInt("groups");
            groupItems = data.getInt("groupItems");
            groupNames = data.getStringArrayList("groupNames");
            groupTypes = data.getStringArrayList("groupTypes");
            groupItemNumbers = data.getIntegerArrayList("groupItemNumbers");
            groupItemNames = data.getParcelableArrayList("groupItemNames");
        }

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
    public int changeActivityInt(int x, View view) {
        if(x == 1) {
            return groups;
        } else if(x == 2) {
            return groupItems;
        }
        return x;
    }

    @Override
    public ArrayList<String> changeActivityStr(int x, View view) {
        if(x == 1) {
            return groupNames;
        } else if(x == 2) {
            return groupTypes;
        }
        return fail;
    }

    @Override
    public ArrayList<Integer> changeActivityIntArray(View view) {
        return groupItemNumbers;
    }

    @Override
    public ArrayList<? extends ArrayList<String>> changeActivityArray(View view) {return groupItemNames;}

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