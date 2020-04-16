package app.codelabs.forum.activities.event.adapter;

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
import app.codelabs.forum.models.ResponsParticipantEvent;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterParticipant extends RecyclerView.Adapter<AdapterParticipant.MyHolder> {
    private  Context context;
    private List<ResponsParticipantEvent.DataEntity> data;

    public AdapterParticipant(){
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
       View view= LayoutInflater.from(context).inflate(R.layout.item_participant,parent,false);
       return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipant.MyHolder holder, int position) {
        ResponsParticipantEvent.DataEntity datas = data.get(position);
        holder.txtname.setText(datas.getUsername());
        holder.txtfollowes.setText(String.valueOf(datas.getFollowers()));
        Picasso.with(context).load(datas.getPhoto()).fit().into(holder.imgparticipant);


    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }
    public void setItems(List<ResponsParticipantEvent.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponsParticipantEvent.DataEntity> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView txtname,txtfollowes;
        CircleImageView imgparticipant;
        public MyHolder(@NonNull View view) {
            super(view);


            setView(view);
            setEvent();

        }

        private void setEvent() {

        }

        private void setView(View view) {
            txtname = view.findViewById(R.id.txtname);
            txtfollowes = view.findViewById(R.id.txt_follow_participant);
            imgparticipant = view.findViewById(R.id.img_participan);
        }
    }


}
