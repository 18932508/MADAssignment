package com.example.f8lin.a18932508madproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderMain extends AppCompatActivity {

    private static List<Food> menuArray = new ArrayList<Food>();
    private static ArrayList<Food> orderArray = new ArrayList<>();
    private OrderListAdapter adapter;
    private ListView orderListView;
    private TextView warningText;
    private Button addToOrder;
    private Button subTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        orderListView = (ListView) findViewById(R.id.orderListView);
        warningText = (TextView)  findViewById(R.id.warningTextView);
        addToOrder = (Button) findViewById(R.id.addToOrderButton);
        subTotal = (Button) findViewById(R.id.subTotalButton);

        menuArray = MainActivity.getMenuArray();

        checkWarningText();

        adapter = new OrderListAdapter(this, R.layout.order_main_item, orderArray);
        orderListView.setAdapter(adapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = (Food) adapterView.getAdapter().getItem(i);
                minusFoodItem(food);
                //Toast.makeText(getApplicationContext(), "Click Product id = " + view.getTag(), Toast.LENGTH_SHORT).show();
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
                //add check for empty order array and make pop up if it happens!!!
                Intent i = new Intent(OrderMain.this, SubTotal.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("TEST", "BACK");

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                //ArrayList<Food> test = (ArrayList<Food>) data.getSerializableExtra("results");
                ArrayList<Food> test = OrderAdd.getOrderArray();
                setOrderArray(test);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public void setOrderArray (ArrayList ar)
    {
        orderArray = ar;
        for(Food f: orderArray)
        {
            String memes = f.getName() + " " + f.getQuantity();
            Log.d("TEST", memes);
        }
        adapter.updateReceiptsList(orderArray);
    }
    public void minusFoodItem (Food food)
    {
        Log.d("Test", "minusFoodItem");
        food.decQuantity();
        if(food.getQuantity() == 0)
        {
            orderArray.remove(food);
            checkWarningText();
            adapter.updateReceiptsList(orderArray);
            return;
        }
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
}
