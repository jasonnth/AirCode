package com.airbnb.android.itinerary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Insert;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface HomeReservationModel {
    public static final String CREATE_TABLE = "CREATE TABLE home_reservations (\n    id TEXT NOT NULL PRIMARY KEY,\n    reservation TEXT\n)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS home_reservations";

    /* renamed from: ID */
    public static final String f8211ID = "id";
    public static final String RESERVATION = "reservation";
    public static final String SELECT_ALL = "SELECT *\nFROM home_reservations";
    public static final String SELECT_HOME_RESERVATION_BY_ID = "SELECT *\nFROM home_reservations\nWHERE id = ?";
    public static final String TABLE_NAME = "home_reservations";

    public static final class Factory<T extends HomeReservationModel> {
        public final Creator<T> creator;

        public Factory(Creator<T> creator2) {
            this.creator = creator2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null);
        }

        @Deprecated
        public Marshal marshal(HomeReservationModel copy) {
            return new Marshal(copy);
        }

        public SqlDelightStatement select_home_reservation_by_id(String id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM home_reservations\nWHERE id = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(HomeReservationModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_home_reservation_by_id(String id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM home_reservations\nWHERE id = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(HomeReservationModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement insert_home_reservation(String id, String reservation) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("INSERT OR REPLACE INTO home_reservations (\n  id,\n  reservation)\nVALUES (");
            int currentIndex = 1 + 1;
            query.append('?').append(1);
            args.add(id);
            query.append(", ");
            if (reservation == null) {
                query.append("null");
                int i = currentIndex;
            } else {
                int i2 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(reservation);
            }
            query.append(")");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(HomeReservationModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_home_reservation(String reservation, String id) {
            List<String> args = new ArrayList<>();
            int currentIndex = 1;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE home_reservations\nSET reservation = ");
            if (reservation == null) {
                query.append("null");
            } else {
                int currentIndex2 = 1 + 1;
                query.append('?').append(1);
                args.add(reservation);
                currentIndex = currentIndex2;
            }
            query.append("\nWHERE id = ");
            int i = currentIndex + 1;
            query.append('?').append(currentIndex);
            args.add(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(HomeReservationModel.TABLE_NAME));
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_home_reservation_by_idMapper() {
            return new Mapper<>(this);
        }
    }

    public interface Creator<T extends HomeReservationModel> {
        T create(String str, String str2);
    }

    public static final class Delete_home_reservation_by_id extends Delete {
        public Delete_home_reservation_by_id(SQLiteDatabase database) {
            super(HomeReservationModel.TABLE_NAME, database.compileStatement("DELETE FROM home_reservations\nWHERE id = ?"));
        }

        public void bind(String id) {
            this.program.bindString(1, id);
        }
    }

    public static final class Insert_home_reservation extends Insert {
        public Insert_home_reservation(SQLiteDatabase database) {
            super(HomeReservationModel.TABLE_NAME, database.compileStatement("INSERT OR REPLACE INTO home_reservations (\n  id,\n  reservation)\nVALUES (?, ?)"));
        }

        public void bind(String id, String reservation) {
            this.program.bindString(1, id);
            if (reservation == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, reservation);
            }
        }
    }

    public static final class Mapper<T extends HomeReservationModel> implements RowMapper<T> {
        private final Factory<T> homeReservationModelFactory;

        public Mapper(Factory<T> homeReservationModelFactory2) {
            this.homeReservationModelFactory = homeReservationModelFactory2;
        }

        public T map(Cursor cursor) {
            return this.homeReservationModelFactory.creator.create(cursor.getString(0), cursor.isNull(1) ? null : cursor.getString(1));
        }
    }

    public static final class Marshal {
        protected final ContentValues contentValues = new ContentValues();

        Marshal(HomeReservationModel copy) {
            if (copy != null) {
                mo56972id(copy.mo10305id());
                reservation(copy.reservation());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        /* renamed from: id */
        public Marshal mo56972id(String id) {
            this.contentValues.put("id", id);
            return this;
        }

        public Marshal reservation(String reservation) {
            this.contentValues.put("reservation", reservation);
            return this;
        }
    }

    public static final class Update_home_reservation extends Update {
        public Update_home_reservation(SQLiteDatabase database) {
            super(HomeReservationModel.TABLE_NAME, database.compileStatement("UPDATE home_reservations\nSET reservation = ?\nWHERE id = ?"));
        }

        public void bind(String reservation, String id) {
            if (reservation == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, reservation);
            }
            this.program.bindString(2, id);
        }
    }

    /* renamed from: id */
    String mo10305id();

    String reservation();
}
