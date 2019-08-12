package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.CandidateMessageData */
public final class CandidateMessageData implements Struct {
    public static final Adapter<CandidateMessageData, Object> ADAPTER = new CandidateMessageDataAdapter();
    public final Boolean eligible;
    public final List<KeyValueThreshold> key_value_threshold;
    public final String message_type;
    public final Boolean winner;

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.CandidateMessageData$CandidateMessageDataAdapter */
    private static final class CandidateMessageDataAdapter implements Adapter<CandidateMessageData, Object> {
        private CandidateMessageDataAdapter() {
        }

        public void write(Protocol protocol, CandidateMessageData struct) throws IOException {
            protocol.writeStructBegin("CandidateMessageData");
            if (struct.message_type != null) {
                protocol.writeFieldBegin("message_type", 1, PassportService.SF_DG11);
                protocol.writeString(struct.message_type);
                protocol.writeFieldEnd();
            }
            if (struct.eligible != null) {
                protocol.writeFieldBegin("eligible", 2, 2);
                protocol.writeBool(struct.eligible.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.winner != null) {
                protocol.writeFieldBegin("winner", 3, 2);
                protocol.writeBool(struct.winner.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.key_value_threshold != null) {
                protocol.writeFieldBegin("key_value_threshold", 4, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.key_value_threshold.size());
                for (KeyValueThreshold item0 : struct.key_value_threshold) {
                    KeyValueThreshold.ADAPTER.write(protocol, item0);
                }
                protocol.writeListEnd();
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
        if (!(other instanceof CandidateMessageData)) {
            return false;
        }
        CandidateMessageData that = (CandidateMessageData) other;
        if ((this.message_type == that.message_type || (this.message_type != null && this.message_type.equals(that.message_type))) && ((this.eligible == that.eligible || (this.eligible != null && this.eligible.equals(that.eligible))) && (this.winner == that.winner || (this.winner != null && this.winner.equals(that.winner))))) {
            if (this.key_value_threshold == that.key_value_threshold) {
                return true;
            }
            if (this.key_value_threshold != null && this.key_value_threshold.equals(that.key_value_threshold)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.message_type == null ? 0 : this.message_type.hashCode())) * -2128831035) ^ (this.eligible == null ? 0 : this.eligible.hashCode())) * -2128831035) ^ (this.winner == null ? 0 : this.winner.hashCode())) * -2128831035;
        if (this.key_value_threshold != null) {
            i = this.key_value_threshold.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CandidateMessageData{message_type=" + this.message_type + ", eligible=" + this.eligible + ", winner=" + this.winner + ", key_value_threshold=" + this.key_value_threshold + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
