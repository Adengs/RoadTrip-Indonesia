package app.codelabs.forum.activities.event.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseSchedule;

public class ItemScheduleAdapter extends RecyclerView.Adapter<ItemScheduleAdapter.MyHolder> {
    private Context context;
    private List<ResponseSchedule.SchedulesEntity> items = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ResponseSchedule.SchedulesEntity item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvNumber.setText("" + (position + 1));
        holder.tvDesc.setText(Html.fromHtml(item.getDescription()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseSchedule.SchedulesEntity> schedules) {
        this.items = schedules;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvMap, tvNumber, tvTitle, tvDesc;

        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();
        }

        private void setEvent() {
            tvMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseSchedule.SchedulesEntity item = items.get(getAdapterPosition());
                    String url_map = "https://www.google.com/maps/search/?api=1&query=";
                    Uri gmmIntentUri = Uri.parse( url_map +item.getLatitude()+","+item.getLongitude());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });
        }

        private void setView(View view) {
            tvMap = view.findViewById(R.id.tv_map);
            tvNumber = view.findViewById(R.id.tv_number);
            tvTitle = view.findViewById(R.id.tv_title);
            tvDesc = view.findViewById(R.id.tv_desc);
        }
    }
}
