package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingPreBookingAddQuestionsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageListingPreBookingAddQuestionsAdapter$$Icepick<T extends ManageListingPreBookingAddQuestionsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10182H = new Helper("com.airbnb.android.managelisting.settings.ManageListingPreBookingAddQuestionsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.standardQuestionsChecked = (ArrayList) f10182H.getSerializable(state, "standardQuestionsChecked");
            target.customQuestions = f10182H.getStringArrayList(state, "customQuestions");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10182H.putSerializable(state, "standardQuestionsChecked", target.standardQuestionsChecked);
        f10182H.putStringArrayList(state, "customQuestions", target.customQuestions);
    }
}
