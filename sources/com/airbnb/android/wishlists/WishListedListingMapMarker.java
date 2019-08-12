package com.airbnb.android.wishlists;

import android.content.Context;
import android.graphics.Bitmap;
import com.airbnb.android.core.map.ExploreMapMarkerable;
import com.airbnb.android.core.models.WishlistedListing;
import com.airbnb.android.core.utils.MapMarkerGenerator;
import com.airbnb.android.core.utils.SearchUtil;

public class WishListedListingMapMarker extends ExploreMapMarkerable {
    private final WishlistedListing listing;
    private final MapMarkerGenerator mapMarkerGenerator;

    public WishListedListingMapMarker(WishlistedListing listing2, boolean isWishListed, MapMarkerGenerator generator, Context context) {
        super(listing2, isWishListed, context);
        this.listing = listing2;
        this.mapMarkerGenerator = generator;
    }

    public Bitmap getBitmap(boolean isHighlighted, boolean isViewed) {
        boolean shouldShowIb = this.listing.getPricingQuote().isInstantBookable();
        return this.mapMarkerGenerator.getPriceMarker(this.context, SearchUtil.getPriceTagText(this.listing.getPricingQuote()), isViewed, shouldShowIb, this.isWishListed, isHighlighted);
    }
}
