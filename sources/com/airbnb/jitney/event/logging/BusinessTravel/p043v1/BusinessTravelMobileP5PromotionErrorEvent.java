package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileP5PromotionErrorEvent */
public final class BusinessTravelMobileP5PromotionErrorEvent implements Struct {
    public static final Adapter<BusinessTravelMobileP5PromotionErrorEvent, Object> ADAPTER = new BusinessTravelMobileP5PromotionErrorEventAdapter();
    public final String confirmation_code;
    public final Context context;
    public final String email;
    public final String error_message;
    public final String event_name;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileP5PromotionErrorEvent$BusinessTravelMobileP5PromotionErrorEventAdapter */
    private static final class BusinessTravelMobileP5PromotionErrorEventAdapter implements Adapter<BusinessTravelMobileP5PromotionErrorEvent, Object> {
        private BusinessTravelMobileP5PromotionErrorEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelMobileP5PromotionErrorEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelMobileP5PromotionErrorEvent");
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
            protocol.writeFieldBegin("confirmation_code", 4, PassportService.SF_DG11);
            protocol.writeString(struct.confirmation_code);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("error_message", 5, PassportService.SF_DG11);
            protocol.writeString(struct.error_message);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("email", 6, PassportService.SF_DG11);
            protocol.writeString(struct.email);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelMobileP5PromotionErrorEvent)) {
            return false;
        }
        BusinessTravelMobileP5PromotionErrorEvent that = (BusinessTravelMobileP5PromotionErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.confirmation_code == that.confirmation_code || this.confirmation_code.equals(that.confirmation_code)) && ((this.error_message == that.error_message || this.error_message.equals(that.error_message)) && (this.email == that.email || this.email.equals(that.email)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.confirmation_code.hashCode()) * -2128831035) ^ this.error_message.hashCode()) * -2128831035) ^ this.email.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelMobileP5PromotionErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", confirmation_code=" + this.confirmation_code + ", error_message=" + this.error_message + ", email=" + this.email + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
