package app.codelabs.roadtrip.activities.home.fragment.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.CategorySelected;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCategoryList extends RecyclerView.Adapter<AdapterCategoryList.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseShopCategory.DataEntity> item;
    private Fragment fragment;

    public AdapterCategoryList(Fragment fragment) {
        this.fragment = fragment;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterCategoryList.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_category, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryList.MyHolder holder, int position) {
        ResponseShopCategory.DataEntity items = item.get(position);

        int id = items.getId();
        String category = items.getCategory();

        holder.tvCategory.setText(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " + id);
                EventBus.getDefault().post(new CategorySelected(category, id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponseShopCategory.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;

        public MyHolder(@NonNull View view) {

            super(view);
            setView(view);
        }

        private void setView(View view) {
            tvCategory = view.findViewById(R.id.text);
        }
    }
}
