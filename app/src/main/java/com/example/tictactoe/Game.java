package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1turn = true;
    private String player1Token = "X";
    private String player2Token = "O";
    private int roundCount;
    private int gameCount = 1;
    private int player1Points = 0;
    private int player2Points = 0;

    private TextView textView1;
    private TextView textView2;

    private String player1name = "Player 1";
    private String player2name = "Player 2";

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        player1name = intent.getStringExtra(MainActivity.EXTRA_TEXT_1);
        player2name = intent.getStringExtra(MainActivity.EXTRA_TEXT_2);

        textView1 = (TextView)findViewById(R.id.gametextView1);
        textView2 = (TextView)findViewById(R.id.gametextView2);

        textView1.setText(player1Token + "-" + player1name + ": " + player1Points);
        textView2.setText(player2Token + "-" + player2name + ": " + player2Points);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
       if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1turn) {
            ((Button)v).setText(player1Token);
        } else {
            ((Button)v).setText(player2Token);
        }

        roundCount++;

        if (checkForWin()) {
            if (player1turn) {
                gameCount++;
                player1Wins();
            } else {
                gameCount++;
                player2Wins();
            }
        } else if (roundCount == 9) {
            gameCount++;
            draw();
        } else {
            player1turn = !player1turn;
        }



    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")) {
            return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, player1name + " wins! " + whoesTurn() + "'s turn", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, player2name + " wins! " + whoesTurn() + "'s turn", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw! " + whoesTurn() + "'s turn", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    @SuppressLint("SetTextI18n")
    private void updatePointsText() {
        textView1.setText(player1Token + "-" + player1name + ": " + player1Points);
        textView2.setText(player2Token + "-" + player2name + ": " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1turn = isPlayer1Round();
    }


    private void resetGame() {
        gameCount = 1;
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    private boolean isPlayer1Round() {
        return (gameCount%2==1)?true:false;
    }

    private String whoesTurn() {
        return (gameCount%2==1)?player1name:player2name;
    }

}
