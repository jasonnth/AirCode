package com.airbnb.jitney.event.logging.SchedulableInfo.p023v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.SchedulableInfo.v2.SchedulableInfo */
public final class C0973SchedulableInfo implements Struct {
    public static final Adapter<C0973SchedulableInfo, Builder> ADAPTER = new SchedulableInfoAdapter();
    public final String schedulable_id;
    public final String schedulable_type;

    /* renamed from: com.airbnb.jitney.event.logging.SchedulableInfo.v2.SchedulableInfo$Builder */
    public static final class Builder implements StructBuilder<C0973SchedulableInfo> {
        /* access modifiers changed from: private */
        public String schedulable_id;
        /* access modifiers changed from: private */
        public String schedulable_type;

        private Builder() {
        }

        public Builder(String schedulable_type2, String schedulable_id2) {
            this.schedulable_type = schedulable_type2;
            this.schedulable_id = schedulable_id2;
        }

        public C0973SchedulableInfo build() {
            if (this.schedulable_type == null) {
                throw new IllegalStateException("Required field 'schedulable_type' is missing");
            } else if (this.schedulable_id != null) {
                return new C0973SchedulableInfo(this);
            } else {
                throw new IllegalStateException("Required field 'schedulable_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.SchedulableInfo.v2.SchedulableInfo$SchedulableInfoAdapter */
    private static final class SchedulableInfoAdapter implements Adapter<C0973SchedulableInfo, Builder> {
        private SchedulableInfoAdapter() {
        }

        public void write(Protocol protocol, C0973SchedulableInfo struct) throws IOException {
            protocol.writeStructBegin("SchedulableInfo");
            protocol.writeFieldBegin("schedulable_type", 1, PassportService.SF_DG11);
            protocol.writeString(struct.schedulable_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("schedulable_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.schedulable_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C0973SchedulableInfo(Builder builder) {
        this.schedulable_type = builder.schedulable_type;
        this.schedulable_id = builder.schedulable_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C0973SchedulableInfo)) {
            return false;
        }
        C0973SchedulableInfo that = (C0973SchedulableInfo) other;
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
