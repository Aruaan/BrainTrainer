package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;

    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button playAgainButton;
    TextView timerTextView;
    TextView resultTextView;
    TextView sumTextView;
    TextView scoreTextView;
    int number1;
    int number2;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<>();
    CountDownTimer countDownTimer;
    Random rand = new Random();
    int locationOfCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        playAgainButton = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView.setText(score + "/" + numberOfQuestions);
    }


    public void start(View v) {
        startOfGame();
    }

    public void playAgain(View v) {
        startOfGame();
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        numberOfQuestions=0;
        scoreTextView.setText(score + "/" + numberOfQuestions);

    }

    public void chooseAnswer(View v) {
        if (Integer.toString(locationOfCorrectAnswer).equals(v.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        newQuestion();
    }

    public void gameFinished() {
        playAgainButton.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.INVISIBLE);
        answer3.setVisibility(View.INVISIBLE);
        answer1.setVisibility(View.INVISIBLE);
        answer4.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        resultTextView.setText("Your score is:" + score + " out of:" + numberOfQuestions);

    }

    public void startOfGame() {

        goButton.setVisibility(View.INVISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer1.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);

        newQuestion();

        countDownTimer = new CountDownTimer(30000 - 200, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                String secondString = Integer.toString(seconds);
                if (seconds <= 9) {
                    secondString = "0" + secondString;
                }
                timerTextView.setText("0:" + secondString);
            }

            @Override
            public void onFinish() {

                gameFinished();


            }
        }.start();
    }

    public void newQuestion() {
        number1 = rand.nextInt(21);
        number2 = rand.nextInt(21);
        sumTextView.setText(number1 + " + " + number2);
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(number1 + number2);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == number2 + number1) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);


            }
        }
        answer1.setText(String.valueOf(answers.get(0)));
        answer2.setText(String.valueOf(answers.get(1)));
        answer3.setText(String.valueOf(answers.get(2)));
        answer4.setText(String.valueOf(answers.get(3)));

    }
}