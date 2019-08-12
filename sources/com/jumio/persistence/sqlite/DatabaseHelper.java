package com.jumio.persistence.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jumio.persistence.sqlite.CreateOpenHelper.CreateOpenHelperParams;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseParams;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper {
    private static DatabaseHelper instance = null;
    AtomicInteger count;
    SQLiteDatabaseParams databaseParams;
    SQLiteOpenHelper openHelper;

    public static synchronized DatabaseHelper getInstance(SQLiteDatabaseParams databaseParams2) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            if (instance == null) {
                instance = new DatabaseHelper(databaseParams2);
            }
            databaseHelper = instance;
        }
        return databaseHelper;
    }

    private DatabaseHelper(SQLiteDatabaseParams databaseParams2) {
        this.openHelper = null;
        this.count = null;
        this.count = new AtomicInteger();
        this.databaseParams = databaseParams2;
    }

    public synchronized SQLiteOpenHelper openHelper() {
        this.count.getAndIncrement();
        if (this.openHelper == null) {
            this.openHelper = new CreateOpenHelper((CreateOpenHelperParams) this.databaseParams);
        }
        return this.openHelper;
    }

    public synchronized void closeHelper(SQLiteDatabase database) {
        if (this.count.decrementAndGet() == 0 && this.openHelper != null) {
            if (database != null) {
                database.close();
            }
            this.openHelper.close();
            this.openHelper = null;
        }
    }
}
