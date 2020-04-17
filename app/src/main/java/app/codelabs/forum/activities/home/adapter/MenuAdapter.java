package app.codelabs.forum.activities.home.adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.models.HomeMenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private List<HomeMenuItem> items = new ArrayList<>();

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.home_item_grid, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        HomeMenuItem item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.ivIcon.setImageResource(item.getIcon());
    }

    public void setItems(List<HomeMenuItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;

        MenuViewHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setView(View view) {
            ivIcon = view.findViewById(R.id.ivIcon);
            tvName = view.findViewById(R.id.tvName);
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeMenuItem item = items.get(getAdapterPosition());
                    Intent intent = new Intent(context, item.getActivity());
                    context.startActivity(intent);
                }
            });
        }

    }

}
