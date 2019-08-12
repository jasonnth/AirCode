package com.airbnb.jitney.event.logging.Rolodex.p236v1;

import com.airbnb.jitney.event.logging.ContactBookImportType.p074v1.C1973ContactBookImportType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v1.RolodexMobileContactBookUploadEvent */
public final class RolodexMobileContactBookUploadEvent implements Struct {
    public static final Adapter<RolodexMobileContactBookUploadEvent, Object> ADAPTER = new RolodexMobileContactBookUploadEventAdapter();
    public final Context context;
    public final String device_id;
    public final String event_name;
    public final C1973ContactBookImportType import_type;
    public final String import_uuid;
    public final Boolean is_delta;
    public final Boolean is_new;
    public final Long num_contacts;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v1.RolodexMobileContactBookUploadEvent$RolodexMobileContactBookUploadEventAdapter */
    private static final class RolodexMobileContactBookUploadEventAdapter implements Adapter<RolodexMobileContactBookUploadEvent, Object> {
        private RolodexMobileContactBookUploadEventAdapter() {
        }

        public void write(Protocol protocol, RolodexMobileContactBookUploadEvent struct) throws IOException {
            protocol.writeStructBegin("RolodexMobileContactBookUploadEvent");
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
            protocol.writeFieldBegin("user_id", 3, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("import_type", 4, 8);
            protocol.writeI32(struct.import_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("num_contacts", 5, 10);
            protocol.writeI64(struct.num_contacts.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("import_uuid", 6, PassportService.SF_DG11);
            protocol.writeString(struct.import_uuid);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_new", 7, 2);
            protocol.writeBool(struct.is_new.booleanValue());
            protocol.writeFieldEnd();
            if (struct.is_delta != null) {
                protocol.writeFieldBegin("is_delta", 8, 2);
                protocol.writeBool(struct.is_delta.booleanValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("device_id", 9, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
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
        if (!(other instanceof RolodexMobileContactBookUploadEvent)) {
            return false;
        }
        RolodexMobileContactBookUploadEvent that = (RolodexMobileContactBookUploadEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.import_type == that.import_type || this.import_type.equals(that.import_type)) && ((this.num_contacts == that.num_contacts || this.num_contacts.equals(that.num_contacts)) && ((this.import_uuid == that.import_uuid || this.import_uuid.equals(that.import_uuid)) && ((this.is_new == that.is_new || this.is_new.equals(that.is_new)) && ((this.is_delta == that.is_delta || (this.is_delta != null && this.is_delta.equals(that.is_delta))) && (this.device_id == that.device_id || this.device_id.equals(that.device_id))))))))))) {
            return true;
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.import_type.hashCode()) * -2128831035) ^ this.num_contacts.hashCode()) * -2128831035) ^ this.import_uuid.hashCode()) * -2128831035) ^ this.is_new.hashCode()) * -2128831035;
        if (this.is_delta != null) {
            i = this.is_delta.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.device_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexMobileContactBookUploadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", import_type=" + this.import_type + ", num_contacts=" + this.num_contacts + ", import_uuid=" + this.import_uuid + ", is_new=" + this.is_new + ", is_delta=" + this.is_delta + ", device_id=" + this.device_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
