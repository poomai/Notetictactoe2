package com.example.momotaro.notetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button humanButton, comButton;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initialview();

        buttonController();


    } // Main Method

    private void buttonController() {
        humanButton.setOnClickListener(FirstActivity.this);
        comButton.setOnClickListener(FirstActivity.this);
    }

    private void initialview() {
        humanButton = (Button) findViewById(R.id.btnHuman);
        comButton = (Button) findViewById(R.id.btnCom);
    }

    @Override
    public void onClick(View v) {

        //For Human
        if (v == humanButton) {
            Intent intent = new Intent(FirstActivity.this, MainActivity.class);
            startActivity(intent);

        }

        //For com


        if (v == comButton) {
            Intent intent = new Intent(FirstActivity.this, SelectMode.class);
            startActivity(intent);

        }
    }
} //Main Class
