package com.tencent.p313mm.sdk.openapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import com.tencent.p313mm.sdk.channel.compatible.MMPluginProviderConstants.Resolver;
import com.tencent.p313mm.sdk.channel.compatible.MMPluginProviderConstants.SharedPref;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.tencent.mm.sdk.openapi.MMSharedPreferences */
class MMSharedPreferences implements SharedPreferences {
    private final String[] columns = {DbHelper.TABLE_ID, "key", "type", "value"};

    /* renamed from: cr */
    private final ContentResolver f4048cr;
    private REditor editor = null;
    private final HashMap<String, Object> values = new HashMap<>();

    /* renamed from: com.tencent.mm.sdk.openapi.MMSharedPreferences$REditor */
    private static class REditor implements Editor {
        private boolean clear = false;

        /* renamed from: cr */
        private final ContentResolver f4049cr;
        private final Set<String> remove = new HashSet();
        private final Map<String, Object> values = new HashMap();

        public REditor(ContentResolver cr) {
            this.f4049cr = cr;
        }

        public Editor putString(String key, String value) {
            this.values.put(key, value);
            this.remove.remove(key);
            return this;
        }

        public Editor putInt(String key, int value) {
            this.values.put(key, Integer.valueOf(value));
            this.remove.remove(key);
            return this;
        }

        public Editor putLong(String key, long value) {
            this.values.put(key, Long.valueOf(value));
            this.remove.remove(key);
            return this;
        }

        public Editor putFloat(String key, float value) {
            this.values.put(key, Float.valueOf(value));
            this.remove.remove(key);
            return this;
        }

        public Editor putBoolean(String key, boolean value) {
            this.values.put(key, Boolean.valueOf(value));
            this.remove.remove(key);
            return this;
        }

        public Editor remove(String key) {
            this.remove.add(key);
            return this;
        }

        public Editor clear() {
            this.clear = true;
            return this;
        }

        public boolean commit() {
            ContentValues kv = new ContentValues();
            if (this.clear) {
                this.f4049cr.delete(SharedPref.CONTENT_URI, null, null);
                this.clear = false;
            }
            for (String key : this.remove) {
                this.f4049cr.delete(SharedPref.CONTENT_URI, "key = ?", new String[]{key});
            }
            for (Entry<String, Object> e : this.values.entrySet()) {
                if (Resolver.unresolveObj(kv, e.getValue())) {
                    this.f4049cr.update(SharedPref.CONTENT_URI, kv, "key = ?", new String[]{(String) e.getKey()});
                }
            }
            return true;
        }

        public void apply() {
        }

        public Editor putStringSet(String arg0, Set<String> set) {
            return null;
        }
    }

    public MMSharedPreferences(Context context) {
        this.f4048cr = context.getContentResolver();
    }

    private Object getValue(String key) {
        try {
            Cursor cu = this.f4048cr.query(SharedPref.CONTENT_URI, this.columns, "key = ?", new String[]{key}, null);
            if (cu == null) {
                return null;
            }
            int typeIdx = cu.getColumnIndex("type");
            int valueIdx = cu.getColumnIndex("value");
            Object obj = null;
            if (cu.moveToFirst()) {
                obj = Resolver.resolveObj(cu.getInt(typeIdx), cu.getString(valueIdx));
            }
            cu.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, ?> getAll() {
        try {
            Cursor cu = this.f4048cr.query(SharedPref.CONTENT_URI, this.columns, null, null, null);
            if (cu == null) {
                return null;
            }
            int keyIdx = cu.getColumnIndex("key");
            int typeIdx = cu.getColumnIndex("type");
            int valueIdx = cu.getColumnIndex("value");
            while (cu.moveToNext()) {
                this.values.put(cu.getString(keyIdx), Resolver.resolveObj(cu.getInt(typeIdx), cu.getString(valueIdx)));
            }
            cu.close();
            return this.values;
        } catch (Exception e) {
            e.printStackTrace();
            return this.values;
        }
    }

    public String getString(String key, String defValue) {
        Object value = getValue(key);
        return (value == null || !(value instanceof String)) ? defValue : (String) value;
    }

    public int getInt(String key, int defValue) {
        Object value = getValue(key);
        return (value == null || !(value instanceof Integer)) ? defValue : ((Integer) value).intValue();
    }

    public long getLong(String key, long defValue) {
        Object value = getValue(key);
        return (value == null || !(value instanceof Long)) ? defValue : ((Long) value).longValue();
    }

    public float getFloat(String key, float defValue) {
        Object value = getValue(key);
        return (value == null || !(value instanceof Float)) ? defValue : ((Float) value).floatValue();
    }

    public boolean getBoolean(String key, boolean defValue) {
        Object value = getValue(key);
        return (value == null || !(value instanceof Boolean)) ? defValue : ((Boolean) value).booleanValue();
    }

    public boolean contains(String key) {
        return getValue(key) != null;
    }

    public Editor edit() {
        if (this.editor == null) {
            this.editor = new REditor(this.f4048cr);
        }
        return this.editor;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
    }

    public Set<String> getStringSet(String arg0, Set<String> set) {
        return null;
    }
}
