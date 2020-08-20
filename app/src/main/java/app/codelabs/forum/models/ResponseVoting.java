package app.codelabs.forum.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 14 Apr 2020, 16:17
 */
public class ResponseVoting {
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
        @SerializedName("company_id")
        private String company_id;
        @SerializedName("user_id")
        private String user_id;
        @SerializedName("candidate_id")
        private String candidate_id;
        @SerializedName("vote_id")
        private String vote_id;
        @SerializedName("question")
        private boolean question;

        public boolean isQuestion() {
            return question;
        }

        public void setQuestion(boolean question) {
            this.question = question;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCandidate_id() {
            return candidate_id;
        }

        public void setCandidate_id(String candidate_id) {
            this.candidate_id = candidate_id;
        }

        public String getVote_id() {
            return vote_id;
        }

        public void setVote_id(String vote_id) {
            this.vote_id = vote_id;
        }
    }
}
