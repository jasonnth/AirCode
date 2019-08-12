package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListDetailsPanelTracking */
public final class ListDetailsPanelTracking implements Struct {
    public static final Adapter<ListDetailsPanelTracking, Object> ADAPTER = new ListDetailsPanelTrackingAdapter();
    public final ListDetailsPanelAction action;
    public final ListingCardTracking listing_card;
    public final SimilarListingsSectionTracking similar_listing_section;
    public final ListDetailsPanelTab tab;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.ListDetailsPanelTracking$ListDetailsPanelTrackingAdapter */
    private static final class ListDetailsPanelTrackingAdapter implements Adapter<ListDetailsPanelTracking, Object> {
        private ListDetailsPanelTrackingAdapter() {
        }

        public void write(Protocol protocol, ListDetailsPanelTracking struct) throws IOException {
            protocol.writeStructBegin("ListDetailsPanelTracking");
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
            if (struct.tab != null) {
                protocol.writeFieldBegin("tab", 3, 8);
                protocol.writeI32(struct.tab.value);
                protocol.writeFieldEnd();
            }
            if (struct.similar_listing_section != null) {
                protocol.writeFieldBegin("similar_listing_section", 4, PassportService.SF_DG12);
                SimilarListingsSectionTracking.ADAPTER.write(protocol, struct.similar_listing_section);
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
        if (!(other instanceof ListDetailsPanelTracking)) {
            return false;
        }
        ListDetailsPanelTracking that = (ListDetailsPanelTracking) other;
        if ((this.action == that.action || (this.action != null && this.action.equals(that.action))) && ((this.listing_card == that.listing_card || (this.listing_card != null && this.listing_card.equals(that.listing_card))) && (this.tab == that.tab || (this.tab != null && this.tab.equals(that.tab))))) {
            if (this.similar_listing_section == that.similar_listing_section) {
                return true;
            }
            if (this.similar_listing_section != null && this.similar_listing_section.equals(that.similar_listing_section)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035) ^ (this.listing_card == null ? 0 : this.listing_card.hashCode())) * -2128831035) ^ (this.tab == null ? 0 : this.tab.hashCode())) * -2128831035;
        if (this.similar_listing_section != null) {
            i = this.similar_listing_section.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ListDetailsPanelTracking{action=" + this.action + ", listing_card=" + this.listing_card + ", tab=" + this.tab + ", similar_listing_section=" + this.similar_listing_section + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
