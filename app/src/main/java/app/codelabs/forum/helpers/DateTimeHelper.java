package app.codelabs.forum.helpers;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 13 Apr 2020, 10:31
 */
public class DateTimeHelper {
    public static String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static String PATTERN_ONLY_DATE = "yyyy-MM-dd";
    public static String PATTERN_ONLY_TIME = "HH:mm:ss";
    private Date date;

    public static DateTimeHelper instance(String dateTime) {
        return new DateTimeHelper(dateTime);
    }

    public static DateTimeHelper instance(Date dateTime) {
        return new DateTimeHelper(dateTime);
    }

    public static DateTimeHelper instance(String dateTime, String pattern) {
        return new DateTimeHelper(dateTime, pattern);
    }


    private DateTimeHelper(String dateTime) {
        if(dateTime == null){
            return;
        }

        String patternDate = PATTERN_DEFAULT;
        SimpleDateFormat format = new SimpleDateFormat(patternDate);
        try {
            this.date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private DateTimeHelper(String dateTime, String pattern) {
        if(dateTime == null){
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            this.date = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private DateTimeHelper(Date date) {
        this.date = date;
    }

    public String getReadableTime() {
        if(date == null){
            return "Datetime is null";
        }
        Date now = new Date();
        String str = "NaN";
        DateTime nowDateTime = new DateTime(now);
        DateTime dateTime = new DateTime(date);

        DateTime resultDateTime = new DateTime(nowDateTime.getMillis() - dateTime.getMillis());

        if (resultDateTime.getDayOfYear() >= nowDateTime.dayOfMonth().getMaximumValue()) { // month
            str = (resultDateTime.getMonthOfYear() - 1) + " month" +
                    ((resultDateTime.getMonthOfYear() - 1) == 1 ? "" : "s") + " ago";

        } else if (resultDateTime.getDayOfYear() >= 7) {
            str = (resultDateTime.getDayOfYear() / 7) + " week" +
                    ((resultDateTime.getDayOfYear() / 7) == 1 ? "" : "s") + " ago";

        } else if ((resultDateTime.getDayOfYear() - 1) != 0) {
            str = (resultDateTime.getDayOfYear() - 1) + " day" +
                    ((resultDateTime.getDayOfYear() - 1) == 1 ? "" : "s") + " ago";

        } else if (Hours.hoursBetween(dateTime, nowDateTime).getHours() > 0) {
            str = Hours.hoursBetween(dateTime, nowDateTime).getHours() + " hour" +
                    (Hours.hoursBetween(dateTime, nowDateTime).getHours() == 1 ? "" : "s")
                    + " ago";

        } else if (Minutes.minutesBetween(dateTime, nowDateTime).getMinutes() > 0) {
            str = Minutes.minutesBetween(dateTime, nowDateTime).getMinutes() + " minute" +
                    (Minutes.minutesBetween(dateTime, nowDateTime).getMinutes() == 1 ? "" : "s")
                    + " ago";

        } else {
            str = "Few moment ago";
        }
        return str;
    }
}
