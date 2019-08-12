package com.airbnb.android.lib.utils.listing;

import android.text.TextUtils;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.Trebuchet;
import com.google.common.collect.Lists;
import java.util.List;

public class ListingProgressUtil {

    interface RequiredStep {
        boolean completed(Listing listing);
    }

    public static int calculateLocalStepsRemaining(Listing listing) {
        Check.notNull(listing);
        return calculateLocalStepsRemaining(listing, generateRequirements());
    }

    private static int calculateLocalStepsRemaining(Listing listing, List<RequiredStep> requirements) {
        if (listing.hasBeenListed() || listing.hasAvailability()) {
            return 0;
        }
        int stepsRemaining = 0;
        for (RequiredStep requirement : requirements) {
            if (!requirement.completed(listing)) {
                stepsRemaining++;
            }
        }
        return stepsRemaining;
    }

    private static List<RequiredStep> generateRequirements() {
        List<RequiredStep> requirements = Lists.newArrayList();
        requirements.add(ListingProgressUtil$$Lambda$1.lambdaFactory$());
        requirements.add(ListingProgressUtil$$Lambda$2.lambdaFactory$());
        requirements.add(ListingProgressUtil$$Lambda$3.lambdaFactory$());
        requirements.add(ListingProgressUtil$$Lambda$4.lambdaFactory$());
        requirements.add(ListingProgressUtil$$Lambda$5.lambdaFactory$());
        if (!Trebuchet.launch("instant_book_settings", "disabled", false)) {
            requirements.add(ListingProgressUtil$$Lambda$6.lambdaFactory$());
        }
        return requirements;
    }

    static /* synthetic */ boolean lambda$generateRequirements$0(Listing listing) {
        return !TextUtils.isEmpty(listing.getName()) && !TextUtils.isEmpty(listing.getSummary());
    }

    static /* synthetic */ boolean lambda$generateRequirements$1(Listing listing) {
        return listing.getPictureCount() > 0;
    }

    static /* synthetic */ boolean lambda$generateRequirements$2(Listing listing) {
        return listing.getListingPriceNative() > 0;
    }
}
