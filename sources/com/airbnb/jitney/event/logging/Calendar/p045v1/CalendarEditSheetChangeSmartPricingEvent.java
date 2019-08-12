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

/* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarEditSheetChangeSmartPricingEvent */
public final class CalendarEditSheetChangeSmartPricingEvent implements Struct {
    public static final Adapter<CalendarEditSheetChangeSmartPricingEvent, Builder> ADAPTER = new CalendarEditSheetChangeSmartPricingEventAdapter();
    public final Context context;
    public final List<String> dates_selected;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final String schema;
    public final Boolean smart_pricing_on;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarEditSheetChangeSmartPricingEvent$Builder */
    public static final class Builder implements StructBuilder<CalendarEditSheetChangeSmartPricingEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates_selected;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Boolean smart_pricing_on;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarEditSheetChangeSmartPricingEvent:1.0.0";
            this.event_name = "calendar_edit_sheet_change_smart_pricing";
            this.page = "calendar_edit_sheet";
            this.target = "save_button";
            this.operation = "click";
        }

        public Builder(Context context2, Long listing_id2, List<String> dates_selected2, Boolean smart_pricing_on2) {
            this.schema = "com.airbnb.jitney.event.logging.Calendar:CalendarEditSheetChangeSmartPricingEvent:1.0.0";
            this.event_name = "calendar_edit_sheet_change_smart_pricing";
            this.context = context2;
            this.page = "calendar_edit_sheet";
            this.target = "save_button";
            this.operation = "click";
            this.listing_id = listing_id2;
            this.dates_selected = dates_selected2;
            this.smart_pricing_on = smart_pricing_on2;
        }

        public CalendarEditSheetChangeSmartPricingEvent build() {
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
            } else if (this.dates_selected == null) {
                throw new IllegalStateException("Required field 'dates_selected' is missing");
            } else if (this.smart_pricing_on != null) {
                return new CalendarEditSheetChangeSmartPricingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'smart_pricing_on' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.CalendarEditSheetChangeSmartPricingEvent$CalendarEditSheetChangeSmartPricingEventAdapter */
    private static final class CalendarEditSheetChangeSmartPricingEventAdapter implements Adapter<CalendarEditSheetChangeSmartPricingEvent, Builder> {
        private CalendarEditSheetChangeSmartPricingEventAdapter() {
        }

        public void write(Protocol protocol, CalendarEditSheetChangeSmartPricingEvent struct) throws IOException {
            protocol.writeStructBegin("CalendarEditSheetChangeSmartPricingEvent");
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
            protocol.writeFieldBegin("dates_selected", 7, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates_selected.size());
            for (String item0 : struct.dates_selected) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("smart_pricing_on", 8, 2);
            protocol.writeBool(struct.smart_pricing_on.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CalendarEditSheetChangeSmartPricingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.dates_selected = Collections.unmodifiableList(builder.dates_selected);
        this.smart_pricing_on = builder.smart_pricing_on;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CalendarEditSheetChangeSmartPricingEvent)) {
            return false;
        }
        CalendarEditSheetChangeSmartPricingEvent that = (CalendarEditSheetChangeSmartPricingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.dates_selected == that.dates_selected || this.dates_selected.equals(that.dates_selected)) && (this.smart_pricing_on == that.smart_pricing_on || this.smart_pricing_on.equals(that.smart_pricing_on)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.dates_selected.hashCode()) * -2128831035) ^ this.smart_pricing_on.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CalendarEditSheetChangeSmartPricingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", dates_selected=" + this.dates_selected + ", smart_pricing_on=" + this.smart_pricing_on + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
