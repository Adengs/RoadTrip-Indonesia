package app.codelabs.forum.activities.conversation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseChatRoomList;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyHolder> {
    private Context context;
    private List<ResponseChatRoomList.DataEntity> items = new ArrayList<>();
    private Listener listener;

    @NonNull
    @Override
    public ConversationAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_conversation, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationAdapter.MyHolder holder, int position) {
        ResponseChatRoomList.DataEntity item = items.get(position);
        holder.tvTitle.setText(item.getRoom().getTitle());
        if(item.getRoom().getLatest_chat() != null) {
            holder.tvLatestChat.setText(item.getRoom().getLatest_chat().getContent());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ResponseChatRoomList.DataEntity> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLatestChat;
        ImageView ivLogo;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRoomClick(items.get(getAdapterPosition()));
                }
            });
        }

        private void setView(View itemView) {
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLatestChat = itemView.findViewById(R.id.tv_latest_chat);
            ivLogo = itemView.findViewById(R.id.iv_logo);
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onRoomClick(ResponseChatRoomList.DataEntity item);
    }
}
