package com.airbnb.jitney.event.p304v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import okio.ByteString;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.RawMessage */
public final class RawMessage implements Struct {
    public static final Adapter<RawMessage, Builder> ADAPTER = new RawMessageAdapter();
    public final EventMetadata metadata;
    public final ByteString raw_event;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.v1.RawMessage$Builder */
    public static final class Builder implements StructBuilder<RawMessage> {
        /* access modifiers changed from: private */
        public EventMetadata metadata;
        /* access modifiers changed from: private */
        public ByteString raw_event;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event:RawMessage:1.1.2";

        private Builder() {
        }

        public Builder(EventMetadata metadata2, ByteString raw_event2) {
            this.metadata = metadata2;
            this.raw_event = raw_event2;
        }

        public RawMessage build() {
            if (this.metadata == null) {
                throw new IllegalStateException("Required field 'metadata' is missing");
            } else if (this.raw_event != null) {
                return new RawMessage(this);
            } else {
                throw new IllegalStateException("Required field 'raw_event' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.v1.RawMessage$RawMessageAdapter */
    private static final class RawMessageAdapter implements Adapter<RawMessage, Builder> {
        private RawMessageAdapter() {
        }

        public void write(Protocol protocol, RawMessage struct) throws IOException {
            protocol.writeStructBegin("RawMessage");
            protocol.writeFieldBegin("metadata", 1, PassportService.SF_DG12);
            EventMetadata.ADAPTER.write(protocol, struct.metadata);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("raw_event", 2, PassportService.SF_DG11);
            protocol.writeBinary(struct.raw_event);
            protocol.writeFieldEnd();
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private RawMessage(Builder builder) {
        this.metadata = builder.metadata;
        this.raw_event = builder.raw_event;
        this.schema = builder.schema;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof RawMessage)) {
            return false;
        }
        RawMessage that = (RawMessage) other;
        if ((this.metadata == that.metadata || this.metadata.equals(that.metadata)) && (this.raw_event == that.raw_event || this.raw_event.equals(that.raw_event))) {
            if (this.schema == that.schema) {
                return true;
            }
            if (this.schema != null && this.schema.equals(that.schema)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.metadata.hashCode()) * -2128831035) ^ this.raw_event.hashCode()) * -2128831035) ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035;
    }

    public String toString() {
        return "RawMessage{metadata=" + this.metadata + ", raw_event=" + this.raw_event + ", schema=" + this.schema + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
