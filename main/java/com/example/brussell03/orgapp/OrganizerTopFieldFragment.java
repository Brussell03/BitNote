package com.example.brussell03.orgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class OrganizerTopFieldFragment extends Fragment {
    private static final String TAG = "briansMessage";


    TopFieldListener activityCommander;
    public interface TopFieldListener{
        public void topFieldInput(int i, View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (TopFieldListener) getActivity();// Say Here
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.organizer_top_field_fragment, container, false);

        Button newGroupButton = (Button)view.findViewById(R.id.newGroupButton);

        newGroupButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GroupCreatorActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    /*public void menuButtonClicked(View view) {
        activityCommander.changeActivity(3, view);
        //Intent m = new Intent(this, MainActivity.class);
        //startActivity(m);
    }*/
}
