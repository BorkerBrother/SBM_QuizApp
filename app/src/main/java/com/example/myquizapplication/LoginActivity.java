package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myquizapplication.databinding.ActivityLoginAcitvityBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginAcitvityBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginAcitvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String passwort = binding.loginPassword.getText().toString();

                if (email.equals("") || passwort.equals(""))
                    Toast.makeText(LoginActivity.this,"All fields are mandatory", Toast.LENGTH_SHORT);
                else {
                    Boolean checkCredentials = databaseHelper.checkEmailpassword(email,passwort);

                    if (checkCredentials == true ){
                        Toast.makeText(LoginActivity.this,"Login Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), QuestionMenu.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Invalid Credentials", Toast.LENGTH_SHORT);
                    }
                }
            }
        });


    }
}