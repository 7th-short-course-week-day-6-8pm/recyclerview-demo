package com.rathana.recyclerveiwdemo.model;

import java.util.List;

public class Parent {

    private String title;
    private List<Child> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
