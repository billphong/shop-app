package com.shop.submit.shop.DataJsons;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.shop.submit.shop.Adapters.ProductAdapter;
import com.shop.submit.shop.Adapters.ProductItem;
import com.shop.submit.shop.Commons.Apis;
import com.shop.submit.shop.Helpers.DataApiHelpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDataProducts extends AsyncTask<String, Void, String> {

    private GridView _grid;
    private String _urlApi;

    public GetDataProducts(GridView grid, String urlApi){
        this._grid = grid;
        this._urlApi = urlApi;
    }

    protected String doInBackground(String... params) {
        return DataApiHelpers.GetData(_urlApi);
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<ProductItem> cate = new ArrayList<ProductItem>();
        try {
            JSONArray jsCates = new JSONArray(s);
            for (int i = 0; i < jsCates.length(); i ++){
                JSONObject obj = jsCates.getJSONObject(i);
                cate.add(new ProductItem(obj));
            }
            ProductAdapter adapter = new ProductAdapter(_grid.getContext(), cate);
            _grid.setAdapter(adapter);
        }catch (Exception ex){
            Log.e("initBestSeller", ex.getMessage());
        }

        super.onPostExecute(s);
    }
}
