package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 15:08
 */
public class ResponseRoomChatDetail {
    @SerializedName("DATA")
    private DataEntity data;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("STATUS")
    private int status;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
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
        @SerializedName("members")
        private List<MembersEntity> members;
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

        public List<MembersEntity> getMembers() {
            return members;
        }

        public void setMembers(List<MembersEntity> members) {
            this.members = members;
        }

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

    public static class MembersEntity {
        @SerializedName("metadata")
        private MetadataEntity metadata;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("is_active")
        private int is_active;
        @SerializedName("role")
        private int role;
        @SerializedName("metadata_id")
        private int metadata_id;
        @SerializedName("member_id")
        private int member_id;
        @SerializedName("room_id")
        private int room_id;
        @SerializedName("id")
        private int id;

        public MetadataEntity getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataEntity metadata) {
            this.metadata = metadata;
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

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getMetadata_id() {
            return metadata_id;
        }

        public void setMetadata_id(int metadata_id) {
            this.metadata_id = metadata_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class MetadataEntity {
        @SerializedName("meta_member")
        private Meta_memberEntity meta_member;

        public Meta_memberEntity getMeta_member() {
            return meta_member;
        }

        public void setMeta_member(Meta_memberEntity meta_member) {
            this.meta_member = meta_member;
        }
    }

    public static class Meta_memberEntity {
        @SerializedName("image")
        private String image;
        @SerializedName("name")
        private String name;

        public Meta_memberEntity(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
