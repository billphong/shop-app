package com.shop.submit.shop.Adapters;

import android.util.Log;

import com.shop.submit.shop.Commons.Apis;

import org.json.JSONObject;

public class ProductItem {
    private int id;
    private String name;
    private String img;
    private int price;
    private int oldPrice;
    private int discount;
    private String saleOff; //khuyen mai

    public ProductItem(int id, String name, String img, int price, int discount, String saleOff){
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.discount = discount;
        this.saleOff = saleOff;
    }

    public ProductItem(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("ID");
            this.name = jsonObject.getString("Name");
            this.img = (Apis.HOST + jsonObject.getString("Img")).replace("//","/");
            this.price = 0;//(int)jsonObject.getDouble("Price");
            this.discount = 0;//jsonObject.getInt("Discount");
            this.saleOff = "";//jsonObject.getString("SaleOff");
            this.oldPrice = 0;
        }catch (Exception ex){
            Log.e("Init CateItem", ex.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(String saleOff) {
        this.saleOff = saleOff;
    }
}
