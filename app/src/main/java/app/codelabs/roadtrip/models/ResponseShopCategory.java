package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseShopCategory {

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
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("category")
        private String category;
        @SerializedName("id")
        private int id;

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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
