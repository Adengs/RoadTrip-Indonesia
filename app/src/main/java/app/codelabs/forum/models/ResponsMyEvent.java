package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsMyEvent {

    @SerializedName("data")
    private List<DataEntity> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {
        @SerializedName("image")
        private String image;
        @SerializedName("event_end")
        private String event_end;
        @SerializedName("event_start")
        private String event_start;
        @SerializedName("event_title")
        private String event_title;
        @SerializedName("event_id")
        private int event_id;
        @SerializedName("user_id")
        private int user_id;
        @SerializedName("id")
        private int id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEvent_end() {
            return event_end;
        }

        public void setEvent_end(String event_end) {
            this.event_end = event_end;
        }

        public String getEvent_start() {
            return event_start;
        }

        public void setEvent_start(String event_start) {
            this.event_start = event_start;
        }

        public String getEvent_title() {
            return event_title;
        }

        public void setEvent_title(String event_title) {
            this.event_title = event_title;
        }

        public int getEvent_id() {
            return event_id;
        }

        public void setEvent_id(int event_id) {
            this.event_id = event_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
