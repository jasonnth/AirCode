package com.airbnb.jitney.event.p304v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.Sequence */
public final class Sequence implements Struct {
    public static final Adapter<Sequence, Object> ADAPTER = new SequenceAdapter();
    public final Long bytes_sent;
    public final List<String> key;
    public final Long value;

    /* renamed from: com.airbnb.jitney.event.v1.Sequence$SequenceAdapter */
    private static final class SequenceAdapter implements Adapter<Sequence, Object> {
        private SequenceAdapter() {
        }

        public void write(Protocol protocol, Sequence struct) throws IOException {
            protocol.writeStructBegin("Sequence");
            protocol.writeFieldBegin("key", 1, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.key.size());
            for (String item0 : struct.key) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("value", 2, 10);
            protocol.writeI64(struct.value.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("bytes_sent", 3, 10);
            protocol.writeI64(struct.bytes_sent.longValue());
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
        if (!(other instanceof Sequence)) {
            return false;
        }
        Sequence that = (Sequence) other;
        if ((this.key == that.key || this.key.equals(that.key)) && ((this.value == that.value || this.value.equals(that.value)) && (this.bytes_sent == that.bytes_sent || this.bytes_sent.equals(that.bytes_sent)))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.key.hashCode()) * -2128831035) ^ this.value.hashCode()) * -2128831035) ^ this.bytes_sent.hashCode()) * -2128831035;
    }

    public String toString() {
        return "Sequence{key=" + this.key + ", value=" + this.value + ", bytes_sent=" + this.bytes_sent + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
