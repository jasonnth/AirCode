package com.mparticle.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.mparticle.BuildConfig;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.MessageManager.C4597a;
import com.mparticle.messaging.AbstractCloudMessage;
import com.mparticle.messaging.CloudAction;
import com.mparticle.messaging.MPCloudNotificationMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.mparticle.internal.m */
class C4620m extends Handler {

    /* renamed from: e */
    private static final String[] f3785e = {"breadcrumb_time", "message"};

    /* renamed from: f */
    private static final String[] f3786f = {DbHelper.TABLE_ID};

    /* renamed from: a */
    private final SQLiteOpenHelper f3787a;

    /* renamed from: b */
    private final Context f3788b;

    /* renamed from: c */
    private SQLiteDatabase f3789c;

    /* renamed from: d */
    private final C4621n f3790d;

    public C4620m(Looper looper, C4621n nVar, SQLiteOpenHelper sQLiteOpenHelper, Context context) {
        super(looper);
        this.f3790d = nVar;
        this.f3787a = sQLiteOpenHelper;
        this.f3788b = context;
    }

    /* renamed from: c */
    private boolean m2317c() {
        if (this.f3789c == null) {
            try {
                this.f3789c = this.f3787a.getWritableDatabase();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public void handleMessage(Message msg) {
        JSONObject jSONObject = null;
        if (m2317c()) {
            this.f3790d.mo44828m();
            switch (msg.what) {
                case 0:
                    try {
                        C4609g gVar = (C4609g) msg.obj;
                        gVar.put("cs", MessageManager.m2153a());
                        String string = gVar.getString("dt");
                        if ("ss".equals(string)) {
                            m2319d(gVar);
                        } else {
                            m2310a(gVar.mo44872b(), gVar.getLong("ct"), 0);
                            gVar.put("id", UUID.randomUUID().toString());
                        }
                        if ("x".equals(string)) {
                            m2313a((JSONObject) gVar);
                        }
                        if ("ast".equals(string)) {
                            m2315b(gVar);
                        }
                        if (!"pm".equals(string) || !gVar.has("bhv") || m2314a(gVar)) {
                            m2320e(gVar);
                            this.f3790d.mo44805a(gVar);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        ConfigManager.log(LogLevel.ERROR, e, "Error saving message to mParticle DB.");
                        return;
                    }
                case 1:
                    try {
                        JSONObject jSONObject2 = (JSONObject) msg.obj;
                        m2311a(jSONObject2.getString("sid"), jSONObject2.getString("attrs"));
                        return;
                    } catch (Exception e2) {
                        ConfigManager.log(LogLevel.ERROR, e2, "Error updating session attributes in mParticle DB.");
                        return;
                    }
                case 2:
                    try {
                        Session session = (Session) msg.obj;
                        m2310a(session.f3728b, session.f3730d, session.mo44853f());
                        return;
                    } catch (Exception e3) {
                        ConfigManager.log(LogLevel.ERROR, e3, "Error updating session end time in mParticle DB");
                        return;
                    }
                case 3:
                    try {
                        String str = (String) msg.obj;
                        String[] strArr = {"start_time", "end_time", "session_length", "attributes"};
                        Cursor query = this.f3789c.query("sessions", strArr, "session_id=?", new String[]{str}, null, null, null);
                        if (query.moveToFirst()) {
                            long j = query.getLong(0);
                            long j2 = query.getLong(1);
                            long j3 = query.getLong(2);
                            String string2 = query.getString(3);
                            if (string2 != null) {
                                jSONObject = new JSONObject(string2);
                            }
                            try {
                                C4609g a = this.f3790d.mo44793a(str, j, j2, j3, jSONObject);
                                a.put("id", UUID.randomUUID().toString());
                                m2320e(a);
                            } catch (JSONException e4) {
                                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle session end message");
                            }
                        } else {
                            ConfigManager.log(LogLevel.ERROR, "Error creating session end, no entry for sessionId in mParticle DB");
                        }
                        query.close();
                        if (msg.arg1 == 1) {
                            this.f3790d.mo44829n();
                            return;
                        }
                        return;
                    } catch (Exception e5) {
                        ConfigManager.log(LogLevel.ERROR, e5, "Error creating session end message in mParticle DB");
                        return;
                    }
                case 4:
                    try {
                        String[] strArr2 = {"session_id"};
                        Cursor query2 = this.f3789c.query("sessions", strArr2, "api_key=?", new String[]{this.f3790d.mo44825l()}, null, null, null);
                        while (query2.moveToNext()) {
                            sendMessage(obtainMessage(3, 0, 0, query2.getString(0)));
                        }
                        query2.close();
                        return;
                    } catch (Exception e6) {
                        ConfigManager.log(LogLevel.ERROR, e6, "Error processing initialization in mParticle DB");
                        return;
                    }
                case 5:
                    try {
                        C4609g gVar2 = (C4609g) msg.obj;
                        gVar2.put("id", UUID.randomUUID().toString());
                        m2316c(gVar2);
                        return;
                    } catch (Exception e7) {
                        ConfigManager.log(LogLevel.ERROR, e7, "Error saving breadcrumb to mParticle DB");
                        return;
                    }
                case 6:
                    try {
                        m2308a((AbstractCloudMessage) msg.obj, msg.getData().getString("appstate"));
                        return;
                    } catch (Exception e8) {
                        ConfigManager.log(LogLevel.ERROR, e8, "Error saving GCM message to mParticle DB", e8.toString());
                        return;
                    }
                case 7:
                    m2306a((C4597a) msg.obj);
                    return;
                case 8:
                    try {
                        m2318d();
                        return;
                    } catch (Exception e9) {
                        ConfigManager.log(LogLevel.ERROR, e9, "Error while clearing provider GCM messages: ", e9.toString());
                        return;
                    }
                case 9:
                    try {
                        m2312a((List) msg.obj);
                        return;
                    } catch (Exception e10) {
                        if (BuildConfig.MP_DEBUG.booleanValue()) {
                            ConfigManager.log(LogLevel.ERROR, e10, "Error while inserting reporting messages: ", e10.toString());
                            return;
                        }
                        return;
                    }
                case 10:
                    try {
                        m2307a((C4599c) msg.obj, this.f3790d);
                        return;
                    } catch (Exception e11) {
                        ConfigManager.log(LogLevel.ERROR, e11, "Error while removing user attribute: ", e11.toString());
                        return;
                    }
                case 11:
                    try {
                        mo44911a((C4600d) msg.obj);
                        return;
                    } catch (Exception e12) {
                        ConfigManager.log(LogLevel.ERROR, e12, "Error while setting user attribute: ", e12.toString());
                        return;
                    }
                case 12:
                    try {
                        m2309a((String) msg.obj, msg.arg1);
                        return;
                    } catch (Exception e13) {
                        ConfigManager.log(LogLevel.ERROR, e13, "Error while incrementing user attribute: ", e13.toString());
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m2309a(String str, int i) {
        String num;
        TreeMap a = mo44910a();
        if (a.containsKey(str) || !mo44912b().containsKey(str)) {
            String str2 = (String) a.get(str);
            if (str2 == null) {
                num = Integer.toString(i);
            } else {
                try {
                    num = Integer.toString(Integer.parseInt(str2) + i);
                } catch (NumberFormatException e) {
                    ConfigManager.log(LogLevel.ERROR, "Error while attempting to increment user attribute - existing attribute is not a number.");
                    return;
                }
            }
            C4600d dVar = new C4600d();
            dVar.f3724a = new HashMap(1);
            dVar.f3724a.put(str, num);
            mo44911a(dVar);
            MParticle.getInstance().getKitManager().setUserAttribute(str, num);
            return;
        }
        ConfigManager.log(LogLevel.ERROR, "Error while attempting to increment user attribute - existing attribute is a list, which can't be incremented.");
    }

    /* renamed from: a */
    private void m2307a(C4599c cVar, C4621n nVar) {
        Map allUserAttributes = MParticle.getInstance().getAllUserAttributes();
        String[] strArr = {cVar.f3722a};
        try {
            this.f3789c.beginTransaction();
            int delete = this.f3789c.delete("attributes", "attribute_key = ?", strArr);
            if (nVar != null && delete > 0) {
                nVar.mo44818d(cVar.f3722a);
                nVar.mo44794a(cVar.f3722a, null, allUserAttributes.get(cVar.f3722a), true, false, cVar.f3723b);
            }
            this.f3789c.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            this.f3789c.endTransaction();
        }
    }

    /* renamed from: d */
    private void m2318d() {
        this.f3789c.delete("gcm_messages", "content_id = ?", new String[]{Integer.toString(-1)});
    }

    /* renamed from: a */
    private boolean m2314a(C4609g gVar) {
        Cursor cursor;
        boolean z;
        int i;
        int optInt = gVar.optInt("bhv");
        try {
            ConfigManager.log(LogLevel.DEBUG, "Validating GCM behaviors...");
            String[] strArr = {Integer.toString(gVar.getInt("content_id"))};
            cursor = this.f3789c.query("gcm_messages", null, "content_id =?", strArr, null, null, null);
            long j = 0;
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(cursor.getColumnIndex("behavior"));
                    cursor.close();
                    if ((optInt & 2) == 2 && (i2 & 8) == 8) {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return false;
                    } else if ((optInt & 8) == 8 && (i2 & 2) == 2) {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return false;
                    } else {
                        if ((i2 & 1) == 1) {
                            i = optInt & -2;
                        } else {
                            i = optInt;
                        }
                        if ((i2 & 16) == 16) {
                            i &= -17;
                        }
                        if ((i & 16) == 16) {
                            j = gVar.mo44871a();
                        }
                        gVar.put("bhv", i);
                        if (i != i2) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("behavior", Integer.valueOf(i));
                            if (j > 0) {
                                contentValues.put("displayed_time", Long.valueOf(j));
                            }
                            if (this.f3789c.update("gcm_messages", contentValues, "content_id =?", strArr) > 0) {
                                ConfigManager.log(LogLevel.DEBUG, "Updated GCM with content ID: " + gVar.getInt("content_id") + " and behavior(s): " + m2305a(i));
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                } else {
                    z = true;
                }
                if (cursor == null || cursor.isClosed()) {
                    return z;
                }
                cursor.close();
                return z;
            } catch (Exception e) {
                e = e;
                try {
                    ConfigManager.log(LogLevel.DEBUG, e, "Failed to update GCM message.");
                    if (cursor != null || cursor.isClosed()) {
                        return true;
                    }
                    cursor.close();
                    return true;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
            ConfigManager.log(LogLevel.DEBUG, e, "Failed to update GCM message.");
            if (cursor != null) {
            }
            return true;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: a */
    private String m2305a(int i) {
        String str = "";
        if ((i & 2) == 2) {
            return str + "direct-open, ";
        }
        if ((i & 8) == 8) {
            return str + "influence-open, ";
        }
        if ((i & 1) == 1) {
            return str + "received, ";
        }
        if ((i & 16) == 16) {
            return str + "displayed, ";
        }
        return str;
    }

    /* renamed from: a */
    private void m2306a(C4597a aVar) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            Cursor query = this.f3789c.query("gcm_messages", null, "content_id != -1 and displayed_time > 0 and displayed_time > " + (aVar.f3718a - aVar.f3719b) + " and ((" + "behavior" + " & " + 8 + "" + ") != " + 8 + ")", null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.f3790d.mo44801a(query.getInt(query.getColumnIndex("content_id")), query.getString(query.getColumnIndex("payload")), (CloudAction) null, query.getString(query.getColumnIndex("appstate")), 8);
                } catch (Exception e) {
                    e = e;
                    cursor = query;
                    try {
                        ConfigManager.log(LogLevel.ERROR, e, "Error logging influence-open message to mParticle DB ", e.toString());
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        cursor2.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = query;
                    cursor2.close();
                    throw th;
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private void m2315b(C4609g gVar) {
        Cursor cursor;
        try {
            cursor = this.f3789c.query("gcm_messages", null, "displayed_time > 0", null, null, null, "displayed_time desc limit 1");
            try {
                if (cursor.moveToFirst()) {
                    gVar.put("pay", cursor.getString(cursor.getColumnIndex("payload")));
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
                e = e;
                try {
                    ConfigManager.log(LogLevel.DEBUG, "Failed to append latest push notification payload: " + e.toString());
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: a */
    private void m2313a(JSONObject jSONObject) throws JSONException {
        Cursor cursor;
        try {
            cursor = this.f3789c.query("breadcrumbs", f3785e, null, null, null, null, "breadcrumb_time desc limit " + ConfigManager.getBreadcrumbLimit());
            try {
                if (cursor.getCount() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    int columnIndex = cursor.getColumnIndex("message");
                    while (cursor.moveToNext()) {
                        jSONArray.put(new JSONObject(cursor.getString(columnIndex)));
                    }
                    jSONObject.put("bc", jSONArray);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
                e = e;
                try {
                    ConfigManager.log(LogLevel.DEBUG, "Error while appending breadcrumbs: " + e.toString());
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: c */
    private void m2316c(C4609g gVar) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("api_key", this.f3790d.mo44825l());
        contentValues.put("breadcrumb_time", Long.valueOf(gVar.getLong("ct")));
        contentValues.put("session_id", gVar.mo44872b());
        contentValues.put("message", gVar.toString());
        this.f3789c.insert("breadcrumbs", null, contentValues);
        Cursor query = this.f3789c.query("breadcrumbs", f3786f, null, null, null, null, " _id desc limit 1");
        if (query.moveToFirst()) {
            int i = query.getInt(0);
            if (i > ConfigManager.getBreadcrumbLimit()) {
                this.f3789c.delete("breadcrumbs", " _id < ?", new String[]{Integer.toString(i - ConfigManager.getBreadcrumbLimit())});
            }
        }
    }

    /* renamed from: a */
    private void m2308a(AbstractCloudMessage abstractCloudMessage, String str) throws JSONException {
        ContentValues contentValues = new ContentValues();
        if (abstractCloudMessage instanceof MPCloudNotificationMessage) {
            contentValues.put("content_id", Integer.valueOf(((MPCloudNotificationMessage) abstractCloudMessage).getContentId()));
            contentValues.put("campaign_id", Integer.valueOf(((MPCloudNotificationMessage) abstractCloudMessage).getCampaignId()));
            contentValues.put("expiration", Long.valueOf(((MPCloudNotificationMessage) abstractCloudMessage).getExpiration()));
            contentValues.put("displayed_time", Long.valueOf(abstractCloudMessage.getActualDeliveryTime()));
        } else {
            contentValues.put("content_id", Integer.valueOf(-1));
            contentValues.put("campaign_id", Integer.valueOf(0));
            contentValues.put("expiration", Long.valueOf(System.currentTimeMillis() + 86400000));
            contentValues.put("displayed_time", Long.valueOf(System.currentTimeMillis()));
        }
        contentValues.put("payload", abstractCloudMessage.getRedactedJsonPayload().toString());
        contentValues.put("behavior", Integer.valueOf(0));
        contentValues.put("message_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("appstate", str);
        this.f3789c.replace("gcm_messages", null, contentValues);
    }

    /* renamed from: d */
    private void m2319d(C4609g gVar) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("api_key", this.f3790d.mo44825l());
        contentValues.put("session_id", gVar.mo44872b());
        contentValues.put("start_time", Long.valueOf(gVar.getLong("ct")));
        contentValues.put("end_time", Long.valueOf(gVar.getLong("ct")));
        contentValues.put("session_length", Integer.valueOf(0));
        contentValues.put("app_info", this.f3790d.mo44833r().mo44863d(this.f3788b).toString());
        contentValues.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, this.f3790d.mo44833r().mo44862c(this.f3788b).toString());
        this.f3789c.insert("sessions", null, contentValues);
    }

    /* renamed from: a */
    private void m2312a(List<JsonReportingMessage> list) throws JSONException {
        try {
            this.f3789c.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                JsonReportingMessage jsonReportingMessage = (JsonReportingMessage) list.get(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("report_time", Long.valueOf(jsonReportingMessage.getTimestamp()));
                contentValues.put("module_id", Integer.valueOf(jsonReportingMessage.getModuleId()));
                contentValues.put("message", jsonReportingMessage.toJson().toString());
                contentValues.put("session_id", jsonReportingMessage.getSessionId());
                this.f3789c.insert("reporting", null, contentValues);
            }
            this.f3789c.setTransactionSuccessful();
        } catch (Exception e) {
            if (BuildConfig.MP_DEBUG.booleanValue()) {
                ConfigManager.log(LogLevel.ERROR, "Error inserting reporting message: " + e.toString());
            }
        } finally {
            this.f3789c.endTransaction();
        }
    }

    /* renamed from: e */
    private void m2320e(C4609g gVar) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("api_key", this.f3790d.mo44825l());
        contentValues.put("message_time", Long.valueOf(gVar.getLong("ct")));
        String b = gVar.mo44872b();
        contentValues.put("session_id", b);
        if ("NO-SESSION".equals(b)) {
            gVar.remove("sid");
        }
        contentValues.put("message", gVar.toString());
        if (gVar.getString("dt") == "fr") {
            contentValues.put("upload_status", Integer.valueOf(2));
        } else {
            contentValues.put("upload_status", Integer.valueOf(1));
        }
        this.f3789c.insert("messages", null, contentValues);
    }

    /* renamed from: a */
    private void m2311a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("attributes", str2);
        this.f3789c.update("sessions", contentValues, "session_id=?", new String[]{str});
    }

    /* renamed from: a */
    private void m2310a(String str, long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("end_time", Long.valueOf(j));
        if (j2 > 0) {
            contentValues.put("session_length", Long.valueOf(j2));
        }
        this.f3789c.update("sessions", contentValues, "session_id=?", new String[]{str});
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v12, types: [java.util.TreeMap<java.lang.String, java.lang.String>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.TreeMap<java.lang.String, java.lang.String> mo44910a() {
        /*
            r10 = this;
            r8 = 0
            boolean r0 = r10.m2317c()
            if (r0 != 0) goto L_0x0009
            r0 = r8
        L_0x0008:
            return r0
        L_0x0009:
            java.util.TreeMap r9 = new java.util.TreeMap
            java.util.Comparator r0 = java.lang.String.CASE_INSENSITIVE_ORDER
            r9.<init>(r0)
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ Exception -> 0x004b }
            r0 = 0
            java.lang.String r1 = "1"
            r4[r0] = r1     // Catch:{ Exception -> 0x004b }
            android.database.sqlite.SQLiteDatabase r0 = r10.f3789c     // Catch:{ Exception -> 0x004b }
            java.lang.String r1 = "attributes"
            r2 = 0
            java.lang.String r3 = "is_list != ?"
            r5 = 0
            r6 = 0
            java.lang.String r7 = "attribute_key, created_time desc"
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x004b }
            java.lang.String r0 = "attribute_key"
            int r0 = r8.getColumnIndex(r0)     // Catch:{ Exception -> 0x004b }
            java.lang.String r1 = "attribute_value"
            int r1 = r8.getColumnIndex(r1)     // Catch:{ Exception -> 0x004b }
        L_0x0039:
            boolean r2 = r8.moveToNext()     // Catch:{ Exception -> 0x004b }
            if (r2 == 0) goto L_0x006e
            java.lang.String r2 = r8.getString(r0)     // Catch:{ Exception -> 0x004b }
            java.lang.String r3 = r8.getString(r1)     // Catch:{ Exception -> 0x004b }
            r9.put(r2, r3)     // Catch:{ Exception -> 0x004b }
            goto L_0x0039
        L_0x004b:
            r0 = move-exception
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.ERROR     // Catch:{ all -> 0x007a }
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ all -> 0x007a }
            r3 = 0
            java.lang.String r4 = "Error while querying user attributes: "
            r2[r3] = r4     // Catch:{ all -> 0x007a }
            r3 = 1
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x007a }
            r2[r3] = r4     // Catch:{ all -> 0x007a }
            com.mparticle.internal.ConfigManager.log(r1, r0, r2)     // Catch:{ all -> 0x007a }
            if (r8 == 0) goto L_0x006c
            boolean r0 = r8.isClosed()
            if (r0 != 0) goto L_0x006c
            r8.close()
        L_0x006c:
            r0 = r9
            goto L_0x0008
        L_0x006e:
            if (r8 == 0) goto L_0x006c
            boolean r0 = r8.isClosed()
            if (r0 != 0) goto L_0x006c
            r8.close()
            goto L_0x006c
        L_0x007a:
            r0 = move-exception
            if (r8 == 0) goto L_0x0086
            boolean r1 = r8.isClosed()
            if (r1 != 0) goto L_0x0086
            r8.close()
        L_0x0086:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.C4620m.mo44910a():java.util.TreeMap");
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v3 */
    /* JADX WARNING: type inference failed for: r8v4 */
    /* JADX WARNING: type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v17, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: type inference failed for: r8v6 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r8v10 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r8v6
      assigns: []
      uses: []
      mth insns count: 63
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.TreeMap<java.lang.String, java.util.List<java.lang.String>> mo44912b() {
        /*
            r10 = this;
            r8 = 0
            boolean r0 = r10.m2317c()
            if (r0 != 0) goto L_0x0009
            r0 = r8
        L_0x0008:
            return r0
        L_0x0009:
            java.util.TreeMap r9 = new java.util.TreeMap
            java.util.Comparator r0 = java.lang.String.CASE_INSENSITIVE_ORDER
            r9.<init>(r0)
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ Exception -> 0x00a0 }
            r0 = 0
            java.lang.String r1 = "1"
            r4[r0] = r1     // Catch:{ Exception -> 0x00a0 }
            android.database.sqlite.SQLiteDatabase r0 = r10.f3789c     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r1 = "attributes"
            r2 = 0
            java.lang.String r3 = "is_list = ?"
            r5 = 0
            r6 = 0
            java.lang.String r7 = "attribute_key, created_time desc"
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x00a0 }
            java.lang.String r0 = "attribute_key"
            int r2 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            java.lang.String r0 = "attribute_value"
            int r3 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
        L_0x0039:
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = r1.getString(r2)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            boolean r4 = r0.equals(r8)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            if (r4 != 0) goto L_0x0052
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            r4.<init>()     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            r9.put(r0, r4)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            r8 = r0
        L_0x0052:
            java.lang.Object r0 = r9.get(r0)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            java.util.List r0 = (java.util.List) r0     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            java.lang.String r4 = r1.getString(r3)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            r0.add(r4)     // Catch:{ Exception -> 0x0060, all -> 0x009d }
            goto L_0x0039
        L_0x0060:
            r0 = move-exception
            r8 = r1
        L_0x0062:
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.ERROR     // Catch:{ all -> 0x0090 }
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ all -> 0x0090 }
            r3 = 0
            java.lang.String r4 = "Error while querying user attribute lists: "
            r2[r3] = r4     // Catch:{ all -> 0x0090 }
            r3 = 1
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0090 }
            r2[r3] = r4     // Catch:{ all -> 0x0090 }
            com.mparticle.internal.ConfigManager.log(r1, r0, r2)     // Catch:{ all -> 0x0090 }
            if (r8 == 0) goto L_0x0082
            boolean r0 = r8.isClosed()
            if (r0 != 0) goto L_0x0082
            r8.close()
        L_0x0082:
            r0 = r9
            goto L_0x0008
        L_0x0084:
            if (r1 == 0) goto L_0x0082
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L_0x0082
            r1.close()
            goto L_0x0082
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            if (r8 == 0) goto L_0x009c
            boolean r1 = r8.isClosed()
            if (r1 != 0) goto L_0x009c
            r8.close()
        L_0x009c:
            throw r0
        L_0x009d:
            r0 = move-exception
            r8 = r1
            goto L_0x0091
        L_0x00a0:
            r0 = move-exception
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.C4620m.mo44912b():java.util.TreeMap");
    }

    /* renamed from: a */
    public void mo44911a(C4600d dVar) {
        if (m2317c()) {
            Map allUserAttributes = MParticle.getInstance().getAllUserAttributes();
            this.f3789c.beginTransaction();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (dVar.f3725b != null) {
                    for (Entry entry : dVar.f3725b.entrySet()) {
                        String str = (String) entry.getKey();
                        List<String> list = (List) entry.getValue();
                        boolean z = this.f3789c.delete("attributes", "attribute_key = ?", new String[]{str}) == 0;
                        ContentValues contentValues = new ContentValues();
                        for (String str2 : list) {
                            contentValues.put("attribute_key", str);
                            contentValues.put("attribute_value", str2);
                            contentValues.put("is_list", Boolean.valueOf(true));
                            contentValues.put("created_time", Long.valueOf(currentTimeMillis));
                            this.f3789c.insert("attributes", null, contentValues);
                        }
                        this.f3790d.mo44794a(str, list, allUserAttributes.get(str), false, z, dVar.f3726c);
                    }
                }
                if (dVar.f3724a != null) {
                    for (Entry entry2 : dVar.f3724a.entrySet()) {
                        String str3 = (String) entry2.getKey();
                        String str4 = (String) entry2.getValue();
                        boolean z2 = this.f3789c.delete("attributes", "attribute_key = ?", new String[]{str3}) == 0;
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("attribute_key", str3);
                        contentValues2.put("attribute_value", str4);
                        contentValues2.put("is_list", Boolean.valueOf(false));
                        contentValues2.put("created_time", Long.valueOf(currentTimeMillis));
                        this.f3789c.insert("attributes", null, contentValues2);
                        this.f3790d.mo44794a(str3, str4, allUserAttributes.get(str3), false, z2, dVar.f3726c);
                    }
                }
                this.f3789c.setTransactionSuccessful();
            } catch (Exception e) {
                ConfigManager.log(LogLevel.ERROR, e, "Error while adding user attributes: ", e.toString());
            } finally {
                this.f3789c.endTransaction();
            }
        }
    }
}
