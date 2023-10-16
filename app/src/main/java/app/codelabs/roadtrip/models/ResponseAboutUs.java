package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

public class ResponseAboutUs {

    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("about_id")
        private int aboutId;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("view")
        private Object view;
        @SerializedName("created_at")
        private String createdAt;

        public int getAboutId() {
            return aboutId;
        }

        public void setAboutId(int aboutId) {
            this.aboutId = aboutId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Object getView() {
            return view;
        }

        public void setView(Object view) {
            this.view = view;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }
}
