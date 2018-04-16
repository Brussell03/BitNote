package com.example.brussell03.orgapp;

import android.support.v4.app.FragmentTransaction;
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
import android.graphics.Color;

public class OrganizerActivity extends AppCompatActivity implements OrganizerTopFieldFragment.TopFieldListener {

    private static final String TAG = "briansMessage";
    String[] groupNames;
    String[] groupTypes;
    int groupItems = 0;
    int groups = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);

        RelativeLayout organizerLayout = findViewById(R.id.organizerLayout);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, r.getDisplayMetrics());

        Bundle newGroupData = getIntent().getExtras();
        if(newGroupData != null) {
            String newGroupName = newGroupData.getString("nameMessage");
            String newGroupType = newGroupData.getString("typeMessage");

            Log.i(TAG, newGroupName);
            Log.i(TAG, newGroupType);

            //groupNames[groupNames.length + 1] = newGroupName;
            //groupTypes[groupTypes.length + 1] = newGroupType;

            TextView groupNameLabel = new TextView(this);
            groupNameLabel.setText(newGroupName);
            groupNameLabel.setTextSize(24);
            groupNameLabel.setTextColor(Color.WHITE);
            //groupNameLabel.setId(Integer.parseInt(newGroupName + "1"));
            groupNameLabel.setId(groupItems + 1);
            groupItems++;

            TextView groupTypeLabel = new TextView(this);
            groupTypeLabel.setText(newGroupType);
            groupTypeLabel.setTextSize(16);
            groupTypeLabel.setTextColor(Color.WHITE);
            //groupTypeLabel.setId(Integer.parseInt(newGroupName + "2"));
            groupNameLabel.setId(groupItems + 1);
            groupItems++;

            Button groupEditButton = new Button(this);
            groupEditButton.setText("Edit");
            //groupTypeLabel.setId(Integer.parseInt(newGroupName + "3"));
            groupNameLabel.setId(groupItems + 1);
            groupItems++;

            TextView groupBackground = new TextView(this);
            groupBackground.setText(null);
            groupBackground.setBackgroundColor(Color.BLUE);
            groupBackground.setWidth(px);
            groupBackground.setHeight(px/5);
            //groupTypeLabel.setId(Integer.parseInt(newGroupName + "4"));
            groupNameLabel.setId(groupItems + 1);
            groupItems++;

            //Log.i(TAG, );
            System.out.println(groupItems);

            RelativeLayout.LayoutParams groupBackgroundDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            groupBackgroundDetails.setMargins(10, 150 + (groups * (px/5 + 10)),0,0);

            RelativeLayout.LayoutParams groupEditButtonDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            groupEditButtonDetails.setMargins(0, 160 + (groups * (px/5 + 10)),10,0);

            RelativeLayout.LayoutParams groupNameLabelDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            groupNameLabelDetails.setMargins(30, 160 + (groups * (px/5 + 10)),0,0);

            RelativeLayout.LayoutParams groupTypeLabelDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            groupTypeLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            groupTypeLabelDetails.addRule(RelativeLayout.BELOW, groupNameLabel.getId());
            groupTypeLabelDetails.setMargins(30, 0,0,0);

            organizerLayout.addView(groupBackground, groupBackgroundDetails);
            organizerLayout.addView(groupEditButton, groupEditButtonDetails);
            organizerLayout.addView(groupNameLabel, groupNameLabelDetails);
            organizerLayout.addView(groupTypeLabel, groupTypeLabelDetails);

            groups++;
        }

    }

    

    public void topFieldInput(int i, View view) {
        switch(i) {
            case 1:
                /*FragmentManager fm = getSupportFragmentManager();
		        FragmentTransaction ft = fm.beginTransaction();
		        //Fragment groupCreatorFragment = fm.findFragmentByTag("new_group_creator_fragment");
                Fragment groupCreatorFragment = new Fragment();
                ft.add(R.id.newGroupCreatorContainer, groupCreatorFragment, "My Creator");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

		        if(groupCreatorFragment == null) {
		            //groupCreatorFragment = new Fragment();

		        } else {
		            return;
		        }

		        ft.commit();*/
            default:
                Log.i(TAG, "Something went wrong with declaring the new group");
        }
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
