package com.airbnb.android.itinerary.data.models;

import android.database.sqlite.SQLiteDatabase;
import com.airbnb.android.itinerary.ExperienceReservationModel;
import com.airbnb.android.itinerary.ExperienceReservationModel.Delete_experience_reservation_by_id;
import com.airbnb.android.itinerary.ExperienceReservationModel.Factory;
import com.airbnb.android.itinerary.ExperienceReservationModel.Insert_experience_reservation;
import com.airbnb.android.itinerary.ExperienceReservationModel.Update_experience_reservation;
import com.airbnb.android.itinerary.data.models.BaseReservationObject.ReservationObjectType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.RowMapper;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class ExperienceReservationObject implements ExperienceReservationModel, BaseReservationObject {
    public static final Factory<ExperienceReservationObject> FACTORY = new Factory<>(ExperienceReservationObject$$Lambda$1.lambdaFactory$());
    public static final RowMapper<ExperienceReservationObject> MAPPER = FACTORY.select_allMapper();

    public static abstract class Builder {
        public abstract ExperienceReservationObject build();

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo10303id(String str);

        @JsonProperty
        public abstract Builder reservation(String str);
    }

    @JsonProperty
    /* renamed from: id */
    public abstract String mo10298id();

    @JsonProperty
    public abstract String reservation();

    public abstract Builder toBuilder();

    public Insert_experience_reservation getInsertStatement(SQLiteDatabase database) {
        Insert_experience_reservation insert = new Insert_experience_reservation(database);
        insert.bind(mo10298id(), reservation());
        return insert;
    }

    public Update_experience_reservation getUpdateStatement(SQLiteDatabase database) {
        Update_experience_reservation update = new Update_experience_reservation(database);
        update.bind(reservation(), mo10298id());
        return update;
    }

    public Delete_experience_reservation_by_id getDeleteStatement(SQLiteDatabase database) {
        Delete_experience_reservation_by_id delete = new Delete_experience_reservation_by_id(database);
        delete.bind(mo10298id());
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
        return mo10298id().equals(((ExperienceReservationObject) o).mo10298id());
    }

    public int hashCode() {
        return mo10298id().hashCode();
    }

    public String getReservation() {
        return reservation();
    }

    public ReservationObjectType getReservationObjectType() {
        return ReservationObjectType.EXPERIENCE;
    }
}
