package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

public class ResponseApi {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataEntity data;
    @SerializedName("success")
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public static class DataEntity {
        @SerializedName("app_token")
        private String app_token;

        public String getApp_token() {
            return app_token;
        }

        public void setApp_token(String app_token) {
            this.app_token = app_token;
        }
    }


//    @SerializedName("data")
//    private String data;
//    @SerializedName("success")
//    private boolean success;
//    @SerializedName("message")
//    private String message;
//
//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public boolean getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
