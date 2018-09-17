package com.example.f8lin.a18932508madproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SubTotal extends AppCompatActivity {

    private ArrayList<Food> orderSubTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_total);



        orderSubTotal = OrderMain.getOrderArray();

    }
}
