package com.unknown.catworld;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class RoomFragment extends Fragment implements View.OnClickListener {

    DrawCatView drawCatView;
    HomeActivity homeActivity;


    public RoomFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        homeActivity = (HomeActivity) getActivity();
        drawCatView = view.findViewById(R.id.draw_cat_view_room);
        view.findViewById(R.id.bt_cat_list).setOnClickListener(this);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        drawCatView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        drawCatView.pause();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_cat_list) {
            startActivity(new Intent(homeActivity, CatListActivity.class));
        }
    }
}
