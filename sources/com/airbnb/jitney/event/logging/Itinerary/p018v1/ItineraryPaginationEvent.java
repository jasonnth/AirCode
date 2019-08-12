package com.airbnb.jitney.event.logging.Itinerary.p018v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Direction.p012v1.C0940Direction;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryPaginationEvent */
public final class ItineraryPaginationEvent implements Struct {
    public static final Adapter<ItineraryPaginationEvent, Builder> ADAPTER = new ItineraryPaginationEventAdapter();
    public final Context context;
    public final C0940Direction direction;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long page_number;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryPaginationEvent$Builder */
    public static final class Builder implements StructBuilder<ItineraryPaginationEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public C0940Direction direction;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long page_number;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryPaginationEvent:1.0.0";
            this.event_name = "itinerary_pagination";
            this.operation = C2451Operation.Show;
        }

        public Builder(Context context2, String page2) {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryPaginationEvent:1.0.0";
            this.event_name = "itinerary_pagination";
            this.context = context2;
            this.page = page2;
            this.operation = C2451Operation.Show;
        }

        public Builder direction(C0940Direction direction2) {
            this.direction = direction2;
            return this;
        }

        public ItineraryPaginationEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation != null) {
                return new ItineraryPaginationEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v1.ItineraryPaginationEvent$ItineraryPaginationEventAdapter */
    private static final class ItineraryPaginationEventAdapter implements Adapter<ItineraryPaginationEvent, Builder> {
        private ItineraryPaginationEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryPaginationEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryPaginationEvent");
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
            if (struct.page_number != null) {
                protocol.writeFieldBegin("page_number", 4, 10);
                protocol.writeI64(struct.page_number.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.direction != null) {
                protocol.writeFieldBegin("direction", 5, 8);
                protocol.writeI32(struct.direction.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ItineraryPaginationEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.page_number = builder.page_number;
        this.direction = builder.direction;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ItineraryPaginationEvent)) {
            return false;
        }
        ItineraryPaginationEvent that = (ItineraryPaginationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.page_number == that.page_number || (this.page_number != null && this.page_number.equals(that.page_number))) && ((this.direction == that.direction || (this.direction != null && this.direction.equals(that.direction))) && (this.operation == that.operation || this.operation.equals(that.operation)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.page_number == null ? 0 : this.page_number.hashCode())) * -2128831035;
        if (this.direction != null) {
            i = this.direction.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryPaginationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", page_number=" + this.page_number + ", direction=" + this.direction + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
