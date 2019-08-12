package com.airbnb.jitney.event.logging.LYS.p130v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.jitney.event.logging.Address.p037v1.C1797Address;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSLocationAddressFormEvent */
public final class LYSLocationAddressFormEvent implements Struct {
    public static final Adapter<LYSLocationAddressFormEvent, Builder> ADAPTER = new LYSLocationAddressFormEventAdapter();
    public final C1797Address address;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSLocationAddressFormEvent$Builder */
    public static final class Builder implements StructBuilder<LYSLocationAddressFormEvent> {
        /* access modifiers changed from: private */
        public C1797Address address;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public Long user_id;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSLocationAddressFormEvent:2.0.0";
            this.event_name = "lys_location_address_form";
            this.page = "LocationAddressForm";
            this.target = RegistrationAnalytics.NEXT_BUTTON;
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C1797Address address2, Long user_id2, Long listing_id2) {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSLocationAddressFormEvent:2.0.0";
            this.event_name = "lys_location_address_form";
            this.context = context2;
            this.page = "LocationAddressForm";
            this.target = RegistrationAnalytics.NEXT_BUTTON;
            this.operation = C2451Operation.Click;
            this.address = address2;
            this.user_id = user_id2;
            this.listing_id = listing_id2;
        }

        public LYSLocationAddressFormEvent build() {
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
            } else if (this.address == null) {
                throw new IllegalStateException("Required field 'address' is missing");
            } else if (this.user_id == null) {
                throw new IllegalStateException("Required field 'user_id' is missing");
            } else if (this.listing_id != null) {
                return new LYSLocationAddressFormEvent(this);
            } else {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSLocationAddressFormEvent$LYSLocationAddressFormEventAdapter */
    private static final class LYSLocationAddressFormEventAdapter implements Adapter<LYSLocationAddressFormEvent, Builder> {
        private LYSLocationAddressFormEventAdapter() {
        }

        public void write(Protocol protocol, LYSLocationAddressFormEvent struct) throws IOException {
            protocol.writeStructBegin("LYSLocationAddressFormEvent");
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
            protocol.writeFieldBegin("address", 6, PassportService.SF_DG12);
            C1797Address.ADAPTER.write(protocol, struct.address);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 7, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 8, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LYSLocationAddressFormEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.address = builder.address;
        this.user_id = builder.user_id;
        this.listing_id = builder.listing_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LYSLocationAddressFormEvent)) {
            return false;
        }
        LYSLocationAddressFormEvent that = (LYSLocationAddressFormEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.address == that.address || this.address.equals(that.address)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.address.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSLocationAddressFormEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", address=" + this.address + ", user_id=" + this.user_id + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}