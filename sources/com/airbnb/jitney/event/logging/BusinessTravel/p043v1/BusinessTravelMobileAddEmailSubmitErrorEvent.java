package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileAddEmailSubmitErrorEvent */
public final class BusinessTravelMobileAddEmailSubmitErrorEvent implements Struct {
    public static final Adapter<BusinessTravelMobileAddEmailSubmitErrorEvent, Object> ADAPTER = new BusinessTravelMobileAddEmailSubmitErrorEventAdapter();
    public final Context context;
    public final String email;
    public final String error_message;
    public final String event_name;
    public final String page;
    public final String referrer;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelMobileAddEmailSubmitErrorEvent$BusinessTravelMobileAddEmailSubmitErrorEventAdapter */
    private static final class BusinessTravelMobileAddEmailSubmitErrorEventAdapter implements Adapter<BusinessTravelMobileAddEmailSubmitErrorEvent, Object> {
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
            protocol.writeFieldBegin("referrer", 6, PassportService.SF_DG11);
            protocol.writeString(struct.referrer);
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
        if (!(other instanceof BusinessTravelMobileAddEmailSubmitErrorEvent)) {
            return false;
        }
        BusinessTravelMobileAddEmailSubmitErrorEvent that = (BusinessTravelMobileAddEmailSubmitErrorEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.error_message == that.error_message || this.error_message.equals(that.error_message)) && ((this.email == that.email || this.email.equals(that.email)) && (this.referrer == that.referrer || this.referrer.equals(that.referrer)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.error_message.hashCode()) * -2128831035) ^ this.email.hashCode()) * -2128831035) ^ this.referrer.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelMobileAddEmailSubmitErrorEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", error_message=" + this.error_message + ", email=" + this.email + ", referrer=" + this.referrer + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
