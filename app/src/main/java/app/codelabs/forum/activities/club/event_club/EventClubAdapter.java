package app.codelabs.forum.activities.club.event_club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;

public class EventClubAdapter extends RecyclerView.Adapter<EventClubAdapter.MyHolder> {
    private Context context;
    public static OnItemSelection listener;


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
    public void setListener (OnItemSelection listener){
        this.listener = listener ;
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
                    listener.onBtnJoin();
                }
            });
        }

        private void setView(View view) {
            txtJoin = view.findViewById(R.id.txtjoin);
        }

    }

    public interface OnItemSelection{
        void onBtnJoin();

    }


}
