package com.airbnb.android.explore.map;

import android.content.Context;
import android.graphics.Bitmap;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.core.map.ExploreMapMarkerable;

public class GuidebookPlaceMarkerable extends ExploreMapMarkerable {
    private final GuidebookMarkerGenerator generator;
    private final GuidebookPlace place;

    public GuidebookPlaceMarkerable(Context context, GuidebookPlace place2, GuidebookMarkerGenerator generator2, boolean isWishListed) {
        super(place2, isWishListed, context);
        this.generator = generator2;
        this.place = place2;
    }

    public Bitmap getBitmap(boolean isHighlighted, boolean isViewed) {
        return this.generator.getMarker(this.place, isViewed, isHighlighted);
    }
}
