package com.mparticle.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.mparticle.internal.p */
class C4623p extends SQLiteOpenHelper {
    C4623p(Context context) {
        super(context, "mparticle_segment.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS segments (_id INTEGER PRIMARY KEY, name TEXT NOT NULL, endpoint_ids TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS segment_memberships (_id INTEGER PRIMARY KEY AUTOINCREMENT, segment_id INTEGER NOT NULL, timestamp REAL NOT NULL, action INTEGER NOT NULL,  FOREIGN KEY (segment_id) REFERENCES segments (_id));");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS segments (_id INTEGER PRIMARY KEY, name TEXT NOT NULL, endpoint_ids TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS segment_memberships (_id INTEGER PRIMARY KEY AUTOINCREMENT, segment_id INTEGER NOT NULL, timestamp REAL NOT NULL, action INTEGER NOT NULL,  FOREIGN KEY (segment_id) REFERENCES segments (_id));");
    }
}
