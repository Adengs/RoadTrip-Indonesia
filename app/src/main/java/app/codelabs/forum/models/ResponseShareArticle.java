package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 16 Apr 2020, 11:06
 */
public class ResponseShareArticle {

    @SerializedName("data")
    private String data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
