package com.airbnb.jitney.event.logging.ManageListing.p147v2;

import com.airbnb.jitney.event.logging.RegistrationManageListingPagesType.p223v1.C2607RegistrationManageListingPagesType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationTextInputEvent */
public final class ManageListingCityRegistrationTextInputEvent implements Struct {
    public static final Adapter<ManageListingCityRegistrationTextInputEvent, Object> ADAPTER = new ManageListingCityRegistrationTextInputEventAdapter();
    public final Context context;
    public final String event_name;
    public final String group_key;
    public final String input_key;
    public final C2607RegistrationManageListingPagesType page;
    public final String regulatory_body;
    public final String schema;
    public final String value;

    /* renamed from: com.airbnb.jitney.event.logging.ManageListing.v2.ManageListingCityRegistrationTextInputEvent$ManageListingCityRegistrationTextInputEventAdapter */
    private static final class ManageListingCityRegistrationTextInputEventAdapter implements Adapter<ManageListingCityRegistrationTextInputEvent, Object> {
        private ManageListingCityRegistrationTextInputEventAdapter() {
        }

        public void write(Protocol protocol, ManageListingCityRegistrationTextInputEvent struct) throws IOException {
            protocol.writeStructBegin("ManageListingCityRegistrationTextInputEvent");
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
            protocol.writeFieldBegin("input_key", 5, PassportService.SF_DG11);
            protocol.writeString(struct.input_key);
            protocol.writeFieldEnd();
            if (struct.group_key != null) {
                protocol.writeFieldBegin("group_key", 6, PassportService.SF_DG11);
                protocol.writeString(struct.group_key);
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof ManageListingCityRegistrationTextInputEvent)) {
            return false;
        }
        ManageListingCityRegistrationTextInputEvent that = (ManageListingCityRegistrationTextInputEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.regulatory_body == that.regulatory_body || this.regulatory_body.equals(that.regulatory_body)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.input_key == that.input_key || this.input_key.equals(that.input_key)) && (this.group_key == that.group_key || (this.group_key != null && this.group_key.equals(that.group_key))))))))) {
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
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.regulatory_body.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.input_key.hashCode()) * -2128831035) ^ (this.group_key == null ? 0 : this.group_key.hashCode())) * -2128831035;
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ManageListingCityRegistrationTextInputEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", regulatory_body=" + this.regulatory_body + ", page=" + this.page + ", input_key=" + this.input_key + ", group_key=" + this.group_key + ", value=" + this.value + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
