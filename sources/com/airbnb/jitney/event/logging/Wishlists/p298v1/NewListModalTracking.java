package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.NewListModalTracking */
public final class NewListModalTracking implements Struct {
    public static final Adapter<NewListModalTracking, Object> ADAPTER = new NewListModalTrackingAdapter();
    public final NewListModalAction action;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.NewListModalTracking$NewListModalTrackingAdapter */
    private static final class NewListModalTrackingAdapter implements Adapter<NewListModalTracking, Object> {
        private NewListModalTrackingAdapter() {
        }

        public void write(Protocol protocol, NewListModalTracking struct) throws IOException {
            protocol.writeStructBegin("NewListModalTracking");
            protocol.writeFieldBegin("action", 1, 8);
            protocol.writeI32(struct.action.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof NewListModalTracking)) {
            return false;
        }
        NewListModalTracking that = (NewListModalTracking) other;
        if (this.action == that.action || this.action.equals(that.action)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (16777619 ^ this.action.hashCode()) * -2128831035;
    }

    public String toString() {
        return "NewListModalTracking{action=" + this.action + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
