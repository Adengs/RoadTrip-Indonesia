package app.codelabs.fevci.helpers;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 22 Apr 2020, 10:09
 */
public class StringUtil {
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static String toCapitalize(String str) {
        String tempStr = "";
        for (String s : str.split(" ")) {
            tempStr += s.substring(0, 1).toUpperCase() + s.substring(1) + " ";
        }
        return tempStr.trim();
    }
}
