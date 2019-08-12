package com.airbnb.android.itinerary.data.models;

import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.itinerary.HomeReservationModel;
import com.airbnb.android.itinerary.HomeReservationModel.Delete_home_reservation_by_id;
import com.airbnb.android.itinerary.HomeReservationModel.Factory;
import com.airbnb.android.itinerary.HomeReservationModel.Insert_home_reservation;
import com.airbnb.android.itinerary.HomeReservationModel.Update_home_reservation;
import com.airbnb.android.itinerary.data.models.BaseReservationObject.ReservationObjectType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.RowMapper;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class HomeReservationObject implements HomeReservationModel, BaseReservationObject {
    public static final Factory<HomeReservationObject> FACTORY = new Factory<>(HomeReservationObject$$Lambda$1.lambdaFactory$());
    public static final RowMapper<HomeReservationObject> MAPPER = FACTORY.select_allMapper();

    public static abstract class Builder {
        public abstract HomeReservationObject build();

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo10310id(String str);

        @JsonProperty
        public abstract Builder reservation(String str);
    }

    @JsonProperty
    /* renamed from: id */
    public abstract String mo10305id();

    @JsonProperty
    public abstract String reservation();

    public abstract Builder toBuilder();

    public Insert_home_reservation getInsertStatement(SQLiteDatabase database) {
        Insert_home_reservation insert = new Insert_home_reservation(database);
        insert.bind(mo10305id(), reservation());
        return insert;
    }

    public Update_home_reservation getUpdateStatement(SQLiteDatabase database) {
        Update_home_reservation update = new Update_home_reservation(database);
        update.bind(reservation(), mo10305id());
        return update;
    }

    public Delete_home_reservation_by_id getDeleteStatement(SQLiteDatabase database) {
        Delete_home_reservation_by_id delete = new Delete_home_reservation_by_id(database);
        delete.bind(mo10305id());
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
        return mo10305id().equals(((HomeReservationObject) o).mo10305id());
    }

    public int hashCode() {
        return mo10305id().hashCode();
    }

    public String getReservation() {
        return reservation();
    }

    public ReservationObjectType getReservationObjectType() {
        return ReservationObjectType.HOME;
    }
}
