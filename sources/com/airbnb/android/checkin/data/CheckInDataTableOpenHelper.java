package com.airbnb.android.checkin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.checkin.CheckInGuideDataModel;
import com.airbnb.android.checkin.CheckInGuideDataModel.Delete_guide_by_id;
import com.airbnb.android.checkin.CheckInGuideDataModel.Insert_guide;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.utils.IOUtils;
import com.google.common.collect.ImmutableList;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite.Builder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.schedulers.Schedulers;

public class CheckInDataTableOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "check_in_guide_store.db";
    private static final int VERSION = 1;
    private final BriteDatabase database = new Builder().build().wrapDatabaseHelper(this, Schedulers.m4048io());

    public CheckInDataTableOpenHelper(Context context) {
        super(context, FILE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CheckInGuideDataModel.DROP_TABLE);
        db.execSQL(CheckInGuideDataModel.CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void clearAll() {
        this.database.delete(CheckInGuideDataModel.TABLE_NAME, null, new String[0]);
    }

    public List<CheckInGuideData> getAllGuides() {
        Cursor cursor = null;
        try {
            List<CheckInGuideData> guides = new ArrayList<>();
            cursor = this.database.query(CheckInGuideDataModel.SELECT_ALL, new String[0]);
            while (cursor.moveToNext()) {
                guides.add(CheckInGuideData.MAPPER.map(cursor));
            }
            return ImmutableList.copyOf((Collection<? extends E>) guides);
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    public CheckInGuideData getCheckInGuideDataForId(long listingId) {
        Cursor cursor = null;
        CheckInGuideData message = null;
        try {
            cursor = this.database.query(CheckInGuideDataModel.SELECT_GUIDE_BY_ID, Long.toString(listingId));
            if (cursor.moveToNext()) {
                message = (CheckInGuideData) CheckInGuideData.MAPPER.map(cursor);
            }
            return message;
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    public void insertCheckInGuide(CheckInGuide checkinGuide) {
        insertCheckInGuide(CheckInGuideData.create(checkinGuide.getListingId(), checkinGuide.getUpdatedAt(), checkinGuide));
    }

    public void insertCheckInGuide(CheckInGuideData checkInGuideData) {
        Insert_guide insert = new Insert_guide(getWritableDatabase(), CheckInGuideData.FACTORY);
        insert.bind(checkInGuideData.listing_id(), checkInGuideData.updated_at(), checkInGuideData.check_in_guide());
        insert.program.executeInsert();
    }

    public boolean deleteGuide(long listingId) {
        Delete_guide_by_id delete = new Delete_guide_by_id(getWritableDatabase());
        delete.bind(listingId);
        return delete.program.executeUpdateDelete() > 0;
    }
}
