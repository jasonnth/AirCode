package com.airbnb.android.erf.p010db;

import android.content.ContentValues;
import android.database.Cursor;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.android.erf.db.ErfExperimentsModel */
public interface ErfExperimentsModel {
    public static final String ASSIGNEDTREATMENT = "assignedTreatment";
    public static final String CREATE_TABLE = "CREATE TABLE erf_experiments (\n  experimentName TEXT NOT NULL PRIMARY KEY,\n  assignedTreatment TEXT NOT NULL,\n  subject TEXT NOT NULL,\n  version INTEGER NOT NULL,\n  treatments TEXT NOT NULL,\n  timestamp INTEGER NOT NULL,\n  holdoutExperimentName TEXT\n)";
    public static final String DELETE_ALL = "DELETE FROM erf_experiments";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS erf_experiments";
    public static final String EXPERIMENTNAME = "experimentName";
    public static final String HOLDOUTEXPERIMENTNAME = "holdoutExperimentName";
    public static final String SELECT_ALL = "SELECT *\nFROM erf_experiments";
    public static final String SELECT_BY_NAME = "SELECT *\nFROM erf_experiments\nWHERE experimentName = ?";
    public static final String SUBJECT = "subject";
    public static final String TABLE_NAME = "erf_experiments";
    public static final String TIMESTAMP = "timestamp";
    public static final String TREATMENTS = "treatments";
    public static final String VERSION = "version";

    /* renamed from: com.airbnb.android.erf.db.ErfExperimentsModel$Factory */
    public static final class Factory<T extends ErfExperimentsModel> {
        public final Creator<T> creator;

        public Factory(Creator<T> creator2) {
            this.creator = creator2;
        }

        @Deprecated
        public Marshal marshal() {
            return new Marshal(null);
        }

        @Deprecated
        public Marshal marshal(ErfExperimentsModel copy) {
            return new Marshal(copy);
        }

        public SqlDelightStatement select_by_name(String experimentName) {
            List<String> args = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            query.append("SELECT *\nFROM erf_experiments\nWHERE experimentName = ");
            int i = 1 + 1;
            query.append('?').append(1);
            args.add(experimentName);
            return new SqlDelightStatement(query.toString(), (String[]) args.toArray(new String[args.size()]), Collections.singleton(ErfExperimentsModel.TABLE_NAME));
        }

        public Mapper<T> select_by_nameMapper() {
            return new Mapper<>(this);
        }

        public Mapper<T> select_allMapper() {
            return new Mapper<>(this);
        }
    }

    /* renamed from: com.airbnb.android.erf.db.ErfExperimentsModel$Creator */
    public interface Creator<T extends ErfExperimentsModel> {
        T create(String str, String str2, String str3, long j, String str4, long j2, String str5);
    }

    /* renamed from: com.airbnb.android.erf.db.ErfExperimentsModel$Mapper */
    public static final class Mapper<T extends ErfExperimentsModel> implements RowMapper<T> {
        private final Factory<T> erfExperimentsModelFactory;

        public Mapper(Factory<T> erfExperimentsModelFactory2) {
            this.erfExperimentsModelFactory = erfExperimentsModelFactory2;
        }

        public T map(Cursor cursor) {
            String string;
            Creator<T> creator = this.erfExperimentsModelFactory.creator;
            String string2 = cursor.getString(0);
            String string3 = cursor.getString(1);
            String string4 = cursor.getString(2);
            long j = cursor.getLong(3);
            String string5 = cursor.getString(4);
            long j2 = cursor.getLong(5);
            if (cursor.isNull(6)) {
                string = null;
            } else {
                string = cursor.getString(6);
            }
            return creator.create(string2, string3, string4, j, string5, j2, string);
        }
    }

    /* renamed from: com.airbnb.android.erf.db.ErfExperimentsModel$Marshal */
    public static final class Marshal {
        protected final ContentValues contentValues = new ContentValues();

        Marshal(ErfExperimentsModel copy) {
            if (copy != null) {
                experimentName(copy.experimentName());
                assignedTreatment(copy.assignedTreatment());
                subject(copy.subject());
                version(copy.version());
                treatments(copy.treatments());
                timestamp(copy.timestamp());
                holdoutExperimentName(copy.holdoutExperimentName());
            }
        }

        public ContentValues asContentValues() {
            return this.contentValues;
        }

        public Marshal experimentName(String experimentName) {
            this.contentValues.put(ErfExperimentsModel.EXPERIMENTNAME, experimentName);
            return this;
        }

        public Marshal assignedTreatment(String assignedTreatment) {
            this.contentValues.put(ErfExperimentsModel.ASSIGNEDTREATMENT, assignedTreatment);
            return this;
        }

        public Marshal subject(String subject) {
            this.contentValues.put(ErfExperimentsModel.SUBJECT, subject);
            return this;
        }

        public Marshal version(long version) {
            this.contentValues.put("version", Long.valueOf(version));
            return this;
        }

        public Marshal treatments(String treatments) {
            this.contentValues.put(ErfExperimentsModel.TREATMENTS, treatments);
            return this;
        }

        public Marshal timestamp(long timestamp) {
            this.contentValues.put(ErfExperimentsModel.TIMESTAMP, Long.valueOf(timestamp));
            return this;
        }

        public Marshal holdoutExperimentName(String holdoutExperimentName) {
            this.contentValues.put(ErfExperimentsModel.HOLDOUTEXPERIMENTNAME, holdoutExperimentName);
            return this;
        }
    }

    String assignedTreatment();

    String experimentName();

    String holdoutExperimentName();

    String subject();

    long timestamp();

    String treatments();

    long version();
}
