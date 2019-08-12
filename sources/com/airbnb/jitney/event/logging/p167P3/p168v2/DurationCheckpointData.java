package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.DurationCheckpointData */
public final class DurationCheckpointData implements Struct {
    public static final Adapter<DurationCheckpointData, Object> ADAPTER = new DurationCheckpointDataAdapter();
    public final Integer page_view_duration;
    public final Integer total_view_duration;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.DurationCheckpointData$DurationCheckpointDataAdapter */
    private static final class DurationCheckpointDataAdapter implements Adapter<DurationCheckpointData, Object> {
        private DurationCheckpointDataAdapter() {
        }

        public void write(Protocol protocol, DurationCheckpointData struct) throws IOException {
            protocol.writeStructBegin("DurationCheckpointData");
            if (struct.page_view_duration != null) {
                protocol.writeFieldBegin("page_view_duration", 1, 8);
                protocol.writeI32(struct.page_view_duration.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.total_view_duration != null) {
                protocol.writeFieldBegin("total_view_duration", 2, 8);
                protocol.writeI32(struct.total_view_duration.intValue());
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
        if (!(other instanceof DurationCheckpointData)) {
            return false;
        }
        DurationCheckpointData that = (DurationCheckpointData) other;
        if (this.page_view_duration == that.page_view_duration || (this.page_view_duration != null && this.page_view_duration.equals(that.page_view_duration))) {
            if (this.total_view_duration == that.total_view_duration) {
                return true;
            }
            if (this.total_view_duration != null && this.total_view_duration.equals(that.total_view_duration)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.page_view_duration == null ? 0 : this.page_view_duration.hashCode())) * -2128831035;
        if (this.total_view_duration != null) {
            i = this.total_view_duration.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "DurationCheckpointData{page_view_duration=" + this.page_view_duration + ", total_view_duration=" + this.total_view_duration + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
