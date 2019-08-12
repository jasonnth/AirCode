package p004bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: bo.app.dp */
public final class C0455dp {

    /* renamed from: a */
    private static final String f380a = AppboyLogger.getAppboyLogTag(C0455dp.class);

    /* renamed from: b */
    private static final Locale f381b = Locale.US;

    /* renamed from: c */
    private static final TimeZone f382c = TimeZone.getTimeZone("UTC");

    /* renamed from: a */
    public static long m515a() {
        return System.currentTimeMillis() / 1000;
    }

    /* renamed from: b */
    public static double m522b() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    /* renamed from: c */
    public static long m523c() {
        return System.currentTimeMillis();
    }

    /* renamed from: a */
    public static Date m521a(String str, C0628q qVar) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m1739w(f380a, String.format("Null or blank date string received: %s", new Object[]{str}));
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(f382c);
        try {
            switch (qVar) {
                case SHORT:
                    simpleDateFormat.applyPattern(C0628q.SHORT.mo7328a());
                    return simpleDateFormat.parse(str);
                case LONG:
                    simpleDateFormat.applyPattern(C0628q.LONG.mo7328a());
                    return simpleDateFormat.parse(str);
                default:
                    AppboyLogger.m1739w(f380a, "Unsupported date format. Returning null");
                    return null;
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(f380a, String.format("Exception parsing date %s. Returning null", new Object[]{str}), e);
            return null;
        }
        AppboyLogger.m1736e(f380a, String.format("Exception parsing date %s. Returning null", new Object[]{str}), e);
        return null;
    }

    /* renamed from: a */
    public static String m517a(Date date, C0628q qVar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(f382c);
        switch (qVar) {
            case SHORT:
                simpleDateFormat.applyPattern(C0628q.SHORT.mo7328a());
                break;
            case LONG:
                simpleDateFormat.applyPattern(C0628q.LONG.mo7328a());
                break;
            default:
                AppboyLogger.m1739w(f380a, "Unsupported date format. Defaulting to " + C0628q.LONG.mo7328a());
                simpleDateFormat.applyPattern(C0628q.LONG.mo7328a());
                break;
        }
        return simpleDateFormat.format(date);
    }

    /* renamed from: a */
    public static Date m518a(int i, int i2, int i3) {
        return m519a(i, i2, i3, 0, 0, 0);
    }

    /* renamed from: a */
    public static Date m519a(int i, int i2, int i3, int i4, int i5, int i6) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2, i3, i4, i5, i6);
        gregorianCalendar.setTimeZone(f382c);
        return gregorianCalendar.getTime();
    }

    /* renamed from: a */
    public static Date m520a(long j) {
        return new Date(1000 * j);
    }

    /* renamed from: a */
    public static long m516a(Date date) {
        return date.getTime() / 1000;
    }
}
