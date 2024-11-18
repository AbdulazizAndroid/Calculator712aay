package com.example.a712aay;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';
    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonPercent, buttonClear, buttonOFF, buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.#########");

        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        buttonAdd = findViewById(R.id.addd);
        buttonSub = findViewById(R.id.subtract);
        buttonDivide = findViewById(R.id.devicion);
        buttonDot = findViewById(R.id.btnPoint);
        buttonMultiply = findViewById(R.id.multiplyy);
        buttonClear = findViewById(R.id.clear);
        buttonOFF = findViewById(R.id.offf);
        buttonEqual = findViewById(R.id.equal);
        buttonPercent = findViewById(R.id.percentt);

        setNumberListeners();
        setOperatorListeners();
    }

    private void setNumberListeners() {
        View.OnClickListener numberClickListener = view -> {
            MaterialButton button = (MaterialButton) view;
            inputDisplay.setText(inputDisplay.getText().toString() + button.getText().toString());
        };

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        buttonDot.setOnClickListener(numberClickListener);
    }

    private void setOperatorListeners() {
        buttonAdd.setOnClickListener(view -> handleOperator(ADDITION));
        buttonSub.setOnClickListener(view -> handleOperator(SUBTRACTION));
        buttonMultiply.setOnClickListener(view -> handleOperator(MULTIPLICATION));
        buttonDivide.setOnClickListener(view -> handleOperator(DIVISION));
        buttonPercent.setOnClickListener(view -> handleOperator(PERCENT));

        buttonClear.setOnClickListener(view -> {
            firstValue = Double.NaN;
            secondValue = Double.NaN;
            inputDisplay.setText("");
            outputDisplay.setText("");
        });

        buttonOFF.setOnClickListener(view -> finish());

        buttonEqual.setOnClickListener(view -> {
            performCalculation();
            outputDisplay.setText(decimalFormat.format(firstValue));
            firstValue = Double.NaN;
        });
    }

    private void handleOperator(char operator) {
        performCalculation();
        currentSymbol = operator;
        outputDisplay.setText(decimalFormat.format(firstValue) + " " + operator);
        inputDisplay.setText(null);
    }

    private void performCalculation() {
        if (!Double.isNaN(firstValue)) {
            try {
                secondValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
                secondValue = 0;
            }

            switch (currentSymbol) {
                case ADDITION:
                    firstValue += secondValue;
                    break;
                case SUBTRACTION:
                    firstValue -= secondValue;
                    break;
                case MULTIPLICATION:
                    firstValue *= secondValue;
                    break;
                case DIVISION:
                    if (secondValue != 0) {
                        firstValue /= secondValue;
                    } else {
                        outputDisplay.setText("Error: Division by zero");
                        firstValue = Double.NaN;
                    }
                    break;
                case PERCENT:
                    firstValue %= secondValue;
                    break;
            }
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
                firstValue = 0;
            }
        }
    }
}
