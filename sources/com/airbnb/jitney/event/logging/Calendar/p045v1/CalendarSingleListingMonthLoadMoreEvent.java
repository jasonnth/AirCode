package com.airbnb.jitney.event.logging.Calendar.p045v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarSingleListingMonthLoadMoreEvent */
public final class CalendarSingleListingMonthLoadMoreEvent implements Struct {
    public static final Adapter<CalendarSingleListingMonthLoadMoreEvent, Object> ADAPTER = new CalendarSingleListingMonthLoadMoreEventAdapter();
    public final Context context;
    public final String event_name;
    public final String operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarSingleListingMonthLoadMoreEvent$CalendarSingleListingMonthLoadMoreEventAdapter */
    private static final class CalendarSingleListingMonthLoadMoreEventAdapter implements Adapter<CalendarSingleListingMonthLoadMoreEvent, Object> {
        private CalendarSingleListingMonthLoadMoreEventAdapter() {
        }

        public void write(Protocol protocol, CalendarSingleListingMonthLoadMoreEvent struct) throws IOException {
            protocol.writeStructBegin("CalendarSingleListingMonthLoadMoreEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
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
        if (!(other instanceof CalendarSingleListingMonthLoadMoreEvent)) {
            return false;
        }
        CalendarSingleListingMonthLoadMoreEvent that = (CalendarSingleListingMonthLoadMoreEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && (this.operation == that.operation || this.operation.equals(that.operation)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CalendarSingleListingMonthLoadMoreEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
