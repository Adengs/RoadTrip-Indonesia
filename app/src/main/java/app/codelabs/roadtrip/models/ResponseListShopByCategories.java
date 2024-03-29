package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListShopByCategories {

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
        @SerializedName("store")
        private StoreEntity store;
        @SerializedName("category")
        private CategoryEntity category;
        @SerializedName("description")
        private String description;
        @SerializedName("price")
        private int price;
        @SerializedName("photo")
        private List<Photo> photo;
        @SerializedName("name")
        private String name;
        @SerializedName("category_id")
        private int category_id;
        @SerializedName("store_id")
        private int store_id;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("id")
        private int id;

        public StoreEntity getStore() {
            return store;
        }

        public void setStore(StoreEntity store) {
            this.store = store;
        }

        public CategoryEntity getCategory() {
            return category;
        }

        public void setCategory(CategoryEntity category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<Photo> getPhoto() {
            return photo;
        }

        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class StoreEntity {
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("location")
        private String location;
        @SerializedName("phone")
        private String phone;
        @SerializedName("name")
        private String name;
        @SerializedName("logo")
        private String logo;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("id")
        private int id;

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class CategoryEntity {
        @SerializedName("category")
        private String category;
        @SerializedName("id")
        private int id;

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

    public static class Photo {
        @SerializedName("user_store_item_image_id")
        private int imageId;
        @SerializedName("shop_item_id")
        private int itemId;
        @SerializedName("image")
        private String image;

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int image_id) {
            this.imageId = image_id;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int item_id) {
            this.itemId = item_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
