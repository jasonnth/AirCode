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

/* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerOpenEvent */
public final class StatsListingPickerOpenEvent implements Struct {
    public static final Adapter<StatsListingPickerOpenEvent, Builder> ADAPTER = new StatsListingPickerOpenEventAdapter();
    public final Context context;
    public final String event_name;
    public final String operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerOpenEvent$Builder */
    public static final class Builder implements StructBuilder<StatsListingPickerOpenEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsListingPickerOpenEvent:1.0.0";
            this.event_name = "stats_listing_picker_open";
            this.target = "listing_picker";
            this.operation = "tap";
        }

        public Builder(Context context2, String page2) {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsListingPickerOpenEvent:1.0.0";
            this.event_name = "stats_listing_picker_open";
            this.context = context2;
            this.page = page2;
            this.target = "listing_picker";
            this.operation = "tap";
        }

        public StatsListingPickerOpenEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation != null) {
                return new StatsListingPickerOpenEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsListingPickerOpenEvent$StatsListingPickerOpenEventAdapter */
    private static final class StatsListingPickerOpenEventAdapter implements Adapter<StatsListingPickerOpenEvent, Builder> {
        private StatsListingPickerOpenEventAdapter() {
        }

        public void write(Protocol protocol, StatsListingPickerOpenEvent struct) throws IOException {
            protocol.writeStructBegin("StatsListingPickerOpenEvent");
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
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private StatsListingPickerOpenEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StatsListingPickerOpenEvent)) {
            return false;
        }
        StatsListingPickerOpenEvent that = (StatsListingPickerOpenEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && (this.operation == that.operation || this.operation.equals(that.operation))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "StatsListingPickerOpenEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
