package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartView extends AppCompatActivity implements View.OnClickListener{

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);


        startButton = findViewById(R.id.start_Btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        startButton.animate().translationYBy(-100).setDuration(400);

    }

    public void openActivity(){
        Intent intent = new Intent(this, QuestionMenu.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {

    }
}