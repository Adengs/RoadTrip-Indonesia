package app.codelabs.fevci.activities.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.fevci.activities.article.DetailArticleActivity;
import app.codelabs.fevci.helpers.DateTimeHelper;
import app.codelabs.fevci.models.ResponseListArticle;
import app.codelabs.forum.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleVH> {
    public static final int REQ_REFRESH_ARTICLE_LIST = 2001;
    private List<ResponseListArticle.Article> items;
    private Context context;
    private Fragment fragment;

    public ArticleAdapter(Fragment fragment) {
        this.fragment = fragment;
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleVH holder, int position) {
        ResponseListArticle.Article item = items.get(position);
        holder.tvCategory.setText(item.getCategory().getCategory());
        holder.tvContent.setText(Html.fromHtml(item.getContent()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(DateTimeHelper.instance(item.getCreated_at()).getTimeElapsed());
        Picasso.with(context).load(item.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseListArticle.Article> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    class ArticleVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvTime, tvCategory;
        ImageView ivImage;

        ArticleVH(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListArticle.Article item = items.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailArticleActivity.class);
                    intent.putExtra("data", new Gson().toJson(item));
                    fragment.startActivityForResult(intent, REQ_REFRESH_ARTICLE_LIST);
                }
            });

        }

        private void setView(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            tvCategory = view.findViewById(R.id.tv_category);
            tvContent = view.findViewById(R.id.tv_content);
            tvTime = view.findViewById(R.id.tv_time);
            ivImage = view.findViewById(R.id.iv_image);

        }
    }
}