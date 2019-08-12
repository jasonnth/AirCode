package com.airbnb.jitney.event.logging.Rolodex.p237v2;

import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.jitney.event.logging.Contact.p072v1.C1969Contact;
import com.airbnb.jitney.event.logging.ContactBookImport.p073v1.C1971ContactBookImport;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexMobileContactUploadEvent */
public final class RolodexMobileContactUploadEvent implements Struct {
    public static final Adapter<RolodexMobileContactUploadEvent, Object> ADAPTER = new RolodexMobileContactUploadEventAdapter();
    public final C1969Contact contact;
    public final C1971ContactBookImport contact_book_import;
    public final Context context;
    public final String event_name;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexMobileContactUploadEvent$RolodexMobileContactUploadEventAdapter */
    private static final class RolodexMobileContactUploadEventAdapter implements Adapter<RolodexMobileContactUploadEvent, Object> {
        private RolodexMobileContactUploadEventAdapter() {
        }

        public void write(Protocol protocol, RolodexMobileContactUploadEvent struct) throws IOException {
            protocol.writeStructBegin("RolodexMobileContactUploadEvent");
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
            protocol.writeFieldBegin(SecurityCheckAnalytics.PAGE_CONTACT_IMPRESSION, 4, PassportService.SF_DG12);
            C1969Contact.ADAPTER.write(protocol, struct.contact);
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
        if (!(other instanceof RolodexMobileContactUploadEvent)) {
            return false;
        }
        RolodexMobileContactUploadEvent that = (RolodexMobileContactUploadEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.contact_book_import == that.contact_book_import || this.contact_book_import.equals(that.contact_book_import)) && (this.contact == that.contact || this.contact.equals(that.contact)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.contact_book_import.hashCode()) * -2128831035) ^ this.contact.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexMobileContactUploadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", contact_book_import=" + this.contact_book_import + ", contact=" + this.contact + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
