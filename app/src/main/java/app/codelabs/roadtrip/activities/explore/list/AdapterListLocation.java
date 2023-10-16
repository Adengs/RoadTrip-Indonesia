package app.codelabs.roadtrip.activities.explore.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.DetailExploreActivity;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterListShop;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.ResponseDetailExplore;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseListLocation;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListLocation extends RecyclerView.Adapter<AdapterListLocation.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseListLocation.DataEntity> item;
    private Fragment fragment;

    public AdapterListLocation(Context context) {
        this.context = context;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_location, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ResponseListLocation.DataEntity items = item.get(position);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.tvCategoryFood.setVisibility(View.GONE);
        holder.layStartFrom.setVisibility(View.GONE);

        holder.tvStore.setText(items.getTitle());
        holder.tvCity.setText(items.getCity());
        holder.tvProvince.setText(items.getProvince());

        if (items.getId() == 3){
            holder.tvCategoryFood.setVisibility(View.VISIBLE);
            holder.tvCategoryFood.setText(items.getTags().get(0));
            holder.layStartFrom.setVisibility(View.VISIBLE);
            holder.tvPriceFrom.setText((formatRupiah.format((double)items.getPrice_start())).replace(",00", "").replace("Rp",""));
        }
        if (items.getId() == 4){
            holder.layStartFrom.setVisibility(View.VISIBLE);
            holder.tvCategoryFood.setVisibility(View.GONE);
            holder.tvPriceFrom.setText((formatRupiah.format((double)items.getPrice_start())).replace(",00", "").replace("Rp",""));
        }

        if (items.getImages().isEmpty()){

        }else {
            Picasso.with(context).load(items.getImages().get(0))
                    .placeholder(R.drawable.ic_car)
                    .error(R.drawable.default_no_image)
                    .fit().centerCrop().into(holder.imgStore);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
//        return (item != null ? item.size() : 0);
    }

    public void setItems(List<ResponseListLocation.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvStore, tvCity, tvProvince, tvCategoryFood, tvPriceFrom;
        private LinearLayout layStartFrom;
        private ShapeableImageView imgStore;

        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);
            setEvent();


        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListLocation.DataEntity items = item.get(getAdapterPosition());
                    ConnectionApi.apiService(context).getDetailExplore(items.getId()).enqueue(new Callback<ResponseDetailExplore>() {
                        @Override
                        public void onResponse(Call<ResponseDetailExplore> call, Response<ResponseDetailExplore> response) {
                            if (response.body() != null) {
                                if (response.isSuccessful() && response.body().getSuccess()) {
                                    Intent intent = new Intent(context, DetailExploreActivity.class);
//                                    intent.putExtra("data", new Gson().toJson(response.body().getData()));
                                    intent.putExtra("outlet_id", response.body().getData().getId());
                                    context.startActivity(intent);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseDetailExplore> call, Throwable t) {
                            if (t.getMessage() != null) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            });

        }

        private void setView(View view) {
            tvStore = view.findViewById(R.id.store);
            tvCity = view.findViewById(R.id.city);
            tvProvince = view.findViewById(R.id.province);
            tvCategoryFood = view.findViewById(R.id.country_food);
            tvPriceFrom = view.findViewById(R.id.price_from);
            imgStore = view.findViewById(R.id.icon_store);
            layStartFrom = view.findViewById(R.id.lay_start_from);

        }
    }
}
