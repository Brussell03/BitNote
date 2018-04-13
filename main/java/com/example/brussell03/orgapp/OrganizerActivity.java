package com.example.brussell03.orgapp;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
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
import android.support.v4.app.Fragment;


public class OrganizerActivity extends AppCompatActivity implements OrganizerTopFieldFragment.TopFieldListener, newGroupCreatorFragment.NewGroupListener {

    private static final String TAG = "briansMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);

        RelativeLayout organizerLayout = findViewById(R.id.organizerLayout);
    }

    

    public void topFieldInput(int i, View view) {
        switch(i) {
            case 1:
                FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		FragmentOne groupCreatorFragment = (FragmentOne) fm.findFragmentByTag("tag");

		if(groupCreatorFragment == null) {
		    groupCreatorFragment = new FragmentOne();
		    ft.add(R.id.newGroupCreatorContainer, groupCreatorFragment, "tag");
		    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		} else {
		    return true;
		}

		ft.commit();
            default:
                Log.i(TAG, "Something went wrong with declaring the new group");
        }
    }

    public void newGroupInput(String name, String type, View view) {
	
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
