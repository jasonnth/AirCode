package com.airbnb.android.core.messaging.p007db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.core.models.InboxType;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel */
public interface SyncDataModel {
    public static final String CREATE_TABLE = "CREATE TABLE sync_store (\n  inboxType TEXT NOT NULL PRIMARY KEY,\n  syncSequenceId INTEGER NOT NULL,\n  unreadCount INTEGER NOT NULL\n)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS sync_store";
    public static final String INBOXTYPE = "inboxType";
    public static final String SELECT_SYNC_BY_INBOX = "SELECT syncSequenceId\nFROM sync_store\nWHERE inboxType = ?";
    public static final String SELECT_UNREAD_BY_INBOX = "SELECT unreadCount\nFROM sync_store\nWHERE inboxType = ?";
    public static final String SYNCSEQUENCEID = "syncSequenceId";
    public static final String TABLE_NAME = "sync_store";
    public static final String UNREADCOUNT = "unreadCount";

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Factory */
    public static final class Factory<T extends SyncDataModel> {
        public final Creator<T> creator;
        public final ColumnAdapter<InboxType, String> inboxTypeAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<InboxType, String> inboxTypeAdapter2) {
            this.creator = creator2;
            this.inboxTypeAdapter = inboxTypeAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.inboxTypeAdapter);
        }

        @Deprecated
        public Marshal marshal(SyncDataModel copy) {
            return new Marshal(copy, this.inboxTypeAdapter);
        }

        public SqlDelightStatement select_sync_by_inbox(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT syncSequenceId\nFROM sync_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SyncDataModel.TABLE_NAME));
        }

        public SqlDelightStatement select_unread_by_inbox(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT unreadCount\nFROM sync_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SyncDataModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement decrement_unread_by_inbox(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("UPDATE sync_store\nSET unreadCount = unreadCount - 1\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            query.append("\n  AND unreadCount > 0");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SyncDataModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_for_inbox(InboxType inboxType) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM sync_store\nWHERE inboxType = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add((String) this.inboxTypeAdapter.encode(inboxType));
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SyncDataModel.TABLE_NAME));
        }

        public RowMapper<Long> select_sync_by_inboxMapper() {
            return new RowMapper<Long>() {
                public Long map(Cursor cursor) {
                    return Long.valueOf(cursor.getLong(0));
                }
            };
        }

        public RowMapper<Long> select_unread_by_inboxMapper() {
            return new RowMapper<Long>() {
                public Long map(Cursor cursor) {
                    return Long.valueOf(cursor.getLong(0));
                }
            };
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Creator */
    public interface Creator<T extends SyncDataModel> {
        T create(InboxType inboxType, long j, long j2);
    }

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Decrement_unread_by_inbox */
    public static final class Decrement_unread_by_inbox extends Update {
        private final Factory<? extends SyncDataModel> syncDataModelFactory;

        public Decrement_unread_by_inbox(SQLiteDatabase database, Factory<? extends SyncDataModel> syncDataModelFactory2) {
            super(SyncDataModel.TABLE_NAME, database.compileStatement("UPDATE sync_store\nSET unreadCount = unreadCount - 1\nWHERE inboxType = ?\n  AND unreadCount > 0"));
            this.syncDataModelFactory = syncDataModelFactory2;
        }

        public void bind(InboxType inboxType) {
            this.program.bindString(1, (String) this.syncDataModelFactory.inboxTypeAdapter.encode(inboxType));
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Delete_for_inbox */
    public static final class Delete_for_inbox extends Delete {
        private final Factory<? extends SyncDataModel> syncDataModelFactory;

        public Delete_for_inbox(SQLiteDatabase database, Factory<? extends SyncDataModel> syncDataModelFactory2) {
            super(SyncDataModel.TABLE_NAME, database.compileStatement("DELETE FROM sync_store\nWHERE inboxType = ?"));
            this.syncDataModelFactory = syncDataModelFactory2;
        }

        public void bind(InboxType inboxType) {
            this.program.bindString(1, (String) this.syncDataModelFactory.inboxTypeAdapter.encode(inboxType));
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Mapper */
    public static final class Mapper<T extends SyncDataModel> implements RowMapper<T> {
        private final Factory<T> syncDataModelFactory;

        public Mapper(Factory<T> syncDataModelFactory2) {
            this.syncDataModelFactory = syncDataModelFactory2;
        }

        public T map(Cursor cursor) {
            return this.syncDataModelFactory.creator.create((InboxType) this.syncDataModelFactory.inboxTypeAdapter.decode(cursor.getString(0)), cursor.getLong(1), cursor.getLong(2));
        }
    }

    /* renamed from: com.airbnb.android.core.messaging.db.SyncDataModel$Marshal */
    public static final class Marshal {
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<InboxType, String> inboxTypeAdapter;

        Marshal(SyncDataModel copy, ColumnAdapter<InboxType, String> inboxTypeAdapter2) {
            this.inboxTypeAdapter = inboxTypeAdapter2;
            if (copy != null) {
                inboxType(copy.inboxType());
                syncSequenceId(copy.syncSequenceId());
                unreadCount(copy.unreadCount());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal inboxType(InboxType inboxType) {
            this.contentValues.put("inboxType", (String) this.inboxTypeAdapter.encode(inboxType));
            return this;
        }

        public Marshal syncSequenceId(long syncSequenceId) {
            this.contentValues.put(SyncDataModel.SYNCSEQUENCEID, Long.valueOf(syncSequenceId));
            return this;
        }

        public Marshal unreadCount(long unreadCount) {
            this.contentValues.put(SyncDataModel.UNREADCOUNT, Long.valueOf(unreadCount));
            return this;
        }
    }

    InboxType inboxType();

    long syncSequenceId();

    long unreadCount();
}
