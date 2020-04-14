package app.codelabs.forum.activities.event.participant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseParticipantEvent;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterParticipant extends RecyclerView.Adapter<AdapterParticipant.ParticipantHolder> {
    private  Context context;
    private List<ResponseParticipantEvent.DataEntity> item;

    public AdapterParticipant(){
        item = new ArrayList<>();
    }

    @NonNull
    @Override
    public ParticipantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
       View view= LayoutInflater.from(context).inflate(R.layout.item_participant,parent,false);
       return new ParticipantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantHolder holder, int position) {
        ResponseParticipantEvent.DataEntity items= item.get(position);
        holder.tvName.setText(items.getUsername());
        holder.tvfollowes.setText(String.valueOf(items.getFollowers()));
        Picasso.with(context).load(items.getPhoto()).fit().into(holder.ivParticipant);
    }

    @Override
    public int getItemCount() {
        return item.size() ;
    }
    public void setItems(List<ResponseParticipantEvent.DataEntity> items) {
        this.item = items;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponseParticipantEvent.DataEntity> items) {
        this.item.addAll(items);
        notifyDataSetChanged();
    }

    public class ParticipantHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvfollowes;
        CircleImageView ivParticipant;

        public ParticipantHolder(@NonNull View view) {
            super(view);

            setView(view);

        }
        private void setView(View view) {
            tvName = view.findViewById(R.id.tv_Name);
            tvfollowes = view.findViewById(R.id.tv_Followers);
            ivParticipant = view.findViewById(R.id.iv_Image);
        }
    }
}
