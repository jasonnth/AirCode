package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzua {
    private static final Map<String, Integer> zzahA = new HashMap(zzahz.length);
    private static final String[] zzahz = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};

    static {
        for (int i = 0; i < zzahz.length; i++) {
            zzahA.put(zzahz[i], Integer.valueOf(i));
        }
    }

    public static String zzaP(int i) {
        if (i < 0 || i >= zzahz.length) {
            return null;
        }
        return zzahz[i];
    }

    public static int zzcm(String str) {
        Integer num = (Integer) zzahA.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 44).append("[").append(str).append("] is not a valid global search section name").toString());
    }

    public static int zzqG() {
        return zzahz.length;
    }
}
