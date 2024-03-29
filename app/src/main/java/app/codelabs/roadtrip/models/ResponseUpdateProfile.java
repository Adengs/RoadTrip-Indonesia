package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 12:51
 */
public class ResponseUpdateProfile {

    @SerializedName("data")
    private User data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public User getData() {
        return data;
    }

    public void setData(User data) {
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

    public static class User {
        @SerializedName("role")
        private String role;
        @SerializedName("city")
        private String city;
        @SerializedName("date_birth")
        private String date_birth;
        @SerializedName("photo")
        private String photo;
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("id")
        private int id;
        @SerializedName("nra")
        private String nra;
        @SerializedName("chapter")
        private String chapter;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate_birth() {
            return date_birth;
        }

        public void setDate_birth(String date_birth) {
            this.date_birth = date_birth;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getNra() {
            return nra;
        }

        public void setNra(String nra) {
            this.nra = nra;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }
    }
}
