package com.airbnb.jitney.event.logging.PaidAmenities.p176v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.p178v1.C2493PaidAmenitiesOrderContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesGuestAddClickShowServicesEvent */
public final class PaidAmenitiesGuestAddClickShowServicesEvent implements Struct {
    public static final Adapter<PaidAmenitiesGuestAddClickShowServicesEvent, Builder> ADAPTER = new PaidAmenitiesGuestAddClickShowServicesEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2493PaidAmenitiesOrderContext paid_amenity_order_context;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesGuestAddClickShowServicesEvent$Builder */
    public static final class Builder implements StructBuilder<PaidAmenitiesGuestAddClickShowServicesEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C2493PaidAmenitiesOrderContext paid_amenity_order_context;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.PaidAmenities:PaidAmenitiesGuestAddClickShowServicesEvent:1.0.0";
            this.event_name = "paidamenities_guest_add_click_show_services";
            this.page = "pa_guest_add_blurb";
            this.target = "pa_guest_add_services";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C2493PaidAmenitiesOrderContext paid_amenity_order_context2) {
            this.schema = "com.airbnb.jitney.event.logging.PaidAmenities:PaidAmenitiesGuestAddClickShowServicesEvent:1.0.0";
            this.event_name = "paidamenities_guest_add_click_show_services";
            this.context = context2;
            this.page = "pa_guest_add_blurb";
            this.target = "pa_guest_add_services";
            this.operation = C2451Operation.Click;
            this.paid_amenity_order_context = paid_amenity_order_context2;
        }

        public PaidAmenitiesGuestAddClickShowServicesEvent build() {
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
            } else if (this.paid_amenity_order_context != null) {
                return new PaidAmenitiesGuestAddClickShowServicesEvent(this);
            } else {
                throw new IllegalStateException("Required field 'paid_amenity_order_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenities.v1.PaidAmenitiesGuestAddClickShowServicesEvent$PaidAmenitiesGuestAddClickShowServicesEventAdapter */
    private static final class PaidAmenitiesGuestAddClickShowServicesEventAdapter implements Adapter<PaidAmenitiesGuestAddClickShowServicesEvent, Builder> {
        private PaidAmenitiesGuestAddClickShowServicesEventAdapter() {
        }

        public void write(Protocol protocol, PaidAmenitiesGuestAddClickShowServicesEvent struct) throws IOException {
            protocol.writeStructBegin("PaidAmenitiesGuestAddClickShowServicesEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("paid_amenity_order_context", 6, PassportService.SF_DG12);
            C2493PaidAmenitiesOrderContext.ADAPTER.write(protocol, struct.paid_amenity_order_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PaidAmenitiesGuestAddClickShowServicesEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.paid_amenity_order_context = builder.paid_amenity_order_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaidAmenitiesGuestAddClickShowServicesEvent)) {
            return false;
        }
        PaidAmenitiesGuestAddClickShowServicesEvent that = (PaidAmenitiesGuestAddClickShowServicesEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.paid_amenity_order_context == that.paid_amenity_order_context || this.paid_amenity_order_context.equals(that.paid_amenity_order_context)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.paid_amenity_order_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaidAmenitiesGuestAddClickShowServicesEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", paid_amenity_order_context=" + this.paid_amenity_order_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
