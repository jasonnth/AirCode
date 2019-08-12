package com.airbnb.jitney.event.logging.ManageListing.p147v2;

import com.airbnb.jitney.event.logging.RegistrationManageListingPagesType.p223v1.C2607RegistrationManageListingPagesType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationClickApplyForRegistrationEvent */
public final class ManageListingCityRegistrationClickApplyForRegistrationEvent implements Struct {
    public static final Adapter<ManageListingCityRegistrationClickApplyForRegistrationEvent, Object> ADAPTER = new C2398xef45228e();
    public final Context context;
    public final String event_name;
    public final C2607RegistrationManageListingPagesType page;
    public final String regulatory_body;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationClickApplyForRegistrationEvent$ManageListingCityRegistrationClickApplyForRegistrationEventAdapter */
    private static final class C2398xef45228e implements Adapter<ManageListingCityRegistrationClickApplyForRegistrationEvent, Object> {
        private C2398xef45228e() {
        }

        public void write(Protocol protocol, ManageListingCityRegistrationClickApplyForRegistrationEvent struct) throws IOException {
            protocol.writeStructBegin("ManageListingCityRegistrationClickApplyForRegistrationEvent");
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
            protocol.writeFieldBegin("regulatory_body", 3, PassportService.SF_DG11);
            protocol.writeString(struct.regulatory_body);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 4, 8);
            protocol.writeI32(struct.page.value);
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
        if (!(other instanceof ManageListingCityRegistrationClickApplyForRegistrationEvent)) {
            return false;
        }
        ManageListingCityRegistrationClickApplyForRegistrationEvent that = (ManageListingCityRegistrationClickApplyForRegistrationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.regulatory_body == that.regulatory_body || this.regulatory_body.equals(that.regulatory_body)) && (this.page == that.page || this.page.equals(that.page)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.regulatory_body.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ManageListingCityRegistrationClickApplyForRegistrationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", regulatory_body=" + this.regulatory_body + ", page=" + this.page + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}