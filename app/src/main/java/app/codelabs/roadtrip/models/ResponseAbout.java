package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAbout {

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
        @SerializedName("secretariat")
        private List<SecretariatEntity> secretariat;
        @SerializedName("history")
        private String history;
        @SerializedName("background")
        private String background;
        @SerializedName("logo")
        private String logo;
        @SerializedName("full_name")
        private String full_name;
        @SerializedName("company_name")
        private String company_name;
        @SerializedName("id")
        private int id;

        public List<SecretariatEntity> getSecretariat() {
            return secretariat;
        }

        public void setSecretariat(List<SecretariatEntity> secretariat) {
            this.secretariat = secretariat;
        }

        public String getHistory() {
            return history;
        }

        public void setHistory(String history) {
            this.history = history;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class SecretariatEntity {
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("address")
        private String address;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
