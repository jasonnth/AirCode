package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.C0716R;

public final class ListingReviewsUtil {
    private ListingReviewsUtil() {
    }

    public static CharSequence getNumReviewsText(Context context, int reviewCount) {
        return context.getResources().getQuantityString(C0716R.plurals.reviews, reviewCount, new Object[]{Integer.toString(reviewCount)});
    }
}
