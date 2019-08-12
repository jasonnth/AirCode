package p005cn.jpush.android.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;

/* renamed from: cn.jpush.android.util.Logger */
public class Logger {
    private static final String COMMON_TAG = "JPush";
    private static final String DATE_PATTERN = "MM.dd_HH:mm:ss_SSS";
    private static final boolean FILE_ENABLED = false;
    private static final boolean INTERNAL_USE = true;

    /* renamed from: IT */
    private static final String f1927IT = " ";
    private static final int KEEP_LATEST_LOG_FILE_DAYS = 2;
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String LEVEL_ERROR = "ERROR";
    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_VERBOSE = "TRACE";
    private static final String LEVEL_WARN = "WARN";
    private static final int LOG_ENABLED_LEVEL = 2;
    private static final int MAX_LOGS_LINE_TO_WRITE_FILE = 500;
    private static final String TAG = "Logger";
    private static ILogger _delegate = new DefaultLogger();
    private static boolean _isExternalStorageAvailable = false;
    private static boolean _isFlusing = false;
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
    /* access modifiers changed from: private */
    public static ArrayList<String> mCachedLog = new ArrayList<>();

    static void setDelegate(ILogger logger) {
        _delegate = logger;
    }

    private static boolean isServerEnabled(int level) {
        if (level >= 2) {
            return INTERNAL_USE;
        }
        return false;
    }

    /* renamed from: v */
    public static void m1428v(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(2)) {
            _delegate.mo18531v(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: vv */
    public static void m1430vv(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(2)) {
            _delegate.mo18531v(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: d */
    public static void m1416d(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(3)) {
            _delegate.mo18525d(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: dd */
    public static void m1418dd(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(3)) {
            _delegate.mo18525d(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: i */
    public static void m1424i(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(4)) {
            _delegate.mo18529i(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: ii */
    public static void m1426ii(String tag, String msg) {
        if (JPush.DEBUG_MODE && isServerEnabled(4)) {
            _delegate.mo18529i(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: w */
    public static void m1432w(String tag, String msg) {
        if (isServerEnabled(5)) {
            _delegate.mo18533w(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: ww */
    public static void m1434ww(String tag, String msg) {
        if (isServerEnabled(5)) {
            _delegate.mo18533w(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: e */
    public static void m1420e(String tag, String msg) {
        if (isServerEnabled(6)) {
            _delegate.mo18527e(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: ee */
    public static void m1422ee(String tag, String msg) {
        if (isServerEnabled(6)) {
            _delegate.mo18527e(COMMON_TAG, "[" + tag + "] " + msg);
        }
    }

    /* renamed from: v */
    public static void m1429v(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(2)) {
            _delegate.mo18532v(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: vv */
    public static void m1431vv(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(2)) {
            _delegate.mo18532v(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: d */
    public static void m1417d(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(3)) {
            _delegate.mo18526d(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: dd */
    public static void m1419dd(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(3)) {
            _delegate.mo18526d(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: i */
    public static void m1425i(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(4)) {
            _delegate.mo18530i(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: ii */
    public static void m1427ii(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(4)) {
            _delegate.mo18530i(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: w */
    public static void m1433w(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(5)) {
            _delegate.mo18534w(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: ww */
    public static void m1435ww(String tag, String msg, Throwable tr) {
        if (JPush.DEBUG_MODE && isServerEnabled(5)) {
            _delegate.mo18534w(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: e */
    public static void m1421e(String tag, String msg, Throwable tr) {
        if (isServerEnabled(6)) {
            _delegate.mo18528e(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    /* renamed from: ee */
    public static void m1423ee(String tag, String msg, Throwable tr) {
        if (isServerEnabled(6)) {
            _delegate.mo18528e(COMMON_TAG, "[" + tag + "] " + msg, tr);
        }
    }

    public static void flushCached2File() {
    }

    private static void log2File(String level, String tag, String msg, Throwable tr) {
        if (tag == null || tag.trim().equals("")) {
            tag = TAG;
        }
        if (msg != null) {
            CharSequence formattedDate = format.format(new Date());
            BufferedReader br = new BufferedReader(new StringReader(msg), 256);
            String tag2 = StringUtils.fixedLengthString("[" + tag + "]", 24);
            while (true) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    logToFile(formattedDate + f1927IT + StringUtils.fixedLengthString(level, 5) + f1927IT + tag2 + f1927IT + line);
                } catch (IOException ie) {
                    m1420e(TAG, ie.getMessage());
                }
            }
            if (tr != null) {
                StringWriter stringWriter = new StringWriter();
                tr.printStackTrace(new PrintWriter(stringWriter));
                logToFile(formattedDate + f1927IT + level + stringWriter.toString());
            }
        }
    }

    private static void logToFile(String msg) {
        if (!_isFlusing) {
            mCachedLog.add(msg);
            if (mCachedLog.size() == 500) {
                ArrayList<String> tobeSavedLogs = mCachedLog;
                mCachedLog = new ArrayList<>();
                _isExternalStorageAvailable = AndroidUtil.isSdcardExist();
                if (_isExternalStorageAvailable) {
                    m1428v(TAG, "have writable external storage, write log file");
                    saveLogs(tobeSavedLogs);
                    return;
                }
                m1428v(TAG, "no writable external storage");
            }
        }
    }

    private static void saveLogs(final ArrayList<String> tobeSavedLogs) {
        new Thread() {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x00e1 A[Catch:{ IOException -> 0x0106 }] */
            /* JADX WARNING: Removed duplicated region for block: B:42:0x0124 A[Catch:{ IOException -> 0x0128 }] */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x013d A[Catch:{ IOException -> 0x0141 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r11 = this;
                    r0 = 0
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r8.<init>()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = p005cn.jpush.android.JPushConstants.LOG_FILE_NAME_PRE     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = "-"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = p005cn.jpush.android.util.DateUtils.getTodayDateTimeForFilename()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = "_1.txt"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r4 = r8.toString()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r3.<init>(r4)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.io.File r8 = r3.getParentFile()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r8.mkdirs()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r5 = 2
                L_0x0033:
                    boolean r8 = r3.exists()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    if (r8 == 0) goto L_0x007b
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r8.<init>()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = p005cn.jpush.android.JPushConstants.LOG_FILE_NAME_PRE     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = "-"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = p005cn.jpush.android.util.DateUtils.getTodayDateTimeForFilename()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = "_"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r8 = r8.append(r5)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = ".txt"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r4 = r8.toString()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r3.<init>(r4)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r8 = 10
                    if (r5 <= r8) goto L_0x00e8
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "Unexpected error here, so many existed error file."
                    p005cn.jpush.android.util.Logger.m1432w(r8, r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                L_0x007b:
                    java.lang.String r8 = "Logger"
                    java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r9.<init>()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r10 = "Write log file: "
                    java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r10 = r3.getName()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    p005cn.jpush.android.util.Logger.m1428v(r8, r9)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.io.FileWriter r8 = new java.io.FileWriter     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r8.<init>(r4)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    r1.<init>(r8)     // Catch:{ IOException -> 0x0152, ClassCastException -> 0x0111 }
                    java.util.ArrayList r8 = r1     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.util.Iterator r8 = r8.iterator()     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                L_0x00a9:
                    boolean r9 = r8.hasNext()     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    if (r9 == 0) goto L_0x00ec
                    java.lang.Object r7 = r8.next()     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    r9.<init>()     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.lang.StringBuilder r9 = r9.append(r7)     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.lang.String r10 = "\n"
                    java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    r1.write(r9)     // Catch:{ IOException -> 0x00cd, ClassCastException -> 0x014f, all -> 0x014c }
                    goto L_0x00a9
                L_0x00cd:
                    r6 = move-exception
                    r0 = r1
                L_0x00cf:
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "write logs to file error"
                    p005cn.jpush.android.util.Logger.m1421e(r8, r9, r6)     // Catch:{ all -> 0x0133 }
                    java.util.ArrayList r8 = p005cn.jpush.android.util.Logger.mCachedLog     // Catch:{ IOException -> 0x0106 }
                    r8.clear()     // Catch:{ IOException -> 0x0106 }
                    if (r0 == 0) goto L_0x00e4
                    r0.close()     // Catch:{ IOException -> 0x0106 }
                L_0x00e4:
                    p005cn.jpush.android.util.Logger.removeOldDebugLogFiles()
                    return
                L_0x00e8:
                    int r5 = r5 + 1
                    goto L_0x0033
                L_0x00ec:
                    java.util.ArrayList r8 = p005cn.jpush.android.util.Logger.mCachedLog     // Catch:{ IOException -> 0x00fa }
                    r8.clear()     // Catch:{ IOException -> 0x00fa }
                    if (r1 == 0) goto L_0x00f8
                    r1.close()     // Catch:{ IOException -> 0x00fa }
                L_0x00f8:
                    r0 = r1
                    goto L_0x00e4
                L_0x00fa:
                    r6 = move-exception
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "close file stream error"
                    p005cn.jpush.android.util.Logger.m1421e(r8, r9, r6)
                    r0 = r1
                    goto L_0x00e4
                L_0x0106:
                    r6 = move-exception
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "close file stream error"
                    p005cn.jpush.android.util.Logger.m1421e(r8, r9, r6)
                    goto L_0x00e4
                L_0x0111:
                    r2 = move-exception
                L_0x0112:
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "ClassCastException maybe caused by (String)(tobeSavedLogs.iterator().next())"
                    p005cn.jpush.android.util.Logger.m1420e(r8, r9)     // Catch:{ all -> 0x0133 }
                    java.util.ArrayList r8 = p005cn.jpush.android.util.Logger.mCachedLog     // Catch:{ IOException -> 0x0128 }
                    r8.clear()     // Catch:{ IOException -> 0x0128 }
                    if (r0 == 0) goto L_0x00e4
                    r0.close()     // Catch:{ IOException -> 0x0128 }
                    goto L_0x00e4
                L_0x0128:
                    r6 = move-exception
                    java.lang.String r8 = "Logger"
                    java.lang.String r9 = "close file stream error"
                    p005cn.jpush.android.util.Logger.m1421e(r8, r9, r6)
                    goto L_0x00e4
                L_0x0133:
                    r8 = move-exception
                L_0x0134:
                    java.util.ArrayList r9 = p005cn.jpush.android.util.Logger.mCachedLog     // Catch:{ IOException -> 0x0141 }
                    r9.clear()     // Catch:{ IOException -> 0x0141 }
                    if (r0 == 0) goto L_0x0140
                    r0.close()     // Catch:{ IOException -> 0x0141 }
                L_0x0140:
                    throw r8
                L_0x0141:
                    r6 = move-exception
                    java.lang.String r9 = "Logger"
                    java.lang.String r10 = "close file stream error"
                    p005cn.jpush.android.util.Logger.m1421e(r9, r10, r6)
                    goto L_0x0140
                L_0x014c:
                    r8 = move-exception
                    r0 = r1
                    goto L_0x0134
                L_0x014f:
                    r2 = move-exception
                    r0 = r1
                    goto L_0x0112
                L_0x0152:
                    r6 = move-exception
                    goto L_0x00cf
                */
                throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.Logger.C16181.run():void");
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public static void removeOldDebugLogFiles() {
        File[] listFiles;
        try {
            File dir = new File(JPushConstants.LOG_FILE_PATH);
            if (dir.exists()) {
                int begin = JPushConstants.LOG_FILE_PRE.length() + 1;
                int end = begin + DateUtils.PATTERN_DATETIME_FILENAME.length();
                for (File file : dir.listFiles()) {
                    if (DateUtils.isDaysAgo(DateUtils.parseDateInFilename(file.getName().substring(begin, end)), 2)) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            m1420e(TAG, e.getMessage());
        }
    }
}
