package com.airbnb.android.itinerary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.itinerary.ExperienceReservationModel;
import com.airbnb.android.itinerary.HomeReservationModel;
import com.airbnb.android.itinerary.PlaceReservationModel;
import com.airbnb.android.itinerary.TimelineTripModel;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.android.itinerary.TripEventModel.Delete_all_by_trip;
import com.airbnb.android.itinerary.data.models.BaseReservationObject;
import com.airbnb.android.itinerary.data.models.BaseReservationObject.ReservationObjectType;
import com.airbnb.android.itinerary.data.models.ExperienceReservationObject;
import com.airbnb.android.itinerary.data.models.HomeReservationObject;
import com.airbnb.android.itinerary.data.models.PlaceReservationObject;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventCardType;
import com.airbnb.android.utils.IOUtils;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.squareup.sqldelight.RowMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import p032rx.schedulers.Schedulers;

public class ItineraryTableOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "itinerary.db";
    private static final String TAG = "ItineraryTableHelper";
    private static final int VERSION = 11;
    private final BriteDatabase database = SqlBrite.create().wrapDatabaseHelper(this, Schedulers.m4048io());

    public ItineraryTableOpenHelper(Context context) {
        super(context, FILE_NAME, null, 11);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TimelineTripModel.DROP_TABLE);
        db.execSQL(TripEventModel.DROP_TABLE);
        db.execSQL(HomeReservationModel.DROP_TABLE);
        db.execSQL(ExperienceReservationModel.DROP_TABLE);
        db.execSQL(PlaceReservationModel.DROP_TABLE);
        db.execSQL(TimelineTripModel.CREATE_TABLE);
        db.execSQL(TripEventModel.CREATE_TABLE);
        db.execSQL(HomeReservationModel.CREATE_TABLE);
        db.execSQL(ExperienceReservationModel.CREATE_TABLE);
        db.execSQL(PlaceReservationModel.CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void clearAll() {
        synchronized (this.database) {
            this.database.delete(TimelineTripModel.TABLE_NAME, null, new String[0]);
            this.database.delete(TripEventModel.TABLE_NAME, null, new String[0]);
            this.database.delete(HomeReservationModel.TABLE_NAME, null, new String[0]);
            this.database.delete(ExperienceReservationModel.TABLE_NAME, null, new String[0]);
            this.database.delete(PlaceReservationModel.TABLE_NAME, null, new String[0]);
        }
    }

    public int insertTimelineTrips(TimelineTrip... models) {
        return insertTimelineTrips(Arrays.asList(models));
    }

    public int insertTimelineTrips(List<TimelineTrip> models) {
        return FluentIterable.from((Iterable<E>) models).filter(ItineraryTableOpenHelper$$Lambda$1.lambdaFactory$(this)).toList().size();
    }

    /* access modifiers changed from: private */
    public boolean insertTimelineTrip(TimelineTrip model) {
        insertTripEvents((List<TripEvent>) model.trip_schedule_cards());
        return model.getInsertStatement(getWritableDatabase()).program.executeInsert() > 0;
    }

    public int insertTripEvents(TripEvent... models) {
        return insertTripEvents(Arrays.asList(models));
    }

    public int insertTripEvents(List<TripEvent> models) {
        return FluentIterable.from((Iterable<E>) models).filter(ItineraryTableOpenHelper$$Lambda$2.lambdaFactory$(this)).toList().size();
    }

    /* access modifiers changed from: private */
    public boolean insertTripEvent(TripEvent model) {
        if (TripEventCardType.Freetime.equals(model.card_type()) || model.getInsertStatement(getWritableDatabase()).program.executeInsert() <= 0) {
            return false;
        }
        return true;
    }

    public int insertHomeReservationObject(HomeReservationObject model) {
        return (int) model.getInsertStatement(getWritableDatabase()).program.executeInsert();
    }

    public int insertExperienceReservationObject(ExperienceReservationObject model) {
        return (int) model.getInsertStatement(getWritableDatabase()).program.executeInsert();
    }

    public int insertPlaceReservationObject(PlaceReservationObject model) {
        return (int) model.getInsertStatement(getWritableDatabase()).program.executeInsert();
    }

    public List<TimelineTrip> getAllTimelineTrips() {
        return getTimelineTripList(TimelineTripModel.SELECT_ALL, TimelineTrip.MAPPER, new String[0]);
    }

    public TimelineTrip getTimelineTripByConfirmationCode(String confirmationCode) {
        List<TimelineTrip> list = getTimelineTripList(TimelineTripModel.SELECT_TIMELINE_TRIP_BY_ID, TimelineTrip.MAPPER, confirmationCode);
        if (ListUtils.isEmpty((Collection<?>) list) || list.size() > 1) {
            return null;
        }
        return (TimelineTrip) list.get(0);
    }

    public List<String> getAllTimelineTripConfirmationCodes() {
        List<String> items = new ArrayList<>();
        try {
            Cursor cursor = this.database.query(TimelineTripModel.SELECT_ALL_CONFIRMATION_CODES, new String[0]);
            while (cursor.moveToNext()) {
                items.add(TimelineTrip.CONFIRMATION_CODES_MAPPER.map(cursor));
            }
            IOUtils.closeQuietly(cursor);
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
        }
        return items;
    }

    public TripEvent getTripEventById(long id) {
        return getTripEvent(TripEventModel.SELECT_TRIP_EVENT_BY_ID, Long.toString(id));
    }

    public TripEvent getTripEventByPrimaryKey(String primaryKey) {
        return getTripEvent(TripEventModel.SELECT_TRIP_EVENT_BY_PRIMARY_KEY, primaryKey);
    }

    public TripEvent getTripEventByTripConfirmationCodeAndCardType(String confirmationCode, TripEventCardType cardType) {
        return getTripEvent(TripEventModel.SELECT_TRIP_EVENT_BY_CARD_TYPE_AND_CONFIRMATION_CODE, confirmationCode, cardType.getKey());
    }

    public List<TripEvent> getAllTripEvents() {
        return getTripEventList(TripEventModel.SELECT_ALL, TripEvent.MAPPER, new String[0]);
    }

    public List<TripEvent> getTripEventsByTripConfirmationCode(String confirmationCode) {
        return getTripEventList(TripEventModel.SELECT_TRIP_EVENT_BY_TRIP, TripEvent.MAPPER, confirmationCode);
    }

    private List<TimelineTrip> getTimelineTripList(String query, RowMapper<TimelineTrip> mapper, String... args) {
        List<TimelineTrip> items = new ArrayList<>();
        try {
            Cursor cursor = this.database.query(query, args);
            while (cursor.moveToNext()) {
                items.add(mapper.map(cursor));
            }
            IOUtils.closeQuietly(cursor);
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
        }
        return items;
    }

    private List<TripEvent> getTripEventList(String query, RowMapper<TripEvent> mapper, String... args) {
        List<TripEvent> items = new ArrayList<>();
        try {
            Cursor cursor = this.database.query(query, args);
            while (cursor.moveToNext()) {
                items.add(mapper.map(cursor));
            }
            IOUtils.closeQuietly(cursor);
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
        }
        return items;
    }

    private TripEvent getTripEvent(String query, String... args) {
        List<TripEvent> list = getTripEventList(query, TripEvent.MAPPER, args);
        if (ListUtils.isEmpty((Collection<?>) list) || list.size() > 1) {
            return null;
        }
        return (TripEvent) list.get(0);
    }

    public HomeReservationObject getHomeReservationObject(String id) {
        return (HomeReservationObject) getReservationObject(HomeReservationModel.SELECT_HOME_RESERVATION_BY_ID, id, ReservationObjectType.HOME);
    }

    public ExperienceReservationObject getExperienceReservationObject(String id) {
        return (ExperienceReservationObject) getReservationObject(ExperienceReservationModel.SELECT_EXPERIENCE_RESERVATION_BY_ID, id, ReservationObjectType.EXPERIENCE);
    }

    public PlaceReservationObject getPlaceReservationObject(String id) {
        return (PlaceReservationObject) getReservationObject(PlaceReservationModel.SELECT_PLACE_RESERVATION_BY_ID, id, ReservationObjectType.PLACE);
    }

    private BaseReservationObject getReservationObject(String query, String id, ReservationObjectType type) {
        List<BaseReservationObject> items = new ArrayList<>();
        try {
            Cursor cursor = this.database.query(query, id);
            while (cursor.moveToNext()) {
                BaseReservationObject reservationObject = mapReservationObject(type, cursor);
                if (reservationObject != null) {
                    items.add(reservationObject);
                }
            }
            IOUtils.closeQuietly(cursor);
            if (ListUtils.isEmpty((Collection<?>) items) || items.size() > 1) {
                return null;
            }
            return (BaseReservationObject) items.get(0);
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
            if (ListUtils.isEmpty((Collection<?>) items) || items.size() > 1) {
                return null;
            }
            return (BaseReservationObject) items.get(0);
        }
    }

    private BaseReservationObject mapReservationObject(ReservationObjectType type, Cursor cursor) {
        switch (type) {
            case HOME:
                return (BaseReservationObject) HomeReservationObject.MAPPER.map(cursor);
            case EXPERIENCE:
                return (BaseReservationObject) ExperienceReservationObject.MAPPER.map(cursor);
            case PLACE:
                return (BaseReservationObject) PlaceReservationObject.MAPPER.map(cursor);
            default:
                return null;
        }
    }

    public boolean updateTimelineTrip(TimelineTrip timelineTrip) {
        return timelineTrip.getUpdateStatement(getWritableDatabase()).program.executeUpdateDelete() > 0;
    }

    public boolean deleteTimelineTrip(String confirmationCode) {
        TimelineTrip timelineTrip = getTimelineTripByConfirmationCode(confirmationCode);
        if (timelineTrip != null) {
            return deleteTimelineTrip(timelineTrip);
        }
        return false;
    }

    public boolean deleteTimelineTrip(TimelineTrip timelineTrip) {
        deleteAllTripEventsForConfirmationCode(timelineTrip.confirmation_code());
        return timelineTrip.getDeleteStatement(getWritableDatabase()).program.executeUpdateDelete() > 0;
    }

    public boolean deleteTripEvent(long id) {
        TripEvent tripEvent = getTripEventById(id);
        if (tripEvent == null || !deleteTripEventFromTimelineTrip(tripEvent) || tripEvent.getDeleteStatement(getWritableDatabase()).program.executeUpdateDelete() <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteAllTripEventsForConfirmationCode(String confirmationCode) {
        Delete_all_by_trip delete = new Delete_all_by_trip(getWritableDatabase());
        delete.bind(confirmationCode);
        return delete.program.executeUpdateDelete() > 0;
    }

    private boolean deleteTripEventFromTimelineTrip(TripEvent tripEvent) {
        TimelineTrip timelineTrip = getTimelineTripByConfirmationCode(tripEvent.schedule_confirmation_code());
        if (timelineTrip != null) {
            List<TripEvent> tripEvents = timelineTrip.trip_schedule_cards();
            if (!ListUtils.isEmpty((Collection<?>) tripEvents)) {
                tripEvents.remove(tripEvent);
                if (ListUtils.isEmpty((Collection<?>) tripEvents)) {
                    return deleteTimelineTrip(timelineTrip);
                }
                return updateTimelineTrip(timelineTrip.toBuilder().trip_schedule_cards(Lists.newArrayList((Iterable<? extends E>) tripEvents)).build());
            }
        }
        return false;
    }
}
