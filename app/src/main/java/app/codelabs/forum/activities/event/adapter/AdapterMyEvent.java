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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.models.ResponsMyEvent;

public class AdapterMyEvent extends RecyclerView.Adapter<AdapterMyEvent.MyEventHolder> {
    private Context context;
    private Boolean is_join;
    private List<ResponsMyEvent.DataEntity> item;

    public AdapterMyEvent(){
        item = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event,parent,false);
        return new MyEventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyEventHolder holder, int position) {
        final ResponsMyEvent.DataEntity items = item.get(position);

        holder.tvtitle.setText(items.getTitle());
        holder.tvStartEvent.setText(items.getEvent_start());
        holder.tvEndEvent.setText(items.getEvent_end());
        Picasso.with(context).load(items.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivImage);
    }


    @Override
    public int getItemCount() {
        return item.size();
    }
    public void setItems(List<ResponsMyEvent.DataEntity> datas) {
        this.item = datas;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponsMyEvent.DataEntity> datas) {
        this.item.addAll(datas);
        notifyDataSetChanged();
    }

    public class MyEventHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView ivImage;
        private TextView tvtitle,tvStartEvent,tvEndEvent;

        public MyEventHolder(View view) {
            super(view);

            setView(view);
            setEvent();
        }
        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsMyEvent.DataEntity items = item.get(getAdapterPosition());
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("data", new Gson().toJson(items));
                    intent.putExtra("is_join",true);
                    context.startActivity(intent);
                }
            });

        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewevent);
            ivImage = view.findViewById(R.id.ivImage);
            tvtitle = view.findViewById(R.id.tv_Title);
            tvStartEvent = view.findViewById(R.id.tv_Start_Event);
            tvEndEvent = view.findViewById(R.id.tv_End_Event);
        }
    }
}
