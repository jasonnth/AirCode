package com.airbnb.android.core.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class ListingTrayCarouselAdapter extends AirEpoxyAdapter {
    private final CarouselItemClickListener carouselClickListener;
    private final Context context;

    public interface CarouselItemClickListener {
        void onCarouselItemClicked(View view, ListingTrayItem listingTrayItem);
    }

    public static class ListingTrayItem {
        public final Listing listing;
        public final PricingQuote price;
        /* access modifiers changed from: private */
        public final WishListableData wishListableData;

        public ListingTrayItem(Listing listing2, PricingQuote price2, C2813WishlistSource source, String searchSessionId, boolean allowAutoAdd, GuestDetails guestDetails, AirDate checkIn, AirDate checkOut) {
            this.listing = listing2;
            this.price = price2;
            this.wishListableData = WishListableData.forHome(listing2).searchSessionId(searchSessionId).source(source).guestDetails(guestDetails).checkIn(checkIn).checkOut(checkOut).allowAutoAdd(allowAutoAdd).build();
        }

        public static List<ListingTrayItem> fromSimilarListings(List<SimilarListing> similarListings, C2813WishlistSource source) {
            return fromSimilarListings(similarListings, source, false, null, null, null, null);
        }

        public static List<ListingTrayItem> fromSimilarListings(List<SimilarListing> similarListings, C2813WishlistSource source, boolean allowAutoAdd, String searchSessionId, GuestDetails guestDetails, AirDate checkIn, AirDate checkOut) {
            return FluentIterable.from((Iterable<E>) similarListings).transform(ListingTrayCarouselAdapter$ListingTrayItem$$Lambda$1.lambdaFactory$(source, searchSessionId, allowAutoAdd, guestDetails, checkIn, checkOut)).toList();
        }

        static /* synthetic */ ListingTrayItem lambda$fromSimilarListings$0(C2813WishlistSource source, String searchSessionId, boolean allowAutoAdd, GuestDetails guestDetails, AirDate checkIn, AirDate checkOut, SimilarListing l) {
            return new ListingTrayItem(l.getListing(), l.getPricingQuote(), source, searchSessionId, allowAutoAdd, guestDetails, checkIn, checkOut);
        }

        public boolean equals(Object other) {
            if (!(other instanceof ListingTrayItem)) {
                return false;
            }
            ListingTrayItem otherItem = (ListingTrayItem) other;
            if (this.listing == null || otherItem.listing == null || this.listing.getId() != otherItem.listing.getId()) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (this.listing == null) {
                return 0;
            }
            return Long.valueOf(this.listing.getId()).hashCode();
        }
    }

    public ListingTrayCarouselAdapter(CarouselItemClickListener carouselClickListener2, Context context2) {
        super(false);
        this.carouselClickListener = carouselClickListener2;
        this.context = context2;
    }

    public void setItems(List<ListingTrayItem> dataList) {
        this.models.clear();
        for (ListingTrayItem data : dataList) {
            this.models.add(buildHomeCardEpoxyModel(data));
        }
        notifyDataSetChanged();
    }

    private EpoxyModel<?> buildHomeCardEpoxyModel(ListingTrayItem data) {
        return new HomeCardEpoxyModel_().listing(data.listing).pricingQuote(data.price).wishListableData(data.wishListableData).displayOptions(DisplayOptions.forHomeCard(this.context, DisplayType.Horizontal)).clickListener(ListingTrayCarouselAdapter$$Lambda$1.lambdaFactory$(this, data));
    }
}
