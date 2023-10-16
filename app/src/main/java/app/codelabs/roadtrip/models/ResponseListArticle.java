package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseListArticle {

    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id")
        private int id;
        @SerializedName("company_id")
        private int companyId;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("media_type")
        private int mediaType;
        @SerializedName("image")
        private String image;
        @SerializedName("media_link")
        private String mediaLink;
        @SerializedName("tags")
        private List<?> tags;
        @SerializedName("category_id")
        private int categoryId;
        @SerializedName("views")
        private int views;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("company")
        private Company company;
        @SerializedName("category")
        private Category category;

        public boolean isBookmark() {
            return bookmark;
        }

        public void setBookmark(boolean bookmark) {
            this.bookmark = bookmark;
        }

        private boolean bookmark = false;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMediaType() {
            return mediaType;
        }

        public void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMediaLink() {
            return mediaLink;
        }

        public void setMediaLink(String mediaLink) {
            this.mediaLink = mediaLink;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
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

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public static class Company {
            @SerializedName("id")
            private int id;
            @SerializedName("full_name")
            private String fullName;
            @SerializedName("created")
            private String created;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }
        }

        public static class Category {
            @SerializedName("id")
            private int id;
            @SerializedName("category")
            private String category;
            @SerializedName("company_id")
            private int companyId;

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

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }
        }
    }


//    @SerializedName("data")
//    private List<Article> data;
//    @SerializedName("success")
//    private boolean success;
//    @SerializedName("message")
//    private String message;
//
//    public List<Article> getData() {
//        return data;
//    }
//
//    public void setData(List<Article> data) {
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
//    public static class Article {
//        @SerializedName("category")
//        private CategoryEntity category;
//        @SerializedName("updated_at")
//        private String updated_at;
//        @SerializedName("created_at")
//        private String created_at;
//
//        public String getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(String created_at) {
//            this.created_at = created_at;
//        }
//
//        @SerializedName("views")
//        private int views;
//        @SerializedName("category_id")
//        private int category_id;
//        @SerializedName("tags")
//        private List<String> tags;
//        @SerializedName("image")
//        private String image;
//        @SerializedName("content")
//        private String content;
//        @SerializedName("title")
//        private String title;
//        @SerializedName("company_id")
//        private int company_id;
//        @SerializedName("id")
//        private int id;
//
//        public boolean isBookmark() {
//            return bookmark;
//        }
//
//        public void setBookmark(boolean bookmark) {
//            this.bookmark = bookmark;
//        }
//
//        private boolean bookmark = false;
//
//        public CategoryEntity getCategory() {
//            return category;
//        }
//
//        public void setCategory(CategoryEntity category) {
//            this.category = category;
//        }
//
//        public String getUpdated_at() {
//            return updated_at;
//        }
//
//        public void setUpdated_at(String updated_at) {
//            this.updated_at = updated_at;
//        }
//
//        public int getViews() {
//            return views;
//        }
//
//        public void setViews(int views) {
//            this.views = views;
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
//        public List<String> getTags() {
//            return tags;
//        }
//
//        public void setTags(List<String> tags) {
//            this.tags = tags;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
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
//
//    }
//
//    public static class CategoryEntity {
//        @SerializedName("company_id")
//        private int company_id;
//        @SerializedName("category")
//        private String category;
//        @SerializedName("id")
//        private int id;
//
//        public int getCompany_id() {
//            return company_id;
//        }
//
//        public void setCompany_id(int company_id) {
//            this.company_id = company_id;
//        }
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
