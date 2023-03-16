package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 16 Apr 2020, 10:55
 */
public class ResponseBookmarkArticle {

    @SerializedName("data")
    private List<ResponseListArticle.Article> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<ResponseListArticle.Article> getData() {
        return data;
    }

    public void setData(List<ResponseListArticle.Article> data) {
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
