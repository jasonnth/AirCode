package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class NightlyPriceAdapter$$Icepick<T extends NightlyPriceAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9797H = new Helper("com.airbnb.android.listing.adapters.NightlyPriceAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isSmartPricingEnabled = f9797H.getBoolean(state, "isSmartPricingEnabled");
            target.isKnownFrequencyVersion = f9797H.getBoolean(state, "isKnownFrequencyVersion");
            target.minInputValue = f9797H.getBoxedInt(state, "minInputValue");
            target.maxInputValue = f9797H.getBoxedInt(state, "maxInputValue");
            target.baseInputValue = f9797H.getBoxedInt(state, "baseInputValue");
            target.minPriceInputShowError = f9797H.getBoolean(state, "minPriceInputShowError");
            target.maxPriceInputShowError = f9797H.getBoolean(state, "maxPriceInputShowError");
            target.frequency = (DesiredHostingFrequency) f9797H.getSerializable(state, "frequency");
            target.isEditing = f9797H.getBoolean(state, "isEditing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9797H.putBoolean(state, "isSmartPricingEnabled", target.isSmartPricingEnabled);
        f9797H.putBoolean(state, "isKnownFrequencyVersion", target.isKnownFrequencyVersion);
        f9797H.putBoxedInt(state, "minInputValue", target.minInputValue);
        f9797H.putBoxedInt(state, "maxInputValue", target.maxInputValue);
        f9797H.putBoxedInt(state, "baseInputValue", target.baseInputValue);
        f9797H.putBoolean(state, "minPriceInputShowError", target.minPriceInputShowError);
        f9797H.putBoolean(state, "maxPriceInputShowError", target.maxPriceInputShowError);
        f9797H.putSerializable(state, "frequency", target.frequency);
        f9797H.putBoolean(state, "isEditing", target.isEditing);
    }
}
