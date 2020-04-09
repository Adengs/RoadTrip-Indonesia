package app.codelabs.forum.activities.club.event_club;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.club.member.MemberAdapter;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponseListArticle;
import retrofit2.Callback;

public class EventClubAdapter extends RecyclerView.Adapter<EventClubAdapter.MyHolder> {
    private Context context;
    private  List<ResponsListEventCommunity.DataEntity> data;
    public  OnItemSelection listener;

    public EventClubAdapter(){
        data = new ArrayList<>();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_club, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ResponsListEventCommunity.DataEntity datas = data.get(position);
        holder.tvtitle.setText(datas.getTitle());
        holder.tvStartEvent.setText(datas.getEvent_start());
        holder.tvEndEvent.setText(datas.getEvent_end());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.ivEvent);
        if (datas.getIs_join() == true) {
            holder.llJoin.setVisibility(View.GONE);
            holder.tvDojoin.setTextColor(Color.parseColor("#F62C4C"));
            holder.tvDojoin.setText("You are join this event");
        }else {
            holder.llJoin.setVisibility(View.VISIBLE);
            holder.tvDojoin.setText("Do you want to join?");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setItems(List<ResponsListEventCommunity.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponsListEventCommunity.DataEntity> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }
    public void setListener (OnItemSelection listener){
        this.listener = listener ;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvJoin,tvtitle, tvStartEvent, tvEndEvent,tvDojoin;
        private LinearLayout llJoin;
        private ImageView ivEvent;


        public MyHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListEventCommunity.DataEntity item = data.get(getAdapterPosition());
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("data", new Gson().toJson(item));
                    context.startActivity(intent);
                }
            });
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListEventCommunity.DataEntity datas = data.get(getAdapterPosition());
                    listener.onBtnJoin(datas);
                }
            });
        }

        private void setView(View view) {
            llJoin = view.findViewById(R.id.llJoin);
            tvJoin = view.findViewById(R.id.tvjoin);
            tvDojoin = view.findViewById(R.id.tv_do_join);
            tvtitle = view.findViewById(R.id.tvTitle);
            tvStartEvent = view.findViewById(R.id.tvStartEvent);
            tvEndEvent = view.findViewById(R.id.tvEndEvent);
            ivEvent = view.findViewById(R.id.img_event);
        }

    }
    public interface OnItemSelection{
        void onBtnJoin(ResponsListEventCommunity.DataEntity datas);
    }


}
