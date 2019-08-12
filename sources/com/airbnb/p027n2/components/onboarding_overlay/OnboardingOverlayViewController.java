package com.airbnb.p027n2.components.onboarding_overlay;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayView.OnboardingOverlayViewBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.onboarding_overlay.OnboardingOverlayViewController */
public class OnboardingOverlayViewController {
    public static void show(Activity activity, View target, String title, String dismissText, String UniqueOnboardingOverlayKey, int showOnSeenTimes) {
        show(activity, target, title, dismissText, UniqueOnboardingOverlayKey, activity.getResources().getDimensionPixelSize(R.dimen.n2_default_overlay_target_circle_padding), showOnSeenTimes);
    }

    public static void show(Activity activity, View target, String title, String dismissText, String UniqueOnboardingOverlayKey, int shapePaddingDp, int showOnSeenTimes) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("onboarding_overlay_seen_prefs", 0);
        if (shouldShowOnboardingOverlay(showOnSeenTimes, UniqueOnboardingOverlayKey, sharedPreferences)) {
            showOverlay(activity, target, title, dismissText, shapePaddingDp);
        }
        setHasSeen(UniqueOnboardingOverlayKey, showOnSeenTimes, sharedPreferences);
    }

    private static void showOverlay(Activity activity, View target, String title, String dismissText, int shapePaddingDp) {
        ((OnboardingOverlayViewBuilder) new OnboardingOverlayViewBuilder(activity).setTarget(target).setTitleText(title).setDismissText(dismissText).setMaskColour(ContextCompat.getColor(activity, R.color.n2_babu_90)).setTargetTouchable(true).setDismissOnTargetTouch(false).setShapePadding(ViewLibUtils.dpToPx(activity, (float) shapePaddingDp)).setDismissOnTouch(true)).setTargetStrokeWidth(ViewLibUtils.dpToPx(activity, (float) activity.getResources().getDimensionPixelSize(R.dimen.n2_default_overlay_target_border_width))).show();
    }

    private static boolean shouldShowOnboardingOverlay(int showOnTimes, String uniqueOnboardingOverlayKey, SharedPreferences sharedPreferences) {
        if (showOnTimes - 1 == sharedPreferences.getInt("seen_" + uniqueOnboardingOverlayKey, 0)) {
            return true;
        }
        return false;
    }

    private static void setHasSeen(String UniqueOnboardingOverlayKey, int showOnSeenTimes, SharedPreferences sharedPreferences) {
        String key = "seen_" + UniqueOnboardingOverlayKey;
        int hasSeenTimes = sharedPreferences.getInt(key, 0);
        if (hasSeenTimes < showOnSeenTimes) {
            sharedPreferences.edit().putInt(key, hasSeenTimes + 1).apply();
        }
    }
}
