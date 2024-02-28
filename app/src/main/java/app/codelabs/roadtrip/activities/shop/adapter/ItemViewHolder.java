package app.codelabs.roadtrip.activities.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.roadtrip.R;


public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvProductName, tvLocation, tvPrice, tvCategory;
    public ImageView ivImage;

    public ItemViewHolder(@NonNull View view) {

        super(view);

        tvProductName = view.findViewById(R.id.tv_Product_Name);
        tvLocation = view.findViewById(R.id.tv_Location);
        tvPrice = view.findViewById(R.id.tv_Price);
        tvCategory = view.findViewById(R.id.tv_Category);
        ivImage = view.findViewById(R.id.iv_Image);

    }
}
