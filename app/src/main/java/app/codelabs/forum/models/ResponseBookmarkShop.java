package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 16 Apr 2020, 13:45
 */
public class ResponseBookmarkShop {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public List<ResponseListShopByCategories.DataEntity> getData() {
        return data;
    }

    public void setData(List<ResponseListShopByCategories.DataEntity> data) {
        this.data = data;
    }

    @SerializedName("data")
    private List<ResponseListShopByCategories.DataEntity> data;

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
