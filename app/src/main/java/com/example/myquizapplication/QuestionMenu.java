package com.example.myquizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.widget.SeekBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class QuestionMenu extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    SeekBar seekbarBees;

    /// TODO Score
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    final String key = "speicher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_menu);

        /// Viewpager
        viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment1(), "Level 1" );
        vpAdapter.addFragment(new Fragment2(), "Level 2" );
        vpAdapter.addFragment(new Fragment3(), "Level 3" );
        viewPager.setAdapter(vpAdapter);

        seekbarBees = findViewById(R.id.seekScoreBees);
        seekbarBees.setEnabled(false);
        seekbarBees.setProgressDrawable(getResources().getDrawable(R.drawable.custom_seekbar_progress));
        seekbarBees.setThumb(getResources().getDrawable(R.drawable.seekbar_thumb));

    }

    private void addTabs(ViewPager viewPager) {
    }


    // Hier musst du den entsprechenden FragmentHolder definieren
    // Je nach deinem Layout kann dies variieren
}
