package com.airbnb.android.itinerary.data.models;

import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.itinerary.PlaceReservationModel;
import com.airbnb.android.itinerary.PlaceReservationModel.Delete_place_reservation_by_id;
import com.airbnb.android.itinerary.PlaceReservationModel.Factory;
import com.airbnb.android.itinerary.PlaceReservationModel.Insert_place_reservation;
import com.airbnb.android.itinerary.PlaceReservationModel.Update_place_reservation;
import com.airbnb.android.itinerary.data.models.BaseReservationObject.ReservationObjectType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.RowMapper;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class PlaceReservationObject implements PlaceReservationModel, BaseReservationObject {
    public static final Factory<PlaceReservationObject> FACTORY = new Factory<>(PlaceReservationObject$$Lambda$1.lambdaFactory$());
    public static final RowMapper<PlaceReservationObject> MAPPER = FACTORY.select_allMapper();

    public static abstract class Builder {
        public abstract PlaceReservationObject build();

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo10317id(String str);

        @JsonProperty
        public abstract Builder reservation(String str);
    }

    @JsonProperty
    /* renamed from: id */
    public abstract String mo10312id();

    @JsonProperty
    public abstract String reservation();

    public abstract Builder toBuilder();

    public Insert_place_reservation getInsertStatement(SQLiteDatabase database) {
        Insert_place_reservation insert = new Insert_place_reservation(database);
        insert.bind(mo10312id(), reservation());
        return insert;
    }

    public Update_place_reservation getUpdateStatement(SQLiteDatabase database) {
        Update_place_reservation update = new Update_place_reservation(database);
        update.bind(reservation(), mo10312id());
        return update;
    }

    public Delete_place_reservation_by_id getDeleteStatement(SQLiteDatabase database) {
        Delete_place_reservation_by_id delete = new Delete_place_reservation_by_id(database);
        delete.bind(mo10312id());
        return delete;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return mo10312id().equals(((PlaceReservationObject) o).mo10312id());
    }

    public int hashCode() {
        return mo10312id().hashCode();
    }

    public String getReservation() {
        return reservation();
    }

    public ReservationObjectType getReservationObjectType() {
        return ReservationObjectType.PLACE;
    }
}
