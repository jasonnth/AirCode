package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Set;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.OutsideAppTracking */
public final class OutsideAppTracking implements Struct {
    public static final Adapter<OutsideAppTracking, Object> ADAPTER = new OutsideAppTrackingAdapter();
    public final OutsideAppAction action;
    public final Set<Long> listing_ids;
    public final Long target_wishlist_id;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.OutsideAppTracking$OutsideAppTrackingAdapter */
    private static final class OutsideAppTrackingAdapter implements Adapter<OutsideAppTracking, Object> {
        private OutsideAppTrackingAdapter() {
        }

        public void write(Protocol protocol, OutsideAppTracking struct) throws IOException {
            protocol.writeStructBegin("OutsideAppTracking");
            protocol.writeFieldBegin("action", 1, 8);
            protocol.writeI32(struct.action.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("target_wishlist_id", 2, 10);
            protocol.writeI64(struct.target_wishlist_id.longValue());
            protocol.writeFieldEnd();
            if (struct.listing_ids != null) {
                protocol.writeFieldBegin("listing_ids", 3, 14);
                protocol.writeSetBegin(10, struct.listing_ids.size());
                for (Long item0 : struct.listing_ids) {
                    protocol.writeI64(item0.longValue());
                }
                protocol.writeSetEnd();
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
        if (!(other instanceof OutsideAppTracking)) {
            return false;
        }
        OutsideAppTracking that = (OutsideAppTracking) other;
        if ((this.action == that.action || this.action.equals(that.action)) && (this.target_wishlist_id == that.target_wishlist_id || this.target_wishlist_id.equals(that.target_wishlist_id))) {
            if (this.listing_ids == that.listing_ids) {
                return true;
            }
            if (this.listing_ids != null && this.listing_ids.equals(that.listing_ids)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.action.hashCode()) * -2128831035) ^ this.target_wishlist_id.hashCode()) * -2128831035) ^ (this.listing_ids == null ? 0 : this.listing_ids.hashCode())) * -2128831035;
    }

    public String toString() {
        return "OutsideAppTracking{action=" + this.action + ", target_wishlist_id=" + this.target_wishlist_id + ", listing_ids=" + this.listing_ids + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
