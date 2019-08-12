package com.airbnb.jitney.event.logging.HostStats.p013v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsRatingsSelectStarEvent */
public final class StatsRatingsSelectStarEvent implements Struct {
    public static final Adapter<StatsRatingsSelectStarEvent, Object> ADAPTER = new StatsRatingsSelectStarEventAdapter();
    public final Context context;
    public final String event_name;
    public final String operation;
    public final String page;
    public final Double percent_of_reviews;
    public final String schema;
    public final Double stars_chosen;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsRatingsSelectStarEvent$StatsRatingsSelectStarEventAdapter */
    private static final class StatsRatingsSelectStarEventAdapter implements Adapter<StatsRatingsSelectStarEvent, Object> {
        private StatsRatingsSelectStarEventAdapter() {
        }

        public void write(Protocol protocol, StatsRatingsSelectStarEvent struct) throws IOException {
            protocol.writeStructBegin("StatsRatingsSelectStarEvent");
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
            protocol.writeFieldBegin("stars_chosen", 6, 4);
            protocol.writeDouble(struct.stars_chosen.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("percent_of_reviews", 7, 4);
            protocol.writeDouble(struct.percent_of_reviews.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StatsRatingsSelectStarEvent)) {
            return false;
        }
        StatsRatingsSelectStarEvent that = (StatsRatingsSelectStarEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.stars_chosen == that.stars_chosen || this.stars_chosen.equals(that.stars_chosen)) && (this.percent_of_reviews == that.percent_of_reviews || this.percent_of_reviews.equals(that.percent_of_reviews))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.stars_chosen.hashCode()) * -2128831035) ^ this.percent_of_reviews.hashCode()) * -2128831035;
    }

    public String toString() {
        return "StatsRatingsSelectStarEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", stars_chosen=" + this.stars_chosen + ", percent_of_reviews=" + this.percent_of_reviews + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
