package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzo;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p005cn.jpush.android.JPushConstants;

class zzsg extends zzsa implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzaeu = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzaev = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zza zzaew;
    private final zztj zzaex = new zztj(zznR());
    /* access modifiers changed from: private */
    public final zztj zzaey = new zztj(zznR());

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, null, 1);
        }

        private void zza(SQLiteDatabase sQLiteDatabase) {
            boolean z = true;
            Set zzb = zzb(sQLiteDatabase, "hits2");
            String[] strArr = {"hit_id", "hit_string", "hit_time", "hit_url"};
            for (int i = 0; i < 4; i++) {
                String str = strArr[i];
                if (!zzb.remove(str)) {
                    String str2 = "Database hits2 is missing required column: ";
                    String valueOf = String.valueOf(str);
                    throw new SQLiteException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
            if (zzb.remove("hit_app_id")) {
                z = false;
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean zza(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
            /*
                r10 = this;
                r8 = 0
                r9 = 0
                java.lang.String r1 = "SQLITE_MASTER"
                r0 = 1
                java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0029, all -> 0x003a }
                r0 = 0
                java.lang.String r3 = "name"
                r2[r0] = r3     // Catch:{ SQLiteException -> 0x0029, all -> 0x003a }
                java.lang.String r3 = "name=?"
                r0 = 1
                java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0029, all -> 0x003a }
                r0 = 0
                r4[r0] = r12     // Catch:{ SQLiteException -> 0x0029, all -> 0x003a }
                r5 = 0
                r6 = 0
                r7 = 0
                r0 = r11
                android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0029, all -> 0x003a }
                boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0044 }
                if (r1 == 0) goto L_0x0028
                r1.close()
            L_0x0028:
                return r0
            L_0x0029:
                r0 = move-exception
                r1 = r9
            L_0x002b:
                com.google.android.gms.internal.zzsg r2 = com.google.android.gms.internal.zzsg.this     // Catch:{ all -> 0x0041 }
                java.lang.String r3 = "Error querying for table"
                r2.zzc(r3, r12, r0)     // Catch:{ all -> 0x0041 }
                if (r1 == 0) goto L_0x0038
                r1.close()
            L_0x0038:
                r0 = r8
                goto L_0x0028
            L_0x003a:
                r0 = move-exception
            L_0x003b:
                if (r9 == 0) goto L_0x0040
                r9.close()
            L_0x0040:
                throw r0
            L_0x0041:
                r0 = move-exception
                r9 = r1
                goto L_0x003b
            L_0x0044:
                r0 = move-exception
                goto L_0x002b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zza.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        private Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
            HashSet hashSet = new HashSet();
            Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                return hashSet;
            } finally {
                rawQuery.close();
            }
        }

        private void zzb(SQLiteDatabase sQLiteDatabase) {
            Set zzb = zzb(sQLiteDatabase, "properties");
            String[] strArr = {"app_uid", "cid", "tid", NativeProtocol.WEB_DIALOG_PARAMS, "adid", "hits_count"};
            for (int i = 0; i < 6; i++) {
                String str = strArr[i];
                if (!zzb.remove(str)) {
                    String str2 = "Database properties is missing required column: ";
                    String valueOf = String.valueOf(str);
                    throw new SQLiteException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!zzsg.this.zzaey.zzA(3600000)) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzsg.this.zzaey.start();
                zzsg.this.zzbT("Opening the database failed, dropping the table and recreating it");
                zzsg.this.getContext().getDatabasePath(zzsg.this.zzow()).delete();
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzsg.this.zzaey.clear();
                    return writableDatabase;
                } catch (SQLiteException e2) {
                    zzsg.this.zze("Failed to open freshly created database", e2);
                    throw e2;
                }
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzsv.zzca(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (!zza(sQLiteDatabase, "hits2")) {
                sQLiteDatabase.execSQL(zzsg.zzaeu);
            } else {
                zza(sQLiteDatabase);
            }
            if (!zza(sQLiteDatabase, "properties")) {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
            } else {
                zzb(sQLiteDatabase);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzsg(zzsc zzsc) {
        super(zzsc);
        this.zzaew = new zza(zzsc.getContext(), zzow());
    }

    private static String zzT(Map<String, String> map) {
        zzac.zzw(map);
        Builder builder = new Builder();
        for (Entry entry : map.entrySet()) {
            builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private String zzd(zzsz zzsz) {
        return zzsz.zzpS() ? zznT().zzpj() : zznT().zzpk();
    }

    private static String zze(zzsz zzsz) {
        zzac.zzw(zzsz);
        Builder builder = new Builder();
        for (Entry entry : zzsz.zzfE().entrySet()) {
            String str = (String) entry.getKey();
            if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str)) {
                builder.appendQueryParameter(str, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private void zzov() {
        int zzpt = zznT().zzpt();
        long zzom = zzom();
        if (zzom > ((long) (zzpt - 1))) {
            List zzt = zzt((zzom - ((long) zzpt)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzt.size()));
            zzr(zzt);
        }
    }

    /* access modifiers changed from: private */
    public String zzow() {
        return zznT().zzpv();
    }

    public void beginTransaction() {
        zzob();
        getWritableDatabase().beginTransaction();
    }

    public void close() {
        try {
            this.zzaew.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    public void endTransaction() {
        zzob();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: 0000 */
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzaew.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isEmpty() {
        return zzom() == 0;
    }

    public void setTransactionSuccessful() {
        zzob();
        getWritableDatabase().setTransactionSuccessful();
    }

    public long zza(long j, String str, String str2) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzob();
        zzmR();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public void zza(long j, String str) {
        zzac.zzdr(str);
        zzob();
        zzmR();
        int delete = getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(j), str});
        if (delete > 0) {
            zza("Deleted property records", Integer.valueOf(delete));
        }
    }

    public void zzb(zzse zzse) {
        zzac.zzw(zzse);
        zzob();
        zzmR();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String zzT = zzT(zzse.zzfE());
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_uid", Long.valueOf(zzse.zzoj()));
        contentValues.put("cid", zzse.zzmy());
        contentValues.put("tid", zzse.zzok());
        contentValues.put("adid", Integer.valueOf(zzse.zzol() ? 1 : 0));
        contentValues.put("hits_count", Long.valueOf(zzse.zzom()));
        contentValues.put(NativeProtocol.WEB_DIALOG_PARAMS, zzT);
        try {
            if (writableDatabase.insertWithOnConflict("properties", null, contentValues, 5) == -1) {
                zzbT("Failed to insert/update a property (got -1)");
            }
        } catch (SQLiteException e) {
            zze("Error storing a property", e);
        }
    }

    /* access modifiers changed from: 0000 */
    public Map<String, String> zzbU(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return zzo.zza(new URI(str), JPushConstants.ENCODING_UTF_8);
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    /* access modifiers changed from: 0000 */
    public Map<String, String> zzbV(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        String str2 = "?";
        try {
            String valueOf = String.valueOf(str);
            return zzo.zza(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), JPushConstants.ENCODING_UTF_8);
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public void zzc(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzmR();
        zzob();
        String zze = zze(zzsz);
        if (zze.length() > 8192) {
            zznS().zza(zzsz, "Hit length exceeds the maximum allowed size");
            return;
        }
        zzov();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", zze);
        contentValues.put("hit_time", Long.valueOf(zzsz.zzpQ()));
        contentValues.put("hit_app_id", Integer.valueOf(zzsz.zzpO()));
        contentValues.put("hit_url", zzd(zzsz));
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzbT("Failed to insert a hit (got -1)");
                return;
            }
            zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzsz);
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public long zzom() {
        zzmR();
        zzob();
        return zzb("SELECT COUNT(*) FROM hits2", null);
    }

    public int zzot() {
        zzmR();
        zzob();
        if (!this.zzaex.zzA(86400000)) {
            return 0;
        }
        this.zzaex.start();
        zzbP("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zznR().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public long zzou() {
        zzmR();
        zzob();
        return zza(zzaev, (String[]) null, 0);
    }

    public void zzr(List<Long> list) {
        zzac.zzw(list);
        zzmR();
        zzob();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    Long l = (Long) list.get(i2);
                    if (l != null && l.longValue() != 0) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        sb.append(l);
                        i = i2 + 1;
                    }
                } else {
                    sb.append(")");
                    String sb2 = sb.toString();
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                        int delete = writableDatabase.delete("hits2", sb2, null);
                        if (delete != list.size()) {
                            zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                            return;
                        }
                        return;
                    } catch (SQLiteException e) {
                        zze("Error deleting hits", e);
                        throw e;
                    }
                }
            }
            throw new SQLiteException("Invalid hit id");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> zzt(long r14) {
        /*
            r13 = this;
            r10 = 0
            r13.zzmR()
            r13.zzob()
            r0 = 0
            int r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0012
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x0011:
            return r0
        L_0x0012:
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            r11 = 0
            java.lang.String r12 = "hit_id"
            r8[r11] = r12     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0062, all -> 0x0070 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x007a }
            if (r0 == 0) goto L_0x005b
        L_0x0049:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch:{ SQLiteException -> 0x007a }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x007a }
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x007a }
            if (r0 != 0) goto L_0x0049
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            r0 = r9
            goto L_0x0011
        L_0x0062:
            r0 = move-exception
            r1 = r10
        L_0x0064:
            java.lang.String r2 = "Error selecting hit ids"
            r13.zzd(r2, r0)     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0060
            r1.close()
            goto L_0x0060
        L_0x0070:
            r0 = move-exception
        L_0x0071:
            if (r10 == 0) goto L_0x0076
            r10.close()
        L_0x0076:
            throw r0
        L_0x0077:
            r0 = move-exception
            r10 = r1
            goto L_0x0071
        L_0x007a:
            r0 = move-exception
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzt(long):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a7, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ab, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ad, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ae, code lost:
        r1 = r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ab A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x001a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzsz> zzu(long r14) {
        /*
            r13 = this;
            r0 = 1
            r1 = 0
            r9 = 0
            r2 = 0
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0097
        L_0x0009:
            com.google.android.gms.common.internal.zzac.zzaw(r0)
            r13.zzmR()
            r13.zzob()
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.lang.String r1 = "hits2"
            r2 = 5
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 1
            java.lang.String r4 = "hit_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 2
            java.lang.String r4 = "hit_string"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 3
            java.lang.String r4 = "hit_url"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 4
            java.lang.String r4 = "hit_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            r10 = 0
            java.lang.String r11 = "hit_id"
            r8[r10] = r11     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r10.<init>()     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            if (r0 == 0) goto L_0x0091
        L_0x0061:
            r0 = 0
            long r6 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r0 = 1
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r0 = 2
            java.lang.String r0 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r1 = 3
            java.lang.String r1 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r2 = 4
            int r8 = r9.getInt(r2)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            java.util.Map r2 = r13.zzbU(r0)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            boolean r5 = com.google.android.gms.internal.zztm.zzcj(r1)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            com.google.android.gms.internal.zzsz r0 = new com.google.android.gms.internal.zzsz     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r1 = r13
            r0.<init>(r1, r2, r3, r5, r6, r8)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            r10.add(r0)     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
            if (r0 != 0) goto L_0x0061
        L_0x0091:
            if (r9 == 0) goto L_0x0096
            r9.close()
        L_0x0096:
            return r10
        L_0x0097:
            r0 = r1
            goto L_0x0009
        L_0x009a:
            r0 = move-exception
            r1 = r9
        L_0x009c:
            java.lang.String r2 = "Error loading hits from the database"
            r13.zze(r2, r0)     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            r9 = r1
        L_0x00a5:
            if (r9 == 0) goto L_0x00aa
            r9.close()
        L_0x00aa:
            throw r0
        L_0x00ab:
            r0 = move-exception
            goto L_0x00a5
        L_0x00ad:
            r0 = move-exception
            r1 = r9
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzu(long):java.util.List");
    }

    public void zzv(long j) {
        zzmR();
        zzob();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzr(arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c5, code lost:
        r1 = r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c2 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzse> zzw(long r14) {
        /*
            r13 = this;
            r13.zzob()
            r13.zzmR()
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            r9 = 0
            r1 = 5
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 0
            java.lang.String r3 = "cid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 1
            java.lang.String r3 = "tid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 2
            java.lang.String r3 = "adid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 3
            java.lang.String r3 = "hits_count"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 4
            java.lang.String r3 = "params"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            com.google.android.gms.internal.zzsp r1 = r13.zznT()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            int r10 = r1.zzpu()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            java.lang.String r3 = "app_uid=?"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r1 = 0
            java.lang.String r5 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            java.lang.String r1 = "properties"
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c2 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r11.<init>()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 == 0) goto L_0x0093
        L_0x005a:
            r0 = 0
            java.lang.String r3 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r0 = 1
            java.lang.String r4 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r0 = 2
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 == 0) goto L_0x00a5
            r5 = 1
        L_0x006c:
            r0 = 3
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            long r6 = (long) r0     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r0 = 4
            java.lang.String r0 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            java.util.Map r8 = r13.zzbV(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 != 0) goto L_0x0087
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 == 0) goto L_0x00a7
        L_0x0087:
            java.lang.String r0 = "Read property with empty client id or tracker id"
            r13.zzc(r0, r3, r4)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
        L_0x008d:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 != 0) goto L_0x005a
        L_0x0093:
            int r0 = r11.size()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            if (r0 < r10) goto L_0x009f
            java.lang.String r0 = "Sending hits to too many properties. Campaign report might be incorrect"
            r13.zzbS(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
        L_0x009f:
            if (r9 == 0) goto L_0x00a4
            r9.close()
        L_0x00a4:
            return r11
        L_0x00a5:
            r5 = 0
            goto L_0x006c
        L_0x00a7:
            com.google.android.gms.internal.zzse r0 = new com.google.android.gms.internal.zzse     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r1 = r14
            r0.<init>(r1, r3, r4, r5, r6, r8)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            r11.add(r0)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00c2 }
            goto L_0x008d
        L_0x00b1:
            r0 = move-exception
            r1 = r9
        L_0x00b3:
            java.lang.String r2 = "Error loading hits from the database"
            r13.zze(r2, r0)     // Catch:{ all -> 0x00ba }
            throw r0     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r0 = move-exception
            r9 = r1
        L_0x00bc:
            if (r9 == 0) goto L_0x00c1
            r9.close()
        L_0x00c1:
            throw r0
        L_0x00c2:
            r0 = move-exception
            goto L_0x00bc
        L_0x00c4:
            r0 = move-exception
            r1 = r9
            goto L_0x00b3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzw(long):java.util.List");
    }
}
