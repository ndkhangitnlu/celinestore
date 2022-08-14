package com.fit.nlu.CelineShop.model;

import java.io.Serializable;

public class Category extends BaseModel implements Serializable {
    private String name;

    public Category(String name) {
        this.name = name;
    }
    public Category() {
        super();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
