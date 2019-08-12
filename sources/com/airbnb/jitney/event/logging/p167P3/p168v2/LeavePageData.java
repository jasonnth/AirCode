package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.LeavePageData */
public final class LeavePageData implements Struct {
    public static final Adapter<LeavePageData, Builder> ADAPTER = new LeavePageDataAdapter();
    public final Integer page_view_duration;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.LeavePageData$Builder */
    public static final class Builder implements StructBuilder<LeavePageData> {
        /* access modifiers changed from: private */
        public Integer page_view_duration;

        public Builder page_view_duration(Integer page_view_duration2) {
            this.page_view_duration = page_view_duration2;
            return this;
        }

        public LeavePageData build() {
            return new LeavePageData(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.LeavePageData$LeavePageDataAdapter */
    private static final class LeavePageDataAdapter implements Adapter<LeavePageData, Builder> {
        private LeavePageDataAdapter() {
        }

        public void write(Protocol protocol, LeavePageData struct) throws IOException {
            protocol.writeStructBegin("LeavePageData");
            if (struct.page_view_duration != null) {
                protocol.writeFieldBegin("page_view_duration", 1, 8);
                protocol.writeI32(struct.page_view_duration.intValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LeavePageData(Builder builder) {
        this.page_view_duration = builder.page_view_duration;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof LeavePageData)) {
            return false;
        }
        LeavePageData that = (LeavePageData) other;
        if (this.page_view_duration == that.page_view_duration || (this.page_view_duration != null && this.page_view_duration.equals(that.page_view_duration))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (16777619 ^ (this.page_view_duration == null ? 0 : this.page_view_duration.hashCode())) * -2128831035;
    }

    public String toString() {
        return "LeavePageData{page_view_duration=" + this.page_view_duration + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
