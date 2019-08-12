package com.airbnb.android.aireventlogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.airbnb.jitney.event.p304v1.EventMetadata;
import com.airbnb.jitney.event.p304v1.MessageType;
import com.airbnb.jitney.event.p304v1.RawMessage;
import com.airbnb.jitney.event.p304v1.RawMessage.Builder;
import com.airbnb.jitney.event.p304v1.Tier;
import com.microsoft.thrifty.Struct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import okio.ByteString;

class JitneyEventTableHandler implements TableHandler {
    private static final String COL_EVENT_DATA = "event_data";
    private static final String COL_ID = "id";
    private static final String COL_SCHEMA = "schema";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS jitneyevents (id INTEGER PRIMARY KEY AUTOINCREMENT,event_data BLOB, schema TEXT)";
    private static final String DELETE_WHERE_CLAUSE = "id between ? and ?";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS jitneyevents";
    private static final String QUERY_FETCH = "SELECT * from jitneyevents LIMIT ?";
    private static final String TABLE_NAME = "jitneyevents";
    private static final String TAG = JitneyEventTableHandler.class.getSimpleName();
    private static final String TIER_HOST = "localhost";
    private static final String TIER_IP = "127.0.0.1";
    private static final String TIER_TYPE = "android-jitney-producer";
    private final DatabaseHelper dbHelper;
    private Boolean tableCreated = Boolean.valueOf(false);

    JitneyEventTableHandler(Context context) {
        this.dbHelper = DatabaseHelper.getInstanceAndRegister(context, this);
    }

    private static int getId(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex("id"));
    }

    private static byte[] getEventData(Cursor cursor) {
        return cursor.getBlob(cursor.getColumnIndex("event_data"));
    }

    private static String getSchema(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(COL_SCHEMA));
    }

    private void createTableIfNecessary(SQLiteDatabase db) {
        if (!this.tableCreated.booleanValue()) {
            db.execSQL(CREATE_TABLE);
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
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL(CREATE_TABLE);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2 && newVersion == 1) {
            db.execSQL(DROP_TABLE);
        }
    }

    /* access modifiers changed from: 0000 */
    public void saveEvent(AirEvent<Struct> airEvent) {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            if (db.insert(TABLE_NAME, null, getContentValues((Struct) airEvent.data())) < 0) {
                Log.d(TAG, "event saving failed");
            }
        } catch (SQLiteException e) {
            Log.w(TAG, "error saving event", e);
        } finally {
            Utils.closeQuietly(db);
        }
    }

    private ContentValues getContentValues(Struct event) {
        ContentValues values = new ContentValues();
        String schema = getSchema(event);
        values.put("event_data", toBlob(event));
        values.put(COL_SCHEMA, schema);
        return values;
    }

    private String getSchema(Struct event) {
        try {
            return (String) event.getClass().getDeclaredField(COL_SCHEMA).get(event);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e2) {
            throw new RuntimeException(e2);
        }
    }

    private byte[] toBlob(Struct event) {
        try {
            return Utils.toBuffer(event).readByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Could not serialize struct " + event + " to binary", e);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public JitneyData getPendingData(int numToFetch) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(QUERY_FETCH, new String[]{String.valueOf(numToFetch)});
            if (cursor == null || cursor.getCount() == 0) {
                Utils.closeQuietly(cursor);
                Utils.closeQuietly(db);
                return null;
            }
            int firstId = -1;
            int lastId = -1;
            List<RawMessage> rawMessages = new ArrayList<>();
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    firstId = getId(cursor);
                }
                rawMessages.add(createRawMessage(getEventData(cursor), getSchema(cursor)));
                if (cursor.isLast()) {
                    lastId = getId(cursor);
                }
            }
            JitneyData jitneyData = new JitneyData(rawMessages, firstId, lastId);
            Utils.closeQuietly(cursor);
            Utils.closeQuietly(db);
            return jitneyData;
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

    private RawMessage createRawMessage(byte[] object, String schema) {
        return new Builder(new EventMetadata.Builder(schema, UUID.randomUUID().toString(), getTiers()).message_type(MessageType.JITNEY_THRIFT).build(), ByteString.m3949of(object)).build();
    }

    /* access modifiers changed from: 0000 */
    public List<Tier> getTiers() {
        return Collections.singletonList(new Tier.Builder(Utils.getSessionID(), TIER_TYPE, TIER_HOST, TIER_IP, Long.valueOf(System.currentTimeMillis())).build());
    }

    /* access modifiers changed from: 0000 */
    public void deleteEvents(int first, int last) {
        SQLiteDatabase db = null;
        try {
            db = this.dbHelper.getWritableDatabase();
            db.delete(TABLE_NAME, DELETE_WHERE_CLAUSE, new String[]{String.valueOf(first), String.valueOf(last)});
        } catch (SQLiteException e) {
            Log.w(TAG, "error deleting events", e);
        } finally {
            Utils.closeQuietly(db);
        }
    }
}
