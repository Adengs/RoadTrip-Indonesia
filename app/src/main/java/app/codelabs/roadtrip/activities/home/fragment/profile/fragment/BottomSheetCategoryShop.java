package app.codelabs.roadtrip.activities.home.fragment.profile.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.home.fragment.profile.adapter.AdapterCategoryList;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterListShop;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetCategoryShop extends BottomSheetDialogFragment {

    private Context context;
    public final static int CATEGORIES = 0;

    private RecyclerView recyclerView;
    private ProgressBar progressView;
    private AdapterCategoryList adapter;

    public BottomSheetCategoryShop() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_category_item_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        initSetup();
        fethData();
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_category);
        adapter = new AdapterCategoryList(this);
        progressView = view.findViewById(R.id.progress_view);
    }

    private void initSetup() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }

    private void fethData(){
        getShopCategory();
    }

//    public void getShopCategory(){
//        Call<TypeComplaint> call = apiService.getTypeComplaint(auth);
//        call.enqueue(new Callback<TypeComplaint>() {
//            @Override
//            public void onResponse(@NonNull Call<TypeComplaint> call, @NonNull Response<TypeComplaint> response) {
//                if (response.isSuccessful()) {
//                    TypeComplaint data = response.body();
//                    if (response.code() == 200) {
//                        typeComplaintSAdapter.setData(data.getData().getItemsTypeComplaint());
//                        progressView.setVisibility(View.GONE);
//                    }
//                } else {
//                    ApiError error = ErrorUtils.parseError(response);
//                    Toast.makeText(requireContext(), error.message(), Toast.LENGTH_SHORT).show();
//                    progressView.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<TypeComplaint> call, @NonNull Throwable t) {
//                if (!call.isCanceled()) {
//                    Toast.makeText(requireContext(), getString(R.string.toast_onfailure), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void getShopCategory() {
        progressView.setVisibility(View.VISIBLE);
        ConnectionApi.apiService(context).getShopCategories().enqueue(new Callback<ResponseShopCategory>() {
            @Override
            public void onResponse(Call<ResponseShopCategory> call, Response<ResponseShopCategory> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {

                        progressView.setVisibility(View.GONE);
                        setListShopCategory(response.body().getData());
                    }else{
                        progressView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseShopCategory> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setListShopCategory(List<ResponseShopCategory.DataEntity> data) {
        adapter.setItems(data);
    }

}