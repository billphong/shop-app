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

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private final ArrayList<CateItem> ls;

    public CategoryAdapter(Context c, ArrayList<CateItem> ls) {
        mContext = c;
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
            view = inflater.inflate(R.layout.grid_cate_item, null);
            TextView textView = (TextView) view.findViewById(R.id.grid_cate_text);
            ImageView imageView = (ImageView)view.findViewById(R.id.grid_cate_image);
            textView.setText(ls.get(i).getTxt());
            //imageView.setImageResource(ls.get(i).getImgId());
            new DownloadImageTask(imageView).execute(ls.get(i).getImgUrl());
        }
        return view;
    }
}
