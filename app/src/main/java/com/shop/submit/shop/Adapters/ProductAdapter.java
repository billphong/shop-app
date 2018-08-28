package com.shop.submit.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shop.submit.shop.Helpers.DownloadImageTask;
import com.shop.submit.shop.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context mContext;
    private final ArrayList<ProductItem> ls;

    public ProductAdapter(Context mContext, ArrayList<ProductItem> ls) {
        this.mContext = mContext;
        this.ls = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ls.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = new View(mContext);
            view = inflater.inflate(R.layout.grid_product_item, null);
            TextView txtName = (TextView) view.findViewById(R.id.grid_product_text);
            ImageView imageView = (ImageView)view.findViewById(R.id.grid_cate_image);
            txtName.setText(ls.get(i).getName());
            new DownloadImageTask(imageView).execute(ls.get(i).getImg());
            TextView txtPrice = (TextView) view.findViewById(R.id.grid_product_price);
            txtPrice.setText(Integer.toString(ls.get(i).getPrice()));
            TextView txtOldPrice = (TextView) view.findViewById(R.id.grid_product_old_price);
            txtOldPrice.setText(Integer.toString(ls.get(i).getOldPrice()));
            TextView txtDiscount = (TextView) view.findViewById(R.id.grid_product_discount);
            txtDiscount.setText(Integer.toString(ls.get(i).getDiscount()));
        }
        return view;
    }
}
