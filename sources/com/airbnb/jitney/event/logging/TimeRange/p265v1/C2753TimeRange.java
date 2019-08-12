package com.airbnb.jitney.event.logging.TimeRange.p265v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.TimeRange.v1.TimeRange */
public final class C2753TimeRange implements Struct {
    public static final Adapter<C2753TimeRange, Object> ADAPTER = new TimeRangeAdapter();
    public final Long end_time;
    public final Long start_time;

    /* renamed from: com.airbnb.jitney.event.logging.TimeRange.v1.TimeRange$TimeRangeAdapter */
    private static final class TimeRangeAdapter implements Adapter<C2753TimeRange, Object> {
        private TimeRangeAdapter() {
        }

        public void write(Protocol protocol, C2753TimeRange struct) throws IOException {
            protocol.writeStructBegin("TimeRange");
            if (struct.start_time != null) {
                protocol.writeFieldBegin("start_time", 1, 10);
                protocol.writeI64(struct.start_time.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.end_time != null) {
                protocol.writeFieldBegin("end_time", 2, 10);
                protocol.writeI64(struct.end_time.longValue());
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
        if (!(other instanceof C2753TimeRange)) {
            return false;
        }
        C2753TimeRange that = (C2753TimeRange) other;
        if (this.start_time == that.start_time || (this.start_time != null && this.start_time.equals(that.start_time))) {
            if (this.end_time == that.end_time) {
                return true;
            }
            if (this.end_time != null && this.end_time.equals(that.end_time)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.start_time == null ? 0 : this.start_time.hashCode())) * -2128831035;
        if (this.end_time != null) {
            i = this.end_time.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "TimeRange{start_time=" + this.start_time + ", end_time=" + this.end_time + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
