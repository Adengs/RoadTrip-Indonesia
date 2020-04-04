package app.codelabs.forum.activities.article_home.adapter;

import android.content.Context;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article_home.ArticleHomeCardView;
import app.codelabs.forum.models.ResponsListArticelbyCategory;


public class ArticleTipsAdapter extends RecyclerView.Adapter<ArticleTipsAdapter.ArticleViewHolder> {
    private Context context;
    private Integer category_id;
    private List<ResponsListArticelbyCategory.DataEntity> data;

    public ArticleTipsAdapter() {
        this.data = new ArrayList<>();
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_club, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
         final ResponsListArticelbyCategory.DataEntity datas = data.get(position);
         if(datas.getCategory_id() == 1){
             holder.tvtitle.setText(Html.fromHtml(datas.getTitle()));
             holder.tvContent.setText(Html.fromHtml(datas.getContent()));
             holder.tvupdate.setText(datas.getUpdated_at());
             Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.ivCars);
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(v.getContext(), ArticleHomeCardView.class);
                     intent.putExtra(String.valueOf(datas.getId()),"id");
                     v.getContext().startActivity(intent);
                 }
             });

         }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(List<ResponsListArticelbyCategory.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponsListArticelbyCategory.DataEntity> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvtitle, tvContent, tvupdate;
        ImageView ivCars;
        public ArticleViewHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            //cardView = view.findViewById(R.id.cv_articel);
            tvtitle = view.findViewById(R.id.tvtitleArticel);
            tvContent = view.findViewById(R.id.tvContentArticel);
            ivCars = view.findViewById(R.id.ivArticel);
            tvupdate = view.findViewById(R.id.tvUpdateArticel);

        }
    }
}
