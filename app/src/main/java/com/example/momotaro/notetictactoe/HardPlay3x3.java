package com.example.momotaro.notetictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private boolean aBoolean = true; // Value True ==> first User Choose

    private int timesAnInt = 1;
    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_play3x3);

        initialView();

        buttonController();

    }   // Main Method

    private void buttonController() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1].setOnClickListener(HardPlay3x3.this);
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

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                if (view == buttons[i][i1]) {

                    //For Write X on Button
                    writeXOonButton(buttons[i][i1], "X");

                    //For Write O on Button
                    calculateByCom(i, i1, timesAnInt);  // ครั้งแรกส่งเลย 1

                    timesAnInt += 1;    //เพิ่มค่าอีก 1

                }   // if
            }   // for
        }   // for


    }   // onClick

    private void calculateByCom(int indexX, int indexy, int inttimes) {

        Log.d("30AprilV1", "intTime ==> " + inttimes);
        Log.d("30AprilV1", "indexX ==> " + indexX);
        Log.d("30AprilV1", "indexY ==> " + indexy);



        switch (inttimes) {
            case 1: // คือการ คลิกครั้งแรก

                x1 = indexX;
                y1 = indexy;

                // ตรวจสอบว่า ถ้าคลิกครั้งแรก ที่รอบๆ ให้ AI เลือกกลาง แต่ถ้าเราเลือกกลาง AI จะเลือก 0,0
                if ((indexX == 1) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                } else {
                    writeXOonButton(buttons[1][1], "O");
                }
                break;
            case 2: // นี่คือการคลิกครั้งที่ สอง

                x2 = indexX;
                y2 = indexy;

                calculateByComTime2(x1, y1, indexX, indexy);

                break;
            case 3: // นี่คือการคลิกครั้ง สาม

                x3 = indexX;
                y3 = indexy;

                calculateByComTime3(x1, y1, x2, y2, indexX, indexy);

                break;
            case 4: // นี่คือการคลิกครั้ง สี่

                calculateByComTime4(x1, y1, x2, y2, x3, y3, indexX, indexy);

                break;
        }


    }   // calculateByCom

    private void calculateByComTime4(int x1, int y1, int x2, int y2, int x3, int y3, int indexX, int indexy) {

        String tag = "1MayV2";

        Log.d(tag, "x1 ==> " + x1);
        Log.d(tag, "y1 ==> " + y1);
        Log.d(tag, "x2 ==> " + x2);
        Log.d(tag, "y2 ==> " + y2);
        Log.d(tag, "x3 ==> " + x3);
        Log.d(tag, "y3 ==> " + y3);
        Log.d(tag, "indexX ==> " + indexX);
        Log.d(tag, "inDexY ==> " + indexy);

        //For (0,0)
        //จุดที่สอง (0,1), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && ( y1 == 0)) {

            if ((x2 == 0) && (y2 == 1)) {

                if ((x3 == 2) && (y3 == 0)) {

                    if ((indexX == 1) && (indexy == 2)) {
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }

                }

            }   // if2

            if ((x2 == 0) && (y2 == 2)) {

                if ((x3 == 2) && (y3 == 1)) {

                    if ((indexX == 1) && (indexy == 2)) {
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }

                }

            }

            if ((x2 == 1) && (y2 == 2)) {
            }

            if ((x2 == 2) && (y2 == 2)) {
            }

            if ((x2 == 2) && (y2 == 1)) {
            }

            if ((x2 == 2) && (y2 == 0)) {
            }

            if ((x2 == 1) && (y2 == 0)) {
            }

        }   // if1

        //For (0,1)

        //For (0,2)

        //For (1,2)

        //For (2,2)

        //For (2,1)

        //For (2,0)

        //For (1,0)


    }   // calculate

    private void calculateByComTime3(int x1, int y1, int x2, int y2, int indexX, int indexy) {

        String tag = "1MayV1";

        Log.d(tag, "x1 ==> " + x1);
        Log.d(tag, "y1 ==> " + y1);
        Log.d(tag, "x2 ==> " + x2);
        Log.d(tag, "y2 ==> " + y2);
        Log.d(tag, "indexX ==> " + indexX);
        Log.d(tag, "inDexY ==> " + indexy);

        //For (0,0) จุดแรกที่เลือก  จุดที่สองที่เป็นไปได้(0,1), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 0)) {
            // if1 จุดแรกที่เลือก (0,0)
            if ((x2 == 0) && (y2 == 1)) {
                //คลิกครั้งที่สามเหลือ (1,2), (2,2), (2,1), (2,0), (1,0)

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

            }   // if1.1 For (0,0) > (0,1)

            if ((x2 == 0) && (y2 == 2)) {
                //คลิกครั้งที่สามเหลือ (1,2), (2,2), (2,0), (1,0), (2,1)

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                }

            } // if1.2 For (0,0) > (0,2)

            if ((x2 == 1) && (y2 == 2)) {
                //คลิกครั้งที่สามเหลือ (0,2), (2,2), (2,0), (1,0), (2,1)

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                }

            } // if1.3 For (0,0) > (1,2)

            if ((x2 == 2) && (y2 == 2)) {
                //คลิกครั้งที่สามเหลือ (0,2), (2,2), (2,0), (1,0), (2,1)

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                }

            } // if1.4 For (0,0) > (2,2)

            if ((x2 == 2) && (y2 == 1)) {
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,0), (2,2), (1,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }

            } // if1.5 For (0,0) > (2,1)

            if ((x2 == 2) && (y2 == 0)) {
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,0), (2,2), (1,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                }

            } // if1.6 For (0,0) > (2,0)

            if ((x2 == 1) && (y2 == 0)) {
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,0), (2,2), (1,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                }

            } // if1.7 For (0,0) > (1,0)
        }   // if1 จุดแรกที่เลือก (0,0)

        // For (0,1)จุดแรกที่เลือก  (0,0), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)




        // For (0,2)จุดแรกที่เลือก  (0,0), (0,1), (1,2), (2,2), (2,1), (2,0), (1,0)

        // For (1,2)จุดแรกที่เลือก

        // For (2,2)จุดแรกที่เลือก

        // For (2,1)จุดแรกที่เลือก

        // For (2,0)จุดแรกที่เลือก

        // For (1,0)จุดแรกที่เลือก

    }   // calculate

    private void showAlert(final String strWin) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HardPlay3x3.this);
        builder.setTitle("The Winner");
        builder.setMessage("The Winner is " + strWin);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (strWin.equals("AI")) {

                    Intent intent = new Intent(HardPlay3x3.this, Hard2Play3x3.class);
                    startActivity(intent);

                } else {
                    //Restart Activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }

            }
        });
        builder.show();
    }

    private void calculateByComTime2(int x1, int y1, int indexX, int indexY) {

        //For (0,0) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,1), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 0)) {

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[2][0], "O");
            }

        }   // if1 For (0,0)


        //For (0,1) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 1)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[0][0], "O");
            }

        }  // if2  For (0,1)

        //For (0,2) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 2)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[1][2], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[0][1], "O");
            }

        } // if3  For (0,2)

        //For (1,2) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (0,2), (2,2), (2,1), (2,0), (1,0)

        if ((x1 == 1) && (y1 == 2)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[0][0], "O");
            }

        } // if4 For (1,2)

        //For (2,2) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (0,2), (1,2), (2,1), (2,0), (1,0)

        if ((x1 == 2) && (y1 == 2)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[1][2], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[2][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[2][1], "O");
            }

        } // if5  For (2,2)

        //For (2,1) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (0,2), (1,2), (2,2), (2,0), (1,0)

        if ((x1 == 2) && (y1 == 1)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[2][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[2][0], "O");
            }

        } // if6 For (2,1)

        //For (2,0) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (0,2), (1,2), (2,2), (2,1), (1,0)

        if ((x1 == 2) && (y1 == 0)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[2][2], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[0][0], "O");
            }

        } // if7 For (2,0)

        //For (1,0) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง (0,0), (0,1), (0,2), (1,2), (2,2), (2,1), (2,0)

        if ((x1 == 1) && (y1 == 0)) {

            if ((indexX == 0) && (indexY == 0)) {
                writeXOonButton(buttons[2][0], "O");
            }

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[0][0], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[2][0], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[0][0], "O");
            }

        } // if8 For (1,0)

    }   // calculate


    private void writeXOonButton(Button btnClick, String strXO) {

        btnClick.setText(strXO);
        btnClick.setClickable(false);

    }   // writeX

}   // Main Class
