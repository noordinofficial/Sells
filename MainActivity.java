package com.example.midsem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextText, editTextText2, editTextText3, editTextText4, editTextText5,
            editTextText6, editTextText7, editTextText8, editTextText9;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);
        editTextText3 = findViewById(R.id.editTextText3);
        editTextText4 = findViewById(R.id.editTextText4);
        editTextText5 = findViewById(R.id.editTextText5);
        editTextText6 = findViewById(R.id.editTextText6);
        editTextText7 = findViewById(R.id.editTextText7);
        editTextText8 = findViewById(R.id.editTextText8);
        editTextText9 = findViewById(R.id.editTextText9);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        int breadQty = getQuantity(editTextText);
        int milkQty = getQuantity(editTextText3);
        int sugarQty = getQuantity(editTextText5);

        if (breadQty == 0 && milkQty == 0 && sugarQty == 0) {
            Toast.makeText(MainActivity.this, "You must enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        if (breadQty > 4 || milkQty > 4 || sugarQty > 4) {
            Toast.makeText(MainActivity.this, "You cannot buy more than 4 items", Toast.LENGTH_SHORT).show();
            return;
        }

        int breadTotal = breadQty * 150;
        int milkTotal = milkQty * 1500;
        int sugarTotal = sugarQty * 5000;

        int grandTotal = breadTotal + milkTotal + sugarTotal;
        editTextText2.setText(String.valueOf(breadTotal));
        editTextText4.setText(String.valueOf(milkTotal));
        editTextText6.setText(String.valueOf(sugarTotal));
        editTextText7.setText(String.valueOf(grandTotal));

        applyDiscount(grandTotal);
    }

    private int getQuantity(EditText editText) {
        String quantityString = editText.getText().toString();
        if (quantityString.isEmpty()) return 0;
        return Integer.parseInt(quantityString);
    }

    private void applyDiscount(int grandTotal) {
        double discountPercentage;
        if (grandTotal <= 10000) {
            discountPercentage = 0;
        } else if (grandTotal>=11000 && grandTotal<= 25000) {
            discountPercentage = 0.15;
        } else if (grandTotal >=26000 && grandTotal<= 35000) {
            discountPercentage = 0.25;
        } else {
            discountPercentage = 0.30;
        }

        double discountAmount = grandTotal * discountPercentage;
        double netPay = grandTotal - discountAmount;

        editTextText8.setText(String.valueOf(discountAmount));
        editTextText9.setText(String.valueOf(netPay));
    }
}
