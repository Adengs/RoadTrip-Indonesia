package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 16 Apr 2020, 12:36
 */
public class ResponseBookmarkEvent {

    @SerializedName("data")
    private List<ResponseListEventCommunity.DataEntity> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<ResponseListEventCommunity.DataEntity> getData() {
        return data;
    }

    public void setData(List<ResponseListEventCommunity.DataEntity> data) {
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

}
