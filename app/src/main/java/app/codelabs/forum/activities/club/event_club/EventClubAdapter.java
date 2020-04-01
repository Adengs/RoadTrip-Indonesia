package app.codelabs.forum.activities.club.event_club;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        final ResponsListEventCommunity.DataEntity datas =data.get(position);
        holder.tvtitle.setText(datas.getTitle());
        holder.tvStartEvent.setText(datas.getEvent_start());
        holder.tvEndEvent.setText(datas.getEvent_end());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.ivEvent);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("event_id",datas.getId());
                context.startActivity(intent);
            }
        });

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
        private TextView tvJoin,tvtitle, tvStartEvent, tvEndEvent;
        private ImageView ivEvent;


        public MyHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListEventCommunity.DataEntity datas = data.get(getAdapterPosition());
                    listener.onBtnJoin(datas);
                }
            });
        }

        private void setView(View view) {
            tvJoin = view.findViewById(R.id.tvjoin);
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
