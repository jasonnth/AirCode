package com.airbnb.jitney.event.p304v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.KafkaMetadata */
public final class KafkaMetadata implements Struct {
    public static final Adapter<KafkaMetadata, Object> ADAPTER = new KafkaMetadataAdapter();
    public final Long offset;
    public final Integer partition;
    public final String topic;

    /* renamed from: com.airbnb.jitney.event.v1.KafkaMetadata$KafkaMetadataAdapter */
    private static final class KafkaMetadataAdapter implements Adapter<KafkaMetadata, Object> {
        private KafkaMetadataAdapter() {
        }

        public void write(Protocol protocol, KafkaMetadata struct) throws IOException {
            protocol.writeStructBegin("KafkaMetadata");
            protocol.writeFieldBegin("topic", 1, PassportService.SF_DG11);
            protocol.writeString(struct.topic);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("partition", 2, 8);
            protocol.writeI32(struct.partition.intValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("offset", 3, 10);
            protocol.writeI64(struct.offset.longValue());
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
        if (!(other instanceof KafkaMetadata)) {
            return false;
        }
        KafkaMetadata that = (KafkaMetadata) other;
        if ((this.topic == that.topic || this.topic.equals(that.topic)) && ((this.partition == that.partition || this.partition.equals(that.partition)) && (this.offset == that.offset || this.offset.equals(that.offset)))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.topic.hashCode()) * -2128831035) ^ this.partition.hashCode()) * -2128831035) ^ this.offset.hashCode()) * -2128831035;
    }

    public String toString() {
        return "KafkaMetadata{topic=" + this.topic + ", partition=" + this.partition + ", offset=" + this.offset + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
