package com.jumio.persistence.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseParams;

public class CreateOpenHelper extends SQLiteOpenHelper {
    private CreateOpenHelperParams databaseParams;

    public static class CreateOpenHelperParams extends SQLiteDatabaseParams {
        public String[] create;
    }

    public CreateOpenHelper(CreateOpenHelperParams databaseParams2) {
        super(databaseParams2.context, databaseParams2.databaseName, null, databaseParams2.databaseVersion);
        this.databaseParams = databaseParams2;
    }

    public void onCreate(SQLiteDatabase database) {
        for (String execSQL : this.databaseParams.create) {
            database.execSQL(execSQL);
        }
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (this.databaseParams.callback != null && oldVersion < newVersion) {
            this.databaseParams.callback.onUpgrade(database, oldVersion, newVersion);
        }
    }
}
