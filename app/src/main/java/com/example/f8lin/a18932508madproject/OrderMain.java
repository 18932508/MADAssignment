package com.example.f8lin.a18932508madproject;

import android.support.v4.app.FragmentManager;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderMain extends AppCompatActivity {

    private static List<Food> menuArray = new ArrayList<Food>();
    private static List<Food> orderArray = new ArrayList<>();
    private MenuListAdapter adapter;
    private ListView menuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        menuListView = (ListView) findViewById(R.id.menuListView);
        menuArray = MainActivity.getMenuArray();

        Log.d("TEST","VALUE: " + Integer.toString(MainActivity.getTableNumber()));

        /*for(Food cn: menuArray)
        {
            String log = "Name: " +cn.getName() + " Cost: " + cn.getCost();
            Log.d("TEST", log);
        }*/

        adapter = new MenuListAdapter(getApplicationContext(), menuArray);
        menuListView.setAdapter(adapter);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Food food = menuArray.get(i);
                addToOrderArray(food);

                Toast.makeText(getApplicationContext(), "Click Product id = " + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static void addToOrderArray(Food food)
    {
        Log.d("TEST", "got to add");
        String log = "Name: " + food.getName() + " Cost: " + food.getCost() + ", Quantity: " + food.getQuantity();
        Log.d("TEST", log);

        for(Food cn: orderArray) {
            Log.d("TEST", "got to FOR");
            if (cn.getName().equalsIgnoreCase(food.getName())) {
                Log.d("TEST", "got to IF");
                //increment quantity;
                food.addQuantity();

                String log2 = "Name: " + cn.getName() + " Cost: " + cn.getCost() + ", Quantity: " + cn.getQuantity();
                Log.d("TEST", log2);
                break;
            }
        }
        Log.d("TEST", "got to ELSE");
        
        orderArray.add(food);

        Log.d("TEST", "got to add END");
        /*for(Food cn: orderArray)
        {
            String log = "Name: " + cn.getName() + " Cost: " + cn.getCost() + ", Quantity: " +cn.getQuantity();
            Log.d("TEST", log);
        }*/
    }

}
