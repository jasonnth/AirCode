package com.airbnb.jitney.event.logging.Calendar.p045v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarSingleListingAgendaEditDatesEvent */
public final class CalendarSingleListingAgendaEditDatesEvent implements Struct {
    public static final Adapter<CalendarSingleListingAgendaEditDatesEvent, Builder> ADAPTER = new CalendarSingleListingAgendaEditDatesEventAdapter();
    public final Context context;
    public final List<String> dates_selected;
    public final String event_name;
    public final Long listing_id;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarSingleListingAgendaEditDatesEvent$Builder */
    public static final class Builder implements StructBuilder<CalendarSingleListingAgendaEditDatesEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates_selected;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarSingleListingAgendaEditDatesEvent:1.0.0";
            this.page = "calendar_single_listing_agenda";
            this.target = "edit_button";
            this.event_name = "calendar_single_listing_agenda_edit_dates_event";
        }

        public Builder(Long listing_id2, List<String> dates_selected2, Context context2) {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarSingleListingAgendaEditDatesEvent:1.0.0";
            this.listing_id = listing_id2;
            this.page = "calendar_single_listing_agenda";
            this.target = "edit_button";
            this.dates_selected = dates_selected2;
            this.event_name = "calendar_single_listing_agenda_edit_dates_event";
            this.context = context2;
        }

        public CalendarSingleListingAgendaEditDatesEvent build() {
            if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.dates_selected == null) {
                throw new IllegalStateException("Required field 'dates_selected' is missing");
            } else if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context != null) {
                return new CalendarSingleListingAgendaEditDatesEvent(this);
            } else {
                throw new IllegalStateException("Required field 'context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarSingleListingAgendaEditDatesEvent$CalendarSingleListingAgendaEditDatesEventAdapter */
    private static final class CalendarSingleListingAgendaEditDatesEventAdapter implements Adapter<CalendarSingleListingAgendaEditDatesEvent, Builder> {
        private CalendarSingleListingAgendaEditDatesEventAdapter() {
        }

        public void write(Protocol protocol, CalendarSingleListingAgendaEditDatesEvent struct) throws IOException {
            protocol.writeStructBegin("CalendarSingleListingAgendaEditDatesEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("listing_id", 1, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 2, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 3, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("dates_selected", 4, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates_selected.size());
            for (String item0 : struct.dates_selected) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("event_name", 5, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 6, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CalendarSingleListingAgendaEditDatesEvent(Builder builder) {
        this.schema = builder.schema;
        this.listing_id = builder.listing_id;
        this.page = builder.page;
        this.target = builder.target;
        this.dates_selected = Collections.unmodifiableList(builder.dates_selected);
        this.event_name = builder.event_name;
        this.context = builder.context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CalendarSingleListingAgendaEditDatesEvent)) {
            return false;
        }
        CalendarSingleListingAgendaEditDatesEvent that = (CalendarSingleListingAgendaEditDatesEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.dates_selected == that.dates_selected || this.dates_selected.equals(that.dates_selected)) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && (this.context == that.context || this.context.equals(that.context)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.dates_selected.hashCode()) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CalendarSingleListingAgendaEditDatesEvent{schema=" + this.schema + ", listing_id=" + this.listing_id + ", page=" + this.page + ", target=" + this.target + ", dates_selected=" + this.dates_selected + ", event_name=" + this.event_name + ", context=" + this.context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
