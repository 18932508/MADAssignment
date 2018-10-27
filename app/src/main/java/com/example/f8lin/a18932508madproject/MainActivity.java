package com.example.f8lin.a18932508madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button startOrder = null;
    Button loadMenu = null;
    TextView warningView = null;

    private static ArrayList<Food> menuArray = new ArrayList<Food>();
    private static ArrayList<Food> orderArray = new ArrayList<Food>();
    private Food f = new Food("", 0 ,0);
    private static int tableNumber = 0;
    private RequestQueue mQueue;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startOrder = (Button) findViewById(R.id.startOrder);
        loadMenu = (Button) findViewById(R.id.loadMenu);

        mQueue = Volley.newRequestQueue(this);

        loadMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
        startOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getMenuArraySize() < 1)
                {
                    Toast.makeText(getApplicationContext(), "Please press load menu before proceeding", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Load next Screen
                    Intent loadTableNumberScreen = new Intent(MainActivity.this, TableNumber.class);
                    startActivity(loadTableNumberScreen);
                }
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
    public int getMenuArraySize()
    {
        return menuArray.size();
    }
    public static ArrayList<Food> getMenuArray()
    {
        return menuArray;
    }
    public void jsonParse() {
        final ArrayList<Food> foodTemp = new ArrayList<>();
        String url = "https://api.myjson.com/bins/1337d0";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("menu");
                            for(i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject food = jsonArray.getJSONObject(i);
                                foodTemp.add(new Food(food.getString("name"), food.getInt("cost"), 0));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        menuArray = foodTemp;
        mQueue.add(request);

    }
}
