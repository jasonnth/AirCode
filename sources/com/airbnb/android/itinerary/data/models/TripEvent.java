package com.airbnb.android.itinerary.data.models;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.column_adapters.AirDateTimeColumnAdapter;
import com.airbnb.android.core.column_adapters.JsonColumnAdapter;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.android.itinerary.TripEventModel.Delete_trip_event_by_primary_key;
import com.airbnb.android.itinerary.TripEventModel.Factory;
import com.airbnb.android.itinerary.TripEventModel.Insert_trip_event;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class TripEvent implements TripEventModel, BaseItineraryItem {
    private static final String BUNDLE_ARG = "trip_event";
    public static final Factory<TripEvent> FACTORY = new Factory<>(TripEvent$$Lambda$1.lambdaFactory$(), TripEventCardTypeColumnAdapter.INSTANCE, AirDateTimeColumnAdapter.INSTANCE, AirDateTimeColumnAdapter.INSTANCE, TRIP_EVENT_SECONDARY_ACTIONS_LIST_ADAPTER);
    public static final RowMapper<TripEvent> MAPPER = FACTORY.select_allMapper();
    private static final JsonColumnAdapter<ArrayList<TripEventSecondaryAction>> TRIP_EVENT_SECONDARY_ACTIONS_LIST_ADAPTER = new JsonColumnAdapter<>(new TypeReference<ArrayList<TripEventSecondaryAction>>() {
    }.getType());

    public static abstract class Builder {
        public abstract TripEvent build();

        @JsonProperty
        public abstract Builder card_subtitle(String str);

        @JsonProperty
        public abstract Builder card_title(String str);

        @JsonProperty
        public abstract Builder card_type(TripEventCardType tripEventCardType);

        @JsonProperty
        public abstract Builder category(String str);

        @JsonProperty
        public abstract Builder confirmation_code(String str);

        @JsonProperty
        public abstract Builder ends_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder header(String str);

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo10371id(Long l);

        @JsonProperty
        public abstract Builder picture(String str);

        @JsonProperty
        public abstract Builder primary_key(String str);

        @JsonProperty
        public abstract Builder schedule_confirmation_code(String str);

        @JsonProperty
        public abstract Builder secondary_actions(ArrayList<TripEventSecondaryAction> arrayList);

        @JsonProperty
        public abstract Builder starts_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder time_zone(String str);
    }

    public static class TripEventCardTypeColumnAdapter implements ColumnAdapter<TripEventCardType, String> {
        public static final TripEventCardTypeColumnAdapter INSTANCE = new TripEventCardTypeColumnAdapter();

        public TripEventCardType decode(String databaseValue) {
            return TripEventCardType.from(databaseValue);
        }

        public String encode(TripEventCardType value) {
            return value.getKey();
        }
    }

    @JsonProperty
    public abstract String card_subtitle();

    @JsonProperty
    public abstract String card_title();

    @JsonProperty
    public abstract TripEventCardType card_type();

    @JsonProperty
    public abstract String category();

    @JsonProperty
    public abstract String confirmation_code();

    @JsonProperty
    public abstract AirDateTime ends_at();

    @JsonProperty
    public abstract String header();

    @JsonProperty
    /* renamed from: id */
    public abstract Long mo10245id();

    @JsonProperty
    public abstract String picture();

    @JsonProperty
    public abstract String primary_key();

    @JsonProperty
    public abstract String schedule_confirmation_code();

    @JsonProperty
    public abstract ArrayList<TripEventSecondaryAction> secondary_actions();

    @JsonProperty
    public abstract AirDateTime starts_at();

    @JsonProperty
    public abstract String time_zone();

    public abstract Builder toBuilder();

    public Insert_trip_event getInsertStatement(SQLiteDatabase database) {
        Insert_trip_event insert = new Insert_trip_event(database, FACTORY);
        insert.bind(primary_key(), schedule_confirmation_code(), mo10245id(), card_type(), category(), confirmation_code(), picture(), starts_at(), ends_at(), time_zone(), header(), card_title(), card_subtitle(), secondary_actions());
        return insert;
    }

    public Delete_trip_event_by_primary_key getDeleteStatement(SQLiteDatabase database) {
        Delete_trip_event_by_primary_key delete = new Delete_trip_event_by_primary_key(database);
        delete.bind(primary_key());
        return delete;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isHomeCard() {
        return !TextUtils.isEmpty(confirmation_code());
    }

    public boolean isCheckinCard() {
        return TripEventCardType.Checkin.equals(card_type());
    }

    public boolean isCheckoutCard() {
        return TripEventCardType.Checkout.equals(card_type());
    }

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
        return primary_key();
    }

    public TripEventSecondaryAction getMainSecondaryAction() {
        if (ListUtils.isEmpty((Collection<?>) secondary_actions())) {
            return null;
        }
        Iterator it = secondary_actions().iterator();
        while (it.hasNext()) {
            TripEventSecondaryAction secondaryAction = (TripEventSecondaryAction) it.next();
            if (SecondaryActionType.Deeplink.equals(secondaryAction.type())) {
                return secondaryAction;
            }
            if (SecondaryActionType.Map.equals(secondaryAction.type()) && !ItineraryUtils.shouldHideSecondaryAction(this)) {
                return secondaryAction;
            }
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return primary_key().equals(((TripEvent) o).primary_key());
    }

    public int hashCode() {
        return primary_key().hashCode();
    }
}
