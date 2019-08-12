package com.airbnb.jitney.event.p304v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.EventMetadata */
public final class EventMetadata implements Struct {
    public static final Adapter<EventMetadata, Builder> ADAPTER = new EventMetadataAdapter();
    public final String event_schema;

    /* renamed from: id */
    public final String f2696id;
    public final KafkaMetadata kafka_metadata;
    public final MessageType message_type;
    public final Map<String, String> properties;
    public final List<Tier> tiers;

    /* renamed from: com.airbnb.jitney.event.v1.EventMetadata$Builder */
    public static final class Builder implements StructBuilder<EventMetadata> {
        /* access modifiers changed from: private */
        public String event_schema;
        /* access modifiers changed from: private */

        /* renamed from: id */
        public String f2697id;
        /* access modifiers changed from: private */
        public KafkaMetadata kafka_metadata;
        /* access modifiers changed from: private */
        public MessageType message_type;
        /* access modifiers changed from: private */
        public Map<String, String> properties;
        /* access modifiers changed from: private */
        public List<Tier> tiers;

        private Builder() {
        }

        public Builder(String event_schema2, String id, List<Tier> tiers2) {
            this.event_schema = event_schema2;
            this.f2697id = id;
            this.tiers = tiers2;
        }

        public Builder message_type(MessageType message_type2) {
            this.message_type = message_type2;
            return this;
        }

        public EventMetadata build() {
            if (this.event_schema == null) {
                throw new IllegalStateException("Required field 'event_schema' is missing");
            } else if (this.f2697id == null) {
                throw new IllegalStateException("Required field 'id' is missing");
            } else if (this.tiers != null) {
                return new EventMetadata(this);
            } else {
                throw new IllegalStateException("Required field 'tiers' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.v1.EventMetadata$EventMetadataAdapter */
    private static final class EventMetadataAdapter implements Adapter<EventMetadata, Builder> {
        private EventMetadataAdapter() {
        }

        public void write(Protocol protocol, EventMetadata struct) throws IOException {
            protocol.writeStructBegin("EventMetadata");
            protocol.writeFieldBegin("event_schema", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_schema);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.f2696id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("tiers", 3, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.tiers.size());
            for (Tier item0 : struct.tiers) {
                Tier.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.kafka_metadata != null) {
                protocol.writeFieldBegin("kafka_metadata", 4, PassportService.SF_DG12);
                KafkaMetadata.ADAPTER.write(protocol, struct.kafka_metadata);
                protocol.writeFieldEnd();
            }
            if (struct.properties != null) {
                protocol.writeFieldBegin("properties", 5, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.properties.size());
                for (Entry<String, String> entry0 : struct.properties.entrySet()) {
                    String value0 = (String) entry0.getValue();
                    protocol.writeString((String) entry0.getKey());
                    protocol.writeString(value0);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            if (struct.message_type != null) {
                protocol.writeFieldBegin("message_type", 6, 8);
                protocol.writeI32(struct.message_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private EventMetadata(Builder builder) {
        this.event_schema = builder.event_schema;
        this.f2696id = builder.f2697id;
        this.tiers = Collections.unmodifiableList(builder.tiers);
        this.kafka_metadata = builder.kafka_metadata;
        this.properties = builder.properties == null ? null : Collections.unmodifiableMap(builder.properties);
        this.message_type = builder.message_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof EventMetadata)) {
            return false;
        }
        EventMetadata that = (EventMetadata) other;
        if ((this.event_schema == that.event_schema || this.event_schema.equals(that.event_schema)) && ((this.f2696id == that.f2696id || this.f2696id.equals(that.f2696id)) && ((this.tiers == that.tiers || this.tiers.equals(that.tiers)) && ((this.kafka_metadata == that.kafka_metadata || (this.kafka_metadata != null && this.kafka_metadata.equals(that.kafka_metadata))) && (this.properties == that.properties || (this.properties != null && this.properties.equals(that.properties))))))) {
            if (this.message_type == that.message_type) {
                return true;
            }
            if (this.message_type != null && this.message_type.equals(that.message_type)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ this.event_schema.hashCode()) * -2128831035) ^ this.f2696id.hashCode()) * -2128831035) ^ this.tiers.hashCode()) * -2128831035) ^ (this.kafka_metadata == null ? 0 : this.kafka_metadata.hashCode())) * -2128831035) ^ (this.properties == null ? 0 : this.properties.hashCode())) * -2128831035;
        if (this.message_type != null) {
            i = this.message_type.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "EventMetadata{event_schema=" + this.event_schema + ", id=" + this.f2696id + ", tiers=" + this.tiers + ", kafka_metadata=" + this.kafka_metadata + ", properties=" + this.properties + ", message_type=" + this.message_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
