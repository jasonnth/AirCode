package p005cn.jpush.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* renamed from: cn.jpush.android.util.DateUtils */
public class DateUtils {
    public static String PATTERN_DATETIME_FILENAME = "yyyyMMdd_HHmm";

    public static String getTodayDateTimeForReport() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
    }

    public static String getTodayDateTimeForFilename() {
        return new SimpleDateFormat(PATTERN_DATETIME_FILENAME).format(new Date());
    }

    public static Date parseDateInFilename(String dateInFilename) {
        try {
            return new SimpleDateFormat(PATTERN_DATETIME_FILENAME).parse(dateInFilename);
        } catch (ParseException e) {
            Logger.m1421e("DateUtil", "parse filename datetime error - " + dateInFilename, e);
            return null;
        }
    }

    public static boolean isDaysAgo(Date old, int days) {
        if (old == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        Calendar before = Calendar.getInstance();
        before.setTimeInMillis(old.getTime());
        now.roll(6, -days);
        return now.after(before);
    }
}
