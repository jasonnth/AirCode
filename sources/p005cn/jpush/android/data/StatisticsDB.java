package p005cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.StatisticsDB */
public class StatisticsDB {
    public static final String DATABASE_CREATE = "CREATE TABLE jpush_statistics (_id INTEGER PRIMARY KEY AUTOINCREMENT ,st_sort_key text not null,st_net text not null,st_conn_ip text not null,st_local_dns text,st_source integer not null,st_failed integer not null,st_total integer not null,st_count_1 integer,st_count_1_3 integer,st_count_3_10 integer,st_count_10 integer);";
    private static final String DATABASE_NAME = "jpush_statistics.db";
    private static final String DATABASE_TABLE = "jpush_statistics";
    private static final int DATABASE_VERSION = 1;
    public static final String[] FIELDS = {KEY_ST_SORT, "_id", KEY_ST_NET, KEY_ST_CONN_IP, KEY_ST_LOCAL_DNS, KEY_ST_SOURCE, KEY_ST_FAILED, KEY_ST_TOTAL, KEY_ST_COUNT_1, KEY_ST_COUNT_1_3, KEY_ST_COUNT_3_10, KEY_ST_COUNT_10};
    private static final String KEY_ROWID = "_id";
    private static final String KEY_ST_CONN_IP = "st_conn_ip";
    private static final String KEY_ST_COUNT_1 = "st_count_1";
    private static final String KEY_ST_COUNT_10 = "st_count_10";
    private static final String KEY_ST_COUNT_1_3 = "st_count_1_3";
    private static final String KEY_ST_COUNT_3_10 = "st_count_3_10";
    private static final String KEY_ST_FAILED = "st_failed";
    private static final String KEY_ST_LOCAL_DNS = "st_local_dns";
    private static final String KEY_ST_NET = "st_net";
    private static final String KEY_ST_SORT = "st_sort_key";
    private static final String KEY_ST_SOURCE = "st_source";
    private static final String KEY_ST_TOTAL = "st_total";
    private static final String TAG = "StatisticsDB";
    private static Object mObject = new Object();
    private static StatisticsDB mStatisticsDBInstance;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDatabase;

    /* renamed from: cn.jpush.android.data.StatisticsDB$DBHelper */
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        public DBHelper(Context context) {
            this(context, StatisticsDB.DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(StatisticsDB.DATABASE_CREATE);
            } catch (Exception e) {
                Logger.m1428v(StatisticsDB.TAG, "table jpush_statistics is already");
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS jpush_statistics");
            onCreate(db);
        }
    }

    private StatisticsDB(Context context2) {
        this.context = context2;
        this.dbHelper = new DBHelper(context2);
    }

    public static StatisticsDB getInstance(Context ctx) {
        synchronized (mObject) {
            if (mStatisticsDBInstance == null) {
                mStatisticsDBInstance = new StatisticsDB(ctx);
            }
        }
        return mStatisticsDBInstance;
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

    public synchronized long insertData(String st_sort, String st_net, String st_conn_ip, String st_local_dns, String st_source, int st_failed, int st_total, int st_count_1, int st_count_1_3, int st_count_3_10, int st_count_10) {
        long ret;
        ret = 0;
        ContentValues content = new ContentValues();
        content.put(KEY_ST_SORT, st_sort);
        content.put(KEY_ST_NET, st_net);
        content.put(KEY_ST_CONN_IP, st_conn_ip);
        content.put(KEY_ST_LOCAL_DNS, st_local_dns);
        content.put(KEY_ST_SOURCE, st_source);
        content.put(KEY_ST_FAILED, Integer.valueOf(st_failed));
        content.put(KEY_ST_TOTAL, Integer.valueOf(st_total));
        content.put(KEY_ST_COUNT_1, Integer.valueOf(st_count_1));
        content.put(KEY_ST_COUNT_1_3, Integer.valueOf(st_count_1_3));
        content.put(KEY_ST_COUNT_3_10, Integer.valueOf(st_count_3_10));
        content.put(KEY_ST_COUNT_10, Integer.valueOf(st_count_10));
        try {
            ret = this.sqliteDatabase.insert(DATABASE_TABLE, null, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean deleteData(String sort_key) {
        try {
            return this.sqliteDatabase.delete(DATABASE_TABLE, new StringBuilder().append("st_sort_key='").append(sort_key).append("'").toString(), null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor fetchAllData() {
        Cursor cursor = null;
        try {
            return this.sqliteDatabase.query(DATABASE_TABLE, FIELDS, null, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return cursor;
        }
    }

    public Cursor fetchData(String sort_key) throws SQLException {
        Cursor cursor = null;
        try {
            cursor = this.sqliteDatabase.query(true, DATABASE_TABLE, FIELDS, "st_sort_key='" + sort_key + "'", null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public synchronized boolean updateData(String st_sort, String st_net, String st_conn_ip, String st_local_dns, String st_source, int st_failed, int st_total, int st_count_1, int st_count_1_3, int st_count_3_10, int st_count_10) {
        boolean ret;
        String where = "st_sort_key='" + st_sort + "'";
        ContentValues content = new ContentValues();
        content.put(KEY_ST_SORT, st_sort);
        content.put(KEY_ST_NET, st_net);
        content.put(KEY_ST_CONN_IP, st_conn_ip);
        content.put(KEY_ST_LOCAL_DNS, st_local_dns);
        content.put(KEY_ST_SOURCE, st_source);
        content.put(KEY_ST_FAILED, Integer.valueOf(st_failed));
        content.put(KEY_ST_TOTAL, Integer.valueOf(st_total));
        content.put(KEY_ST_COUNT_1, Integer.valueOf(st_count_1));
        content.put(KEY_ST_COUNT_1_3, Integer.valueOf(st_count_1_3));
        content.put(KEY_ST_COUNT_3_10, Integer.valueOf(st_count_3_10));
        content.put(KEY_ST_COUNT_10, Integer.valueOf(st_count_10));
        ret = false;
        try {
            ret = this.sqliteDatabase.update(DATABASE_TABLE, content, where, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public synchronized boolean isExist(String sort_key) {
        boolean ret;
        ret = false;
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.sqliteDatabase.rawQuery("select COUNT(st_sort_key) from jpush_statistics where st_sort_key='" + sort_key + "'", null);
            if (cursor2 != null) {
                cursor2.moveToFirst();
                if (cursor2.getInt(0) == 0) {
                    ret = false;
                } else {
                    ret = true;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return ret;
    }

    public synchronized void deleteTable() {
        try {
            this.sqliteDatabase.execSQL("delete from jpush_statistics");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void deleteDataBySql(String sort_key) {
        try {
            this.sqliteDatabase.execSQL("delete from jpush_statistics where st_sort_key= ?", new Object[]{sort_key});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Cursor getDataBySortkey(String st_sort) {
        Cursor cursor;
        cursor = null;
        try {
            cursor = this.sqliteDatabase.query(true, DATABASE_TABLE, FIELDS, "st_sort_key='" + st_sort + "'", null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public synchronized StatisticsDBData getStatisticsData(Cursor cursor) {
        StatisticsDBData dbData;
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                dbData = new StatisticsDBData();
                try {
                    dbData.setSt_sort(cursor.getString(1));
                    dbData.setSt_net(cursor.getString(2));
                    dbData.setSt_conn_ip(cursor.getString(3));
                    dbData.setSt_local_dns(cursor.getString(4));
                    dbData.setSt_source(cursor.getString(5));
                    dbData.setFailed(cursor.getInt(6));
                    dbData.setTotal(cursor.getInt(7));
                    dbData.setCount_1(cursor.getInt(8));
                    dbData.setCount_1_3(cursor.getInt(9));
                    dbData.setCount_3_10(cursor.getInt(10));
                    dbData.setCount_10(cursor.getInt(11));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Logger.m1424i(TAG, dbData.toString());
            }
        }
        Logger.m1416d(TAG, "cursor is null");
        dbData = null;
        return dbData;
    }

    public synchronized Cursor getFailedTop3Data() {
        Cursor cursor;
        cursor = null;
        try {
            cursor = this.sqliteDatabase.rawQuery("select * from jpush_statistics where st_failed > 0  order by st_failed desc limit 3", null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public synchronized Cursor getSucceedTop3Data() {
        Cursor cursor;
        cursor = null;
        try {
            cursor = this.sqliteDatabase.rawQuery("select * from jpush_statistics where st_total > 0 and st_failed = 0  order by st_total desc limit 3", null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public synchronized int getCountTotalOrFailed(boolean isTotal) {
        int returnCount;
        returnCount = 0;
        String strFlag = KEY_ST_FAILED;
        if (isTotal) {
            strFlag = KEY_ST_TOTAL;
        }
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.sqliteDatabase.rawQuery("select SUM(" + strFlag + ") from " + DATABASE_TABLE, null);
            if (cursor2 != null) {
                cursor2.moveToFirst();
                returnCount = cursor2.getInt(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return returnCount;
    }
}
