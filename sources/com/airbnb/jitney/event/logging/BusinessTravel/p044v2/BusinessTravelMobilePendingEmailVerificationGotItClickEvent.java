package com.airbnb.jitney.event.logging.BusinessTravel.p044v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.BizTravelReferrer.p041v1.C1805BizTravelReferrer;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobilePendingEmailVerificationGotItClickEvent */
public final class BusinessTravelMobilePendingEmailVerificationGotItClickEvent implements Struct {
    public static final Adapter<BusinessTravelMobilePendingEmailVerificationGotItClickEvent, Builder> ADAPTER = new C1866xb847d982();
    public final C1805BizTravelReferrer biz_travel_referrer;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobilePendingEmailVerificationGotItClickEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelMobilePendingEmailVerificationGotItClickEvent> {
        /* access modifiers changed from: private */
        public C1805BizTravelReferrer biz_travel_referrer;
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
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobilePendingEmailVerificationGotItClickEvent:2.0.0";
            this.event_name = "businesstravel_mobile_pending_email_verification_got_it_click";
            this.operation = C2451Operation.Click;
            this.page = "mobile_pending_user_email_verification";
        }

        public Builder(Context context2, C1805BizTravelReferrer biz_travel_referrer2) {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobilePendingEmailVerificationGotItClickEvent:2.0.0";
            this.event_name = "businesstravel_mobile_pending_email_verification_got_it_click";
            this.context = context2;
            this.operation = C2451Operation.Click;
            this.page = "mobile_pending_user_email_verification";
            this.biz_travel_referrer = biz_travel_referrer2;
        }

        public BusinessTravelMobilePendingEmailVerificationGotItClickEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.biz_travel_referrer != null) {
                return new BusinessTravelMobilePendingEmailVerificationGotItClickEvent(this);
            } else {
                throw new IllegalStateException("Required field 'biz_travel_referrer' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobilePendingEmailVerificationGotItClickEvent$BusinessTravelMobilePendingEmailVerificationGotItClickEventAdapter */
    private static final class C1866xb847d982 implements Adapter<BusinessTravelMobilePendingEmailVerificationGotItClickEvent, Builder> {
        private C1866xb847d982() {
        }

        public void write(Protocol protocol, BusinessTravelMobilePendingEmailVerificationGotItClickEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelMobilePendingEmailVerificationGotItClickEvent");
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
            protocol.writeFieldBegin("biz_travel_referrer", 5, 8);
            protocol.writeI32(struct.biz_travel_referrer.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private BusinessTravelMobilePendingEmailVerificationGotItClickEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.page = builder.page;
        this.biz_travel_referrer = builder.biz_travel_referrer;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelMobilePendingEmailVerificationGotItClickEvent)) {
            return false;
        }
        BusinessTravelMobilePendingEmailVerificationGotItClickEvent that = (BusinessTravelMobilePendingEmailVerificationGotItClickEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.page == that.page || this.page.equals(that.page)) && (this.biz_travel_referrer == that.biz_travel_referrer || this.biz_travel_referrer.equals(that.biz_travel_referrer))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.biz_travel_referrer.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelMobilePendingEmailVerificationGotItClickEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", page=" + this.page + ", biz_travel_referrer=" + this.biz_travel_referrer + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
