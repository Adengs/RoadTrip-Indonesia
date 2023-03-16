package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 07 Apr 2020, 9:04
 */
public class ResponseArticleCategory {
    @SerializedName("data")
    private List<Category> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
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

    public static class Category {
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("category")
        private String category;
        @SerializedName("id")
        private int id;

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
