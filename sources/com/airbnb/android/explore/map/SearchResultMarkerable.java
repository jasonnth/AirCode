package com.airbnb.android.explore.map;

import android.content.Context;
import android.graphics.Bitmap;
import com.airbnb.android.core.map.ExploreMapMarkerable;
import com.airbnb.android.core.models.SearchResult;
import com.airbnb.android.core.utils.MapMarkerGenerator;
import com.airbnb.android.core.utils.SearchUtil;

public class SearchResultMarkerable extends ExploreMapMarkerable {
    private final MapMarkerGenerator mapMarkerGenerator;
    private final SearchResult searchResult;
    private final boolean shouldShowBusinessTravelReady;

    public SearchResultMarkerable(SearchResult searchResult2, boolean isWishListed, boolean shouldShowBusinessTravelReady2, MapMarkerGenerator generator, Context context) {
        super(searchResult2, isWishListed, context);
        this.searchResult = searchResult2;
        this.shouldShowBusinessTravelReady = shouldShowBusinessTravelReady2;
        this.mapMarkerGenerator = generator;
    }

    public Bitmap getBitmap(boolean isHighlighted, boolean isViewed) {
        return this.mapMarkerGenerator.getPriceMarker(this.context, SearchUtil.getPriceTagText(this.searchResult.getPricingQuote()), isViewed, this.searchResult.getPricingQuote().isInstantBookable(), this.isWishListed, isHighlighted, this.searchResult.getListing().isBusinessTravelReady() && this.shouldShowBusinessTravelReady);
    }
}
