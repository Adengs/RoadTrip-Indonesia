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
import app.codelabs.forum.models.ResponsListArticelbyCategory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder> {
    private Context context;
    private List<ResponsListArticelbyCategory.DataEntity> data;

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
        final ResponsListArticelbyCategory.DataEntity datas = data.get(position);
        holder.tvtitle.setText(Html.fromHtml(datas.getTitle()));
        holder.tvupdate.setText(datas.getUpdated_at());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.ivCarsArticel);

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

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvtitle, tvComunityName, tvupdate;
        ImageView ivCarsArticel;
        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            tvtitle = view.findViewById(R.id.tvtitleArticel);
            ivCarsArticel = view.findViewById(R.id.ivArticel);
            tvupdate = view.findViewById(R.id.tvUpdateArticel);
        }
    }
}
