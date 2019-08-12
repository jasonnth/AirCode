package com.airbnb.android.aireventlogger;

import android.database.sqlite.SQLiteDatabase;

interface TableHandler {
    void onCreate(SQLiteDatabase sQLiteDatabase);

    void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    void onOpen(SQLiteDatabase sQLiteDatabase);

    void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
}
