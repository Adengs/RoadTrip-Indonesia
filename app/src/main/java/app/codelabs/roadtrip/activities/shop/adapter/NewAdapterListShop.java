package app.codelabs.roadtrip.activities.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.nativead.NativeAd;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAdapterListShop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private static final int IS_AD = 0;
    private static final int NOT_AD = 1;
    private Context context;
    private Fragment fragment;

    private final ArrayList<Object> item = new ArrayList<>();

    public NewAdapterListShop(Fragment fragment){
        this.fragment = fragment;
    }

    public void setList(List<ResponseListShopByCategories.DataEntity> list){
        this.item.addAll(list);
    }
    public void setAd(List<NativeAd> nativeAd){
        this.item.addAll(nativeAd);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == IS_AD) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_shop_ads, parent, false);
            return new AdViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        ResponseListShopByCategories.DataEntity items = item.get(position);

        if (getItemViewType(position) == IS_AD){
            AdViewHolder adv = (AdViewHolder) holder;
            adv.setNativeAd((NativeAd) item.get(position));
        }else{
            ResponseListShopByCategories.DataEntity items = (ResponseListShopByCategories.DataEntity) item.get(position);
            ItemViewHolder ivh = (ItemViewHolder) holder;

            ivh.tvProductName.setText(items.getName());
            ivh.tvLocation.setText(items.getStore().getLocation());
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            ivh.tvPrice.setText((formatRupiah.format((double) items.getPrice())));
            ivh.tvCategory.setText(items.getCategory().getCategory());

            if (items.getPhoto().isEmpty()){

            }else {
                Picasso.with(context).load(items.getPhoto().get(0).getImage())
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_no_image)
                        .fit().centerCrop().into(ivh.ivImage);
            }

            ivh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListShopByCategories.DataEntity items = (ResponseListShopByCategories.DataEntity) item.get(position);
                    ConnectionApi.apiService(context).getShopDetailItem(items.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
                        @Override
                        public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                            if (response.body() != null) {
                                if (response.isSuccessful() && response.body().getSuccess()) {
                                    Intent intent = new Intent(context, DetailProductActivity.class);
                                    intent.putExtra("data", new Gson().toJson(response.body().getData()));
                                    fragment.startActivityForResult(intent, REQ_REFRESH_PRODUCT_LIST);
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
            });
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (item.get(position) instanceof NativeAd){
            return IS_AD;
        }else {
            return NOT_AD;
        }
    }
}
