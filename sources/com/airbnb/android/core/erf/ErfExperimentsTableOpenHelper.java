package com.airbnb.android.core.erf;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.android.utils.IOUtils;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.BriteDatabase.Transaction;
import com.squareup.sqlbrite.SqlBrite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.schedulers.Schedulers;

public class ErfExperimentsTableOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "erf_experiments.db";
    private static final int VERSION = 2;
    private final BriteDatabase database = SqlBrite.create().wrapDatabaseHelper(this, Schedulers.m4048io());
    private final ErfExperimentFactory factory;

    public ErfExperimentsTableOpenHelper(Context context, ErfExperimentFactory factory2) {
        super(context, FILE_NAME, null, 2);
        this.factory = factory2;
    }

    public void onCreate(SQLiteDatabase db) {
        synchronized (this.database) {
            db.execSQL(ErfExperimentsModel.DROP_TABLE);
            db.execSQL(ErfExperimentsModel.CREATE_TABLE);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insert(ErfExperiment model) {
        synchronized (this.database) {
            this.database.insert(ErfExperimentsModel.TABLE_NAME, this.factory.marshal(model).asContentValues());
        }
    }

    public void insert(Collection<ErfExperiment> models) {
        synchronized (this.database) {
            Transaction transaction = this.database.newTransaction();
            try {
                for (ErfExperiment model : models) {
                    this.database.insert(ErfExperimentsModel.TABLE_NAME, this.factory.marshal(model).asContentValues());
                }
                transaction.markSuccessful();
                transaction.end();
            } catch (Throwable th) {
                transaction.end();
                throw th;
            }
        }
    }

    public List<ErfExperiment> getAllExperiments() {
        List<ErfExperiment> items;
        synchronized (this.database) {
            try {
                Cursor cursor = this.database.query(ErfExperimentsModel.SELECT_ALL, new String[0]);
                items = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    items.add(this.factory.map(cursor));
                }
                IOUtils.closeQuietly(cursor);
            } catch (Throwable th) {
                IOUtils.closeQuietly(null);
                throw th;
            }
        }
        return items;
    }

    public void clear() {
        synchronized (this.database) {
            this.database.execute(ErfExperimentsModel.DELETE_ALL);
        }
    }
}
