package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 15:10
 */
public class ResponseListChatInRoom {
    @SerializedName("DATA")
    private List<ChatEntity> data;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("STATUS")
    private int status;

    public List<ChatEntity> getData() {
        return data;
    }

    public void setData(List<ChatEntity> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ChatEntity {
        @SerializedName("time")
        private String time;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("type")
        private int type;
        @SerializedName("is_read")
        private int is_read;
        @SerializedName("is_delivered")
        private int is_delivered;
        @SerializedName("is_deleted")
        private int is_deleted;
        @SerializedName("content")
        private String content;
        @SerializedName("room_id")
        private int room_id;
        @SerializedName("role")
        private int role;
        @SerializedName("author_id")
        private int author_id;
        @SerializedName("id")
        private int id;
        @SerializedName("is_socket")
        private boolean is_socket = false;

        public boolean isSocket() {
            return is_socket;
        }

        public void setIsSocket(boolean is_socket) {
            this.is_socket = is_socket;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public int getIs_delivered() {
            return is_delivered;
        }

        public void setIs_delivered(int is_delivered) {
            this.is_delivered = is_delivered;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
