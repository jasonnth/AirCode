package com.danikula.videocache.sourcestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.danikula.videocache.Preconditions;
import com.danikula.videocache.SourceInfo;
import p005cn.jpush.android.data.DbHelper;

class DatabaseSourceInfoStorage extends SQLiteOpenHelper implements SourceInfoStorage {
    private static final String[] ALL_COLUMNS = {DbHelper.TABLE_ID, "url", "length", "mime"};

    DatabaseSourceInfoStorage(Context context) {
        super(context, "AndroidVideoCache.db", null, 1);
        Preconditions.checkNotNull(context);
    }

    public void onCreate(SQLiteDatabase db) {
        Preconditions.checkNotNull(db);
        db.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    /* JADX INFO: finally extract failed */
    public SourceInfo get(String url) {
        Preconditions.checkNotNull(url);
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().query("SourceInfo", ALL_COLUMNS, "url=?", new String[]{url}, null, null, null);
            SourceInfo convert = (cursor == null || !cursor.moveToFirst()) ? null : convert(cursor);
            if (cursor != null) {
                cursor.close();
            }
            return convert;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void put(String url, SourceInfo sourceInfo) {
        boolean exist;
        Preconditions.checkAllNotNull(url, sourceInfo);
        if (get(url) != null) {
            exist = true;
        } else {
            exist = false;
        }
        ContentValues contentValues = convert(sourceInfo);
        if (exist) {
            getWritableDatabase().update("SourceInfo", contentValues, "url=?", new String[]{url});
            return;
        }
        getWritableDatabase().insert("SourceInfo", null, contentValues);
    }

    private SourceInfo convert(Cursor cursor) {
        return new SourceInfo(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getString(cursor.getColumnIndexOrThrow("mime")));
    }

    private ContentValues convert(SourceInfo sourceInfo) {
        ContentValues values = new ContentValues();
        values.put("url", sourceInfo.url);
        values.put("length", Long.valueOf(sourceInfo.length));
        values.put("mime", sourceInfo.mime);
        return values;
    }
}
