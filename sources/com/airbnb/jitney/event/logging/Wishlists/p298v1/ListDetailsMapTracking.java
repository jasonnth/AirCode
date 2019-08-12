package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListDetailsMapTracking */
public final class ListDetailsMapTracking implements Struct {
    public static final Adapter<ListDetailsMapTracking, Object> ADAPTER = new ListDetailsMapTrackingAdapter();
    public final ListDetailsMapAction action;
    public final ListingCardTracking listing_card;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListDetailsMapTracking$ListDetailsMapTrackingAdapter */
    private static final class ListDetailsMapTrackingAdapter implements Adapter<ListDetailsMapTracking, Object> {
        private ListDetailsMapTrackingAdapter() {
        }

        public void write(Protocol protocol, ListDetailsMapTracking struct) throws IOException {
            protocol.writeStructBegin("ListDetailsMapTracking");
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 1, 8);
                protocol.writeI32(struct.action.value);
                protocol.writeFieldEnd();
            }
            if (struct.listing_card != null) {
                protocol.writeFieldBegin(ContentFrameworkAnalytics.LISTING_CARD, 2, PassportService.SF_DG12);
                ListingCardTracking.ADAPTER.write(protocol, struct.listing_card);
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
        if (!(other instanceof ListDetailsMapTracking)) {
            return false;
        }
        ListDetailsMapTracking that = (ListDetailsMapTracking) other;
        if (this.action == that.action || (this.action != null && this.action.equals(that.action))) {
            if (this.listing_card == that.listing_card) {
                return true;
            }
            if (this.listing_card != null && this.listing_card.equals(that.listing_card)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035;
        if (this.listing_card != null) {
            i = this.listing_card.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ListDetailsMapTracking{action=" + this.action + ", listing_card=" + this.listing_card + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
