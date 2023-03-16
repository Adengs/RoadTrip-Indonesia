package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

public  class ResponseFollow {

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

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
