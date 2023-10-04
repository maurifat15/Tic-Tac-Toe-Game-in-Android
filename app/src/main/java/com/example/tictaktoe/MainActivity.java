package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Game state variables
    int flag = 0;
    int count = 0;
    int countXWins = 0;
    int countOWins = 0;
    int countDraws = 0;
    String Btn01, Btn02, Btn03, Btn04, Btn05, Btn06, Btn07, Btn08, Btn09;

    // Buttons, LinearLayout, and TextViews
    Button ticBtn01, ticBtn02, ticBtn03, ticBtn04, ticBtn05, ticBtn06, ticBtn07, ticBtn08, ticBtn09;
    TextView textViewXWins, textViewOWins, textViewDraws;
    LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and text views
        init();

        parentLayout = findViewById(R.id.parentLayout);

        // Display initial counts
        updateCounters();
    }

    // Initialize buttons and text views
    private void init() {
        ticBtn01 = findViewById(R.id.ticBtn01);
        ticBtn02 = findViewById(R.id.ticBtn02);
        ticBtn03 = findViewById(R.id.ticBtn03);
        ticBtn04 = findViewById(R.id.ticBtn04);
        ticBtn05 = findViewById(R.id.ticBtn05);
        ticBtn06 = findViewById(R.id.ticBtn06);
        ticBtn07 = findViewById(R.id.ticBtn07);
        ticBtn08 = findViewById(R.id.ticBtn08);
        ticBtn09 = findViewById(R.id.ticBtn09);

        textViewXWins = findViewById(R.id.textViewXWins);
        textViewOWins = findViewById(R.id.textViewOWins);
        textViewDraws = findViewById(R.id.textViewDraws);
    }

    // Handle button click event
    public void CheckClick(View view) {
        Button btnCurrent = (Button) view;

        // Check if button is empty
        if (btnCurrent.getText().toString().equals("")) {
            count++;
            if (flag == 0) {
                btnCurrent.setText("X");
                flag = 1;
            } else {
                btnCurrent.setText("O");
                flag = 0;
            }

            // Check for a winner
            if (count > 4) {
                Btn01 = ticBtn01.getText().toString();
                Btn02 = ticBtn02.getText().toString();
                Btn03 = ticBtn03.getText().toString();
                Btn04 = ticBtn04.getText().toString();
                Btn05 = ticBtn05.getText().toString();
                Btn06 = ticBtn06.getText().toString();
                Btn07 = ticBtn07.getText().toString();
                Btn08 = ticBtn08.getText().toString();
                Btn09 = ticBtn09.getText().toString();

                String winner = checkWinner();

                if (winner != null) {
                    Toast.makeText(this, "Winner is: " + winner, Toast.LENGTH_SHORT).show();

                    if (winner.equals("X")) {
                        countXWins++;
                        parentLayout.setBackgroundColor(getResources().getColor(R.color.colorXWin));
                    } else {
                        countOWins++;
                        parentLayout.setBackgroundColor(getResources().getColor(R.color.colorOWin));
                    }

                    updateCounters();
                    resetGame();
                } else if (count == 9) {
                    Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                    countDraws++;
                    parentLayout.setBackgroundColor(getResources().getColor(R.color.colorDraw));
                    updateCounters();
                    resetGame();
                }
            }

            // Apply rotate animation
            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            btnCurrent.startAnimation(rotate);
        }
    }

    // Check for a winner
    private String checkWinner() {
        // Check rows
        if (checkLine(Btn01, Btn02, Btn03)) return Btn01;
        if (checkLine(Btn04, Btn05, Btn06)) return Btn04;
        if (checkLine(Btn07, Btn08, Btn09)) return Btn07;

        // Check columns
        if (checkLine(Btn01, Btn04, Btn07)) return Btn01;
        if (checkLine(Btn02, Btn05, Btn08)) return Btn02;
        if (checkLine(Btn03, Btn06, Btn09)) return Btn03;

        // Check diagonals
        if (checkLine(Btn01, Btn05, Btn09)) return Btn01;
        if (checkLine(Btn03, Btn05, Btn07)) return Btn03;

        return null;
    }

    // Check if a line has the same symbols (X or O)
    private boolean checkLine(String a, String b, String c) {
        return !a.equals("") && a.equals(b) && b.equals(c);
    }

    // Reset the game state
    private void resetGame() {
        ticBtn01.setText("");
        ticBtn02.setText("");
        ticBtn03.setText("");
        ticBtn04.setText("");
        ticBtn05.setText("");
        ticBtn06.setText("");
        ticBtn07.setText("");
        ticBtn08.setText("");
        ticBtn09.setText("");
        count = 0;
        flag = 0;
    }

    // Handle reset button click
    public void onResetClick(View view) {
        resetGame();
        parentLayout.setBackgroundColor(getResources().getColor(R.color.black));
    }

    // Update win counters
    private void updateCounters() {
        textViewXWins.setText("X Wins: " + countXWins);
        textViewOWins.setText("O Wins: " + countOWins);
        textViewDraws.setText("Draws: " + countDraws);
    }
}


