package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.FloatingButtonTracking */
public final class FloatingButtonTracking implements Struct {
    public static final Adapter<FloatingButtonTracking, Object> ADAPTER = new FloatingButtonTrackingAdapter();
    public final FloatingButtonAction action;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.FloatingButtonTracking$FloatingButtonTrackingAdapter */
    private static final class FloatingButtonTrackingAdapter implements Adapter<FloatingButtonTracking, Object> {
        private FloatingButtonTrackingAdapter() {
        }

        public void write(Protocol protocol, FloatingButtonTracking struct) throws IOException {
            protocol.writeStructBegin("FloatingButtonTracking");
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 1, 8);
                protocol.writeI32(struct.action.value);
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
        if (other == null || !(other instanceof FloatingButtonTracking)) {
            return false;
        }
        FloatingButtonTracking that = (FloatingButtonTracking) other;
        if (this.action == that.action || (this.action != null && this.action.equals(that.action))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (16777619 ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035;
    }

    public String toString() {
        return "FloatingButtonTracking{action=" + this.action + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
