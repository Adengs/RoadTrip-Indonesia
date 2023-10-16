package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMyProfile {
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
        private String gender;
        @SerializedName("photo")
        private String photo;
        @SerializedName("fcm_id")
        private Object fcmId;
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
        private Object statusMerchandise;
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
        private Object notes;
        @SerializedName("status_starter_kit")
        private int statusStarterKit;
        @SerializedName("reset_token")
        private String resetToken;
        @SerializedName("followers")
        private int followers;
        @SerializedName("following")
        private int following;
        @SerializedName("store")
        private Store store;

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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getFcmId() {
            return fcmId;
        }

        public void setFcmId(Object fcmId) {
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

        public Object getStatusMerchandise() {
            return statusMerchandise;
        }

        public void setStatusMerchandise(Object statusMerchandise) {
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

        public Object getNotes() {
            return notes;
        }

        public void setNotes(Object notes) {
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

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
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
            @SerializedName("item")
            private List<Item> item;

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

            public List<Item> getItem() {
                return item;
            }

            public void setItem(List<Item> item) {
                this.item = item;
            }

            public static class Item {
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

                public static class Photo {
                    @SerializedName("user_store_item_image_id")
                    private int imageId;
                    @SerializedName("shop_item_id")
                    private int itemId;
                    @SerializedName("image")
                    private String image;

                    public int getImageId() {
                        return imageId;
                    }

                    public void setImageId(int image_id) {
                        this.imageId = image_id;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int item_id) {
                        this.itemId = item_id;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }
                }

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
//        @SerializedName("following")
//        private int following;
//        @SerializedName("followers")
//        private int followers;
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

//        @SerializedName("nra")
//        private String nra;
//        @SerializedName("nickname")
//        private String nickname;
//        @SerializedName("gender")
//        private String gender;
//        @SerializedName("phone")
//        private String phone;
//        @SerializedName("chapter")
//        private String chapter;
//        @SerializedName("status")
//        private int status;
//        @SerializedName("alamat")
//        private String alamat;
//        @SerializedName("rt")
//        private String rt;
//        @SerializedName("rw")
//        private String rw;
//        @SerializedName("desa_kelurahan")
//        private String desaKelurahan;
//        @SerializedName("kecamatan")
//        private String kecamatan;
//        @SerializedName("kabupaten_kota")
//        private String kabupatenKota;
//        @SerializedName("provinsi")
//        private String provinsi;
//        @SerializedName("foto_ktp")
//        private String fotoKtp;
//        @SerializedName("foto_sim")
//        private String fotoSim;
//        @SerializedName("foto_stnk")
//        private String fotoStnk;
//        @SerializedName("foto_mobil")
//        private String fotoMobil;
//        @SerializedName("keterangan")
//        private String keterangan;
//        @SerializedName("status_starter_kit")
//        private String statusStarterKit;
//        @SerializedName("reset_token")
//        private String resetToken;
//
//
//        public int getFollowing() {
//            return following;
//        }
//
//        public void setFollowing(int following) {
//            this.following = following;
//        }
//
//        public int getFollowers() {
//            return followers;
//        }
//
//        public void setFollowers(int followers) {
//            this.followers = followers;
//        }
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
//        public String getNickname() {
//            return nickname;
//        }
//
//        public void setNickname(String nickname) {
//            this.nickname = nickname;
//        }
//
//        public String getGender() {
//            return gender;
//        }
//
//        public void setGender(String gender) {
//            this.gender = gender;
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
//        public String getChapter() {
//            return chapter;
//        }
//
//        public void setChapter(String chapter) {
//            this.chapter = chapter;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public String getAlamat() {
//            return alamat;
//        }
//
//        public void setAlamat(String alamat) {
//            this.alamat = alamat;
//        }
//
//        public String getRt() {
//            return rt;
//        }
//
//        public void setRt(String rt) {
//            this.rt = rt;
//        }
//
//        public String getRw() {
//            return rw;
//        }
//
//        public void setRw(String rw) {
//            this.rw = rw;
//        }
//
//        public String getDesaKelurahan() {
//            return desaKelurahan;
//        }
//
//        public void setDesaKelurahan(String desaKelurahan) {
//            this.desaKelurahan = desaKelurahan;
//        }
//
//        public String getKecamatan() {
//            return kecamatan;
//        }
//
//        public void setKecamatan(String kecamatan) {
//            this.kecamatan = kecamatan;
//        }
//
//        public String getKabupatenKota() {
//            return kabupatenKota;
//        }
//
//        public void setKabupatenKota(String kabupatenKota) {
//            this.kabupatenKota = kabupatenKota;
//        }
//
//        public String getProvinsi() {
//            return provinsi;
//        }
//
//        public void setProvinsi(String provinsi) {
//            this.provinsi = provinsi;
//        }
//
//        public String getFotoKtp() {
//            return fotoKtp;
//        }
//
//        public void setFotoKtp(String fotoKtp) {
//            this.fotoKtp = fotoKtp;
//        }
//
//        public String getFotoSim() {
//            return fotoSim;
//        }
//
//        public void setFotoSim(String fotoSim) {
//            this.fotoSim = fotoSim;
//        }
//
//        public String getFotoStnk() {
//            return fotoStnk;
//        }
//
//        public void setFotoStnk(String fotoStnk) {
//            this.fotoStnk = fotoStnk;
//        }
//
//        public String getFotoMobil() {
//            return fotoMobil;
//        }
//
//        public void setFotoMobil(String fotoMobil) {
//            this.fotoMobil = fotoMobil;
//        }
//
//        public String getKeterangan() {
//            return keterangan;
//        }
//
//        public void setKeterangan(String keterangan) {
//            this.keterangan = keterangan;
//        }
//
//        public String getStatusStarterKit() {
//            return statusStarterKit;
//        }
//
//        public void setStatusStarterKit(String statusStarterKit) {
//            this.statusStarterKit = statusStarterKit;
//        }
//
//        public String getResetToken() {
//            return resetToken;
//        }
//
//        public void setResetToken(String resetToken) {
//            this.resetToken = resetToken;
//        }
//    }
}
