package app.codelabs.forum.activities.club.member;

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

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyHolder> {
    private Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_member_club, parent, false);
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
        private TextView txtfollow;

        public MyHolder(@NonNull View view) {
            super(view);

            setView(view);
            setEvent();

        }

        private void setEvent() {
            txtfollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtfollow.setText("Following");
                    txtfollow.setTextColor(rgb(255, 255, 255));
                    txtfollow.setBackgroundColor(ContextCompat.getColor(context, R.color.merah));
                }
            });
        }

        private void setView(View view) {
            txtfollow = view.findViewById(R.id.txtfollow);
        }
    }


}
