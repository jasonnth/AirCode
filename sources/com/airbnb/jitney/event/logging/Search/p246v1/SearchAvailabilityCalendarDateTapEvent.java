package com.airbnb.jitney.event.logging.Search.p246v1;

import com.airbnb.jitney.event.logging.DateType.p078v1.C1977DateType;
import com.airbnb.jitney.event.logging.UnavailableReason.p277v1.C2775UnavailableReason;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchAvailabilityCalendarDateTapEvent */
public final class SearchAvailabilityCalendarDateTapEvent implements Struct {
    public static final Adapter<SearchAvailabilityCalendarDateTapEvent, Object> ADAPTER = new SearchAvailabilityCalendarDateTapEventAdapter();
    public final Context context;
    public final C1977DateType date_type;
    public final String event_name;
    public final String page;
    public final Boolean pop_up_shown;
    public final String schema;
    public final C2775UnavailableReason unavailable_reason;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchAvailabilityCalendarDateTapEvent$SearchAvailabilityCalendarDateTapEventAdapter */
    private static final class SearchAvailabilityCalendarDateTapEventAdapter implements Adapter<SearchAvailabilityCalendarDateTapEvent, Object> {
        private SearchAvailabilityCalendarDateTapEventAdapter() {
        }

        public void write(Protocol protocol, SearchAvailabilityCalendarDateTapEvent struct) throws IOException {
            protocol.writeStructBegin("SearchAvailabilityCalendarDateTapEvent");
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
            protocol.writeFieldBegin("date_type", 4, 8);
            protocol.writeI32(struct.date_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("unavailable_reason", 5, 8);
            protocol.writeI32(struct.unavailable_reason.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("pop_up_shown", 6, 2);
            protocol.writeBool(struct.pop_up_shown.booleanValue());
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
        if (!(other instanceof SearchAvailabilityCalendarDateTapEvent)) {
            return false;
        }
        SearchAvailabilityCalendarDateTapEvent that = (SearchAvailabilityCalendarDateTapEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.date_type == that.date_type || this.date_type.equals(that.date_type)) && ((this.unavailable_reason == that.unavailable_reason || this.unavailable_reason.equals(that.unavailable_reason)) && (this.pop_up_shown == that.pop_up_shown || this.pop_up_shown.equals(that.pop_up_shown)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.date_type.hashCode()) * -2128831035) ^ this.unavailable_reason.hashCode()) * -2128831035) ^ this.pop_up_shown.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SearchAvailabilityCalendarDateTapEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", date_type=" + this.date_type + ", unavailable_reason=" + this.unavailable_reason + ", pop_up_shown=" + this.pop_up_shown + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
