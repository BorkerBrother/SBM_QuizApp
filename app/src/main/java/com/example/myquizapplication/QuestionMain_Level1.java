package com.example.myquizapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionMain_Level1 extends AppCompatActivity implements View.OnClickListener{

    private String[] levelString = {
            "1/10",
            "2/10",
            "3/10",
            "4/10",
            "5/10",
            "6/10",
            "7/10",
            "8/10",
            "9/10",
            "10/10"

    };


    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    TextView questionTotal;
    Button submitBtn;
    SeekBar seekbar;

    private int score = 0;
    private int question = 0;
    int totalQuestions = QuestionAnswer.questions.length;
    private int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);

        questionTotal = findViewById(R.id.questionScore);
        //questionTextView.setAutoSizeTextTypeUniformWithConfiguration(10,20,2,1);
        //questionTextView.getAutoSizeMinTextSize();
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);


        seekbar = findViewById(R.id.seekScoreQuestion);
        seekbar.setEnabled(false);
        seekbar.setProgressDrawable(getResources().getDrawable(R.drawable.custom_seekbar_progress));
        seekbar.setThumb(getResources().getDrawable(R.drawable.seekbar_thumb));

        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        //totalQuestionTextView.setText("Total questions : " +totalQuestions);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        //ansB.setBackgroundColor(Color.RED);
        //ansC.setBackgroundColor(Color.RED);
        //ansD.setBackgroundColor(Color.RED);

        ansA.setBackgroundResource(R.drawable.buttoncolor);
        ansB.setBackgroundResource(R.drawable.buttoncolor);
        ansC.setBackgroundResource(R.drawable.buttoncolor);
        ansD.setBackgroundResource(R.drawable.buttoncolor);

        ansA.setBackgroundResource(R.drawable.roundstyle);
        ansB.setBackgroundResource(R.drawable.roundstyle);
        ansC.setBackgroundResource(R.drawable.roundstyle);
        ansD.setBackgroundResource(R.drawable.roundstyle);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() ==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            question += 10;
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            // choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.WHITE);
            clickedButton.setBackgroundResource(R.drawable.roundstyle_pressed);
        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }


        // TODO Change ANSWER to different questions
        questionTextView.setText(QuestionAnswer_Level1.questions[currentQuestionIndex]);

        questionTotal.setText(levelString[currentQuestionIndex]);

        ansA.setText(QuestionAnswer_Level1.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer_Level1.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer_Level1.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer_Level1.choices[currentQuestionIndex][3]);

        ansA.setBackgroundResource(R.drawable.roundstyle);
        ansB.setBackgroundResource(R.drawable.roundstyle);
        ansC.setBackgroundResource(R.drawable.roundstyle);
        ansD.setBackgroundResource(R.drawable.roundstyle);

        seekbar.setProgress(question);



    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestions*0.60){
            passStatus = "Glückwunsch du hast 100 Bees gesammelt";

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Du hast " + score + " richtige Fragen beantwortet")
                    .setNegativeButton("Menu",((dialogInterface, i) -> goMenu()))
                    .setCancelable(false)
                    .show();
        }
        else{
            passStatus = "Leider hast du nicht bestanden";

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Du hast " + score + " richtige Fragen beantwortet")
                    .setPositiveButton("Neustart",((dialogInterface, i) -> restartQuiz()))
                    .setNegativeButton("Menu",((dialogInterface, i) -> goMenu()))
                    .setCancelable(false)
                    .show();

        }

    }

    void restartQuiz(){
        score = 0;
        question = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }

    void goMenu(){
        question = 0;
        score = 0;
        currentQuestionIndex = 0;
        Intent intent = new Intent(this, QuestionMenu.class);
        startActivity(intent);

    }

}