package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingMembershipTracking */
public final class SettingMembershipTracking implements Struct {
    public static final Adapter<SettingMembershipTracking, Object> ADAPTER = new SettingMembershipTrackingAdapter();
    public final Long user_id_removed;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingMembershipTracking$SettingMembershipTrackingAdapter */
    private static final class SettingMembershipTrackingAdapter implements Adapter<SettingMembershipTracking, Object> {
        private SettingMembershipTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingMembershipTracking struct) throws IOException {
            protocol.writeStructBegin("SettingMembershipTracking");
            if (struct.user_id_removed != null) {
                protocol.writeFieldBegin("user_id_removed", 1, 10);
                protocol.writeI64(struct.user_id_removed.longValue());
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
        if (other == null || !(other instanceof SettingMembershipTracking)) {
            return false;
        }
        SettingMembershipTracking that = (SettingMembershipTracking) other;
        if (this.user_id_removed == that.user_id_removed || (this.user_id_removed != null && this.user_id_removed.equals(that.user_id_removed))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (16777619 ^ (this.user_id_removed == null ? 0 : this.user_id_removed.hashCode())) * -2128831035;
    }

    public String toString() {
        return "SettingMembershipTracking{user_id_removed=" + this.user_id_removed + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
