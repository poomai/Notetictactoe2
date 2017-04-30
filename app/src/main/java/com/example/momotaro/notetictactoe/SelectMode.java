package com.example.momotaro.notetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectMode extends AppCompatActivity implements View.OnClickListener {

    private Button easyButton, mediumButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);


        initialview();

        buttonController();

    }  // Main Method

    private void buttonController() {

        easyButton.setOnClickListener(SelectMode.this);
        mediumButton.setOnClickListener(SelectMode.this);
        hardButton.setOnClickListener(SelectMode.this);

    }

    private void initialview() {
        easyButton = (Button) findViewById(R.id.btnEasy);
        mediumButton = (Button) findViewById(R.id.btnMedium);
        hardButton = (Button) findViewById(R.id.btnHard);

    }

    @Override
    public void onClick(View v) {

        if (v == easyButton) {

        }

        if (v == mediumButton) {

        }


        if (v == hardButton) {
            Intent intent = new Intent(SelectMode.this, HardPlay3x3.class);
            startActivity(intent);
        }

    }
}  // Main Class
