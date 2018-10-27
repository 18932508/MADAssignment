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

import static android.view.View.GONE;

public class OrderMain extends AppCompatActivity {

    private static ArrayList<Food> orderArray = new ArrayList<>();
    private static ArrayList<Food> orderArrayTemp = new ArrayList<>();
    private OrderListAdapter adapter;
    private ListView orderListView;
    private TextView warningText;
    private TextView totalView;
    private TextView costView;
    private TextView qtyView;
    private TextView nameView;
    private TextView prompt;
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
        costView = (TextView)  findViewById(R.id.textView3);
        nameView = (TextView)  findViewById(R.id.textView4);
        qtyView = (TextView)  findViewById(R.id.textView5);
        prompt = (TextView) findViewById(R.id.textView8);
        addToOrder = (Button) findViewById(R.id.addToOrderButton);
        subTotal = (Button) findViewById(R.id.subTotalButton);

        checkWarningText();
        Log.d("TEST", "onCreate() OrderMain");
        Log.d("TEST", String.valueOf(MainActivity.getTableNumber()));

        adapter = new OrderListAdapter(this, R.layout.order_main_item, orderArray);
        orderListView.setAdapter(adapter);

        Log.d("REEEEEEEEEEEE", "ordermain on create");

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = (Food) adapterView.getAdapter().getItem(i);
                minusFoodItem(food);
                //Toast.makeText(getApplicationContext(), "Minus Food", Toast.LENGTH_SHORT).show();
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
        if (requestCode == 1)
        {
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
            nameView.setVisibility(View.GONE);
            costView.setVisibility(View.GONE);
            qtyView.setVisibility(View.GONE);
            prompt.setVisibility(View.GONE);
        }
        else
        {
            warningText.setText("Current Order");
            nameView.setVisibility(View.VISIBLE);
            costView.setVisibility(View.VISIBLE);
            qtyView.setVisibility(View.VISIBLE);
            prompt.setVisibility(View.VISIBLE);
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
    @Override
    public void onBackPressed()
    {
        Log.d("REEEEEEEEEEEE", "ordermain onBackPressed");
        finish();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.d("REEEEEEEEEEEE", "ordermain on restart");
        orderArray = OrderAdd.getOrderArray();
        checkWarningText();
        adapter.updateReceiptsList(orderArray);
        //OnBackPressed();
    }
}
