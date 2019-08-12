package com.airbnb.jitney.event.logging.Rolodex.p238v3;

import com.airbnb.jitney.event.logging.Contact.p072v1.C1969Contact;
import com.airbnb.jitney.event.logging.ContactBookImport.p073v1.C1971ContactBookImport;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v3.RolodexMobileContactBatchUploadEvent */
public final class RolodexMobileContactBatchUploadEvent implements Struct {
    public static final Adapter<RolodexMobileContactBatchUploadEvent, Builder> ADAPTER = new RolodexMobileContactBatchUploadEventAdapter();
    public final C1971ContactBookImport contact_book_import;
    public final List<C1969Contact> contacts;
    public final Long contacts_count;
    public final Context context;
    public final String event_name;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v3.RolodexMobileContactBatchUploadEvent$Builder */
    public static final class Builder implements StructBuilder<RolodexMobileContactBatchUploadEvent> {
        /* access modifiers changed from: private */
        public C1971ContactBookImport contact_book_import;
        /* access modifiers changed from: private */
        public List<C1969Contact> contacts;
        /* access modifiers changed from: private */
        public Long contacts_count;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "rolodex_mobile_contact_batch_upload";
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Rolodex:RolodexMobileContactBatchUploadEvent:3.0.0";
        /* access modifiers changed from: private */
        public Long user_id;

        private Builder() {
        }

        public Builder(Context context2, Long user_id2, C1971ContactBookImport contact_book_import2, List<C1969Contact> contacts2, Long contacts_count2) {
            this.context = context2;
            this.user_id = user_id2;
            this.contact_book_import = contact_book_import2;
            this.contacts = contacts2;
            this.contacts_count = contacts_count2;
        }

        public RolodexMobileContactBatchUploadEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.user_id == null) {
                throw new IllegalStateException("Required field 'user_id' is missing");
            } else if (this.contact_book_import == null) {
                throw new IllegalStateException("Required field 'contact_book_import' is missing");
            } else if (this.contacts == null) {
                throw new IllegalStateException("Required field 'contacts' is missing");
            } else if (this.contacts_count != null) {
                return new RolodexMobileContactBatchUploadEvent(this);
            } else {
                throw new IllegalStateException("Required field 'contacts_count' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v3.RolodexMobileContactBatchUploadEvent$RolodexMobileContactBatchUploadEventAdapter */
    private static final class RolodexMobileContactBatchUploadEventAdapter implements Adapter<RolodexMobileContactBatchUploadEvent, Builder> {
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
            protocol.writeFieldBegin("contact_book_import", 4, PassportService.SF_DG12);
            C1971ContactBookImport.ADAPTER.write(protocol, struct.contact_book_import);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contacts", 5, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.contacts.size());
            for (C1969Contact item0 : struct.contacts) {
                C1969Contact.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contacts_count", 6, 10);
            protocol.writeI64(struct.contacts_count.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private RolodexMobileContactBatchUploadEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.user_id = builder.user_id;
        this.contact_book_import = builder.contact_book_import;
        this.contacts = Collections.unmodifiableList(builder.contacts);
        this.contacts_count = builder.contacts_count;
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.contact_book_import == that.contact_book_import || this.contact_book_import.equals(that.contact_book_import)) && ((this.contacts == that.contacts || this.contacts.equals(that.contacts)) && (this.contacts_count == that.contacts_count || this.contacts_count.equals(that.contacts_count)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.contact_book_import.hashCode()) * -2128831035) ^ this.contacts.hashCode()) * -2128831035) ^ this.contacts_count.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexMobileContactBatchUploadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", contact_book_import=" + this.contact_book_import + ", contacts=" + this.contacts + ", contacts_count=" + this.contacts_count + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
