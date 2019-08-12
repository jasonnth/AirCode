package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.core.models.ListingReviewScores;
import com.airbnb.android.lib.C0880R;

public class ListingReviewScoresPresenter {
    private static final String STATE_NEGATIVE = "warning";
    private static final String STATE_NEUTRAL = "no_reviews";
    private static final String STATE_POSITIVE = "success";

    public static int getStateTextColor(ListingReviewScores reviewScores, Context context) {
        int colorRes;
        String stateKey = reviewScores.getStateKey();
        char c = 65535;
        switch (stateKey.hashCode()) {
            case -1867169789:
                if (stateKey.equals("success")) {
                    c = 0;
                    break;
                }
                break;
            case 257578909:
                if (stateKey.equals(STATE_NEUTRAL)) {
                    c = 3;
                    break;
                }
                break;
            case 1124446108:
                if (stateKey.equals(STATE_NEGATIVE)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                colorRes = C0880R.color.listing_review_score_card_text_positive;
                break;
            case 1:
                colorRes = C0880R.color.listing_review_score_card_text_negative;
                break;
            default:
                colorRes = C0880R.color.listing_review_score_card_text_neutral;
                break;
        }
        return ContextCompat.getColor(context, colorRes);
    }
}
