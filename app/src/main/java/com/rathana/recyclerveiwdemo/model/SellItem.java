package com.rathana.recyclerveiwdemo.model;

import android.support.annotation.DrawableRes;

public class SellItem {

    @DrawableRes
    private int thumb;
    private String title;
    private double price;

    public SellItem(@DrawableRes int thumb, String title, double price) {
        this.thumb = thumb;
        this.title = title;
        this.price = price;
    }

    public int getThumb()
    {
        return thumb;
    }

    public void setThumb(@DrawableRes int thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
