package com.jumio.persistence.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseParams;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteTableParams;
import p005cn.jpush.android.data.DbHelper;

public class SQLiteDB extends DatabaseAdapter {
    private SQLiteTableParams tableParams;

    public SQLiteDB(SQLiteTableParams tableParams2, DatabaseHelper databaseHelper) {
        super(databaseHelper);
        this.tableParams = tableParams2;
    }

    public SQLiteDB(SQLiteTableParams tableParams2, SQLiteDatabaseParams databaseParams) {
        super(databaseParams);
        this.tableParams = tableParams2;
    }

    public long insert(String[] values) {
        ContentValues contentValues = new ContentValues();
        if (this.database == null) {
            throw new IllegalStateException("call open() before insert()!");
        }
        if (this.tableParams.columns[0].equals(DbHelper.TABLE_ID)) {
            if (this.tableParams.columns.length - 1 != values.length) {
                throw new SQLException("Size of Columns is not equal to size of values!");
            }
            for (int i = 1; i < this.tableParams.columns.length; i++) {
                if (values[i - 1].compareToIgnoreCase("NULL") == 0) {
                    contentValues.putNull(this.tableParams.columns[i]);
                } else {
                    contentValues.put(this.tableParams.columns[i], values[i - 1]);
                }
            }
        } else if (this.tableParams.columns.length != values.length) {
            throw new SQLException("Size of Columns is not equal to size of values!");
        } else {
            for (int i2 = 0; i2 < this.tableParams.columns.length; i2++) {
                if (values[i2].compareToIgnoreCase("NULL") == 0) {
                    contentValues.putNull(this.tableParams.columns[i2]);
                } else {
                    contentValues.put(this.tableParams.columns[i2], values[i2]);
                }
            }
        }
        return this.database.insert(this.tableParams.table, null, contentValues);
    }

    public long insert(ContentValues values) {
        return this.database.insert(this.tableParams.table, null, values);
    }

    public boolean update(String[] values) {
        return update(this.tableParams.columns, values, "_id = " + values[0]);
    }

    public boolean update(String[] values, String where) {
        return update(this.tableParams.columns, values, where);
    }

    public boolean update(String[] columns, String[] values, String where) {
        ContentValues contentValues = new ContentValues();
        if (columns.length != values.length) {
            throw new SQLException("Size of Columns is not equal to size of values!");
        }
        for (int i = 0; i < columns.length; i++) {
            if (values[i].compareTo("") != 0) {
                if (values[i].compareToIgnoreCase("NULL") == 0) {
                    contentValues.putNull(this.tableParams.columns[i]);
                }
                contentValues.put(columns[i], values[i]);
            }
        }
        return this.database.update(this.tableParams.table, contentValues, where, null) > 0;
    }

    public boolean deleteID(String id) {
        return this.database.delete(this.tableParams.table, new StringBuilder().append(this.tableParams.columns[0]).append("=").append(id).toString(), null) > 0;
    }

    public boolean delete(String where) {
        return this.database.delete(this.tableParams.table, where, null) > 0;
    }

    public boolean truncate() {
        return this.database.delete(this.tableParams.table, null, null) > 0;
    }

    public Cursor select(String[] columns, String where, String orderBy, String limit) throws SQLException {
        return select(columns, where, null, null, orderBy, limit);
    }

    public Cursor select(String[] columns, String where, String groupBy, String having, String orderBy, String limit) throws SQLException {
        Cursor cursor = this.database.query(true, this.tableParams.table, columns, where, null, groupBy, having, orderBy, limit);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean dropTable() {
        this.database.execSQL("DROP TABLE `" + this.tableParams.table + "`;");
        return true;
    }
}
