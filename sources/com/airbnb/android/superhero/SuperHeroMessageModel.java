package com.airbnb.android.superhero;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.airdate.AirDateTime;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Delete;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Insert;
import com.squareup.sqldelight.SqlDelightCompiledStatement.Update;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface SuperHeroMessageModel {
    public static final String CREATE_TABLE = "CREATE TABLE super_hero_messages (\n  id INTEGER NOT NULL PRIMARY KEY,\n  starts_at TEXT,\n  ends_at TEXT,\n  lat REAL,\n  lng REAL,\n  radius INTEGER,\n  dismiss_text TEXT,\n  hero_type_string TEXT,\n  messages BLOB NOT NULL,\n  should_takeover INTEGER NOT NULL DEFAULT 0,\n  hero_actions BLOB,\n  status INTEGER NOT NULL,\n  trigger_type INTEGER\n)";
    public static final String DELETE_ALL = "DELETE FROM super_hero_messages";
    public static final String DISMISS_TEXT = "dismiss_text";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS super_hero_messages";
    public static final String ENDS_AT = "ends_at";
    public static final String HERO_ACTIONS = "hero_actions";
    public static final String HERO_TYPE_STRING = "hero_type_string";

    /* renamed from: ID */
    public static final String f2444ID = "id";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String MESSAGES = "messages";
    public static final String RADIUS = "radius";
    public static final String SELECT_ALL = "SELECT *\nFROM super_hero_messages";
    public static final String SELECT_MESSAGES_FOR_LAUNCH = "SELECT *\nFROM super_hero_messages\nWHERE (status = 0 OR status = 1)\nORDER BY starts_at DESC";
    public static final String SELECT_MESSAGES_TO_PRESENT = "SELECT *\nFROM super_hero_messages\nWHERE (status = 2 OR status = 3)\nORDER BY starts_at ASC";
    public static final String SELECT_MESSAGE_BY_ID = "SELECT *\nFROM super_hero_messages\nWHERE id = ?";
    public static final String SELECT_PREVIEW_MESSAGES_FOR_INBOX = "SELECT * FROM\n  (SELECT * FROM super_hero_messages WHERE status=2 order by starts_at LIMIT 1)\nUNION\nSELECT * FROM\n  (SELECT * FROM super_hero_messages WHERE status=3 order by starts_at DESC LIMIT 1)";
    public static final String SHOULD_TAKEOVER = "should_takeover";
    public static final String STARTS_AT = "starts_at";
    public static final String STATUS = "status";
    public static final String TABLE_NAME = "super_hero_messages";
    public static final String TRIGGER_TYPE = "trigger_type";

    public static final class Factory<T extends SuperHeroMessageModel> {
        public final Creator<T> creator;
        public final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        public final ColumnAdapter<ArrayList<SuperHeroAction>, byte[]> hero_actionsAdapter;
        public final ColumnAdapter<ArrayList<String>, byte[]> messagesAdapter;
        public final ColumnAdapter<AirDateTime, String> starts_atAdapter;

        public Factory(Creator<T> creator2, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<ArrayList<String>, byte[]> messagesAdapter2, ColumnAdapter<ArrayList<SuperHeroAction>, byte[]> hero_actionsAdapter2) {
            this.creator = creator2;
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.messagesAdapter = messagesAdapter2;
            this.hero_actionsAdapter = hero_actionsAdapter2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null, this.starts_atAdapter, this.ends_atAdapter, this.messagesAdapter, this.hero_actionsAdapter);
        }

        @Deprecated
        public Marshal marshal(SuperHeroMessageModel copy) {
            return new Marshal(copy, this.starts_atAdapter, this.ends_atAdapter, this.messagesAdapter, this.hero_actionsAdapter);
        }

        public SqlDelightStatement select_message_by_id(long id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM super_hero_messages\nWHERE id = ");
            query.append(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SuperHeroMessageModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement delete_message(long id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM super_hero_messages\nWHERE id = ");
            query.append(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SuperHeroMessageModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement insert_message(long id, AirDateTime starts_at, AirDateTime ends_at, Double lat, Double lng, Long radius, String dismiss_text, String hero_type_string, ArrayList<String> messages, boolean should_takeover, ArrayList<SuperHeroAction> hero_actions, long status, Long trigger_type) {
            List<String> args = new ArrayList<>();
            int currentIndex = 1;
            StringBuilder query = new StringBuilder();
            query.append("INSERT OR REPLACE INTO super_hero_messages (\n  id, starts_at, ends_at, lat, lng, radius, dismiss_text, hero_type_string, messages, should_takeover, hero_actions, status, trigger_type)\nVALUES (");
            query.append(id);
            query.append(", ");
            if (starts_at == null) {
                query.append("null");
            } else {
                int currentIndex2 = 1 + 1;
                query.append('?').append(1);
                args.add((String) this.starts_atAdapter.encode(starts_at));
                currentIndex = currentIndex2;
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
            if (lat == null) {
                query.append("null");
            } else {
                query.append(lat);
            }
            query.append(", ");
            if (lng == null) {
                query.append("null");
            } else {
                query.append(lng);
            }
            query.append(", ");
            if (radius == null) {
                query.append("null");
            } else {
                query.append(radius);
            }
            query.append(", ");
            if (dismiss_text == null) {
                query.append("null");
            } else {
                int currentIndex4 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(dismiss_text);
                currentIndex = currentIndex4;
            }
            query.append(", ");
            if (hero_type_string == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(hero_type_string);
                int i = currentIndex5;
            }
            query.append(", ");
            query.append(this.messagesAdapter.encode(messages));
            query.append(", ");
            query.append(should_takeover ? 1 : 0);
            query.append(", ");
            if (hero_actions == null) {
                query.append("null");
            } else {
                query.append(this.hero_actionsAdapter.encode(hero_actions));
            }
            query.append(", ");
            query.append(status);
            query.append(", ");
            if (trigger_type == null) {
                query.append("null");
            } else {
                query.append(trigger_type);
            }
            query.append(")");
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SuperHeroMessageModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_message(AirDateTime starts_at, AirDateTime ends_at, Double lat, Double lng, Long radius, String dismiss_text, String hero_type_string, ArrayList<String> messages, boolean should_takeover, ArrayList<SuperHeroAction> hero_actions, long status, Long trigger_type, long id) {
            List<String> args = new ArrayList<>();
            int currentIndex = 1;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE super_hero_messages\nSET starts_at = ");
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
            query.append(",\n    lat = ");
            if (lat == null) {
                query.append("null");
            } else {
                query.append(lat);
            }
            query.append(",\n    lng = ");
            if (lng == null) {
                query.append("null");
            } else {
                query.append(lng);
            }
            query.append(",\n    radius = ");
            if (radius == null) {
                query.append("null");
            } else {
                query.append(radius);
            }
            query.append(",\n    dismiss_text = ");
            if (dismiss_text == null) {
                query.append("null");
            } else {
                int currentIndex4 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(dismiss_text);
                currentIndex = currentIndex4;
            }
            query.append(",\n    hero_type_string = ");
            if (hero_type_string == null) {
                query.append("null");
            } else {
                int currentIndex5 = currentIndex + 1;
                query.append('?').append(currentIndex);
                args.add(hero_type_string);
                int i = currentIndex5;
            }
            query.append(",\n    messages = ");
            query.append(this.messagesAdapter.encode(messages));
            query.append(",\n    should_takeover = ");
            query.append(should_takeover ? 1 : 0);
            query.append(",\n    hero_actions = ");
            if (hero_actions == null) {
                query.append("null");
            } else {
                query.append(this.hero_actionsAdapter.encode(hero_actions));
            }
            query.append(",\n    status = ");
            query.append(status);
            query.append(",\n    trigger_type = ");
            if (trigger_type == null) {
                query.append("null");
            } else {
                query.append(trigger_type);
            }
            query.append("\nWHERE id = ");
            query.append(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SuperHeroMessageModel.TABLE_NAME));
        }

        @Deprecated
        public SqlDelightStatement update_message_status(long status, long id) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("UPDATE super_hero_messages\nSET status = ");
            query.append(status);
            query.append("\nWHERE id = ");
            query.append(id);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(SuperHeroMessageModel.TABLE_NAME));
        }

        public Mapper<T> select_message_by_idMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_messages_to_presentMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_messages_for_launchMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_preview_messages_for_inboxMapper() {
            return new Mapper<>(this);
        }
    }

    public interface Creator<T extends SuperHeroMessageModel> {
        T create(long j, AirDateTime airDateTime, AirDateTime airDateTime2, Double d, Double d2, Long l, String str, String str2, ArrayList<String> arrayList, boolean z, ArrayList<SuperHeroAction> arrayList2, long j2, Long l2);
    }

    public static final class Delete_message extends Delete {
        public Delete_message(SQLiteDatabase database) {
            super(SuperHeroMessageModel.TABLE_NAME, database.compileStatement("DELETE FROM super_hero_messages\nWHERE id = ?"));
        }

        public void bind(long id) {
            this.program.bindLong(1, id);
        }
    }

    public static final class Insert_message extends Insert {
        private final Factory<? extends SuperHeroMessageModel> superHeroMessageModelFactory;

        public Insert_message(SQLiteDatabase database, Factory<? extends SuperHeroMessageModel> superHeroMessageModelFactory2) {
            super(SuperHeroMessageModel.TABLE_NAME, database.compileStatement("INSERT OR REPLACE INTO super_hero_messages (\n  id, starts_at, ends_at, lat, lng, radius, dismiss_text, hero_type_string, messages, should_takeover, hero_actions, status, trigger_type)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
            this.superHeroMessageModelFactory = superHeroMessageModelFactory2;
        }

        public void bind(long id, AirDateTime starts_at, AirDateTime ends_at, Double lat, Double lng, Long radius, String dismiss_text, String hero_type_string, ArrayList<String> messages, boolean should_takeover, ArrayList<SuperHeroAction> hero_actions, long status, Long trigger_type) {
            this.program.bindLong(1, id);
            if (starts_at == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, (String) this.superHeroMessageModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(3);
            } else {
                this.program.bindString(3, (String) this.superHeroMessageModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (lat == null) {
                this.program.bindNull(4);
            } else {
                this.program.bindDouble(4, lat.doubleValue());
            }
            if (lng == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindDouble(5, lng.doubleValue());
            }
            if (radius == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindLong(6, radius.longValue());
            }
            if (dismiss_text == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, dismiss_text);
            }
            if (hero_type_string == null) {
                this.program.bindNull(8);
            } else {
                this.program.bindString(8, hero_type_string);
            }
            this.program.bindBlob(9, (byte[]) this.superHeroMessageModelFactory.messagesAdapter.encode(messages));
            this.program.bindLong(10, should_takeover ? 1 : 0);
            if (hero_actions == null) {
                this.program.bindNull(11);
            } else {
                this.program.bindBlob(11, (byte[]) this.superHeroMessageModelFactory.hero_actionsAdapter.encode(hero_actions));
            }
            this.program.bindLong(12, status);
            if (trigger_type == null) {
                this.program.bindNull(13);
            } else {
                this.program.bindLong(13, trigger_type.longValue());
            }
        }
    }

    public static final class Mapper<T extends SuperHeroMessageModel> implements RowMapper<T> {
        private final Factory<T> superHeroMessageModelFactory;

        public Mapper(Factory<T> superHeroMessageModelFactory2) {
            this.superHeroMessageModelFactory = superHeroMessageModelFactory2;
        }

        public T map(Cursor cursor) {
            Long valueOf;
            Creator<T> creator = this.superHeroMessageModelFactory.creator;
            long j = cursor.getLong(0);
            AirDateTime airDateTime = cursor.isNull(1) ? null : (AirDateTime) this.superHeroMessageModelFactory.starts_atAdapter.decode(cursor.getString(1));
            AirDateTime airDateTime2 = cursor.isNull(2) ? null : (AirDateTime) this.superHeroMessageModelFactory.ends_atAdapter.decode(cursor.getString(2));
            Double valueOf2 = cursor.isNull(3) ? null : Double.valueOf(cursor.getDouble(3));
            Double valueOf3 = cursor.isNull(4) ? null : Double.valueOf(cursor.getDouble(4));
            Long valueOf4 = cursor.isNull(5) ? null : Long.valueOf(cursor.getLong(5));
            String string = cursor.isNull(6) ? null : cursor.getString(6);
            String string2 = cursor.isNull(7) ? null : cursor.getString(7);
            ArrayList arrayList = (ArrayList) this.superHeroMessageModelFactory.messagesAdapter.decode(cursor.getBlob(8));
            boolean z = cursor.getInt(9) == 1;
            ArrayList arrayList2 = cursor.isNull(10) ? null : (ArrayList) this.superHeroMessageModelFactory.hero_actionsAdapter.decode(cursor.getBlob(10));
            long j2 = cursor.getLong(11);
            if (cursor.isNull(12)) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(cursor.getLong(12));
            }
            return creator.create(j, airDateTime, airDateTime2, valueOf2, valueOf3, valueOf4, string, string2, arrayList, z, arrayList2, j2, valueOf);
        }
    }

    public static final class Marshal {
        protected final ContentValues contentValues = new ContentValues();
        private final ColumnAdapter<AirDateTime, String> ends_atAdapter;
        private final ColumnAdapter<ArrayList<SuperHeroAction>, byte[]> hero_actionsAdapter;
        private final ColumnAdapter<ArrayList<String>, byte[]> messagesAdapter;
        private final ColumnAdapter<AirDateTime, String> starts_atAdapter;

        Marshal(SuperHeroMessageModel copy, ColumnAdapter<AirDateTime, String> starts_atAdapter2, ColumnAdapter<AirDateTime, String> ends_atAdapter2, ColumnAdapter<ArrayList<String>, byte[]> messagesAdapter2, ColumnAdapter<ArrayList<SuperHeroAction>, byte[]> hero_actionsAdapter2) {
            this.starts_atAdapter = starts_atAdapter2;
            this.ends_atAdapter = ends_atAdapter2;
            this.messagesAdapter = messagesAdapter2;
            this.hero_actionsAdapter = hero_actionsAdapter2;
            if (copy != null) {
                mo19671id(copy.mo11531id());
                starts_at(copy.starts_at());
                ends_at(copy.ends_at());
                lat(copy.lat());
                lng(copy.lng());
                radius(copy.radius());
                dismiss_text(copy.dismiss_text());
                hero_type_string(copy.hero_type_string());
                messages(copy.messages());
                should_takeover(copy.should_takeover());
                hero_actions(copy.hero_actions());
                status(copy.status());
                trigger_type(copy.trigger_type());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        /* renamed from: id */
        public Marshal mo19671id(long id) {
            this.contentValues.put("id", Long.valueOf(id));
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

        public Marshal lat(Double lat) {
            this.contentValues.put("lat", lat);
            return this;
        }

        public Marshal lng(Double lng) {
            this.contentValues.put("lng", lng);
            return this;
        }

        public Marshal radius(Long radius) {
            this.contentValues.put(SuperHeroMessageModel.RADIUS, radius);
            return this;
        }

        public Marshal dismiss_text(String dismiss_text) {
            this.contentValues.put(SuperHeroMessageModel.DISMISS_TEXT, dismiss_text);
            return this;
        }

        public Marshal hero_type_string(String hero_type_string) {
            this.contentValues.put(SuperHeroMessageModel.HERO_TYPE_STRING, hero_type_string);
            return this;
        }

        public Marshal messages(ArrayList<String> messages) {
            this.contentValues.put("messages", (byte[]) this.messagesAdapter.encode(messages));
            return this;
        }

        public Marshal should_takeover(boolean should_takeover) {
            this.contentValues.put(SuperHeroMessageModel.SHOULD_TAKEOVER, Integer.valueOf(should_takeover ? 1 : 0));
            return this;
        }

        public Marshal hero_actions(ArrayList<SuperHeroAction> hero_actions) {
            if (hero_actions != null) {
                this.contentValues.put(SuperHeroMessageModel.HERO_ACTIONS, (byte[]) this.hero_actionsAdapter.encode(hero_actions));
            } else {
                this.contentValues.putNull(SuperHeroMessageModel.HERO_ACTIONS);
            }
            return this;
        }

        public Marshal status(long status) {
            this.contentValues.put("status", Long.valueOf(status));
            return this;
        }

        public Marshal trigger_type(Long trigger_type) {
            this.contentValues.put(SuperHeroMessageModel.TRIGGER_TYPE, trigger_type);
            return this;
        }
    }

    public static final class Update_message extends Update {
        private final Factory<? extends SuperHeroMessageModel> superHeroMessageModelFactory;

        public Update_message(SQLiteDatabase database, Factory<? extends SuperHeroMessageModel> superHeroMessageModelFactory2) {
            super(SuperHeroMessageModel.TABLE_NAME, database.compileStatement("UPDATE super_hero_messages\nSET starts_at = ?,\n    ends_at = ?,\n    lat = ?,\n    lng = ?,\n    radius = ?,\n    dismiss_text = ?,\n    hero_type_string = ?,\n    messages = ?,\n    should_takeover = ?,\n    hero_actions = ?,\n    status = ?,\n    trigger_type = ?\nWHERE id = ?"));
            this.superHeroMessageModelFactory = superHeroMessageModelFactory2;
        }

        public void bind(AirDateTime starts_at, AirDateTime ends_at, Double lat, Double lng, Long radius, String dismiss_text, String hero_type_string, ArrayList<String> messages, boolean should_takeover, ArrayList<SuperHeroAction> hero_actions, long status, Long trigger_type, long id) {
            if (starts_at == null) {
                this.program.bindNull(1);
            } else {
                this.program.bindString(1, (String) this.superHeroMessageModelFactory.starts_atAdapter.encode(starts_at));
            }
            if (ends_at == null) {
                this.program.bindNull(2);
            } else {
                this.program.bindString(2, (String) this.superHeroMessageModelFactory.ends_atAdapter.encode(ends_at));
            }
            if (lat == null) {
                this.program.bindNull(3);
            } else {
                this.program.bindDouble(3, lat.doubleValue());
            }
            if (lng == null) {
                this.program.bindNull(4);
            } else {
                this.program.bindDouble(4, lng.doubleValue());
            }
            if (radius == null) {
                this.program.bindNull(5);
            } else {
                this.program.bindLong(5, radius.longValue());
            }
            if (dismiss_text == null) {
                this.program.bindNull(6);
            } else {
                this.program.bindString(6, dismiss_text);
            }
            if (hero_type_string == null) {
                this.program.bindNull(7);
            } else {
                this.program.bindString(7, hero_type_string);
            }
            this.program.bindBlob(8, (byte[]) this.superHeroMessageModelFactory.messagesAdapter.encode(messages));
            this.program.bindLong(9, should_takeover ? 1 : 0);
            if (hero_actions == null) {
                this.program.bindNull(10);
            } else {
                this.program.bindBlob(10, (byte[]) this.superHeroMessageModelFactory.hero_actionsAdapter.encode(hero_actions));
            }
            this.program.bindLong(11, status);
            if (trigger_type == null) {
                this.program.bindNull(12);
            } else {
                this.program.bindLong(12, trigger_type.longValue());
            }
            this.program.bindLong(13, id);
        }
    }

    public static final class Update_message_status extends Update {
        public Update_message_status(SQLiteDatabase database) {
            super(SuperHeroMessageModel.TABLE_NAME, database.compileStatement("UPDATE super_hero_messages\nSET status = ?\nWHERE id = ?"));
        }

        public void bind(long status, long id) {
            this.program.bindLong(1, status);
            this.program.bindLong(2, id);
        }
    }

    String dismiss_text();

    AirDateTime ends_at();

    ArrayList<SuperHeroAction> hero_actions();

    String hero_type_string();

    /* renamed from: id */
    long mo11531id();

    Double lat();

    Double lng();

    ArrayList<String> messages();

    Long radius();

    boolean should_takeover();

    AirDateTime starts_at();

    long status();

    Long trigger_type();
}
