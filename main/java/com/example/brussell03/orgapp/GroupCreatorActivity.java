package com.example.brussell03.orgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;


public class GroupCreatorActivity extends AppCompatActivity {

    private static final String TAG = "newGroupLog";

    int groups;
    int groupItems;
    ArrayList<String> groupNames = new ArrayList<String>();
    ArrayList<String> groupTypes = new ArrayList<String>();
    ArrayList<Integer> groupItemNumbers = new ArrayList<Integer>();
    ArrayList<? extends ArrayList<String>> groupItemNames = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            groups = data.getInt("groups");
            groupItems = data.getInt("groupItems");
            groupNames = data.getStringArrayList("groupNames");
            groupTypes = data.getStringArrayList("groupTypes");
            groupItemNumbers = data.getIntegerArrayList("groupItemNumbers");
            groupItemNames = data.getParcelableArrayList("groupItemNames");
        }

        Button finishButton = (Button) findViewById(R.id.newGroupFinish);

        finishButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(GroupCreatorActivity.this, OrganizerActivity.class);

                final EditText nameInput = (EditText) findViewById(R.id.newGroupNameInput);
                final String nameString = nameInput.getText().toString();
                //i.putExtra("nameMessage", nameString);

                final EditText typeInput = (EditText) findViewById(R.id.newGroupTypeInput);
                final String typeString = typeInput.getText().toString();
                //i.putExtra("typeMessage", typeString);

                Bundle extras = new Bundle();
                extras.putInt("groups", groups);
                extras.putInt("groupItems", groupItems);
                extras.putStringArrayList("groupNames", groupNames);
                extras.putStringArrayList("groupTypes", groupTypes);
                extras.putIntegerArrayList("groupItemNumbers", groupItemNumbers);
                i.putExtras(extras);

                Log.i(TAG, "1");
                i.putExtra("nameMessage", nameString);
                i.putExtra("typeMessage", typeString);
                i.putExtra("newGroup", true);
                i.putExtra("groupItemNames", groupItemNames);

                Log.i(TAG, "2");
                startActivity(i);
            }
        });

    }

}
