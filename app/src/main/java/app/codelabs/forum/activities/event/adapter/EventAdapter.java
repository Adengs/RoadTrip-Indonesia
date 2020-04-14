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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventVH> {
    private Context context;
    private Boolean is_join;
    private List<ResponsMyEvent.DataEntity>data;

    public EventAdapter(){
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event,parent,false);
        return new EventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        final ResponsMyEvent.DataEntity datas = data.get(position);
        holder.txttitle.setText(datas.getTitle());
        holder.txttglmulai.setText(datas.getEvent_start());
        holder.txttglberakhir.setText(datas.getEvent_end());
        Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(holder.imgEventCars);
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

    public class EventVH extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgEventCars;
        TextView txttitle,txttglmulai,txttglberakhir;

        public EventVH(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();

        }

        private void setEvent() {
              itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                           public void onClick(View v) {
                                ResponsMyEvent.DataEntity item = data.get(getAdapterPosition());
                                Intent intent = new Intent(context, EventActivity.class);
                                intent.putExtra("data", new Gson().toJson(item));
                                intent.putExtra("is_join",true);
                               context.startActivity(intent);
                           }
                       });

        }

        private void setView(View view) {
            cardView = view.findViewById(R.id.cardviewevent);
            imgEventCars = view.findViewById(R.id.imgListEvent);
            txttitle = view.findViewById(R.id.txttitleEvent);
            txttglmulai = view.findViewById(R.id.txt_tgl_Myevent_mulai);
            txttglberakhir = view.findViewById(R.id.txt_tgl_Myevent_akhir);
        }
    }
}
