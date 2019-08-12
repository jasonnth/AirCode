package p005cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: cn.jpush.android.data.MessageDBAdapter */
public class MessageDBAdapter {
    private static final String DBAdapter_DB_CREATE = "CREATE TABLE msg_table (msg_id TEXT PRIMARY KEY,msg_package TEXT,msg_data TEXT,msg_type INTERGER,msg_order INTERGER)";
    private static final String DBAdapter_DB_NAME = "MsgDB.db";
    private static final String DBAdapter_DB_TABLE = "msg_table";
    private static final int DBAdapter_DB_VERSION = 1;
    public static final String DBAdapter_KEY_DATA = "msg_data";
    public static final String DBAdapter_KEY_MESSAGEID = "msg_id";
    public static final String DBAdapter_KEY_ORDER = "msg_order";
    public static final String DBAdapter_KEY_PACKAGE = "msg_package";
    public static final String DBAdapter_KEY_TYPE = "msg_type";
    private static DatabaseHelper mDatabaseHelper = null;
    private static SQLiteDatabase mSQLiteDatabase = null;

    /* renamed from: cn.jpush.android.data.MessageDBAdapter$DatabaseHelper */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, MessageDBAdapter.DBAdapter_DB_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MessageDBAdapter.DBAdapter_DB_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    public MessageDBAdapter(Context context) {
    }

    public static void open(Context context) {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new DatabaseHelper(context);
        }
        if (mSQLiteDatabase == null) {
            mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        }
    }

    public void close() {
        if (mDatabaseHelper != null) {
            mDatabaseHelper.close();
        }
    }

    public static String insertOrUpdateData(Context context, String msgId, String data, String packageName, int type, int order) {
        open(context);
        Cursor c = null;
        try {
            c = mSQLiteDatabase.query(true, DBAdapter_DB_TABLE, new String[]{"msg_id", DBAdapter_KEY_PACKAGE, DBAdapter_KEY_DATA}, "msg_id=" + msgId, null, null, null, null, null);
            if (c == null || c.getCount() <= 0) {
                ContentValues initialValues = new ContentValues();
                initialValues.put("msg_id", msgId);
                initialValues.put(DBAdapter_KEY_PACKAGE, packageName);
                initialValues.put(DBAdapter_KEY_DATA, data);
                initialValues.put(DBAdapter_KEY_TYPE, Integer.valueOf(type));
                initialValues.put(DBAdapter_KEY_ORDER, Integer.valueOf(order));
                mSQLiteDatabase.insert(DBAdapter_DB_TABLE, "msg_id", initialValues);
            } else {
                c.moveToFirst();
                ContentValues initialValues2 = new ContentValues();
                initialValues2.put(DBAdapter_KEY_PACKAGE, packageName);
                initialValues2.put(DBAdapter_KEY_DATA, data);
                initialValues2.put(DBAdapter_KEY_TYPE, Integer.valueOf(type));
                initialValues2.put(DBAdapter_KEY_ORDER, Integer.valueOf(order));
                mSQLiteDatabase.update(DBAdapter_DB_TABLE, initialValues2, "msg_id=?", new String[]{msgId});
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            msgId = null;
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
        return msgId;
    }

    public boolean deleteData(Context context, String rowId) {
        open(context);
        return mSQLiteDatabase.delete(DBAdapter_DB_TABLE, new StringBuilder().append("msg_id=").append(rowId).toString(), null) > 0;
    }

    public static Cursor fetchAllData(Context context) {
        open(context);
        return mSQLiteDatabase.query(DBAdapter_DB_TABLE, new String[]{"msg_id", DBAdapter_KEY_PACKAGE, DBAdapter_KEY_DATA}, null, null, null, null, null);
    }

    public static Cursor fetchData(Context context, String msgId) throws SQLException {
        open(context);
        Cursor mCursor = mSQLiteDatabase.query(true, DBAdapter_DB_TABLE, new String[]{"msg_id", DBAdapter_KEY_PACKAGE, DBAdapter_KEY_DATA}, "msg_id=" + msgId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public static boolean updateData(Context context, String msgId, String data, String packageName, int type, int order) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("msg_id", msgId);
        initialValues.put(DBAdapter_KEY_PACKAGE, packageName);
        initialValues.put(DBAdapter_KEY_DATA, data);
        initialValues.put(DBAdapter_KEY_TYPE, Integer.valueOf(type));
        initialValues.put(DBAdapter_KEY_ORDER, Integer.valueOf(order));
        return mSQLiteDatabase.update(DBAdapter_DB_TABLE, initialValues, new StringBuilder().append("msg_id=").append(msgId).toString(), null) > 0;
    }
}
