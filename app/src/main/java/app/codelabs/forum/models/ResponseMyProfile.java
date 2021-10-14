package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

public class ResponseMyProfile {

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
        @SerializedName("following")
        private int following;
        @SerializedName("followers")
        private int followers;
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
        /**
         * nra : 2020
         * nickname : sample
         * gender : Laki-Laki
         * phone : +628999
         * chapter : bandung
         * status : 1
         * status_jabatan : null
         * status_merchandise : null
         * alamat : sample
         * rt : 1
         * rw : 1
         * desa_kelurahan : Cimahi
         * kecamatan : Cimahi Tengah
         * kabupaten_kota : Cimahi
         * provinsi : sample provinsi
         * foto_ktp : UploadedFile/ktp/1606127007
         * foto_sim : UploadedFile/sim/1606127007
         * foto_stnk : UploadedFile/stnk/1606127007
         * foto_mobil : UploadedFile/mobil/1606127007
         * keterangan :
         * status_starter_kit :
         * reset_token :
         */

        @SerializedName("nra")
        private String nra;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("gender")
        private String gender;
        @SerializedName("phone")
        private String phone;
        @SerializedName("chapter")
        private String chapter;
        @SerializedName("status")
        private int status;
        @SerializedName("alamat")
        private String alamat;
        @SerializedName("rt")
        private String rt;
        @SerializedName("rw")
        private String rw;
        @SerializedName("desa_kelurahan")
        private String desaKelurahan;
        @SerializedName("kecamatan")
        private String kecamatan;
        @SerializedName("kabupaten_kota")
        private String kabupatenKota;
        @SerializedName("provinsi")
        private String provinsi;
        @SerializedName("foto_ktp")
        private String fotoKtp;
        @SerializedName("foto_sim")
        private String fotoSim;
        @SerializedName("foto_stnk")
        private String fotoStnk;
        @SerializedName("foto_mobil")
        private String fotoMobil;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("status_starter_kit")
        private String statusStarterKit;
        @SerializedName("reset_token")
        private String resetToken;


        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getFotoKtp() {
            return fotoKtp;
        }

        public void setFotoKtp(String fotoKtp) {
            this.fotoKtp = fotoKtp;
        }

        public String getFotoSim() {
            return fotoSim;
        }

        public void setFotoSim(String fotoSim) {
            this.fotoSim = fotoSim;
        }

        public String getFotoStnk() {
            return fotoStnk;
        }

        public void setFotoStnk(String fotoStnk) {
            this.fotoStnk = fotoStnk;
        }

        public String getFotoMobil() {
            return fotoMobil;
        }

        public void setFotoMobil(String fotoMobil) {
            this.fotoMobil = fotoMobil;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getStatusStarterKit() {
            return statusStarterKit;
        }

        public void setStatusStarterKit(String statusStarterKit) {
            this.statusStarterKit = statusStarterKit;
        }

        public String getResetToken() {
            return resetToken;
        }

        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }
    }
}
