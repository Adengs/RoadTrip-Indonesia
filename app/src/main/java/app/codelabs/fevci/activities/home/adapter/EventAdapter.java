package app.codelabs.fevci.activities.home.adapter;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.fevci.activities.event.DetailEventActivity;
import app.codelabs.fevci.models.ResponseListEventCommunity;
import app.codelabs.forum.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventVH> {
    private Context context;
    private List<ResponseListEventCommunity.DataEntity> items;
    public OnItemSelection listener;
    public Fragment fragment;

    public EventAdapter(Fragment fragment) {
        this.fragment = fragment;
        items = new ArrayList<>();
    }


    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_club, parent, false);
        return new EventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        final ResponseListEventCommunity.DataEntity item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvStartEvent.setText(item.getEvent_start());
        holder.tvEndEvent.setText(item.getEvent_end());
        holder.tvStartEvent.setText(item.getEvent_start());
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

    public void setItemByIndex(ResponseListEventCommunity.DataEntity item, int index) {
        this.items.set(index, item);
        notifyDataSetChanged();
    }

    public void setItems(List<ResponseListEventCommunity.DataEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<ResponseListEventCommunity.DataEntity> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setListener(OnItemSelection listener) {
        this.listener = listener;
    }


    public class EventVH extends RecyclerView.ViewHolder {
        private TextView tvJoin, tvTitle, tvStartEvent, tvEndEvent, tvDoJoin;
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
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListEventCommunity.DataEntity data = items.get(getAdapterPosition());
                    listener.onBtnJoin(data, getAdapterPosition());
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
        void onBtnJoin(ResponseListEventCommunity.DataEntity item, int index);
    }
}
