package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.SendGiftCardFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SendGiftCardFragment$$Icepick<T extends SendGiftCardFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9553H = new Helper("com.airbnb.android.lib.fragments.SendGiftCardFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.recipientName = f9553H.getString(state, PostalAddress.RECIPIENT_NAME_KEY);
            target.recipientEmail = f9553H.getString(state, "recipientEmail");
            target.giftCreditTemplates = f9553H.getParcelableArrayList(state, "giftCreditTemplates");
            target.giftAmount = f9553H.getInt(state, "giftAmount");
            target.completedGiftCreditPurchase = f9553H.getBoolean(state, "completedGiftCreditPurchase");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9553H.putString(state, PostalAddress.RECIPIENT_NAME_KEY, target.recipientName);
        f9553H.putString(state, "recipientEmail", target.recipientEmail);
        f9553H.putParcelableArrayList(state, "giftCreditTemplates", target.giftCreditTemplates);
        f9553H.putInt(state, "giftAmount", target.giftAmount);
        f9553H.putBoolean(state, "completedGiftCreditPurchase", target.completedGiftCreditPurchase);
    }
}
