package app.codelabs.fevci.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.codelabs.fevci.models.ResponseBookmarkShop;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.shop.adapter.AdapterListShop;
import app.codelabs.fevci.helpers.ConnectionApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static app.codelabs.fevci.activities.shop.adapter.AdapterListShop.REQ_REFRESH_PRODUCT_LIST;

public class BookmarkShopFragment extends Fragment {
    private Context context;
    private ProgressBar progressBar;
    private TextView tvNoData;

    private RecyclerView recyclerView;
    private AdapterListShop adapter;

    public BookmarkShopFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark_shop, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setView(view);
        setRecyclerview();
        getData();
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        ConnectionApi.apiService(context).getBookmarkShop().enqueue(new Callback<ResponseBookmarkShop>() {
            @Override
            public void onResponse(Call<ResponseBookmarkShop> call, Response<ResponseBookmarkShop> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }
                }
                if(adapter.getItemCount() == 0){
                    tvNoData.setVisibility(View.VISIBLE);
                }else{
                    tvNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBookmarkShop> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void setRecyclerview() {
        adapter = new AdapterListShop(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        progressBar = view.findViewById(R.id.progressbar);
        recyclerView = view.findViewById(R.id.recyclerview);
        tvNoData = view.findViewById(R.id.tv_no_data);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQ_REFRESH_PRODUCT_LIST && resultCode == RESULT_OK){
            adapter.setItems(new ArrayList());
            getData();
        }
    }
}
