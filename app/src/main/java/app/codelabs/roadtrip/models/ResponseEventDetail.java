package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 22 Apr 2020, 12:01
 */
public class ResponseEventDetail {
    @SerializedName("data")
    private ResponseListEventCommunity.DataEntity data;

    public ResponseListEventCommunity.DataEntity getData() {
        return data;
    }

    public void setData(ResponseListEventCommunity.DataEntity data) {
        this.data = data;
    }

    public boolean isSuccess() {
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

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
}
