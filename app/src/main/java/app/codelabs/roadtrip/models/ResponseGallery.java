package app.codelabs.roadtrip.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 9:54
 */
public class ResponseGallery {
    @SerializedName("data")
    private List<Gallery> data;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<Gallery> getData() {
        return data;
    }

    public void setData(List<Gallery> data) {
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

    public static class Gallery {
        @SerializedName("photos")
        private List<String> photos = new ArrayList<>();
        @SerializedName("event_name")
        private String event_name;

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }

        public String getEvent_name() {
            return event_name;
        }

        public void setEvent_name(String event_name) {
            this.event_name = event_name;
        }
    }
}
