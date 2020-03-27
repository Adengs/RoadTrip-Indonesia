package app.codelabs.forum.activities.club.post;

import android.content.Context;
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
    private List<ResponsListArticelbyCategory.Data> data;

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
        final ResponsListArticelbyCategory.Data datas = data.get(position);
        holder.txttitle.setText(datas.getTitle());
        holder.txtContent.setText(datas.getContent());
        holder.txtupdate.setText(datas.getUpdated_at());
        Picasso.with(context).load(datas.getImage()).into(holder.imgCars);

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

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txttitle, txtContent, txtupdate;
        ImageView imgCars;
        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            txttitle = view.findViewById(R.id.txttitlepost);
            txtContent = view.findViewById(R.id.txtContenpost);
            imgCars = view.findViewById(R.id.imgpost);
            txtupdate = view.findViewById(R.id.txtupdatepost);
        }
    }
}
