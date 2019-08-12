package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.CollisionData */
public final class CollisionData implements Struct {
    public static final Adapter<CollisionData, Object> ADAPTER = new CollisionDataAdapter();
    public final List<CandidateMessageData> message_data;
    public final List<String> priority_list;
    public final List<CandidateMessageData> prospective_message_data;
    public final CollisionResolvingStrategy strategy;

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.CollisionData$CollisionDataAdapter */
    private static final class CollisionDataAdapter implements Adapter<CollisionData, Object> {
        private CollisionDataAdapter() {
        }

        public void write(Protocol protocol, CollisionData struct) throws IOException {
            protocol.writeStructBegin("CollisionData");
            if (struct.message_data != null) {
                protocol.writeFieldBegin("message_data", 1, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.message_data.size());
                for (CandidateMessageData item0 : struct.message_data) {
                    CandidateMessageData.ADAPTER.write(protocol, item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.strategy != null) {
                protocol.writeFieldBegin("strategy", 2, 8);
                protocol.writeI32(struct.strategy.value);
                protocol.writeFieldEnd();
            }
            if (struct.priority_list != null) {
                protocol.writeFieldBegin("priority_list", 3, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.priority_list.size());
                for (String item02 : struct.priority_list) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.prospective_message_data != null) {
                protocol.writeFieldBegin("prospective_message_data", 4, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.prospective_message_data.size());
                for (CandidateMessageData item03 : struct.prospective_message_data) {
                    CandidateMessageData.ADAPTER.write(protocol, item03);
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
        if (!(other instanceof CollisionData)) {
            return false;
        }
        CollisionData that = (CollisionData) other;
        if ((this.message_data == that.message_data || (this.message_data != null && this.message_data.equals(that.message_data))) && ((this.strategy == that.strategy || (this.strategy != null && this.strategy.equals(that.strategy))) && (this.priority_list == that.priority_list || (this.priority_list != null && this.priority_list.equals(that.priority_list))))) {
            if (this.prospective_message_data == that.prospective_message_data) {
                return true;
            }
            if (this.prospective_message_data != null && this.prospective_message_data.equals(that.prospective_message_data)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.message_data == null ? 0 : this.message_data.hashCode())) * -2128831035) ^ (this.strategy == null ? 0 : this.strategy.hashCode())) * -2128831035) ^ (this.priority_list == null ? 0 : this.priority_list.hashCode())) * -2128831035;
        if (this.prospective_message_data != null) {
            i = this.prospective_message_data.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CollisionData{message_data=" + this.message_data + ", strategy=" + this.strategy + ", priority_list=" + this.priority_list + ", prospective_message_data=" + this.prospective_message_data + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
