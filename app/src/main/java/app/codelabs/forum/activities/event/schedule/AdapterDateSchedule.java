package app.codelabs.forum.activities.event.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.club.gallery.GalleryAdapter;
import app.codelabs.forum.activities.club.gallery.GalleryNameAdapter;

public class AdapterDateSchedule extends RecyclerView.Adapter<AdapterDateSchedule.MyHolder> {

    private Context context;
    public AdapterSchedule adapterSchedule;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_date_schedule, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        adapterSchedule = new AdapterSchedule();
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapterSchedule);

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;

        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
        }

        private void setView(View view) {
            recyclerView = view.findViewById(R.id.rv_schedule);
        }
    }
}
