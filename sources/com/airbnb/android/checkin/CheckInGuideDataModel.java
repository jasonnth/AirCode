package com.airbnb.android.checkin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CheckInGuide;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Insert;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface CheckInGuideDataModel {
    public static final String CHECK_IN_GUIDE = "check_in_guide";
    public static final String CREATE_TABLE = "CREATE TABLE check_in_guides (\n  listing_id INTEGER NOT NULL PRIMARY KEY,\n  updated_at TEXT,\n  check_in_guide BLOB NOT NULL\n)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS check_in_guides";
    public static final String LISTING_ID = "listing_id";
    public static final String SELECT_ALL = "SELECT *\nFROM check_in_guides";
    public static final String SELECT_GUIDE_BY_ID = "SELECT *\nFROM check_in_guides\nWHERE listing_id = ?";
    public static final String TABLE_NAME = "check_in_guides";
    public static final String UPDATED_AT = "updated_at";

    public static final class Factory<T extends CheckInGuideDataModel> {
        public final ColumnAdapter<CheckInGuide, byte[]> check_in_guideAdapter;
        public final Creator<T> creator;
        public final ColumnAdapter<AirDateTime, String> updated_atAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<AirDateTime, String> updated_atAdapter2, ColumnAdapter<CheckInGuide, byte[]> check_in_guideAdapter2) {
            this.creator = creator2;
            this.updated_atAdapter = updated_atAdapter2;
            this.check_in_guideAdapter = check_in_guideAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.updated_atAdapter, this.check_in_guideAdapter);
        }

        @Deprecated
        public Marshal marshal(CheckInGuideDataModel copy) {
            return new Marshal(copy, this.updated_atAdapter, this.check_in_guideAdapter);
        }

        @Deprecated
        public SqlDelightStatement insert_guide(long listing_id, AirDateTime updated_at, CheckInGuide check_in_guide) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("INSERT OR REPLACE INTO check_in_guides (listing_id, updated_at, check_in_guide)\nVALUES (");
            query.append(listing_id);
            query.append(", ");
            if (updated_at == null) {
                query.append("null");
            } else {
                int currentIndex = 1 + 1;
                query.append('?').append(1);
                args.add((String) this.updated_atAdapter.encode(updated_at));
                int i = currentIndex;
            }
            query.append(", ");
            query.append(this.check_in_guideAdapter.encode(check_in_guide));
            query.append(")");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(CheckInGuideDataModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_guide(AirDateTime updated_at, CheckInGuide check_in_guide, long listing_id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("UPDATE check_in_guides\nSET updated_at = ");
            if (updated_at == null) {
                query.append("null");
            } else {
                int currentIndex = 1 + 1;
                query.append('?').append(1);
                args.add((String) this.updated_atAdapter.encode(updated_at));
                int i = currentIndex;
            }
            query.append(",\n    check_in_guide = ");
            query.append(this.check_in_guideAdapter.encode(check_in_guide));
            query.append("\nWHERE listing_id = ");
            query.append(listing_id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(CheckInGuideDataModel.TABLE_NAME));
        }

        public SqlDelightStatement select_guide_by_id(long listing_id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM check_in_guides\nWHERE listing_id = ");
            query.append(listing_id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(CheckInGuideDataModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_guide_by_id(long listing_id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM check_in_guides\nWHERE listing_id = ");
            query.append(listing_id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(CheckInGuideDataModel.TABLE_NAME));
        }

        public Mapper<T> select_guide_by_idMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }
    }

    public interface Creator<T extends CheckInGuideDataModel> {
        T create(long j, AirDateTime airDateTime, CheckInGuide checkInGuide);
    }

    public static final class Delete_guide_by_id extends Delete {
        public Delete_guide_by_id(SQLiteDatabase database) {
            super(CheckInGuideDataModel.TABLE_NAME, database.compileStatement("DELETE FROM check_in_guides\nWHERE listing_id = ?"));
        }

        public void bind(long listing_id) {
            this.program.bindLong(1, listing_id);
        }
    }

    public static final class Insert_guide extends Insert {
        private final Factory<? extends CheckInGuideDataModel> checkInGuideDataModelFactory;

        public Insert_guide(SQLiteDatabase database, Factory<? extends CheckInGuideDataModel> checkInGuideDataModelFactory2) {
            super(CheckInGuideDataModel.TABLE_NAME, database.compileStatement("INSERT OR REPLACE INTO check_in_guides (listing_id, updated_at, check_in_guide)\nVALUES (?, ?, ?)"));
            this.checkInGuideDataModelFactory = checkInGuideDataModelFactory2;
        }

        public void bind(long listing_id, AirDateTime updated_at, CheckInGuide check_in_guide) {
            this.program.bindLong(1, listing_id);
            if (updated_at == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, (String) this.checkInGuideDataModelFactory.updated_atAdapter.encode(updated_at));
            }
            this.program.bindBlob(3, (byte[]) this.checkInGuideDataModelFactory.check_in_guideAdapter.encode(check_in_guide));
        }
    }

    public static final class Mapper<T extends CheckInGuideDataModel> implements RowMapper<T> {
        private final Factory<T> checkInGuideDataModelFactory;

        public Mapper(Factory<T> checkInGuideDataModelFactory2) {
            this.checkInGuideDataModelFactory = checkInGuideDataModelFactory2;
        }

        public T map(Cursor cursor) {
            return this.checkInGuideDataModelFactory.creator.create(cursor.getLong(0), cursor.isNull(1) ? null : (AirDateTime) this.checkInGuideDataModelFactory.updated_atAdapter.decode(cursor.getString(1)), (CheckInGuide) this.checkInGuideDataModelFactory.check_in_guideAdapter.decode(cursor.getBlob(2)));
        }
    }

    public static final class Marshal {
        private final ColumnAdapter<CheckInGuide, byte[]> check_in_guideAdapter;
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<AirDateTime, String> updated_atAdapter;

        Marshal(CheckInGuideDataModel copy, ColumnAdapter<AirDateTime, String> updated_atAdapter2, ColumnAdapter<CheckInGuide, byte[]> check_in_guideAdapter2) {
            this.updated_atAdapter = updated_atAdapter2;
            this.check_in_guideAdapter = check_in_guideAdapter2;
            if (copy != null) {
                listing_id(copy.listing_id());
                updated_at(copy.updated_at());
                check_in_guide(copy.check_in_guide());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal listing_id(long listing_id) {
            this.contentValues.put("listing_id", Long.valueOf(listing_id));
            return this;
        }

        public Marshal updated_at(AirDateTime updated_at) {
            if (updated_at != null) {
                this.contentValues.put(CheckInGuideDataModel.UPDATED_AT, (String) this.updated_atAdapter.encode(updated_at));
            } else {
                this.contentValues.putNull(CheckInGuideDataModel.UPDATED_AT);
            }
            return this;
        }

        public Marshal check_in_guide(CheckInGuide check_in_guide) {
            this.contentValues.put(CheckInGuideDataModel.CHECK_IN_GUIDE, (byte[]) this.check_in_guideAdapter.encode(check_in_guide));
            return this;
        }
    }

    public static final class Update_guide extends Update {
        private final Factory<? extends CheckInGuideDataModel> checkInGuideDataModelFactory;

        public Update_guide(SQLiteDatabase database, Factory<? extends CheckInGuideDataModel> checkInGuideDataModelFactory2) {
            super(CheckInGuideDataModel.TABLE_NAME, database.compileStatement("UPDATE check_in_guides\nSET updated_at = ?,\n    check_in_guide = ?\nWHERE listing_id = ?"));
            this.checkInGuideDataModelFactory = checkInGuideDataModelFactory2;
        }

        public void bind(AirDateTime updated_at, CheckInGuide check_in_guide, long listing_id) {
            if (updated_at == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, (String) this.checkInGuideDataModelFactory.updated_atAdapter.encode(updated_at));
            }
            this.program.bindBlob(2, (byte[]) this.checkInGuideDataModelFactory.check_in_guideAdapter.encode(check_in_guide));
            this.program.bindLong(3, listing_id);
        }
    }

    CheckInGuide check_in_guide();

    long listing_id();

    AirDateTime updated_at();
}
