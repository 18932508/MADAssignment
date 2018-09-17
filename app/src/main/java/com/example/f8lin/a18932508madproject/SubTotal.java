package com.example.f8lin.a18932508madproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubTotal extends AppCompatActivity {

    private ArrayList<Food> orderSubTotal;
    private Button backButton;
    private ListView subtotalView;
    private TextView totalCost;
    private TextView subtotalWelcome;
    private OrderListAdapter adapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_total);

        backButton = (Button) findViewById(R.id.backButtonST);
        subtotalView = (ListView) findViewById(R.id.subtotalView);
        totalCost = (TextView) findViewById(R.id.totalCost);
        subtotalWelcome = (TextView) findViewById(R.id.subtotalWelcome);

        orderSubTotal = OrderMain.getOrderArray();

        adapter = new OrderListAdapter(this, R.layout.order_main_item, orderSubTotal);
        subtotalView.setAdapter(adapter);

        total = 0;

        subtotalWelcome.setText("Final Order for Table Number: " + String.valueOf(MainActivity.getTableNumber()));

        for(Food f: orderSubTotal)
        {
            total += f.getQuantity() * f.getCost();
        }
        totalCost.setText("The Final Total is $ " + String.valueOf(total));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
}
