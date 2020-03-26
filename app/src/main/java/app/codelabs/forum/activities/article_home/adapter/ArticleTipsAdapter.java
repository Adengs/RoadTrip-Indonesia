package app.codelabs.forum.activities.article_home.adapter;

import android.content.Context;
import android.content.Intent;
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
    private List<ResponsListArticelbyCategory.Data> data;

    public ArticleTipsAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ResponsListArticelbyCategory.Data datas = data.get(position);
        holder.txttitle.setText(datas.getTitle());
        holder.txtContent.setText(datas.getContent());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.imgCars);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArticleHomeCardView.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(List<ResponsListArticelbyCategory.Data> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponsListArticelbyCategory.Data> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView txttitle, txtContent;
        ImageView imgCars;
        public ArticleViewHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewtabhome);
            txttitle = view.findViewById(R.id.txtnamamobil1_latest);
            txtContent = view.findViewById(R.id.txt_desc_mobil_latest);
            imgCars = view.findViewById(R.id.img_mobill_latest);
        }
    }
}
