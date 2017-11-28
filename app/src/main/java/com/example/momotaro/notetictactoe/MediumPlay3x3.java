package com.example.momotaro.notetictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class MediumPlay3x3 extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private int[][] ints = new int[][]{
            {R.id.btn3x11, R.id.btn3x12, R.id.btn3x13},
            {R.id.btn3x21, R.id.btn3x22, R.id.btn3x23},
            {R.id.btn3x31, R.id.btn3x32, R.id.btn3x33}};
    private boolean aBoolean = true; // Value True ==> first User Choose

    private int timesAnInt = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_play3x3);

        initialView();

        buttonController();

        writeXOonButton(buttons[1][1], "O");



    } // Main Method

    private void writeXOonButton(Button btnClick, String strXO) {

        btnClick.setText(strXO);
        btnClick.setClickable(false);

    }   // writeX

    private void buttonController() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {


                buttons[i][i1].setOnClickListener(MediumPlay3x3.this);

            }
        }

    }   // buttonController


    private void initialView() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1] = (Button) findViewById(ints[i][i1]);
            }   //for
        }   // for

    }   // initial

    @Override
    public void onClick(View view) {

    }


}  // Main Class
