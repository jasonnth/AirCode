package com.airbnb.jitney.event.logging.HostStats.p013v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsRatingsRecentRatingTapEvent */
public final class StatsRatingsRecentRatingTapEvent implements Struct {
    public static final Adapter<StatsRatingsRecentRatingTapEvent, Builder> ADAPTER = new StatsRatingsRecentRatingTapEventAdapter();
    public final Long carousel_index;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final String recent_rating_state;
    public final String schema;
    public final String subcategory;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsRatingsRecentRatingTapEvent$Builder */
    public static final class Builder implements StructBuilder<StatsRatingsRecentRatingTapEvent> {
        /* access modifiers changed from: private */
        public Long carousel_index;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String recent_rating_state;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String subcategory;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsRatingsRecentRatingTapEvent:1.0.0";
            this.event_name = "stats_ratings_recent_rating_tap";
            this.page = HostStatsJitneyLogger.PAGE_REVIEW_DETAILS;
            this.target = "recent_rating";
            this.operation = "tap";
        }

        public Builder(Context context2, Long listing_id2, Long carousel_index2, String recent_rating_state2) {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsRatingsRecentRatingTapEvent:1.0.0";
            this.event_name = "stats_ratings_recent_rating_tap";
            this.context = context2;
            this.page = HostStatsJitneyLogger.PAGE_REVIEW_DETAILS;
            this.target = "recent_rating";
            this.operation = "tap";
            this.listing_id = listing_id2;
            this.carousel_index = carousel_index2;
            this.recent_rating_state = recent_rating_state2;
        }

        public StatsRatingsRecentRatingTapEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.carousel_index == null) {
                throw new IllegalStateException("Required field 'carousel_index' is missing");
            } else if (this.recent_rating_state != null) {
                return new StatsRatingsRecentRatingTapEvent(this);
            } else {
                throw new IllegalStateException("Required field 'recent_rating_state' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsRatingsRecentRatingTapEvent$StatsRatingsRecentRatingTapEventAdapter */
    private static final class StatsRatingsRecentRatingTapEventAdapter implements Adapter<StatsRatingsRecentRatingTapEvent, Builder> {
        private StatsRatingsRecentRatingTapEventAdapter() {
        }

        public void write(Protocol protocol, StatsRatingsRecentRatingTapEvent struct) throws IOException {
            protocol.writeStructBegin("StatsRatingsRecentRatingTapEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 6, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("carousel_index", 7, 10);
            protocol.writeI64(struct.carousel_index.longValue());
            protocol.writeFieldEnd();
            if (struct.subcategory != null) {
                protocol.writeFieldBegin("subcategory", 8, PassportService.SF_DG11);
                protocol.writeString(struct.subcategory);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("recent_rating_state", 9, PassportService.SF_DG11);
            protocol.writeString(struct.recent_rating_state);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private StatsRatingsRecentRatingTapEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.carousel_index = builder.carousel_index;
        this.subcategory = builder.subcategory;
        this.recent_rating_state = builder.recent_rating_state;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StatsRatingsRecentRatingTapEvent)) {
            return false;
        }
        StatsRatingsRecentRatingTapEvent that = (StatsRatingsRecentRatingTapEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.carousel_index == that.carousel_index || this.carousel_index.equals(that.carousel_index)) && ((this.subcategory == that.subcategory || (this.subcategory != null && this.subcategory.equals(that.subcategory))) && (this.recent_rating_state == that.recent_rating_state || this.recent_rating_state.equals(that.recent_rating_state))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.carousel_index.hashCode()) * -2128831035;
        if (this.subcategory != null) {
            i = this.subcategory.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.recent_rating_state.hashCode()) * -2128831035;
    }

    public String toString() {
        return "StatsRatingsRecentRatingTapEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", carousel_index=" + this.carousel_index + ", subcategory=" + this.subcategory + ", recent_rating_state=" + this.recent_rating_state + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
