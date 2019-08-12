package com.airbnb.jitney.event.logging.Itinerary.p018v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryImpressionItemEvent */
public final class ItineraryImpressionItemEvent implements Struct {
    public static final Adapter<ItineraryImpressionItemEvent, Builder> ADAPTER = new ItineraryImpressionItemEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C0973SchedulableInfo parent_schedulable_info;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryImpressionItemEvent$Builder */
    public static final class Builder implements StructBuilder<ItineraryImpressionItemEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C0973SchedulableInfo parent_schedulable_info;
        /* access modifiers changed from: private */
        public C0973SchedulableInfo schedulable_info;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryImpressionItemEvent:1.0.0";
            this.event_name = "itinerary_impression_item";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, String page2, C0973SchedulableInfo schedulable_info2) {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryImpressionItemEvent:1.0.0";
            this.event_name = "itinerary_impression_item";
            this.context = context2;
            this.page = page2;
            this.operation = C2451Operation.Impression;
            this.schedulable_info = schedulable_info2;
        }

        public Builder parent_schedulable_info(C0973SchedulableInfo parent_schedulable_info2) {
            this.parent_schedulable_info = parent_schedulable_info2;
            return this;
        }

        public ItineraryImpressionItemEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.schedulable_info != null) {
                return new ItineraryImpressionItemEvent(this);
            } else {
                throw new IllegalStateException("Required field 'schedulable_info' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryImpressionItemEvent$ItineraryImpressionItemEventAdapter */
    private static final class ItineraryImpressionItemEventAdapter implements Adapter<ItineraryImpressionItemEvent, Builder> {
        private ItineraryImpressionItemEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryImpressionItemEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryImpressionItemEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("schedulable_info", 5, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
            protocol.writeFieldEnd();
            if (struct.parent_schedulable_info != null) {
                protocol.writeFieldBegin("parent_schedulable_info", 6, PassportService.SF_DG12);
                C0973SchedulableInfo.ADAPTER.write(protocol, struct.parent_schedulable_info);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ItineraryImpressionItemEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.schedulable_info = builder.schedulable_info;
        this.parent_schedulable_info = builder.parent_schedulable_info;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ItineraryImpressionItemEvent)) {
            return false;
        }
        ItineraryImpressionItemEvent that = (ItineraryImpressionItemEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info))))))) {
            if (this.parent_schedulable_info == that.parent_schedulable_info) {
                return true;
            }
            if (this.parent_schedulable_info != null && this.parent_schedulable_info.equals(that.parent_schedulable_info)) {
                return true;
            }
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035;
        if (this.parent_schedulable_info != null) {
            i = this.parent_schedulable_info.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ItineraryImpressionItemEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", schedulable_info=" + this.schedulable_info + ", parent_schedulable_info=" + this.parent_schedulable_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
