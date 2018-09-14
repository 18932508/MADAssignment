package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TableNumber extends AppCompatActivity {

    Button tableNumberButton = null;
    EditText tableNumberText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_number);

        tableNumberButton = (Button) findViewById(R.id.tableNumberButton);
        tableNumberText = (EditText) findViewById(R.id.tableNumberText);

        tableNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = tableNumberText.getText().toString();

                int input=Integer.parseInt(input1);

                //MainActivity ma = new MainActivity();
                MainActivity.setTableNumber(input);

                Log.d("TEST","VALUE: " + Integer.toString(MainActivity.getTableNumber()));
                Intent loadOrderMain = new Intent(TableNumber.this, OrderMain.class);
                startActivity(loadOrderMain);

            }
        });

    }
}
