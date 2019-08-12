package com.airbnb.jitney.event.logging.Rolodex.p236v1;

import com.airbnb.jitney.event.logging.Contact.p072v1.C1969Contact;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v1.RolodexMobileContactBatchUploadEvent */
public final class RolodexMobileContactBatchUploadEvent implements Struct {
    public static final Adapter<RolodexMobileContactBatchUploadEvent, Object> ADAPTER = new RolodexMobileContactBatchUploadEventAdapter();
    public final List<C1969Contact> contacts;
    public final Context context;
    public final String event_name;
    public final String import_uuid;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v1.RolodexMobileContactBatchUploadEvent$RolodexMobileContactBatchUploadEventAdapter */
    private static final class RolodexMobileContactBatchUploadEventAdapter implements Adapter<RolodexMobileContactBatchUploadEvent, Object> {
        private RolodexMobileContactBatchUploadEventAdapter() {
        }

        public void write(Protocol protocol, RolodexMobileContactBatchUploadEvent struct) throws IOException {
            protocol.writeStructBegin("RolodexMobileContactBatchUploadEvent");
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
            protocol.writeFieldBegin("import_uuid", 4, PassportService.SF_DG11);
            protocol.writeString(struct.import_uuid);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contacts", 5, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.contacts.size());
            for (C1969Contact item0 : struct.contacts) {
                C1969Contact.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
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
        if (!(other instanceof RolodexMobileContactBatchUploadEvent)) {
            return false;
        }
        RolodexMobileContactBatchUploadEvent that = (RolodexMobileContactBatchUploadEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.import_uuid == that.import_uuid || this.import_uuid.equals(that.import_uuid)) && (this.contacts == that.contacts || this.contacts.equals(that.contacts))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.import_uuid.hashCode()) * -2128831035) ^ this.contacts.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexMobileContactBatchUploadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", import_uuid=" + this.import_uuid + ", contacts=" + this.contacts + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
