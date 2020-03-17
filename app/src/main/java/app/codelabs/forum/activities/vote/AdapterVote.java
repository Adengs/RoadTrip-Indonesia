package app.codelabs.forum.activities.vote;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.models.Vote;

public class AdapterVote extends RecyclerView.Adapter<AdapterVote.MyHolder> {
    List<Vote> items = new ArrayList<>();
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    private static Context context;

    public void setItems(List<Vote> items) {

        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_vote, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        /*if (items.get(position).getRbVote()){
            holder.rbVote.setChecked(true);
        }else {
            holder.rbVote.setChecked(false);
        }*/


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView txtImage;
        TextView txtName;
        RadioButton rbVote;
        RadioGroup rgVote;

        public MyHolder(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Vote item : items) {
                    item.setRbVote(false);
                }
                if (items.get(getAdapterPosition()).getRbVote()) {
                    items.get(getAdapterPosition()).setRbVote(false);

                }else {
                    items.get(getAdapterPosition()).setRbVote(true);
                }

                notifyDataSetChanged();

            }
        });

        }

        private void setView(View view) {
            rbVote = view.findViewById(R.id.rb_selectVote);
            rgVote =view.findViewById(R.id.rgroup_vote);
            txtImage = view.findViewById(R.id.txtImageVote);
            txtName = view.findViewById(R.id.txtnamevote);

        }
    }
}
