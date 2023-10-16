package app.codelabs.roadtrip.activities.questioner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.models.ResponseAnswerQuestioner;
import app.codelabs.roadtrip.R;

public class QuestionResultAdapter extends RecyclerView.Adapter<QuestionResultAdapter.QuestionResultVH> {
    private Context context;
    private List<ResponseAnswerQuestioner.DetailsEntity> items = new ArrayList<>();

    @NonNull
    @Override
    public QuestionResultVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new QuestionResultVH(LayoutInflater.from(context).inflate(R.layout.item_question_result, parent, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionResultVH holder, int position) {
        ResponseAnswerQuestioner.DetailsEntity item = items.get(position);
        if (item.isHigher()) {
            holder.container.setBackgroundResource(R.drawable.shape_rounded_border_question_result_correct);
            holder.tvQuestion.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            holder.tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            holder.tvPercentage.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        } else {
            holder.container.setBackgroundResource(R.drawable.shape_rounded_border_question);
            holder.tvQuestion.setTextColor(ContextCompat.getColor(context, R.color.colorText));
            holder.tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.texttablayout));
            holder.tvPercentage.setTextColor(ContextCompat.getColor(context, R.color.texttablayout));
        }

        holder.tvQuestion.setText("Question " + (position + 1));
        holder.tvAnswer.setText(item.getChoice());
        holder.tvPercentage.setText(item.getPercentageChoice() + "%");
    }

    public void setItems(List<ResponseAnswerQuestioner.DetailsEntity> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    class QuestionResultVH extends RecyclerView.ViewHolder {
        private FrameLayout container;
        private TextView tvQuestion, tvAnswer, tvPercentage;

        public QuestionResultVH(@NonNull View itemView) {
            super(itemView);
            tvPercentage = itemView.findViewById(R.id.tv_percentage);
            container = itemView.findViewById(R.id.container);
            tvQuestion = itemView.findViewById(R.id.tv_lbl_question);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
        }
    }
}
