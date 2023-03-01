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
    private static int tries = 5;

    public void decreaseTries() {
        --tries;
    }


    public void generateNumber() {
        Random random = new Random();
        pcGuessNumber = random.nextInt(10) + 1;
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

        }
    };

    public void Guess(View view) {
        TextView result = (TextView) findViewById(R.id.textView3);
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
                message = "Ви відгадали. Вітаю!";
                newRound.setText("Комп'ютер загадав нове число. \n              Бажаємо удачі!");
                generateNumber();
            }
        }
        else {
            message = "Ви витратили всі спроби!!!";
            tries = 5;
            newRound.setText("Комп'ютер загадав нове число. \n              Бажаємо удачі!");
            generateNumber();
        }

        result.setText(message);
    }
}