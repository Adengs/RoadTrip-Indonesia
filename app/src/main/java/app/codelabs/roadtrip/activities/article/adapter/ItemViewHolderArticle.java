package app.codelabs.roadtrip.activities.article.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.roadtrip.R;

public class ItemViewHolderArticle extends RecyclerView.ViewHolder {
    public TextView tvTitle, tvContent, tvTime, tvCategory;
    public ImageView ivImage;

    public ItemViewHolderArticle(@NonNull View view) {

        super(view);

        tvTitle = view.findViewById(R.id.tv_title);
        tvCategory = view.findViewById(R.id.tv_category);
        tvContent = view.findViewById(R.id.tv_content);
        tvTime = view.findViewById(R.id.tv_time);
        ivImage = view.findViewById(R.id.iv_image);
    }
}
