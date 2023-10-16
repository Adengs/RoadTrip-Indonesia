package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 13:38
 */
public class SocketChatJoinRoom extends SocketModel {
    @SerializedName("room_id")
    private int room_id;
    @SerializedName("author_id")
    private int author_id;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
