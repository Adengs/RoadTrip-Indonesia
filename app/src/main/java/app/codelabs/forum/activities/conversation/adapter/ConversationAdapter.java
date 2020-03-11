package app.codelabs.forum.activities.conversation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyHolder> {
    private Context context;
    List<Fragment> items = new ArrayList<>();
    @NonNull
    @Override
    public ConversationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_conversation,parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
