package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class TableNumber extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button tableNumberButton = null;
    Spinner tableNumberSpinner = null;
    private String[] numbers = {"Table 1","Table 2","Table 3","Table 4","Table 5","Table 6","Table 7","Table 8","Table 9","Table 10","Table 11","Table 12","Table 13","Table 14","Table 15"};
    private int temp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_number);

        tableNumberButton = (Button) findViewById(R.id.tableNumberButton);
        tableNumberSpinner = (Spinner) findViewById(R.id.tableNumberSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TableNumber.this, android.R.layout.simple_spinner_item,numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tableNumberSpinner.setAdapter(adapter);
        tableNumberSpinner.setOnItemSelectedListener(this);


        tableNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.setTableNumber(temp);

                Intent loadOrderMain = new Intent(TableNumber.this, OrderMain.class);
                startActivityForResult(loadOrderMain, 1);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    String selected = numbers[i];
    String temp2 = selected.substring(6);
    temp = Integer.parseInt(temp2);
    Log.d("Test", String.valueOf(temp));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            setOrderArray();
        }
    }
    public void setOrderArray()
    {
        OrderMain.setOrderArrayList(OrderMain.getOrderArray());
    }
}
