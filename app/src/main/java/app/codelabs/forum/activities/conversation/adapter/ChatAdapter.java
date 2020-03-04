package app.codelabs.forum.activities.conversation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static final int VIEW_TYPE_LEFT_CHAT = 0;
    public static final int VIEW_TYPE_RIGHT_CHAT = 1;

    List<Chat> items;
    Context context;
    int ownerId;

    public ChatAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == VIEW_TYPE_LEFT_CHAT) {
            return new LeftSideChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_left, parent, false));
        } else if (viewType == VIEW_TYPE_RIGHT_CHAT) {
            return new RightSideChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_right, parent, false));

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getUserId() == ownerId) {
            return VIEW_TYPE_RIGHT_CHAT;
        } else {
            return VIEW_TYPE_LEFT_CHAT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_LEFT_CHAT) {

            onBindViewLeftHolder(holder, position);

        } else if (holder.getItemViewType() == VIEW_TYPE_RIGHT_CHAT) {

            onBindViewRightHolder(holder, position);
        }
    }

    private void onBindViewRightHolder(RecyclerView.ViewHolder holder, int position) {
        RightSideChatViewHolder rightSide = (RightSideChatViewHolder) holder;
        rightSide.tv_message.setText(items.get(position).getMessage());
    }

    private void onBindViewLeftHolder(RecyclerView.ViewHolder holder, int position) {
        LeftSideChatViewHolder leftSide = (LeftSideChatViewHolder) holder;
        leftSide.tv_message.setText(items.get(position).getMessage());
    }

    public void setItems(List<Chat> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class LeftSideChatViewHolder extends RecyclerView.ViewHolder {
        TextView tv_message;

        public LeftSideChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_message = itemView.findViewById(R.id.tv_message_left_side);
        }
    }


    class RightSideChatViewHolder extends RecyclerView.ViewHolder {
        TextView tv_message;

        public RightSideChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_message = itemView.findViewById(R.id.tv_message_right_side);

        }
    }
}
