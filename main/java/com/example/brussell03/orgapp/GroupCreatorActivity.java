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


public class GroupCreatorActivity extends AppCompatActivity {

    private static final String TAG = "newGroupLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator);

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

                /*Bundle extras = new Bundle();
                extras.putString("nameMessage", "Hi");
                extras.putString("typeMessage", "List");
                i.putExtras(extras);*/

                Log.i(TAG, "1");
                i.putExtra("nameMessage", nameString);
                i.putExtra("typeMessage", typeString);

                Log.i(TAG, "2");
                startActivity(i);
            }
        });

    }

}
