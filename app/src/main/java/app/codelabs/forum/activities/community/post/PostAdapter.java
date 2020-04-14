package app.codelabs.forum.activities.community.post;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponsListArticelbyCategory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private Context context;
    private List<ResponsListArticelbyCategory.DataEntity> item;

    public PostAdapter() {
        item = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_club, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        final ResponsListArticelbyCategory.DataEntity items = item.get(position);
        holder.tvtitle.setText(Html.fromHtml(items.getTitle()));
        holder.tvContent.setText(Html.fromHtml(items.getContent()));
        holder.tvupdate.setText(items.getUpdated_at());
        holder.tvCategory.setText(items.getCategory().getCategory());
        Picasso.with(context).load(items.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivArticel);
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponsListArticelbyCategory.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponsListArticelbyCategory.DataEntity> items) {
        this.item.addAll(items);
        notifyDataSetChanged();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        private TextView tvtitle, tvContent, tvupdate,tvCategory;
        private ImageView ivArticel;

        public PostHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            tvtitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tv_Content);
            ivArticel = view.findViewById(R.id.iv_Article);
            tvCategory = view.findViewById(R.id.tv_Category);
            tvupdate = view.findViewById(R.id.tv_Time);
        }
    }
}
