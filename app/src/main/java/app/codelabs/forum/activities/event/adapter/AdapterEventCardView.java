package app.codelabs.forum.activities.event.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsMyEvent;

public class AdapterEventCardView extends RecyclerView.Adapter<AdapterEventCardView.MyHolder> {
    Context context;
    private List<ResponsMyEvent.DataEntity>data;

    public AdapterEventCardView(){
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ResponsMyEvent.DataEntity datas = data.get(position);
        holder.txttitle.setText(datas.getEvent_title());
        holder.txttglmulai.setText(datas.getEvent_start());
        holder.txttglberakhir.setText(datas.getEvent_end());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.imgEventCars);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("event_id",datas.getEvent_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setItems(List<ResponsMyEvent.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }
    public void addItems(List<ResponsMyEvent.DataEntity> datas) {
        this.data.addAll(datas);
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgEventCars;
        TextView txttitle,txttglmulai,txttglberakhir;

        public MyHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.cardviewevent);
            imgEventCars = view.findViewById(R.id.imgListEvent);
            txttitle = view.findViewById(R.id.txttitleEvent);
            txttglmulai = view.findViewById(R.id.txt_tgl_Myevent_mulai);
            txttglberakhir = view.findViewById(R.id.txt_tgl_Myevent_akhir);
        }
    }
}
