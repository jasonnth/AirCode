package p005cn.jpush.android.util;

import android.content.Context;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.data.JLog;

/* renamed from: cn.jpush.android.util.JLogger */
public class JLogger {

    /* renamed from: D */
    private static final int f1922D = 2;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /* renamed from: E */
    private static final int f1923E = 16;

    /* renamed from: I */
    private static final int f1924I = 4;

    /* renamed from: V */
    private static final int f1925V = 1;

    /* renamed from: W */
    private static final int f1926W = 8;
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
    private static JLoggerReportHelper jLoggerReportHelper = new JLoggerReportHelper();

    /* renamed from: v */
    public static void m1414v(String tag, String msg) {
        processLog(1, tag, msg);
    }

    /* renamed from: d */
    public static void m1411d(String tag, String msg) {
        processLog(2, tag, msg);
    }

    /* renamed from: i */
    public static void m1413i(String tag, String msg) {
        processLog(4, tag, msg);
    }

    /* renamed from: w */
    public static void m1415w(String tag, String msg) {
        processLog(8, tag, msg);
    }

    /* renamed from: e */
    public static void m1412e(String tag, String msg) {
        processLog(16, tag, msg);
    }

    private static void processLog(int level, String tag, String msg) {
        String levelStr = "V";
        switch (level) {
            case 1:
                if (JPush.DEBUG_MODE) {
                    Log.v(tag, msg);
                }
                levelStr = "V";
                break;
            case 2:
                if (JPush.DEBUG_MODE) {
                    Log.d(tag, msg);
                }
                levelStr = "D";
                break;
            case 4:
                if (JPush.DEBUG_MODE) {
                    Log.i(tag, msg);
                }
                levelStr = "I";
                break;
            case 8:
                if (JPush.DEBUG_MODE) {
                    Log.w(tag, msg);
                }
                levelStr = "W";
                break;
            case 16:
                if (JPush.DEBUG_MODE) {
                    Log.e(tag, msg);
                }
                levelStr = "E";
                break;
        }
        if (jLoggerReportHelper != null && jLoggerReportHelper.b_ReportEnabled && (jLoggerReportHelper.reportLevels & level) != 0) {
            JLog log = new JLog(level, levelStr, tag, msg, format.format(new Date()));
            if (jLoggerReportHelper != null) {
                jLoggerReportHelper.processLog(log);
            }
        }
    }

    public static void parseModalJson(String json, Context context) {
        if (context != null && jLoggerReportHelper != null) {
            jLoggerReportHelper.parseJsonCommand(context, json);
        }
    }

    public static void reportByHeartbeats() {
        if (jLoggerReportHelper != null) {
            jLoggerReportHelper.batchReportByHeartBeats();
        }
    }
}
