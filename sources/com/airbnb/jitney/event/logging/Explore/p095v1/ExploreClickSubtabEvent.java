package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickSubtabEvent */
public final class ExploreClickSubtabEvent implements Struct {
    public static final Adapter<ExploreClickSubtabEvent, Object> ADAPTER = new ExploreClickSubtabEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;
    public final C2139ExploreSubtab subtab_previous;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickSubtabEvent$ExploreClickSubtabEventAdapter */
    private static final class ExploreClickSubtabEventAdapter implements Adapter<ExploreClickSubtabEvent, Object> {
        private ExploreClickSubtabEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickSubtabEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickSubtabEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("location", 6, PassportService.SF_DG11);
            protocol.writeString(struct.location);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 9, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab_previous", 10, 8);
            protocol.writeI32(struct.subtab_previous.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 11, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
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
        if (!(other instanceof ExploreClickSubtabEvent)) {
            return false;
        }
        ExploreClickSubtabEvent that = (ExploreClickSubtabEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.location == that.location || this.location.equals(that.location)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && ((this.guests == that.guests || this.guests.equals(that.guests)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.subtab_previous == that.subtab_previous || this.subtab_previous.equals(that.subtab_previous)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.location.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.subtab_previous.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreClickSubtabEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + ", subtab_previous=" + this.subtab_previous + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}