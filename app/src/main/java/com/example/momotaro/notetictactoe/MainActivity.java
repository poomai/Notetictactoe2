package com.example.momotaro.notetictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button tic3x3Button, tic5x5Button, tic7x7Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialview();

        //Button Controller
        buttonacontroller();

    } // Method

    private void buttonacontroller() {

        tic3x3Button.setOnClickListener(MainActivity.this);
        tic5x5Button.setOnClickListener(MainActivity.this);
        tic7x7Button.setOnClickListener(MainActivity.this);
    }

    private void initialview() {
        tic3x3Button = (Button) findViewById(R.id.btn3x3);
        tic5x5Button = (Button) findViewById(R.id.btn5x5);
        tic7x7Button = (Button) findViewById(R.id.btn7x7);
    }

    @Override
    public void onClick(View v) {

        //For 3x3
        if (v == tic3x3Button) {

        }//For 5x5
        if (v == tic5x5Button) {

        }//For 7x7
        if (v == tic7x7Button) {
        }


    }
} // Main Class
