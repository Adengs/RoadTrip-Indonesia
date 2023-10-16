package app.codelabs.roadtrip.helpers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDetailExplore {

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
        @SerializedName("category")
        private CategoryEntity category;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("operational_times")
        private String operational_times;
        @SerializedName("operational_days")
        private String operational_days;
        @SerializedName("images")
        private List<String> images;
        @SerializedName("tags")
        private List<String> tags;
        @SerializedName("price_start")
        private int price_start;
        @SerializedName("latitude")
        private double latitude;
        @SerializedName("longitude")
        private double longitude;
        @SerializedName("description")
        private String description;
        @SerializedName("phone")
        private String phone;
        @SerializedName("address")
        private String address;
        @SerializedName("province")
        private String province;
        @SerializedName("city")
        private String city;
        @SerializedName("title")
        private String title;
        @SerializedName("place_category_id")
        private int place_category_id;
        @SerializedName("id")
        private int id;

        public CategoryEntity getCategory() {
            return category;
        }

        public void setCategory(CategoryEntity category) {
            this.category = category;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getOperational_times() {
            return operational_times;
        }

        public void setOperational_times(String operational_times) {
            this.operational_times = operational_times;
        }

        public String getOperational_days() {
            return operational_days;
        }

        public void setOperational_days(String operational_days) {
            this.operational_days = operational_days;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public int getPrice_start() {
            return price_start;
        }

        public void setPrice_start(int price_start) {
            this.price_start = price_start;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlace_category_id() {
            return place_category_id;
        }

        public void setPlace_category_id(int place_category_id) {
            this.place_category_id = place_category_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class CategoryEntity {
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("title")
        private String title;
        @SerializedName("id")
        private int id;

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
