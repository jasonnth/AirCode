package com.airbnb.android.itinerary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.itinerary.data.models.TripEventCardType;
import com.airbnb.android.itinerary.data.models.TripEventSecondaryAction;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Insert;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface TripEventModel {
    public static final String CARD_SUBTITLE = "card_subtitle";
    public static final String CARD_TITLE = "card_title";
    public static final String CARD_TYPE = "card_type";
    public static final String CATEGORY = "category";
    public static final String CONFIRMATION_CODE = "confirmation_code";
    public static final String CREATE_TABLE = "CREATE TABLE trip_events (\n    primary_key TEXT NOT NULL PRIMARY KEY,\n    schedule_confirmation_code TEXT,\n    id INTEGER,\n    card_type TEXT NOT NULL,\n    category TEXT,\n    confirmation_code TEXT,\n    picture TEXT,\n    starts_at TEXT,\n    ends_at TEXT,\n    time_zone TEXT,\n    header TEXT,\n    card_title TEXT,\n    card_subtitle TEXT,\n    secondary_actions BLOB\n)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS trip_events";
    public static final String ENDS_AT = "ends_at";
    public static final String HEADER = "header";

    /* renamed from: ID */
    public static final String f1147ID = "id";
    public static final String PICTURE = "picture";
    public static final String PRIMARY_KEY = "primary_key";
    public static final String SCHEDULE_CONFIRMATION_CODE = "schedule_confirmation_code";
    public static final String SECONDARY_ACTIONS = "secondary_actions";
    public static final String SELECT_ALL = "SELECT *\nFROM trip_events";
    public static final String SELECT_TRIP_EVENT_BY_CARD_TYPE_AND_CONFIRMATION_CODE = "SELECT *\nFROM trip_events\nWHERE schedule_confirmation_code = ? AND card_type = ?\norder by starts_at";
    public static final String SELECT_TRIP_EVENT_BY_ID = "SELECT *\nFROM trip_events\nWHERE id = ?";
    public static final String SELECT_TRIP_EVENT_BY_PRIMARY_KEY = "SELECT *\nFROM trip_events\nWHERE primary_key = ?";
    public static final String SELECT_TRIP_EVENT_BY_TRIP = "SELECT *\nFROM trip_events\nWHERE schedule_confirmation_code = ?\norder by starts_at";
    public static final String STARTS_AT = "starts_at";
    public static final String TABLE_NAME = "trip_events";
    public static final String TIME_ZONE = "time_zone";

    public static final class Factory<T extends TripEventModel> {
        public final ColumnAdapter<TripEventCardType, String> card_typeAdapter;
        public final Creator<T> creator;
        public final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        public final ColumnAdapter<ArrayList<TripEventSecondaryAction>, byte[]> secondary_actionsAdapter;
        public final ColumnAdapter<AirDateTime, String> starts_atAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<TripEventCardType, String> card_typeAdapter2, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<ArrayList<TripEventSecondaryAction>, byte[]> secondary_actionsAdapter2) {
            this.creator = creator2;
            this.card_typeAdapter = card_typeAdapter2;
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.secondary_actionsAdapter = secondary_actionsAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.card_typeAdapter, this.starts_atAdapter, this.ends_atAdapter, this.secondary_actionsAdapter);
        }

        @Deprecated
        public Marshal marshal(TripEventModel copy) {
            return new Marshal(copy, this.card_typeAdapter, this.starts_atAdapter, this.ends_atAdapter, this.secondary_actionsAdapter);
        }

        public SqlDelightStatement select_trip_event_by_primary_key(String primary_key) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM trip_events\nWHERE primary_key = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(primary_key);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        public SqlDelightStatement select_trip_event_by_id(Long id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM trip_events\nWHERE id = ");
            if (id == null) {
                query.append("null");
            } else {
                query.append(id);
            }
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        public SqlDelightStatement select_trip_event_by_trip(String schedule_confirmation_code) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM trip_events\nWHERE schedule_confirmation_code = ");
            if (schedule_confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex = 1 + 1;
                query.append('?').append(1);
                args.add(schedule_confirmation_code);
                int i = currentIndex;
            }
            query.append("\norder by starts_at");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        public SqlDelightStatement select_trip_event_by_card_type_and_confirmation_code(String schedule_confirmation_code, TripEventCardType card_type) {
            List<String> args = new ArrayList<>();
            int currentIndex = 1;
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM trip_events\nWHERE schedule_confirmation_code = ");
            if (schedule_confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex2 = 1 + 1;
                query.append('?').append(1);
                args.add(schedule_confirmation_code);
                currentIndex = currentIndex2;
            }
            query.append(" AND card_type = ");
            int i = currentIndex + 1;
            query.append('?').append(currentIndex);
            args.add((String) this.card_typeAdapter.encode(card_type));
            query.append("\norder by starts_at");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_all_by_trip(String schedule_confirmation_code) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM trip_events\nWHERE schedule_confirmation_code = ");
            if (schedule_confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex = 1 + 1;
                query.append('?').append(1);
                args.add(schedule_confirmation_code);
                int i = currentIndex;
            }
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_trip_event_by_primary_key(String primary_key) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM trip_events\nWHERE primary_key = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(primary_key);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement insert_trip_event(String primary_key, String schedule_confirmation_code, Long id, TripEventCardType card_type, String category, String confirmation_code, String picture, AirDateTime starts_at, AirDateTime ends_at, String time_zone, String header, String card_title, String card_subtitle, ArrayList<TripEventSecondaryAction> secondary_actions) {
            int currentIndex;
            int currentIndex2;
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("INSERT OR REPLACE INTO trip_events (\n  primary_key,\n  schedule_confirmation_code,\n  id,\n  card_type,\n  category,\n  confirmation_code,\n  picture,\n  starts_at,\n  ends_at,\n  time_zone,\n  header,\n  card_title,\n  card_subtitle,\n  secondary_actions)\nVALUES (");
            int currentIndex3 = 1 + 1;
            query.append('?').append(1);
            args.add(primary_key);
            query.append(", ");
            if (schedule_confirmation_code == null) {
                query.append("null");
                currentIndex = currentIndex3;
            } else {
                currentIndex = currentIndex3 + 1;
                query.append('?').append(currentIndex3);
                args.add(schedule_confirmation_code);
            }
            query.append(", ");
            if (id == null) {
                query.append("null");
            } else {
                query.append(id);
            }
            query.append(", ");
            int currentIndex4 = currentIndex + 1;
            query.append('?').append(currentIndex);
            args.add((String) this.card_typeAdapter.encode(card_type));
            query.append(", ");
            if (category == null) {
                query.append("null");
                currentIndex2 = currentIndex4;
            } else {
                currentIndex2 = currentIndex4 + 1;
                query.append('?').append(currentIndex4);
                args.add(category);
            }
            query.append(", ");
            if (confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(confirmation_code);
                currentIndex2 = currentIndex5;
            }
            query.append(", ");
            if (picture == null) {
                query.append("null");
            } else {
                int currentIndex6 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(picture);
                currentIndex2 = currentIndex6;
            }
            query.append(", ");
            if (starts_at == null) {
                query.append("null");
            } else {
                int currentIndex7 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add((String) this.starts_atAdapter.encode(starts_at));
                currentIndex2 = currentIndex7;
            }
            query.append(", ");
            if (ends_at == null) {
                query.append("null");
            } else {
                int currentIndex8 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add((String) this.ends_atAdapter.encode(ends_at));
                currentIndex2 = currentIndex8;
            }
            query.append(", ");
            if (time_zone == null) {
                query.append("null");
            } else {
                int currentIndex9 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(time_zone);
                currentIndex2 = currentIndex9;
            }
            query.append(", ");
            if (header == null) {
                query.append("null");
            } else {
                int currentIndex10 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(header);
                currentIndex2 = currentIndex10;
            }
            query.append(", ");
            if (card_title == null) {
                query.append("null");
            } else {
                int currentIndex11 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(card_title);
                currentIndex2 = currentIndex11;
            }
            query.append(", ");
            if (card_subtitle == null) {
                query.append("null");
            } else {
                int currentIndex12 = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add(card_subtitle);
                int i = currentIndex12;
            }
            query.append(", ");
            if (secondary_actions == null) {
                query.append("null");
            } else {
                query.append(this.secondary_actionsAdapter.encode(secondary_actions));
            }
            query.append(")");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_trip_event(String schedule_confirmation_code, Long id, TripEventCardType card_type, String category, String confirmation_code, String picture, AirDateTime starts_at, AirDateTime ends_at, String time_zone, String header, String card_title, String card_subtitle, ArrayList<TripEventSecondaryAction> secondary_actions, String primary_key) {
            int currentIndex;
            List<String> args = new ArrayList<>();
            int currentIndex2 = 1;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE trip_events\nSET schedule_confirmation_code = ");
            if (schedule_confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex3 = 1 + 1;
                query.append('?').append(1);
                args.add(schedule_confirmation_code);
                currentIndex2 = currentIndex3;
            }
            query.append(",\n    id = ");
            if (id == null) {
                query.append("null");
            } else {
                query.append(id);
            }
            query.append(",\n    card_type = ");
            int currentIndex4 = currentIndex2 + 1;
            query.append('?').append(currentIndex2);
            args.add((String) this.card_typeAdapter.encode(card_type));
            query.append(",\n    category = ");
            if (category == null) {
                query.append("null");
                currentIndex = currentIndex4;
            } else {
                currentIndex = currentIndex4 + 1;
                query.append('?').append(currentIndex4);
                args.add(category);
            }
            query.append(",\n    confirmation_code = ");
            if (confirmation_code == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(confirmation_code);
                currentIndex = currentIndex5;
            }
            query.append(",\n    picture = ");
            if (picture == null) {
                query.append("null");
            } else {
                int currentIndex6 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(picture);
                currentIndex = currentIndex6;
            }
            query.append(",\n    starts_at = ");
            if (starts_at == null) {
                query.append("null");
            } else {
                int currentIndex7 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.starts_atAdapter.encode(starts_at));
                currentIndex = currentIndex7;
            }
            query.append(",\n    ends_at = ");
            if (ends_at == null) {
                query.append("null");
            } else {
                int currentIndex8 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.ends_atAdapter.encode(ends_at));
                currentIndex = currentIndex8;
            }
            query.append(",\n    time_zone = ");
            if (time_zone == null) {
                query.append("null");
            } else {
                int currentIndex9 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(time_zone);
                currentIndex = currentIndex9;
            }
            query.append(",\n    header = ");
            if (header == null) {
                query.append("null");
            } else {
                int currentIndex10 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(header);
                currentIndex = currentIndex10;
            }
            query.append(",\n    card_title = ");
            if (card_title == null) {
                query.append("null");
            } else {
                int currentIndex11 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(card_title);
                currentIndex = currentIndex11;
            }
            query.append(",\n    card_subtitle = ");
            if (card_subtitle == null) {
                query.append("null");
            } else {
                int currentIndex12 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(card_subtitle);
                currentIndex = currentIndex12;
            }
            query.append(",\n    secondary_actions = ");
            if (secondary_actions == null) {
                query.append("null");
            } else {
                query.append(this.secondary_actionsAdapter.encode(secondary_actions));
            }
            query.append("\nWHERE primary_key = ");
            int i = currentIndex + 1;
            query.append('?').append(currentIndex);
            args.add(primary_key);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TripEventModel.TABLE_NAME));
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_trip_event_by_primary_keyMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_trip_event_by_idMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_trip_event_by_tripMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_trip_event_by_card_type_and_confirmation_codeMapper() {
            return new Mapper<>(this);
        }
    }

    public interface Creator<T extends TripEventModel> {
        T create(String str, String str2, Long l, TripEventCardType tripEventCardType, String str3, String str4, String str5, AirDateTime airDateTime, AirDateTime airDateTime2, String str6, String str7, String str8, String str9, ArrayList<TripEventSecondaryAction> arrayList);
    }

    public static final class Delete_all_by_trip extends Delete {
        public Delete_all_by_trip(SQLiteDatabase database) {
            super(TripEventModel.TABLE_NAME, database.compileStatement("DELETE FROM trip_events\nWHERE schedule_confirmation_code = ?"));
        }

        public void bind(String schedule_confirmation_code) {
            if (schedule_confirmation_code == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, schedule_confirmation_code);
            }
        }
    }

    public static final class Delete_trip_event_by_primary_key extends Delete {
        public Delete_trip_event_by_primary_key(SQLiteDatabase database) {
            super(TripEventModel.TABLE_NAME, database.compileStatement("DELETE FROM trip_events\nWHERE primary_key = ?"));
        }

        public void bind(String primary_key) {
            this.program.bindString(1, primary_key);
        }
    }

    public static final class Insert_trip_event extends Insert {
        private final Factory<? extends TripEventModel> tripEventModelFactory;

        public Insert_trip_event(SQLiteDatabase database, Factory<? extends TripEventModel> tripEventModelFactory2) {
            super(TripEventModel.TABLE_NAME, database.compileStatement("INSERT OR REPLACE INTO trip_events (\n  primary_key,\n  schedule_confirmation_code,\n  id,\n  card_type,\n  category,\n  confirmation_code,\n  picture,\n  starts_at,\n  ends_at,\n  time_zone,\n  header,\n  card_title,\n  card_subtitle,\n  secondary_actions)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
            this.tripEventModelFactory = tripEventModelFactory2;
        }

        public void bind(String primary_key, String schedule_confirmation_code, Long id, TripEventCardType card_type, String category, String confirmation_code, String picture, AirDateTime starts_at, AirDateTime ends_at, String time_zone, String header, String card_title, String card_subtitle, ArrayList<TripEventSecondaryAction> secondary_actions) {
            this.program.bindString(1, primary_key);
            if (schedule_confirmation_code == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, schedule_confirmation_code);
            }
            if (id == null) {
                this.program.bindNull(3);
            } else {
                this.program.bindLong(3, id.longValue());
            }
            this.program.bindString(4, (String) this.tripEventModelFactory.card_typeAdapter.encode(card_type));
            if (category == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindString(5, category);
            }
            if (confirmation_code == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindString(6, confirmation_code);
            }
            if (picture == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, picture);
            }
            if (starts_at == null) {
                this.program.bindNull(8);
            } else {
                this.program.bindString(8, (String) this.tripEventModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(9);
            } else {
                this.program.bindString(9, (String) this.tripEventModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (time_zone == null) {
                this.program.bindNull(10);
            } else {
                this.program.bindString(10, time_zone);
            }
            if (header == null) {
                this.program.bindNull(11);
            } else {
                this.program.bindString(11, header);
            }
            if (card_title == null) {
                this.program.bindNull(12);
            } else {
                this.program.bindString(12, card_title);
            }
            if (card_subtitle == null) {
                this.program.bindNull(13);
            } else {
                this.program.bindString(13, card_subtitle);
            }
            if (secondary_actions == null) {
                this.program.bindNull(14);
            } else {
                this.program.bindBlob(14, (byte[]) this.tripEventModelFactory.secondary_actionsAdapter.encode(secondary_actions));
            }
        }
    }

    public static final class Mapper<T extends TripEventModel> implements RowMapper<T> {
        private final Factory<T> tripEventModelFactory;

        public Mapper(Factory<T> tripEventModelFactory2) {
            this.tripEventModelFactory = tripEventModelFactory2;
        }

        public T map(Cursor cursor) {
            ArrayList arrayList;
            Creator<T> creator = this.tripEventModelFactory.creator;
            String string = cursor.getString(0);
            String string2 = cursor.isNull(1) ? null : cursor.getString(1);
            Long valueOf = cursor.isNull(2) ? null : Long.valueOf(cursor.getLong(2));
            TripEventCardType tripEventCardType = (TripEventCardType) this.tripEventModelFactory.card_typeAdapter.decode(cursor.getString(3));
            String string3 = cursor.isNull(4) ? null : cursor.getString(4);
            String string4 = cursor.isNull(5) ? null : cursor.getString(5);
            String string5 = cursor.isNull(6) ? null : cursor.getString(6);
            AirDateTime airDateTime = cursor.isNull(7) ? null : (AirDateTime) this.tripEventModelFactory.starts_atAdapter.decode(cursor.getString(7));
            AirDateTime airDateTime2 = cursor.isNull(8) ? null : (AirDateTime) this.tripEventModelFactory.ends_atAdapter.decode(cursor.getString(8));
            String string6 = cursor.isNull(9) ? null : cursor.getString(9);
            String string7 = cursor.isNull(10) ? null : cursor.getString(10);
            String string8 = cursor.isNull(11) ? null : cursor.getString(11);
            String string9 = cursor.isNull(12) ? null : cursor.getString(12);
            if (cursor.isNull(13)) {
                arrayList = null;
            } else {
                arrayList = (ArrayList) this.tripEventModelFactory.secondary_actionsAdapter.decode(cursor.getBlob(13));
            }
            return creator.create(string, string2, valueOf, tripEventCardType, string3, string4, string5, airDateTime, airDateTime2, string6, string7, string8, string9, arrayList);
        }
    }

    public static final class Marshal {
        private final ColumnAdapter<TripEventCardType, String> card_typeAdapter;
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        private final ColumnAdapter<ArrayList<TripEventSecondaryAction>, byte[]> secondary_actionsAdapter;
        private final ColumnAdapter<AirDateTime, String> starts_atAdapter;

        Marshal(TripEventModel copy, ColumnAdapter<TripEventCardType, String> card_typeAdapter2, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<ArrayList<TripEventSecondaryAction>, byte[]> secondary_actionsAdapter2) {
            this.card_typeAdapter = card_typeAdapter2;
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.secondary_actionsAdapter = secondary_actionsAdapter2;
            if (copy != null) {
                primary_key(copy.primary_key());
                schedule_confirmation_code(copy.schedule_confirmation_code());
                mo10279id(copy.mo10245id());
                card_type(copy.card_type());
                category(copy.category());
                confirmation_code(copy.confirmation_code());
                picture(copy.picture());
                starts_at(copy.starts_at());
                ends_at(copy.ends_at());
                time_zone(copy.time_zone());
                header(copy.header());
                card_title(copy.card_title());
                card_subtitle(copy.card_subtitle());
                secondary_actions(copy.secondary_actions());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal primary_key(String primary_key) {
            this.contentValues.put(TripEventModel.PRIMARY_KEY, primary_key);
            return this;
        }

        public Marshal schedule_confirmation_code(String schedule_confirmation_code) {
            this.contentValues.put(TripEventModel.SCHEDULE_CONFIRMATION_CODE, schedule_confirmation_code);
            return this;
        }

        /* renamed from: id */
        public Marshal mo10279id(Long id) {
            this.contentValues.put("id", id);
            return this;
        }

        public Marshal card_type(TripEventCardType card_type) {
            this.contentValues.put(TripEventModel.CARD_TYPE, (String) this.card_typeAdapter.encode(card_type));
            return this;
        }

        public Marshal category(String category) {
            this.contentValues.put(TripEventModel.CATEGORY, category);
            return this;
        }

        public Marshal confirmation_code(String confirmation_code) {
            this.contentValues.put("confirmation_code", confirmation_code);
            return this;
        }

        public Marshal picture(String picture) {
            this.contentValues.put("picture", picture);
            return this;
        }

        public Marshal starts_at(AirDateTime starts_at) {
            if (starts_at != null) {
                this.contentValues.put("starts_at", (String) this.starts_atAdapter.encode(starts_at));
            } else {
                this.contentValues.putNull("starts_at");
            }
            return this;
        }

        public Marshal ends_at(AirDateTime ends_at) {
            if (ends_at != null) {
                this.contentValues.put("ends_at", (String) this.ends_atAdapter.encode(ends_at));
            } else {
                this.contentValues.putNull("ends_at");
            }
            return this;
        }

        public Marshal time_zone(String time_zone) {
            this.contentValues.put("time_zone", time_zone);
            return this;
        }

        public Marshal header(String header) {
            this.contentValues.put(TripEventModel.HEADER, header);
            return this;
        }

        public Marshal card_title(String card_title) {
            this.contentValues.put(TripEventModel.CARD_TITLE, card_title);
            return this;
        }

        public Marshal card_subtitle(String card_subtitle) {
            this.contentValues.put(TripEventModel.CARD_SUBTITLE, card_subtitle);
            return this;
        }

        public Marshal secondary_actions(ArrayList<TripEventSecondaryAction> secondary_actions) {
            if (secondary_actions != null) {
                this.contentValues.put(TripEventModel.SECONDARY_ACTIONS, (byte[]) this.secondary_actionsAdapter.encode(secondary_actions));
            } else {
                this.contentValues.putNull(TripEventModel.SECONDARY_ACTIONS);
            }
            return this;
        }
    }

    public static final class Update_trip_event extends Update {
        private final Factory<? extends TripEventModel> tripEventModelFactory;

        public Update_trip_event(SQLiteDatabase database, Factory<? extends TripEventModel> tripEventModelFactory2) {
            super(TripEventModel.TABLE_NAME, database.compileStatement("UPDATE trip_events\nSET schedule_confirmation_code = ?,\n    id = ?,\n    card_type = ?,\n    category = ?,\n    confirmation_code = ?,\n    picture = ?,\n    starts_at = ?,\n    ends_at = ?,\n    time_zone = ?,\n    header = ?,\n    card_title = ?,\n    card_subtitle = ?,\n    secondary_actions = ?\nWHERE primary_key = ?"));
            this.tripEventModelFactory = tripEventModelFactory2;
        }

        public void bind(String schedule_confirmation_code, Long id, TripEventCardType card_type, String category, String confirmation_code, String picture, AirDateTime starts_at, AirDateTime ends_at, String time_zone, String header, String card_title, String card_subtitle, ArrayList<TripEventSecondaryAction> secondary_actions, String primary_key) {
            if (schedule_confirmation_code == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, schedule_confirmation_code);
            }
            if (id == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindLong(2, id.longValue());
            }
            this.program.bindString(3, (String) this.tripEventModelFactory.card_typeAdapter.encode(card_type));
            if (category == null) {
                this.program.bindNull(4);
            } else {
                this.program.bindString(4, category);
            }
            if (confirmation_code == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindString(5, confirmation_code);
            }
            if (picture == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindString(6, picture);
            }
            if (starts_at == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, (String) this.tripEventModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(8);
            } else {
                this.program.bindString(8, (String) this.tripEventModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (time_zone == null) {
                this.program.bindNull(9);
            } else {
                this.program.bindString(9, time_zone);
            }
            if (header == null) {
                this.program.bindNull(10);
            } else {
                this.program.bindString(10, header);
            }
            if (card_title == null) {
                this.program.bindNull(11);
            } else {
                this.program.bindString(11, card_title);
            }
            if (card_subtitle == null) {
                this.program.bindNull(12);
            } else {
                this.program.bindString(12, card_subtitle);
            }
            if (secondary_actions == null) {
                this.program.bindNull(13);
            } else {
                this.program.bindBlob(13, (byte[]) this.tripEventModelFactory.secondary_actionsAdapter.encode(secondary_actions));
            }
            this.program.bindString(14, primary_key);
        }
    }

    String card_subtitle();

    String card_title();

    TripEventCardType card_type();

    String category();

    String confirmation_code();

    AirDateTime ends_at();

    String header();

    /* renamed from: id */
    Long mo10245id();

    String picture();

    String primary_key();

    String schedule_confirmation_code();

    ArrayList<TripEventSecondaryAction> secondary_actions();

    AirDateTime starts_at();

    String time_zone();
}
