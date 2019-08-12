package com.jumio.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jumio.commons.log.Log;
import com.jumio.persistence.sqlite.CreateOpenHelper.CreateOpenHelperParams;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseCallback;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseParams;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteTableParams;
import com.jumio.persistence.sqlite.SQLiteDB;
import p005cn.jpush.android.data.DbHelper;

public class SqliteSink implements PersistenceTarget {
    private static final String COLUMN_BINARY_DATA = "binaryData";
    private static final String COLUMN_KEY = "key";
    private static final String DATABASE_NAME = "JMSDK 2.4.0 (0-55)_objectpool";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "objects";
    private static final String TAG = "SqliteSink";
    private String key = null;
    private SQLiteDB mDatabase;
    private CreateOpenHelperParams mDatabaseParams;

    public SqliteSink(Context context, String key2) {
        if (this.mDatabaseParams == null) {
            this.mDatabaseParams = createDatabaseParams(context);
        }
        this.key = key2;
        SQLiteTableParams tableParams = new SQLiteTableParams();
        tableParams.table = TABLE_NAME;
        tableParams.columns = new String[]{DbHelper.TABLE_ID, COLUMN_BINARY_DATA, COLUMN_KEY};
        this.mDatabase = new SQLiteDB(tableParams, (SQLiteDatabaseParams) this.mDatabaseParams);
    }

    private static CreateOpenHelperParams createDatabaseParams(Context context) {
        CreateOpenHelperParams params = new CreateOpenHelperParams();
        params.databaseName = DATABASE_NAME;
        params.databaseVersion = 1;
        params.context = context;
        params.create = new String[]{String.format("CREATE TABLE IF NOT EXISTS `%s` (`_id` integer primary key autoincrement NOT NULL , `binaryData` BLOB NOT NULL, `key` TEXT NOT NULL );", new Object[]{TABLE_NAME})};
        params.callback = new SQLiteDatabaseCallback() {
            public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
                Log.m1929w("Persistor", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            }
        };
        return params;
    }

    public long storeBlob(byte[] blob) throws PersistenceException {
        ContentValues vals = new ContentValues();
        if (blob != null) {
            try {
                vals.put(COLUMN_BINARY_DATA, blob);
                if (this.key != null) {
                    vals.put(COLUMN_KEY, this.key);
                }
                if (this.mDatabase.open()) {
                    this.mDatabase.beginTransaction();
                    long res = this.mDatabase.insert(vals);
                    this.mDatabase.commitTransaction();
                    this.mDatabase.endTransaction();
                    this.mDatabase.close();
                    return res;
                }
                this.mDatabase.endTransaction();
                this.mDatabase.close();
            } catch (RuntimeException ex) {
                Log.m1930w("Persistor", "error in storeAsBlob()", (Throwable) ex);
                throw new PersistenceException((Exception) ex);
            } catch (Exception ex2) {
                Log.m1930w("Persistor", "error in storeAsBlob()", (Throwable) ex2);
                throw new PersistenceException(ex2);
            } catch (Throwable th) {
                this.mDatabase.endTransaction();
                this.mDatabase.close();
                throw th;
            }
        }
        return -1;
    }

    public byte[] readBlob() throws PersistenceException {
        Cursor cursor;
        byte[] object = null;
        try {
            if (this.mDatabase.open()) {
                this.mDatabase.beginTransaction();
                cursor = this.mDatabase.select(new String[]{"*"}, this.key == null ? "_id > -1" : "key = '" + this.key + "'", "_id DESC", null);
                if (cursor.getCount() > 0) {
                    object = cursor.getBlob(cursor.getColumnIndex(COLUMN_BINARY_DATA));
                    this.mDatabase.commitTransaction();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
            this.mDatabase.endTransaction();
            this.mDatabase.close();
            return object;
        } catch (Exception ex) {
            try {
                Log.m1930w("Persistor", "error in restoreFromBlob()", (Throwable) ex);
                throw new PersistenceException(ex);
            } catch (Throwable th) {
                this.mDatabase.endTransaction();
                this.mDatabase.close();
                throw th;
            }
        } catch (Throwable th2) {
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    public boolean delete() throws PersistenceException {
        boolean res;
        try {
            if (this.mDatabase.open()) {
                this.mDatabase.beginTransaction();
                this.mDatabase.delete("key = '" + this.key + "'");
                this.mDatabase.commitTransaction();
                res = true;
            } else {
                res = false;
            }
            this.mDatabase.endTransaction();
            this.mDatabase.close();
            return res;
        } catch (Exception e) {
            Log.m1930w("Persistor", "error in delete()", (Throwable) e);
            throw new PersistenceException(e);
        } catch (Throwable th) {
            this.mDatabase.endTransaction();
            this.mDatabase.close();
            throw th;
        }
    }
}
