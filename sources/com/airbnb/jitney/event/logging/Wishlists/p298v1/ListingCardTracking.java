package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListingCardTracking */
public final class ListingCardTracking implements Struct {
    public static final Adapter<ListingCardTracking, Object> ADAPTER = new ListingCardTrackingAdapter();
    public final ListingCardAction action;
    public final Long listing_id;
    public final Integer visible_image_index;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListingCardTracking$ListingCardTrackingAdapter */
    private static final class ListingCardTrackingAdapter implements Adapter<ListingCardTracking, Object> {
        private ListingCardTrackingAdapter() {
        }

        public void write(Protocol protocol, ListingCardTracking struct) throws IOException {
            protocol.writeStructBegin("ListingCardTracking");
            protocol.writeFieldBegin("listing_id", 1, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 2, 8);
                protocol.writeI32(struct.action.value);
                protocol.writeFieldEnd();
            }
            if (struct.visible_image_index != null) {
                protocol.writeFieldBegin("visible_image_index", 3, 8);
                protocol.writeI32(struct.visible_image_index.intValue());
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
        if (!(other instanceof ListingCardTracking)) {
            return false;
        }
        ListingCardTracking that = (ListingCardTracking) other;
        if ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.action == that.action || (this.action != null && this.action.equals(that.action)))) {
            if (this.visible_image_index == that.visible_image_index) {
                return true;
            }
            if (this.visible_image_index != null && this.visible_image_index.equals(that.visible_image_index)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((16777619 ^ this.listing_id.hashCode()) * -2128831035) ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035;
        if (this.visible_image_index != null) {
            i = this.visible_image_index.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ListingCardTracking{listing_id=" + this.listing_id + ", action=" + this.action + ", visible_image_index=" + this.visible_image_index + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
