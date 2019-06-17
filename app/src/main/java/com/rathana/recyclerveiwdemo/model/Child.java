package com.rathana.recyclerveiwdemo.model;

import android.support.annotation.DrawableRes;

public class Child {
    private String appName;
    @DrawableRes
    private int thumb;

    public Child(String appName, int thumb) {
        this.appName = appName;
        this.thumb = thumb;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }
}
