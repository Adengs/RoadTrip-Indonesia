package app.codelabs.fevci.activities.gallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.fevci.models.ResponseGallery;

public class EventGalleryAdapter extends RecyclerView.Adapter<EventGalleryAdapter.EventGalleryVH> {
    private Context context;
    private List<ResponseGallery.Gallery> items = new ArrayList<>();

    @NonNull
    @Override
    public EventGalleryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_gallery, parent, false);
        return new EventGalleryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventGalleryVH holder, int position) {
        ResponseGallery.Gallery item = items.get(position);

        holder.tvEventName.setText(item.getEvent_name());
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        holder.recyclerView.setAdapter(new ImageGalleryAdapter(item.getPhotos()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseGallery.Gallery> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class EventGalleryVH extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView tvEventName;

        EventGalleryVH(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            recyclerView = view.findViewById(R.id.recyclerview);
            tvEventName = view.findViewById(R.id.tv_event_name);
        }
    }
}
