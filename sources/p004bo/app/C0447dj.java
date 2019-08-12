package p004bo.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.json.JSONException;

/* renamed from: bo.app.dj */
public class C0447dj implements C0446di {

    /* renamed from: a */
    private static final String f362a = AppboyLogger.getAppboyLogTag(C0447dj.class);

    /* renamed from: b */
    private static final String[] f363b = {"session_id", "start_time", "end_time", "new_sent", "endtime_sent"};

    /* renamed from: c */
    private static final String[] f364c = {"session_id", "event_type", "event_data", ErfExperimentsModel.TIMESTAMP};

    /* renamed from: d */
    private SQLiteDatabase f365d;

    /* renamed from: e */
    private final C0435dd f366e;

    public C0447dj(C0435dd ddVar) {
        this.f366e = ddVar;
    }

    /* renamed from: c */
    public synchronized SQLiteDatabase mo6964c() {
        if (this.f365d == null || !this.f365d.isOpen()) {
            this.f365d = this.f366e.getWritableDatabase();
        }
        return this.f365d;
    }

    /* renamed from: a */
    public void mo6938a(C0391bt btVar) {
        if (btVar != null) {
            mo6964c().beginTransaction();
            try {
                mo6941b(btVar);
                m466c(btVar);
                mo6964c().setTransactionSuccessful();
            } finally {
                mo6964c().endTransaction();
            }
        } else {
            AppboyLogger.m1735e(f362a, "Erroneously received upsertSession call with null session value. Please report this result to Appboy.");
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo6963a(C0395bx bxVar, C0386bo boVar) {
        if (mo6964c().insert("ab_events", null, m463b(bxVar, boVar)) != -1) {
            return true;
        }
        AppboyLogger.m1739w(f362a, String.format("Failed to insert event [%s] for session with ID [%s]", new Object[]{boVar.toString(), bxVar.toString()}));
        return false;
    }

    /* renamed from: a */
    public void mo6939a(C0391bt btVar, C0386bo boVar) {
        if (!C0397bz.m293a(boVar)) {
            mo6963a(btVar.mo6828a(), boVar);
            m469f(btVar);
        }
    }

    /* renamed from: a */
    public C0391bt mo6937a() {
        try {
            Cursor query = mo6964c().query("ab_sessions", f363b, String.format("%s = ?", new Object[]{"sealed"}), new String[]{String.valueOf(0)}, null, null, null);
            if (query.getCount() > 1) {
                AppboyLogger.m1735e(f362a, "Got > 1 session while trying to get stored open session. " + query.getCount() + " open sessions in database");
            }
            return m461a(query);
        } catch (Exception e) {
            AppboyLogger.m1736e(f362a, "Failed to retrieve stored open session.", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e2 A[SYNTHETIC, Splitter:B:29:0x00e2] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<p004bo.app.C0391bt> mo6940b() {
        /*
            r16 = this;
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.lang.String r2 = "%s = ?"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.String r5 = "sealed"
            r3[r4] = r5
            java.lang.String r5 = java.lang.String.format(r2, r3)
            java.lang.String r2 = "%s = ?"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.String r6 = "session_id"
            r3[r4] = r6
            java.lang.String r14 = java.lang.String.format(r2, r3)
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]
            r2 = 0
            r3 = 1
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r6[r2] = r3
            java.lang.String r2 = f362a
            java.lang.String r3 = "Querying for all sealed sessions"
            com.appboy.support.AppboyLogger.m1733d(r2, r3)
            r10 = 0
            android.database.sqlite.SQLiteDatabase r2 = r16.mo6964c()     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = "ab_sessions"
            java.lang.String[] r4 = f363b     // Catch:{ all -> 0x00ec }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00ec }
            java.lang.String r2 = f362a     // Catch:{ all -> 0x00d4 }
            java.lang.String r3 = "Found %d sealed session rows."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00d4 }
            r5 = 0
            int r6 = r12.getCount()     // Catch:{ all -> 0x00d4 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00d4 }
            r4[r5] = r6     // Catch:{ all -> 0x00d4 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x00d4 }
            com.appboy.support.AppboyLogger.m1737i(r2, r3)     // Catch:{ all -> 0x00d4 }
            r0 = r16
            java.util.Collection r2 = r0.m464b(r12)     // Catch:{ all -> 0x00d4 }
            r12.close()     // Catch:{ all -> 0x00d4 }
            java.util.Iterator r15 = r2.iterator()     // Catch:{ all -> 0x00d4 }
        L_0x0070:
            boolean r2 = r15.hasNext()     // Catch:{ all -> 0x00d4 }
            if (r2 == 0) goto L_0x00e6
            java.lang.Object r2 = r15.next()     // Catch:{ all -> 0x00d4 }
            r0 = r2
            bo.app.bt r0 = (p004bo.app.C0391bt) r0     // Catch:{ all -> 0x00d4 }
            r10 = r0
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ all -> 0x00d4 }
            r2 = 0
            bo.app.bx r3 = r10.mo6828a()     // Catch:{ all -> 0x00d4 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00d4 }
            r6[r2] = r3     // Catch:{ all -> 0x00d4 }
            r11 = 0
            android.database.sqlite.SQLiteDatabase r2 = r16.mo6964c()     // Catch:{ all -> 0x00de }
            java.lang.String r3 = "ab_events"
            java.lang.String[] r4 = f364c     // Catch:{ all -> 0x00de }
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r14
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00de }
            r0 = r16
            java.util.Collection r2 = r0.m465c(r11)     // Catch:{ all -> 0x00ef }
            bo.app.bl r7 = new bo.app.bl     // Catch:{ all -> 0x00ef }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x00ef }
            r3.<init>(r2)     // Catch:{ all -> 0x00ef }
            r7.<init>(r3)     // Catch:{ all -> 0x00ef }
            bo.app.bt r2 = new bo.app.bt     // Catch:{ all -> 0x00ef }
            bo.app.bx r3 = r10.mo6828a()     // Catch:{ all -> 0x00ef }
            double r4 = r10.mo6833b()     // Catch:{ all -> 0x00ef }
            java.lang.Double r6 = r10.mo6835c()     // Catch:{ all -> 0x00ef }
            boolean r8 = r10.mo6837e()     // Catch:{ all -> 0x00ef }
            if (r8 != 0) goto L_0x00dc
            r8 = 1
        L_0x00c3:
            r9 = 1
            boolean r10 = r10.mo6840h()     // Catch:{ all -> 0x00ef }
            r2.<init>(r3, r4, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00ef }
            r13.add(r2)     // Catch:{ all -> 0x00ef }
            if (r11 == 0) goto L_0x0070
            r11.close()     // Catch:{ all -> 0x00d4 }
            goto L_0x0070
        L_0x00d4:
            r2 = move-exception
            r3 = r12
        L_0x00d6:
            if (r3 == 0) goto L_0x00db
            r3.close()
        L_0x00db:
            throw r2
        L_0x00dc:
            r8 = 0
            goto L_0x00c3
        L_0x00de:
            r2 = move-exception
            r3 = r11
        L_0x00e0:
            if (r3 == 0) goto L_0x00e5
            r3.close()     // Catch:{ all -> 0x00d4 }
        L_0x00e5:
            throw r2     // Catch:{ all -> 0x00d4 }
        L_0x00e6:
            if (r12 == 0) goto L_0x00eb
            r12.close()
        L_0x00eb:
            return r13
        L_0x00ec:
            r2 = move-exception
            r3 = r10
            goto L_0x00d6
        L_0x00ef:
            r2 = move-exception
            r3 = r11
            goto L_0x00e0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0447dj.mo6940b():java.util.Collection");
    }

    /* renamed from: b */
    public void mo6941b(C0391bt btVar) {
        mo6964c().beginTransaction();
        try {
            String format = String.format("%s = ?", new Object[]{"session_id"});
            String[] strArr = {btVar.mo6828a().toString()};
            int delete = mo6964c().delete("ab_sessions", format, strArr);
            AppboyLogger.m1733d(f362a, String.format("Deleting session removed %d rows.", new Object[]{Integer.valueOf(delete)}));
            int delete2 = mo6964c().delete("ab_events", String.format("%s = ?", new Object[]{"session_id"}), strArr);
            AppboyLogger.m1733d(f362a, String.format("Deleting session events removed %d rows.", new Object[]{Integer.valueOf(delete2)}));
            mo6964c().setTransactionSuccessful();
        } finally {
            mo6964c().endTransaction();
        }
    }

    /* renamed from: c */
    private void m466c(C0391bt btVar) {
        C0383bl j = btVar.mo6842j();
        ContentValues d = m467d(btVar);
        mo6964c().beginTransaction();
        try {
            long insertWithOnConflict = mo6964c().insertWithOnConflict("ab_sessions", null, d, 5);
            AppboyLogger.m1733d(f362a, String.format("Inserted session into row %d", new Object[]{Long.valueOf(insertWithOnConflict)}));
            for (C0386bo b : j.mo6806a()) {
                long insert = mo6964c().insert("ab_events", null, m463b(btVar.mo6828a(), b));
                AppboyLogger.m1733d(f362a, String.format("Inserted event into row %d", new Object[]{Long.valueOf(insert)}));
            }
            mo6964c().setTransactionSuccessful();
        } finally {
            mo6964c().endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cd  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p004bo.app.C0391bt m461a(android.database.Cursor r19) {
        /*
            r18 = this;
            r10 = 0
            java.lang.String r2 = "%s = ?"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00c4 }
            r4 = 0
            java.lang.String r5 = "session_id"
            r3[r4] = r5     // Catch:{ all -> 0x00c4 }
            java.lang.String r5 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x00c4 }
            boolean r2 = r19.moveToFirst()     // Catch:{ all -> 0x00c4 }
            if (r2 == 0) goto L_0x00b8
            java.lang.String r2 = "session_id"
            r0 = r19
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x00c4 }
            r0 = r19
            java.lang.String r14 = r0.getString(r2)     // Catch:{ all -> 0x00c4 }
            java.lang.String r2 = "start_time"
            r0 = r19
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x00c4 }
            r0 = r19
            double r16 = r0.getDouble(r2)     // Catch:{ all -> 0x00c4 }
            r2 = 0
            java.lang.String r3 = "end_time"
            r0 = r19
            int r3 = r0.getColumnIndex(r3)     // Catch:{ all -> 0x00c4 }
            r0 = r19
            boolean r4 = r0.isNull(r3)     // Catch:{ all -> 0x00c4 }
            if (r4 != 0) goto L_0x00d6
            r0 = r19
            double r2 = r0.getDouble(r3)     // Catch:{ all -> 0x00c4 }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ all -> 0x00c4 }
            r13 = r2
        L_0x0052:
            java.lang.String r2 = "new_sent"
            r0 = r19
            int r3 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x00c4 }
            r2 = 0
            r0 = r19
            boolean r4 = r0.isNull(r3)     // Catch:{ all -> 0x00c4 }
            if (r4 != 0) goto L_0x00d4
            r0 = r19
            int r2 = r0.getInt(r3)     // Catch:{ all -> 0x00c4 }
            r12 = r2
        L_0x006b:
            r19.close()     // Catch:{ all -> 0x00c4 }
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ all -> 0x00c4 }
            r2 = 0
            r6[r2] = r14     // Catch:{ all -> 0x00c4 }
            android.database.sqlite.SQLiteDatabase r2 = r18.mo6964c()     // Catch:{ all -> 0x00c4 }
            java.lang.String r3 = "ab_events"
            java.lang.String[] r4 = f364c     // Catch:{ all -> 0x00c4 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00c4 }
            r0 = r18
            java.util.Collection r4 = r0.m465c(r11)     // Catch:{ all -> 0x00d1 }
            bo.app.bt r2 = new bo.app.bt     // Catch:{ all -> 0x00d1 }
            bo.app.bx r3 = p004bo.app.C0395bx.m274a(r14)     // Catch:{ all -> 0x00d1 }
            bo.app.bl r7 = new bo.app.bl     // Catch:{ all -> 0x00d1 }
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ all -> 0x00d1 }
            r5.<init>(r4)     // Catch:{ all -> 0x00d1 }
            r7.<init>(r5)     // Catch:{ all -> 0x00d1 }
            r0 = r18
            boolean r4 = r0.m462a(r12)     // Catch:{ all -> 0x00d1 }
            if (r4 != 0) goto L_0x00b6
            r8 = 1
        L_0x00a3:
            r9 = 0
            r10 = 0
            r4 = r16
            r6 = r13
            r2.<init>(r3, r4, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00d1 }
            if (r19 == 0) goto L_0x00b0
            r19.close()
        L_0x00b0:
            if (r11 == 0) goto L_0x00b5
            r11.close()
        L_0x00b5:
            return r2
        L_0x00b6:
            r8 = 0
            goto L_0x00a3
        L_0x00b8:
            r2 = 0
            if (r19 == 0) goto L_0x00be
            r19.close()
        L_0x00be:
            if (r10 == 0) goto L_0x00b5
            r10.close()
            goto L_0x00b5
        L_0x00c4:
            r2 = move-exception
            r3 = r10
        L_0x00c6:
            if (r19 == 0) goto L_0x00cb
            r19.close()
        L_0x00cb:
            if (r3 == 0) goto L_0x00d0
            r3.close()
        L_0x00d0:
            throw r2
        L_0x00d1:
            r2 = move-exception
            r3 = r11
            goto L_0x00c6
        L_0x00d4:
            r12 = r2
            goto L_0x006b
        L_0x00d6:
            r13 = r2
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0447dj.m461a(android.database.Cursor):bo.app.bt");
    }

    /* renamed from: d */
    private ContentValues m467d(C0391bt btVar) {
        int i;
        int i2 = 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("session_id", btVar.mo6828a().toString());
        contentValues.put("start_time", Double.valueOf(btVar.mo6833b()));
        contentValues.put("end_time", m468e(btVar));
        contentValues.put("new_sent", Integer.valueOf(btVar.mo6837e() ? 1 : 0));
        String str = "endtime_sent";
        if (btVar.mo6840h()) {
            i = 1;
        } else {
            i = 0;
        }
        contentValues.put(str, Integer.valueOf(i));
        if (!btVar.mo6839g()) {
            i2 = 0;
        }
        contentValues.put("sealed", Integer.valueOf(i2));
        return contentValues;
    }

    /* renamed from: e */
    private Double m468e(C0391bt btVar) {
        Double c = btVar.mo6835c();
        if (c == null) {
            return Double.valueOf(C0455dp.m522b());
        }
        return c;
    }

    /* renamed from: f */
    private void m469f(C0391bt btVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("end_time", m468e(btVar));
        int updateWithOnConflict = mo6964c().updateWithOnConflict("ab_sessions", contentValues, String.format("%s = ? AND %s = ?", new Object[]{"sealed", "session_id"}), new String[]{String.valueOf(0), String.valueOf(btVar.mo6828a())}, 2);
        if (btVar.mo6839g()) {
            if (updateWithOnConflict == 0) {
                AppboyLogger.m1733d(f362a, "Did not update end time because the stored session was sealed.");
            }
        } else if (updateWithOnConflict != 1) {
            AppboyLogger.m1739w(f362a, String.format("Attempt to update end time affected %d rows, expected just one.", new Object[]{Integer.valueOf(updateWithOnConflict)}));
        }
    }

    /* renamed from: b */
    private ContentValues m463b(C0395bx bxVar, C0386bo boVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("session_id", bxVar.toString());
        contentValues.put("event_type", boVar.mo6824b().forJsonPut());
        contentValues.put("event_data", boVar.mo6825c().toString());
        contentValues.put(ErfExperimentsModel.TIMESTAMP, Double.valueOf(boVar.mo6823a()));
        return contentValues;
    }

    /* renamed from: b */
    private Collection<C0391bt> m464b(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex("session_id");
        int columnIndex2 = cursor.getColumnIndex("start_time");
        int columnIndex3 = cursor.getColumnIndex("end_time");
        int columnIndex4 = cursor.getColumnIndex("new_sent");
        int columnIndex5 = cursor.getColumnIndex("endtime_sent");
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            double d = cursor.getDouble(columnIndex2);
            double d2 = cursor.getDouble(columnIndex3);
            int i = cursor.getInt(columnIndex4);
            int i2 = cursor.getInt(columnIndex5);
            arrayList.add(new C0391bt(C0395bx.m274a(string), d, Double.valueOf(d2), new C0383bl(new HashSet()), !m462a(i), true, m462a(i2)));
        }
        return arrayList;
    }

    /* renamed from: c */
    private Collection<C0386bo> m465c(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex("event_type");
        int columnIndex2 = cursor.getColumnIndex("event_data");
        int columnIndex3 = cursor.getColumnIndex(ErfExperimentsModel.TIMESTAMP);
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            String string2 = cursor.getString(columnIndex2);
            double d = cursor.getDouble(columnIndex3);
            try {
                arrayList.add(C0397bz.m283a(string, string2, d));
            } catch (JSONException e) {
                AppboyLogger.m1735e(f362a, String.format("Could not create AppboyEvent from [type=%s, data=%s, timestamp=%f] ... Skipping", new Object[]{string, string2, Double.valueOf(d)}));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private boolean m462a(int i) {
        return i == 1;
    }
}
