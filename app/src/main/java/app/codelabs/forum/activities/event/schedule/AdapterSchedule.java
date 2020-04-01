package app.codelabs.forum.activities.event.schedule;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.participant.AdapterParticipant;

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.MyHolder> {
    private Context context;


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtMaps;
        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();
        }

        private void setEvent() {
            txtMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtMaps.setText("Maps");
                    txtMaps.setTextColor(Color.rgb(255,255,255));
                    txtMaps.setBackgroundColor(ContextCompat.getColor(context,R.color.pink));
                }
            });
        }

        private void setView(View view) {
            txtMaps=view.findViewById(R.id.txtMapsShcedule);
    }
}
}
