package com.example.f8lin.a18932508madproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OrderMain extends AppCompatActivity {

    //Spinner itemSpinner = null;
    private static List<Food> menuArray = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        //itemSpinner = (Spinner) findViewById(R.id.itemSpinner);

        menuArray = MainActivity.getMenuArray();

        Log.d("TEST","VALUE: " + Integer.toString(MainActivity.getTableNumber()));

        for(Food cn: menuArray)
        {
            String log = "Name: " +cn.getName() + " Cost: " + cn.getCost();
            Log.d("TEST", log);
        }

        //Log.d("TEST","VALUE: " + menuArray.toString());
    }
}
