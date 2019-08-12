package net.danlew.android.joda;

import android.content.Context;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import net.danlew.android.joda.R.raw;
import org.joda.time.DateTimeZone;
import org.joda.time.p322tz.DateTimeZoneBuilder;
import org.joda.time.p322tz.Provider;

public class ResourceZoneInfoProvider implements Provider {
    private final Map<String, Object> iZoneInfoMap;
    private Context mAppContext;

    public ResourceZoneInfoProvider(Context context) throws IOException {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.mAppContext = context.getApplicationContext();
        this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }

    public DateTimeZone getZone(String id) {
        if (id == null) {
            return null;
        }
        Object obj = this.iZoneInfoMap.get(id);
        if (obj == null) {
            return null;
        }
        if (id.equals(obj)) {
            return loadZoneData(id);
        }
        if (!(obj instanceof SoftReference)) {
            return getZone((String) obj);
        }
        DateTimeZone tz = (DateTimeZone) ((SoftReference) obj).get();
        if (tz == null) {
            return loadZoneData(id);
        }
        return tz;
    }

    public Set<String> getAvailableIDs() {
        return new TreeSet(this.iZoneInfoMap.keySet());
    }

    /* access modifiers changed from: protected */
    public void uncaughtException(Exception ex) {
        ex.printStackTrace();
    }

    private InputStream openResource(String name) throws IOException {
        if (this.mAppContext == null) {
            throw new RuntimeException("Need to call JodaTimeAndroid.init() before using joda-time-android");
        }
        String resName = ResUtils.getTzResource(name);
        int resId = ResUtils.getIdentifier(raw.class, resName);
        if (resId != 0) {
            return this.mAppContext.getResources().openRawResource(resId);
        }
        throw new IOException("Resource not found: \"" + name + "\" (resName: \"" + resName + "\")");
    }

    private DateTimeZone loadZoneData(String id) {
        DateTimeZone tz;
        InputStream in = null;
        try {
            in = openResource(id);
            tz = DateTimeZoneBuilder.readFrom(in, id);
            this.iZoneInfoMap.put(id, new SoftReference(tz));
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException ex) {
            uncaughtException(ex);
            this.iZoneInfoMap.remove(id);
            tz = null;
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e2) {
                }
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e3) {
                }
            }
        }
        return tz;
    }

    private static Map<String, Object> loadZoneInfoMap(InputStream in) throws IOException {
        Map<String, Object> map = new ConcurrentHashMap<>();
        DataInputStream din = new DataInputStream(in);
        try {
            readZoneInfoMap(din, map);
            map.put("UTC", new SoftReference(DateTimeZone.UTC));
            return map;
        } finally {
            try {
                din.close();
            } catch (IOException e) {
            }
        }
    }

    private static void readZoneInfoMap(DataInputStream din, Map<String, Object> zimap) throws IOException {
        int size = din.readUnsignedShort();
        String[] pool = new String[size];
        for (int i = 0; i < size; i++) {
            pool[i] = din.readUTF().intern();
        }
        int size2 = din.readUnsignedShort();
        int i2 = 0;
        while (i2 < size2) {
            try {
                zimap.put(pool[din.readUnsignedShort()], pool[din.readUnsignedShort()]);
                i2++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }
}
