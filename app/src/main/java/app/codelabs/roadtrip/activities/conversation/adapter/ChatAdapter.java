package app.codelabs.roadtrip.activities.conversation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.roadtrip.helpers.DateTimeHelper;
import app.codelabs.roadtrip.models.ResponseListChatInRoom;
import app.codelabs.roadtrip.models.ResponseRoomChatDetail;
import app.codelabs.roadtrip.models.SocketChatSendMessageToRoom;
import app.codelabs.roadtrip.R;

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
        if (item.isSocket()) {
            rightSide.tvTime.setText(DateTimeHelper.instance(item.getTime()).get("HH:mm"));
        } else {
            rightSide.tvTime.setText(item.getTime());
        }

    }

    private void onBindViewLeftHolder(RecyclerView.ViewHolder holder, int position) {
        LeftSideChatViewHolder leftSide = (LeftSideChatViewHolder) holder;
        ResponseListChatInRoom.ChatEntity item = items.get(position);

        leftSide.tvMessage.setText(item.getContent());
        if (item.isSocket()) {
            leftSide.tvTime.setText(DateTimeHelper.instance(item.getTime()).get("HH:mm"));
        } else {
            leftSide.tvTime.setText(item.getTime());
        }
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

        ResponseRoomChatDetail.MembersEntity member = getMember(data);
        addMember(member);

        chat.setAuthor_id(data.getAuthor_id());
        chat.setRoom_id(data.getRoom_id());
        chat.setType(data.getType());
        chat.setContent(data.getContent());
        chat.setTime(DateTimeHelper.instance(new Date(data.getTime())).get(DateTimeHelper.PATTERN_DEFAULT));
        chat.setIsSocket(true);
        this.items.add(0, chat);
        notifyDataSetChanged();
    }

    private ResponseRoomChatDetail.MembersEntity getMember(SocketChatSendMessageToRoom data) {
        ResponseRoomChatDetail.MembersEntity member = new ResponseRoomChatDetail.MembersEntity();
        new ResponseRoomChatDetail.MembersEntity();
        member.setMember_id(data.getAuthor_id());
        ResponseRoomChatDetail.MetadataEntity memberMetaData = new ResponseRoomChatDetail.MetadataEntity();
        memberMetaData.setMeta_member(new ResponseRoomChatDetail.Meta_memberEntity(data.getAuthor_name()));
        member.setMetadata(memberMetaData);
        return member;
    }

    public void addMember(ResponseRoomChatDetail.MembersEntity member) {
        boolean isExist = false;
        for (ResponseRoomChatDetail.MembersEntity item : members) {
            if (item.getMember_id() == member.getMember_id()) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            members.add(member);
            notifyDataSetChanged();
        }
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
