package com.example.brussell03.orgapp;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout; //This is how you add in a layout
import android.widget.Button; //This is how you would add in a widget
import android.graphics.Color; //Put this in to have color
import android.content.res.Resources;  //Needs for finding devices resources
import android.util.TypedValue; //Need for doing width
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;

public class PlannerActivity extends AppCompatActivity implements MenuBarFragment.MenuBarListener, OnGestureListener{

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<String> fail = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> groupItemNames = new ArrayList<>();

    int notes;
    ArrayList<String> noteNames = new ArrayList<>();
    ArrayList<String> noteDesc = new ArrayList<>();

    GestureDetector gestureDetector;
    RelativeLayout noteLayout;
    int px;
    int startingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        gestureDetector = new GestureDetector(this, this);
        noteLayout = findViewById(R.id.NoteLayout);

        ScrollView scrollView = findViewById(R.id.plnScrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector != null && gestureDetector.onTouchEvent(event);
            }
        });

        Resources r = getResources();
        px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, r.getDisplayMetrics());

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
            notes = data.getInt("notes");
            noteNames = data.getStringArrayList("noteNames");
            noteDesc = data.getStringArrayList("noteDesc");
            startingItems = notes;
            Log.i(TAG, String.valueOf(noteNames));

            for(int t = 0; t < notes; t++) {
                String name = noteNames.get(t);

                TextView noteBackground = new TextView(PlannerActivity.this);
                noteBackground.setWidth(px/2 - 10);
                noteBackground.setHeight(px / 6);
                noteBackground.setText(null);
                if(t % 4 >= 2) {
                    noteBackground.setBackgroundColor(Color.parseColor("#1e0d61"));
                } else { noteBackground.setBackgroundColor(Color.parseColor("#812630")); }
                noteBackground.setId(3000 + t + 1);

                EditText noteName = new EditText(PlannerActivity.this);
                noteName.setWidth(px/2 - 20);
                noteName.setHeight(px / 6 - 10);
                //noteName.setText(R.string.group_item_placeholder);
                noteName.setText(name);
                noteName.setId(4000 + t + 1);
                noteName.setTextColor(Color.WHITE);

                RelativeLayout.LayoutParams noteBackgroundDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_START);
                noteBackgroundDetails.setMargins(newNoteXCoord(t), t/2 * (px/6 + 10), 0, 0);

                RelativeLayout.LayoutParams noteNameDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_START);
                noteNameDetails.setMargins(newNoteXCoord(t) + 10, t/2 * (px/6 + 10), 0, 0);

                noteLayout.addView(noteBackground, noteBackgroundDetails);
                noteLayout.addView(noteName, noteNameDetails);
            }

            ImageButton newNoteButton = findViewById(R.id.newNoteButton);
            newNoteButton.setOnClickListener(new ImageButton.OnClickListener() {
                public void onClick(View view) {
                    Log.i(TAG, "New Note Step 1");
                    TextView noteBackground = new TextView(PlannerActivity.this);
                    noteBackground.setWidth(px/2 - 10);
                    noteBackground.setHeight(px / 6);
                    noteBackground.setText(null);
                    if(notes % 4 >= 2) {
                        noteBackground.setBackgroundColor(Color.parseColor("#1e0d61"));
                    } else { noteBackground.setBackgroundColor(Color.parseColor("#812630")); }

                    Log.i(TAG, "New Note Step 2");
                    EditText noteName = new EditText(PlannerActivity.this);
                    noteName.setWidth(px/2 - 20);
                    noteName.setHeight(px / 6 - 10);
                    noteName.setText(R.string.group_item_placeholder);
                    noteName.setId(4000 + notes + 1);
                    noteName.setTextColor(Color.WHITE);

                    RelativeLayout.LayoutParams noteBackgroundDetails = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    noteBackgroundDetails.addRule(RelativeLayout.ALIGN_PARENT_START);
                    noteBackgroundDetails.setMargins(newNoteXCoord(notes), notes/2 * (px/6 + 10), 0, 0);

                    RelativeLayout.LayoutParams noteNameDetails = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    noteNameDetails.addRule(RelativeLayout.ALIGN_PARENT_START);
                    noteNameDetails.setMargins(newNoteXCoord(notes) + 10, notes/2 * (px/6 + 10), 0, 0);

                    Log.i(TAG, "New Note Step 3");
                    notes++;

                    noteLayout.addView(noteBackground, noteBackgroundDetails);
                    noteLayout.addView(noteName, noteNameDetails);

                    //decrease height
                    //ontouch make bigger & show desc
                }
            });


        }

    }

    public int newNoteXCoord(int x) {
        if(x % 2 == 0) {
            return 10;
        } else {
            return 20 + px/2;
        }
    }

    @Override
    public int changeActivityInt(int x, View view) {
        if(x == 1) {
            return groups;
        } else if(x == 2) {
            return groupItems;
        } else if(x == 3) {
            return notes;
        }
        return x;
    }

    @Override
    public ArrayList<String> changeActivityStr(int x, View view) {
        switch(x) {
            case 1:
                return groupNames;
            case 2:
                return groupTypes;
            case 3:
                for(int i = startingItems; i < notes; i++) {
                    //int p = endingItems - x;
                    //Log.i(TAG, "1");
                    EditText nameLabel = findViewById(4000 + i + 1);
                    String name = nameLabel.getText().toString();
                    //Log.i(TAG, "2");
                    noteNames.add(name);
                }
                return noteNames;
            case 4:
                return noteDesc;
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
        int endingItems = notes;
        Log.i(TAG, String.valueOf(endingItems));
        if (motionEvent1.getX() - motionEvent2.getX() > 100) {
            //Left
            Intent i = new Intent(this, MainActivity.class);
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
            for(int x = startingItems; x < endingItems; x++) {
                //int p = endingItems - x;
                //Log.i(TAG, "1");
                EditText nameLabel = findViewById(4000 + x + 1);
                String name = nameLabel.getText().toString();
                //Log.i(TAG, "2");
                noteNames.add(name);
            }

            i.putExtra("notes", notes);
            i.putExtra("noteNames", noteNames);
            i.putExtra("noteDesc", noteDesc);

            i.putExtras(extras);
            startActivity(i);
            return true;
        }

        if (motionEvent2.getX() - motionEvent1.getX() > 100) {
            //Right
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
            for(int x = startingItems; x < endingItems; x++) {
                //int p = endingItems - x;
                //Log.i(TAG, "1");
                EditText nameLabel = findViewById(4000 + x + 1);
                String name = nameLabel.getText().toString();
                //Log.i(TAG, "2");
                noteNames.add(name);
            }
            i.putExtra("notes", notes);
            i.putExtra("noteNames", noteNames);
            i.putExtra("noteDesc", noteDesc);
            i.putExtras(extras);
            startActivity(i);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

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
