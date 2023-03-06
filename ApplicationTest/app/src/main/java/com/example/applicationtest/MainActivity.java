package com.example.applicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int pcGuessedNumber;
    private int userEnterNumber;
    private String valueOfEdittext;
    private int tries = 5;

    TextView triesView;
    EditText inputNumber;
    Button guessNumberBtn;
    Button newGameBtn;
    TextView resultOutput;

    public void randNumber() {
        Random rand = new Random();
        pcGuessedNumber = 5;// rand.nextInt(10) + 1;
    }

    public void decreaseTries() {
        --tries;
    }

    public void triesIsZero() {
        if (tries <= 0) {
            guessNumberBtn.setEnabled(false);
            newGameBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triesView = findViewById(R.id.triesId);
        inputNumber = findViewById(R.id.inputNumberId);
        guessNumberBtn = findViewById(R.id.guessNumberBtn);
        newGameBtn = findViewById(R.id.newGameBtn);
        resultOutput = findViewById(R.id.resultOutput);

        randNumber();
        triesView.setText("Tries: " + tries);

        guessNumberBtn.setEnabled(false);
        newGameBtn.setVisibility(View.GONE);

        inputNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

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
                triesIsZero();
            }
        });

    }

    public void guessNumber(View view) {
        decreaseTries();
        triesView.setText("Tries: " + tries);

        valueOfEdittext = inputNumber.getText().toString();
        userEnterNumber = Integer.parseInt(valueOfEdittext);

        if (tries != 0) {
            if (userEnterNumber > pcGuessedNumber) {
                resultOutput.setText("Try lower");
            } else if (userEnterNumber < pcGuessedNumber) {
                resultOutput.setText("Try higher");
            } else if (userEnterNumber == pcGuessedNumber) {
                resultOutput.setText("You guessed!");
                tries = 0;
            }
        }

        triesIsZero();
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}