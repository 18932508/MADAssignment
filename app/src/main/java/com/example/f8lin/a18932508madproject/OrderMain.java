package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderMain extends AppCompatActivity {

    private static ArrayList<Food> orderArray = new ArrayList<>();
    private OrderListAdapter adapter;
    private ListView orderListView;
    private TextView warningText;
    private TextView totalView;
    private Button addToOrder;
    private Button subTotal;
    private int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        orderListView = (ListView) findViewById(R.id.orderListView);
        warningText = (TextView)  findViewById(R.id.warningTextView);
        totalView = (TextView)  findViewById(R.id.totalView);
        addToOrder = (Button) findViewById(R.id.addToOrderButton);
        subTotal = (Button) findViewById(R.id.subTotalButton);

        checkWarningText();
        Log.d("TEST", "onCreate() OrderMain");
        Log.d("TEST", String.valueOf(MainActivity.getTableNumber()));

        adapter = new OrderListAdapter(this, R.layout.order_main_item, orderArray);
        orderListView.setAdapter(adapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = (Food) adapterView.getAdapter().getItem(i);
                minusFoodItem(food);
            }
        });

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMain.this , OrderAdd.class);
                startActivityForResult(i,1);
            }
        });
        subTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderArray.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Please add items to the order before proceeding", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(OrderMain.this, SubTotal.class);
                    startActivityForResult(i, 2);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            setOrderArray();
        }
        if(requestCode == 2)
        {
            setOrderArray();
        }
    }
    public void setOrderArray ()
    {
        checkWarningText();
        for(Food f: orderArray)
        {
            String memes = f.getName() + " " + f.getQuantity();
            Log.d("TEST", memes);
        }
        adapter.updateReceiptsList(orderArray);
        getTotal();
    }
    public void minusFoodItem (Food food)
    {
        food.decQuantity();
        if(food.getQuantity() == 0)
        {
            orderArray.remove(food);
            checkWarningText();
            getTotal();
            adapter.updateReceiptsList(orderArray);
            return;
        }
        getTotal();
        adapter.updateReceiptsList(orderArray);
        checkWarningText();
    }
    public void checkWarningText()
    {
        if(orderArray.size() == 0) {
            warningText.setText("There is nothing in the Order");
        }
        else
        {
            warningText.setText("Current Order");
        }
    }
    public static ArrayList<Food> getOrderArray() {
        return orderArray;
    }
    public static void setOrderArrayList(ArrayList<Food> fl)
    {
        orderArray = fl;
    }
    public void getTotal()
    {
        total = 0;
        for (Food f: orderArray)
        {
            total+= f.getQuantity() * f.getCost();
        }
        totalView.setText("Your current Total is $ " + String.valueOf(total));
    }
    public void OnBackPressed()
    {
        orderArray = OrderAdd.getOrderArray();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        checkWarningText();
        OnBackPressed();
    }
}
