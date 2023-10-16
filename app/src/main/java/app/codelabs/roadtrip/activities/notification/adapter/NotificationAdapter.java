package app.codelabs.roadtrip.activities.notification.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.notification.NotificationDetailActivity;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    ArrayList<Fragment> items = new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_notifivation_home, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NotificationDetailActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public NotificationViewHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewnotification);
        }
    }
}
