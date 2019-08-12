package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingCheckInGuideFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingCheckInGuideFragment$$Icepick<T extends ManageListingCheckInGuideFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10177H = new Helper("com.airbnb.android.managelisting.settings.ManageListingCheckInGuideFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.errorMessage = f10177H.getString(state, "errorMessage");
            target.currentStepId = f10177H.getLong(state, "currentStepId");
            target.numActualSteps = f10177H.getInt(state, "numActualSteps");
            target.numCardsToDisplay = f10177H.getInt(state, "numCardsToDisplay");
            target.isPublishing = f10177H.getBoolean(state, "isPublishing");
            target.imagePath = f10177H.getString(state, "imagePath");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10177H.putString(state, "errorMessage", target.errorMessage);
        f10177H.putLong(state, "currentStepId", target.currentStepId);
        f10177H.putInt(state, "numActualSteps", target.numActualSteps);
        f10177H.putInt(state, "numCardsToDisplay", target.numCardsToDisplay);
        f10177H.putBoolean(state, "isPublishing", target.isPublishing);
        f10177H.putString(state, "imagePath", target.imagePath);
    }
}
