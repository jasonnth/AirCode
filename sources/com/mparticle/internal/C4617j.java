package com.mparticle.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.MParticle;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.mparticle.internal.j */
class C4617j extends SQLiteOpenHelper {

    /* renamed from: b */
    private static String[] f3779b = {"content_id", "campaign_id", "expiration", "displayed_time"};

    /* renamed from: c */
    private static String f3780c = "expiration < ? and displayed_time > 0";

    /* renamed from: d */
    private static final String[] f3781d = {DbHelper.TABLE_ID, "message", "message_time", "upload_status", "session_id"};

    /* renamed from: e */
    private static String f3782e = String.format("(%s = %d) and (%s != ?)", new Object[]{"upload_status", Integer.valueOf(3), "session_id"});

    /* renamed from: f */
    private static String[] f3783f = {Integer.toString(3)};

    /* renamed from: a */
    private final Context f3784a;

    /* renamed from: a */
    static Cursor m2284a(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query("gcm_messages", f3779b, null, null, null, null, "expiration desc");
    }

    /* renamed from: b */
    static int m2286b(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.delete("gcm_messages", f3780c, new String[]{Long.toString(System.currentTimeMillis())});
    }

    /* renamed from: a */
    static Cursor m2285a(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.query("messages", f3781d, f3782e, new String[]{str}, null, null, "session_id, _id asc");
    }

    /* renamed from: c */
    public static Cursor m2287c(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query("sessions", null, null, null, null, null, null);
    }

    /* renamed from: d */
    static Cursor m2288d(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query("messages", null, "upload_status != ?", f3783f, null, null, "session_id, _id asc");
    }

    /* renamed from: e */
    static Cursor m2289e(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query("reporting", null, null, null, null, null, "_id asc");
    }

    C4617j(Context context) {
        super(context, "mparticle.db", null, 6);
        this.f3784a = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sessions (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, start_time INTEGER NOT NULL,end_time INTEGER NOT NULL,session_length INTEGER NOT NULL,attributes TEXT, cfuuid TEXT,app_info TEXT, device_info TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, message TEXT, upload_status INTEGER, message_time INTEGER NOT NULL, message_type TEXT, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS uploads (_id INTEGER PRIMARY KEY AUTOINCREMENT, api_key STRING NOT NULL, message TEXT, message_time INTEGER NOT NULL, cfuuid TEXT, session_id TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS commands (_id INTEGER PRIMARY KEY AUTOINCREMENT, url STRING NOT NULL, method STRING NOT NULL, post_data TEXT, headers TEXT, timestamp INTEGER, session_id TEXT, api_key STRING NOT NULL, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS breadcrumbs (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, message TEXT, breadcrumb_time INTEGER NOT NULL, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS gcm_messages (content_id INTEGER PRIMARY KEY, payload TEXT NOT NULL, appstate TEXT NOT NULL, message_time INTEGER NOT NULL, expiration INTEGER NOT NULL, behavior INTEGER NOT NULL,campaign_id TEXT NOT NULL, displayed_time INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS reporting (_id INTEGER PRIMARY KEY AUTOINCREMENT, module_id INTEGER NOT NULL, message TEXT NOT NULL, session_id STRING NOT NULL, report_time INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS attributes (_id INTEGER PRIMARY KEY AUTOINCREMENT, attribute_key COLLATE NOCASE NOT NULL, attribute_value TEXT, is_list INTEGER NOT NULL, created_time INTEGER NOT NULL );");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS sessions (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, start_time INTEGER NOT NULL,end_time INTEGER NOT NULL,session_length INTEGER NOT NULL,attributes TEXT, cfuuid TEXT,app_info TEXT, device_info TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, message TEXT, upload_status INTEGER, message_time INTEGER NOT NULL, message_type TEXT, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS uploads (_id INTEGER PRIMARY KEY AUTOINCREMENT, api_key STRING NOT NULL, message TEXT, message_time INTEGER NOT NULL, cfuuid TEXT, session_id TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS commands (_id INTEGER PRIMARY KEY AUTOINCREMENT, url STRING NOT NULL, method STRING NOT NULL, post_data TEXT, headers TEXT, timestamp INTEGER, session_id TEXT, api_key STRING NOT NULL, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS breadcrumbs (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id STRING NOT NULL, api_key STRING NOT NULL, message TEXT, breadcrumb_time INTEGER NOT NULL, cfuuid TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS gcm_messages (content_id INTEGER PRIMARY KEY, payload TEXT NOT NULL, appstate TEXT NOT NULL, message_time INTEGER NOT NULL, expiration INTEGER NOT NULL, behavior INTEGER NOT NULL,campaign_id TEXT NOT NULL, displayed_time INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS reporting (_id INTEGER PRIMARY KEY AUTOINCREMENT, module_id INTEGER NOT NULL, message TEXT NOT NULL, session_id STRING NOT NULL, report_time INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS attributes (_id INTEGER PRIMARY KEY AUTOINCREMENT, attribute_key COLLATE NOCASE NOT NULL, attribute_value TEXT, is_list INTEGER NOT NULL, created_time INTEGER NOT NULL );");
        if (oldVersion < 5) {
            m2292h(db);
        }
        if (oldVersion < 6) {
            m2290f(db);
            m2291g(db);
        }
    }

    /* renamed from: f */
    private void m2290f(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sessions ADD COLUMN app_info TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sessions ADD COLUMN device_info TEXT");
    }

    /* renamed from: g */
    private void m2291g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE reporting ADD COLUMN session_id STRING");
    }

    /* renamed from: h */
    private void m2292h(SQLiteDatabase sQLiteDatabase) {
        String str;
        SharedPreferences sharedPreferences = this.f3784a.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        try {
            JSONObject jSONObject = new JSONObject(sharedPreferences.getString("mp::user_attrs::" + MParticle.getInstance().getConfigManager().getApiKey(), null));
            Iterator keys = jSONObject.keys();
            double currentTimeMillis = (double) System.currentTimeMillis();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                try {
                    Object obj = jSONObject.get(str2);
                    if (obj != null) {
                        str = obj.toString();
                    } else {
                        str = null;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("attribute_key", str2);
                    contentValues.put("attribute_value", str);
                    contentValues.put("is_list", Boolean.valueOf(false));
                    contentValues.put("created_time", Double.valueOf(currentTimeMillis));
                    sQLiteDatabase.insert("attributes", null, contentValues);
                } catch (JSONException e) {
                }
            }
        } catch (Exception e2) {
        } finally {
            sharedPreferences.edit().remove("mp::user_attrs::" + MParticle.getInstance().getConfigManager().getApiKey()).apply();
        }
    }
}
