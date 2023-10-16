package app.codelabs.roadtrip.activities.shop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.codelabs.roadtrip.activities.home.fragment.profile.activity.NewPostProfileActivity;
import app.codelabs.roadtrip.activities.home.fragment.profile.adapter.ItemImageAdapter;
import app.codelabs.roadtrip.activities.home.fragment.profile.fragment.DialogDeleteProduct;
import app.codelabs.roadtrip.models.ConfirmDelete;
import app.codelabs.roadtrip.models.DeleteImage;
import app.codelabs.roadtrip.models.RefreshAdapter;
import app.codelabs.roadtrip.models.ResponseBookmarkShop;
import app.codelabs.roadtrip.models.ResponseDoBookmark;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterShopDeskription;
import app.codelabs.roadtrip.activities.shop.fragment.FragmentDetailShop;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseUpdateProfile;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PHONE = 101;
    private static final int REQ_EDIT_SHOP = 1000;
    private Context context;
    private Toolbar toolbar;
    private TextView tvHubungi, tvWa, tvDelete, tvEdit;
    private BottomNavigationView btnShop, btnEdit;
    public ResponseDetailShopItem.Data data;
    private AdapterShopDeskription adapter;
    private Boolean editMode = false;
    private int id = 0 ;


    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private DialogDeleteProduct dialogDeleteProduct = new DialogDeleteProduct();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        context = getApplicationContext();
        progressDialogFragment.show(getSupportFragmentManager(), "detail-shop");
        getData();
        setView();
        setEvent();
        setToolBar();
        setFragment(new FragmentDetailShop());
        setViewPager();
        getBookmark();
        loadData();

        checkCondition();
    }

    private void loadData() {

    }

    private void checkCondition() {
        if (getIntent().getStringExtra("edit") != null){
            editMode = true;
//            String strData = getIntent().getStringExtra("edit");
//            data = new Gson().fromJson(strData, ResponseDetailShopItem.Data.class);

            btnShop.setVisibility(View.GONE);
            btnEdit.setVisibility(View.VISIBLE);
        }
    }

    private void getData() {
        String strData = getIntent().getStringExtra("data");
        data = new Gson().fromJson(strData, ResponseDetailShopItem.Data.class);
        ConnectionApi.apiService(context).getShopDetailItem(data.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
            @Override
            public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        data = response.body().getData();
                        id = data.getId();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailShopItem> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
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
        tvDelete.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
    }

    private void setView() {
        tvHubungi = findViewById(R.id.tvHubungi);
        tvWa = findViewById(R.id.tvWA);
        toolbar = findViewById(R.id.toolbar);
        tvDelete = findViewById(R.id.tv_delete);
        tvEdit = findViewById(R.id.tv_edit);
        btnShop = findViewById(R.id.bottomshop);
        btnEdit = findViewById(R.id.bottom_edit);
    }

    private void setViewPager() {
        adapter = new AdapterShopDeskription(getSupportFragmentManager());
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
                openWhatApp();
                break;
            case R.id.tv_delete:
                deleteDialog();
                break;
            case R.id.tv_edit:
                editItem();
                break;
        }
    }

    private void editItem() {
        Intent intent = new Intent(context, NewPostProfileActivity.class);
        intent.putExtra("edit", new Gson().toJson(data));
        startActivityForResult(intent, REQ_EDIT_SHOP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
//            if (resultCode == REQ_EDIT_SHOP){
                getData();
                setFragment(new FragmentDetailShop());
                setViewPager();
                getBookmark();
                loadData();
//            }
        }
    }

    private void deleteDialog() {
        dialogDeleteProduct.show(getSupportFragmentManager(), dialogDeleteProduct.getTag());
    }

    private void deleteItem() {
        Log.e("TAG", "cek Klik" + id );

//        Map<String, RequestBody> data = new HashMap<>();
//        data.put("user_store_item_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id)));
        ConnectionApi.apiService(context).removeItem(id).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
//                        Toast.makeText(context, "Success Delete", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
//                        onBackPressed();
//                        Intent intent = new Intent(context, DetailProductActivity.class);
//                        intent.putExtra("data", new Gson().toJson(response.body().getData()));
//                        startActivityForResult(intent, REQ_REFRESH_PRODUCT_LIST);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {

            }
        });
    }

    private void openWhatApp() {
        String url = data.getWhatsapp();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void phone() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DetailProductActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PHONE);
                return;
            }
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + data.getStore().getPhone()));
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + data.getStore().getPhone()));
            startActivity(intent);
        }

    }


    private void bookmarkShop() {
        progressDialogFragment.show(getSupportFragmentManager(), "detail-shop");
        ConnectionApi.apiService(context).doBookmarkShop(data.getId(), "shop").enqueue(new Callback<ResponseDoBookmark>() {
            @Override
            public void onResponse(Call<ResponseDoBookmark> call, Response<ResponseDoBookmark> response) {

                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setResult(RESULT_OK);
                        getBookmark();
                    }
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

    @Subscribe
    public void onConfirmDelete(ConfirmDelete confirmDelete){
        deleteItem();
        Toast.makeText(context, "Success Delete", Toast.LENGTH_SHORT).show();
//        setResult(RESULT_OK);
//        EventBus.getDefault().post(new RefreshAdapter());
        onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
