package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

public class ResponseFinishPassword {


    @SerializedName("status")
    private int status;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
