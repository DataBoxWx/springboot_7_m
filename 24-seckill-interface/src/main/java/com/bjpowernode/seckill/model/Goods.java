package com.bjpowernode.seckill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Goods implements Serializable {

    private static final long serialVersionUID = -2701710546754482987L;

    private Integer id;

    private String name;

    private String namedesc;

    private String randomname;

    //定义价格，金额的时候，java代码中需要使用BigDecimal，p2p项目面试问的时候，说BigDecimal这个类型
    private BigDecimal price;

    private String imageurl;

    private Integer store;

    private Date starttime;

    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamedesc() {
        return namedesc;
    }

    public void setNamedesc(String namedesc) {
        this.namedesc = namedesc;
    }

    public String getRandomname() {
        return randomname;
    }

    public void setRandomname(String randomname) {
        this.randomname = randomname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}