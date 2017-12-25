package com.example.momotaro.notetictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Hard2Play3x3 extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;
    //ปุ่มย้อนกลับ

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
        setContentView(R.layout.activity_hard2_play3x3);

        initialView();

        buttonController();

        writeXOonButton(buttons[1][1], "O");
        //writeXOonButton(buttons[1][1], getColor() );


    }   // Main Method

    private void writeXOonButton(Button btnClick, String strXO) {

        btnClick.setText(strXO);
        btnClick.setClickable(false);

    }   // writeX

    private void buttonController() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1].setOnClickListener(Hard2Play3x3.this);
            }
        }

        backButton.setOnClickListener(Hard2Play3x3.this);
        //ปุ่มย้อนกลับ
    }   // buttonController

    private void initialView() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1] = (Button) findViewById(ints[i][i1]);
            }   //for
        }   // for
        backButton = (Button) findViewById(R.id.backbutton1);
        //ปุ่นย้อนกลับ
    }   // initial

    @Override
    public void onClick(View view) {

        if (view == backButton) {
            Intent intent = new Intent(Hard2Play3x3.this, SelectMode.class);
            startActivity(intent);
        }
        //ปุ่นย้อนกลับ
    }
}   // Main Class
