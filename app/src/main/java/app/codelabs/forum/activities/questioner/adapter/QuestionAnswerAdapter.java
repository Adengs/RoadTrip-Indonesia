package app.codelabs.forum.activities.questioner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseQuestion;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.QuestionAnswerVH> {
    private Context context;
    private int questionIndex;
    private OnAnswerSelected callback;
    private List<ResponseQuestion.QuestionAnswerEntity> items = new ArrayList<>();

    @NonNull
    @Override
    public QuestionAnswerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new QuestionAnswerVH(LayoutInflater.from(context).inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAnswerVH holder, int position) {
        ResponseQuestion.QuestionAnswerEntity item = items.get(position);
        holder.tvName.setText(item.getChoice());
        if (item.isSelected()) {
            holder.tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_check_circle,0);
        } else {
            holder.tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_circle_outline,0);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setCallback(OnAnswerSelected callback) {
        this.callback = callback;
    }

    public void setItems(List<ResponseQuestion.QuestionAnswerEntity> data, int questionIndex) {
        this.items = data;
        this.questionIndex = questionIndex;
        notifyDataSetChanged();
    }

    class QuestionAnswerVH extends RecyclerView.ViewHolder {
        private TextView tvName;

        public QuestionAnswerVH(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (ResponseQuestion.QuestionAnswerEntity item : items) {
                        item.setSelected(false);
                    }
                    items.get(getAdapterPosition()).setSelected(true);
                    if (callback != null) {
                        callback.onSelected(items.get(getAdapterPosition()), questionIndex);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        private void setView(View view) {
            tvName = view.findViewById(R.id.tv_name);
        }
    }

    public interface OnAnswerSelected {
        void onSelected(ResponseQuestion.QuestionAnswerEntity item, int questionIndex);
    }
}
