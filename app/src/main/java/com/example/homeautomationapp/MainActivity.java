package com.example.homeautomationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    AppBarLayout appBarLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tablayout);
        appBarLayout = findViewById(R.id.appbarlayout);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdaptor adaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        adaptor.AddFragment(new room1freg(MainActivity.this),"Room 1");
        adaptor.AddFragment(new room2freg(),"Room 2");
        adaptor.AddFragment(new room3freg(),"Room 3");

        viewPager.setAdapter(adaptor);
        tableLayout.setupWithViewPager(viewPager);
    }
}