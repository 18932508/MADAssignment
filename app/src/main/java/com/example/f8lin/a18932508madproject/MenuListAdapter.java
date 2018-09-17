package com.example.f8lin.a18932508madproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends ArrayAdapter<Food> implements  Filterable{
    private Context mContext;
    private List<Food> mFoodList, tempArray;
    private int mResource;
    private CustomFilter cs;

    public MenuListAdapter(Context mContext, int resource, List<Food> mFoodList)
    {
        super(mContext, resource, mFoodList);
        this.mContext = mContext;
        this.mFoodList = mFoodList;
        mResource = resource;
        tempArray = mFoodList;
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

        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(mResource, viewGroup, false);

        TextView nameView = (TextView)view.findViewById(R.id.nameView);
        TextView costView = (TextView)view.findViewById(R.id.costView);

        nameView.setText(mFoodList.get(position).getName());
        costView.setText("$" + String.valueOf(mFoodList.get(position).getCost()));

        return view;
    }

    @Override
    public Filter getFilter()
    {
        if(cs == null)
        {
            cs = new CustomFilter();
        }
        return cs;
    }

    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            FilterResults results = new FilterResults();

            if(charSequence!=null && charSequence.length()>0)
            {
                charSequence = charSequence.toString().toUpperCase();

                List<Food> filters = new ArrayList<>();

                for(int i=0; i < tempArray.size();i++)
                {
                    if(tempArray.get(i).getName().toUpperCase().contains(charSequence))
                    {
                        Food food = new Food(tempArray.get(i).getName(), tempArray.get(i).getCost(), tempArray.get(i).getQuantity());
                        filters.add(food);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }
            else
            {
                results.count = tempArray.size();
                results.values = tempArray;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            mFoodList = (List<Food>)filterResults.values;
            notifyDataSetChanged();

        }
    }
}
