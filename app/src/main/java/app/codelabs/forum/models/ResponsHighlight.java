package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ResponsHighlight {

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
        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
