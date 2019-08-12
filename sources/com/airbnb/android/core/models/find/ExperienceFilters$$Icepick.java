package com.airbnb.android.core.models.find;

import android.os.Bundle;
import com.airbnb.android.core.models.find.ExperienceFilters;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExperienceFilters$$Icepick<T extends ExperienceFilters> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8476H = new Helper("com.airbnb.android.core.models.find.ExperienceFilters$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.experienceSocialGoodOnly = f8476H.getBoxedBoolean(state, "experienceSocialGoodOnly");
            target.experienceProductTypes = f8476H.getParcelableArrayList(state, "experienceProductTypes");
            target.experienceCategories = f8476H.getParcelableArrayList(state, "experienceCategories");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8476H.putBoxedBoolean(state, "experienceSocialGoodOnly", target.experienceSocialGoodOnly);
        f8476H.putParcelableArrayList(state, "experienceProductTypes", target.experienceProductTypes);
        f8476H.putParcelableArrayList(state, "experienceCategories", target.experienceCategories);
    }
}
