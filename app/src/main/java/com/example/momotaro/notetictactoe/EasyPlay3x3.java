package com.example.momotaro.notetictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class EasyPlay3x3 extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private Button[][] buttons = new Button[3][3];
    private int[][] ints = new int[][]{
            {R.id.btn3x11, R.id.btn3x12, R.id.btn3x13},
            {R.id.btn3x21, R.id.btn3x22, R.id.btn3x23},
            {R.id.btn3x31, R.id.btn3x32, R.id.btn3x33}};
    private int timesAnInt = 1, indexArrayList = 0;
    private ArrayList<Button> buttonArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_play3x3);

        initialView();

        buttonController();

        setupArray();

    }   // Main Method

    private void setupArray() {
        buttonArrayList = new ArrayList<Button>();
        for (int i=0;i<3;i++) {
            for (int i1=0;i1<3;i1++) {
                buttonArrayList.add(buttons[i][i1]);
            }
        }




    }

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

                    Log.d("1MayV3", "buttonArrayList.size ==> ก่อนเลือก" + buttonArrayList.size());
                    indexArrayList = findIndexArrayList(i, i1);
                    Log.d("1MayV3", "indexArrayList ที่เลือก ==> " + indexArrayList);
                    buttonArrayList.remove(indexArrayList);
                    Log.d("1MayV3", "buttonArrayList.size ==> ที่เหลือ" + buttonArrayList.size());

                    Button[] myButtons = buttonArrayList.toArray(new Button[buttonArrayList.size()]);

                    Random random = new Random();
                    int intRandom = random.nextInt(buttonArrayList.size());
                    Log.d("1MayV3", "intRandom ==> " + intRandom);

                    writeXOonButton(myButtons[intRandom], "O");
                    buttonArrayList.remove(intRandom);



                    //For Write O on Button
                   // calculateByCom(i, i1, timesAnInt);  // ครั้งแรกส่งเลย 1

                    timesAnInt += 1;    //เพิ่มค่าอีก 1

                }   // if
            }   // for
        }   // for

    }   // onClick

    private int findIndexArrayList(int x, int y) {



        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                }
                break;
            case 1:
                switch (y) {
                    case 0:
                        return 3;
                    case 1:
                        return 4;
                    case 2:
                        return 5;
                }
                break;
            case 2:
                switch (y) {
                    case 0:
                        return 6;
                    case 1:
                        return 7;
                    case 2:
                        return 8;
                }
                break;
        }


        return 0;
    }
}   // Main Class
