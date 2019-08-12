package com.airbnb.android.core.views.guestpicker;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class GuestsPickerSheetWithButtonView$$Icepick<T extends GuestsPickerSheetWithButtonView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8512H = new Helper("com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.showMaxGuestDescription = f8512H.getBoolean(state, "showMaxGuestDescription");
        return super.restore(target, f8512H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8512H.putParent(super.save(target, p));
        f8512H.putBoolean(state, "showMaxGuestDescription", target.showMaxGuestDescription);
        return state;
    }
}
