package com.tencent.p313mm.sdk.channel.compatible;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/* renamed from: com.tencent.mm.sdk.channel.compatible.MMPluginProviderConstants */
public class MMPluginProviderConstants {

    /* renamed from: com.tencent.mm.sdk.channel.compatible.MMPluginProviderConstants$Resolver */
    public static final class Resolver {
        public static int getType(Object value) {
            if (value == null) {
                Log.e("PluginProvider.Resolver", "unresolve failed, null value");
                return 0;
            } else if (value instanceof Integer) {
                return 1;
            } else {
                if (value instanceof Long) {
                    return 2;
                }
                if (value instanceof String) {
                    return 3;
                }
                if (value instanceof Boolean) {
                    return 4;
                }
                if (value instanceof Float) {
                    return 5;
                }
                if (value instanceof Double) {
                    return 6;
                }
                Log.e("PluginProvider.Resolver", "unresolve failed, unknown type=" + value.getClass().toString());
                return 0;
            }
        }

        public static boolean unresolveObj(ContentValues values, Object value) {
            int type = getType(value);
            if (type == 0) {
                return false;
            }
            values.put("type", Integer.valueOf(type));
            values.put("value", value.toString());
            return true;
        }

        public static Object resolveObj(int type, String value) {
            switch (type) {
                case 1:
                    return Integer.valueOf(value);
                case 2:
                    return Long.valueOf(value);
                case 3:
                    return value;
                case 4:
                    return Boolean.valueOf(value);
                case 5:
                    return Float.valueOf(value);
                case 6:
                    return Double.valueOf(value);
                default:
                    try {
                        Log.e("PluginProvider.Resolver", "unknown type");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
            }
        }
    }

    /* renamed from: com.tencent.mm.sdk.channel.compatible.MMPluginProviderConstants$SharedPref */
    public static final class SharedPref implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
