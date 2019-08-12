package com.apollographql.apollo.cache.normalized.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import p005cn.jpush.android.data.DbHelper;

public final class SqlNormalizedCache extends NormalizedCache {
    private static final String DELETE_ALL_RECORD_STATEMENT = String.format("DELETE FROM %s", new Object[]{"records"});
    private static final String DELETE_STATEMENT = String.format("DELETE FROM %s WHERE %s=?", new Object[]{"records", "key"});
    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)", new Object[]{"records", "key", "record"});
    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?", new Object[]{"records", "key", "record", "key"});
    private final String[] allColumns = {DbHelper.TABLE_ID, "key", "record"};
    SQLiteDatabase database;
    private final ApolloSqlHelper dbHelper;
    private final SQLiteStatement deleteAllRecordsStatement;
    private final SQLiteStatement deleteStatement;
    private final SQLiteStatement insertStatement;
    private final SQLiteStatement updateStatement;

    SqlNormalizedCache(RecordFieldAdapter recordFieldAdapter, ApolloSqlHelper dbHelper2) {
        super(recordFieldAdapter);
        this.dbHelper = dbHelper2;
        this.database = dbHelper2.getWritableDatabase();
        this.insertStatement = this.database.compileStatement(INSERT_STATEMENT);
        this.updateStatement = this.database.compileStatement(UPDATE_STATEMENT);
        this.deleteStatement = this.database.compileStatement(DELETE_STATEMENT);
        this.deleteAllRecordsStatement = this.database.compileStatement(DELETE_ALL_RECORD_STATEMENT);
    }

    public Record loadRecord(String key, CacheHeaders cacheHeaders) {
        Record record = (Record) selectRecordForKey(key).orNull();
        if (cacheHeaders.hasHeader("evict-after-read") && record != null) {
            deleteRecord(key);
        }
        return record;
    }

    public Set<String> merge(Record apolloRecord, CacheHeaders cacheHeaders) {
        if (cacheHeaders.hasHeader("do-not-store")) {
            return Collections.emptySet();
        }
        Optional<Record> optionalOldRecord = selectRecordForKey(apolloRecord.key());
        if (!optionalOldRecord.isPresent()) {
            createRecord(apolloRecord.key(), recordAdapter().toJson(apolloRecord.fields()));
            return Collections.emptySet();
        }
        Record oldRecord = (Record) optionalOldRecord.get();
        Set<String> changedKeys = oldRecord.mergeWith(apolloRecord);
        if (changedKeys.isEmpty()) {
            return changedKeys;
        }
        updateRecord(oldRecord.key(), recordAdapter().toJson(oldRecord.fields()));
        return changedKeys;
    }

    public Set<String> merge(Collection<Record> recordSet, CacheHeaders cacheHeaders) {
        if (cacheHeaders.hasHeader("do-not-store")) {
            return Collections.emptySet();
        }
        Set emptySet = Collections.emptySet();
        try {
            this.database.beginTransaction();
            Set<String> changedKeys = super.merge(recordSet, cacheHeaders);
            this.database.setTransactionSuccessful();
            return changedKeys;
        } finally {
            this.database.endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    public long createRecord(String key, String fields) {
        this.insertStatement.bindString(1, key);
        this.insertStatement.bindString(2, fields);
        return this.insertStatement.executeInsert();
    }

    /* access modifiers changed from: 0000 */
    public void updateRecord(String key, String fields) {
        this.updateStatement.bindString(1, key);
        this.updateStatement.bindString(2, fields);
        this.updateStatement.bindString(3, key);
        this.updateStatement.executeInsert();
    }

    /* access modifiers changed from: 0000 */
    public void deleteRecord(String key) {
        this.deleteStatement.bindString(1, key);
        this.deleteStatement.executeUpdateDelete();
    }

    /* access modifiers changed from: 0000 */
    public Optional<Record> selectRecordForKey(String key) {
        Cursor cursor = this.database.query("records", this.allColumns, "key = ?", new String[]{key}, null, null, null);
        if (cursor == null || !cursor.moveToFirst()) {
            return Optional.absent();
        }
        try {
            return Optional.m1700of(cursorToRecord(cursor));
        } catch (IOException e) {
            return Optional.absent();
        } finally {
            cursor.close();
        }
    }

    /* access modifiers changed from: 0000 */
    public Record cursorToRecord(Cursor cursor) throws IOException {
        return new Record(cursor.getString(1), recordAdapter().from(cursor.getString(2)));
    }
}
