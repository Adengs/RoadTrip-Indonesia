package app.codelabs.forum.activities.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.DetailProductActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseDetailShopItem;
import app.codelabs.forum.models.ResponseListShopByCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListShop extends RecyclerView.Adapter<AdapterListShop.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseListShopByCategories.DataEntity> item;
    private Fragment fragment;

    public AdapterListShop(Fragment fragment) {
        this.fragment = fragment;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterListShop.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListShop.MyHolder holder, int position) {
        ResponseListShopByCategories.DataEntity items = item.get(position);
        holder.tvProductName.setText(items.getName());
        holder.tvLocation.setText(items.getStore().getLocation());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvPrice.setText((formatRupiah.format((double) items.getPrice())));
        holder.tvCategory.setText((items.getCategory().getCategory()));
        Picasso.with(context).load(items.getPhoto())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponseListShopByCategories.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvLocation, tvPrice, tvCategory;
        private ImageView ivImage;
        private CardView cardViewPro;

        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);
            setEvent();


        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListShopByCategories.DataEntity items = item.get(getAdapterPosition());
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

        private void setView(View view) {
            tvProductName = view.findViewById(R.id.tv_Product_Name);
            tvLocation = view.findViewById(R.id.tv_Location);
            tvPrice = view.findViewById(R.id.tv_Price);
            tvCategory = view.findViewById(R.id.tv_Category);
            cardViewPro = itemView.findViewById(R.id.cardShopPro);
            ivImage = view.findViewById(R.id.iv_Image);

        }
    }
}
