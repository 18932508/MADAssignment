package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button startOrder = null;
    Button loadMenu = null;
    TextView warningView = null;

    private Menu menu = new Menu();
    private static ArrayList<Food> menuArray = new ArrayList<Food>();
    private Food f = new Food("", 0 ,0);
    private static int tableNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuArray.add(new Food("Chicken Burger", 12, 0));
        menuArray.add(new Food("Beef Pie", 15, 0));
        menuArray.add(new Food("Lamb Shanks", 18, 0));
        menuArray.add(new Food("Pork Chops", 21, 0));
        menuArray.add(new Food("Cheese Burger", 17, 0));
        menuArray.add(new Food("Chicken Chop", 20, 0));
        menuArray.add(new Food("Vanilla Waffles with Homemade IceCream", 25, 0));

        startOrder = (Button) findViewById(R.id.startOrder);
        loadMenu = (Button) findViewById(R.id.loadMenu);
        //warningView = (TextView) findViewById(R.id.warningView);

        startOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuArray == null)
                {
                    String str = "Menu is empty ya dingdong";
                    warningView.setText(str);
                }
                else
                {
                    //Load next Screen
                    Intent loadTableNumberScreen = new Intent(MainActivity.this, TableNumber.class);
                    startActivity(loadTableNumberScreen);
                }
            }
        });
        loadMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //do this later
            }
        });

    }
    public static void setTableNumber(int a)
    {
        tableNumber = a;
    }
    public static int getTableNumber()
    {
        return tableNumber;
    }
    public static ArrayList<Food> getMenuArray()
    {
        return menuArray;
    }
}
