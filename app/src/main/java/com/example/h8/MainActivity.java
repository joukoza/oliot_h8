package com.example.h8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {

    public BottleDispenser botMach = BottleDispenser.getInstance();
    TextView bottleList;
    EditText bottleID;
    TextView currentMoney;
    TextView addMoney;
    TextView printOut;
    SeekBar seek;
    Spinner spinner;
    Context context = null;
    int spinnerCheck = 0; // Prevents the spinner from activating during initialization.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottleList = (TextView) findViewById(R.id.textView);
        currentMoney = (TextView) findViewById(R.id.textView3);
        seek = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        printOut = (TextView) findViewById(R.id.printOut);
        context = MainActivity.this;
        PrintBottles();
        UpdateSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (spinnerCheck == 0){
            spinnerCheck = 1;
        } else {
            String bottleChoice;
            String printMessage;
            bottleChoice = parent.getItemAtPosition(pos).toString();
            printMessage = botMach.buyBottle(bottleChoice);
            PrintUI(printMessage);
            PrintMoney();
            PrintBottles();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void UIAddMoney(View v) {
        int moneyAmount = seek.getProgress();
        botMach.addMoney(moneyAmount);
        currentMoney.setText(Double.toString(botMach.getMoney()));
        seek.setProgress(0);
    }

    public void PrintBottles() {
        String bottles = botMach.listContents();
        bottleList.setText(bottles);
    }

    public void PrintMoney() {
        currentMoney.setText(Double.toString(botMach.getMoney()));
    }

    public void UIReturnMoney(View v) {
        String stuff = botMach.returnMoney();
        PrintMoney();
        PrintUI(stuff);
    }

    public void UpdateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.drinks_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void PrintUI(String stuff) {
        printOut.setText(stuff);
    }

    public void PrintReceipt(View v) {
        String receipt = botMach.getReceipt();
        String fileName = "receipt.txt";
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            ows.write(receipt);
            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "PrintReceipt failed.");
        }
    }

}

