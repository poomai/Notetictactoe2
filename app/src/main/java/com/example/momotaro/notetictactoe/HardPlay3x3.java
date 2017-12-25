package com.example.momotaro.notetictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.PublicKey;

public class HardPlay3x3 extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;

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

        backButton.setOnClickListener(HardPlay3x3.this);
    }   // buttonController

    private void initialView() {

        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                buttons[i][i1] = (Button) findViewById(ints[i][i1]);
            }   //for
        }   // for

        backButton = (Button) findViewById(R.id.backbutton1);

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

        if (view == backButton) {
            Intent intent = new Intent(HardPlay3x3.this, SelectMode.class);
            startActivity(intent);
        }
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
            // จุดแรกเลือก (0,0) AI เลือก (1,1)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (0,2)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) เหลือ (1,2) , (2,2) , (2,1)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 1.1 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,2)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (1,2) , (2,2) , (2,0)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.2 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(0,1)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,2) , (1,0) , (2,2)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("ไม่มี");


                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.3 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,1)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,2) , (1,0) , (1,2)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.4 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(0,1)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,2) , (1,0) , (2,2)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.5 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(1,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,2) , (2,1) , (2,2)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.6 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(1,0)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (1,2) , (2,1) , (2,2)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 1.7 จุดแรกเลือก(0,0) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(2,0)
        }   ///////////////////////////////////////////// if1 For จุดแรก (0,0) AI เลือก (1,1)

        //For (0,1)
        //จุดที่สอง (0,0), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && ( y1 == 1)) {
            // จุดแรกเลือก (0,1) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (0,2)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) เหลือ (1,2) , (2,2) , (2,1)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,2) เสมอ ถ้าไม่เลือก (2,1)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 2.1 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(0,2)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (1,2) , (2,2) , (2,0)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) เสมอ ถ้าไม่เลือก (2,1)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.2 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(0,0)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,2)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) เหลือ (1,0) , (2,1) , (2,2)
                    if ((indexX == 2) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (2,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.3 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,2)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (0,1)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.4 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(1,0)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (1,2) , (1,0)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) AI ชนะ เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.5 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(0,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (0,2) , (2,1)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.6 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(1,0)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (0,2) , (1,2) , (2,1)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 2.7 จุดแรกเลือก(0,1) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(0,0)
        }   ///////////////////////////////////////////// if2 For จุดแรก (0,1) AI เลือก (1,1)

        //For (0,2)
        //จุดที่สอง (0,0), (0,1), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && ( y1 == 2)) {
            // จุดแรกเลือก (0,2) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (1,2) , (2,2) , (2,0)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,2) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 3.1 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (1,0) , (2,0) , (2,1)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) เสมอ ถ้าไม่เลือก (2,1)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.2 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,0)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (1,0) , (2,1) , (2,0)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.3 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,2)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (1,2)
                if ((x3 == 1) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (1,0) เหลือ (0,0) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.4 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(1,0)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (0,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (0,1) , (2,0)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) AI ชนะ เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.5 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(1,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (1,0)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,0) , (1,0) , (1,2)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.6 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(0,1)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,0) , (1,2) , (2,0)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 3.7 จุดแรกเลือก(0,2) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(0,1)
        }   ///////////////////////////////////////////// if3 For จุดแรก (0,2) AI เลือก (1,1)

        //For (1,2)
        //จุดที่สอง (0,0), (0,1), (0,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 1) && ( y1 == 2)) {
            // จุดแรกเลือก (1,2) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,2) , (1,0) , (2,2)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 4.1 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (1,0) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,0) เสมอ ถ้าไม่เลือก (2,1)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.2 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,2)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (1,0) , (2,1) , (2,0)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,1) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.3 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(2,2)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (0,2)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) เหลือ (0,0) , (0,1) , (1,0)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,1) เสมอ ถ้าไม่เลือก (0,0)  AI ชนะ
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.4 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(0,2)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (0,1) , (0,2) , (1,0)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.5 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,2)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (1,0) , (0,2) , (2,2)
                    if ((indexX == 2) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.6 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(2,1)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,1) , (2,0) , (2,1)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 4.7 จุดแรกเลือก(1,2) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(0,0)
        }   ///////////////////////////////////////////// if4 For จุดแรก (1,2) AI เลือก (1,1)


        //For (2,2)
        //จุดที่สอง (0,0), (0,1), (0,2), (1,2), (2,1), (2,0), (1,0)
        if ((x1 == 2) && ( y1 == 2)) {
            // จุดแรกเลือก (2,2) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,2) , (1,0) , (1,2)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 5.1 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,1)  AI ชนะ
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.2 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(1,0)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (1,2)
                if ((x3 == 1) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (1,0) เหลือ (0,0) , (2,1) , (2,0)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,1) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.3 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(1,2)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,2)
                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) เหลือ (0,0) , (0,1) , (1,0)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,1) เสมอ ถ้าไม่เลือก (0,0)  AI ชนะ
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.4 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,2)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (0,0) , (0,1) , (1,0)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.5 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,0) , (0,2) , (1,2)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.6 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(2,1)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,0) , (1,2) , (2,0)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 5.7 จุดแรกเลือก(2,2) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(2,1)
        }   ///////////////////////////////////////////// if5 For จุดแรก (2,2) AI เลือก (1,1)

        //For (2,1)
        //จุดที่สอง (0,0), (0,1), (0,2), (1,2), (2,1), (2,0), (1,0)
        if ((x1 == 2) && ( y1 == 1)) {
            // จุดแรกเลือก (2,1) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,1) , (2,0) , (2,2)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 6.1 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(1,0)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (1,0) , (0,2) , (1,2)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.2 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,0)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (0,1) , (2,0)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.3 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(1,0)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (0,1) , (0,2) , (1,0)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.4 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(2,2)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (0,0) , (0,1) , (1,0)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.5 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (0,1) , (0,2) , (1,2)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.6 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(2,1)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (0,0) , (0,1) , (1,2)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 6.7 จุดแรกเลือก(2,1) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(2,0)
        }   ///////////////////////////////////////////// if6 For จุดแรก (2,1) AI เลือก (1,1)

        //For (2,0)
        //จุดที่สอง (0,0), (0,1), (0,2), (1,2), (2,1), (2,2), (1,0)
        if ((x1 == 2) && ( y1 == 0)) {
            // จุดแรกเลือก (2,0) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,2) , (2,1) , (2,2)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 7.1 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(1,0)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (1,0)
                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) เหลือ (0,0) , (0,2) , (2,1)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.2 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(1,0)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (1,0)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,0) , (1,0) , (1,2)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.3 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(0,1)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,2) , (2,2) , (1,0)
                    if ((indexX == 2) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.4 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(2,1)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,0) , (0,2) , (1,2)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (1,0) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.5 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(2,1)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (2,2)
                if ((x3 == 0) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (0,0) เหลือ (0,1) , (0,2) , (1,2)
                    if ((indexX == 1) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.6 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,2)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (1,0) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (0,1) , (1,2)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 7.7 จุดแรกเลือก(2,0) AI เลือก(1,1) จุุด2 เลือก(1,0) AI เลือก(0,0)
        }   ///////////////////////////////////////////// if7 For จุดแรก (2,0) AI เลือก (1,1)


        //For (1,0)
        //จุดที่สอง (0,0), (0,1), (0,2), (1,2), (2,1), (2,2), (2,0)
        if ((x1 == 1) && ( y1 == 0)) {
            // จุดแรกเลือก (1,0) AI เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // จุด2 เลือก (0,0) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (1,2) , (2,1) , (2,2)
                    if ((indexX == 2) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (2,1) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert(" ไม่มี ");
                    } else {
                        writeXOonButton(buttons[2][1], "O");
                        showAlert("AI");
                    }
                }
            }   /////////////////////// if 8.1 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,0) AI เลือก(2,0)

            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (1,2) , (2,1)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.2 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,0)

            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,0) , (1,2) , (2,0)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.3 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(0,1)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,1) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.4 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,0)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (2,2) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,0) , (2,0) , (1,2)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.5 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(2,1)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,1) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (0,1) , (0,0) , (1,2)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.6 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,0)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (0,1) , (1,2)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.7 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(0,0)
        }   ///////////////////////////////////////////// if8 For จุดแรก (1,0) AI เลือก (1,1)

        //For (1,1)
        //จุดที่สองเหลือ (0,1), (0,2), (1,2), (2,1), (2,2), (2,0) , (1,0)
        if ((x1 == 1) && ( y1 == 1)) {
            // จุดแรกเลือก (1,1) AI เลือก (0,0)
            //-----------------------------------------------------------------------------
            if ((x2 == 0) && (y2 == 1)) {
                // จุด2 เลือก (0,1) AI เลือก (2,1)

                //----เหลือ (0,2) , (1,2),  (2,2), (2,0) , (1,0)

                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) AI เลือก (2,0)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("AI");
                    }
                } /////////////////////////////// if 9.1.1 จุดที่เลือก (0,2) AI เลือก (2,0)

                if ((x3 == 1) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (1,2) AI เลือก (1,0)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }//////////////////////////////// if 9.1.2 จุดที่3เลือก (1,2) AI เลือก (1,0)

                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) AI เลือก (1,0)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        writeXOonButton(buttons[1][2], "X");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }//////////////////////////////// if 9.1.3 จุดที่3เลือก (2,2) AI เลือก (1,0)

                if ((x3 == 2) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (2,0) AI เลือก (0,2)
                    if ((indexX == 1) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        //writeXOonButton(buttons[1][2], "X");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[1][0], "O");
                        showAlert("ไม่มี");
                    }
                }//////////////////////////////// if 9.1.4 จุดที่3เลือก (2,0) AI เลือก (1,2)

                if ((x3 == 1) && (y3 == 0)) {
                    // จุด3 ต้องเลือก (1,2) AI เลือก (1,2)
                    if ((indexX == 2) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (2,0) เสมอ ถ้าไม่เลือก (2,2)  AI ชนะ
                        writeXOonButton(buttons[0][2], "O");
                        //writeXOonButton(buttons[1][2], "X");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][2], "O");
                        showAlert("ไม่มี");
                    }
                }//////////////////////////////// if 9.1.5 จุดที่3เลือก (1,0) AI เลือก (1,2)
            }   /////////////////////// if 9.1 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(2,1)
                //--------------------------- จุดที่3 เหลือ (0,2) (1,2) (2,2) (2,0) (2,1)


            if ((x2 == 0) && (y2 == 2)) {
                // จุด2 เลือก (0,1) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (1,2) , (2,1)
                    if ((indexX == 0) && (indexy == 2)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (1,2)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][2], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.2 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,1) AI เลือก(0,0)

            if ((x2 == 1) && (y2 == 2)) {
                // จุด2 เลือก (0,2) AI เลือก (0,1)
                if ((x3 == 2) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (2,1) เหลือ (0,0) , (1,2) , (2,0)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.3 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(0,2) AI เลือก(0,1)

            if ((x2 == 2) && (y2 == 2)) {
                // จุด2 เลือก (1,2) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,1) , (2,0) , (2,1)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,2) เสมอ ถ้าไม่เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.4 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(1,2) AI เลือก(0,0)

            if ((x2 == 2) && (y2 == 1)) {
                // จุด2 เลือก (2,2) AI เลือก (2,1)
                if ((x3 == 0) && (y3 == 1)) {
                    // จุด3 ต้องเลือก (0,1) เหลือ (0,0) , (2,0) , (1,2)
                    if ((indexX == 2) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (1,0) AI ชนะ เลือก (0,1)  AI ชนะ
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[2][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.5 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,2) AI เลือก(2,1)

            if ((x2 == 2) && (y2 == 0)) {
                // จุด2 เลือก (2,1) AI เลือก (2,0)
                if ((x3 == 0) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (0,2) เหลือ (0,1) , (0,0) , (1,2)
                    if ((indexX == 0) && (indexy == 0)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (1,0)  AI ชนะ
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][0], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 8.6 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,1) AI เลือก(2,0)

            if ((x2 == 1) && (y2 == 0)) {
                // จุด2 เลือก (2,0) AI เลือก (0,0)
                if ((x3 == 2) && (y3 == 2)) {
                    // จุด3 ต้องเลือก (2,2) เหลือ (0,2) , (0,1) , (1,2)
                    if ((indexX == 0) && (indexy == 1)) {
                        // จุด4 ถ้าเลือก (0,0) เสมอ ถ้าไม่เลือก (2,0)  AI ชนะ
                        writeXOonButton(buttons[1][2], "O");
                        showAlert("ไม่มี");
                    } else {
                        writeXOonButton(buttons[0][1], "O");
                        showAlert("AI");
                    }
                }
            } ///////////////////////// if 9.7 จุดแรกเลือก(1,0) AI เลือก(1,1) จุุด2 เลือก(2,0) AI เลือก(0,0)
        }   ///////////////////////////////////////////// if9 For จุดแรก (1,1) AI เลือก (0,0)

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
                    writeXOonButton(buttons[0][2], "O");
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
        ////////////////////////////////////////////////////////////////////////////////   if1

        // For (0,1)จุดแรกที่เลือก  จุดที่สองที่เป็นไปได้ (0,0), (0,2), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 1)) {
            // if2 จุดแรกที่เลือก (0,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  Ai เลือก 0,2
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
            }   // if2.1 For (0,1) > (0,0)

            if ((x2 == 0) && (y2 == 2)) {
                //คลิกครั้งที่สามเหลือ (1,2), (2,2), (2,0), (1,0), (2,1)
                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            } // if2.2 For (0,1) > (0,2)

            if ((x2 == 1) && (y2 == 2)) {     // 1,2 ไป 0,2
                //คลิกครั้งที่สามเหลือ (0,0), (1,0), (2,0), (2,1), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
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
                    writeXOonButton(buttons[0][0], "O");
                }

            } // if2.3 For 1,2 ไป 0,2

            if ((x2 == 2) && (y2 == 2)) {   // 2,2 ไป 1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,2), (1,2), (2,1), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
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

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }

            } // if2.4 For (2,2) > (1,0)

            if ((x2 == 2) && (y2 == 1)) {     //2,1 >  0,0
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (1,0), (2,0)

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }

            } // if2.5 For (2,1) > (0,0)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,2), (1,2), (2,2), (2,1)

                if ((indexX == 0) && (indexy == 0)) {
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
                    writeXOonButton(buttons[2][2], "O");
                }

            } // if2.6 For (2,0) > (1,0)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 0,0
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (2,1), (2,0)

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }

            } // if2.7 For (1,1) > (0,0)
        }   // if2 จุดแรกที่เลือก (0,1)
        /////////////////////////////////////////////////////////////////////////////   if2


        // For (0,2)จุดแรกที่เลือก  (0,0), (0,1), (1,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 0) && (y1 == 2)) {
            // if3 จุดแรกที่เลือก (0,2) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (1,0), (1,2), (2,2), (2,1), (2,0)
                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }
            }   // if3.1 For จุุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (1,2), (2,2), (2,1), (2,0), (1,0)
                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            } // if3.2 For จุุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(0,0)

            if ((x2 == 1) && (y2 == 2)) {     // 1,2 ไป 2,2
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 2,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (2,1), (2,0), (1,0)

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                }

            } // if3.3 For จุุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(1,2) แล้วAi เลือก(2,2)

            if ((x2 == 2) && (y2 == 2)) {   // 2,2 ไป 1,2
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 1,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (2,1), (2,0), (1,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                }

            } // if3.4 For จุุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(2,2) แล้วAi เลือก(1,2)

            if ((x2 == 2) && (y2 == 1)) {     //2,1 >  1,0
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (1,2), (2,2), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
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
                    writeXOonButton(buttons[2][2], "O");
                }

            } // if3.5 For จุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(2,1) แล้วAi เลือก(1,0)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  0,1
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 0,1
                // คลิกครั้งที่สามเหลือ (0,0), (1,0), (1,2), (2,2), (2,1)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                }

            } // if3.6 For จุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(2,0) แล้วAi เลือก(0,1)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 0,1
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,0), (1,2), (2,2), (2,1), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

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

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                }

            } // if3.7 For จุดแรกที่เลือก(0,2)>AI เลือก(1,1) ต่อไปเลือก(1,0) แล้วAi เลือก(0,1)
        }   // if3 จุดแรกที่เลือก (0,2) Ai เลือก (1,1)
        ///////////////////////////////////////////////////////////////////////////   if3


        // For (1,2)จุดแรกที่เลือก  (0,0), (0,1), (0,2), (2,2), (2,1), (2,0), (1,0)
        if ((x1 == 1) && (y1 == 2)) {
            // if4 จุดแรกที่เลือก (1,2) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,2), (2,2), (2,1), (2,0), (1,0)
                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
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

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }
            }   // if4.1 For จุุดแรกที่เลือก(1,2)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 0,2
                //คลิกครั้งที่สามเหลือ (0,0), (1,0), (2,0), (2,1), (2,2)
                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
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
                    writeXOonButton(buttons[0][0], "O");
                }
            } // if4.2 For จุุดแรกที่เลือก(1,2)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(0,2)

            if ((x2 == 0) && (y2 == 2)) {     // 0,2 ไป 2,2
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 2,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (1,0), (2,0), (2,1)

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                }
            } // if4.3 For จุุดแรกที่เลือก(1,2)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(2,2)

            if ((x2 == 2) && (y2 == 2)) {   // 2,2 ไป 0,2
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 0,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (2,1), (2,0), (1,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                }
            } // if4.4 For จุุดแรกที่เลือก(1,2)>AI เลือก(1,1) ต่อไปเลือก(2,2) แล้วAi เลือก(0,2)

            if ((x2 == 2) && (y2 == 1)) {     //2,1 >  2,2
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 2,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (2,0)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            } // if4.5 For จุดแรกที่เลือก(1,2)> AI เลือก(1,1) ต่อไปเลือก(2,1) แล้วAi เลือก(2,2)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  2,1
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 2,1
                // คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                }
            } // if4.6 For จุดแรกที่เลือก(1,2)>AI เลือก(1,1) ต่อไปเลือก(2,0) แล้วAi เลือก(2,1)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 0,0
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,2), (2,0), (2,1), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }

            } // if4.7 For จุดแรกที่เลือก(1,2)>AI เลือก(1,1) ต่อไปเลือก(1,0) แล้วAi เลือก(0,0)
        }   // if4 จุดแรกที่เลือก (1,2) Ai เลือก (1,1)
        //////////////////////////////////////////////////////////////////////////////// if4

        // For (2,2)จุดแรกที่เลือก  เหลือ(0,0), (0,1), (0,2), (1,2), (2,1), (2,0), (1,0)
        if ((x1 == 2) && (y1 == 2)) {  // (2,2) > (1,1)
            // if5 จุดแรกที่เลือก (2,2) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,1), (2,0), (1,0)
                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            }   // if5.1 For จุุดแรกที่เลือก(2,2)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(0,1)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,2), (1,2), (2,0), (2,1)
                if ((indexX == 0) && (indexy == 0)) {
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

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }
            } // if5.2 For จุุดแรกที่เลือก(2,2)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(1,0)

            if ((x2 == 0) && (y2 == 2)) {     // 0,2 ไป 1,2
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 1,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (1,0), (2,0), (2,1)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                }
            } // if5.3 For จุุดแรกที่เลือก(2,2)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(1,2)

            if ((x2 == 1) && (y2 == 2)) {   // 1,2 ไป 0,2
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 0,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (1,0), (2,0), (2,1)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                }
            } // if5.4 For จุุดแรกที่เลือก(2,2)>AI เลือก(1,1) ต่อไปเลือก(1,2) แล้วAi เลือก(0,2)

            if ((x2 == 2) && (y2 == 1)) {     //2,1 >  2,0
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 2,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            } // if5.5 For จุดแรกที่เลือก(2,2)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(1,2)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  2,1
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 2,1
                // คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                }
            } // if5.6 For จุดแรกที่เลือก(2,2)>AI เลือก(1,1) ต่อไปเลือก(2,0) แล้วAi เลือก(2,1)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 2,1
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 2,1
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,2), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                }

            } // if5.7 For จุดแรกที่เลือก(2,2)>AI เลือก(1,1) ต่อไปเลือก(1,0) แล้วAi เลือก(2,1)
        }   // if5 จุดแรกที่เลือก (2,2) Ai เลือก (1,1)
        ///////////////////////////////////////////////////////////////////////////// if5


        // For (2,1)จุดแรกที่เลือก  (0,0), (0,1), (0,2), (2,2), (1,2), (2,0), (1,0)
        if ((x1 == 2) && (y1 == 1)) {  // (2,1) > (1,1)
            // if6 จุดแรกที่เลือก (2,1) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (1,2), (2,2), (2,0)
                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }
            }   // if6.1 For จุุดแรกที่เลือก(2,1)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(1,0)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (2,0), (1,0)
                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            } // if6.2 For จุุดแรกที่เลือก(2,1)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(0,0)

            if ((x2 == 0) && (y2 == 2)) {     // 0,2 ไป 1,0
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (1,2), (2,2), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                }
            } // if6.3 For จุุดแรกที่เลือก(2,1)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(1,0)

            if ((x2 == 1) && (y2 == 2)) {   // 1,2 ไป 2,2
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 2,2
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (2,0)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            } // if6.4 For จุุดแรกที่เลือก(2,1)>AI เลือก(1,1) ต่อไปเลือก(1,2) แล้วAi เลือก(2,2)

            if ((x2 == 2) && (y2 == 2)) {     //2,2 >  2,0
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 2,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            } // if6.5 For จุดแรกที่เลือก(2,2)> AI เลือก(1,1) ต่อไปเลือก(2,2) แล้วAi เลือก(2,0)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  2,2
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 2,2
                // คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                }
            } // if6.6 For จุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(2,0) แล้วAi เลือก(2,2)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 2,0
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 2,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,2), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                }

            } // if6.7 For จุดแรกที่เลือก(2,1)>AI เลือก(1,1) ต่อไปเลือก(1,0) แล้วAi เลือก(2,0)
        }   // if6 จุดแรกที่เลือก (2,1) Ai เลือก (1,1)
        ///////////////////////////////////////////////////////////////////////////// if6


        // For (2,0)จุดแรกที่เลือก  (0,0), (0,1), (0,2), (2,2), (2,1), (1,0), (1,2)

        if ((x1 == 2) && (y1 == 0)) {  // (2,0) > (1,1)
            // if7 จุดแรกที่เลือก (2,0) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (1,2), (2,2), (2,1)
                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                }
            }   // if7.1 For จุุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(1,0)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,0), (0,2), (1,2), (2,2), (2,1)
                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                }
            } // if7.2 For จุุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(1,0)

            if ((x2 == 0) && (y2 == 2)) {     // 0,2 ไป 0,1
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,0), (1,0), (1,2), (2,1), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                }
            } // if7.3 For จุุดแรกที่เลือก(2,0)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(0,1)

            if ((x2 == 1) && (y2 == 2)) {   // 1,2 ไป 2,1
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 2,1
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                }
            } // if7.4 For จุุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(1,2) แล้วAi เลือก(2,1)

            if ((x2 == 2) && (y2 == 2)) {     //2,2 >  2,1
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 2,1
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                }
            } // if7.5 For จุดแรกที่เลือก(2,0)> AI เลือก(1,1) ต่อไปเลือก(2,2) แล้วAi เลือก(2,1)

            if ((x2 == 2) && (y2 == 1)) {      //2,1  >  2,2
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 2,2
                // คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,0), (1,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[1][0], "O");
                }
            } // if7.6 For จุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(2,1) แล้วAi เลือก(2,2)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 0,0
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (1,2), (2,2), (2,1)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                }

            } // if7.7 For จุดแรกที่เลือก(2,0)>AI เลือก(1,1) ต่อไปเลือก(1,0) แล้วAi เลือก(0,0)
        }   // if7 จุดแรกที่เลือก (2,0) Ai เลือก (1,1)
        ///////////////////////////////////////////////////////////////////////////// if7


        // For (1,0)จุดแรกที่เลือก  (0,0), (0,1), (0,2), (1,2), (2,1), (2,2), (2,0)

        if ((x1 == 1) && (y1 == 0)) {  // (1,0) > (1,1)
            // if8 จุดแรกที่เลือก (1,0) Ai เลือก (1,1)
            if ((x2 == 0) && (y2 == 0)) {
                // ต่อไปเลือก 0,0  แล้ว Ai เลือก 2,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (1,2), (2,2), (2,1)
                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                }
            }   // if8.1 For จุุดแรกที่เลือก(1,0)>AI เลือก(1,1) ต่อไปเลือก(0,0) แล้วAi เลือก(2,0)

            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (2,1), (2,0)
                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            } // if8.2 For จุุดแรกที่เลือก(1,0)>AI เลือก(1,1) ต่อไปเลือก(0,1) แล้วAi เลือก(0,0)

            if ((x2 == 0) && (y2 == 2)) {     // 0,2 ไป 0,1
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,0), (1,2), (2,2), (2,1), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[2][1], "O");
                    showAlert("AI");
                }

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

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                }
            } // if8.3 For จุุดแรกที่เลือก(1,0)> AI เลือก(1,1) ต่อไปเลือก(0,2) แล้วAi เลือก(0,1)

            if ((x2 == 1) && (y2 == 2)) {   // 1,2 ไป 0,0
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,0), (2,1), (2,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }
            } // if8.4 For จุุดแรกที่เลือก(1,0)>AI เลือก(1,1) ต่อไปเลือก(1,2) แล้วAi เลือก(0,0)

            if ((x2 == 2) && (y2 == 2)) {     //2,2 >  2,1
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 2,1
                //คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,2), (2,0)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                }
            } // if8.5 For จุดแรกที่เลือก(1,0)> AI เลือก(1,1) ต่อไปเลือก(2,2) แล้วAi เลือก(2,1)

            if ((x2 == 2) && (y2 == 1)) {      //2,1  >  2,0
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 2,0
                // คลิกครั้งที่สามเหลือ (0,0), (0,1), (0,2), (1,2), (2,2)

                if ((indexX == 0) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                }
            } // if8.6 For จุดแรกที่เลือก(1,0)>AI เลือก(1,1) ต่อไปเลือก(2,1) แล้วAi เลือก(2,0)

            if ((x2 == 2) && (y2 == 0)) {    // 2,0 > ไป 0,0
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 0,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (1,2), (2,2), (2,1)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[2][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[2][1], "O");
                }

            } // if8.7 For จุดแรกที่เลือก(1,0)>AI เลือก(1,1) ต่อไปเลือก(2,0) แล้วAi เลือก(0,0)
        }   // if8 จุดแรกที่เลือก (1,0) Ai เลือก (1,1)
        ///////////////////////////////////////////////////////////////////////////// if8


        // For (1,1)จุดแรกที่เลือก  (0,0), (0,1), (1,2), (2,2), (2,1), (2,0), (1,0)

        if ((x1 == 1) && (y1 == 1)) {  // (1,1) > (0,0)
            // if9 จุดแรกที่เลือก (1,1) Ai เลือก (0,0)
            if ((x2 == 0) && (y2 == 1)) {
                // ต่อไปเลือก 0,1  แล้ว Ai เลือก 2,1
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (1,0), (2,0)
                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                    //showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[1][0], "O");
                    //showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][0], "O");
                    //showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    //showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            }   // if9.1 For จุุดแรกที่เลือก(1,1)>AI เลือก(0,0) ต่อไปเลือก(0,1) แล้วAi เลือก(2,1)


            if ((x2 == 0) && (y2 == 2)) {
                // ต่อไปเลือก 0,2  แล้ว Ai เลือก 2,0
                //คลิกครั้งที่สามเหลือ (0,1), (1,2), (2,2), (2,1), (1,0)
                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[1][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[1][2], "O");
                }
            } // if9.2 For จุุดแรกที่เลือก(1,1)>AI เลือก(0,0) ต่อไปเลือก(0,2) แล้วAi เลือก(2,0)

            if ((x2 == 1) && (y2 == 2)) {     // 1,2 ไป 1,0
                // ต่อไปเลือก 1,2  แล้ว Ai เลือก 1,0
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,0), (2,1), (2,2)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][0], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
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
                    writeXOonButton(buttons[0][2], "O");
                }
            } // if9.3 For จุุดแรกที่เลือก(1,1)> AI เลือก(0,0) ต่อไปเลือก(1,2) แล้วAi เลือก(1,0)

            if ((x2 == 2) && (y2 == 2)) {   // 2,2 ไป 0,2
                // ต่อไปเลือก 2,2  แล้ว Ai เลือก 0,2
                //คลิกครั้งที่สามเหลือ (0,1), (1,2), (1,0), (2,0), (2,1)

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][1], "O");
                }
            } // if9.4 For จุุดแรกที่เลือก(1,1)>AI เลือก(0,0) ต่อไปเลือก(2,2) แล้วAi เลือก(0,2)

            if ((x2 == 2) && (y2 == 1)) {     //2,1 >  0,1
                // ต่อไปเลือก 2,1  แล้ว Ai เลือก 0,1
                //คลิกครั้งที่สามเหลือ (0,2), (1,2), (2,2), (1,0), (2,0)

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                }
            } // if9.5 For จุดแรกที่เลือก(1,1)> AI เลือก(0,0) ต่อไปเลือก(2,1) แล้วAi เลือก(0,1)

            if ((x2 == 2) && (y2 == 0)) {      //2,0  >  0,2
                // ต่อไปเลือก 2,0  แล้ว Ai เลือก 0,2
                // คลิกครั้งที่สามเหลือ (0,1), (1,2), (2,2), (2,1), (1,0)

                if ((indexX == 1) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 1) && (indexy == 0)) {
                    writeXOonButton(buttons[0][1], "O");
                    showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][1], "O");
                }
            } // if9.6 For จุดแรกที่เลือก(1,1)>AI เลือก(0,0) ต่อไปเลือก(2,0) แล้วAi เลือก(0,2)

            if ((x2 == 1) && (y2 == 0)) {    // 1,0 > ไป 1,2
                // ต่อไปเลือก 1,0  แล้ว Ai เลือก 1,2
                //คลิกครั้งที่สามเหลือ (0,1), (0,2), (2,2), (2,1), (2,0)

                if ((indexX == 0) && (indexy == 1)) {
                    writeXOonButton(buttons[2][1], "O");
                    //showAlert("AI");
                }

                if ((indexX == 0) && (indexy == 2)) {
                    writeXOonButton(buttons[2][0], "O");
                    //showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 0)) {
                    writeXOonButton(buttons[0][2], "O");
                    //showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 1)) {
                    writeXOonButton(buttons[0][1], "O");
                    //showAlert("AI");
                }

                if ((indexX == 2) && (indexy == 2)) {
                    writeXOonButton(buttons[0][2], "O");
                }

            } // if9.7 For จุดแรกที่เลือก(1,1)>AI เลือก(0,0) ต่อไปเลือก(1,0) แล้วAi เลือก(1,2)
        }   // if9 จุดแรกที่เลือก (1,1) Ai เลือก (0,0)
        ///////////////////////////////////////////////////////////////////////////// if9

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

        //For (1,1) > (0,0) จุดที่เป็นไปได้ที่จะคลิกครั้งที่สอง  (0,1), (0,2), (1,2), (2,2), (2,1), (2,0) (1,0),
        if ((x1 == 1) && (y1 == 1)) {

            if ((indexX == 0) && (indexY == 1)) {
                writeXOonButton(buttons[2][1], "O");
            }

            if ((indexX == 0) && (indexY == 2)) {
                writeXOonButton(buttons[2][0], "O");
            }

            if ((indexX == 1) && (indexY == 2)) {
                writeXOonButton(buttons[1][0], "O");
            }

            if ((indexX == 2) && (indexY == 2)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 2) && (indexY == 1)) {
                writeXOonButton(buttons[0][1], "O");
            }

            if ((indexX == 2) && (indexY == 0)) {
                writeXOonButton(buttons[0][2], "O");
            }

            if ((indexX == 1) && (indexY == 0)) {
                writeXOonButton(buttons[1][2], "O");
            }

        } // if9 For (1,1) > (0,0) คลิกครั้งที่สองผู้เล่นเลือกตรงกลาง (1,1) AI เลือก (0,0)

    }   // calculate


    private void writeXOonButton(Button btnClick, String strXO) {

        btnClick.setText(strXO);
        btnClick.setClickable(false);

    }   // writeX




}   // Main Class
