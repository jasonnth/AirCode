package com.jumio.persistence.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
    protected SQLiteDatabase database;
    protected DatabaseHelper databaseHelper;
    boolean instanceOpened = false;

    public interface SQLiteDatabaseCallback {
        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public static class SQLiteDatabaseParams {
        public SQLiteDatabaseCallback callback;
        public Context context;
        public String databaseName;
        public int databaseVersion;
    }

    public static class SQLiteTableParams {
        public String[] columns;
        public String table;
    }

    public DatabaseAdapter(DatabaseHelper databaseHelper2) {
        this.databaseHelper = databaseHelper2;
    }

    public DatabaseAdapter(SQLiteDatabaseParams databaseParams) {
        this.databaseHelper = DatabaseHelper.getInstance(databaseParams);
    }

    public synchronized boolean open() throws SQLException {
        if (!this.instanceOpened) {
            this.database = this.databaseHelper.openHelper().getWritableDatabase();
            this.instanceOpened = this.database != null;
        }
        return this.instanceOpened;
    }

    public synchronized void close() {
        if (this.instanceOpened) {
            this.databaseHelper.closeHelper(this.database);
            this.instanceOpened = false;
        }
    }

    public boolean inTransaction() {
        return this.database.inTransaction();
    }

    public void beginTransaction() {
        this.database.beginTransaction();
    }

    public void commitTransaction() {
        if (this.database != null && inTransaction()) {
            this.database.setTransactionSuccessful();
        }
    }

    public void endTransaction() {
        if (this.database != null && inTransaction()) {
            this.database.endTransaction();
        }
    }
}
