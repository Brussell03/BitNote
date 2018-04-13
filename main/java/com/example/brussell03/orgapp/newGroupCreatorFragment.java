package com.example.brussell03.orgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
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
import android.widget.EditText;

public class newGroupCreatorFragment extends Fragment{

    NewGroupListener activityCommander;
    public interface NewGroupListener{
        public void newGroupInput(String name, String type, View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (NewGroupListener) getActivity();// Say Here
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_group_creator_fragment, container, false);

        Button newGroupFinish = (Button)view.findViewById(R.id.newGroupFinish);
        EditText newGroupName = (EditText)view.findViewById(R.id.newGroupNameInput);
        EditText newGroupType = (EditText)view.findViewById(R.id.newGroupTypeInput);
        final String name = newGroupName.getText().toString();
        final String type = newGroupType.getText().toString();


        newGroupFinish.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        activityCommander.newGroupInput(name, type, view);
                    }
                }
        );

        return view;
    }

}
