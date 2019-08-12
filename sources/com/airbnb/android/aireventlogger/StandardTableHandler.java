package com.airbnb.android.aireventlogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.airbnb.android.aireventlogger.Converter.Factory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import java.util.ArrayList;
import java.util.List;

class StandardTableHandler implements TableHandler {
    private static final String COL_EVENT = "event";
    private static final String COL_ID = "id";
    private static final String DELETE_WHERE_CLAUSE = "id between ? and ?";
    private static final String TAG = StandardTableHandler.class.getSimpleName();
    private final Factory converterFactory;
    private final DatabaseHelper dbHelper;
    private Boolean tableCreated = Boolean.valueOf(false);
    private final String tableName;

    StandardTableHandler(Context context, Factory converterFactory2, String tableName2) {
        this.dbHelper = DatabaseHelper.getInstanceAndRegister(context, this);
        this.converterFactory = converterFactory2;
        if (tableName2 == null) {
            this.tableName = AirbnbEventLogger.AIREVENTS_TARGET;
        } else {
            this.tableName = tableName2;
        }
    }

    private void createTableIfNecessary(SQLiteDatabase db) {
        if (!this.tableCreated.booleanValue()) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + this.tableName + " (" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "event" + " TEXT)");
            this.tableCreated = Boolean.valueOf(true);
        }
    }

    public void onOpen(SQLiteDatabase db) {
        createTableIfNecessary(db);
    }

    public void onCreate(SQLiteDatabase db) {
        createTableIfNecessary(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /* access modifiers changed from: 0000 */
    public <T> void saveEvent(AirEvent<T> airEvent) {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("event", toJson(airEvent));
            if (db.insert(this.tableName, null, values) < 0) {
                Log.d(TAG, "event saving failed");
            }
        } catch (SQLiteException e) {
            Log.w(TAG, "error saving event", e);
        } finally {
            Utils.closeQuietly(db);
        }
    }

    private <T> byte[] toJson(AirEvent<T> airEvent) {
        return ((Converter) Utils.throwIfNull(this.converterFactory.get(airEvent.getEventType()), "converter == null")).toJson(airEvent.data());
    }

    private static int getId(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex("id"));
    }

    private static byte[] getEvent(Cursor cursor) {
        return cursor.getBlob(cursor.getColumnIndex("event"));
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public JsonData getPendingData(int numToFetch) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        String queryFetch = "select * from " + this.tableName + " limit ?";
        try {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(queryFetch, new String[]{String.valueOf(numToFetch)});
            if (cursor == null || cursor.getCount() == 0) {
                Utils.closeQuietly(cursor);
                Utils.closeQuietly(db);
                return null;
            }
            int firstId = -1;
            int lastId = -1;
            List<byte[]> data = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    firstId = getId(cursor);
                }
                data.add(getEvent(cursor));
                if (cursor.isLast()) {
                    lastId = getId(cursor);
                }
            }
            JsonData jsonData = new JsonData(data, firstId, lastId);
            Utils.closeQuietly(cursor);
            Utils.closeQuietly(db);
            return jsonData;
        } catch (SQLiteException e) {
            Log.w(TAG, "Error getting pending events", e);
            Utils.closeQuietly(cursor);
            Utils.closeQuietly(db);
            return null;
        } catch (Throwable th) {
            Utils.closeQuietly(cursor);
            Utils.closeQuietly(db);
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    public void deleteEvents(int first, int last) {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            db.delete(this.tableName, DELETE_WHERE_CLAUSE, new String[]{String.valueOf(first), String.valueOf(last)});
        } catch (SQLiteException e) {
            Log.w(TAG, "error deleting events", e);
        } finally {
            Utils.closeQuietly(db);
        }
    }
}
