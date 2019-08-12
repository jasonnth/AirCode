package org.apache.sanselan.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Debug {
    private static long counter = 0;
    public static String newline = "\r\n";
    private static final SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSS");

    public static void debug(String message) {
        System.out.println(message);
    }

    public static String getDebug(String message) {
        return message;
    }

    public static void debug() {
        newline();
    }

    public static void newline() {
        System.out.print(newline);
    }

    public static String getDebug(String message, int[] v) {
        StringBuffer result = new StringBuffer();
        if (v == null) {
            result.append(message + " (" + null + ")" + newline);
        } else {
            result.append(message + " (" + v.length + ")" + newline);
            for (int i = 0; i < v.length; i++) {
                result.append("\t" + v[i] + newline);
            }
            result.append(newline);
        }
        return result.toString();
    }

    public static String getDebug(String message, byte[] v) {
        return getDebug(message, v, 250);
    }

    public static String getDebug(String message, byte[] v, int max) {
        char c;
        StringBuffer result = new StringBuffer();
        if (v == null) {
            result.append(message + " (" + null + ")" + newline);
        } else {
            result.append(message + " (" + v.length + ")" + newline);
            int i = 0;
            while (i < max && i < v.length) {
                int b = v[i] & 255;
                if (b == 0 || b == 10 || b == 11 || b == 13) {
                    c = ' ';
                } else {
                    c = (char) b;
                }
                result.append("\t" + i + ": " + b + " (" + c + ", 0x" + Integer.toHexString(b) + ")" + newline);
                i++;
            }
            if (v.length > max) {
                result.append("\t..." + newline);
            }
            result.append(newline);
        }
        return result.toString();
    }

    public static String getDebug(String message, char[] v) {
        StringBuffer result = new StringBuffer();
        if (v == null) {
            result.append(getDebug(message + " (" + null + ")") + newline);
        } else {
            result.append(getDebug(message + " (" + v.length + ")") + newline);
            for (int i = 0; i < v.length; i++) {
                result.append(getDebug("\t" + v[i] + " (" + (v[i] & 255)) + ")" + newline);
            }
            result.append(newline);
        }
        return result.toString();
    }

    public static void debug(String message, Map map) {
        debug(getDebug(message, map));
    }

    public static String getDebug(String message, Map map) {
        StringBuffer result = new StringBuffer();
        if (map == null) {
            return getDebug(message + " map: " + null);
        }
        ArrayList keys = new ArrayList(map.keySet());
        result.append(getDebug(message + " map: " + keys.size()) + newline);
        for (int i = 0; i < keys.size(); i++) {
            Object key = keys.get(i);
            result.append(getDebug("\t" + i + ": '" + key + "' -> '" + map.get(key) + "'") + newline);
        }
        result.append(newline);
        return result.toString();
    }

    public static void debug(String message, Object value) {
        if (value == null) {
            debug(message, "null");
        } else if (value instanceof char[]) {
            debug(message, (char[]) (char[]) value);
        } else if (value instanceof byte[]) {
            debug(message, (byte[]) (byte[]) value);
        } else if (value instanceof int[]) {
            debug(message, (int[]) (int[]) value);
        } else if (value instanceof String) {
            debug(message, (String) value);
        } else if (value instanceof List) {
            debug(message, (List) value);
        } else if (value instanceof Map) {
            debug(message, (Map) value);
        } else if (value instanceof File) {
            debug(message, (File) value);
        } else if (value instanceof Date) {
            debug(message, (Date) value);
        } else if (value instanceof Calendar) {
            debug(message, (Calendar) value);
        } else {
            debug(message, value.toString());
        }
    }

    public static String getType(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Object[]) {
            return "[Object[]: " + ((Object[]) value).length + "]";
        }
        if (value instanceof char[]) {
            return "[char[]: " + ((char[]) value).length + "]";
        }
        if (value instanceof byte[]) {
            return "[byte[]: " + ((byte[]) value).length + "]";
        }
        if (value instanceof short[]) {
            return "[short[]: " + ((short[]) value).length + "]";
        }
        if (value instanceof int[]) {
            return "[int[]: " + ((int[]) value).length + "]";
        }
        if (value instanceof long[]) {
            return "[long[]: " + ((long[]) value).length + "]";
        }
        if (value instanceof float[]) {
            return "[float[]: " + ((float[]) value).length + "]";
        }
        if (value instanceof double[]) {
            return "[double[]: " + ((double[]) value).length + "]";
        }
        if (value instanceof boolean[]) {
            return "[boolean[]: " + ((boolean[]) value).length + "]";
        }
        return value.getClass().getName();
    }

    public static void debug(String message, byte[] v) {
        debug(getDebug(message, v));
    }

    public static void debug(String message, char[] v) {
        debug(getDebug(message, v));
    }

    public static void debug(String message, Calendar value) {
        debug(message, value == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(value.getTime()));
    }

    public static void debug(String message, Date value) {
        debug(message, value == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(value));
    }

    public static void debug(String message, File file) {
        debug(message + ": " + (file == null ? "null" : file.getPath()));
    }

    public static void debug(String message, int value) {
        debug(message + ": " + value);
    }

    public static void debug(String message, int[] v) {
        debug(getDebug(message, v));
    }

    public static void debug(String message, List v) {
        StringBuilder append = new StringBuilder().append(" [");
        long j = counter;
        counter = 1 + j;
        String suffix = append.append(j).append("]").toString();
        debug(message + " (" + v.size() + ")" + suffix);
        for (int i = 0; i < v.size(); i++) {
            debug("\t" + v.get(i).toString() + suffix);
        }
        debug();
    }

    public static void debug(String message, String value) {
        debug(message + " " + value);
    }

    public static void debug(Throwable e) {
        debug(getDebug(e));
    }

    public static String getDebug(Throwable e) {
        return getDebug(e, -1);
    }

    public static String getDebug(Throwable e, int max) {
        StringBuffer result = new StringBuffer();
        String datetime = timestamp.format(new Date()).toLowerCase();
        result.append(newline);
        result.append("Throwable: " + (e == null ? "" : "(" + e.getClass().getName() + ")") + ":" + datetime + newline);
        result.append("Throwable: " + (e == null ? "null" : e.getLocalizedMessage()) + newline);
        result.append(newline);
        result.append(getStackTrace(e, max));
        result.append("Caught here:" + newline);
        result.append(getStackTrace(new Exception(), max, 1));
        result.append(newline);
        return result.toString();
    }

    public static String getStackTrace(Throwable e, int limit) {
        return getStackTrace(e, limit, 0);
    }

    public static String getStackTrace(Throwable e, int limit, int skip) {
        StringBuffer result = new StringBuffer();
        if (e != null) {
            StackTraceElement[] stes = e.getStackTrace();
            if (stes != null) {
                int i = skip;
                while (i < stes.length && (limit < 0 || i < limit)) {
                    StackTraceElement ste = stes[i];
                    result.append("\tat " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")" + newline);
                    i++;
                }
                if (limit >= 0 && stes.length > limit) {
                    result.append("\t..." + newline);
                }
            }
            result.append(newline);
        }
        return result.toString();
    }
}
