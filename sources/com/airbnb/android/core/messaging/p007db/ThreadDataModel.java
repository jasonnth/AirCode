package com.airbnb.android.core.messaging.p007db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel */
public interface ThreadDataModel {
    public static final String CREATE_TABLE = "CREATE TABLE threads_store (\n  threadId INTEGER NOT NULL PRIMARY KEY,\n  inboxType TEXT NOT NULL,\n  threadModel BLOB NOT NULL,\n  lastMessageAt INTEGER NOT NULL,\n  isInThreadlist INTEGER NOT NULL,\n  doesContainAllInfo INTEGER NOT NULL\n)";
    public static final String DOESCONTAINALLINFO = "doesContainAllInfo";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS threads_store";
    public static final String GET_OLDEST_THREAD = "SELECT *\nFROM threads_store\nWHERE inboxType = ?\n  AND isInThreadlist = 1\nORDER BY lastMessageAt ASC, threadId ASC\nLIMIT 1";
    public static final String INBOX = "SELECT *\nFROM threads_store\nWHERE inboxType = ?\n  AND isInThreadlist = 1\nORDER BY lastMessageAt DESC, threadId DESC\nLIMIT ?";
    public static final String INBOXTYPE = "inboxType";
    public static final String INBOX_WITH_OFFSET = "SELECT *\nFROM threads_store\nWHERE inboxType = ?\n  AND isInThreadlist = 1\n  AND (lastMessageAt < ? OR (lastMessageAt == ? AND threadId < ?))\nORDER BY lastMessageAt DESC, threadId DESC\nLIMIT ?";
    public static final String ISINTHREADLIST = "isInThreadlist";
    public static final String LASTMESSAGEAT = "lastMessageAt";
    public static final String SELECT_THREAD_BY_ID = "SELECT *\nFROM threads_store\nWHERE threadId = ?";
    public static final String TABLE_NAME = "threads_store";
    public static final String THREADID = "threadId";
    public static final String THREADMODEL = "threadModel";

    /* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel$Factory */
    public static final class Factory<T extends ThreadDataModel> {
        public final Creator<T> creator;
        public final ColumnAdapter<InboxType, String> inboxTypeAdapter;
        public final ColumnAdapter<Thread, byte[]> threadModelAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<InboxType, String> inboxTypeAdapter2, ColumnAdapter<Thread, byte[]> threadModelAdapter2) {
            this.creator = creator2;
            this.inboxTypeAdapter = inboxTypeAdapter2;
            this.threadModelAdapter = threadModelAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.inboxTypeAdapter, this.threadModelAdapter);
        }

        @Deprecated
        public Marshal marshal(ThreadDataModel copy) {
            return new Marshal(copy, this.inboxTypeAdapter, this.threadModelAdapter);
        }

        public SqlDelightStatement select_thread_by_id(long threadId) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM threads_store\nWHERE threadId = ");
            query.append(threadId);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ThreadDataModel.TABLE_NAME));
        }

        public SqlDelightStatement inbox(InboxType inboxType, long arg2) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM threads_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            query.append("\n  AND isInThreadlist = 1\nORDER BY lastMessageAt DESC, threadId DESC\nLIMIT ");
            query.append(arg2);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ThreadDataModel.TABLE_NAME));
        }

        public SqlDelightStatement inbox_with_offset(InboxType inboxType, long lastMessageAt, long lastMessageAt_, long threadId, long arg5) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM threads_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            query.append("\n  AND isInThreadlist = 1\n  AND (lastMessageAt < ");
            query.append(lastMessageAt);
            query.append(" OR (lastMessageAt == ");
            query.append(lastMessageAt_);
            query.append(" AND threadId < ");
            query.append(threadId);
            query.append("))\nORDER BY lastMessageAt DESC, threadId DESC\nLIMIT ");
            query.append(arg5);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ThreadDataModel.TABLE_NAME));
        }

        public SqlDelightStatement get_oldest_thread(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM threads_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            query.append("\n  AND isInThreadlist = 1\nORDER BY lastMessageAt ASC, threadId ASC\nLIMIT 1");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ThreadDataModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_for_inbox(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM threads_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ThreadDataModel.TABLE_NAME));
        }

        public Mapper<T> select_thread_by_idMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> inboxMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> inbox_with_offsetMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> get_oldest_threadMapper() {
            return new Mapper<>(this);
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel$Creator */
    public interface Creator<T extends ThreadDataModel> {
        T create(long j, InboxType inboxType, Thread thread, long j2, boolean z, boolean z2);
    }

    /* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel$Delete_for_inbox */
    public static final class Delete_for_inbox extends Delete {
        private final Factory<? extends ThreadDataModel> threadDataModelFactory;

        public Delete_for_inbox(SQLiteDatabase database, Factory<? extends ThreadDataModel> threadDataModelFactory2) {
            super(ThreadDataModel.TABLE_NAME, database.compileStatement("DELETE FROM threads_store\nWHERE inboxType = ?"));
            this.threadDataModelFactory = threadDataModelFactory2;
        }

        public void bind(InboxType inboxType) {
            this.program.bindString(1, (String) this.threadDataModelFactory.inboxTypeAdapter.encode(inboxType));
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel$Mapper */
    public static final class Mapper<T extends ThreadDataModel> implements RowMapper<T> {
        private final Factory<T> threadDataModelFactory;

        public Mapper(Factory<T> threadDataModelFactory2) {
            this.threadDataModelFactory = threadDataModelFactory2;
        }

        public T map(Cursor cursor) {
            boolean z;
            boolean z2 = false;
            Creator<T> creator = this.threadDataModelFactory.creator;
            long j = cursor.getLong(0);
            InboxType inboxType = (InboxType) this.threadDataModelFactory.inboxTypeAdapter.decode(cursor.getString(1));
            Thread thread = (Thread) this.threadDataModelFactory.threadModelAdapter.decode(cursor.getBlob(2));
            long j2 = cursor.getLong(3);
            if (cursor.getInt(4) == 1) {
                z = true;
            } else {
                z = false;
            }
            if (cursor.getInt(5) == 1) {
                z2 = true;
            }
            return creator.create(j, inboxType, thread, j2, z, z2);
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.ThreadDataModel$Marshal */
    public static final class Marshal {
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<InboxType, String> inboxTypeAdapter;
        private final ColumnAdapter<Thread, byte[]> threadModelAdapter;

        Marshal(ThreadDataModel copy, ColumnAdapter<InboxType, String> inboxTypeAdapter2, ColumnAdapter<Thread, byte[]> threadModelAdapter2) {
            this.inboxTypeAdapter = inboxTypeAdapter2;
            this.threadModelAdapter = threadModelAdapter2;
            if (copy != null) {
                threadId(copy.threadId());
                inboxType(copy.inboxType());
                threadModel(copy.threadModel());
                lastMessageAt(copy.lastMessageAt());
                isInThreadlist(copy.isInThreadlist());
                doesContainAllInfo(copy.doesContainAllInfo());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal threadId(long threadId) {
            this.contentValues.put(ThreadDataModel.THREADID, Long.valueOf(threadId));
            return this;
        }

        public Marshal inboxType(InboxType inboxType) {
            this.contentValues.put("inboxType", (String) this.inboxTypeAdapter.encode(inboxType));
            return this;
        }

        public Marshal threadModel(Thread threadModel) {
            this.contentValues.put(ThreadDataModel.THREADMODEL, (byte[]) this.threadModelAdapter.encode(threadModel));
            return this;
        }

        public Marshal lastMessageAt(long lastMessageAt) {
            this.contentValues.put(ThreadDataModel.LASTMESSAGEAT, Long.valueOf(lastMessageAt));
            return this;
        }

        public Marshal isInThreadlist(boolean isInThreadlist) {
            this.contentValues.put(ThreadDataModel.ISINTHREADLIST, Integer.valueOf(isInThreadlist ? 1 : 0));
            return this;
        }

        public Marshal doesContainAllInfo(boolean doesContainAllInfo) {
            this.contentValues.put(ThreadDataModel.DOESCONTAINALLINFO, Integer.valueOf(doesContainAllInfo ? 1 : 0));
            return this;
        }
    }

    boolean doesContainAllInfo();

    InboxType inboxType();

    boolean isInThreadlist();

    long lastMessageAt();

    long threadId();

    Thread threadModel();
}
