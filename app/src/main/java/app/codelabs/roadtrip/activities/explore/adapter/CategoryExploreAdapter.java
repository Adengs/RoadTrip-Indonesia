package app.codelabs.roadtrip.activities.explore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.models.CategoryExploreSelected;
import app.codelabs.roadtrip.models.CategorySelected;
import app.codelabs.roadtrip.models.ResponseCategoryExplore;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;

public class CategoryExploreAdapter extends RecyclerView.Adapter<CategoryExploreAdapter.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseCategoryExplore.DataEntity> item ;
    private int category = 0;
    
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_explore, parent, false);
        return new MyHolder(view);
    }

   
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ResponseCategoryExplore.DataEntity items = item.get(position);
        holder.tvCategory.setText(items.getTitle());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = position;
                EventBus.getDefault().post(new CategoryExploreSelected(items.getId()));

                notifyDataSetChanged();
            }
        });

        if (category == position){
            holder.tvCategory.setBackgroundResource(R.drawable.background_category_red);
            holder.tvCategory.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            if (items.getId() == 1){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_car_white, 0, 0, 0);
            }
            if (items.getId() == 2){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_wisata_white, 0, 0, 0);
            }
            if (items.getId() == 3){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_resto_white, 0, 0, 0);
            }
            if (items.getId() == 4){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_rent_white, 0, 0, 0);
            }
        }else {
            holder.tvCategory.setBackgroundResource(R.drawable.background_category_outline);
            holder.tvCategory.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
            if (items.getId() == 1){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_car_red, 0, 0, 0);
            }
            if (items.getId() == 2){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_wisata_red, 0, 0, 0);
            }
            if (items.getId() == 3){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_resto_red, 0, 0, 0);
            }
            if (items.getId() == 4){
                holder.tvCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_rent_red, 0, 0, 0);
            }
        }
    }
    
    @Override
    public int getItemCount() {
//        return item.size();
        return (item != null ? item.size() : 0);
    }

    public void setItems(List<ResponseCategoryExplore.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private RelativeLayout container;

        public MyHolder(@NonNull View view) {
            super(view);
            
            setView(view);
            setEvent();
        }

        private void setEvent() {
        }

        private void setView(View view) {
            tvCategory = view.findViewById(R.id.text_type);
            container = view.findViewById(R.id.container_type);
        }
    }
}
