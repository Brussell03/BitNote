package com.example.brussell03.orgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import android.widget.Spinner;


public class GroupCreatorActivity extends AppCompatActivity {

    private static final String TAG = "briansMessage";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> groupItemNames = new ArrayList<>();

    int notes;
    ArrayList<String> noteNames = new ArrayList<>();
    ArrayList<String> noteDesc = new ArrayList<>();

    String typeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator);

        Log.i(TAG, "Creator Step 1");
        final Spinner typeDropdown = findViewById(R.id.newGroupTypeInput);
        Log.i(TAG, "Creator Step 2");
        String [] dropdownItems = new String[] {"List", "CheckList"};
        Log.i(TAG, "Creator Step 3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownItems);
        Log.i(TAG, "Creator Step 4");
        typeDropdown.setAdapter(adapter);
        Log.i(TAG, "Creator Step 5");
        typeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeString = adapterView.getItemAtPosition(i).toString();
                Log.i(TAG, "Creator Step 7");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        Log.i(TAG, "Creator Step 6");

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
            Log.i(TAG, groupItemNames.toString());
        }

        Button finishButton = (Button) findViewById(R.id.newGroupFinish);

        finishButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(GroupCreatorActivity.this, OrganizerActivity.class);

                final EditText nameInput = (EditText) findViewById(R.id.newGroupNameInput);
                final String nameString = nameInput.getText().toString();

                groups++;
                groupItems += 4;
                groupNames.add(nameString);
                groupTypes.add(typeString);
                groupItemNumbers.add(0);
                groupItemNames.add(new ArrayList<String>());

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

                Log.i(TAG, "1");
                i.putExtra("nameMessage", nameString);
                i.putExtra("typeMessage", typeString);
                i.putExtra("notes", notes);
                i.putExtra("noteNames", noteNames);
                i.putExtra("noteDesc", noteDesc);

                Log.i(TAG, "2");
                startActivity(i);
            }
        });

    }
}
