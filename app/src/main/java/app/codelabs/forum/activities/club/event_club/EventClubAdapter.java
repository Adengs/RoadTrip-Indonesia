package app.codelabs.forum.activities.club.event_club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

import static android.graphics.Color.rgb;

public class EventClubAdapter extends RecyclerView.Adapter<EventClubAdapter.MyHolder> {
    private Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_club, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView txtJoin, txtDojoin;

        public MyHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            txtJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtDojoin.setText("Congratulations!");
                    txtJoin.setText("Joined");
                    txtJoin.setTextColor(rgb(255, 255, 255));
                    txtJoin.setBackgroundColor(ContextCompat.getColor(context, R.color.merah));
                }
            });
        }

        private void setView(View view) {
            txtJoin = view.findViewById(R.id.txtjoin);
            txtDojoin = view.findViewById(R.id.txt_do_join);
        }
    }
}
