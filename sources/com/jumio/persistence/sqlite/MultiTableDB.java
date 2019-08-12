package com.jumio.persistence.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteDatabaseParams;
import com.jumio.persistence.sqlite.DatabaseAdapter.SQLiteTableParams;

public class MultiTableDB extends DatabaseAdapter {
    public MultiTableDB(DatabaseHelper databaseHelper) {
        super(databaseHelper);
    }

    public MultiTableDB(SQLiteDatabaseParams databaseParams) {
        super(databaseParams);
    }

    public long insert(SQLiteTableParams tableParams, String[] values) {
        return insert(tableParams.table, tableParams.columns, values);
    }

    public long insert(String table, String[] columns, String[] values) {
        ContentValues contentValues = new ContentValues();
        if (columns.length != values.length) {
            throw new SQLException("Size of Columns is not equal to size of values!");
        }
        for (int i = 0; i < columns.length; i++) {
            if (values[i].compareToIgnoreCase("NULL") == 0) {
                contentValues.putNull(columns[i]);
            } else {
                contentValues.put(columns[i], values[i]);
            }
        }
        return this.database.insert(table, null, contentValues);
    }

    public long insert(String table, ContentValues values) {
        return this.database.insert(table, null, values);
    }

    public boolean update(SQLiteTableParams tableParams, String[] values) {
        return update(tableParams.table, tableParams.columns, values);
    }

    public boolean update(SQLiteTableParams tableParams, String[] values, String where) {
        return update(tableParams.table, tableParams.columns, values, where);
    }

    public boolean update(String table, String[] columns, String[] values) {
        return update(table, columns, values, "_id = " + values[0]);
    }

    public boolean update(String table, String[] columns, String[] values, String where) {
        ContentValues contentValues = new ContentValues();
        if (columns.length != values.length) {
            throw new SQLException("Size of Columns is not equal to size of values!");
        }
        for (int i = 0; i < columns.length; i++) {
            if (values[i].compareTo("") != 0) {
                if (values[i].compareToIgnoreCase("NULL") == 0) {
                    contentValues.putNull(columns[i]);
                }
                contentValues.put(columns[i], values[i]);
            }
        }
        return this.database.update(table, contentValues, where, null) > 0;
    }

    public boolean delete(SQLiteTableParams tableParams, int index, String value) {
        return delete(tableParams.table, tableParams.columns[index] + "=" + value);
    }

    public boolean delete(String table, String index, String value) {
        return delete(table, index + "=" + value);
    }

    public boolean delete(String table, String where) {
        return this.database.delete(table, where, null) > 0;
    }

    public boolean truncate(String table) {
        return this.database.delete(table, null, null) > 0;
    }

    public Cursor select(SQLiteTableParams tableParams, String where, String orderBy, String limit) throws SQLException {
        return select(tableParams.table, tableParams.columns, where, null, null, null, orderBy, limit);
    }

    public Cursor select(String table, String[] columns, String where, String orderBy, String limit) throws SQLException {
        return select(table, columns, where, null, null, null, orderBy, limit);
    }

    public Cursor select(SQLiteTableParams tableParams, String where, String[] whereArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        return select(tableParams.table, tableParams.columns, where, whereArgs, groupBy, having, orderBy, limit);
    }

    public Cursor select(String table, String[] columns, String where, String[] whereArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        Cursor cursor = this.database.query(true, table, columns, where, whereArgs, groupBy, having, orderBy, limit);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor join(String[] tables, String[] columns, String where, String orderBy, String limit) throws SQLException {
        return join(false, tables, columns, where, null, null, null, orderBy, limit);
    }

    public Cursor join(boolean distinct, String[] tables, String[] columns, String where, String orderBy, String limit) throws SQLException {
        return join(distinct, tables, columns, where, null, null, null, orderBy, limit);
    }

    public Cursor join(String[] tables, String[] columns, String where, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        return join(false, tables, columns, where, selectionArgs, groupBy, having, orderBy, limit);
    }

    public Cursor join(boolean distinct, String[] tables, String[] columns, String where, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        if (distinct) {
            query.append("DISTINCT ");
        }
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i + 1 < columns.length) {
                query.append(", ");
            }
        }
        query.append(" FROM ");
        for (int i2 = 0; i2 < tables.length; i2++) {
            query.append(tables[i2] + " ");
        }
        if (where != null) {
            query.append(" WHERE " + where);
        }
        if (groupBy != null) {
            query.append(" GROUP BY " + groupBy);
        }
        if (having != null) {
            query.append(" HAVING " + having);
        }
        if (orderBy != null) {
            query.append(" ORDER BY " + orderBy);
        }
        if (limit != null) {
            query.append(" LIMIT " + limit);
        }
        Cursor cursor = this.database.rawQuery(query.toString(), selectionArgs);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor raw(String query) {
        Cursor cursor = this.database.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
