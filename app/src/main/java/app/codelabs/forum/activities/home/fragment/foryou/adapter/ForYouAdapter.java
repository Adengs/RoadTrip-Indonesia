package app.codelabs.forum.activities.home.fragment.foryou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.latest_popular_foryou.CardViewActivity;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ForYouViewHolder> {
    List<Fragment> items = new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public ForYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ForYouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYouViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardViewActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ForYouViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ForYouViewHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewtabhome);
        }
    }
}
