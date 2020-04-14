package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ResponsListShopByCategories {

    @SerializedName("data")
    private List<DataEntity> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
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

    public static class DataEntity {
        @SerializedName("location")
        private String location;
        @SerializedName("contact")
        private String contact;
        @SerializedName("price")
        private int price;
        @SerializedName("description")
        private String description;
        @SerializedName("name")
        private String name;
        @SerializedName("photo")
        private String photo;
        @SerializedName("category")
        private String category;
        @SerializedName("category_id")
        private int category_id;
        @SerializedName("id")
        private int id;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}