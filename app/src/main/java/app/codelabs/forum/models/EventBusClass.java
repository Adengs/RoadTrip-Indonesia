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

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

    public static class EventJoin {
    }
}
