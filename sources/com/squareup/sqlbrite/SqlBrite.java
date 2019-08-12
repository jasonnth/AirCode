package com.squareup.sqlbrite;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import p032rx.Observable;
import p032rx.Observable.Transformer;
import p032rx.Scheduler;

public final class SqlBrite {
    static final Logger DEFAULT_LOGGER = new Logger() {
        public void log(String message) {
            Log.d("SqlBrite", message);
        }
    };
    static final Transformer<Object, Object> DEFAULT_TRANSFORMER = new Transformer<Object, Object>() {
        public Observable<Object> call(Observable<Object> queryObservable) {
            return queryObservable;
        }
    };
    private final Logger logger;
    private final Transformer<Object, Object> queryTransformer;

    public static final class Builder {
        private Logger logger = SqlBrite.DEFAULT_LOGGER;
        private Transformer<Object, Object> queryTransformer = SqlBrite.DEFAULT_TRANSFORMER;

        public SqlBrite build() {
            return new SqlBrite(this.logger, this.queryTransformer);
        }
    }

    public interface Logger {
        void log(String str);
    }

    @Deprecated
    public static SqlBrite create() {
        return new SqlBrite(DEFAULT_LOGGER, DEFAULT_TRANSFORMER);
    }

    SqlBrite(Logger logger2, Transformer<Object, Object> queryTransformer2) {
        this.logger = logger2;
        this.queryTransformer = queryTransformer2;
    }

    public BriteDatabase wrapDatabaseHelper(SQLiteOpenHelper helper, Scheduler scheduler) {
        return new BriteDatabase(helper, this.logger, scheduler, this.queryTransformer);
    }
}
