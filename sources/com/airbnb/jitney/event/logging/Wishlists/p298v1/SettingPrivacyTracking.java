package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingPrivacyTracking */
public final class SettingPrivacyTracking implements Struct {
    public static final Adapter<SettingPrivacyTracking, Object> ADAPTER = new SettingPrivacyTrackingAdapter();
    public final Integer current_value;
    public final Integer previous_value;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingPrivacyTracking$SettingPrivacyTrackingAdapter */
    private static final class SettingPrivacyTrackingAdapter implements Adapter<SettingPrivacyTracking, Object> {
        private SettingPrivacyTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingPrivacyTracking struct) throws IOException {
            protocol.writeStructBegin("SettingPrivacyTracking");
            if (struct.previous_value != null) {
                protocol.writeFieldBegin("previous_value", 1, 8);
                protocol.writeI32(struct.previous_value.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.current_value != null) {
                protocol.writeFieldBegin("current_value", 2, 8);
                protocol.writeI32(struct.current_value.intValue());
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
        if (!(other instanceof SettingPrivacyTracking)) {
            return false;
        }
        SettingPrivacyTracking that = (SettingPrivacyTracking) other;
        if (this.previous_value == that.previous_value || (this.previous_value != null && this.previous_value.equals(that.previous_value))) {
            if (this.current_value == that.current_value) {
                return true;
            }
            if (this.current_value != null && this.current_value.equals(that.current_value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.previous_value == null ? 0 : this.previous_value.hashCode())) * -2128831035;
        if (this.current_value != null) {
            i = this.current_value.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SettingPrivacyTracking{previous_value=" + this.previous_value + ", current_value=" + this.current_value + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
