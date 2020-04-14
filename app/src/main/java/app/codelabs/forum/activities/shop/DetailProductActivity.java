package app.codelabs.forum.activities.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.Adapter.AdapterShop;
import app.codelabs.forum.activities.shop.Fragment.FragmentRincian;
import app.codelabs.forum.models.ResponsListShopByCategories;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PHONE=101;
    private Context context;
    private Toolbar toolbar;
    private TextView tvHubungi, tvWa;
    private ResponsListShopByCategories.DataEntity data;

    AdapterShop adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        context = getApplicationContext();

        setView();
        setEvent();
        setToolBar();

        String strData =getIntent().getStringExtra("data");
        data = new Gson().fromJson(strData, ResponsListShopByCategories.DataEntity.class);
        Bundle ags = new Bundle();
        ags.putString("data", new Gson().toJson(data));
        FragmentRincian fragmentRincian = new FragmentRincian();
        fragmentRincian.setArguments(ags);
        setFragment(fragmentRincian);
        setViewPager();
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("");
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containershop, fragment);
        fragmentTransaction.commit();
    }

    private void setEvent() {
        tvHubungi.setOnClickListener(this);
        tvWa.setOnClickListener(this);
    }

    private void setView() {
        tvHubungi = findViewById(R.id.tvHubungi);
        tvWa = findViewById(R.id.tvWA);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setViewPager(){
        adapter=new AdapterShop(getSupportFragmentManager());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_article_detail_bookmark:
                break;
        }
            return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shop_detail, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHubungi :
                phone();
                break;
            case R.id.tvWA :
                WhatApp();
                break;
        }
    }

    private void WhatApp() {
        String url = "https://api.whatsapp.com/send?phone="+"+62"+ data.getContact();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void phone() {
        if (Build.VERSION.SDK_INT>22){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(DetailProductActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_PHONE);
                return;
            }
            Intent intent =new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ data.getContact()));
            startActivity(intent);
        }
        else {
            Intent intent =new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ data.getContact()));
            startActivity(intent);
        }
    }

}
