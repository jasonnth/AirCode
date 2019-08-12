package com.braintreepayments.api.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

public class AnalyticsDatabase extends SQLiteOpenHelper {
    public static AnalyticsDatabase getInstance(Context context) {
        return new AnalyticsDatabase(context, "braintree-analytics.db", null, 1);
    }

    public AnalyticsDatabase(Context context, String name, CursorFactory factory, int version) {
        super(context, "braintree-analytics.db", factory, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table analytics(_id integer primary key autoincrement, event text not null, timestamp long not null, meta_json text not null);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists analytics");
        onCreate(db);
    }

    public void addEvent(AnalyticsEvent request) {
        ContentValues values = new ContentValues();
        values.put("event", request.event);
        values.put(ErfExperimentsModel.TIMESTAMP, Long.valueOf(request.timestamp));
        values.put("meta_json", request.metadata.toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("analytics", null, values);
        db.close();
    }

    public void removeEvents(List<AnalyticsEvent> events) {
        StringBuilder where = new StringBuilder(DbHelper.TABLE_ID).append(" in (");
        String[] whereArgs = new String[events.size()];
        for (int i = 0; i < events.size(); i++) {
            whereArgs[i] = Integer.toString(((AnalyticsEvent) events.get(i)).f2921id);
            where.append("?");
            if (i < events.size() - 1) {
                where.append(",");
            } else {
                where.append(")");
            }
        }
        SQLiteDatabase db = getWritableDatabase();
        db.delete("analytics", where.toString(), whereArgs);
        db.close();
    }

    public List<List<AnalyticsEvent>> getPendingRequests() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(false, "analytics", new String[]{"group_concat(_id)", "group_concat(event)", "group_concat(timestamp)", "meta_json"}, null, null, "meta_json", null, "_id asc", null);
        List<List<AnalyticsEvent>> analyticsRequests = new ArrayList<>();
        while (cursor.moveToNext()) {
            ArrayList arrayList = new ArrayList();
            String[] ids = cursor.getString(0).split(",");
            String[] events = cursor.getString(1).split(",");
            String[] timestamps = cursor.getString(2).split(",");
            for (int i = 0; i < events.length; i++) {
                try {
                    AnalyticsEvent request = new AnalyticsEvent();
                    request.f2921id = Integer.valueOf(ids[i]).intValue();
                    request.event = events[i];
                    request.timestamp = Long.valueOf(timestamps[i]).longValue();
                    request.metadata = new JSONObject(cursor.getString(cursor.getColumnIndex("meta_json")));
                    arrayList.add(request);
                } catch (JSONException e) {
                }
            }
            analyticsRequests.add(arrayList);
        }
        cursor.close();
        db.close();
        return analyticsRequests;
    }
}
