package com.airbnb.jitney.event.logging.BusinessTravel.p044v2;

import com.airbnb.jitney.event.logging.BizTravelReferrer.p041v1.C1805BizTravelReferrer;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobileAddEmailSubmitErrorEvent */
public final class BusinessTravelMobileAddEmailSubmitErrorEvent implements Struct {
    public static final Adapter<BusinessTravelMobileAddEmailSubmitErrorEvent, Builder> ADAPTER = new BusinessTravelMobileAddEmailSubmitErrorEventAdapter();
    public final C1805BizTravelReferrer biz_travel_referrer;
    public final Context context;
    public final String email;
    public final String error_message;
    public final String event_name;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobileAddEmailSubmitErrorEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelMobileAddEmailSubmitErrorEvent> {
        /* access modifiers changed from: private */
        public C1805BizTravelReferrer biz_travel_referrer;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String email;
        /* access modifiers changed from: private */
        public String error_message;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobileAddEmailSubmitErrorEvent:2.0.0";
            this.event_name = "businesstravel_mobile_add_email_submit_error";
            this.page = "mobile_add_email";
        }

        public Builder(Context context2, String error_message2, String email2, C1805BizTravelReferrer biz_travel_referrer2) {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelMobileAddEmailSubmitErrorEvent:2.0.0";
            this.event_name = "businesstravel_mobile_add_email_submit_error";
            this.context = context2;
            this.page = "mobile_add_email";
            this.error_message = error_message2;
            this.email = email2;
            this.biz_travel_referrer = biz_travel_referrer2;
        }

        public BusinessTravelMobileAddEmailSubmitErrorEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.error_message == null) {
                throw new IllegalStateException("Required field 'error_message' is missing");
            } else if (this.email == null) {
                throw new IllegalStateException("Required field 'email' is missing");
            } else if (this.biz_travel_referrer != null) {
                return new BusinessTravelMobileAddEmailSubmitErrorEvent(this);
            } else {
                throw new IllegalStateException("Required field 'biz_travel_referrer' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v2.BusinessTravelMobileAddEmailSubmitErrorEvent$BusinessTravelMobileAddEmailSubmitErrorEventAdapter */
    private static final class BusinessTravelMobileAddEmailSubmitErrorEventAdapter implements Adapter<BusinessTravelMobileAddEmailSubmitErrorEvent, Builder> {
        private BusinessTravelMobileAddEmailSubmitErrorEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelMobileAddEmailSubmitErrorEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelMobileAddEmailSubmitErrorEvent");
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
            protocol.writeFieldBegin("error_message", 4, PassportService.SF_DG11);
            protocol.writeString(struct.error_message);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("email", 5, PassportService.SF_DG11);
            protocol.writeString(struct.email);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("biz_travel_referrer", 6, 8);
            protocol.writeI32(struct.biz_travel_referrer.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private BusinessTravelMobileAddEmailSubmitErrorEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.error_message = builder.error_message;
        this.email = builder.email;
        this.biz_travel_referrer = builder.biz_travel_referrer;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelMobileAddEmailSubmitErrorEvent)) {
            return false;
        }
        BusinessTravelMobileAddEmailSubmitErrorEvent that = (BusinessTravelMobileAddEmailSubmitErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.error_message == that.error_message || this.error_message.equals(that.error_message)) && ((this.email == that.email || this.email.equals(that.email)) && (this.biz_travel_referrer == that.biz_travel_referrer || this.biz_travel_referrer.equals(that.biz_travel_referrer)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.error_message.hashCode()) * -2128831035) ^ this.email.hashCode()) * -2128831035) ^ this.biz_travel_referrer.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelMobileAddEmailSubmitErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", error_message=" + this.error_message + ", email=" + this.email + ", biz_travel_referrer=" + this.biz_travel_referrer + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
