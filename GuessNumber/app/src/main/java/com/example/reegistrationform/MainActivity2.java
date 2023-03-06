package com.example.reegistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    int pcGuessNumber;
    String message;
    private Button buttonConfirm;
    private EditText guess;
    private TextView result;
    private static int tries = 5;

    public void decreaseTries() {
        --tries;
    }


    public void generateNumber() {
        Random random = new Random();
        pcGuessNumber = 5;// random.nextInt(10) + 1;
    }

    public void Back (View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        guess = (EditText) findViewById(R.id.numberGuess);
        buttonConfirm = findViewById(R.id.button4);
        result = (TextView) findViewById(R.id.textView3);

        guess.addTextChangedListener(fieldTextWatcher);
        generateNumber();
    }

    private TextWatcher fieldTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            String guessedInput = guess.getText().toString().trim();
            buttonConfirm.setEnabled(!guessedInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (tries == 0) {
                buttonConfirm.setEnabled(false);
            }
        }
    };

    public void Guess(View view) {
        TextView newRound = (TextView) findViewById(R.id.textView2);
        int userGuessed = Integer.parseInt(guess.getText().toString());

        if (tries > 0) {
            if (pcGuessNumber < userGuessed) {
                message = "Спробуйте менше число!";
                decreaseTries();
            } else if (pcGuessNumber > userGuessed) {
                message = "Спробуйте більше число!";
                decreaseTries();
            } else if (pcGuessNumber == userGuessed) {
                if (tries == 5) {
                    message = "Неймовірно ви відгадали за 1 спробу!";
                }
                else if (tries == 4) {
                    message = "Чудово! Ви відгадали за 2 спроби!";
                }
                else if (tries == 3) {
                    message = "Добре! Ви відгадали за 3 спроби!";
                }
                else if (tries == 2) {
                    message = "Не погано! Ви відгадали за 4 спроби!";
                }
                else if (tries == 1) {
                    message = "Ви майже програли!";
                }
                newRound.setText("Комп'ютер загадав нове число. \n              Бажаємо удачі!");
                generateNumber();
                buttonConfirm.setEnabled(false);
            }
        }
        else {
            tries = 5;
            newRound.setText("Комп'ютер загадав нове число. \n              Бажаємо удачі!");
            generateNumber();
        }

        if(tries == 0) {
            buttonConfirm.setEnabled(false);
            result.setText("Ви витратили всі спроби!!!");
        }
        else {
            result.setText(message);
        }
    }
}