package com.airbnb.android.itinerary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Insert;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface TimelineTripModel {
    public static final String BUNDLE_PHOTO_URLS = "bundle_photo_urls";
    public static final String BUNDLE_SUBTITLE = "bundle_subtitle";
    public static final String BUNDLE_TITLE = "bundle_title";
    public static final String CONFIRMATION_CODE = "confirmation_code";
    public static final String CREATE_TABLE = "CREATE TABLE timeline_trips (\n    confirmation_code TEXT NOT NULL PRIMARY KEY,\n    starts_at TEXT,\n    ends_at TEXT,\n    expires_at TEXT,\n    time_zone TEXT,\n    title TEXT,\n    bundle_title TEXT,\n    bundle_subtitle TEXT,\n    bundle_photo_urls BLOB,\n    picture TEXT,\n    pending_type TEXT,\n    trip_schedule_cards BLOB,\n    should_bundle INTEGER DEFAULT 0\n)";
    public static final String DELETE_ALL = "DELETE FROM timeline_trips";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS timeline_trips";
    public static final String ENDS_AT = "ends_at";
    public static final String EXPIRES_AT = "expires_at";
    public static final String PENDING_TYPE = "pending_type";
    public static final String PICTURE = "picture";
    public static final String SELECT_ALL = "SELECT *\nFROM timeline_trips\norder by starts_at DESC";
    public static final String SELECT_ALL_CONFIRMATION_CODES = "SELECT confirmation_code\nFROM timeline_trips";
    public static final String SELECT_TIMELINE_TRIP_BY_ID = "SELECT *\nFROM timeline_trips\nWHERE confirmation_code = ?";
    public static final String SHOULD_BUNDLE = "should_bundle";
    public static final String STARTS_AT = "starts_at";
    public static final String TABLE_NAME = "timeline_trips";
    public static final String TIME_ZONE = "time_zone";
    public static final String TITLE = "title";
    public static final String TRIP_SCHEDULE_CARDS = "trip_schedule_cards";

    public static final class Factory<T extends TimelineTripModel> {
        public final ColumnAdapter<ArrayList<String>, byte[]> bundle_photo_urlsAdapter;
        public final Creator<T> creator;
        public final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        public final ColumnAdapter<AirDateTime, String> expires_atAdapter;
        public final ColumnAdapter<AirDateTime, String> starts_atAdapter;
        public final ColumnAdapter<ArrayList<TripEvent>, byte[]> trip_schedule_cardsAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<AirDateTime, String> expires_atAdapter2, ColumnAdapter<ArrayList<String>, byte[]> bundle_photo_urlsAdapter2, ColumnAdapter<ArrayList<TripEvent>, byte[]> trip_schedule_cardsAdapter2) {
            this.creator = creator2;
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.expires_atAdapter = expires_atAdapter2;
            this.bundle_photo_urlsAdapter = bundle_photo_urlsAdapter2;
            this.trip_schedule_cardsAdapter = trip_schedule_cardsAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.starts_atAdapter, this.ends_atAdapter, this.expires_atAdapter, this.bundle_photo_urlsAdapter, this.trip_schedule_cardsAdapter);
        }

        @Deprecated
        public Marshal marshal(TimelineTripModel copy) {
            return new Marshal(copy, this.starts_atAdapter, this.ends_atAdapter, this.expires_atAdapter, this.bundle_photo_urlsAdapter, this.trip_schedule_cardsAdapter);
        }

        public SqlDelightStatement select_timeline_trip_by_id(String confirmation_code) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM timeline_trips\nWHERE confirmation_code = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(confirmation_code);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TimelineTripModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_timeline_trip_by_id(String confirmation_code) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM timeline_trips\nWHERE confirmation_code = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(confirmation_code);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TimelineTripModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement insert_timeline_trip(String confirmation_code, AirDateTime starts_at, AirDateTime ends_at, AirDateTime expires_at, String time_zone, String title, String bundle_title, String bundle_subtitle, ArrayList<String> bundle_photo_urls, String picture, String pending_type, ArrayList<TripEvent> trip_schedule_cards, Boolean should_bundle) {
            int currentIndex;
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("INSERT OR REPLACE INTO timeline_trips (\n  confirmation_code, starts_at, ends_at, expires_at, time_zone, title, bundle_title, bundle_subtitle, bundle_photo_urls, picture, pending_type, trip_schedule_cards, should_bundle)\nVALUES (");
            int currentIndex2 = 1 + 1;
            query.append('?').append(1);
            args.add(confirmation_code);
            query.append(", ");
            if (starts_at == null) {
                query.append("null");
                currentIndex = currentIndex2;
            } else {
                currentIndex = currentIndex2 + 1;
                query.append('?').append(currentIndex2);
                args.add((String) this.starts_atAdapter.encode(starts_at));
            }
            query.append(", ");
            if (ends_at == null) {
                query.append("null");
            } else {
                int currentIndex3 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.ends_atAdapter.encode(ends_at));
                currentIndex = currentIndex3;
            }
            query.append(", ");
            if (expires_at == null) {
                query.append("null");
            } else {
                int currentIndex4 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.expires_atAdapter.encode(expires_at));
                currentIndex = currentIndex4;
            }
            query.append(", ");
            if (time_zone == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(time_zone);
                currentIndex = currentIndex5;
            }
            query.append(", ");
            if (title == null) {
                query.append("null");
            } else {
                int currentIndex6 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(title);
                currentIndex = currentIndex6;
            }
            query.append(", ");
            if (bundle_title == null) {
                query.append("null");
            } else {
                int currentIndex7 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(bundle_title);
                currentIndex = currentIndex7;
            }
            query.append(", ");
            if (bundle_subtitle == null) {
                query.append("null");
            } else {
                int currentIndex8 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(bundle_subtitle);
                currentIndex = currentIndex8;
            }
            query.append(", ");
            if (bundle_photo_urls == null) {
                query.append("null");
            } else {
                query.append(this.bundle_photo_urlsAdapter.encode(bundle_photo_urls));
            }
            query.append(", ");
            if (picture == null) {
                query.append("null");
            } else {
                int currentIndex9 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(picture);
                currentIndex = currentIndex9;
            }
            query.append(", ");
            if (pending_type == null) {
                query.append("null");
            } else {
                int currentIndex10 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(pending_type);
                int i = currentIndex10;
            }
            query.append(", ");
            if (trip_schedule_cards == null) {
                query.append("null");
            } else {
                query.append(this.trip_schedule_cardsAdapter.encode(trip_schedule_cards));
            }
            query.append(", ");
            if (should_bundle == null) {
                query.append("null");
            } else {
                query.append(should_bundle.booleanValue() ? 1 : 0);
            }
            query.append(")");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TimelineTripModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_timeline_trip(AirDateTime starts_at, AirDateTime ends_at, AirDateTime expires_at, String time_zone, String title, String bundle_title, String bundle_subtitle, ArrayList<String> bundle_photo_urls, String picture, String pending_type, ArrayList<TripEvent> trip_schedule_cards, Boolean should_bundle, String confirmation_code) {
            List<String> args = new ArrayList<>();
            int currentIndex = 1;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE timeline_trips\nSET starts_at = ");
            if (starts_at == null) {
                query.append("null");
            } else {
                int currentIndex2 = 1 + 1;
                query.append('?').append(1);
                args.add((String) this.starts_atAdapter.encode(starts_at));
                currentIndex = currentIndex2;
            }
            query.append(",\n    ends_at = ");
            if (ends_at == null) {
                query.append("null");
            } else {
                int currentIndex3 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.ends_atAdapter.encode(ends_at));
                currentIndex = currentIndex3;
            }
            query.append(",\n    expires_at = ");
            if (expires_at == null) {
                query.append("null");
            } else {
                int currentIndex4 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add((String) this.expires_atAdapter.encode(expires_at));
                currentIndex = currentIndex4;
            }
            query.append(",\n    time_zone = ");
            if (time_zone == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(time_zone);
                currentIndex = currentIndex5;
            }
            query.append(",\n    title = ");
            if (title == null) {
                query.append("null");
            } else {
                int currentIndex6 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(title);
                currentIndex = currentIndex6;
            }
            query.append(",\n    bundle_title = ");
            if (bundle_title == null) {
                query.append("null");
            } else {
                int currentIndex7 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(bundle_title);
                currentIndex = currentIndex7;
            }
            query.append(",\n    bundle_subtitle = ");
            if (bundle_subtitle == null) {
                query.append("null");
            } else {
                int currentIndex8 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(bundle_subtitle);
                currentIndex = currentIndex8;
            }
            query.append(",\n    bundle_photo_urls = ");
            if (bundle_photo_urls == null) {
                query.append("null");
            } else {
                query.append(this.bundle_photo_urlsAdapter.encode(bundle_photo_urls));
            }
            query.append(",\n    picture = ");
            if (picture == null) {
                query.append("null");
            } else {
                int currentIndex9 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(picture);
                currentIndex = currentIndex9;
            }
            query.append(",\n    pending_type = ");
            if (pending_type == null) {
                query.append("null");
            } else {
                int currentIndex10 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(pending_type);
                currentIndex = currentIndex10;
            }
            query.append(",\n    trip_schedule_cards = ");
            if (trip_schedule_cards == null) {
                query.append("null");
            } else {
                query.append(this.trip_schedule_cardsAdapter.encode(trip_schedule_cards));
            }
            query.append(",\n    should_bundle = ");
            if (should_bundle == null) {
                query.append("null");
            } else {
                query.append(should_bundle.booleanValue() ? 1 : 0);
            }
            query.append("\nWHERE confirmation_code = ");
            int i = currentIndex + 1;
            query.append('?').append(currentIndex);
            args.add(confirmation_code);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(TimelineTripModel.TABLE_NAME));
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }

        public RowMapper<String> select_all_confirmation_codesMapper() {
            return new RowMapper<String>() {
                public String map(Cursor cursor) {
                    return cursor.getString(0);
                }
            };
        }

        public Mapper<T> select_timeline_trip_by_idMapper() {
            return new Mapper<>(this);
        }
    }

    public interface Creator<T extends TimelineTripModel> {
        T create(String str, AirDateTime airDateTime, AirDateTime airDateTime2, AirDateTime airDateTime3, String str2, String str3, String str4, String str5, ArrayList<String> arrayList, String str6, String str7, ArrayList<TripEvent> arrayList2, Boolean bool);
    }

    public static final class Delete_timeline_trip_by_id extends Delete {
        public Delete_timeline_trip_by_id(SQLiteDatabase database) {
            super(TimelineTripModel.TABLE_NAME, database.compileStatement("DELETE FROM timeline_trips\nWHERE confirmation_code = ?"));
        }

        public void bind(String confirmation_code) {
            this.program.bindString(1, confirmation_code);
        }
    }

    public static final class Insert_timeline_trip extends Insert {
        private final Factory<? extends TimelineTripModel> timelineTripModelFactory;

        public Insert_timeline_trip(SQLiteDatabase database, Factory<? extends TimelineTripModel> timelineTripModelFactory2) {
            super(TimelineTripModel.TABLE_NAME, database.compileStatement("INSERT OR REPLACE INTO timeline_trips (\n  confirmation_code, starts_at, ends_at, expires_at, time_zone, title, bundle_title, bundle_subtitle, bundle_photo_urls, picture, pending_type, trip_schedule_cards, should_bundle)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
            this.timelineTripModelFactory = timelineTripModelFactory2;
        }

        public void bind(String confirmation_code, AirDateTime starts_at, AirDateTime ends_at, AirDateTime expires_at, String time_zone, String title, String bundle_title, String bundle_subtitle, ArrayList<String> bundle_photo_urls, String picture, String pending_type, ArrayList<TripEvent> trip_schedule_cards, Boolean should_bundle) {
            this.program.bindString(1, confirmation_code);
            if (starts_at == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, (String) this.timelineTripModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(3);
            } else {
                this.program.bindString(3, (String) this.timelineTripModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (expires_at == null) {
                this.program.bindNull(4);
            } else {
                this.program.bindString(4, (String) this.timelineTripModelFactory.expires_atAdapter.encode(expires_at));
            }
            if (time_zone == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindString(5, time_zone);
            }
            if (title == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindString(6, title);
            }
            if (bundle_title == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, bundle_title);
            }
            if (bundle_subtitle == null) {
                this.program.bindNull(8);
            } else {
                this.program.bindString(8, bundle_subtitle);
            }
            if (bundle_photo_urls == null) {
                this.program.bindNull(9);
            } else {
                this.program.bindBlob(9, (byte[]) this.timelineTripModelFactory.bundle_photo_urlsAdapter.encode(bundle_photo_urls));
            }
            if (picture == null) {
                this.program.bindNull(10);
            } else {
                this.program.bindString(10, picture);
            }
            if (pending_type == null) {
                this.program.bindNull(11);
            } else {
                this.program.bindString(11, pending_type);
            }
            if (trip_schedule_cards == null) {
                this.program.bindNull(12);
            } else {
                this.program.bindBlob(12, (byte[]) this.timelineTripModelFactory.trip_schedule_cardsAdapter.encode(trip_schedule_cards));
            }
            if (should_bundle == null) {
                this.program.bindNull(13);
            } else {
                this.program.bindLong(13, should_bundle.booleanValue() ? 1 : 0);
            }
        }
    }

    public static final class Mapper<T extends TimelineTripModel> implements RowMapper<T> {
        private final Factory<T> timelineTripModelFactory;

        public Mapper(Factory<T> timelineTripModelFactory2) {
            this.timelineTripModelFactory = timelineTripModelFactory2;
        }

        public T map(Cursor cursor) {
            Boolean valueOf;
            Creator<T> creator = this.timelineTripModelFactory.creator;
            String string = cursor.getString(0);
            AirDateTime airDateTime = cursor.isNull(1) ? null : (AirDateTime) this.timelineTripModelFactory.starts_atAdapter.decode(cursor.getString(1));
            AirDateTime airDateTime2 = cursor.isNull(2) ? null : (AirDateTime) this.timelineTripModelFactory.ends_atAdapter.decode(cursor.getString(2));
            AirDateTime airDateTime3 = cursor.isNull(3) ? null : (AirDateTime) this.timelineTripModelFactory.expires_atAdapter.decode(cursor.getString(3));
            String string2 = cursor.isNull(4) ? null : cursor.getString(4);
            String string3 = cursor.isNull(5) ? null : cursor.getString(5);
            String string4 = cursor.isNull(6) ? null : cursor.getString(6);
            String string5 = cursor.isNull(7) ? null : cursor.getString(7);
            ArrayList arrayList = cursor.isNull(8) ? null : (ArrayList) this.timelineTripModelFactory.bundle_photo_urlsAdapter.decode(cursor.getBlob(8));
            String string6 = cursor.isNull(9) ? null : cursor.getString(9);
            String string7 = cursor.isNull(10) ? null : cursor.getString(10);
            ArrayList arrayList2 = cursor.isNull(11) ? null : (ArrayList) this.timelineTripModelFactory.trip_schedule_cardsAdapter.decode(cursor.getBlob(11));
            if (cursor.isNull(12)) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(cursor.getInt(12) == 1);
            }
            return creator.create(string, airDateTime, airDateTime2, airDateTime3, string2, string3, string4, string5, arrayList, string6, string7, arrayList2, valueOf);
        }
    }

    public static final class Marshal {
        private final ColumnAdapter<ArrayList<String>, byte[]> bundle_photo_urlsAdapter;
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        private final ColumnAdapter<AirDateTime, String> expires_atAdapter;
        private final ColumnAdapter<AirDateTime, String> starts_atAdapter;
        private final ColumnAdapter<ArrayList<TripEvent>, byte[]> trip_schedule_cardsAdapter;

        Marshal(TimelineTripModel copy, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<AirDateTime, String> expires_atAdapter2, ColumnAdapter<ArrayList<String>, byte[]> bundle_photo_urlsAdapter2, ColumnAdapter<ArrayList<TripEvent>, byte[]> trip_schedule_cardsAdapter2) {
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.expires_atAdapter = expires_atAdapter2;
            this.bundle_photo_urlsAdapter = bundle_photo_urlsAdapter2;
            this.trip_schedule_cardsAdapter = trip_schedule_cardsAdapter2;
            if (copy != null) {
                confirmation_code(copy.confirmation_code());
                starts_at(copy.starts_at());
                ends_at(copy.ends_at());
                expires_at(copy.expires_at());
                time_zone(copy.time_zone());
                title(copy.title());
                bundle_title(copy.bundle_title());
                bundle_subtitle(copy.bundle_subtitle());
                bundle_photo_urls(copy.bundle_photo_urls());
                picture(copy.picture());
                pending_type(copy.pending_type());
                trip_schedule_cards(copy.trip_schedule_cards());
                should_bundle(copy.should_bundle());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal confirmation_code(String confirmation_code) {
            this.contentValues.put("confirmation_code", confirmation_code);
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

        public Marshal expires_at(AirDateTime expires_at) {
            if (expires_at != null) {
                this.contentValues.put("expires_at", (String) this.expires_atAdapter.encode(expires_at));
            } else {
                this.contentValues.putNull("expires_at");
            }
            return this;
        }

        public Marshal time_zone(String time_zone) {
            this.contentValues.put("time_zone", time_zone);
            return this;
        }

        public Marshal title(String title) {
            this.contentValues.put("title", title);
            return this;
        }

        public Marshal bundle_title(String bundle_title) {
            this.contentValues.put(TimelineTripModel.BUNDLE_TITLE, bundle_title);
            return this;
        }

        public Marshal bundle_subtitle(String bundle_subtitle) {
            this.contentValues.put(TimelineTripModel.BUNDLE_SUBTITLE, bundle_subtitle);
            return this;
        }

        public Marshal bundle_photo_urls(ArrayList<String> bundle_photo_urls) {
            if (bundle_photo_urls != null) {
                this.contentValues.put(TimelineTripModel.BUNDLE_PHOTO_URLS, (byte[]) this.bundle_photo_urlsAdapter.encode(bundle_photo_urls));
            } else {
                this.contentValues.putNull(TimelineTripModel.BUNDLE_PHOTO_URLS);
            }
            return this;
        }

        public Marshal picture(String picture) {
            this.contentValues.put("picture", picture);
            return this;
        }

        public Marshal pending_type(String pending_type) {
            this.contentValues.put(TimelineTripModel.PENDING_TYPE, pending_type);
            return this;
        }

        public Marshal trip_schedule_cards(ArrayList<TripEvent> trip_schedule_cards) {
            if (trip_schedule_cards != null) {
                this.contentValues.put(TimelineTripModel.TRIP_SCHEDULE_CARDS, (byte[]) this.trip_schedule_cardsAdapter.encode(trip_schedule_cards));
            } else {
                this.contentValues.putNull(TimelineTripModel.TRIP_SCHEDULE_CARDS);
            }
            return this;
        }

        public Marshal should_bundle(Boolean should_bundle) {
            if (should_bundle == null) {
                this.contentValues.putNull(TimelineTripModel.SHOULD_BUNDLE);
            } else {
                this.contentValues.put(TimelineTripModel.SHOULD_BUNDLE, Integer.valueOf(should_bundle.booleanValue() ? 1 : 0));
            }
            return this;
        }
    }

    public static final class Update_timeline_trip extends Update {
        private final Factory<? extends TimelineTripModel> timelineTripModelFactory;

        public Update_timeline_trip(SQLiteDatabase database, Factory<? extends TimelineTripModel> timelineTripModelFactory2) {
            super(TimelineTripModel.TABLE_NAME, database.compileStatement("UPDATE timeline_trips\nSET starts_at = ?,\n    ends_at = ?,\n    expires_at = ?,\n    time_zone = ?,\n    title = ?,\n    bundle_title = ?,\n    bundle_subtitle = ?,\n    bundle_photo_urls = ?,\n    picture = ?,\n    pending_type = ?,\n    trip_schedule_cards = ?,\n    should_bundle = ?\nWHERE confirmation_code = ?"));
            this.timelineTripModelFactory = timelineTripModelFactory2;
        }

        public void bind(AirDateTime starts_at, AirDateTime ends_at, AirDateTime expires_at, String time_zone, String title, String bundle_title, String bundle_subtitle, ArrayList<String> bundle_photo_urls, String picture, String pending_type, ArrayList<TripEvent> trip_schedule_cards, Boolean should_bundle, String confirmation_code) {
            if (starts_at == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, (String) this.timelineTripModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, (String) this.timelineTripModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (expires_at == null) {
                this.program.bindNull(3);
            } else {
                this.program.bindString(3, (String) this.timelineTripModelFactory.expires_atAdapter.encode(expires_at));
            }
            if (time_zone == null) {
                this.program.bindNull(4);
            } else {
                this.program.bindString(4, time_zone);
            }
            if (title == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindString(5, title);
            }
            if (bundle_title == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindString(6, bundle_title);
            }
            if (bundle_subtitle == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, bundle_subtitle);
            }
            if (bundle_photo_urls == null) {
                this.program.bindNull(8);
            } else {
                this.program.bindBlob(8, (byte[]) this.timelineTripModelFactory.bundle_photo_urlsAdapter.encode(bundle_photo_urls));
            }
            if (picture == null) {
                this.program.bindNull(9);
            } else {
                this.program.bindString(9, picture);
            }
            if (pending_type == null) {
                this.program.bindNull(10);
            } else {
                this.program.bindString(10, pending_type);
            }
            if (trip_schedule_cards == null) {
                this.program.bindNull(11);
            } else {
                this.program.bindBlob(11, (byte[]) this.timelineTripModelFactory.trip_schedule_cardsAdapter.encode(trip_schedule_cards));
            }
            if (should_bundle == null) {
                this.program.bindNull(12);
            } else {
                this.program.bindLong(12, should_bundle.booleanValue() ? 1 : 0);
            }
            this.program.bindString(13, confirmation_code);
        }
    }

    ArrayList<String> bundle_photo_urls();

    String bundle_subtitle();

    String bundle_title();

    String confirmation_code();

    AirDateTime ends_at();

    AirDateTime expires_at();

    String pending_type();

    String picture();

    Boolean should_bundle();

    AirDateTime starts_at();

    String time_zone();

    String title();

    ArrayList<TripEvent> trip_schedule_cards();
}
