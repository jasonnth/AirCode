package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.lib.fragments.MainGiftCardsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class MainGiftCardsFragment$$Icepick<T extends MainGiftCardsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9538H = new Helper("com.airbnb.android.lib.fragments.MainGiftCardsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.webLinkGiftCard = (GiftCard) f9538H.getParcelable(state, "webLinkGiftCard");
            target.giftBalance = f9538H.getString(state, "giftBalance");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9538H.putParcelable(state, "webLinkGiftCard", target.webLinkGiftCard);
        f9538H.putString(state, "giftBalance", target.giftBalance);
    }
}
