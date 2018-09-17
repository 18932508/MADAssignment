package com.example.f8lin.a18932508madproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends BaseAdapter {
    private Context mContext;
    //private Activity mContext;
    private ArrayList<Food> mFoodList;
    private int mResource;
    private static LayoutInflater inflater = null;
    private TextView nameView;
    private TextView costView;
    private TextView quantityView;

    public OrderListAdapter(Context mContext,int resource, ArrayList<Food> mFoodList) {
        this.mContext = mContext;
        this.mFoodList = mFoodList;
        //inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public Food getItem(int position) {
        return mFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(mResource, viewGroup, false);

        nameView = (TextView) view.findViewById(R.id.orderNameView);
        costView = (TextView) view.findViewById(R.id.orderCostView);
        quantityView = (TextView) view.findViewById(R.id.orderQuantityView);


        nameView.setText(mFoodList.get(position).getName());
        costView.setText("$" + String.valueOf(mFoodList.get(position).getCost()));
        quantityView.setText(String.valueOf(mFoodList.get(position).getQuantity()));

        return view;
    }
    public void updateReceiptsList(ArrayList<Food> fl)
    {
        mFoodList.clear();
        mFoodList.addAll(fl);
        this.notifyDataSetChanged();
    }
}
