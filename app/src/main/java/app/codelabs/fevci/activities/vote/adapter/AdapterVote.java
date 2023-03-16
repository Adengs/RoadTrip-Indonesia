package app.codelabs.fevci.activities.vote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.fevci.models.ResponseVote;
import app.codelabs.forum.R;

public class AdapterVote extends RecyclerView.Adapter<AdapterVote.AdapterVH> {
    private List<ResponseVote.CandidateEntity> items = new ArrayList<>();
    private Context context;
    private ResponseVote.CandidateEntity selectedCandidate = null;
    private boolean isAlreadyVote = false;

    public void setItems(List<ResponseVote.CandidateEntity> items, boolean isAlreadyVote) {
        this.items = items;
        this.isAlreadyVote = isAlreadyVote;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_vote, parent, false);
        return new AdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVH holder, int position) {
        ResponseVote.CandidateEntity item = items.get(position);
        Picasso.with(context).load(item.getUser_photo())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop()
                .into(holder.ivPhoto);
        holder.tvName.setText(item.getUser_name());

        if (isAlreadyVote) {
            holder.ivSelection.setVisibility(View.GONE);
        }

        if (item.isSelect()) {
            holder.ivSelection.setImageResource(R.drawable.ic_radio_button_checked);
        } else {
            holder.ivSelection.setImageResource(R.drawable.ic_circle_outline);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class AdapterVH extends RecyclerView.ViewHolder {
        ImageView ivPhoto, ivSelection;
        TextView tvName;

        public AdapterVH(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isAlreadyVote) {
                        for (ResponseVote.CandidateEntity item : items) {
                            item.setSelect(false);
                        }
                        items.get(getAdapterPosition()).setSelect(true);
                        selectedCandidate = items.get(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });
        }

        private void setView(View view) {
            tvName = view.findViewById(R.id.tv_name);
            ivPhoto = view.findViewById(R.id.iv_photo);
            ivSelection = view.findViewById(R.id.iv_selection);
        }
    }

    public ResponseVote.CandidateEntity getSelectedCandidate() {
        return selectedCandidate;
    }
}
