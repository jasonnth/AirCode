package com.airbnb.android.superhero;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.column_adapters.AirDateTimeColumnAdapter;
import com.airbnb.android.core.column_adapters.JsonColumnAdapter;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.superhero.SuperHeroMessageModel.Delete_message;
import com.airbnb.android.superhero.SuperHeroMessageModel.Factory;
import com.airbnb.android.superhero.SuperHeroMessageModel.Insert_message;
import com.airbnb.android.superhero.SuperHeroMessageModel.Update_message;
import com.airbnb.android.superhero.SuperHeroMessageModel.Update_message_status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.squareup.sqldelight.RowMapper;
import java.util.ArrayList;

@JsonDeserialize(builder = Builder.class)
@JsonSerialize
public abstract class SuperHeroMessage implements Parcelable, SuperHeroMessageModel {
    private static final String BUNDLE_ARG = "super_hero_message";
    private static final int DEFAULT_RADIUS_FOR_LOCATION_METERS = 200;
    public static final Factory<SuperHeroMessage> FACTORY = new Factory<>(SuperHeroMessage$$Lambda$1.lambdaFactory$(), AirDateTimeColumnAdapter.INSTANCE, AirDateTimeColumnAdapter.INSTANCE, STRING_ARRAY_LIST_ADAPTER, HERO_ACTION_LIST_ADAPTER);
    private static final JsonColumnAdapter<ArrayList<SuperHeroAction>> HERO_ACTION_LIST_ADAPTER = new JsonColumnAdapter<>(new TypeReference<ArrayList<SuperHeroAction>>() {
    }.getType());
    public static final RowMapper<SuperHeroMessage> MAPPER = FACTORY.select_allMapper();
    private static final JsonColumnAdapter<ArrayList<String>> STRING_ARRAY_LIST_ADAPTER = new JsonColumnAdapter<>(new TypeReference<ArrayList<String>>() {
    }.getType());

    public static abstract class Builder {
        public abstract SuperHeroMessage build();

        @JsonProperty
        public abstract Builder dismiss_text(String str);

        @JsonProperty
        public abstract Builder ends_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder hero_actions(ArrayList<SuperHeroAction> arrayList);

        @JsonProperty
        public abstract Builder hero_type_string(String str);

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo11547id(long j);

        @JsonProperty
        public abstract Builder lat(Double d);

        @JsonProperty
        public abstract Builder lng(Double d);

        @JsonProperty
        public abstract Builder messages(ArrayList<String> arrayList);

        @JsonProperty
        public abstract Builder radius(Long l);

        @JsonProperty
        public abstract Builder should_takeover(boolean z);

        @JsonProperty
        public abstract Builder starts_at(AirDateTime airDateTime);

        @JsonProperty
        public abstract Builder status(long j);

        @JsonProperty
        public abstract Builder trigger_type(Long l);
    }

    public enum Status {
        UNKNOWN(-1),
        ACTIVE(0),
        SCHEDULED(1),
        TRIGGERED(2),
        PRESENTED(3);
        
        final int value;

        private Status(int value2) {
            this.value = value2;
        }

        static Status fromInt(int value2) {
            switch (value2) {
                case 0:
                    return ACTIVE;
                case 1:
                    return SCHEDULED;
                case 2:
                    return TRIGGERED;
                case 3:
                    return PRESENTED;
                default:
                    return UNKNOWN;
            }
        }
    }

    public enum TriggerType {
        UNKNOWN(-1),
        PUSH(0),
        LOCAL(1),
        PASSIVE(2);
        
        final int value;

        private TriggerType(int value2) {
            this.value = value2;
        }

        static TriggerType fromInt(int value2) {
            switch (value2) {
                case 0:
                    return PUSH;
                case 1:
                    return LOCAL;
                case 2:
                    return PASSIVE;
                default:
                    return UNKNOWN;
            }
        }
    }

    @JsonProperty
    public abstract String dismiss_text();

    @JsonProperty
    public abstract AirDateTime ends_at();

    @JsonProperty
    public abstract ArrayList<SuperHeroAction> hero_actions();

    @JsonProperty
    public abstract String hero_type_string();

    @JsonProperty
    /* renamed from: id */
    public abstract long mo11531id();

    @JsonProperty
    public abstract Double lat();

    @JsonProperty
    public abstract Double lng();

    @JsonProperty
    public abstract ArrayList<String> messages();

    @JsonProperty
    public abstract Long radius();

    @JsonProperty
    public abstract boolean should_takeover();

    @JsonProperty
    public abstract AirDateTime starts_at();

    @JsonProperty
    public abstract long status();

    public abstract Builder toBuilder();

    @JsonProperty
    public abstract Long trigger_type();

    public boolean isValidAndNotYetTriggered() {
        AirDateTime now = AirDateTime.now();
        return isStatusNotYetTriggered() && !hasExpired(now) && (starts_at() == null || starts_at().isBefore(now));
    }

    public boolean shouldBeScheduled() {
        return isStatusNotYetTriggered() && !hasExpired(AirDateTime.now()) && triggerTypeEnum() == TriggerType.LOCAL;
    }

    private boolean isStatusNotYetTriggered() {
        return statusEnum() == Status.ACTIVE || statusEnum() == Status.SCHEDULED;
    }

    public boolean hasLocation() {
        return (lat() == null || lng() == null) ? false : true;
    }

    public double getDistanceToCurrentLocation(Location currentLocation) {
        if (lat() == null || lng() == null) {
            return Double.MAX_VALUE;
        }
        return LocationUtil.getMetersBetweenPoints(currentLocation.getLatitude(), currentLocation.getLongitude(), lat().doubleValue(), lng().doubleValue());
    }

    public double getRadius() {
        if (radius() == null) {
            return 200.0d;
        }
        return (double) radius().longValue();
    }

    public boolean hasExpired(AirDateTime time) {
        return ends_at() != null && ends_at().isBefore(time);
    }

    public Status statusEnum() {
        return Status.fromInt((int) status());
    }

    public TriggerType triggerTypeEnum() {
        Long triggerType = trigger_type();
        if (triggerType == null) {
            return TriggerType.UNKNOWN;
        }
        return TriggerType.fromInt(triggerType.intValue());
    }

    public String firstMessage() {
        return (String) messages().get(0);
    }

    /* access modifiers changed from: 0000 */
    public Insert_message newInsertStatement(SQLiteDatabase database) {
        Insert_message insert = new Insert_message(database, FACTORY);
        insert.bind(mo11531id(), starts_at(), ends_at(), lat(), lng(), radius(), dismiss_text(), hero_type_string(), messages(), should_takeover(), hero_actions(), status(), trigger_type());
        return insert;
    }

    /* access modifiers changed from: 0000 */
    public Delete_message newDeleteStatement(SQLiteDatabase database) {
        Delete_message delete = new Delete_message(database);
        delete.bind(mo11531id());
        return delete;
    }

    /* access modifiers changed from: 0000 */
    public Update_message newUpdateStatement(SQLiteDatabase database) {
        Update_message update = new Update_message(database, FACTORY);
        update.bind(starts_at(), ends_at(), lat(), lng(), radius(), dismiss_text(), hero_type_string(), messages(), should_takeover(), hero_actions(), status(), trigger_type(), mo11531id());
        return update;
    }

    /* access modifiers changed from: 0000 */
    public Update_message_status newUpdateStatusStatement(SQLiteDatabase database, Status newStatus) {
        Update_message_status statement = new Update_message_status(database);
        statement.bind((long) newStatus.value, mo11531id());
        return statement;
    }

    static Bundle getAlarmBundle(Intent intent) {
        return intent.getBundleExtra(BUNDLE_ARG);
    }

    /* access modifiers changed from: 0000 */
    public PendingIntent buildPendingIntent(Context context) {
        return PendingIntent.getBroadcast(context, getRequestCode(), new Intent(context, SuperHeroAlarmReceiver.class).putExtra(BUNDLE_ARG, SuperHeroBundleUtil.from(this)), 134217728);
    }

    private int getRequestCode() {
        return (int) mo11531id();
    }

    public static Builder builder() {
        return new Builder();
    }
}
