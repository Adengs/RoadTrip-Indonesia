package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ResponsListMemberCompany {

    @SerializedName("data")
    private List<Data> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
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

    public static class Data {
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
    }
}
