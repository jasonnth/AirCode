package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

/* renamed from: com.airbnb.android.p3.InstantBookUpsellUtils */
public class InstantBookUpsellUtils {
    private InstantBookUpsellUtils() {
    }

    public static boolean isReservationRequestToBook(P3State state) {
        boolean hasSpecialOffer;
        if (state.listing() == null || state.listing().getSpecialOffer() == null) {
            hasSpecialOffer = false;
        } else {
            hasSpecialOffer = true;
        }
        return !hasSpecialOffer && state.pricingQuote() != null && !state.pricingQuote().isInstantBookable();
    }

    public static boolean isReservationInstantBookable(P3State state) {
        return state.pricingQuote() != null && state.pricingQuote().isInstantBookable();
    }

    public static boolean areAllSimilarListingsInstantBookable(List<SimilarListing> similarListings) {
        return !ListUtils.isEmpty((Collection<?>) similarListings) && getIBSimilarListingCount(similarListings) == similarListings.size();
    }

    public static int getIBSimilarListingCount(List<SimilarListing> similarListings) {
        if (ListUtils.isEmpty((Collection<?>) similarListings)) {
            return 0;
        }
        return FluentIterable.from((Iterable<E>) similarListings).filter(InstantBookUpsellUtils$$Lambda$1.lambdaFactory$()).size();
    }

    static /* synthetic */ boolean lambda$getIBSimilarListingCount$0(SimilarListing listing) {
        return (listing == null || listing.getPricingQuote() == null || !listing.getPricingQuote().isInstantBookable()) ? false : true;
    }
}
