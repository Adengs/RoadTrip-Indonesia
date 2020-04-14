package app.codelabs.forum.activities.club.post;

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
import app.codelabs.forum.models.ResponseListArticelbyCategory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder> {
    private Context context;
    private List<ResponseListArticelbyCategory.DataEntity> data;

    public PostAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_club, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ResponseListArticelbyCategory.DataEntity datas = data.get(position);
        holder.tvtitle.setText(Html.fromHtml(datas.getTitle()));
        holder.tvContent.setText(Html.fromHtml(datas.getContent()));
        holder.tvupdate.setText(datas.getUpdated_at());
        holder.tvCategory.setText(datas.getCategory().getCategory());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.ivCarsArticel);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(List<ResponseListArticelbyCategory.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponseListArticelbyCategory.DataEntity> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvtitle, tvContent, tvupdate,tvCategory;
        ImageView ivCarsArticel;
        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            tvtitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tvContent);
            ivCarsArticel = view.findViewById(R.id.ivArticle);
            tvCategory = view.findViewById(R.id.tvCategory);
            tvupdate = view.findViewById(R.id.tvTime);
        }
    }
}
