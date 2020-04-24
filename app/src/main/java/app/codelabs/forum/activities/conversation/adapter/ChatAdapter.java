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
import app.codelabs.forum.models.ResponseListChatInRoom;
import app.codelabs.forum.models.ResponseRoomChatDetail;
import app.codelabs.forum.models.SocketChatSendMessageToRoom;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_LEFT_CHAT = 0;
    public static final int VIEW_TYPE_RIGHT_CHAT = 1;

    private List<ResponseListChatInRoom.ChatEntity> items;
    private Context context;
    private int ownerId;
    private List<ResponseRoomChatDetail.MembersEntity> members;

    public ChatAdapter() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
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
        if (items.get(position).getAuthor_id() == ownerId) {
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
        ResponseListChatInRoom.ChatEntity item = items.get(position);
        rightSide.tvMessage.setText(item.getContent());
        rightSide.tvTime.setText(item.getTime());
    }

    private void onBindViewLeftHolder(RecyclerView.ViewHolder holder, int position) {
        LeftSideChatViewHolder leftSide = (LeftSideChatViewHolder) holder;
        ResponseListChatInRoom.ChatEntity item = items.get(position);

        leftSide.tvMessage.setText(item.getContent());
        leftSide.tvTime.setText(item.getTime());
        leftSide.tvUser.setText("{username}");
        if (members.size() > 0) {
            for (ResponseRoomChatDetail.MembersEntity member : members) {
                if (member.getMember_id() == item.getAuthor_id()) {
                    leftSide.tvUser.setText(member.getMetadata().getMeta_member().getName());
                    break;
                }
            }
        }
    }

    public void setItems(List<ResponseListChatInRoom.ChatEntity> items) {
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

    public void addAllItems(List<ResponseListChatInRoom.ChatEntity> data) {
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(SocketChatSendMessageToRoom data) {
        ResponseListChatInRoom.ChatEntity chat = new ResponseListChatInRoom.ChatEntity();

        chat.setAuthor_id(data.getAuthor_id());
        chat.setRoom_id(data.getRoom_id());
        chat.setType(data.getType());
        chat.setContent(data.getContent());
        chat.setTime("Baru aja");

        this.items.add(0, chat);
        notifyDataSetChanged();
    }

    public void setRoomMember(List<ResponseRoomChatDetail.MembersEntity> members) {
        this.members = members;
    }

    class LeftSideChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime, tvUser;

        public LeftSideChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message_left_side);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }


    class RightSideChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime, tvUser;

        public RightSideChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message_right_side);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }
}
