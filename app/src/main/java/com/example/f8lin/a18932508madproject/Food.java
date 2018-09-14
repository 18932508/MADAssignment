package com.example.f8lin.a18932508madproject;

public class Food {
    private String name;
    private int cost;
    private int quantity;

    public Food(String name, int cost, int quantity){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setCost(int cost)
    {
        this.cost = cost;
    }
    public int getTotal()
    {
        return cost * quantity;
    }
    public String getName ()
    {
        return name;
    }
    public int getCost()
    {
        return cost;
    }
}
