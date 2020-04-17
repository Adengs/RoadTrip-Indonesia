package app.codelabs.forum.activities.event.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseSchedule;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyHolder> {

    private Context context;
    private List<ResponseSchedule.DataEntity> items = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_date_schedule, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvDate.setText(items.get(position).getDate());
        ItemScheduleAdapter adapterSchedule = new ItemScheduleAdapter();
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapterSchedule);
        adapterSchedule.setItems(items.get(position).getSchedules());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseSchedule.DataEntity> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public TextView tvDate;

        public MyHolder(@NonNull View view) {
            super(view);
            setView(view);
        }

        private void setView(View view) {
            recyclerView = view.findViewById(R.id.rv_schedule);
            tvDate = view.findViewById(R.id.tv_date);
        }
    }
}
