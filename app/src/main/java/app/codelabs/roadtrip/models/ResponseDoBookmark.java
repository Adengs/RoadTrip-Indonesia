package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 16 Apr 2020, 11:16
 */
public class ResponseDoBookmark {

    @SerializedName("data")
    private DataEntity data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
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
        @SerializedName("id")
        private int id;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("user_id")
        private int user_id;
        @SerializedName("module_id")
        private String module_id;
        @SerializedName("module_name")
        private String module_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getModule_id() {
            return module_id;
        }

        public void setModule_id(String module_id) {
            this.module_id = module_id;
        }

        public String getModule_name() {
            return module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }
    }
}
