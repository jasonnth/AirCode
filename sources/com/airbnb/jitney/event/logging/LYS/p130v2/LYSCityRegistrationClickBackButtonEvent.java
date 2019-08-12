package com.airbnb.jitney.event.logging.LYS.p130v2;

import com.airbnb.jitney.event.logging.RegistrationLysPagesType.p222v1.C2606RegistrationLysPagesType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSCityRegistrationClickBackButtonEvent */
public final class LYSCityRegistrationClickBackButtonEvent implements Struct {
    public static final Adapter<LYSCityRegistrationClickBackButtonEvent, Object> ADAPTER = new LYSCityRegistrationClickBackButtonEventAdapter();
    public final Context context;
    public final String event_name;
    public final String group_key;
    public final C2606RegistrationLysPagesType page;
    public final String regulatory_body;
    public final String schema;
    public final String target_group_key;
    public final C2606RegistrationLysPagesType target_page;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSCityRegistrationClickBackButtonEvent$LYSCityRegistrationClickBackButtonEventAdapter */
    private static final class LYSCityRegistrationClickBackButtonEventAdapter implements Adapter<LYSCityRegistrationClickBackButtonEvent, Object> {
        private LYSCityRegistrationClickBackButtonEventAdapter() {
        }

        public void write(Protocol protocol, LYSCityRegistrationClickBackButtonEvent struct) throws IOException {
            protocol.writeStructBegin("LYSCityRegistrationClickBackButtonEvent");
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
            if (struct.target_page != null) {
                protocol.writeFieldBegin("target_page", 5, 8);
                protocol.writeI32(struct.target_page.value);
                protocol.writeFieldEnd();
            }
            if (struct.group_key != null) {
                protocol.writeFieldBegin("group_key", 6, PassportService.SF_DG11);
                protocol.writeString(struct.group_key);
                protocol.writeFieldEnd();
            }
            if (struct.target_group_key != null) {
                protocol.writeFieldBegin("target_group_key", 7, PassportService.SF_DG11);
                protocol.writeString(struct.target_group_key);
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
        if (!(other instanceof LYSCityRegistrationClickBackButtonEvent)) {
            return false;
        }
        LYSCityRegistrationClickBackButtonEvent that = (LYSCityRegistrationClickBackButtonEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.regulatory_body == that.regulatory_body || this.regulatory_body.equals(that.regulatory_body)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target_page == that.target_page || (this.target_page != null && this.target_page.equals(that.target_page))) && (this.group_key == that.group_key || (this.group_key != null && this.group_key.equals(that.group_key))))))))) {
            if (this.target_group_key == that.target_group_key) {
                return true;
            }
            if (this.target_group_key != null && this.target_group_key.equals(that.target_group_key)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.regulatory_body.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.target_page == null ? 0 : this.target_page.hashCode())) * -2128831035) ^ (this.group_key == null ? 0 : this.group_key.hashCode())) * -2128831035;
        if (this.target_group_key != null) {
            i = this.target_group_key.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "LYSCityRegistrationClickBackButtonEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", regulatory_body=" + this.regulatory_body + ", page=" + this.page + ", target_page=" + this.target_page + ", group_key=" + this.group_key + ", target_group_key=" + this.target_group_key + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
