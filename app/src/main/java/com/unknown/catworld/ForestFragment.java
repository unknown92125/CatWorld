package com.unknown.catworld;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ForestFragment extends Fragment {

    DrawCatView drawCatView;

    public ForestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forest, container, false);

        drawCatView = view.findViewById(R.id.draw_cat_view_forest);

        return view;
    }

}
