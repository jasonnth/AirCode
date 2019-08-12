package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.HomeActivity;
import com.airbnb.android.lib.activities.HomeActivity.AccountMode;
import com.airbnb.android.utils.SavedStateMap;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity$$Icepick<T extends HomeActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9466H = new Helper("com.airbnb.android.lib.activities.HomeActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasShownVerifiedIdDialog = f9466H.getBoolean(state, "hasShownVerifiedIdDialog");
            target.hasShownCouponConfirmation = f9466H.getBoolean(state, "hasShownCouponConfirmation");
            target.isShowingAccountTabBadgeForTripsNavUpdate = f9466H.getBoolean(state, "isShowingAccountTabBadgeForTripsNavUpdate");
            target.accountMode = (AccountMode) f9466H.getSerializable(state, "accountMode");
            target.savedStateMap = (SavedStateMap) f9466H.getParcelable(state, "savedStateMap");
            target.shouldShowSuperHeroMessageOnLaunch = f9466H.getBoxedBoolean(state, "shouldShowSuperHeroMessageOnLaunch");
            target.pendingLaunchPostTrebuchetActions = f9466H.getBoolean(state, "pendingLaunchPostTrebuchetActions");
            target.currentNavSection = (NavigationSection) f9466H.getSerializable(state, "currentNavSection");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9466H.putBoolean(state, "hasShownVerifiedIdDialog", target.hasShownVerifiedIdDialog);
        f9466H.putBoolean(state, "hasShownCouponConfirmation", target.hasShownCouponConfirmation);
        f9466H.putBoolean(state, "isShowingAccountTabBadgeForTripsNavUpdate", target.isShowingAccountTabBadgeForTripsNavUpdate);
        f9466H.putSerializable(state, "accountMode", target.accountMode);
        f9466H.putParcelable(state, "savedStateMap", target.savedStateMap);
        f9466H.putBoxedBoolean(state, "shouldShowSuperHeroMessageOnLaunch", target.shouldShowSuperHeroMessageOnLaunch);
        f9466H.putBoolean(state, "pendingLaunchPostTrebuchetActions", target.pendingLaunchPostTrebuchetActions);
        f9466H.putSerializable(state, "currentNavSection", target.currentNavSection);
    }
}
