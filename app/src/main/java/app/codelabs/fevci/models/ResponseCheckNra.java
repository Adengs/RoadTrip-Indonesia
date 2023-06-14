package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

public class ResponseCheckNra {

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

    public boolean isSuccess() {
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
        @SerializedName("companyId")
        private int companyId;
        @SerializedName("nra")
        private String nra;
        @SerializedName("email")
        private String email;
        @SerializedName("username")
        private String username;
        @SerializedName("name")
        private String name;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("gender")
        private String gender;
        @SerializedName("photo")
        private Object photo;
        @SerializedName("simStnk")
        private String simStnk;
        @SerializedName("phone")
        private String phone;
        @SerializedName("dateBirth")
        private Object dateBirth;
        @SerializedName("city")
        private Object city;
        @SerializedName("chapter")
        private String chapter;
        @SerializedName("status")
        private int status;
        @SerializedName("statusJabatan")
        private String statusJabatan;
        @SerializedName("statusMerchandise")
        private String statusMerchandise;
        @SerializedName("alamat")
        private String alamat;
        @SerializedName("rt")
        private String rt;
        @SerializedName("rw")
        private String rw;
        @SerializedName("desaKelurahan")
        private String desaKelurahan;
        @SerializedName("kecamatan")
        private String kecamatan;
        @SerializedName("kabupatenKota")
        private String kabupatenKota;
        @SerializedName("provinsi")
        private String provinsi;
        @SerializedName("fotoKtp")
        private Object fotoKtp;
        @SerializedName("fotoSim")
        private Object fotoSim;
        @SerializedName("fotoStnk")
        private Object fotoStnk;
        @SerializedName("fotoMobil")
        private Object fotoMobil;
        @SerializedName("noPlat")
        private String noPlat;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("catatan")
        private Object catatan;
        @SerializedName("statusStarterKit")
        private String statusStarterKit;
        @SerializedName("apiToken")
        private Object apiToken;
        @SerializedName("resetToken")
        private Object resetToken;
        @SerializedName("role")
        private String role;
        @SerializedName("isActive")
        private int isActive;
        @SerializedName("deletedAt")
        private Object deletedAt;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;

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

        public String getNra() {
            return nra;
        }

        public void setNra(String nra) {
            this.nra = nra;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public String getSimStnk() {
            return simStnk;
        }

        public void setSimStnk(String simStnk) {
            this.simStnk = simStnk;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(Object dateBirth) {
            this.dateBirth = dateBirth;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusJabatan() {
            return statusJabatan;
        }

        public void setStatusJabatan(String statusJabatan) {
            this.statusJabatan = statusJabatan;
        }

        public String getStatusMerchandise() {
            return statusMerchandise;
        }

        public void setStatusMerchandise(String statusMerchandise) {
            this.statusMerchandise = statusMerchandise;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getRt() {
            return rt;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }

        public String getRw() {
            return rw;
        }

        public void setRw(String rw) {
            this.rw = rw;
        }

        public String getDesaKelurahan() {
            return desaKelurahan;
        }

        public void setDesaKelurahan(String desaKelurahan) {
            this.desaKelurahan = desaKelurahan;
        }

        public String getKecamatan() {
            return kecamatan;
        }

        public void setKecamatan(String kecamatan) {
            this.kecamatan = kecamatan;
        }

        public String getKabupatenKota() {
            return kabupatenKota;
        }

        public void setKabupatenKota(String kabupatenKota) {
            this.kabupatenKota = kabupatenKota;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public Object getFotoKtp() {
            return fotoKtp;
        }

        public void setFotoKtp(Object fotoKtp) {
            this.fotoKtp = fotoKtp;
        }

        public Object getFotoSim() {
            return fotoSim;
        }

        public void setFotoSim(Object fotoSim) {
            this.fotoSim = fotoSim;
        }

        public Object getFotoStnk() {
            return fotoStnk;
        }

        public void setFotoStnk(Object fotoStnk) {
            this.fotoStnk = fotoStnk;
        }

        public Object getFotoMobil() {
            return fotoMobil;
        }

        public void setFotoMobil(Object fotoMobil) {
            this.fotoMobil = fotoMobil;
        }

        public String getNoPlat() {
            return noPlat;
        }

        public void setNoPlat(String noPlat) {
            this.noPlat = noPlat;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public Object getCatatan() {
            return catatan;
        }

        public void setCatatan(Object catatan) {
            this.catatan = catatan;
        }

        public String getStatusStarterKit() {
            return statusStarterKit;
        }

        public void setStatusStarterKit(String statusStarterKit) {
            this.statusStarterKit = statusStarterKit;
        }

        public Object getApiToken() {
            return apiToken;
        }

        public void setApiToken(Object apiToken) {
            this.apiToken = apiToken;
        }

        public Object getResetToken() {
            return resetToken;
        }

        public void setResetToken(Object resetToken) {
            this.resetToken = resetToken;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
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
