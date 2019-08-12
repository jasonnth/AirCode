package com.airbnb.jitney.event.logging.SchedulableInfo.p244v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.SchedulableInfo.v1.SchedulableInfo */
public final class C2716SchedulableInfo implements Struct {
    public static final Adapter<C2716SchedulableInfo, Object> ADAPTER = new SchedulableInfoAdapter();
    public final Long schedulable_id;
    public final String schedulable_type;

    /* renamed from: com.airbnb.jitney.event.logging.SchedulableInfo.v1.SchedulableInfo$SchedulableInfoAdapter */
    private static final class SchedulableInfoAdapter implements Adapter<C2716SchedulableInfo, Object> {
        private SchedulableInfoAdapter() {
        }

        public void write(Protocol protocol, C2716SchedulableInfo struct) throws IOException {
            protocol.writeStructBegin("SchedulableInfo");
            protocol.writeFieldBegin("schedulable_type", 1, PassportService.SF_DG11);
            protocol.writeString(struct.schedulable_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("schedulable_id", 2, 10);
            protocol.writeI64(struct.schedulable_id.longValue());
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
        if (!(other instanceof C2716SchedulableInfo)) {
            return false;
        }
        C2716SchedulableInfo that = (C2716SchedulableInfo) other;
        if ((this.schedulable_type == that.schedulable_type || this.schedulable_type.equals(that.schedulable_type)) && (this.schedulable_id == that.schedulable_id || this.schedulable_id.equals(that.schedulable_id))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.schedulable_type.hashCode()) * -2128831035) ^ this.schedulable_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SchedulableInfo{schedulable_type=" + this.schedulable_type + ", schedulable_id=" + this.schedulable_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
