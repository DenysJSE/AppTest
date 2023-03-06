package com.example.applicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int pcGuessedNumber;
    private int tries = 5;

    TextView triesView;
    EditText inputNumber;
    Button guessNumberBtn;

    public void randNumber() {
        Random rand = new Random();
        pcGuessedNumber = 5;// rand.nextInt(10) + 1;
    }

    public void decreaseTries() {
        --tries;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triesView = findViewById(R.id.triesId);
        inputNumber = findViewById(R.id.inputNumberId);
        guessNumberBtn = findViewById(R.id.guessNumberBtn);

        randNumber();
        triesView.setText("Tries: " + tries);

        guessNumberBtn.setEnabled(false);

        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().trim().length() != 0) {
                    guessNumberBtn.setEnabled(true);
                }
                else {
                    guessNumberBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void guessNumber(View view) {
        decreaseTries();
        triesView.setText("Tries: " + tries);
    }
}