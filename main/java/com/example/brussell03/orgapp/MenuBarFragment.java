package com.example.brussell03.orgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
        final View view = inflater.inflate(R.layout.menu_bar_fragment, container, false);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        Intent m = new Intent(getActivity(), MainActivity.class);
                        Bundle menuExtras = new Bundle();
                        menuExtras.putInt("groups", activityCommander.changeActivityInt(1, view));
                        menuExtras.putInt("groupItems", activityCommander.changeActivityInt(2, view));
                        menuExtras.putStringArrayList("groupNames", activityCommander.changeActivityStr(1, view));
                        menuExtras.putStringArrayList("groupTypes", activityCommander.changeActivityStr(2, view));
                        menuExtras.putIntegerArrayList("groupItemNumbers", activityCommander.changeActivityIntArray(view));
                        ArrayList<ArrayList<String>> menu_groupItemNames =  activityCommander.changeActivityArray(view);
                        int menu_groups = activityCommander.changeActivityInt(1, view);
                        int menu_y = 1;
                        for(int x = 0; x < menu_groups; x++, menu_y++) {
                            ArrayList<String> menu_items = menu_groupItemNames.get(x);
                            menuExtras.putStringArrayList(String.valueOf(menu_y), menu_items);
                        }
                        menuExtras.putInt("notes", activityCommander.changeActivityInt(3, view));
                        menuExtras.putStringArrayList("noteNames", activityCommander.changeActivityStr(3, view));
                        menuExtras.putStringArrayList("noteDesc", activityCommander.changeActivityStr(4, view));
                        m.putExtras(menuExtras);
                        startActivity(m);
                        break;
                    case R.id.action_org:
                        Intent o = new Intent(getActivity(), OrganizerActivity.class);
                        Bundle org_extras = new Bundle();
                        org_extras.putInt("groups", activityCommander.changeActivityInt(1, view));
                        org_extras.putInt("groupItems", activityCommander.changeActivityInt(2, view));
                        org_extras.putBoolean("newGroup", false);
                        org_extras.putStringArrayList("groupNames", activityCommander.changeActivityStr(1, view));
                        org_extras.putStringArrayList("groupTypes", activityCommander.changeActivityStr(2, view));
                        org_extras.putIntegerArrayList("groupItemNumbers", activityCommander.changeActivityIntArray(view));
                        ArrayList<ArrayList<String>> org_groupItemNames =  activityCommander.changeActivityArray(view);
                        int org_groups = activityCommander.changeActivityInt(1, view);
                        int org_y = 1;
                        for(int x = 0; x < org_groups; x++, org_y++) {
                            ArrayList<String> items = org_groupItemNames.get(x);
                            org_extras.putStringArrayList(String.valueOf(org_y), items);
                        }
                        org_extras.putInt("notes", activityCommander.changeActivityInt(3, view));
                        org_extras.putStringArrayList("noteNames", activityCommander.changeActivityStr(3, view));
                        org_extras.putStringArrayList("noteDesc", activityCommander.changeActivityStr(4, view));
                        o.putExtras(org_extras);
                        startActivity(o);
                        break;
                    case R.id.action_pln:
                        Intent p = new Intent(getActivity(), PlannerActivity.class);
                        Bundle pln_extras = new Bundle();
                        pln_extras.putInt("groups", activityCommander.changeActivityInt(1, view));
                        pln_extras.putInt("groupItems", activityCommander.changeActivityInt(2, view));
                        pln_extras.putBoolean("newGroup", false);
                        pln_extras.putStringArrayList("groupNames", activityCommander.changeActivityStr(1, view));
                        pln_extras.putStringArrayList("groupTypes", activityCommander.changeActivityStr(2, view));
                        pln_extras.putIntegerArrayList("groupItemNumbers", activityCommander.changeActivityIntArray(view));
                        ArrayList<ArrayList<String>> pln_groupItemNames =  activityCommander.changeActivityArray(view);
                        int pln_groups = activityCommander.changeActivityInt(1, view);
                        int pln_y = 1;
                        for(int x = 0; x < pln_groups; x++, pln_y++) {
                            ArrayList<String> items = pln_groupItemNames.get(x);
                            pln_extras.putStringArrayList(String.valueOf(pln_y), items);
                        }
                        pln_extras.putInt("notes", activityCommander.changeActivityInt(3, view));
                        pln_extras.putStringArrayList("noteNames", activityCommander.changeActivityStr(3, view));
                        pln_extras.putStringArrayList("noteDesc", activityCommander.changeActivityStr(4, view));
                        p.putExtras(pln_extras);
                        startActivity(p);
                        break;
                }
                return true;
            }
        });

        return view;
    }

}
