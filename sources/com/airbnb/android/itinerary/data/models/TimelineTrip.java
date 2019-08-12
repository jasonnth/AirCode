package com.airbnb.android.itinerary.data.models;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.column_adapters.AirDateTimeColumnAdapter;
import com.airbnb.android.core.column_adapters.JsonColumnAdapter;
import com.airbnb.android.itinerary.TimelineTripModel;
import com.airbnb.android.itinerary.TimelineTripModel.Delete_timeline_trip_by_id;
import com.airbnb.android.itinerary.TimelineTripModel.Factory;
import com.airbnb.android.itinerary.TimelineTripModel.Insert_timeline_trip;
import com.airbnb.android.itinerary.TimelineTripModel.Update_timeline_trip;
import com.airbnb.android.utils.BundleBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.RowMapper;
import java.util.ArrayList;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class TimelineTrip implements TimelineTripModel, BaseItineraryItem {
    private static final String BUNDLE_ARG = "timeline_trip";
    public static final RowMapper<String> CONFIRMATION_CODES_MAPPER = FACTORY.select_all_confirmation_codesMapper();
    public static final Factory<TimelineTrip> FACTORY = new Factory<>(TimelineTrip$$Lambda$1.lambdaFactory$(), AirDateTimeColumnAdapter.INSTANCE, AirDateTimeColumnAdapter.INSTANCE, AirDateTimeColumnAdapter.INSTANCE, STRING_ARRAY_LIST_ADAPTER, TRIP_EVENT_LIST_ADAPTER);
    public static final RowMapper<TimelineTrip> MAPPER = FACTORY.select_allMapper();
    private static final JsonColumnAdapter<ArrayList<String>> STRING_ARRAY_LIST_ADAPTER = new JsonColumnAdapter<>(new TypeReference<ArrayList<String>>() {
    }.getType());
    private static final JsonColumnAdapter<ArrayList<TripEvent>> TRIP_EVENT_LIST_ADAPTER = new JsonColumnAdapter<>(new TypeReference<ArrayList<TripEvent>>() {
    }.getType());

    public static abstract class Builder {
        public abstract TimelineTrip build();

        @JsonProperty
        public abstract Builder bundle_photo_urls(ArrayList<String> arrayList);

        @JsonProperty
        public abstract Builder bundle_subtitle(String str);

        @JsonProperty
        public abstract Builder bundle_title(String str);

        @JsonProperty
        public abstract Builder confirmation_code(String str);

        @JsonProperty
        public abstract Builder ends_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder expires_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder pending_type(String str);

        @JsonProperty
        public abstract Builder picture(String str);

        @JsonProperty
        public abstract Builder should_bundle(Boolean bool);

        @JsonProperty
        public abstract Builder starts_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder time_zone(String str);

        @JsonProperty
        public abstract Builder title(String str);

        @JsonProperty
        public abstract Builder trip_schedule_cards(ArrayList<TripEvent> arrayList);
    }

    @JsonProperty
    public abstract ArrayList<String> bundle_photo_urls();

    @JsonProperty
    public abstract String bundle_subtitle();

    @JsonProperty
    public abstract String bundle_title();

    @JsonProperty
    public abstract String confirmation_code();

    @JsonProperty
    public abstract AirDateTime ends_at();

    @JsonProperty
    public abstract AirDateTime expires_at();

    @JsonProperty
    public abstract String pending_type();

    @JsonProperty
    public abstract String picture();

    @JsonProperty
    public abstract Boolean should_bundle();

    @JsonProperty
    public abstract AirDateTime starts_at();

    @JsonProperty
    public abstract String time_zone();

    @JsonProperty
    public abstract String title();

    public abstract Builder toBuilder();

    @JsonProperty
    public abstract ArrayList<TripEvent> trip_schedule_cards();

    public AirDateTime getStartsAt() {
        return starts_at().withZone(time_zone());
    }

    public AirDateTime getEndsAt() {
        if (ends_at() != null) {
            return ends_at().withZone(time_zone());
        }
        return null;
    }

    public String getId() {
        return confirmation_code();
    }

    public Insert_timeline_trip getInsertStatement(SQLiteDatabase database) {
        Insert_timeline_trip insert = new Insert_timeline_trip(database, FACTORY);
        insert.bind(confirmation_code(), starts_at(), ends_at(), expires_at(), time_zone(), title(), bundle_title(), bundle_subtitle(), bundle_photo_urls(), picture(), pending_type(), trip_schedule_cards(), should_bundle());
        return insert;
    }

    public Update_timeline_trip getUpdateStatement(SQLiteDatabase database) {
        Update_timeline_trip update = new Update_timeline_trip(database, FACTORY);
        update.bind(starts_at(), ends_at(), expires_at(), time_zone(), title(), bundle_title(), bundle_subtitle(), bundle_photo_urls(), picture(), pending_type(), trip_schedule_cards(), should_bundle(), confirmation_code());
        return update;
    }

    public Delete_timeline_trip_by_id getDeleteStatement(SQLiteDatabase database) {
        Delete_timeline_trip_by_id delete = new Delete_timeline_trip_by_id(database);
        delete.bind(confirmation_code());
        return delete;
    }

    public Bundle toBundle() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("confirmationCode", confirmation_code())).putString("pendingType", pending_type())).putString("title", title())).putString("startsAt", starts_at() == null ? "" : starts_at().getIsoDateStringUTC())).putString("endsAt", ends_at() == null ? "" : ends_at().getIsoDateStringUTC())).putString("expiresAt", expires_at() == null ? "" : expires_at().getIsoDateStringUTC())).putString("timezone", time_zone())).putString("picture", picture())).toBundle();
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
        return confirmation_code().equals(((TimelineTrip) o).confirmation_code());
    }

    public int hashCode() {
        return confirmation_code().hashCode();
    }
}
