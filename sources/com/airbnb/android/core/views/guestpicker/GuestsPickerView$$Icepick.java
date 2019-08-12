package com.airbnb.android.core.views.guestpicker;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.views.guestpicker.GuestsPickerView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class GuestsPickerView$$Icepick<T extends GuestsPickerView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8513H = new Helper("com.airbnb.android.core.views.guestpicker.GuestsPickerView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.minAdults = f8513H.getInt(state, "minAdults");
        target.allowInfants = f8513H.getBoolean(state, "allowInfants");
        target.allowPets = f8513H.getBoolean(state, "allowPets");
        target.allowChildren = f8513H.getBoolean(state, "allowChildren");
        target.guestDetails = (GuestDetails) f8513H.getParcelable(state, "guestDetails");
        return super.restore(target, f8513H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8513H.putParent(super.save(target, p));
        f8513H.putInt(state, "minAdults", target.minAdults);
        f8513H.putBoolean(state, "allowInfants", target.allowInfants);
        f8513H.putBoolean(state, "allowPets", target.allowPets);
        f8513H.putBoolean(state, "allowChildren", target.allowChildren);
        f8513H.putParcelable(state, "guestDetails", target.guestDetails);
        return state;
    }
}
