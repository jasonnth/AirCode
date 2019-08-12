package net.danlew.android.joda;

import android.content.Context;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public class DateUtils {
    private static final DateTime EPOCH = new DateTime(0, DateTimeZone.UTC);

    public static String formatDateTime(Context context, ReadablePartial time, int flags) {
        return android.text.format.DateUtils.formatDateTime(context, toMillis(time), flags | 8192);
    }

    public static String formatDateTime(Context context, ReadableInstant time, int flags) {
        return android.text.format.DateUtils.formatDateTime(context, toMillis(time), flags | 8192);
    }

    public static String formatDateRange(Context context, ReadablePartial start, ReadablePartial end, int flags) {
        return formatDateRange(context, toMillis(start), toMillis(end), flags);
    }

    public static String formatDateRange(Context context, ReadableInstant start, ReadableInstant end, int flags) {
        return formatDateRange(context, toMillis(start), toMillis(end), flags);
    }

    private static String formatDateRange(Context context, long startMillis, long endMillis, int flags) {
        if (startMillis != endMillis) {
            endMillis += 1000;
        }
        return android.text.format.DateUtils.formatDateRange(context, startMillis, endMillis, flags | 8192);
    }

    private static long toMillis(ReadablePartial time) {
        return time.toDateTime(EPOCH).getMillis();
    }

    private static long toMillis(ReadableInstant time) {
        return (time instanceof DateTime ? (DateTime) time : new DateTime((Object) time)).withZoneRetainFields(DateTimeZone.UTC).getMillis();
    }

    public static boolean isToday(ReadableInstant time) {
        return LocalDate.now().compareTo((ReadablePartial) new LocalDate((Object) time)) == 0;
    }
}
