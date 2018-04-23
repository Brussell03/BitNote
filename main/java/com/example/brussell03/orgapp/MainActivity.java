package com.example.brussell03.orgapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout; //This is how you add in a layout
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;

public class MainActivity extends AppCompatActivity implements MenuBarFragment.MenuBarListener, OnGestureListener {

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<String> fail = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> groupItemNames = new ArrayList<>();
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, this);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            groups = data.getInt("groups");
            groupItems = data.getInt("groupItems");
            groupNames = data.getStringArrayList("groupNames");
            groupTypes = data.getStringArrayList("groupTypes");
            groupItemNumbers = data.getIntegerArrayList("groupItemNumbers");
            int y = 1;
            for(int x = 0; x < groups; x++) {
                ArrayList<String> items = data.getStringArrayList(String.valueOf(y));
                groupItemNames.add(items);
                y++;
            }
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
    public ArrayList<ArrayList<String>> changeActivityArray(View view) {
        return groupItemNames;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {
        if (motionEvent1.getX() - motionEvent2.getX() > 100) {
            //Left
            Intent i = new Intent(this, OrganizerActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("groups", groups);
            extras.putInt("groupItems", groupItems);
            extras.putStringArrayList("groupNames", groupNames);
            extras.putStringArrayList("groupTypes", groupTypes);
            extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
            int y = 1;
            for(int x = 0; x < groups; x++) {
                ArrayList<String> items = groupItemNames.get(x);
                extras.putStringArrayList(String.valueOf(y), items);
                y++;
            }
            i.putExtras(extras);
            startActivity(i);
            return true;
        }

        if (motionEvent2.getX() - motionEvent1.getX() > 100) {
            //Right
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}