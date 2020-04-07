package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT_1 = "com.example.tictactoe.EXTRA_TEXT_1";
    public static final String EXTRA_TEXT_2 = "com.example.tictactoe.EXTRA_TEXT_2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });
    }


    public void openGameActivity(){
        EditText editText1 = (EditText) findViewById(R.id.player1nameedittext);
        String text1 = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.player2nameedittext);
        String text2 = editText2.getText().toString();

        if (text1.equals("")&&text2.equals("")){
            Toast.makeText(this,"Please input players names", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, Game.class);
            intent.putExtra(EXTRA_TEXT_1, text1);
            intent.putExtra(EXTRA_TEXT_2, text2);
            startActivity(intent);
        }
    }
}
