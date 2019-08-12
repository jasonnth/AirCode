package com.airbnb.android.core.contentproviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ViewedListingsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ViewedListingIdsDatabase";
    private static final int DATABASE_VERSION = 1;

    /* renamed from: db */
    private static SQLiteDatabase f8437db;

    public static final class ViewedListingsContract {
        public static final String COL_LISTING_ID = "listing_id";
        public static final String COL_VIEWED_AT = "viewed_at";
        public static final String CREATE_TABLE = "CREATE TABLE map_viewed_listings(listing_id LONG PRIMARY KEY NOT NULL,viewed_at LONG)";
        public static final String TABLE_NAME = "map_viewed_listings";
    }

    public ViewedListingsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ViewedListingsContract.CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS map_viewed_listings");
        onCreate(sqLiteDatabase);
    }

    public synchronized SQLiteDatabase getDatabase() {
        if (f8437db == null) {
            f8437db = super.getWritableDatabase();
        } else {
            f8437db.acquireReference();
        }
        return f8437db;
    }

    public synchronized void releaseDbReference() {
        f8437db.releaseReference();
        if (!f8437db.isOpen()) {
            f8437db = null;
        }
    }

    public SQLiteDatabase getReadableDatabase() {
        throw new UnsupportedOperationException("Call getDatabase() instead");
    }

    public SQLiteDatabase getWritableDatabase() {
        throw new UnsupportedOperationException("Call getDatabase() instead");
    }
}
