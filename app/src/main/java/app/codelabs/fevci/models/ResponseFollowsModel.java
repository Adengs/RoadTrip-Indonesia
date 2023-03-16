package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFollowsModel {

    /**
     * message : Follows
     * success : true
     * data : [{"follower_id":53772,"username":"BabanFirdaus","photo":"http://fevci.id/pehjGSZ/QtKOwrD/api/UploadedFile/UserPhoto/1605845564_tempimage.jpg"}]
     */

    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    /**
     * follower_id : 53772
     * username : BabanFirdaus
     * photo : http://fevci.id/pehjGSZ/QtKOwrD/api/UploadedFile/UserPhoto/1605845564_tempimage.jpg
     */

    @SerializedName("data")
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("follower_id")
        private int followerId;
        @SerializedName("username")
        private String username;
        @SerializedName("photo")
        private String photo;
        /**
         * followed_id : 53787
         * follower_count : 2
         * followed_count : 1
         * is_following : true
         */

        @SerializedName("followed_id")
        private int followedId;
        @SerializedName("follower_count")
        private int followerCount;
        @SerializedName("followed_count")
        private int followedCount;
        @SerializedName("is_following")
        private boolean isFollowing;
        /**
         * name : Amir Makmur Nasution
         */

        @SerializedName("name")
        private String name;


        public int getFollowerId() {
            return followerId;
        }

        public void setFollowerId(int followerId) {
            this.followerId = followerId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getFollowedId() {
            return followedId;
        }

        public void setFollowedId(int followedId) {
            this.followedId = followedId;
        }

        public int getFollowerCount() {
            return followerCount;
        }

        public void setFollowerCount(int followerCount) {
            this.followerCount = followerCount;
        }

        public int getFollowedCount() {
            return followedCount;
        }

        public void setFollowedCount(int followedCount) {
            this.followedCount = followedCount;
        }

        public boolean isIsFollowing() {
            return isFollowing;
        }

        public void setIsFollowing(boolean isFollowing) {
            this.isFollowing = isFollowing;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
