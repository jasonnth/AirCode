package com.airbnb.jitney.event.logging.ManageListing.p147v2;

import com.airbnb.jitney.event.logging.RegistrationManageListingPagesType.p223v1.C2607RegistrationManageListingPagesType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationClickSaveExemptionEvent */
public final class ManageListingCityRegistrationClickSaveExemptionEvent implements Struct {
    public static final Adapter<ManageListingCityRegistrationClickSaveExemptionEvent, Object> ADAPTER = new ManageListingCityRegistrationClickSaveExemptionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2607RegistrationManageListingPagesType page;
    public final String regulatory_body;
    public final String schema;
    public final String value;

    /* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationClickSaveExemptionEvent$ManageListingCityRegistrationClickSaveExemptionEventAdapter */
    private static final class ManageListingCityRegistrationClickSaveExemptionEventAdapter implements Adapter<ManageListingCityRegistrationClickSaveExemptionEvent, Object> {
        private ManageListingCityRegistrationClickSaveExemptionEventAdapter() {
        }

        public void write(Protocol protocol, ManageListingCityRegistrationClickSaveExemptionEvent struct) throws IOException {
            protocol.writeStructBegin("ManageListingCityRegistrationClickSaveExemptionEvent");
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
            if (struct.value != null) {
                protocol.writeFieldBegin("value", 7, PassportService.SF_DG11);
                protocol.writeString(struct.value);
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof ManageListingCityRegistrationClickSaveExemptionEvent)) {
            return false;
        }
        ManageListingCityRegistrationClickSaveExemptionEvent that = (ManageListingCityRegistrationClickSaveExemptionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.regulatory_body == that.regulatory_body || this.regulatory_body.equals(that.regulatory_body)) && (this.page == that.page || this.page.equals(that.page)))))) {
            if (this.value == that.value) {
                return true;
            }
            if (this.value != null && this.value.equals(that.value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.regulatory_body.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035;
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ManageListingCityRegistrationClickSaveExemptionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", regulatory_body=" + this.regulatory_body + ", page=" + this.page + ", value=" + this.value + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
