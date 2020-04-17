package app.codelabs.forum.activities.event.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.DetailEventActivity;
import app.codelabs.forum.models.ResponseListEventCommunity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventVH> {
    private final Fragment fragment;
    private Context context;
    private List<ResponseListEventCommunity.DataEntity> items;

    public EventAdapter(Fragment fragment) {
        items = new ArrayList<>();
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        final ResponseListEventCommunity.DataEntity data = items.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvStartEvent.setText(data.getEvent_start());
        holder.tvEndEvent.setText(data.getEvent_end());

        Picasso.with(context).load(data.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivEvent);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseListEventCommunity.DataEntity> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponseListEventCommunity.DataEntity> data) {
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemByIndex(ResponseListEventCommunity.DataEntity item, int index) {
        this.items.set(index, item);
        notifyDataSetChanged();
    }

    public void removeItemByIndex(int index) {
        items.remove(index);
        notifyDataSetChanged();
    }

    public class EventVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvStartEvent, tvEndEvent;
        private ImageView ivEvent;

        public EventVH(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListEventCommunity.DataEntity item = items.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailEventActivity.class);
                    intent.putExtra("data", new Gson().toJson(item));
                    intent.putExtra("index", getAdapterPosition());
                    fragment.startActivityForResult(intent, DetailEventActivity.REQ_REFRESH_EVENT);
                }
            });

        }

        private void setView(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            tvStartEvent = view.findViewById(R.id.tv_start_event);
            tvEndEvent = view.findViewById(R.id.tv_end_event);
            ivEvent = view.findViewById(R.id.img_event);
        }
    }
}
