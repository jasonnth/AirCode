package com.mparticle.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.segmentation.Segment;
import com.mparticle.segmentation.SegmentListener;
import com.mparticle.segmentation.SegmentMembership;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.mparticle.internal.q */
class C4624q {

    /* renamed from: c */
    private static final String[] f3791c = {DbHelper.TABLE_ID, "segment_id", "action"};

    /* renamed from: a */
    private final C4623p f3792a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C4612h f3793b;

    /* renamed from: com.mparticle.internal.q$a */
    class C4625a extends AsyncTask<Void, Void, SegmentMembership> {

        /* renamed from: a */
        ExecutorService f3794a = Executors.newSingleThreadExecutor();

        /* renamed from: b */
        String f3795b;

        /* renamed from: c */
        SegmentListener f3796c;

        /* renamed from: d */
        long f3797d;

        C4625a(long j, String str, SegmentListener segmentListener) {
            this.f3797d = j;
            this.f3795b = str;
            this.f3796c = segmentListener;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SegmentMembership doInBackground(Void... voidArr) {
            FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() throws Exception {
                    JSONObject b = C4624q.this.f3793b.mo44886b();
                    if (b != null) {
                        C4624q.this.m2337a(b);
                    }
                    return Boolean.valueOf(b != null);
                }
            });
            this.f3794a.execute(futureTask);
            try {
                futureTask.get(this.f3797d, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            } catch (TimeoutException e3) {
                e3.printStackTrace();
            }
            this.f3794a.shutdown();
            return C4624q.this.mo44916a(this.f3795b);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(SegmentMembership segmentMembership) {
            this.f3796c.onSegmentsRetrieved(segmentMembership);
        }
    }

    C4624q(C4623p pVar, C4612h hVar) {
        this.f3792a = pVar;
        this.f3793b = hVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44917a(long j, String str, SegmentListener segmentListener) {
        new C4625a(j, str, segmentListener).execute(new Void[0]);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public SegmentMembership mo44916a(String str) {
        SQLiteDatabase readableDatabase = this.f3792a.getReadableDatabase();
        String str2 = null;
        String[] strArr = null;
        if (str != null && str.length() > 0) {
            str2 = "endpoint_ids like ?";
            strArr = new String[]{"%\"" + str + "\"%"};
        }
        Cursor query = readableDatabase.query("segments", null, str2, strArr, null, null, "_id desc");
        SparseArray sparseArray = new SparseArray();
        StringBuilder sb = new StringBuilder("(");
        if (query.getCount() <= 0) {
            return new SegmentMembership(new ArrayList());
        }
        while (query.moveToNext()) {
            int i = query.getInt(query.getColumnIndex(DbHelper.TABLE_ID));
            sparseArray.put(i, new Segment(i, query.getString(query.getColumnIndex("name")), query.getString(query.getColumnIndex("endpoint_ids"))));
            sb.append(i);
            sb.append(", ");
        }
        query.close();
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query2 = readableDatabase.query(false, "segment_memberships", f3791c, String.format("segment_id in %s and timestamp < %d", new Object[]{sb.toString(), Long.valueOf(currentTimeMillis)}), null, null, null, "segment_id desc, timestamp desc", null);
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        while (query2.moveToNext()) {
            int i3 = query2.getInt(1);
            if (i3 != i2) {
                if (query2.getString(2).equals("add")) {
                    arrayList.add(sparseArray.get(i3));
                }
                i2 = i3;
            }
        }
        query2.close();
        readableDatabase.close();
        return new SegmentMembership(arrayList);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2337a(JSONObject jSONObject) throws JSONException {
        SQLiteDatabase writableDatabase = this.f3792a.getWritableDatabase();
        JSONArray jSONArray = jSONObject.getJSONArray("m");
        writableDatabase.beginTransaction();
        try {
            writableDatabase.delete("segment_memberships", null, null);
            writableDatabase.delete("segments", null, null);
            for (int i = 0; i < jSONArray.length(); i++) {
                ContentValues contentValues = new ContentValues();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                int i2 = jSONObject2.getInt("id");
                String string = jSONObject2.getString("n");
                String jSONArray2 = jSONObject2.getJSONArray("s").toString();
                contentValues.put(DbHelper.TABLE_ID, Integer.valueOf(i2));
                contentValues.put("name", string);
                contentValues.put("endpoint_ids", jSONArray2);
                writableDatabase.insert("segments", null, contentValues);
                JSONArray jSONArray3 = jSONObject2.getJSONArray("c");
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("segment_id", Integer.valueOf(i2));
                    contentValues2.put("action", jSONArray3.getJSONObject(i3).getString("a"));
                    contentValues2.put(ErfExperimentsModel.TIMESTAMP, Long.valueOf(jSONArray3.getJSONObject(i3).optLong("ct", 0)));
                    writableDatabase.insert("segment_memberships", null, contentValues2);
                }
            }
            writableDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            ConfigManager.log(LogLevel.DEBUG, "Failed to insert audiences: " + e.getMessage());
        } finally {
            writableDatabase.endTransaction();
            writableDatabase.close();
        }
    }
}
