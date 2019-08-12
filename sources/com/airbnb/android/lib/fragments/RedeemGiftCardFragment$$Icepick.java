package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.lib.fragments.RedeemGiftCardFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class RedeemGiftCardFragment$$Icepick<T extends RedeemGiftCardFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9547H = new Helper("com.airbnb.android.lib.fragments.RedeemGiftCardFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.webLinkGiftCard = (GiftCard) f9547H.getParcelable(state, "webLinkGiftCard");
            target.giftCardCode = f9547H.getString(state, "giftCardCode");
            target.giftCreditAmountRedeemed = f9547H.getInt(state, "giftCreditAmountRedeemed");
            target.formattedGiftCreditBalanceTotal = f9547H.getString(state, "formattedGiftCreditBalanceTotal");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9547H.putParcelable(state, "webLinkGiftCard", target.webLinkGiftCard);
        f9547H.putString(state, "giftCardCode", target.giftCardCode);
        f9547H.putInt(state, "giftCreditAmountRedeemed", target.giftCreditAmountRedeemed);
        f9547H.putString(state, "formattedGiftCreditBalanceTotal", target.formattedGiftCreditBalanceTotal);
    }
}
