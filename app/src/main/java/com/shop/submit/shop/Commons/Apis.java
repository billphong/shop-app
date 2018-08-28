package com.shop.submit.shop.Commons;

public class Apis {
    public static String HOST = "http://10.0.2.2:8080/";
    public static String API_HOST = "http://10.0.2.2:8080/api/";

    public static final String CATEGORY_API = API_HOST + "categoryapi";
    public static final String PRODUCT_API = API_HOST + "productapi";
    public static final String PRODUCT_BEST_SELLER_API = PRODUCT_API + "?pageIndex=";
}
