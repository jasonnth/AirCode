package com.airbnb.android.insights.adapters;

import android.content.res.Resources;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.insights.ListingInsightsEpoxyModel_;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class ListingInsightsAdapter extends AirEpoxyAdapter {
    ListingInsightClickListener clickListener;

    public interface ListingInsightClickListener {
        void onGoToCardsClick(Listing listing);
    }

    public ListingInsightsAdapter(List<Listing> listings, List<Insight> insights, ListingInsightClickListener listener, Resources res) {
        for (Listing listing : listings) {
            int numInsightsForListing = FluentIterable.from((Iterable<E>) insights).filter(ListingInsightsAdapter$$Lambda$1.lambdaFactory$(listing)).size();
            if (numInsightsForListing > 0) {
                this.models.add(new ListingInsightsEpoxyModel_().description(listing.getName()).title(res.getQuantityString(C6552R.plurals.x_ways_attract_bookings, numInsightsForListing, new Object[]{Integer.valueOf(numInsightsForListing)})).dismissText(res.getQuantityString(C6552R.plurals.dismiss_x_tips, numInsightsForListing, new Object[]{Integer.valueOf(numInsightsForListing)})).listingImageUrl(listing.getThumbnailUrl()).goToCardsClickListener(ListingInsightsAdapter$$Lambda$2.lambdaFactory$(this, listing)));
            }
        }
        this.clickListener = listener;
    }

    static /* synthetic */ boolean lambda$new$0(Listing listing, Insight insight) {
        return insight.getListingId() == listing.getId();
    }
}
