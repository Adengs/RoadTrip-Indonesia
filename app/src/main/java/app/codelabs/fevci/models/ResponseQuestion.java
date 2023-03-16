package app.codelabs.fevci.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseQuestion {
    @SerializedName("data")
    private List<Question> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) {
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

    public static class Question {
        @SerializedName("question_choice")
        private List<QuestionAnswerEntity> question_choice;
        @SerializedName("question")
        private String question;
        @SerializedName("question_id")
        private int question_id;


        public List<QuestionAnswerEntity> getQuestion_choice() {
            return question_choice;
        }

        public void setQuestion_choice(List<QuestionAnswerEntity> question_choice) {
            this.question_choice = question_choice;
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
    }

    public static class QuestionAnswerEntity {
        @SerializedName("choice")
        private String choice;
        @SerializedName("choice_id")
        private int choice_id;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

        public int getChoice_id() {
            return choice_id;
        }

        public void setChoice_id(int choice_id) {
            this.choice_id = choice_id;
        }
    }
}
