package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

public  class ResponseDetailShopItem {

    @SerializedName("data")
    private DataEntity data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
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
        @SerializedName("whatsapp")
        private String whatsapp;
        @SerializedName("store")
        private StoreEntity store;
        @SerializedName("category")
        private CategoryEntity category;
        @SerializedName("description")
        private String description;
        @SerializedName("price")
        private int price;
        @SerializedName("photo")
        private String photo;
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

        private boolean bookmark = false;

        public boolean isBookmark() {
            return bookmark;
        }

        public void setBookmark(boolean bookmark) {
            this.bookmark = bookmark;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
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
}

