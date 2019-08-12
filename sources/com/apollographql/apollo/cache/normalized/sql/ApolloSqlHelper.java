package com.apollographql.apollo.cache.normalized.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import p005cn.jpush.android.data.DbHelper;

public class ApolloSqlHelper extends SQLiteOpenHelper {
    private static final String CREATE_KEY_INDEX = String.format("CREATE INDEX %s ON %s (%s)", new Object[]{"idx_records_key", "records", "key"});
    private static final String DATABASE_CREATE = String.format("create table %s( %s integer primary key autoincrement, %s text not null, %s text not null);", new Object[]{"records", DbHelper.TABLE_ID, "key", "record"});

    public ApolloSqlHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    public static ApolloSqlHelper create(Context context, String name) {
        return new ApolloSqlHelper(context, name);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(CREATE_KEY_INDEX);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS records");
        onCreate(db);
    }
}
