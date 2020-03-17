package app.codelabs.forum.activities.article_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.article_home.ArticleHomeCardView;
import app.codelabs.forum.activities.home.latest_popular_foryou.CardViewActivity;

public class ArticleReviewAdapter extends RecyclerView.Adapter<ArticleReviewAdapter.ReviewViewHolder> {
    ArrayList<Fragment> items = new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
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
        return 9;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ReviewViewHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
           cardView = view.findViewById(R.id.cardviewtabhome);
        }
    }
}
