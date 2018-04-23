package com.example.brussell03.orgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import java.util.ArrayList;

public class MenuBarFragment extends Fragment {

    private static Button menuSwapBtn, orgSwapBtn, plnSwapBtn, calSwapBtn, otherSwapBtn;
    private static final String TAG = "briansMessage";


    MenuBarListener activityCommander;
    public interface MenuBarListener {
        public int changeActivityInt(int x, View view);
        public ArrayList<String> changeActivityStr(int x, View view);
        public ArrayList<Integer> changeActivityIntArray(View view);
        public ArrayList<ArrayList<String>> changeActivityArray(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (MenuBarListener) getActivity();// Say Here
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_bar_fragment, container, false);

        Button menuSwapBtn = (Button) view.findViewById(R.id.menuSwapButton);
        Button orgSwapBtn = (Button) view.findViewById(R.id.orgSwapButton);
        Button plnSwapBtn = (Button) view.findViewById(R.id.plnSwapButton);
        Button calSwapBtn = (Button) view.findViewById(R.id.calSwapButton);
        Button otherSwapBtn = (Button) view.findViewById(R.id.otherSwapButton);

        menuSwapBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("groups", activityCommander.changeActivityInt(1, view));
                        extras.putInt("groupItems", activityCommander.changeActivityInt(2, view));
                        extras.putStringArrayList("groupNames", activityCommander.changeActivityStr(1, view));
                        extras.putStringArrayList("groupTypes", activityCommander.changeActivityStr(2, view));
                        extras.putIntegerArrayList("groupItemNumbers", activityCommander.changeActivityIntArray(view));
                        ArrayList<ArrayList<String>> groupItemNames =  activityCommander.changeActivityArray(view);
                        int groups = activityCommander.changeActivityInt(1, view);
                        int y = 1;
                        for(int x = 0; x < groups; x++) {
                            ArrayList<String> items = groupItemNames.get(x);
                            extras.putStringArrayList(String.valueOf(y), items);
                            y++;
                        }
                        i.putExtras(extras);
                        startActivity(i);
                    }
                }
        );

        orgSwapBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), OrganizerActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("groups", activityCommander.changeActivityInt(1, view));
                        extras.putInt("groupItems", activityCommander.changeActivityInt(2, view));
                        extras.putBoolean("newGroup", false);
                        extras.putStringArrayList("groupNames", activityCommander.changeActivityStr(1, view));
                        extras.putStringArrayList("groupTypes", activityCommander.changeActivityStr(2, view));
                        extras.putIntegerArrayList("groupItemNumbers", activityCommander.changeActivityIntArray(view));
                        ArrayList<ArrayList<String>> groupItemNames =  activityCommander.changeActivityArray(view);
                        int groups = activityCommander.changeActivityInt(1, view);
                        int y = 1;
                        for(int x = 0; x < groups; x++) {
                            ArrayList<String> items = groupItemNames.get(x);
                            extras.putStringArrayList(String.valueOf(y), items);
                            y++;
                        }
                        i.putExtras(extras);
                        startActivity(i);
                    }
                }
        );

        plnSwapBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        //Intent i = new Intent(getActivity(), OrganizerActivity.class);
                        //startActivity(i);
                    }
                }
        );

        calSwapBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        //Intent i = new Intent(getActivity(), OrganizerActivity.class);
                        //startActivity(i);
                    }
                }
        );

        otherSwapBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        //Intent i = new Intent(getActivity(), OrganizerActivity.class);
                        //startActivity(i);
                    }
                }
        );

        return view;
    }

}
