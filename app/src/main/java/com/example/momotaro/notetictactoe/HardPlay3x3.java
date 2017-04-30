package com.example.momotaro.notetictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HardPlay3x3 extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button[][] buttons = new Button[3][3];
    private int[][] ints = new int[][]{
            {R.id.btn3x11, R.id.btn3x12, R.id.btn3x13},
            {R.id.btn3x21, R.id.btn3x22, R.id.btn3x23},
            {R.id.btn3x31, R.id.btn3x32, R.id.btn3x33}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_play3x3);

        initialView();

        buttonController();

    }   // Main Method

    private void buttonController() {

        for (int i=0;i<3;i++) {
            for (int i1=0;i1<3;i1++) {
                buttons[i][i1].setOnClickListener(HardPlay3x3.this);
            }
        }

    }   // buttonController

    private void initialView() {

        for (int i=0;i<3;i++) {
            for (int i1=0;i1<3;i1++) {
                buttons[i][i1] = (Button) findViewById(ints[i][i1]);
            }   //for
        }   // for

    }   // initial

    @Override
    public void onClick(View view) {
        String tag = "30AprilV1";
        Log.d(tag, "You Click ==> " + view.toString());


        for (int i=0;i<3;i++) {
            for (int i1=0;i1<3;i1++) {
                if (view == buttons[i][i1]) {
                    writeXonButton(buttons[i][i1]);
                }
            }
        }



    }   // onClick

    private void writeXonButton(Button btnClick) {

        btnClick.setText("X");

    }   // writeX

}   // Main Class
