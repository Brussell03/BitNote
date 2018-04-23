package com.example.brussell03.orgapp;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.CheckBox;

public class EditGroupActivity extends AppCompatActivity{

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    int group;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<String> fail = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();

    ArrayList<ArrayList<String>> groupItemNames = new ArrayList<>();
    ArrayList<String> itemNames = new ArrayList<>();
    int startingItems;
    int endingItems;

    int px;
    RelativeLayout mainLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        mainLayout = findViewById(R.id.groupViewLayout);

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
            for(int x = 0; x < groups; x++, y++) {
                ArrayList<String> items = data.getStringArrayList(String.valueOf(y));
                groupItemNames.add(items);
            }
            group = data.getInt("group");
            startingItems = groupItemNumbers.get(group-1);

            if (groupItemNames.get(group - 1) != null)
                itemNames = groupItemNames.get(group - 1);

            Log.i(TAG, Integer.toString(group));
            Log.i(TAG, String.valueOf(groupItemNumbers));

            TextView groupNameLabel = (TextView) findViewById(R.id.groupViewNameLabel);
            TextView groupTypeLabel = (TextView) findViewById(R.id.groupViewTypeLabel);
            groupNameLabel.setText(groupNames.get(group - 1));
            groupTypeLabel.setText(groupTypes.get(group - 1));

            for(int t = 0; t < groupItemNumbers.get(group - 1); t++) {
                String name = (String) itemNames.get(t);

                Log.i(TAG, "Step 4");

                EditText itemName = new EditText(EditGroupActivity.this);
                itemName.setText(name);
                itemName.setHeight(px/8);
                itemName.setWidth(px/2 + px/4);

                CheckBox itemCheck = new CheckBox(EditGroupActivity.this);
                itemCheck.setWidth(px/8);
                itemCheck.setHeight(px/8);

                RelativeLayout.LayoutParams itemNameDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                itemNameDetails.setMargins(10 + px/10, 160 + (t * (px/8 + 10)), 0, 0);

                RelativeLayout.LayoutParams itemCheckDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                itemCheckDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                itemCheckDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                itemCheckDetails.setMargins(5, 160 + (t * (px/8 + 10)), 0, 0);

                mainLayout.addView(itemCheck, itemCheckDetails);
                mainLayout.addView(itemName, itemNameDetails);
            }

        }

        Button groupBackButton = findViewById(R.id.groupViewBackButton);
        Button groupNewItemButton = findViewById(R.id.groupViewEditButton);

        groupNewItemButton.setOnClickListener(new Button.OnClickListener(){
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                Log.i(TAG, "OK here");
                int items = groupItemNumbers.get(group - 1);
                Log.i(TAG, "Did it!");

                EditText itemName = new EditText(EditGroupActivity.this);
                itemName.setText(R.string.group_item_placeholder);
                itemName.setHeight(px/8);
                itemName.setWidth(px/2 + px/4);
                itemName.setId(1000 + groupItemNumbers.get(group - 1) + 1);

                CheckBox itemCheck = new CheckBox(EditGroupActivity.this);
                itemCheck.setWidth(px/8);
                itemCheck.setHeight(px/8);

                RelativeLayout.LayoutParams itemNameDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                itemNameDetails.setMargins(10 + px/10, 160 + (items * (px/8 + 10)), 0, 0);

                RelativeLayout.LayoutParams itemCheckDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                itemCheckDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                itemCheckDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                itemCheckDetails.setMargins(5, 160 + (items * (px/8 + 10)), 0, 0);

                mainLayout.addView(itemCheck, itemCheckDetails);
                mainLayout.addView(itemName, itemNameDetails);

                Log.i(TAG, Integer.toString(groupItemNumbers.get(group - 1)));
                groupItemNumbers.add(group - 1, groupItemNumbers.get(group - 1) + 1);
                groupItemNumbers.remove(group);
                Log.i(TAG, Integer.toString(groupItemNumbers.get(group - 1)));
            }
        });

        groupBackButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(EditGroupActivity.this, OrganizerActivity.class);
                Log.i(TAG, "1");
                endingItems = groupItemNumbers.get(group - 1);
                Bundle extras = new Bundle();
                extras.putInt("groups", groups);
                extras.putInt("groupItems", groupItems);
                extras.putStringArrayList("groupNames", groupNames);
                extras.putStringArrayList("groupTypes", groupTypes);
                extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
                /*for(int x = startingItems; x < endingItems; x++) {
                    int p = endingItems - x;
                    EditText textLabel = findViewById(1000 + groupItemNumbers.get(group - 1) - p);
                    String name = textLabel.getText().toString();
                    itemNames.add(name);
                }*/
                //groupItemNameArray.add(group - 1, itemNames);
                //groupItemNameArray.remove(group);
                //groupItemNames.add((? extends ArrayList<String>)groupItemNameArray);
                //groupItemNames = (ArrayList<ArrayList<String>>) groupItemNames;
                //groupItemNames.add(group - 1, (ArrayList<String>) itemNames);
                //groupItemNames.remove(group);
                //i.putExtra("groupItemNames", groupItemNames);
                for(int x = startingItems; x < endingItems; x++) {
                    Log.i(TAG, "2");
                    //Log.i(TAG, Integer.toString(itemNames.size()));
                    int p = endingItems - x;
                    EditText textLabel = findViewById(1000 + groupItemNumbers.get(group - 1) + 1 - p);
                    String name = textLabel.getText().toString();
                    //String name = "hey";
                    Log.i(TAG, "2.3");
                    itemNames.add(name);
                    Log.i(TAG, "2.6");
                    //itemNames.add(groupItemNumbers.get(group), name);
                    //itemNames.remove(groupItemNumbers.get(group - 1));
                    Log.i(TAG, "3");
                }

                Log.i(TAG, itemNames.toString());
                groupItemNames.add(group, itemNames);
                groupItemNames.remove(group - 1);
                Log.i(TAG, groupItemNames.toString());
                Log.i(TAG, "4");

                int y = 1;
                for(int x = 0; x < groups; x++, y++) {
                    ArrayList<String> items = groupItemNames.get(x);
                    extras.putStringArrayList(String.valueOf(y), items);
                }
                i.putExtras(extras);
                startActivity(i);
            }
        });

    }
}
