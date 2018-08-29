package com.shop.submit.shop.FragmentView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.shop.submit.shop.Adapters.CateItem;
import com.shop.submit.shop.Adapters.CategoryAdapter;
import com.shop.submit.shop.Adapters.ProductAdapter;
import com.shop.submit.shop.Adapters.ProductItem;
import com.shop.submit.shop.Commons.Apis;
import com.shop.submit.shop.Commons.Categories;
import com.shop.submit.shop.Commons.CommonConstants;
import com.shop.submit.shop.DataJsons.GetDataProducts;
import com.shop.submit.shop.Helpers.DataApiHelpers;
import com.shop.submit.shop.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Home_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout mDemoSlider;
    private GridView grid;
    private GridView gridProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragement,container,false);

        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);
        grid=(GridView)view.findViewById(R.id.gridCategory);
        gridProduct=(GridView)view.findViewById(R.id.gridProduct);

        initSlider();
        initCategories();
        initBestSeller();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CateItem item = (CateItem) parent.getItemAtPosition(position);
                Fragment fr = new Product_Fragment();
                getActivity().setTitle(Html.fromHtml("<font color='#ffffff'>" + item.getTxt() + " </font>"));
                Bundle bundle = new Bundle();
                bundle.putString(CommonConstants.CATE_NAME_KEY, item.getTxt());
                fr.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, fr).addToBackStack("frags").commit();
            }
        });

        return view;
    }

    private void initSlider(){
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.sl1);
        file_maps.put("Big Bang Theory",R.drawable.sl2);
        file_maps.put("House of Cards",R.drawable.sl3);
        file_maps.put("Game of Thrones", R.drawable.sl2);

        for(String name : file_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity().getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    private void initCategories(){
        ArrayList cate = new ArrayList();// Categories.getInstance().get_cate();

        class GetDataJSON extends AsyncTask<String, Void, String> {
            protected String doInBackground(String... params) {
                return DataApiHelpers.GetData(Apis.CATEGORY_API);
            }

            @Override
            protected void onPostExecute(String s) {
                ArrayList<CateItem> cate = new ArrayList<CateItem>();
                try {
                    JSONArray jsCates = new JSONArray(s);
                    for (int i = 0; i < jsCates.length(); i ++){
                        JSONObject obj = jsCates.getJSONObject(i);
                        cate.add(new CateItem(obj));
                    }
                    CategoryAdapter adapter = new CategoryAdapter(getContext(), cate);
                    grid.setAdapter(adapter);
                }catch (Exception ex){
                    Log.e("initCategories", ex.getMessage());
                }

                super.onPostExecute(s);
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute();

    }

    private void initBestSeller(){
        GetDataProducts g = new GetDataProducts(gridProduct, Apis.PRODUCT_BEST_SELLER_API + "1");
        g.execute();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
}
