package com.together.todayhelp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.together.todayhelp.R;

public class CampusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus, null);
        initData();
        setListener();
        return view;
    }

    private void initData() {
    }

    private void setListener() {
    }

}
