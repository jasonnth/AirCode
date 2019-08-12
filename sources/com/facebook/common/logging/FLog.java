package com.facebook.common.logging;

public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();

    public static void setLoggingDelegate(LoggingDelegate delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        sHandler = delegate;
    }

    public static boolean isLoggable(int level) {
        return sHandler.isLoggable(level);
    }

    public static void setMinimumLoggingLevel(int level) {
        sHandler.setMinimumLoggingLevel(level);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.getMinimumLoggingLevel();
    }

    /* renamed from: v */
    public static void m1835v(String tag, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, msg);
        }
    }

    /* renamed from: v */
    public static void m1836v(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: v */
    public static void m1837v(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: v */
    public static void m1838v(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: v */
    public static void m1839v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: v */
    public static void m1827v(Class<?> cls, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(getTag(cls), msg);
        }
    }

    /* renamed from: v */
    public static void m1828v(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: v */
    public static void m1829v(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: v */
    public static void m1830v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (isLoggable(2)) {
            m1827v(cls, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: v */
    public static void m1831v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: v */
    public static void m1841v(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(tag, formatString(msg, args));
        }
    }

    /* renamed from: v */
    public static void m1842v(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30395v(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: v */
    public static void m1833v(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30394v(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: v */
    public static void m1834v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30395v(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: v */
    public static void m1840v(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30395v(tag, msg, tr);
        }
    }

    /* renamed from: v */
    public static void m1832v(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo30395v(getTag(cls), msg, tr);
        }
    }

    /* renamed from: d */
    public static void m1795d(String tag, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(tag, msg);
        }
    }

    /* renamed from: d */
    public static void m1796d(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: d */
    public static void m1797d(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: d */
    public static void m1798d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: d */
    public static void m1799d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: d */
    public static void m1787d(Class<?> cls, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), msg);
        }
    }

    /* renamed from: d */
    public static void m1788d(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: d */
    public static void m1789d(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: d */
    public static void m1790d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: d */
    public static void m1791d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: d */
    public static void m1801d(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m1795d(tag, formatString(msg, args));
        }
    }

    /* renamed from: d */
    public static void m1802d(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m1800d(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: d */
    public static void m1793d(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30383d(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: d */
    public static void m1794d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30384d(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: d */
    public static void m1800d(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30384d(tag, msg, tr);
        }
    }

    /* renamed from: d */
    public static void m1792d(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo30384d(getTag(cls), msg, tr);
        }
    }

    /* renamed from: i */
    public static void m1819i(String tag, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m1820i(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, formatString(msg, arg1));
        }
    }

    /* renamed from: i */
    public static void m1821i(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: i */
    public static void m1822i(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: i */
    public static void m1823i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: i */
    public static void m1811i(Class<?> cls, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), msg);
        }
    }

    /* renamed from: i */
    public static void m1812i(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), formatString(msg, arg1));
        }
    }

    /* renamed from: i */
    public static void m1813i(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    /* renamed from: i */
    public static void m1814i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    /* renamed from: i */
    public static void m1815i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    /* renamed from: i */
    public static void m1825i(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(tag, formatString(msg, args));
        }
    }

    /* renamed from: i */
    public static void m1826i(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30389i(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: i */
    public static void m1817i(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30388i(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: i */
    public static void m1818i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(4)) {
            sHandler.mo30389i(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: i */
    public static void m1824i(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30389i(tag, msg, tr);
        }
    }

    /* renamed from: i */
    public static void m1816i(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo30389i(getTag(cls), msg, tr);
        }
    }

    /* renamed from: w */
    public static void m1847w(String tag, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30396w(tag, msg);
        }
    }

    /* renamed from: w */
    public static void m1843w(Class<?> cls, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30396w(getTag(cls), msg);
        }
    }

    /* renamed from: w */
    public static void m1849w(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30396w(tag, formatString(msg, args));
        }
    }

    /* renamed from: w */
    public static void m1850w(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30397w(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: w */
    public static void m1845w(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30396w(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: w */
    public static void m1846w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(5)) {
            m1844w(cls, formatString(msg, args), tr);
        }
    }

    /* renamed from: w */
    public static void m1848w(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30397w(tag, msg, tr);
        }
    }

    /* renamed from: w */
    public static void m1844w(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo30397w(getTag(cls), msg, tr);
        }
    }

    /* renamed from: e */
    public static void m1807e(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30385e(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m1803e(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30385e(getTag(cls), msg);
        }
    }

    /* renamed from: e */
    public static void m1809e(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30385e(tag, formatString(msg, args));
        }
    }

    /* renamed from: e */
    public static void m1810e(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30386e(tag, formatString(msg, args), tr);
        }
    }

    /* renamed from: e */
    public static void m1805e(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30385e(getTag(cls), formatString(msg, args));
        }
    }

    /* renamed from: e */
    public static void m1806e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30386e(getTag(cls), formatString(msg, args), tr);
        }
    }

    /* renamed from: e */
    public static void m1808e(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30386e(tag, msg, tr);
        }
    }

    /* renamed from: e */
    public static void m1804e(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo30386e(getTag(cls), msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg);
        }
    }

    public static void wtf(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args));
        }
    }

    public static void wtf(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args), tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args));
        }
    }

    public static void wtf(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg, tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg, tr);
        }
    }

    private static String formatString(String str, Object... args) {
        return String.format(null, str, args);
    }

    private static String getTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
