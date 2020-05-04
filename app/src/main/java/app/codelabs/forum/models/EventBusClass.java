package app.codelabs.forum.models;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 21 Apr 2020, 10:07
 */
public class EventBusClass {

    public static class Profile {
    }
    public static class Search{
        private String search;

        public String getSeach() {
            return search;
        }

        public void setSeach(String search) {
            this.search = search;
        }
    }
}
