package com.unknown.catworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Cats> arrListCats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_start).setOnClickListener(this);

        addCats();

    }

    private void addCats() {
        arrListCats.add(new Cats("야옹이", "room", true, new int[]{R.drawable.cat_1_idle_1, R.drawable.cat_1_idle_2}, new int[]{R.drawable.cat_1_dig_1, R.drawable.cat_1_dig_2}));
        arrListCats.add(new Cats("파랑이", "room", true, new int[]{R.drawable.cat_2_idle_1, R.drawable.cat_2_idle_2}, new int[]{R.drawable.cat_2_dig_1, R.drawable.cat_2_dig_2}));
        ////////////////////////////////////////////
        arrListCats.add(new Cats("파랑이1", "room", false, new int[]{R.drawable.cat_2_idle_1, R.drawable.cat_2_idle_2}, new int[]{R.drawable.cat_2_dig_1, R.drawable.cat_2_dig_2}));
        arrListCats.add(new Cats("파랑이2", "room", false, new int[]{R.drawable.cat_2_idle_1, R.drawable.cat_2_idle_2}, new int[]{R.drawable.cat_2_dig_1, R.drawable.cat_2_dig_2}));
        arrListCats.add(new Cats("파랑이3", "room", false, new int[]{R.drawable.cat_2_idle_1, R.drawable.cat_2_idle_2}, new int[]{R.drawable.cat_2_dig_1, R.drawable.cat_2_dig_2}));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_start) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
