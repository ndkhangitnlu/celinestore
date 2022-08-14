package com.fit.nlu.CelineShop.model;

import java.io.Serializable;
import java.util.Date;

public class Blog extends BaseModel implements Serializable {
    private String image;
    private String blog_category;
    private Date date;
    private String name;
    private String des;

    public Blog(String image, String blog_category, Date date, String name, String des) {
        this.image = image;
        this.blog_category = blog_category;
        this.date = date;
        this.name = name;
        this.des = des; // noi dung blog
    }

    public Blog() {
        super();
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBlog_category() {
        return blog_category;
    }

    public void setBlog_category(String blog_category) {
        this.blog_category = blog_category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
