package app.codelabs.forum.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAnswerQuestioner {
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

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static class DataEntity {
        @SerializedName("details")
        private List<DetailsEntity> details;
        @SerializedName("correct_percentage")
        private String correct_percentage;
        @SerializedName("correct_answer")
        private String correct_answer;
        @SerializedName("total_question")
        private int total_question;

        public List<DetailsEntity> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsEntity> details) {
            this.details = details;
        }

        public String getCorrect_percentage() {
            return correct_percentage;
        }

        public void setCorrect_percentage(String correct_percentage) {
            this.correct_percentage = correct_percentage;
        }

        public String getCorrect_answer() {
            return correct_answer;
        }

        public void setCorrect_answer(String correct_answer) {
            this.correct_answer = correct_answer;
        }

        public int getTotal_question() {
            return total_question;
        }

        public void setTotal_question(int total_question) {
            this.total_question = total_question;
        }
    }

    public static class DetailsEntity {
        @SerializedName("result")
        private String result;
        @SerializedName("choice")
        private String choice;
        @SerializedName("question")
        private String question;
        @SerializedName("question_id")
        private int question_id;
        @SerializedName("percentage_choice")
        private int percentageChoice;

        private boolean isHigher = false;

        public boolean isHigher() {
            return isHigher;
        }

        public void setHigher(boolean higher) {
            isHigher = higher;
        }

        public int getPercentageChoice() {
            return percentageChoice;
        }

        public void setPercentageChoice(int percentageChoice) {
            this.percentageChoice = percentageChoice;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public boolean isCorrect() {
            return result.equals("correct");
        }
    }
}
