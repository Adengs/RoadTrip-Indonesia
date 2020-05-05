package app.codelabs.forum.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 15:58
 */
public class SocketChatSendMessageToRoom extends SocketModel {
    @SerializedName("author_id")
    private int author_id;
    @SerializedName("role")
    private int role = 1;
    @SerializedName("room_id")
    private int room_id;
    @SerializedName("content")
    private String content;
    @SerializedName("type")
    private int type = 1;

    public static SocketChatSendMessageToRoom get(String json) {
        return new Gson().fromJson(json,SocketChatSendMessageToRoom.class);
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
