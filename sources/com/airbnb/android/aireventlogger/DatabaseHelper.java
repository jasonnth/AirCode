package com.airbnb.android.aireventlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AirEventLogger";
    private static final int DATABASE_VERSION = 3;
    private static DatabaseHelper instance;
    private List<TableHandler> tableHandlers = new ArrayList();

    static DatabaseHelper getInstanceAndRegister(Context context, TableHandler handler) {
        return getInstance(context).registerHandler(handler);
    }

    static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper registerHandler(TableHandler handler) {
        this.tableHandlers.add(handler);
        return this;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    public void onOpen(SQLiteDatabase db) {
        for (TableHandler tableHandler : this.tableHandlers) {
            tableHandler.onOpen(db);
        }
    }

    public void onCreate(SQLiteDatabase db) {
        for (TableHandler tableHandler : this.tableHandlers) {
            tableHandler.onCreate(db);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (TableHandler tableHandler : this.tableHandlers) {
            tableHandler.onUpgrade(db, oldVersion, newVersion);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (TableHandler tableHandler : this.tableHandlers) {
            tableHandler.onDowngrade(db, oldVersion, newVersion);
        }
    }
}
