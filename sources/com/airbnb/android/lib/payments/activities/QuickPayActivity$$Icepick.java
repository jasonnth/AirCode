package com.airbnb.android.lib.payments.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.android.lib.payments.activities.QuickPayActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class QuickPayActivity$$Icepick<T extends QuickPayActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9628H = new Helper("com.airbnb.android.lib.payments.activities.QuickPayActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cartItem = (CartItem) f9628H.getParcelable(state, "cartItem");
            target.clientType = (QuickPayClientType) f9628H.getSerializable(state, "clientType");
            target.price = (Price) f9628H.getParcelable(state, EditPriceFragment.RESULT_PRICE);
            target.billPriceQuote = (BillPriceQuote) f9628H.getParcelable(state, "billPriceQuote");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9628H.putParcelable(state, "cartItem", target.cartItem);
        f9628H.putSerializable(state, "clientType", target.clientType);
        f9628H.putParcelable(state, EditPriceFragment.RESULT_PRICE, target.price);
        f9628H.putParcelable(state, "billPriceQuote", target.billPriceQuote);
    }
}
