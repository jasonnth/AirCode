package com.airbnb.android.core.controllers;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.android.core.fragments.LottieNuxCoverPageFragment;
import com.airbnb.android.core.fragments.LottieNuxViewPagerFragment;

public class LottieNuxController {
    public static Intent intentForLottieNux(Context context, LottieNuxViewPagerArguments arguments) {
        if (showCoverPage(arguments)) {
            return LottieNuxCoverPageFragment.intentForNuxArguments(context, arguments);
        }
        return LottieNuxViewPagerFragment.intentForNuxArguments(context, arguments);
    }

    private static boolean showCoverPage(LottieNuxViewPagerArguments arguments) {
        return arguments.coverPageButtonTextRes().isPresent() && arguments.coverPageTitleRes().isPresent() && arguments.coverPageImageRes().isPresent();
    }
}
