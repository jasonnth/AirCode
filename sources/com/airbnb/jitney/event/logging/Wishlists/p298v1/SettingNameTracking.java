package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingNameTracking */
public final class SettingNameTracking implements Struct {
    public static final Adapter<SettingNameTracking, Object> ADAPTER = new SettingNameTrackingAdapter();
    public final String current_name;
    public final Boolean exceeded_limit;
    public final Integer limit;
    public final String previous_name;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingNameTracking$SettingNameTrackingAdapter */
    private static final class SettingNameTrackingAdapter implements Adapter<SettingNameTracking, Object> {
        private SettingNameTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingNameTracking struct) throws IOException {
            protocol.writeStructBegin("SettingNameTracking");
            if (struct.previous_name != null) {
                protocol.writeFieldBegin("previous_name", 1, PassportService.SF_DG11);
                protocol.writeString(struct.previous_name);
                protocol.writeFieldEnd();
            }
            if (struct.current_name != null) {
                protocol.writeFieldBegin("current_name", 2, PassportService.SF_DG11);
                protocol.writeString(struct.current_name);
                protocol.writeFieldEnd();
            }
            if (struct.exceeded_limit != null) {
                protocol.writeFieldBegin("exceeded_limit", 3, 2);
                protocol.writeBool(struct.exceeded_limit.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.limit != null) {
                protocol.writeFieldBegin("limit", 4, 8);
                protocol.writeI32(struct.limit.intValue());
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
        if (!(other instanceof SettingNameTracking)) {
            return false;
        }
        SettingNameTracking that = (SettingNameTracking) other;
        if ((this.previous_name == that.previous_name || (this.previous_name != null && this.previous_name.equals(that.previous_name))) && ((this.current_name == that.current_name || (this.current_name != null && this.current_name.equals(that.current_name))) && (this.exceeded_limit == that.exceeded_limit || (this.exceeded_limit != null && this.exceeded_limit.equals(that.exceeded_limit))))) {
            if (this.limit == that.limit) {
                return true;
            }
            if (this.limit != null && this.limit.equals(that.limit)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.previous_name == null ? 0 : this.previous_name.hashCode())) * -2128831035) ^ (this.current_name == null ? 0 : this.current_name.hashCode())) * -2128831035) ^ (this.exceeded_limit == null ? 0 : this.exceeded_limit.hashCode())) * -2128831035;
        if (this.limit != null) {
            i = this.limit.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SettingNameTracking{previous_name=" + this.previous_name + ", current_name=" + this.current_name + ", exceeded_limit=" + this.exceeded_limit + ", limit=" + this.limit + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
