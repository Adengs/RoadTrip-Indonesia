package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

public  class ResponseLogin {
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
        private int gender;
        @SerializedName("photo")
        private String photo;
        @SerializedName("fcm_id")
        private String fcmId;
        @SerializedName("phone")
        private String phone;
        @SerializedName("date_birth")
        private String dateBirth;
        @SerializedName("chapter")
        private String chapter;
        @SerializedName("status")
        private int status;
        @SerializedName("status_jabatan")
        private int statusJabatan;
        @SerializedName("status_merchandise")
        private String statusMerchandise;
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
        @SerializedName("no_plat")
        private String noPlat;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("notes")
        private String notes;
        @SerializedName("status_starter_kit")
        private int statusStarterKit;
        @SerializedName("reset_token")
        private String resetToken;
        @SerializedName("token")
        private String token;

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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getFcmId() {
            return fcmId;
        }

        public void setFcmId(String fcmId) {
            this.fcmId = fcmId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(String dateBirth) {
            this.dateBirth = dateBirth;
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

        public int getStatusJabatan() {
            return statusJabatan;
        }

        public void setStatusJabatan(int statusJabatan) {
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

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public int getStatusStarterKit() {
            return statusStarterKit;
        }

        public void setStatusStarterKit(int statusStarterKit) {
            this.statusStarterKit = statusStarterKit;
        }

        public String getResetToken() {
            return resetToken;
        }

        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


//    @SerializedName("token")
//    private String token;
//    @SerializedName("data")
//    private Data data;
//    @SerializedName("success")
//    private boolean success;
//    @SerializedName("message")
//    private String message;
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
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
//    public static class Data {
//        @SerializedName("role")
//        private String role;
//        @SerializedName("city")
//        private String city;
//        @SerializedName("date_birth")
//        private String date_birth;
//        @SerializedName("photo")
//        private String photo;
//        @SerializedName("name")
//        private String name;
//        @SerializedName("username")
//        private String username;
//        @SerializedName("email")
//        private String email;
//        @SerializedName("company_id")
//        private int company_id;
//        @SerializedName("id")
//        private int id;
//        @SerializedName("nra")
//        private String nra;
//        @SerializedName("chapter")
//        private String chapter;
//
//        public String getRole() {
//            return role;
//        }
//
//        public void setRole(String role) {
//            this.role = role;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//
//        public String getDate_birth() {
//            return date_birth;
//        }
//
//        public void setDate_birth(String date_birth) {
//            this.date_birth = date_birth;
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
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
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
//        public String getNra() {
//            return nra;
//        }
//
//        public void setNra(String nra) {
//            this.nra = nra;
//        }
//
//        public String getChapter() {
//            return chapter;
//        }
//
//        public void setChapter(String chapter) {
//            this.chapter = chapter;
//        }
//    }
}
