package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.registration.CreateSocialAccountFragment;
import com.airbnb.p027n2.collections.SheetState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreateSocialAccountFragment$$Icepick<T extends CreateSocialAccountFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1563H = new Helper("com.airbnb.android.registration.CreateSocialAccountFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedBirthday = (AirDate) f1563H.getParcelable(state, "selectedBirthday");
            target.sheetState = (SheetState) f1563H.getSerializable(state, "sheetState");
            target.emailText = f1563H.getString(state, "emailText");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1563H.putParcelable(state, "selectedBirthday", target.selectedBirthday);
        f1563H.putSerializable(state, "sheetState", target.sheetState);
        f1563H.putString(state, "emailText", target.emailText);
    }
}
