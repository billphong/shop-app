package com.shop.submit.shop;

import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;

import com.shop.submit.shop.Adapters.CateItem;
import com.shop.submit.shop.Commons.Categories;
import com.shop.submit.shop.Commons.StringHelper;
import com.shop.submit.shop.FragmentView.Home_Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int KEY = 6;
    public static final int ID = 1;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        setCategories();

        //content
        Fragment fr = new Home_Fragment();
        //setTitle("Danh Mục");
        setTitle(Html.fromHtml("<font color='#ffffff'>" + getTitle() + " </font>"));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_main, fr).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCategories(){
        ArrayList<CateItem> cate = new ArrayList<CateItem>(){{
            add(new CateItem(1, StringHelper.getById(MainActivity.this, R.string.cate_book), R.drawable.books));
            add(new CateItem(2, StringHelper.getById(MainActivity.this, R.string.cate_toy), R.drawable.books));
            add(new CateItem(3, StringHelper.getById(MainActivity.this, R.string.cate_closther), R.drawable.books));
            add(new CateItem(4, StringHelper.getById(MainActivity.this, R.string.cate_beauty), R.drawable.books));
            add(new CateItem(5, StringHelper.getById(MainActivity.this, R.string.cate_electric), R.drawable.books));
            add(new CateItem(6,StringHelper.getById(MainActivity.this, R.string.cate_phone), R.drawable.books));
        }};
        Categories.getInstance().set_cate(cate);
    }
}
