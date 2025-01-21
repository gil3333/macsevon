package com.example.macsevon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.TextViewResult);

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "+":
                case "-":
                case "*":
                case "/":
                    if (currentInput.isEmpty()) {
                        display.setText("Error");
                        return;
                    }
                    firstNumber = Double.parseDouble(currentInput);
                    operator = buttonText;
                    currentInput += " " + operator + " "; // שמירה על פורמט יפה בתצוגה
                    display.setText(currentInput);
                    break;
                case "=":
                    if (currentInput.isEmpty() || operator.isEmpty()) {
                        display.setText("Error");
                        return;
                    }
                    String[] parts = currentInput.split(" "); // פיצול המספרים והאופרטור
                    if (parts.length < 3) {
                        display.setText("Error");
                        return;
                    }
                    double secondNumber = Double.parseDouble(parts[2]); // המספר השני
                    double result = calculate(firstNumber, secondNumber, operator);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result); // שמירה על התוצאה להמשך
                    operator = ""; // איפוס אופרטור
                    break;
                case "C":
                    currentInput = "";
                    operator = "";
                    firstNumber = 0;
                    display.setText("0");
                    break;
                default: // לחיצה על מספר
                    currentInput += buttonText; // הוספה לתצוגה
                    display.setText(currentInput);
                    break;
            }
        };


        int[] buttonIds = {
                R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5,
                R.id.n6, R.id.n7, R.id.n8, R.id.n9, R.id.n0,
                R.id.caful, R.id.mainos, R.id.plus,R.id.clear,R.id.equal, R.id.spot, R.id.hilok
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }
}