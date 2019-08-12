package com.squareup.sqlbrite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import com.squareup.sqlbrite.SqlBrite.Logger;
import java.io.Closeable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import p032rx.Observable.Transformer;
import p032rx.Scheduler;
import p032rx.functions.Action0;
import p032rx.subjects.PublishSubject;

public final class BriteDatabase implements Closeable {
    private final Action0 ensureNotInTransaction = new Action0() {
        public void call() {
            if (BriteDatabase.this.transactions.get() != null) {
                throw new IllegalStateException("Cannot subscribe to observable query in a transaction.");
            }
        }
    };
    private final SQLiteOpenHelper helper;
    private final Logger logger;
    volatile boolean logging;
    private final Transformer<Object, Object> queryTransformer;
    private final Scheduler scheduler;
    private final Transaction transaction = new Transaction() {
        public void markSuccessful() {
            if (BriteDatabase.this.logging) {
                BriteDatabase.this.log("TXN SUCCESS %s", BriteDatabase.this.transactions.get());
            }
            BriteDatabase.this.getWritableDatabase().setTransactionSuccessful();
        }

        public void end() {
            SqliteTransaction transaction = (SqliteTransaction) BriteDatabase.this.transactions.get();
            if (transaction == null) {
                throw new IllegalStateException("Not in transaction.");
            }
            BriteDatabase.this.transactions.set(transaction.parent);
            if (BriteDatabase.this.logging) {
                BriteDatabase.this.log("TXN END %s", transaction);
            }
            BriteDatabase.this.getWritableDatabase().endTransaction();
            if (transaction.commit) {
                BriteDatabase.this.sendTableTrigger(transaction);
            }
        }

        public void close() {
            end();
        }
    };
    final ThreadLocal<SqliteTransaction> transactions = new ThreadLocal<>();
    private final PublishSubject<Set<String>> triggers = PublishSubject.create();

    static final class SqliteTransaction extends LinkedHashSet<String> implements SQLiteTransactionListener {
        boolean commit;
        final SqliteTransaction parent;

        SqliteTransaction(SqliteTransaction parent2) {
            this.parent = parent2;
        }

        public void onBegin() {
        }

        public void onCommit() {
            this.commit = true;
        }

        public void onRollback() {
        }

        public String toString() {
            String name = String.format("%08x", new Object[]{Integer.valueOf(System.identityHashCode(this))});
            return this.parent == null ? name : name + " [" + this.parent.toString() + ']';
        }
    }

    public interface Transaction extends Closeable {
        void end();

        void markSuccessful();
    }

    BriteDatabase(SQLiteOpenHelper helper2, Logger logger2, Scheduler scheduler2, Transformer<Object, Object> queryTransformer2) {
        this.helper = helper2;
        this.logger = logger2;
        this.scheduler = scheduler2;
        this.queryTransformer = queryTransformer2;
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.helper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return this.helper.getWritableDatabase();
    }

    /* access modifiers changed from: 0000 */
    public void sendTableTrigger(Set<String> tables) {
        SqliteTransaction transaction2 = (SqliteTransaction) this.transactions.get();
        if (transaction2 != null) {
            transaction2.addAll(tables);
            return;
        }
        if (this.logging) {
            log("TRIGGER %s", tables);
        }
        this.triggers.onNext(tables);
    }

    public Transaction newTransaction() {
        SqliteTransaction transaction2 = new SqliteTransaction((SqliteTransaction) this.transactions.get());
        this.transactions.set(transaction2);
        if (this.logging) {
            log("TXN BEGIN %s", transaction2);
        }
        getWritableDatabase().beginTransactionWithListener(transaction2);
        return this.transaction;
    }

    public void close() {
        this.helper.close();
    }

    public Cursor query(String sql, String... args) {
        long startNanos = System.nanoTime();
        Cursor cursor = getReadableDatabase().rawQuery(sql, args);
        long tookMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
        if (this.logging) {
            log("QUERY (%sms)\n  sql: %s\n  args: %s", Long.valueOf(tookMillis), indentSql(sql), Arrays.toString(args));
        }
        return cursor;
    }

    public long insert(String table, ContentValues values) {
        return insert(table, values, 0);
    }

    public long insert(String table, ContentValues values, int conflictAlgorithm) {
        SQLiteDatabase db = getWritableDatabase();
        if (this.logging) {
            log("INSERT\n  table: %s\n  values: %s\n  conflictAlgorithm: %s", table, values, conflictString(conflictAlgorithm));
        }
        long rowId = db.insertWithOnConflict(table, null, values, conflictAlgorithm);
        if (this.logging) {
            log("INSERT id: %s", Long.valueOf(rowId));
        }
        if (rowId != -1) {
            sendTableTrigger(Collections.singleton(table));
        }
        return rowId;
    }

    public int delete(String table, String whereClause, String... whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        if (this.logging) {
            log("DELETE\n  table: %s\n  whereClause: %s\n  whereArgs: %s", table, whereClause, Arrays.toString(whereArgs));
        }
        int rows = db.delete(table, whereClause, whereArgs);
        if (this.logging) {
            String str = "DELETE affected %s %s";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(rows);
            objArr[1] = rows != 1 ? "rows" : "row";
            log(str, objArr);
        }
        if (rows > 0) {
            sendTableTrigger(Collections.singleton(table));
        }
        return rows;
    }

    public void execute(String sql) {
        if (this.logging) {
            log("EXECUTE\n  sql: %s", sql);
        }
        getWritableDatabase().execSQL(sql);
    }

    public void execute(String sql, Object... args) {
        if (this.logging) {
            log("EXECUTE\n  sql: %s\n  args: %s", sql, Arrays.toString(args));
        }
        getWritableDatabase().execSQL(sql, args);
    }

    static String indentSql(String sql) {
        return sql.replace("\n", "\n       ");
    }

    /* access modifiers changed from: 0000 */
    public void log(String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        this.logger.log(message);
    }

    private static String conflictString(int conflictAlgorithm) {
        switch (conflictAlgorithm) {
            case 0:
                return "none";
            case 1:
                return "rollback";
            case 2:
                return "abort";
            case 3:
                return "fail";
            case 4:
                return "ignore";
            case 5:
                return "replace";
            default:
                return "unknown (" + conflictAlgorithm + ')';
        }
    }
}
