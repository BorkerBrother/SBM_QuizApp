package com.example.myquizapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {

    private Button lvl1Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Level Button

        lvl1Button = view.findViewById(R.id.lvl1Button);
        lvl1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openLevel(); }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void openLevel(){
        Intent intent = new Intent(getActivity(), QuestionMain.class);
        startActivity(intent);
    }
}