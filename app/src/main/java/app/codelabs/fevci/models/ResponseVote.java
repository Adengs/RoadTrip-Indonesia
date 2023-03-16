package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 14:23
 */
public class ResponseVote {
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
        @SerializedName("isAlreadyVote")
        private boolean isAlreadyVote;
        @SerializedName("candidate")
        private List<CandidateEntity> candidate;
        @SerializedName("end_vote")
        private String end_vote;
        @SerializedName("start_vote")
        private String start_vote;
        @SerializedName("title")
        private String title;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("id")
        private int id;

        public boolean getIsAlreadyVote() {
            return isAlreadyVote;
        }

        public void setIsAlreadyVote(boolean isAlreadyVote) {
            this.isAlreadyVote = isAlreadyVote;
        }

        public List<CandidateEntity> getCandidate() {
            return candidate;
        }

        public void setCandidate(List<CandidateEntity> candidate) {
            this.candidate = candidate;
        }

        public String getEnd_vote() {
            return end_vote;
        }

        public void setEnd_vote(String end_vote) {
            this.end_vote = end_vote;
        }

        public String getStart_vote() {
            return start_vote;
        }

        public void setStart_vote(String start_vote) {
            this.start_vote = start_vote;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

    public static class CandidateEntity {
        @SerializedName("user_photo")
        private String user_photo;
        @SerializedName("user_name")
        private String user_name;
        @SerializedName("user_id")
        private int user_id;

        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
