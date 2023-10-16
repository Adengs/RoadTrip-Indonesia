package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ResponseDetailShopItem {
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id")
        private int id;
        @SerializedName("company_id")
        private int companyId;
        @SerializedName("store_id")
        private int storeId;
        @SerializedName("category_id")
        private int categoryId;
        @SerializedName("name")
        private String name;
        @SerializedName("photo")
        private List<Photo> photo;
        @SerializedName("price")
        private int price;
        @SerializedName("description")
        private String description;
        @SerializedName("stock")
        private int stock;
        @SerializedName("kondisi")
        private String kondisi;
        @SerializedName("berat")
        private String berat;
        @SerializedName("min_pesanan")
        private String minPesanan;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("category")
        private Category category;
        @SerializedName("store")
        private Store store;
        @SerializedName("whatsapp")
        private String whatsapp;
        @SerializedName("wa_number")
        private String waNumber;
        @SerializedName("phone_number")
        private String phoneNumber;

        private boolean bookmark = false;

        public boolean isBookmark() {
            return bookmark;
        }

        public void setBookmark(boolean bookmark) {
            this.bookmark = bookmark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Photo> getPhoto() {
            return photo;
        }

        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getKondisi() {
            return kondisi;
        }

        public void setKondisi(String kondisi) {
            this.kondisi = kondisi;
        }

        public String getBerat() {
            return berat;
        }

        public void setBerat(String berat) {
            this.berat = berat;
        }

        public String getMinPesanan() {
            return minPesanan;
        }

        public void setMinPesanan(String minPesanan) {
            this.minPesanan = minPesanan;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getWaNumber() {
            return waNumber;
        }

        public void setWaNumber(String waNumber) {
            this.waNumber = waNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public static class Category {
            @SerializedName("id")
            private int id;
            @SerializedName("category")
            private String category;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }
        }

        public static class Store {
            @SerializedName("id")
            private int id;
            @SerializedName("user_id")
            private int userId;
            @SerializedName("note")
            private Object note;
            @SerializedName("logo")
            private String logo;
            @SerializedName("name")
            private String name;
            @SerializedName("phone")
            private String phone;
            @SerializedName("location")
            private String location;
            @SerializedName("latitude")
            private String latitude;
            @SerializedName("longitude")
            private String longitude;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getNote() {
                return note;
            }

            public void setNote(Object note) {
                this.note = note;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }

        public static class Photo {
            @SerializedName("user_store_item_image_id")
            private int userStoreItemImageId;
            @SerializedName("shop_item_id")
            private int shopItemId;
            @SerializedName("image")
            private String image;
            @SerializedName("deleted_at")
            private Object deletedAt;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;

            public int getUserStoreItemImageId() {
                return userStoreItemImageId;
            }

            public void setUserStoreItemImageId(int userStoreItemImageId) {
                this.userStoreItemImageId = userStoreItemImageId;
            }

            public int getShopItemId() {
                return shopItemId;
            }

            public void setShopItemId(int shopItemId) {
                this.shopItemId = shopItemId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }


//    @SerializedName("data")
//    private DataEntity data;
//    @SerializedName("success")
//    private boolean success;
//    @SerializedName("message")
//    private String message;
//
//    public DataEntity getData() {
//        return data;
//    }
//
//    public void setData(DataEntity data) {
//        this.data = data;
//    }
//
//    public boolean getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public static class DataEntity {
//        @SerializedName("whatsapp")
//        private String whatsapp;
//        @SerializedName("store")
//        private StoreEntity store;
//        @SerializedName("category")
//        private CategoryEntity category;
//        @SerializedName("photos")
//        private List<String> photos;
//        @SerializedName("min_pesanan")
//        private String min_pesanan;
//        @SerializedName("berat")
//        private String berat;
//        @SerializedName("kondisi")
//        private String kondisi;
//        @SerializedName("description")
//        private String description;
//        @SerializedName("price")
//        private int price;
//        @SerializedName("photo")
//        private String photo;
//        @SerializedName("name")
//        private String name;
//        @SerializedName("category_id")
//        private int category_id;
//        @SerializedName("store_id")
//        private int store_id;
//        @SerializedName("company_id")
//        private int company_id;
//        @SerializedName("id")
//        private int id;
//
//        private boolean bookmark = false;
//
//        public boolean isBookmark() {
//            return bookmark;
//        }
//
//        public void setBookmark(boolean bookmark) {
//            this.bookmark = bookmark;
//        }
//
//        public String getWhatsapp() {
//            return whatsapp;
//        }
//
//        public void setWhatsapp(String whatsapp) {
//            this.whatsapp = whatsapp;
//        }
//
//        public StoreEntity getStore() {
//            return store;
//        }
//
//        public void setStore(StoreEntity store) {
//            this.store = store;
//        }
//
//        public CategoryEntity getCategory() {
//            return category;
//        }
//
//        public void setCategory(CategoryEntity category) {
//            this.category = category;
//        }
//
//        public List<String> getPhotos() {
//            return photos;
//        }
//
//        public void setPhotos(List<String> photos) {
//            this.photos = photos;
//        }
//
//        public String getMin_pesanan() {
//            return min_pesanan;
//        }
//
//        public void setMin_pesanan(String min_pesanan) {
//            this.min_pesanan = min_pesanan;
//        }
//
//        public String getBerat() {
//            return berat;
//        }
//
//        public void setBerat(String berat) {
//            this.berat = berat;
//        }
//
//        public String getKondisi() {
//            return kondisi;
//        }
//
//        public void setKondisi(String kondisi) {
//            this.kondisi = kondisi;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public int getPrice() {
//            return price;
//        }
//
//        public void setPrice(int price) {
//            this.price = price;
//        }
//
//        public String getPhoto() {
//            return photo;
//        }
//
//        public void setPhoto(String photo) {
//            this.photo = photo;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getCategory_id() {
//            return category_id;
//        }
//
//        public void setCategory_id(int category_id) {
//            this.category_id = category_id;
//        }
//
//        public int getStore_id() {
//            return store_id;
//        }
//
//        public void setStore_id(int store_id) {
//            this.store_id = store_id;
//        }
//
//        public int getCompany_id() {
//            return company_id;
//        }
//
//        public void setCompany_id(int company_id) {
//            this.company_id = company_id;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//    }
//
//    public static class StoreEntity {
//        @SerializedName("longitude")
//        private String longitude;
//        @SerializedName("latitude")
//        private String latitude;
//        @SerializedName("location")
//        private String location;
//        @SerializedName("phone")
//        private String phone;
//        @SerializedName("name")
//        private String name;
//        @SerializedName("logo")
//        private String logo;
//        @SerializedName("id")
//        private int id;
//
//        public String getLongitude() {
//            return longitude;
//        }
//
//        public void setLongitude(String longitude) {
//            this.longitude = longitude;
//        }
//
//        public String getLatitude() {
//            return latitude;
//        }
//
//        public void setLatitude(String latitude) {
//            this.latitude = latitude;
//        }
//
//        public String getLocation() {
//            return location;
//        }
//
//        public void setLocation(String location) {
//            this.location = location;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getLogo() {
//            return logo;
//        }
//
//        public void setLogo(String logo) {
//            this.logo = logo;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//    }
//
//    public static class CategoryEntity {
//        @SerializedName("category")
//        private String category;
//        @SerializedName("id")
//        private int id;
//
//        public String getCategory() {
//            return category;
//        }
//
//        public void setCategory(String category) {
//            this.category = category;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//    }
}

