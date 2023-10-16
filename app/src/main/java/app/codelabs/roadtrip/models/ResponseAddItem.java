package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAddItem {

    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
