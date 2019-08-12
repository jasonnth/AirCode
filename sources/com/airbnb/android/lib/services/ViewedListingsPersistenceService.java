package com.airbnb.android.lib.services;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Parcelable;
import com.airbnb.android.core.contentproviders.ViewedListing;
import com.airbnb.android.core.contentproviders.ViewedListingsDatabaseHelper;
import com.airbnb.android.core.services.ViewedListingsPersistenceServiceIntents;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;

public class ViewedListingsPersistenceService extends IntentService {
    public static final long VIEWED_LISTINGS_PURGE_INTERVAL_IN_MS = 172800000;
    ViewedListingsDatabaseHelper dbHelper;

    public ViewedListingsPersistenceService() {
        super("ViewedListingsPersistingService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        SQLiteDatabase database = this.dbHelper.getDatabase();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(ViewedListingsPersistenceServiceIntents.VIEWED_LISTINGS_EXTRA_KEY);
        ViewedListing[] listingIds = new ViewedListing[parcelables.length];
        System.arraycopy(parcelables, 0, listingIds, 0, parcelables.length);
        try {
            database.beginTransaction();
            SQLiteStatement insert = database.compileStatement("Insert OR Replace into map_viewed_listings (listing_id, viewed_at)  values (?, ?)");
            for (ViewedListing viewedListing : listingIds) {
                insert.bindString(1, String.valueOf(viewedListing.getListingId()));
                insert.bindString(2, String.valueOf(viewedListing.getTimestamp()));
                insert.execute();
            }
            database.setTransactionSuccessful();
            database.execSQL("DELETE FROM map_viewed_listings WHERE viewed_at <= " + (System.currentTimeMillis() - VIEWED_LISTINGS_PURGE_INTERVAL_IN_MS));
        } finally {
            database.endTransaction();
            this.dbHelper.releaseDbReference();
        }
    }
}
