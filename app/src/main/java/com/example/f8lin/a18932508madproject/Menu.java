package com.example.f8lin.a18932508madproject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Food[] menuArray;
    private int total;

    public Menu ()
    {
        this.menuArray = new Food[50];
        this.total = total;
    }
    public static Food[] getMenuArray()
    {
        return menuArray;
    }
    public void setMenuArray(Food[] menuArray)
    {
        this.menuArray = menuArray;
    }
    public int getTotal()
    {
        int total = 0;
        int length = menuArray.length;
        for(int i = 0;i < length; i++)
        {
            total += menuArray[i].getTotal();
        }
        return total;
    }
}
