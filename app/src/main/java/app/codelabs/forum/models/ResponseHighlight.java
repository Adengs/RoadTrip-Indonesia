package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ResponseHighlight {


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
        @SerializedName("module_title")
        private String module_title;
        @SerializedName("module_image")
        private String module_image;
        @SerializedName("module_type")
        private String module_type;
        @SerializedName("module_id")
        private int module_id;

        public String getModule_title() {
            return module_title;
        }

        public void setModule_title(String module_title) {
            this.module_title = module_title;
        }

        public String getModule_image() {
            return module_image;
        }

        public void setModule_image(String module_image) {
            this.module_image = module_image;
        }

        public String getModule_type() {
            return module_type;
        }

        public void setModule_type(String module_type) {
            this.module_type = module_type;
        }

        public int getModule_id() {
            return module_id;
        }

        public void setModule_id(int module_id) {
            this.module_id = module_id;
        }
    }
}
