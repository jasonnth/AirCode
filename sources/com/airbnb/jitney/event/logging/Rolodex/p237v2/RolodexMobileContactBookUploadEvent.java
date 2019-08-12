package com.airbnb.jitney.event.logging.Rolodex.p237v2;

import com.airbnb.jitney.event.logging.ContactBookImport.p073v1.C1971ContactBookImport;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexMobileContactBookUploadEvent */
public final class RolodexMobileContactBookUploadEvent implements Struct {
    public static final Adapter<RolodexMobileContactBookUploadEvent, Object> ADAPTER = new RolodexMobileContactBookUploadEventAdapter();
    public final C1971ContactBookImport contact_book_import;
    public final Context context;
    public final String event_name;
    public final Long num_contacts;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexMobileContactBookUploadEvent$RolodexMobileContactBookUploadEventAdapter */
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
            protocol.writeFieldBegin("contact_book_import", 3, PassportService.SF_DG12);
            C1971ContactBookImport.ADAPTER.write(protocol, struct.contact_book_import);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("num_contacts", 4, 10);
            protocol.writeI64(struct.num_contacts.longValue());
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.contact_book_import == that.contact_book_import || this.contact_book_import.equals(that.contact_book_import)) && (this.num_contacts == that.num_contacts || this.num_contacts.equals(that.num_contacts)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.contact_book_import.hashCode()) * -2128831035) ^ this.num_contacts.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexMobileContactBookUploadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", contact_book_import=" + this.contact_book_import + ", num_contacts=" + this.num_contacts + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
