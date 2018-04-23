package com.example.brussell03.orgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout; //This is how you add in a layout
import android.widget.Button; //This is how you would add in a widget
import android.graphics.Color; //Put this in to have color
import android.content.res.Resources;  //Needs for finding devices resources
import android.util.TypedValue; //Need for doing width
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class OrganizerActivity extends AppCompatActivity implements MenuBarFragment.MenuBarListener {

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<String> fail = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> groupItemNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);

        RelativeLayout organizerLayout = findViewById(R.id.organizerLayout);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, r.getDisplayMetrics());

        Bundle data = getIntent().getExtras();
        Intent i = getIntent();

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

            for(int t = 0; t < groups; t++) {

                String name;
                String type;
                try {
                    name = groupNames.get(t);
                    type = groupTypes.get(t);
                } catch(Exception e) {
                    break;
                }

                TextView groupNameLabel = new TextView(this);
                groupNameLabel.setText(name);
                groupNameLabel.setTextSize(24);
                groupNameLabel.setTextColor(Color.WHITE);
                //groupNameLabel.setId(Integer.parseInt(newGroupName + "1"));
                //groupNameLabel.setId(groupItems + 1);

                TextView groupTypeLabel = new TextView(this);
                groupTypeLabel.setText(type);
                groupTypeLabel.setTextSize(16);
                groupTypeLabel.setTextColor(Color.WHITE);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "2"));
                //groupNameLabel.setId(groupItems + 1);

                Button groupEditButton = new Button(this);
                groupEditButton.setText(R.string.edit_group_button);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "3"));
                //groupNameLabel.setId(groupItems + 1);

                TextView groupBackground = new TextView(this);
                groupBackground.setText(null);
                groupBackground.setBackgroundColor(Color.BLUE);
                groupBackground.setWidth(px);
                groupBackground.setHeight(px / 5);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "4"));
                //groupNameLabel.setId(groupItems + 1);

                RelativeLayout.LayoutParams groupBackgroundDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupBackgroundDetails.setMargins(10, 150 + (t * (px / 5 + 10)), 0, 0);

                RelativeLayout.LayoutParams groupEditButtonDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                groupEditButtonDetails.setMargins(0, 160 + (t * (px / 5 + 10)), 10, 0);

                RelativeLayout.LayoutParams groupNameLabelDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                groupNameLabelDetails.setMargins(30, 160 + (t * (px / 5 + 10)), 0, 0);

                RelativeLayout.LayoutParams groupTypeLabelDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupTypeLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                groupTypeLabelDetails.setMargins(30, 220 + (t * (px / 5 + 10)), 0, 0);

                organizerLayout.addView(groupBackground, groupBackgroundDetails);
                organizerLayout.addView(groupEditButton, groupEditButtonDetails);
                organizerLayout.addView(groupNameLabel, groupNameLabelDetails);
                organizerLayout.addView(groupTypeLabel, groupTypeLabelDetails);

                final int x = t + 1;
                //groupItemNumbers.add(0);
                groupEditButton.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(OrganizerActivity.this, EditGroupActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("groups", groups);
                        extras.putInt("groupItems", groupItems);
                        extras.putStringArrayList("groupNames", groupNames);
                        extras.putStringArrayList("groupTypes", groupTypes);
                        extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
                        extras.putInt("group", x);
                        Log.i(TAG, String.valueOf(changeActivityArray(view)));
                        Log.i(TAG, "Step 1");
                        int y = 1;
                        for(int x = 0; x < changeActivityInt(1, view); x++) {
                            Log.i(TAG, "Step 2");
                            if(changeActivityArray(view) != null) {
                                Log.i(TAG, "Step 3");
                                ArrayList<ArrayList<String>> itemsArray = changeActivityArray(view);
                                Log.i(TAG, "Step 4");
                                ArrayList<String> items = new ArrayList<>();
                                Log.i(TAG, "Step 4.3");
                                if(itemsArray.size() != 0) {
                                    Log.i(TAG, String.valueOf(itemsArray.get(x)));
                                    Log.i(TAG, "Step 4.5");
                                    items = itemsArray.get(x);
                                    Log.i(TAG, "Step 5");
                                    extras.putStringArrayList(String.valueOf(y), items);
                                }
                                Log.i(TAG, "Step 6");
                                y++;
                            }
                        }
                        i.putExtras(extras);
                        startActivity(i);
                    }
                });

            }

            boolean newGroup = data.getBoolean("newGroup");
            if (newGroup) {
                String newGroupName = data.getString("nameMessage");
                String newGroupType = data.getString("typeMessage");

                Log.i(TAG, newGroupName);
                Log.i(TAG, newGroupType);

                groupNames.add(groups, newGroupName);
                groupTypes.add(groups, newGroupType);

                Log.i(TAG, "\n\n\n\n\n" + groupNames + "\n" + groupTypes + "\n\n\n\n");

                TextView groupNameLabel = new TextView(this);
                groupNameLabel.setText(newGroupName);
                groupNameLabel.setTextSize(24);
                groupNameLabel.setTextColor(Color.WHITE);
                //groupNameLabel.setId(Integer.parseInt(newGroupName + "1"));
                //groupNameLabel.setId(groupItems + 1);
                groupItems++;

                TextView groupTypeLabel = new TextView(this);
                groupTypeLabel.setText(newGroupType);
                groupTypeLabel.setTextSize(16);
                groupTypeLabel.setTextColor(Color.WHITE);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "2"));
                //groupNameLabel.setId(groupItems + 1);
                groupItems++;

                Button groupEditButton = new Button(this);
                groupEditButton.setText(R.string.edit_group_button);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "3"));
                //groupNameLabel.setId(groupItems + 1);
                groupItems++;

                TextView groupBackground = new TextView(this);
                groupBackground.setText(null);
                groupBackground.setBackgroundColor(Color.BLUE);
                groupBackground.setWidth(px);
                groupBackground.setHeight(px / 5);
                //groupTypeLabel.setId(Integer.parseInt(newGroupName + "4"));
                //groupNameLabel.setId(groupItems + 1);
                groupItems++;

                System.out.println(groupItems);

                RelativeLayout.LayoutParams groupBackgroundDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupBackgroundDetails.setMargins(10, 150 + (groups * (px / 5 + 10)), 0, 0);

                RelativeLayout.LayoutParams groupEditButtonDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                groupEditButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                groupEditButtonDetails.setMargins(0, 160 + (groups * (px / 5 + 10)), 10, 0);

                RelativeLayout.LayoutParams groupNameLabelDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                groupNameLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                groupNameLabelDetails.setMargins(30, 160 + (groups * (px / 5 + 10)), 0, 0);

                RelativeLayout.LayoutParams groupTypeLabelDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupTypeLabelDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                groupTypeLabelDetails.setMargins(30, 220 + (groups * (px / 5 + 10)), 0, 0);

                organizerLayout.addView(groupBackground, groupBackgroundDetails);
                organizerLayout.addView(groupEditButton, groupEditButtonDetails);
                organizerLayout.addView(groupNameLabel, groupNameLabelDetails);
                organizerLayout.addView(groupTypeLabel, groupTypeLabelDetails);

                groups++;
                groupItemNumbers.add(0);
                groupItemNames.add(new ArrayList<String>());

                groupEditButton.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(OrganizerActivity.this, EditGroupActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("groups", groups);
                        extras.putInt("groupItems", groupItems);
                        extras.putStringArrayList("groupNames", groupNames);
                        extras.putStringArrayList("groupTypes", groupTypes);
                        extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
                        extras.putInt("group", groups);

                        Log.i(TAG, String.valueOf(changeActivityArray(view)));
                        Log.i(TAG, "Step 1");
                        int y = 1;
                        for(int x = 0; x < changeActivityInt(1, view); x++) {
                            Log.i(TAG, "Step 2");
                            if(changeActivityArray(view) != null) {
                                Log.i(TAG, "Step 3");
                                ArrayList<ArrayList<String>> itemsArray = changeActivityArray(view);
                                Log.i(TAG, "Step 4");
                                ArrayList<String> items = new ArrayList<>();
                                Log.i(TAG, "Step 4.3");
                                if(itemsArray.size() != 0) {
                                    Log.i(TAG, String.valueOf(itemsArray.get(x)));
                                    Log.i(TAG, "Step 4.5");
                                    items = itemsArray.get(x);
                                    Log.i(TAG, "Step 5");
                                    extras.putStringArrayList(String.valueOf(y), items);
                                }
                                Log.i(TAG, "Step 6");
                                y++;
                            }
                        }
                        i.putExtras(extras);
                        startActivity(i);
                    }
                });

            }

        }

        Button newGroupButton = (Button) findViewById(R.id.newGroupButton);

        newGroupButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(OrganizerActivity.this, GroupCreatorActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("groups", groups);
                extras.putInt("groupItems", groupItems);
                extras.putStringArrayList("groupNames", groupNames);
                extras.putStringArrayList("groupTypes", groupTypes);
                extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
                //i.putExtra("groupItemNames", groupItemNames);
                int y = 1;
                for(int x = 0; x < groups; x++) {
                    ArrayList<String> items = groupItemNames.get(x);
                    extras.putStringArrayList(String.valueOf(y), items);
                    y++;
                }
                i.putExtras(extras);
                startActivity(i);
            }
        });

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
        switch(x) {
            case 1:
                return groupNames;
            case 2:
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
