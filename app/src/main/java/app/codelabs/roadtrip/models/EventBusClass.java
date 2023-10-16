package app.codelabs.roadtrip.models;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 21 Apr 2020, 10:07
 */
public class EventBusClass {

    public static class Profile {
    }
    public static class SearchShop {
        private String query;

        public SearchShop(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }
    public static class EventJoin {
    }
}
