package com.mparticle.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.BuildConfig;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.C4613i.C4615b;
import com.mparticle.internal.C4613i.C4616c;
import com.mparticle.segmentation.SegmentListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.mparticle.internal.r */
public class C4627r extends Handler {

    /* renamed from: a */
    volatile boolean f3800a = true;

    /* renamed from: b */
    String[] f3801b = {DbHelper.TABLE_ID, "message"};

    /* renamed from: c */
    String f3802c = "\"dt\":\"se\"";

    /* renamed from: d */
    private final Context f3803d;

    /* renamed from: e */
    private final C4617j f3804e;

    /* renamed from: f */
    private final AppStateManager f3805f;

    /* renamed from: g */
    private final MessageManager f3806g;

    /* renamed from: h */
    private ConfigManager f3807h;

    /* renamed from: i */
    private SQLiteDatabase f3808i;

    /* renamed from: j */
    private final SharedPreferences f3809j;

    /* renamed from: k */
    private final String f3810k;

    /* renamed from: l */
    private final C4623p f3811l;

    /* renamed from: m */
    private C4612h f3812m;

    public C4627r(Context context, Looper looper, ConfigManager configManager, C4617j jVar, AppStateManager appStateManager, MessageManager messageManager) {
        super(looper);
        this.f3807h = configManager;
        this.f3803d = context;
        this.f3810k = this.f3807h.getApiKey();
        this.f3805f = appStateManager;
        this.f3811l = new C4623p(this.f3803d);
        this.f3809j = this.f3803d.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        this.f3804e = jVar;
        this.f3806g = messageManager;
        try {
            mo44926a((C4612h) new C4613i(configManager, this.f3809j, context));
        } catch (MalformedURLException e) {
        }
    }

    public void handleMessage(Message msg) {
        try {
            if (this.f3808i == null) {
                this.f3808i = this.f3804e.getWritableDatabase();
            }
            switch (msg.what) {
                case 1:
                case 5:
                    long uploadInterval = this.f3807h.getUploadInterval();
                    if (this.f3800a && !this.f3812m.mo44887c() && (uploadInterval > 0 || msg.arg1 == 1)) {
                        m2346d(false);
                        if (mo44929a(false)) {
                            sendEmptyMessage(3);
                        }
                    }
                    if (this.f3805f.getSession().mo44847a() && uploadInterval > 0 && msg.arg1 == 0) {
                        sendEmptyMessageDelayed(1, uploadInterval);
                        return;
                    }
                    return;
                case 3:
                    removeMessages(3);
                    m2346d(true);
                    if (this.f3800a) {
                        mo44929a(true);
                        return;
                    }
                    return;
                case 4:
                    MParticle.getInstance().getKitManager().loadKitLibrary();
                    this.f3812m.mo44885a();
                    return;
                case 6:
                    this.f3807h.delayedStart();
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            if (BuildConfig.MP_DEBUG.booleanValue()) {
                ConfigManager.log(LogLevel.DEBUG, "UploadHandler Exception while handling message: " + e.toString());
            }
        } catch (VerifyError e2) {
            if (BuildConfig.MP_DEBUG.booleanValue()) {
                ConfigManager.log(LogLevel.DEBUG, "UploadHandler VerifyError while handling message" + e2.toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:112:0x024e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x024f, code lost:
        r1 = r2;
        r2 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0292, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0293, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        com.mparticle.internal.ConfigManager.log(com.mparticle.MParticle.LogLevel.DEBUG, "Error preparing batch upload in mParticle DB: " + r0.getMessage());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0292 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:80:0x016f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072 A[Catch:{ all -> 0x0296 }] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2346d(boolean r13) {
        /*
            r12 = this;
            r1 = 0
            com.mparticle.internal.AppStateManager r0 = r12.f3805f
            com.mparticle.internal.Session r0 = r0.getSession()
            java.lang.String r6 = r0.f3728b
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
            r0.beginTransaction()     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
            if (r13 == 0) goto L_0x00bc
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
            android.database.Cursor r4 = com.mparticle.internal.C4617j.m2285a(r0, r6)     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
        L_0x0016:
            int r0 = r4.getCount()     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r0 <= 0) goto L_0x02a8
            java.lang.String r0 = "_id"
            int r2 = r4.getColumnIndex(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.String r0 = "message"
            int r3 = r4.getColumnIndex(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.String r0 = "session_id"
            int r5 = r4.getColumnIndex(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            r0 = 2
            r7.<init>(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
        L_0x0037:
            boolean r0 = r4.moveToNext()     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r0 == 0) goto L_0x0111
            java.lang.String r8 = r4.getString(r5)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            int r9 = r4.getInt(r2)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.Object r0 = r7.get(r8)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            com.mparticle.internal.l r0 = (com.mparticle.internal.C4619l) r0     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r0 != 0) goto L_0x0055
            r0 = 0
            com.mparticle.internal.l r0 = r12.mo44931b(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            r7.put(r8, r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
        L_0x0055:
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.String r11 = r4.getString(r3)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r13 == 0) goto L_0x00c4
            r0.mo44901a(r10)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            r12.mo44930b(r9)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            goto L_0x0037
        L_0x0067:
            r0 = move-exception
            r2 = r1
            r3 = r4
        L_0x006a:
            java.lang.Boolean r4 = com.mparticle.BuildConfig.MP_DEBUG     // Catch:{ all -> 0x0296 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0296 }
            if (r4 == 0) goto L_0x0095
            com.mparticle.MParticle$LogLevel r4 = com.mparticle.MParticle.LogLevel.DEBUG     // Catch:{ all -> 0x0296 }
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ all -> 0x0296 }
            r6 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r7.<init>()     // Catch:{ all -> 0x0296 }
            java.lang.String r8 = "Error preparing batch upload in mParticle DB: "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0296 }
            r5[r6] = r0     // Catch:{ all -> 0x0296 }
            com.mparticle.internal.ConfigManager.log(r4, r5)     // Catch:{ all -> 0x0296 }
        L_0x0095:
            if (r3 == 0) goto L_0x00a0
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L_0x00a0
            r3.close()
        L_0x00a0:
            if (r2 == 0) goto L_0x00ab
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x00ab
            r2.close()
        L_0x00ab:
            if (r1 == 0) goto L_0x00b6
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L_0x00b6
            r1.close()
        L_0x00b6:
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i
            r0.endTransaction()
        L_0x00bb:
            return
        L_0x00bc:
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
            android.database.Cursor r4 = com.mparticle.internal.C4617j.m2288d(r0)     // Catch:{ Exception -> 0x029b, all -> 0x0286 }
            goto L_0x0016
        L_0x00c4:
            r0.mo44903b(r10)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.String r0 = "NO-SESSION"
            boolean r0 = r0.equals(r8)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r0 != 0) goto L_0x00de
            com.mparticle.MParticle r0 = com.mparticle.MParticle.getInstance()     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            com.mparticle.internal.ConfigManager r0 = r0.getConfigManager()     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            boolean r0 = r0.getIncludeSessionHistory()     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            if (r0 != 0) goto L_0x010c
        L_0x00de:
            r12.mo44930b(r9)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            goto L_0x0037
        L_0x00e3:
            r0 = move-exception
            r3 = r1
        L_0x00e5:
            if (r4 == 0) goto L_0x00f0
            boolean r2 = r4.isClosed()
            if (r2 != 0) goto L_0x00f0
            r4.close()
        L_0x00f0:
            if (r3 == 0) goto L_0x00fb
            boolean r2 = r3.isClosed()
            if (r2 != 0) goto L_0x00fb
            r3.close()
        L_0x00fb:
            if (r1 == 0) goto L_0x0106
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x0106
            r1.close()
        L_0x0106:
            android.database.sqlite.SQLiteDatabase r1 = r12.f3808i
            r1.endTransaction()
            throw r0
        L_0x010c:
            r12.mo44933c(r9)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            goto L_0x0037
        L_0x0111:
            if (r13 != 0) goto L_0x02a5
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            android.database.Cursor r2 = com.mparticle.internal.C4617j.m2289e(r0)     // Catch:{ Exception -> 0x0067, all -> 0x00e3 }
            java.lang.String r0 = "_id"
            int r3 = r2.getColumnIndex(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
        L_0x0120:
            boolean r0 = r2.moveToNext()     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            if (r0 == 0) goto L_0x0168
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.String r0 = "message"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.String r0 = r2.getString(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.String r0 = "session_id"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.String r0 = r2.getString(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            int r8 = r2.getInt(r3)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            com.mparticle.internal.l r0 = (com.mparticle.internal.C4619l) r0     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            if (r0 != 0) goto L_0x015b
            java.util.Collection r0 = r7.values()     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            java.lang.Object r0 = r0.next()     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            com.mparticle.internal.l r0 = (com.mparticle.internal.C4619l) r0     // Catch:{ Exception -> 0x0164, all -> 0x028b }
        L_0x015b:
            if (r0 == 0) goto L_0x0160
            r0.mo44905c(r5)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
        L_0x0160:
            r12.mo44935d(r8)     // Catch:{ Exception -> 0x0164, all -> 0x028b }
            goto L_0x0120
        L_0x0164:
            r0 = move-exception
            r3 = r4
            goto L_0x006a
        L_0x0168:
            r3 = r2
        L_0x0169:
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x02a0, all -> 0x028f }
            android.database.Cursor r2 = com.mparticle.internal.C4617j.m2287c(r0)     // Catch:{ Exception -> 0x02a0, all -> 0x028f }
        L_0x016f:
            boolean r0 = r2.moveToNext()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r0 == 0) goto L_0x01bc
            java.lang.String r0 = "session_id"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.lang.String r0 = r2.getString(r0)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            com.mparticle.internal.l r0 = (com.mparticle.internal.C4619l) r0     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r0 == 0) goto L_0x016f
            java.lang.String r1 = "app_info"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            java.lang.String r1 = r2.getString(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            r0.mo44907d(r5)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            java.lang.String r1 = "device_info"
            int r1 = r2.getColumnIndex(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            java.lang.String r1 = r2.getString(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            com.mparticle.internal.MessageManager r1 = r12.f3806g     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            com.mparticle.internal.b r1 = r1.mo44833r()     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            android.content.Context r8 = r12.f3803d     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            r1.mo44860a(r8, r5)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            r0.mo44908e(r5)     // Catch:{ Exception -> 0x01ba, all -> 0x0292 }
            goto L_0x016f
        L_0x01ba:
            r0 = move-exception
            goto L_0x016f
        L_0x01bc:
            java.util.Set r0 = r7.entrySet()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.util.Iterator r7 = r0.iterator()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
        L_0x01c4:
            boolean r0 = r7.hasNext()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r0 == 0) goto L_0x0259
            java.lang.Object r0 = r7.next()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.lang.Object r1 = r0.getValue()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            com.mparticle.internal.l r1 = (com.mparticle.internal.C4619l) r1     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r1 == 0) goto L_0x01c4
            java.lang.Object r0 = r0.getKey()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            org.json.JSONObject r5 = r1.mo44899a()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r5 != 0) goto L_0x01f3
            com.mparticle.internal.MessageManager r5 = r12.f3806g     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            com.mparticle.internal.b r5 = r5.mo44833r()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            android.content.Context r8 = r12.f3803d     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            org.json.JSONObject r5 = r5.mo44863d(r8)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r1.mo44907d(r5)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
        L_0x01f3:
            org.json.JSONObject r5 = r1.mo44902b()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r5 == 0) goto L_0x01ff
            boolean r5 = r0.equals(r6)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r5 == 0) goto L_0x020e
        L_0x01ff:
            com.mparticle.internal.MessageManager r5 = r12.f3806g     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            com.mparticle.internal.b r5 = r5.mo44833r()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            android.content.Context r8 = r12.f3803d     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            org.json.JSONObject r5 = r5.mo44862c(r8)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r1.mo44908e(r5)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
        L_0x020e:
            if (r13 == 0) goto L_0x0254
            org.json.JSONArray r5 = r1.mo44904c()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
        L_0x0214:
            org.json.JSONArray r8 = r12.m2343a(r5)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r1.mo44900a(r8)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            org.json.JSONObject r5 = r12.m2345b(r5)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r1.mo44909f(r5)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r12.mo44932b(r1)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r13 != 0) goto L_0x023b
            com.mparticle.MParticle r1 = com.mparticle.MParticle.getInstance()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            com.mparticle.internal.ConfigManager r1 = r1.getConfigManager()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            boolean r1 = r1.getIncludeSessionHistory()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r1 != 0) goto L_0x01c4
            boolean r1 = r0.equals(r6)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            if (r1 != 0) goto L_0x01c4
        L_0x023b:
            android.database.sqlite.SQLiteDatabase r1 = r12.f3808i     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            java.lang.String r5 = "sessions"
            java.lang.String r8 = "session_id=?"
            r9 = 1
            java.lang.String[] r9 = new java.lang.String[r9]     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r10 = 0
            r9[r10] = r0     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r1.delete(r5, r8, r9)     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            goto L_0x01c4
        L_0x024e:
            r0 = move-exception
            r1 = r2
            r2 = r3
            r3 = r4
            goto L_0x006a
        L_0x0254:
            org.json.JSONArray r5 = r1.mo44906d()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            goto L_0x0214
        L_0x0259:
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
            r0.setTransactionSuccessful()     // Catch:{ Exception -> 0x024e, all -> 0x0292 }
        L_0x025e:
            if (r4 == 0) goto L_0x0269
            boolean r0 = r4.isClosed()
            if (r0 != 0) goto L_0x0269
            r4.close()
        L_0x0269:
            if (r3 == 0) goto L_0x0274
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L_0x0274
            r3.close()
        L_0x0274:
            if (r2 == 0) goto L_0x027f
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x027f
            r2.close()
        L_0x027f:
            android.database.sqlite.SQLiteDatabase r0 = r12.f3808i
            r0.endTransaction()
            goto L_0x00bb
        L_0x0286:
            r0 = move-exception
            r3 = r1
            r4 = r1
            goto L_0x00e5
        L_0x028b:
            r0 = move-exception
            r3 = r2
            goto L_0x00e5
        L_0x028f:
            r0 = move-exception
            goto L_0x00e5
        L_0x0292:
            r0 = move-exception
            r1 = r2
            goto L_0x00e5
        L_0x0296:
            r0 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x00e5
        L_0x029b:
            r0 = move-exception
            r2 = r1
            r3 = r1
            goto L_0x006a
        L_0x02a0:
            r0 = move-exception
            r2 = r3
            r3 = r4
            goto L_0x006a
        L_0x02a5:
            r3 = r1
            goto L_0x0169
        L_0x02a8:
            r2 = r1
            r3 = r1
            goto L_0x025e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.C4627r.m2346d(boolean):void");
    }

    /* renamed from: a */
    static JSONObject m2344a() {
        Map allUserAttributes = MParticle.getInstance().getAllUserAttributes();
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : allUserAttributes.entrySet()) {
            Object value = entry.getValue();
            if (entry.getValue() instanceof List) {
                List<String> list = (List) value;
                JSONArray jSONArray = new JSONArray();
                for (String put : list) {
                    jSONArray.put(put);
                }
                try {
                    jSONObject.put((String) entry.getKey(), jSONArray);
                } catch (JSONException e) {
                }
            } else {
                try {
                    Object value2 = entry.getValue();
                    if (value2 == null) {
                        value2 = JSONObject.NULL;
                    }
                    jSONObject.put((String) entry.getKey(), value2);
                } catch (JSONException e2) {
                }
            }
        }
        return jSONObject;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:44:0x0093=Splitter:B:44:0x0093, B:32:0x006a=Splitter:B:32:0x006a} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo44929a(boolean r12) {
        /*
            r11 = this;
            r10 = 1
            r9 = 0
            r8 = 0
            android.database.sqlite.SQLiteDatabase r0 = r11.f3808i     // Catch:{ c -> 0x00c9, SSLHandshakeException -> 0x00c2, Exception -> 0x0090, all -> 0x00ad }
            java.lang.String r1 = "uploads"
            java.lang.String[] r2 = r11.f3801b     // Catch:{ c -> 0x00c9, SSLHandshakeException -> 0x00c2, Exception -> 0x0090, all -> 0x00ad }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "message_time"
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ c -> 0x00c9, SSLHandshakeException -> 0x00c2, Exception -> 0x0090, all -> 0x00ad }
            java.lang.String r0 = "_id"
            int r2 = r1.getColumnIndex(r0)     // Catch:{ c -> 0x00ce, SSLHandshakeException -> 0x00c6, Exception -> 0x00bd }
            java.lang.String r0 = "message"
            int r3 = r1.getColumnIndex(r0)     // Catch:{ c -> 0x00ce, SSLHandshakeException -> 0x00c6, Exception -> 0x00bd }
            int r0 = r1.getCount()     // Catch:{ c -> 0x00ce, SSLHandshakeException -> 0x00c6, Exception -> 0x00bd }
            if (r0 <= 0) goto L_0x002e
            com.mparticle.internal.h r0 = r11.f3812m     // Catch:{ c -> 0x00ce, SSLHandshakeException -> 0x00c6, Exception -> 0x00bd }
            r0.mo44885a()     // Catch:{ c -> 0x00ce, SSLHandshakeException -> 0x00c6, Exception -> 0x00bd }
        L_0x002e:
            r0 = r9
        L_0x002f:
            boolean r4 = r1.moveToNext()     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            if (r4 == 0) goto L_0x0084
            int r4 = r1.getInt(r2)     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            if (r12 == 0) goto L_0x0054
            com.mparticle.internal.ConfigManager r5 = r11.f3807h     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            boolean r5 = r5.getIncludeSessionHistory()     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            if (r5 != 0) goto L_0x0054
            r11.mo44936e(r4)     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            goto L_0x002f
        L_0x0047:
            r2 = move-exception
        L_0x0048:
            if (r1 == 0) goto L_0x0053
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x0053
            r1.close()
        L_0x0053:
            return r0
        L_0x0054:
            java.lang.String r5 = r1.getString(r3)     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            if (r12 != 0) goto L_0x0065
            if (r0 != 0) goto L_0x0065
            java.lang.String r6 = r11.f3802c     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            boolean r6 = r5.contains(r6)     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            if (r6 == 0) goto L_0x0065
            r0 = r10
        L_0x0065:
            r11.mo44924a(r4, r5)     // Catch:{ c -> 0x0047, SSLHandshakeException -> 0x0069, Exception -> 0x00c0 }
            goto L_0x002f
        L_0x0069:
            r2 = move-exception
        L_0x006a:
            com.mparticle.MParticle$LogLevel r2 = com.mparticle.MParticle.LogLevel.DEBUG     // Catch:{ all -> 0x00bb }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x00bb }
            r4 = 0
            java.lang.String r5 = "SSL handshake failed while preparing uploads - possible MITM attack detected."
            r3[r4] = r5     // Catch:{ all -> 0x00bb }
            com.mparticle.internal.ConfigManager.log(r2, r3)     // Catch:{ all -> 0x00bb }
            if (r1 == 0) goto L_0x0053
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x0053
            r1.close()
            goto L_0x0053
        L_0x0084:
            if (r1 == 0) goto L_0x0053
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x0053
            r1.close()
            goto L_0x0053
        L_0x0090:
            r0 = move-exception
            r1 = r8
            r0 = r9
        L_0x0093:
            com.mparticle.MParticle$LogLevel r2 = com.mparticle.MParticle.LogLevel.ERROR     // Catch:{ all -> 0x00bb }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x00bb }
            r4 = 0
            java.lang.String r5 = "Error processing batch uploads in mParticle DB"
            r3[r4] = r5     // Catch:{ all -> 0x00bb }
            com.mparticle.internal.ConfigManager.log(r2, r3)     // Catch:{ all -> 0x00bb }
            if (r1 == 0) goto L_0x0053
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x0053
            r1.close()
            goto L_0x0053
        L_0x00ad:
            r0 = move-exception
            r1 = r8
        L_0x00af:
            if (r1 == 0) goto L_0x00ba
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L_0x00ba
            r1.close()
        L_0x00ba:
            throw r0
        L_0x00bb:
            r0 = move-exception
            goto L_0x00af
        L_0x00bd:
            r0 = move-exception
            r0 = r9
            goto L_0x0093
        L_0x00c0:
            r2 = move-exception
            goto L_0x0093
        L_0x00c2:
            r0 = move-exception
            r1 = r8
            r0 = r9
            goto L_0x006a
        L_0x00c6:
            r0 = move-exception
            r0 = r9
            goto L_0x006a
        L_0x00c9:
            r0 = move-exception
            r1 = r8
            r0 = r9
            goto L_0x0048
        L_0x00ce:
            r0 = move-exception
            r0 = r9
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.C4627r.mo44929a(boolean):boolean");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44924a(int i, String str) throws IOException, C4616c {
        int i2;
        boolean z;
        int i3 = -1;
        try {
            i2 = this.f3812m.mo44884a(str);
            z = false;
        } catch (C4615b e) {
            ConfigManager.log(LogLevel.DEBUG, "This device is being sampled.");
            i2 = i3;
            z = true;
        }
        if (z || mo44928a(i2)) {
            mo44936e(i);
            return;
        }
        ConfigManager.log(LogLevel.WARNING, "Upload failed and will be retried.");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo44928a(int i) {
        return i != 429 && (202 == i || (i >= 400 && i < 500));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public C4619l mo44931b(boolean z) throws JSONException {
        C4619l a = C4619l.m2293a(z, this.f3807h, this.f3809j, this.f3812m.mo44888d());
        mo44927a(a);
        return a;
    }

    /* renamed from: a */
    private JSONArray m2343a(JSONArray jSONArray) {
        JSONArray jSONArray2;
        if (jSONArray != null) {
            jSONArray2 = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    if (jSONArray.getJSONObject(i).get("dt").equals("uic")) {
                        jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("ui");
                        jSONArray.getJSONObject(i).remove("ui");
                    }
                } catch (NullPointerException | JSONException e) {
                }
            }
        } else {
            jSONArray2 = null;
        }
        if (jSONArray2 == null) {
            return this.f3806g.mo44834s();
        }
        return jSONArray2;
    }

    /* renamed from: b */
    private JSONObject m2345b(JSONArray jSONArray) {
        JSONObject jSONObject;
        if (jSONArray != null) {
            jSONObject = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    if (jSONArray.getJSONObject(i).get("dt").equals("uac")) {
                        jSONObject = jSONArray.getJSONObject(i).getJSONObject("ua");
                        jSONArray.getJSONObject(i).remove("ua");
                    }
                } catch (NullPointerException | JSONException e) {
                }
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return m2344a();
        }
        return jSONObject;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44927a(C4619l lVar) {
        Cursor cursor = null;
        try {
            C4617j.m2286b(this.f3808i);
            cursor = C4617j.m2284a(this.f3808i);
            if (cursor.getCount() > 0) {
                JSONObject jSONObject = new JSONObject();
                while (cursor.moveToNext()) {
                    int i = cursor.getInt(cursor.getColumnIndex("content_id"));
                    if (i != -1) {
                        String num = Integer.toString(cursor.getInt(cursor.getColumnIndex("campaign_id")));
                        long j = cursor.getLong(cursor.getColumnIndex("displayed_time"));
                        if (jSONObject.optJSONObject(num) == null) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("cntid", i);
                            jSONObject2.put("ts", j);
                            jSONObject.put(num, jSONObject2);
                        }
                    }
                }
                lVar.put("pch", jSONObject);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            ConfigManager.log(LogLevel.WARNING, e, "Error while building GCM campaign history");
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo44932b(C4619l lVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("api_key", this.f3810k);
        contentValues.put("message_time", Long.valueOf(lVar.optLong("ct", System.currentTimeMillis())));
        contentValues.put("message", lVar.toString());
        this.f3808i.insert("uploads", null, contentValues);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public int mo44930b(int i) {
        return this.f3808i.delete("messages", "_id = ?", new String[]{Integer.toString(i)});
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public void mo44933c(int i) {
        String[] strArr = {Integer.toString(i)};
        ContentValues contentValues = new ContentValues();
        contentValues.put("upload_status", Integer.valueOf(3));
        this.f3808i.update("messages", contentValues, "_id = ?", strArr);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public void mo44935d(int i) {
        String[] strArr = {Long.toString((long) i)};
        this.f3808i.delete("reporting", "_id =?", strArr);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public int mo44936e(int i) {
        return this.f3808i.delete("uploads", "_id=?", new String[]{Long.toString((long) i)});
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44926a(C4612h hVar) {
        this.f3812m = hVar;
    }

    /* renamed from: c */
    public void mo44934c(boolean z) {
        try {
            if (!this.f3800a && z && this.f3807h.isPushEnabled() && PushRegistrationHelper.getLatestPushRegistration(this.f3803d) == null) {
                MParticle.getInstance().Messaging().enablePushNotifications(this.f3807h.getPushSenderId());
            }
        } catch (Exception e) {
        }
        this.f3800a = z;
    }

    /* renamed from: a */
    public void mo44925a(long j, String str, SegmentListener segmentListener) {
        new C4624q(this.f3811l, this.f3812m).mo44917a(j, str, segmentListener);
    }
}
