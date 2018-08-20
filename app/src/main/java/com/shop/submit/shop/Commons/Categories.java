package com.shop.submit.shop.Commons;

import com.shop.submit.shop.Adapters.CateItem;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Categories {
    private ArrayList<CateItem> _cate;

    private static Categories instance;

    private Categories(){}

    public static Categories getInstance() {
        if(instance == null){
            instance = new Categories();
        }
        return instance;
    }

    public ArrayList<CateItem> get_cate() {
        return _cate;
    }

    public void set_cate(ArrayList<CateItem> _cate) {
        this._cate = _cate;
    }
}
