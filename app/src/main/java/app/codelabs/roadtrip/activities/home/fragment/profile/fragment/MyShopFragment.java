package app.codelabs.roadtrip.activities.home.fragment.profile.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.home.fragment.profile.activity.NewPostProfileActivity;
import app.codelabs.roadtrip.activities.home.fragment.profile.adapter.AdapterMyShop;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterListShop;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ConfirmDelete;
import app.codelabs.roadtrip.models.DetailShopItem;
import app.codelabs.roadtrip.models.RefreshAdapter;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.models.ResponseLogin;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyShopFragment extends Fragment {
    private static final int REQ_EDIT_PROFILE = 1001;
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Session session;
    private Context context;
    private RecyclerView recyclerView;
    private AdapterMyShop adapter;

    private TextView tvNameShop, tvNoData, addProduct;
//    private FloatingActionButton floating;

    private  boolean refreshShop = false;

    public MyShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);

        setView(view);
        setShopName();
//        setRecyclerView();
    }

    private void setView(View view) {
        tvNameShop = view.findViewById(R.id.tv_name_shop);
//        floating = view.findViewById(R.id.floating);
        addProduct = view.findViewById(R.id.text_add_product);
        tvNoData = view.findViewById(R.id.tv_no_data);
        recyclerView = view.findViewById(R.id.recylerView_shop_menu);
        adapter = new AdapterMyShop(this);

//        floating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                refreshShop = true;
////                Toast.makeText(context, "On Develop :(", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, NewPostProfileActivity.class);
//                startActivityForResult(intent, REQ_EDIT_PROFILE);
//            }
//        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshShop = true;
                Intent intent = new Intent(context, NewPostProfileActivity.class);
                startActivityForResult(intent, REQ_EDIT_PROFILE);
            }
        });
    }

    private void setShopName(){
        tvNoData.setVisibility(View.GONE);
        ConnectionApi.apiService(context).getProfile().enqueue(new Callback<ResponseMyProfile>() {
            @Override
            public void onResponse(Call<ResponseMyProfile> call, Response<ResponseMyProfile> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        ResponseMyProfile.Data profile = response.body().getData();
                        tvNameShop.setText(profile.getStore().getName());

                        setListMyShop(profile.getStore().getItem());

                        Log.e("TAG", "onResponse: " + adapter.getItemCount() );
                        if (adapter.getItemCount() == 0){
                            tvNoData.setVisibility(View.VISIBLE);
                        }else{
                            tvNoData.setVisibility(View.GONE);
                            setRecyclerView();
                        }
                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMyProfile> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


//        ResponseMyProfile.Data profile = session.getProfile();
//
//        Log.e("TAG", "setShopName: " + profile.getStore().getName() );
//
//        tvNameShop.setText(profile.getStore().getName());
    }

    private void setListMyShop(List<ResponseMyProfile.Data.Store.Item> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK ) {
            if (requestCode == REQ_EDIT_PROFILE){
                setShopName();
            }
            setShopName();
//            if (resultCode == REQ_REFRESH_PRODUCT_LIST){
//                setShopName();
//            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (refreshShop){
            setShopName();
        }
    }

//    @Subscribe
//    public void onRefresh(RefreshAdapter refreshAdapter){
//        setShopName();
//    }

    @Subscribe
    public void onDetailShop(DetailShopItem detailShopItem) {
        ConnectionApi.apiService(context).getShopDetailItem(detailShopItem.id).enqueue(new Callback<ResponseDetailShopItem>() {
            @Override
            public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        refreshShop = true;
                        Intent intent = new Intent(context, DetailProductActivity.class);
                        intent.putExtra("data", new Gson().toJson(response.body().getData()));
                        intent.putExtra("edit", new Gson().toJson(response.body().getData()));
                        startActivityForResult(intent, REQ_REFRESH_PRODUCT_LIST);
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