package p005cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.ReportDBHelper */
public class ReportDBHelper extends SQLiteOpenHelper {
    private static final String DB_CREATE_STR = "CREATE TABLE rep_table (rep_id integer primary key autoincrement,rep_prefix TEXT,rep_data TEXT)";
    private static final String DB_CREATE_TCP = "CREATE TABLE tcp_table (rep_id integer primary key autoincrement,tcp_data TEXT)";
    private static final String DB_NAME = "rep.db";
    private static final String DB_TABLE = "rep_table";
    private static final String DB_TCP_TABLE = "tcp_table";
    public static final String KEY_DATA = "rep_data";
    public static final String KEY_ID = "rep_id";
    public static final String KEY_PREFIX = "rep_prefix";
    public static final String KEY_TCP_DATA = "tcp_data";
    private static final String TAG = "SQLiteOpenHelper";
    private static final int VERSION = 3;
    private static Object mObject = new Object();
    private static ReportDBHelper reportDBHelper;

    public ReportDBHelper(Context context) {
        super(context, DB_NAME, null, 3);
    }

    public void onCreate(SQLiteDatabase db) {
        Logger.m1424i(TAG, "onCreate");
        db.execSQL(DB_CREATE_STR);
        db.execSQL(DB_CREATE_TCP);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.m1416d(TAG, "The oldVersion is: " + oldVersion + " the newVersion is : " + newVersion);
        db.execSQL("drop table IF EXISTS rep_table");
        db.execSQL("drop table IF EXISTS tcp_table");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (VERSION.SDK_INT >= 11) {
            super.onDowngrade(db, oldVersion, newVersion);
        }
        Logger.m1416d(TAG, "The oldVersion is: " + oldVersion + " the newVersion is : " + newVersion);
        db.execSQL("drop table IF EXISTS rep_table");
        db.execSQL("drop table IF EXISTS tcp_table");
        onCreate(db);
    }

    public static ReportDBHelper getSQLiteDBHelper(Context context) {
        synchronized (mObject) {
            if (reportDBHelper == null) {
                reportDBHelper = new ReportDBHelper(context);
            }
        }
        return reportDBHelper;
    }

    public static boolean insert(Context context, String pre, String data) {
        try {
            SQLiteDatabase db = getSQLiteDBHelper(context).getWritableDatabase();
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_PREFIX, pre);
            initialValues.put(KEY_DATA, data);
            if (db.insert(DB_TABLE, KEY_ID, initialValues) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.m1417d(TAG, "", e);
            return false;
        }
    }

    public static synchronized boolean insertTcp(Context context, String data) {
        boolean z = false;
        synchronized (ReportDBHelper.class) {
            try {
                SQLiteDatabase db = getSQLiteDBHelper(context).getWritableDatabase();
                ContentValues initialValues = new ContentValues();
                initialValues.put(KEY_TCP_DATA, data);
                if (db.insert(DB_TCP_TABLE, KEY_ID, initialValues) > 0) {
                    z = true;
                }
            } catch (Exception e) {
                Logger.m1417d(TAG, "", e);
            }
        }
        return z;
    }

    public static Cursor fetchAllData(Context context) {
        try {
            return getSQLiteDBHelper(context).getWritableDatabase().query(DB_TABLE, new String[]{KEY_ID, KEY_DATA, KEY_PREFIX}, null, null, null, null, KEY_ID);
        } catch (Exception e) {
            Logger.m1417d(TAG, "", e);
            return null;
        }
    }

    public static Cursor fetchAllTCPData(Context context) {
        try {
            return getSQLiteDBHelper(context).getWritableDatabase().query(DB_TCP_TABLE, new String[]{KEY_ID, KEY_TCP_DATA}, null, null, null, null, KEY_ID);
        } catch (Exception e) {
            Logger.m1417d(TAG, "", e);
            return null;
        }
    }

    public static boolean deleteData(Context context, int rowId) {
        try {
            if (getSQLiteDBHelper(context).getWritableDatabase().delete(DB_TABLE, "rep_id=" + rowId, null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.m1417d(TAG, "", e);
            return false;
        }
    }

    public static boolean deleteTCPData(Context context, int rowId) {
        try {
            if (getSQLiteDBHelper(context).getWritableDatabase().delete(DB_TCP_TABLE, "rep_id=" + rowId, null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.m1417d(TAG, "", e);
            return false;
        }
    }
}
