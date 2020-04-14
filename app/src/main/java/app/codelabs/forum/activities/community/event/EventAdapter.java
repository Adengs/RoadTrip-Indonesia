package app.codelabs.forum.activities.community.event;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.models.ResponsListEventCommunity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private Context context;
    private List<ResponsListEventCommunity.DataEntity> items;
    public OnItemSelection listener;

    public EventAdapter() {
        items = new ArrayList<>();
    }


    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_club, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        final ResponsListEventCommunity.DataEntity item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvStartEvent.setText(item.getEvent_start());
        holder.tvEndEvent.setText(item.getEvent_end());
        holder.tvStartEvent.setText(item.getEvent_start());
        holder.tvEndEvent.setText(item.getEvent_end());
        Picasso.with(context).load(item.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivEvent);

        if (item.getIs_join() == true) {
            holder.tvJoin.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvJoin.setText("Joined");
            holder.tvJoin.setBackgroundResource(R.drawable.shape_button_follow);
            holder.tvDoJoin.setText("You are join this event");
        } else {
            holder.tvDoJoin.setText("Do you want to join?");
            holder.tvJoin.setText("Join");
            holder.tvJoin.setTextColor(Color.parseColor("#F62C4C"));
            holder.tvJoin.setBackgroundResource(R.drawable.shape_car_club);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponsListEventCommunity.DataEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponsListEventCommunity.DataEntity> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setListener(OnItemSelection listener) {
        this.listener = listener;
    }


    public class EventHolder extends RecyclerView.ViewHolder {
        private TextView tvJoin, tvTitle, tvStartEvent, tvEndEvent, tvDoJoin;
        private ImageView ivEvent;


        public EventHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListEventCommunity.DataEntity item = items.get(getAdapterPosition());
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("data", new Gson().toJson(item));
                    context.startActivity(intent);
                }
            });
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListEventCommunity.DataEntity data = items.get(getAdapterPosition());
                    listener.onBtnJoin(data);
                }
            });
        }

        private void setView(View view) {
            tvJoin = view.findViewById(R.id.tv_join);
            tvDoJoin = view.findViewById(R.id.tv_do_join);
            tvTitle = view.findViewById(R.id.tv_title);
            tvStartEvent = view.findViewById(R.id.tv_start_event);
            tvEndEvent = view.findViewById(R.id.tv_end_event);
            ivEvent = view.findViewById(R.id.img_event);
        }

    }

    public interface OnItemSelection {
        void onBtnJoin(ResponsListEventCommunity.DataEntity item);
    }
}
