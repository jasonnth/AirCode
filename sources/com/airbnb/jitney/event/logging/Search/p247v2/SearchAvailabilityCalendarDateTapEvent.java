package com.airbnb.jitney.event.logging.Search.p247v2;

import com.airbnb.jitney.event.logging.DateType.p078v1.C1977DateType;
import com.airbnb.jitney.event.logging.UnavailableReason.p277v1.C2775UnavailableReason;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Search.v2.SearchAvailabilityCalendarDateTapEvent */
public final class SearchAvailabilityCalendarDateTapEvent implements Struct {
    public static final Adapter<SearchAvailabilityCalendarDateTapEvent, Builder> ADAPTER = new SearchAvailabilityCalendarDateTapEventAdapter();
    public final Context context;
    public final C1977DateType date_type;
    public final String event_name;
    public final String page;
    public final Boolean pop_up_shown;
    public final String schema;
    public final C2775UnavailableReason unavailable_reason;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v2.SearchAvailabilityCalendarDateTapEvent$Builder */
    public static final class Builder implements StructBuilder<SearchAvailabilityCalendarDateTapEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public C1977DateType date_type;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Boolean pop_up_shown;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2775UnavailableReason unavailable_reason;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Search:SearchAvailabilityCalendarDateTapEvent:2.0.0";
            this.event_name = "search_availability_calendar_date_tap";
            this.page = "listing";
        }

        public Builder(Context context2, C1977DateType date_type2, Boolean pop_up_shown2) {
            this.schema = "com.airbnb.jitney.event.logging.Search:SearchAvailabilityCalendarDateTapEvent:2.0.0";
            this.event_name = "search_availability_calendar_date_tap";
            this.context = context2;
            this.page = "listing";
            this.date_type = date_type2;
            this.pop_up_shown = pop_up_shown2;
        }

        public Builder unavailable_reason(C2775UnavailableReason unavailable_reason2) {
            this.unavailable_reason = unavailable_reason2;
            return this;
        }

        public SearchAvailabilityCalendarDateTapEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.date_type == null) {
                throw new IllegalStateException("Required field 'date_type' is missing");
            } else if (this.pop_up_shown != null) {
                return new SearchAvailabilityCalendarDateTapEvent(this);
            } else {
                throw new IllegalStateException("Required field 'pop_up_shown' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Search.v2.SearchAvailabilityCalendarDateTapEvent$SearchAvailabilityCalendarDateTapEventAdapter */
    private static final class SearchAvailabilityCalendarDateTapEventAdapter implements Adapter<SearchAvailabilityCalendarDateTapEvent, Builder> {
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
            if (struct.unavailable_reason != null) {
                protocol.writeFieldBegin("unavailable_reason", 5, 8);
                protocol.writeI32(struct.unavailable_reason.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("pop_up_shown", 6, 2);
            protocol.writeBool(struct.pop_up_shown.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SearchAvailabilityCalendarDateTapEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.date_type = builder.date_type;
        this.unavailable_reason = builder.unavailable_reason;
        this.pop_up_shown = builder.pop_up_shown;
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.date_type == that.date_type || this.date_type.equals(that.date_type)) && ((this.unavailable_reason == that.unavailable_reason || (this.unavailable_reason != null && this.unavailable_reason.equals(that.unavailable_reason))) && (this.pop_up_shown == that.pop_up_shown || this.pop_up_shown.equals(that.pop_up_shown)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.date_type.hashCode()) * -2128831035;
        if (this.unavailable_reason != null) {
            i = this.unavailable_reason.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.pop_up_shown.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SearchAvailabilityCalendarDateTapEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", date_type=" + this.date_type + ", unavailable_reason=" + this.unavailable_reason + ", pop_up_shown=" + this.pop_up_shown + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
