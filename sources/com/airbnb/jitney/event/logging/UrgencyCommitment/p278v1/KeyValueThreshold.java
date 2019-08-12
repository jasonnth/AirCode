package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.KeyValueThreshold */
public final class KeyValueThreshold implements Struct {
    public static final Adapter<KeyValueThreshold, Object> ADAPTER = new KeyValueThresholdAdapter();
    public final String key;
    public final Double threshold;
    public final Double value;

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.KeyValueThreshold$KeyValueThresholdAdapter */
    private static final class KeyValueThresholdAdapter implements Adapter<KeyValueThreshold, Object> {
        private KeyValueThresholdAdapter() {
        }

        public void write(Protocol protocol, KeyValueThreshold struct) throws IOException {
            protocol.writeStructBegin("KeyValueThreshold");
            if (struct.key != null) {
                protocol.writeFieldBegin("key", 1, PassportService.SF_DG11);
                protocol.writeString(struct.key);
                protocol.writeFieldEnd();
            }
            if (struct.value != null) {
                protocol.writeFieldBegin("value", 2, 4);
                protocol.writeDouble(struct.value.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.threshold != null) {
                protocol.writeFieldBegin("threshold", 3, 4);
                protocol.writeDouble(struct.threshold.doubleValue());
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof KeyValueThreshold)) {
            return false;
        }
        KeyValueThreshold that = (KeyValueThreshold) other;
        if ((this.key == that.key || (this.key != null && this.key.equals(that.key))) && (this.value == that.value || (this.value != null && this.value.equals(that.value)))) {
            if (this.threshold == that.threshold) {
                return true;
            }
            if (this.threshold != null && this.threshold.equals(that.threshold)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((16777619 ^ (this.key == null ? 0 : this.key.hashCode())) * -2128831035) ^ (this.value == null ? 0 : this.value.hashCode())) * -2128831035;
        if (this.threshold != null) {
            i = this.threshold.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "KeyValueThreshold{key=" + this.key + ", value=" + this.value + ", threshold=" + this.threshold + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
