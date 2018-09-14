package com.example.f8lin.a18932508madproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Food> mFoodList;

    public MenuListAdapter(Context mContext, List<Food> mFoodList)
    {
        this.mContext = mContext;
        this.mFoodList = mFoodList;
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.fragment_order_food, null);
        TextView nameView = (TextView)v.findViewById(R.id.nameView);
        TextView costView = (TextView)v.findViewById(R.id.costView);

        nameView.setText(mFoodList.get(position).getName());
        costView.setText("$" + String.valueOf(mFoodList.get(position).getCost()));

        v.setTag(mFoodList.get(position).getId());


        return v;
    }
}
