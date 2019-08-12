package com.airbnb.jitney.event.logging.ContactBookImport.p073v1;

import com.airbnb.jitney.event.logging.ContactBookImportType.p074v1.C1973ContactBookImportType;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ContactBookImport.v1.ContactBookImport */
public final class C1971ContactBookImport implements Struct {
    public static final Adapter<C1971ContactBookImport, Builder> ADAPTER = new ContactBookImportAdapter();
    public final String device_id;
    public final C1973ContactBookImportType import_type;
    public final String import_uuid;
    public final Boolean is_delta;
    public final Boolean is_new;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.ContactBookImport.v1.ContactBookImport$Builder */
    public static final class Builder implements StructBuilder<C1971ContactBookImport> {
        /* access modifiers changed from: private */
        public String device_id;
        /* access modifiers changed from: private */
        public C1973ContactBookImportType import_type;
        /* access modifiers changed from: private */
        public String import_uuid;
        /* access modifiers changed from: private */
        public Boolean is_delta;
        /* access modifiers changed from: private */
        public Boolean is_new;
        /* access modifiers changed from: private */
        public Long user_id;

        private Builder() {
        }

        public Builder(Long user_id2, C1973ContactBookImportType import_type2, String import_uuid2, Boolean is_new2, String device_id2) {
            this.user_id = user_id2;
            this.import_type = import_type2;
            this.import_uuid = import_uuid2;
            this.is_new = is_new2;
            this.device_id = device_id2;
        }

        public Builder is_delta(Boolean is_delta2) {
            this.is_delta = is_delta2;
            return this;
        }

        public C1971ContactBookImport build() {
            if (this.user_id == null) {
                throw new IllegalStateException("Required field 'user_id' is missing");
            } else if (this.import_type == null) {
                throw new IllegalStateException("Required field 'import_type' is missing");
            } else if (this.import_uuid == null) {
                throw new IllegalStateException("Required field 'import_uuid' is missing");
            } else if (this.is_new == null) {
                throw new IllegalStateException("Required field 'is_new' is missing");
            } else if (this.device_id != null) {
                return new C1971ContactBookImport(this);
            } else {
                throw new IllegalStateException("Required field 'device_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ContactBookImport.v1.ContactBookImport$ContactBookImportAdapter */
    private static final class ContactBookImportAdapter implements Adapter<C1971ContactBookImport, Builder> {
        private ContactBookImportAdapter() {
        }

        public void write(Protocol protocol, C1971ContactBookImport struct) throws IOException {
            protocol.writeStructBegin("ContactBookImport");
            protocol.writeFieldBegin("user_id", 1, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("import_type", 2, 8);
            protocol.writeI32(struct.import_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("import_uuid", 3, PassportService.SF_DG11);
            protocol.writeString(struct.import_uuid);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_new", 4, 2);
            protocol.writeBool(struct.is_new.booleanValue());
            protocol.writeFieldEnd();
            if (struct.is_delta != null) {
                protocol.writeFieldBegin("is_delta", 5, 2);
                protocol.writeBool(struct.is_delta.booleanValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("device_id", 6, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C1971ContactBookImport(Builder builder) {
        this.user_id = builder.user_id;
        this.import_type = builder.import_type;
        this.import_uuid = builder.import_uuid;
        this.is_new = builder.is_new;
        this.is_delta = builder.is_delta;
        this.device_id = builder.device_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C1971ContactBookImport)) {
            return false;
        }
        C1971ContactBookImport that = (C1971ContactBookImport) other;
        if ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.import_type == that.import_type || this.import_type.equals(that.import_type)) && ((this.import_uuid == that.import_uuid || this.import_uuid.equals(that.import_uuid)) && ((this.is_new == that.is_new || this.is_new.equals(that.is_new)) && ((this.is_delta == that.is_delta || (this.is_delta != null && this.is_delta.equals(that.is_delta))) && (this.device_id == that.device_id || this.device_id.equals(that.device_id))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ this.user_id.hashCode()) * -2128831035) ^ this.import_type.hashCode()) * -2128831035) ^ this.import_uuid.hashCode()) * -2128831035) ^ this.is_new.hashCode()) * -2128831035) ^ (this.is_delta == null ? 0 : this.is_delta.hashCode())) * -2128831035) ^ this.device_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ContactBookImport{user_id=" + this.user_id + ", import_type=" + this.import_type + ", import_uuid=" + this.import_uuid + ", is_new=" + this.is_new + ", is_delta=" + this.is_delta + ", device_id=" + this.device_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
