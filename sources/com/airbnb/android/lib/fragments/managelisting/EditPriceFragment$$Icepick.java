package com.airbnb.android.lib.fragments.managelisting;

import android.os.Bundle;
import com.airbnb.android.core.enums.ManageListingPriceType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditPriceFragment$$Icepick<T extends EditPriceFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9577H = new Helper("com.airbnb.android.lib.fragments.managelisting.EditPriceFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9577H.getParcelable(state, "listing");
            target.priceTypeBeingEdited = (ManageListingPriceType) f9577H.getSerializable(state, "priceTypeBeingEdited");
            target.existingPrice = f9577H.getInt(state, "existingPrice");
            target.suggestedPrice = f9577H.getInt(state, "suggestedPrice");
            target.forActionCard = f9577H.getBoolean(state, "forActionCard");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9577H.putParcelable(state, "listing", target.listing);
        f9577H.putSerializable(state, "priceTypeBeingEdited", target.priceTypeBeingEdited);
        f9577H.putInt(state, "existingPrice", target.existingPrice);
        f9577H.putInt(state, "suggestedPrice", target.suggestedPrice);
        f9577H.putBoolean(state, "forActionCard", target.forActionCard);
    }
}
