package net.danlew.android.joda;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResUtils {
    private static Map<Class<?>, Map<String, Integer>> sIdentifierCache = new ConcurrentHashMap();

    private static String convertPathToResource(String path) {
        File file = new File(path);
        List<String> parts = new ArrayList<>();
        do {
            parts.add(file.getName());
            file = file.getParentFile();
        } while (file != null);
        StringBuffer sb = new StringBuffer();
        for (int a = parts.size() - 1; a >= 0; a--) {
            if (sb.length() > 0) {
                sb.append("_");
            }
            sb.append((String) parts.get(a));
        }
        return sb.toString().replace('-', '_').replace("+", "plus").toLowerCase(Locale.US);
    }

    public static String getTzResource(String tzFile) {
        return "joda_" + convertPathToResource(tzFile);
    }

    public static int getIdentifier(Class<?> type, String name) {
        Map<String, Integer> typeCache;
        if (!sIdentifierCache.containsKey(type)) {
            typeCache = new ConcurrentHashMap<>();
            sIdentifierCache.put(type, typeCache);
        } else {
            typeCache = (Map) sIdentifierCache.get(type);
        }
        if (typeCache.containsKey(name)) {
            return ((Integer) typeCache.get(name)).intValue();
        }
        try {
            int resId = type.getField(name).getInt(null);
            if (resId == 0) {
                return resId;
            }
            typeCache.put(name, Integer.valueOf(resId));
            return resId;
        } catch (Exception e) {
            Log.e("JodaTimeAndroid", "Failed to retrieve identifier: type=" + type + " name=" + name, e);
            return 0;
        }
    }
}
