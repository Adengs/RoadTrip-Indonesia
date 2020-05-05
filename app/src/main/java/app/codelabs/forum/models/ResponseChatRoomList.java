package app.codelabs.forum.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 12:46
 */
public class ResponseChatRoomList {

    @SerializedName("DATA")
    private List<DataEntity> data;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("STATUS")
    private int status;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
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

    public static class DataEntity {
        @SerializedName("room")
        private RoomEntity room;
        @SerializedName("total_unread")
        private int total_unread;
        @SerializedName("role")
        private int role;
        @SerializedName("member_id")
        private int member_id;
        @SerializedName("id")
        private int id;

        public static DataEntity get(String json) {
            return new Gson().fromJson(json,DataEntity.class);
        }

        public RoomEntity getRoom() {
            return room;
        }

        public void setRoom(RoomEntity room) {
            this.room = room;
        }

        public int getTotal_unread() {
            return total_unread;
        }

        public void setTotal_unread(int total_unread) {
            this.total_unread = total_unread;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    public static class RoomEntity {
        @SerializedName("latest_chat")
        private Latest_chatEntity latest_chat;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("is_active")
        private int is_active;
        @SerializedName("category")
        private int category;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("title")
        private String title;
        @SerializedName("id")
        private int id;

        public Latest_chatEntity getLatest_chat() {
            return latest_chat;
        }

        public void setLatest_chat(Latest_chatEntity latest_chat) {
            this.latest_chat = latest_chat;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Latest_chatEntity {
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
