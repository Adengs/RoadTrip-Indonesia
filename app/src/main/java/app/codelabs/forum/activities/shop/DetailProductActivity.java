package app.codelabs.forum.activities.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.activities.shop.adapter.AdapterShop;
import app.codelabs.forum.activities.shop.fragment.FragmentRincian;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseBookmarkShop;
import app.codelabs.forum.models.ResponseDoBookmark;
import app.codelabs.forum.models.ResponseListShopByCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PHONE = 101;
    private Context context;
    private Toolbar toolbar;
    private TextView tvHubungi, tvWa;
    public ResponseListShopByCategories.DataEntity data;

    AdapterShop adapter;


    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        context = getApplicationContext();
        progressDialogFragment.show(getSupportFragmentManager(), "progress");
        getData();
        setView();
        getBookmark();
        setEvent();
        setToolBar();
        setFragment(new FragmentRincian());
        setViewPager();
    }

    private void getData() {
        String strData = getIntent().getStringExtra("data");
        data = new Gson().fromJson(strData, ResponseListShopByCategories.DataEntity.class);
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

    private void setViewPager() {
        adapter = new AdapterShop(getSupportFragmentManager());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_bookmark:
                bookmarkShop();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bookmark, menu);
        this.menu = menu;
        refreshToolbar();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHubungi:
                phone();
                break;
            case R.id.tvWA:
                WhatApp();
                break;
        }
    }

    private void WhatApp() {
        String url = "https://api.whatsapp.com/send?phone=" + "+62" + data.getContact();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void phone() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DetailProductActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PHONE);
                return;
            }
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + data.getContact()));
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + data.getContact()));
            startActivity(intent);
        }
    }


    private void bookmarkShop() {
        progressDialogFragment.show(getSupportFragmentManager(), "progress");
        ConnectionApi.apiService(context).doBookmarkShop(data.getId(), "shop").enqueue(new Callback<ResponseDoBookmark>() {
            @Override
            public void onResponse(Call<ResponseDoBookmark> call, Response<ResponseDoBookmark> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    setResult(RESULT_OK);
                    getBookmark();
                } else {
                    progressDialogFragment.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseDoBookmark> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getBookmark() {
        ConnectionApi.apiService(context).getBookmarkShop().enqueue(new Callback<ResponseBookmarkShop>() {
            @Override
            public void onResponse(Call<ResponseBookmarkShop> call, Response<ResponseBookmarkShop> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            checkBookmark(response.body().getData());
                        } else {
                            checkBookmark(new ArrayList());
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBookmarkShop> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkBookmark(List<ResponseListShopByCategories.DataEntity> items) {
        data.setBookmark(false);
        for (ResponseListShopByCategories.DataEntity item : items) {
            if (item.getId() == data.getId()) {
                data.setBookmark(true);
                break;
            }
        }

        refreshToolbar();
    }

    private void refreshToolbar() {
        if (menu != null) {
            if (data.isBookmark()) {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_fill));
            } else {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark));
            }
        }
    }

}
