package app.codelabs.forum.activities.event.participant;

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

public class AdapterParticipant extends RecyclerView.Adapter<AdapterParticipant.MyHolder> {
    private static Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
       View view= LayoutInflater.from(context).inflate(R.layout.item_participant,parent,false);
       return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipant.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView txtJoin;
        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();

        }

        private void setEvent() {
            txtJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtJoin.setText("Joined");
                    txtJoin.setTextColor(Color.rgb(255,255,255));
                    txtJoin.setBackgroundColor(ContextCompat.getColor(context,R.color.pink));
                }
            });
        }

        private void setView(View view) {
            txtJoin=view.findViewById(R.id.txtJoined);
        }
    }


}
