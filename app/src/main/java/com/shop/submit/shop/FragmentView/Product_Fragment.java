package com.shop.submit.shop.FragmentView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.shop.submit.shop.Adapters.ProductAdapter;
import com.shop.submit.shop.Adapters.ProductItem;
import com.shop.submit.shop.Commons.Apis;
import com.shop.submit.shop.Commons.CommonConstants;
import com.shop.submit.shop.DataJsons.GetDataProducts;
import com.shop.submit.shop.Helpers.DataApiHelpers;
import com.shop.submit.shop.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.shop.submit.shop.Commons.Apis.PRODUCT_GET_BY_CATE_API;

public class Product_Fragment extends Fragment {
    private GridView gridProduct;
    private TextView txtHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragement,container,false);
        txtHeader = (TextView)view.findViewById(R.id.txtHeader);
        gridProduct=(GridView)view.findViewById(R.id.gridProduct);

        Bundle bundle = this.getArguments();
        String cateName = bundle.getString(CommonConstants.CATE_NAME_KEY, "");
        txtHeader.setText(cateName);

        initProduct();

        gridProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductItem item = (ProductItem) parent.getItemAtPosition(position);

            }
        });

        return view;
    }


    private void initProduct(){
        GetDataProducts g = new GetDataProducts(gridProduct, String.format(PRODUCT_GET_BY_CATE_API, 1, 1));
        g.execute();
    }
}
