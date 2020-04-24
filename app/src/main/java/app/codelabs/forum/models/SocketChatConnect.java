package app.codelabs.forum.models;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 17 Apr 2020, 12:32
 */
public class SocketChatConnect extends SocketModel {
    @SerializedName("id")
    private int id;
    @SerializedName("role")
    private int role;

    public SocketChatConnect(int id) {
        this.id = id;
        this.role = 1;
    }

    public SocketChatConnect() {
        this.role = 1;
    }

    public SocketChatConnect(int id, int role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
