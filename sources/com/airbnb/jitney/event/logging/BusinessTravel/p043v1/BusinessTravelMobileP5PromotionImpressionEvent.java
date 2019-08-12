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

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileP5PromotionImpressionEvent */
public final class BusinessTravelMobileP5PromotionImpressionEvent implements Struct {
    public static final Adapter<BusinessTravelMobileP5PromotionImpressionEvent, Builder> ADAPTER = new BusinessTravelMobileP5PromotionImpressionEventAdapter();
    public final String confirmation_code;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileP5PromotionImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelMobileP5PromotionImpressionEvent> {
        /* access modifiers changed from: private */
        public String confirmation_code;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobileP5PromotionImpressionEvent:1.0.0";
            this.event_name = "businesstravel_mobile_p5_promotion_impression";
            this.operation = C2451Operation.Impression;
            this.page = "mobile_p5_promotion";
        }

        public Builder(Context context2, String confirmation_code2) {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobileP5PromotionImpressionEvent:1.0.0";
            this.event_name = "businesstravel_mobile_p5_promotion_impression";
            this.context = context2;
            this.operation = C2451Operation.Impression;
            this.page = "mobile_p5_promotion";
            this.confirmation_code = confirmation_code2;
        }

        public BusinessTravelMobileP5PromotionImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.confirmation_code != null) {
                return new BusinessTravelMobileP5PromotionImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'confirmation_code' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileP5PromotionImpressionEvent$BusinessTravelMobileP5PromotionImpressionEventAdapter */
    private static final class BusinessTravelMobileP5PromotionImpressionEventAdapter implements Adapter<BusinessTravelMobileP5PromotionImpressionEvent, Builder> {
        private BusinessTravelMobileP5PromotionImpressionEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelMobileP5PromotionImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelMobileP5PromotionImpressionEvent");
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
            protocol.writeFieldBegin("page", 4, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("confirmation_code", 5, PassportService.SF_DG11);
            protocol.writeString(struct.confirmation_code);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private BusinessTravelMobileP5PromotionImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.page = builder.page;
        this.confirmation_code = builder.confirmation_code;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelMobileP5PromotionImpressionEvent)) {
            return false;
        }
        BusinessTravelMobileP5PromotionImpressionEvent that = (BusinessTravelMobileP5PromotionImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.page == that.page || this.page.equals(that.page)) && (this.confirmation_code == that.confirmation_code || this.confirmation_code.equals(that.confirmation_code))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.confirmation_code.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelMobileP5PromotionImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", page=" + this.page + ", confirmation_code=" + this.confirmation_code + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
