package com.example.momotaro.notetictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EasyPlay3x3 extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button[][] buttons = new Button[3][3];
    private int[][] ints = new int[][]{
            {R.id.btn3x11, R.id.btn3x12, R.id.btn3x13},
            {R.id.btn3x21, R.id.btn3x22, R.id.btn3x23},
            {R.id.btn3x31, R.id.btn3x32, R.id.btn3x33}};
    private int timesAnInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_play3x3);

        initialView();

        buttonController();

    }   // Main Method

    private void writeXOonButton(Button btnClick, String strXO) {

        btnClick.setText(strXO);

    }   // writeX

    private void buttonController() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1].setOnClickListener(EasyPlay3x3.this);
            }
        }

    }

    private void initialView() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1] = (Button) findViewById(ints[i][i1]);
            }   //for
        }   // for

    }

    @Override
    public void onClick(View view) {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                if (view == buttons[i][i1]) {

                    //For Write X on Button
                    writeXOonButton(buttons[i][i1], "X");

                    //For Write O on Button
                   // calculateByCom(i, i1, timesAnInt);  // ครั้งแรกส่งเลย 1

                    timesAnInt += 1;    //เพิ่มค่าอีก 1

                }   // if
            }   // for
        }   // for

    }   // onClick
}   // Main Class
