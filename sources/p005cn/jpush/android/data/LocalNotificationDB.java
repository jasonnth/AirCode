package p005cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.LocalNotificationDB */
public class LocalNotificationDB {
    public static final String DATABASE_CREATE = "CREATE TABLE t_localnotification (_id INTEGER PRIMARY KEY AUTOINCREMENT ,ln_id long not null,ln_count integer not null,ln_remove integer not null,ln_type integer not null,ln_extra text ,ln_trigger_time long ,ln_add_time long );";
    private static final String DATABASE_NAME = "jpush_local_notification.db";
    private static final String DATABASE_TABLE = "t_localnotification";
    private static final int DATABASE_VERSION = 1;
    public static final String[] FIELDS = {"_id", KEY_LN_ID, KEY_LN_COUNT, KEY_LN_REMOVE, KEY_LN_TYPE, KEY_LN_EXTRA, KEY_TRIGGER_TIME, KEY_LN_ADD_TIME};
    private static final String KEY_LN_ADD_TIME = "ln_add_time";
    private static final String KEY_LN_COUNT = "ln_count";
    private static final String KEY_LN_EXTRA = "ln_extra";
    private static final String KEY_LN_ID = "ln_id";
    private static final String KEY_LN_REMOVE = "ln_remove";
    private static final String KEY_LN_TYPE = "ln_type";
    private static final String KEY_ROWID = "_id";
    private static final String KEY_TRIGGER_TIME = "ln_trigger_time";
    private static final String TAG = "LocalNotificationDB";
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDatabase;

    /* renamed from: cn.jpush.android.data.LocalNotificationDB$DBHelper */
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        public DBHelper(Context context) {
            this(context, LocalNotificationDB.DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(LocalNotificationDB.DATABASE_CREATE);
            } catch (Exception e) {
                Logger.m1428v(LocalNotificationDB.TAG, "表已经存在");
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS t_localnotification");
            onCreate(db);
        }
    }

    public LocalNotificationDB(Context context2) {
        this.context = context2;
        this.dbHelper = new DBHelper(context2);
    }

    public synchronized boolean open(boolean writeable) {
        boolean openReadableDataBase;
        if (writeable) {
            openReadableDataBase = openWritableDatabase();
        } else {
            openReadableDataBase = openReadableDataBase();
        }
        return openReadableDataBase;
    }

    public synchronized boolean openWritableDatabase() {
        boolean ret;
        ret = false;
        try {
            this.sqliteDatabase = this.dbHelper.getWritableDatabase();
            ret = true;
        } catch (Exception e) {
            Logger.m1420e(TAG, "open openWritableDatabase fail e:" + e.getMessage());
        }
        return ret;
    }

    public synchronized boolean openReadableDataBase() {
        boolean ret;
        ret = false;
        try {
            this.sqliteDatabase = this.dbHelper.getReadableDatabase();
            ret = true;
        } catch (Exception e) {
            Logger.m1420e(TAG, "open ReadableDataBase fail e:" + e.getMessage());
        }
        return ret;
    }

    public synchronized void close() {
        if (this.sqliteDatabase != null) {
            this.sqliteDatabase.close();
        }
    }

    public synchronized long insertData(long ln_id, int ln_count, int ln_remove, int ln_type, String ln_extra, long ln_trigger_time, long ln_add_time) {
        long ret;
        ContentValues content = new ContentValues();
        content.put(KEY_LN_ID, Long.valueOf(ln_id));
        content.put(KEY_LN_COUNT, Integer.valueOf(ln_count));
        content.put(KEY_LN_REMOVE, Integer.valueOf(ln_remove));
        content.put(KEY_LN_TYPE, Integer.valueOf(ln_type));
        content.put(KEY_LN_EXTRA, ln_extra);
        content.put(KEY_TRIGGER_TIME, Long.valueOf(ln_trigger_time));
        content.put(KEY_LN_ADD_TIME, Long.valueOf(ln_add_time));
        ret = 0;
        try {
            ret = this.sqliteDatabase.insert(DATABASE_TABLE, null, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public synchronized boolean deleteData(long ln_Id) {
        boolean ret;
        ret = false;
        try {
            ret = this.sqliteDatabase.delete(DATABASE_TABLE, new StringBuilder().append("ln_id=").append(ln_Id).toString(), null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Cursor fetchAllData() {
        return this.sqliteDatabase.query(DATABASE_TABLE, FIELDS, null, null, null, null, null);
    }

    public synchronized Cursor fetchData(long ln_id, int ln_type) throws SQLException {
        Cursor cursor;
        try {
            Cursor cursor2 = this.sqliteDatabase.query(true, DATABASE_TABLE, FIELDS, "ln_id=" + ln_id + " and " + KEY_LN_TYPE + "=" + ln_type, null, null, null, null, null);
            if (cursor2 != null) {
                cursor2.moveToFirst();
            }
            cursor = cursor2;
        } catch (Exception e) {
            cursor = null;
        }
        return cursor;
    }

    public synchronized boolean updateData(long ln_id, int ln_count, int ln_remove, int ln_type, String ln_extra, long ln_trigger_time, long ln_add_time) {
        boolean z;
        try {
            String where = "ln_id=" + ln_id;
            ContentValues content = new ContentValues();
            content.put(KEY_LN_ID, Long.valueOf(ln_id));
            content.put(KEY_LN_COUNT, Integer.valueOf(ln_count));
            content.put(KEY_LN_REMOVE, Integer.valueOf(ln_remove));
            content.put(KEY_LN_TYPE, Integer.valueOf(ln_type));
            content.put(KEY_LN_EXTRA, ln_extra);
            content.put(KEY_TRIGGER_TIME, Long.valueOf(ln_trigger_time));
            content.put(KEY_LN_ADD_TIME, Long.valueOf(ln_add_time));
            z = this.sqliteDatabase.update(DATABASE_TABLE, content, where, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public void clearData(long addTime) throws SQLException {
        String updateSQL = "UPDATE t_localnotification set ln_count=0 , ln_remove=0  where ln_count >0 and ln_add_time < " + addTime;
        Logger.m1420e(TAG, updateSQL);
        this.sqliteDatabase.execSQL(updateSQL);
    }

    public synchronized Cursor getOutDatas(int ln_count, long ln_trigger_time) {
        Cursor cursor;
        try {
            Cursor cursor2 = this.sqliteDatabase.query(true, DATABASE_TABLE, FIELDS, "ln_count=" + ln_count + " and " + KEY_TRIGGER_TIME + "<" + ln_trigger_time, null, null, null, null, null);
            if (cursor2 != null) {
                cursor2.moveToFirst();
            }
            cursor = cursor2;
        } catch (Exception e) {
            e.printStackTrace();
            cursor = null;
        }
        return cursor;
    }

    public synchronized boolean removeAllLN() {
        boolean z = true;
        synchronized (this) {
            String where = "ln_count>0";
            try {
                ContentValues content = new ContentValues();
                content.put(KEY_LN_COUNT, Integer.valueOf(0));
                content.put(KEY_LN_REMOVE, Integer.valueOf(1));
                content.put(KEY_LN_TYPE, Integer.valueOf(0));
                if (this.sqliteDatabase.update(DATABASE_TABLE, content, where, null) <= 0) {
                    z = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    public synchronized Cursor getRtcDatas(long curTime, long rtcTime) {
        Cursor cursor;
        cursor = null;
        try {
            cursor = this.sqliteDatabase.query(true, DATABASE_TABLE, FIELDS, "ln_count>0 and ln_trigger_time<" + (curTime + rtcTime) + " and " + KEY_TRIGGER_TIME + ">" + curTime, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
        }
        return cursor;
    }

    public synchronized void initData(Cursor cursor, LocalNotificationDBData dbData) {
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                if (dbData == null) {
                    dbData = new LocalNotificationDBData();
                }
                try {
                    dbData.setLn_id(cursor.getLong(1));
                    dbData.setLn_count(cursor.getInt(2));
                    dbData.setLn_remove(cursor.getInt(3));
                    dbData.setLn_type(cursor.getInt(4));
                    dbData.setLn_extra(cursor.getString(5));
                    dbData.setLn_trigger_time(cursor.getLong(6));
                    dbData.setLn_add_time(cursor.getLong(7));
                } catch (Exception e) {
                    e.getStackTrace();
                }
                Logger.m1424i(TAG, dbData.toString());
            }
        }
        Logger.m1416d(TAG, "cursor is null");
        return;
    }
}
