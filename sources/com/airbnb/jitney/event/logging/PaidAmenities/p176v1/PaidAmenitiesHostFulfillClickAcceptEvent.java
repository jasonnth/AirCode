package com.airbnb.jitney.event.logging.PaidAmenities.p176v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesHostFulfillClickAcceptEvent */
public final class PaidAmenitiesHostFulfillClickAcceptEvent implements Struct {
    public static final Adapter<PaidAmenitiesHostFulfillClickAcceptEvent, Builder> ADAPTER = new PaidAmenitiesHostFulfillClickAcceptEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long paid_amenity_order_id;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesHostFulfillClickAcceptEvent$Builder */
    public static final class Builder implements StructBuilder<PaidAmenitiesHostFulfillClickAcceptEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long paid_amenity_order_id;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.PaidAmenities:PaidAmenitiesHostFulfillClickAcceptEvent:1.0.0";
            this.event_name = "paidamenities_host_fulfill_click_accept";
            this.page = "pa_host_fulfill_service";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long paid_amenity_order_id2) {
            this.schema = "com.airbnb.jitney.event.logging.PaidAmenities:PaidAmenitiesHostFulfillClickAcceptEvent:1.0.0";
            this.event_name = "paidamenities_host_fulfill_click_accept";
            this.context = context2;
            this.page = "pa_host_fulfill_service";
            this.operation = C2451Operation.Click;
            this.paid_amenity_order_id = paid_amenity_order_id2;
        }

        public PaidAmenitiesHostFulfillClickAcceptEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.paid_amenity_order_id != null) {
                return new PaidAmenitiesHostFulfillClickAcceptEvent(this);
            } else {
                throw new IllegalStateException("Required field 'paid_amenity_order_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesHostFulfillClickAcceptEvent$PaidAmenitiesHostFulfillClickAcceptEventAdapter */
    private static final class PaidAmenitiesHostFulfillClickAcceptEventAdapter implements Adapter<PaidAmenitiesHostFulfillClickAcceptEvent, Builder> {
        private PaidAmenitiesHostFulfillClickAcceptEventAdapter() {
        }

        public void write(Protocol protocol, PaidAmenitiesHostFulfillClickAcceptEvent struct) throws IOException {
            protocol.writeStructBegin("PaidAmenitiesHostFulfillClickAcceptEvent");
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
            protocol.writeFieldBegin("paid_amenity_order_id", 5, 10);
            protocol.writeI64(struct.paid_amenity_order_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaidAmenitiesHostFulfillClickAcceptEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.paid_amenity_order_id = builder.paid_amenity_order_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaidAmenitiesHostFulfillClickAcceptEvent)) {
            return false;
        }
        PaidAmenitiesHostFulfillClickAcceptEvent that = (PaidAmenitiesHostFulfillClickAcceptEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.paid_amenity_order_id == that.paid_amenity_order_id || this.paid_amenity_order_id.equals(that.paid_amenity_order_id))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.paid_amenity_order_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaidAmenitiesHostFulfillClickAcceptEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", paid_amenity_order_id=" + this.paid_amenity_order_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}