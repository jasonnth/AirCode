package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.controllers.LottieNuxController;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.utils.AirbnbConstants;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

public class CheckinIntents {
    public static final String INTENT_EXTRA_CHECKIN_LISTING_ID = "checkin_listing_id";
    public static final String INTENT_EXTRA_PREVIEW = "preview_guide";
    public static final String INTENT_EXTRA_SAMPLE = "sample_guide";
    public static final String INTENT_EXTRA_STARTING_STEP = "checkin_step_number";
    public static final List<Float> animationTimes = Arrays.asList(new Float[]{Float.valueOf(0.14f), Float.valueOf(0.55f), Float.valueOf(1.0f), Float.valueOf(1.0f)});
    public static final List<SimpleEntry<Integer, Integer>> pagesContent = Arrays.asList(new SimpleEntry[]{new SimpleEntry(Integer.valueOf(C0716R.string.checkin_nux_intro_title), Integer.valueOf(C0716R.string.checkin_nux_intro_description)), new SimpleEntry(Integer.valueOf(C0716R.string.checkin_nux_how_to_title), Integer.valueOf(C0716R.string.checkin_nux_how_to_description)), new SimpleEntry(Integer.valueOf(C0716R.string.checkin_nux_why_title), Integer.valueOf(C0716R.string.checkin_nux_why_description))});

    public static Intent intentForListingId(Context context, long listingId) {
        return new Intent(context, Activities.viewCheckin()).putExtra(INTENT_EXTRA_CHECKIN_LISTING_ID, listingId);
    }

    public static Intent intentForListingAndStep(Context context, long listingId, int stepNum) {
        return intentForListingId(context, listingId).putExtra(INTENT_EXTRA_STARTING_STEP, stepNum).putExtra(INTENT_EXTRA_PREVIEW, true);
    }

    public static Intent intentForCheckInNux(Context context, Long listingId) {
        return LottieNuxController.intentForLottieNux(context, LottieNuxViewPagerArguments.builder().pagesContent(pagesContent).animationTimes(animationTimes).buttonText(context.getString(listingId == null ? C0716R.string.checkin_nux_choose_listing : C0716R.string.checkin_nux_get_started)).buttonDeepLink(listingId == null ? "" : "airbnb://d/manage-check-in-guide/" + listingId).sharedPrefsConstant(AirbnbConstants.PREFS_CHECK_IN_GUIDE_NUX).animationFilename("check_in_nux.json").build());
    }

    public static Intent intentForSample(Context context, long listingId) {
        return new Intent(context, Activities.viewCheckin()).putExtra(INTENT_EXTRA_SAMPLE, true).putExtra(INTENT_EXTRA_CHECKIN_LISTING_ID, listingId);
    }

    public static Intent deepLinkIntentForViewGuide(Context context, Bundle extras) {
        return intentForListingId(context, DeepLinkUtils.getParamAsId(extras, "listing_id"));
    }
}
