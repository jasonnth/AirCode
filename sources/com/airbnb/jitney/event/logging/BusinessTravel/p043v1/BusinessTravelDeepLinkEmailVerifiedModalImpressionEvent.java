package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

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

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent */
public final class BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent implements Struct {
    public static final Adapter<BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent, Builder> ADAPTER = new BusinessTravelDeepLinkEmailVerifiedModalImpressionEventAdapter();
    public final Context context;
    public final String email;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String email;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent:1.0.0";
            this.event_name = "businesstravel_deep_link_email_verified_modal_impression";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, String email2) {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent:1.0.0";
            this.event_name = "businesstravel_deep_link_email_verified_modal_impression";
            this.context = context2;
            this.operation = C2451Operation.Impression;
            this.email = email2;
        }

        public BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.email != null) {
                return new BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'email' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent$BusinessTravelDeepLinkEmailVerifiedModalImpressionEventAdapter */
    private static final class BusinessTravelDeepLinkEmailVerifiedModalImpressionEventAdapter implements Adapter<BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent, Builder> {
        private BusinessTravelDeepLinkEmailVerifiedModalImpressionEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("email", 4, PassportService.SF_DG11);
            protocol.writeString(struct.email);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.email = builder.email;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent)) {
            return false;
        }
        BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent that = (BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.email == that.email || this.email.equals(that.email)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.email.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", email=" + this.email + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
