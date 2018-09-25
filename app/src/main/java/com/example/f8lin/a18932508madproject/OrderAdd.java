package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdd extends AppCompatActivity implements TextWatcher {

    private static List<Food> menuArray = new ArrayList<Food>();
    private static ArrayList<Food> orderArray = new ArrayList<Food>();
    private MenuListAdapter adapter;
    private ListView menuListView;
    private EditText searchFood;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);

        backButton = (Button) findViewById(R.id.backButton);
        menuListView = (ListView) findViewById(R.id.menuListView);
        searchFood = (EditText) findViewById(R.id.searchFood);
        searchFood.addTextChangedListener(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        menuArray = MainActivity.getMenuArray();

        adapter = new MenuListAdapter(this, R.layout.fragment_order_food, menuArray);
        menuListView.setAdapter(adapter);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Food food = (Food) adapterView.getAdapter().getItem(i);
                addToOrderArray(food);
                //Toast.makeText(getApplicationContext(), "Click Product id = " + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderMain.setOrderArrayList(orderArray);
                finish();
            }
        });


    }
    public static void addToOrderArray(Food food)
    {
        for(Food f: orderArray) {
            if (f.getName().equalsIgnoreCase(food.getName())) {
                f.addQuantity();
                return;
            }
        }
        orderArray.add(food);
        food.addQuantity();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        this.adapter.getFilter().filter(charSequence);
    }
    @Override
    public void afterTextChanged(Editable editable) {

    }
    public static ArrayList<Food> getOrderArray()
    {
        return orderArray;
    }
    @Override
    public void onBackPressed()
    {
        MainActivity.setOrderArray(orderArray);
        finish();
    }

}
