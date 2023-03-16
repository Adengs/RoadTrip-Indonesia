package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

public class ResponseArticleDetail {
    @SerializedName("data")
    private ResponseListArticle.Article data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;


    public ResponseListArticle.Article getData() {
        return data;
    }

    public void setData(ResponseListArticle.Article data) {
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
}
