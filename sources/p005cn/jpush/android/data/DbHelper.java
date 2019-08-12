package p005cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.DbHelper */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "service.db";
    public static final String[] DOWN_LIST_COM_ITEM = {TABLE_ID, "msg_id", DOWN_LIST_NUMBER, DOWN_LIST_START_POS, DOWN_LIST_END_POS, "content"};
    public static final String DOWN_LIST_CONTENT = "content";
    public static final String DOWN_LIST_END_POS = "end_pos";
    public static final String DOWN_LIST_MSG_ID = "msg_id";
    public static final String DOWN_LIST_NUMBER = "repeat_num";
    public static final String DOWN_LIST_START_POS = "start_pos";
    public static final String TABLE_DOWN_LIST = "downlist";
    public static final String TABLE_ID = "_id";
    public static final String TABLE_UP_LIST = "uplist";
    public static final String UP_LIST_APPID = "app_id";
    public static final String[] UP_LIST_COM_ITEM = {TABLE_ID, "msg_id", "app_id", UP_LIST_MAINID};
    public static final String UP_LIST_MAINID = "main_id";
    public static final String UP_LIST_MSG_ID = "msg_id";
    public static final String UP_LIST_OVERRIDEID = "override_id";
    private static final int VERSION = 3;
    private static DbHelper dbHelper;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 3);
    }

    public void onCreate(SQLiteDatabase db) {
        Logger.m1416d("DbHelper", "onCreate");
        db.execSQL("create table downlist(_id integer primary key autoincrement,msg_id text,repeat_num integer,start_pos integer,end_pos integer,content text)");
        db.execSQL("create table uplist(_id integer primary key autoincrement,msg_id text,app_id text,main_id text, override_id text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.m1416d("DbHelper", "The oldVersion is: " + oldVersion + " the newVersion is : " + newVersion);
        db.execSQL("drop table downlist");
        db.execSQL("drop table uplist");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (VERSION.SDK_INT >= 11) {
            super.onDowngrade(db, oldVersion, newVersion);
        }
        Logger.m1416d("DbHelper", "The oldVersion is: " + oldVersion + " the newVersion is : " + newVersion);
        db.execSQL("drop table downlist");
        db.execSQL("drop table uplist");
        onCreate(db);
    }

    public static DbHelper getSQLiteDBHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    public static synchronized void updateUpTable(Context context, Entity entity, String app_id, String main_id) {
        synchronized (DbHelper.class) {
            updateUpTable(context, entity.messageId, entity.overrideMessageId, app_id, main_id);
        }
    }

    private static synchronized void updateUpTable(Context context, String msg_id, String override_id, String app_id, String main_id) {
        synchronized (DbHelper.class) {
            Cursor c = null;
            try {
                SQLiteDatabase db = getSQLiteDBHelper(context).getWritableDatabase();
                c = db.query(TABLE_UP_LIST, UP_LIST_COM_ITEM, "app_id=?", new String[]{app_id}, null, null, null);
                if (c == null || c.getCount() <= 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("msg_id", msg_id);
                    contentValues.put("app_id", app_id);
                    contentValues.put(UP_LIST_MAINID, main_id);
                    contentValues.put(UP_LIST_OVERRIDEID, override_id);
                    db.insert(TABLE_UP_LIST, null, contentValues);
                } else {
                    c.moveToFirst();
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("msg_id", msg_id);
                    contentValues2.put(UP_LIST_MAINID, main_id);
                    contentValues2.put(UP_LIST_OVERRIDEID, override_id);
                    db.update(TABLE_UP_LIST, contentValues2, "app_id=?", new String[]{app_id});
                }
                if (c != null) {
                    c.close();
                }
            } catch (Exception e) {
                if (c != null) {
                    c.close();
                }
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
                throw th;
            }
        }
    }

    public static synchronized String delUpTableForAppId(Context context, String appname) {
        String str;
        synchronized (DbHelper.class) {
            String msg_id = "";
            String main_id = "";
            String app_id = "";
            String overr_id = "";
            Cursor c = null;
            try {
                SQLiteDatabase db = getSQLiteDBHelper(context).getWritableDatabase();
                Cursor c2 = db.query(TABLE_UP_LIST, UP_LIST_COM_ITEM, null, null, null, null, null);
                if (c2 != null && c2.getCount() >= 0) {
                    while (true) {
                        if (!c2.moveToNext()) {
                            break;
                        }
                        String appid = c2.getString(c2.getColumnIndex("app_id"));
                        String msgid = c2.getString(c2.getColumnIndex("msg_id"));
                        String mainid = c2.getString(c2.getColumnIndex(UP_LIST_MAINID));
                        String overrid = c2.getString(c2.getColumnIndex(UP_LIST_OVERRIDEID));
                        if (appname.endsWith(appid)) {
                            msg_id = msgid;
                            app_id = appid;
                            main_id = mainid;
                            overr_id = overrid;
                            break;
                        }
                    }
                }
                db.delete(TABLE_UP_LIST, "app_id=?", new String[]{app_id});
                str = msg_id + "," + main_id + "," + overr_id;
                if (c2 != null) {
                    c2.close();
                }
            } catch (Exception e) {
                if (c != null) {
                    c.close();
                }
                str = msg_id;
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
                throw th;
            }
        }
        return str;
    }
}
