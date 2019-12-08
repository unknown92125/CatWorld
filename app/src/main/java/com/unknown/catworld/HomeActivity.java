package com.unknown.catworld;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {

    public static String pageAt="room";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.view_pager);

        HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    pageAt = "room";
                } else if (position == 1) {
                    pageAt = "forest";
                }
                Log.e("HomeA", pageAt);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public class HomeAdapter extends FragmentPagerAdapter {

        Fragment[] arrFragments = new Fragment[2];

        public HomeAdapter(FragmentManager fm) {
            super(fm);

            arrFragments[0] = new RoomFragment();
            arrFragments[1] = new ForestFragment();

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }

    }
}
