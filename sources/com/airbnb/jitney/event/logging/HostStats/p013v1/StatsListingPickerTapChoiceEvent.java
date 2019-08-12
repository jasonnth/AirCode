package com.airbnb.jitney.event.logging.HostStats.p013v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerTapChoiceEvent */
public final class StatsListingPickerTapChoiceEvent implements Struct {
    public static final Adapter<StatsListingPickerTapChoiceEvent, Builder> ADAPTER = new StatsListingPickerTapChoiceEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final String schema;
    public final Boolean selected_all_listings;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerTapChoiceEvent$Builder */
    public static final class Builder implements StructBuilder<StatsListingPickerTapChoiceEvent> {
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
        public String schema;
        /* access modifiers changed from: private */
        public Boolean selected_all_listings;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsListingPickerTapChoiceEvent:1.0.0";
            this.event_name = "stats_listing_picker_tap_choice";
            this.page = "stats_listing_picker";
            this.target = "listing";
            this.operation = "tap";
        }

        public Builder(Context context2, Boolean selected_all_listings2) {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsListingPickerTapChoiceEvent:1.0.0";
            this.event_name = "stats_listing_picker_tap_choice";
            this.context = context2;
            this.page = "stats_listing_picker";
            this.target = "listing";
            this.operation = "tap";
            this.selected_all_listings = selected_all_listings2;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public StatsListingPickerTapChoiceEvent build() {
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
            } else if (this.selected_all_listings != null) {
                return new StatsListingPickerTapChoiceEvent(this);
            } else {
                throw new IllegalStateException("Required field 'selected_all_listings' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerTapChoiceEvent$StatsListingPickerTapChoiceEventAdapter */
    private static final class StatsListingPickerTapChoiceEventAdapter implements Adapter<StatsListingPickerTapChoiceEvent, Builder> {
        private StatsListingPickerTapChoiceEventAdapter() {
        }

        public void write(Protocol protocol, StatsListingPickerTapChoiceEvent struct) throws IOException {
            protocol.writeStructBegin("StatsListingPickerTapChoiceEvent");
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
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 6, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("selected_all_listings", 7, 2);
            protocol.writeBool(struct.selected_all_listings.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private StatsListingPickerTapChoiceEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.selected_all_listings = builder.selected_all_listings;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StatsListingPickerTapChoiceEvent)) {
            return false;
        }
        StatsListingPickerTapChoiceEvent that = (StatsListingPickerTapChoiceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && (this.selected_all_listings == that.selected_all_listings || this.selected_all_listings.equals(that.selected_all_listings))))))))) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.selected_all_listings.hashCode()) * -2128831035;
    }

    public String toString() {
        return "StatsListingPickerTapChoiceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", selected_all_listings=" + this.selected_all_listings + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
