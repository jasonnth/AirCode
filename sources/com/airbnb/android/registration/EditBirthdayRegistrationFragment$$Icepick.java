package com.airbnb.android.registration;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.registration.EditBirthdayRegistrationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditBirthdayRegistrationFragment$$Icepick<T extends EditBirthdayRegistrationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f1564H = new Helper("com.airbnb.android.registration.EditBirthdayRegistrationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedBirthday = (AirDate) f1564H.getParcelable(state, "selectedBirthday");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f1564H.putParcelable(state, "selectedBirthday", target.selectedBirthday);
    }
}
