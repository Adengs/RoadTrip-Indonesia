package app.codelabs.roadtrip.activities.home.fragment.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterListShop;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.DetailShopItem;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMyShop extends RecyclerView.Adapter<AdapterMyShop.MyHolder>{
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseMyProfile.Data.Store.Item> item;
    private Fragment fragment;

    public AdapterMyShop(Fragment fragment) {
        this.fragment = fragment;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterMyShop.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        return new AdapterMyShop.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyShop.MyHolder holder, int position) {
        ResponseMyProfile.Data.Store.Item items = item.get(position);
        holder.tvProductName.setText(items.getName());
//        holder.tvLocation.setVisibility(View.GONE);
//        holder.tvLocation.setText(items.getLocation());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvPrice.setText((formatRupiah.format((double) items.getPrice())));
        holder.tvCategory.setText((items.getCategory().getCategory()));
        if (items.getPhoto().isEmpty()){

        }else{
//            Glide.with(context).load(items.getPhoto().get(0).getImage()).into(holder.ivImage);

            Picasso.with(context).load(items.getPhoto().get(0).getImage())
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_no_image)
                    .fit().centerCrop().into(holder.ivImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DetailShopItem(items.getId()));
//                ConnectionApi.apiService(context).getShopDetailItem(items.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
//                    @Override
//                    public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
//                        if (response.body() != null) {
//                            if (response.isSuccessful() && response.body().getSuccess()) {
//                                Intent intent = new Intent(context, DetailProductActivity.class);
//                                intent.putExtra("data", new Gson().toJson(response.body().getData()));
//                                intent.putExtra("edit", new Gson().toJson(response.body().getData()));
//                                fragment.startActivityForResult(intent, REQ_REFRESH_PRODUCT_LIST);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseDetailShopItem> call, Throwable t) {
//                        if (t.getMessage() != null) {
//                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponseMyProfile.Data.Store.Item> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvLocation, tvPrice, tvCategory;
        private ImageView ivImage;

        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);
        }


        private void setView(View view) {
            tvProductName = view.findViewById(R.id.tv_Product_Name);
            tvLocation = view.findViewById(R.id.tv_Location);
            tvPrice = view.findViewById(R.id.tv_Price);
            tvCategory = view.findViewById(R.id.tv_Category);
            ivImage = view.findViewById(R.id.iv_Image);

        }

    }
}
