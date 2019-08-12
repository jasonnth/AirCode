package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.lib.fragments.CompleteGiftCardPurchaseFragment;
import com.airbnb.android.lib.fragments.CompleteGiftCreditRedeemFragment;
import com.airbnb.android.lib.fragments.MainGiftCardsFragment;
import com.airbnb.android.lib.fragments.RedeemGiftCardFragment;
import com.airbnb.android.lib.fragments.SendGiftCardFragment;
import com.airbnb.android.utils.Strap;

public class GiftCardsActivity extends SolitAirActivity {
    public static final String EVENT_DATA_PARAM_GIFT_AMOUNT = "amount";
    public static final String EVENT_GIFT_CARD_CHECKOUT_CLICKED = "gift_card_checkout_clicked";
    public static final String EXTRA_WEB_LINK_GIFT_CARD = "web_link_gift_card";

    public static Intent intent(Context context) {
        return new Intent(context, GiftCardsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            initializeMainFragment();
            AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv("sub_event", "gift_card_impression"));
        }
    }

    private void initializeMainFragment() {
        MainGiftCardsFragment mainFrag;
        GiftCard webLinkGiftCard = (GiftCard) getIntent().getParcelableExtra("web_link_gift_card");
        if (webLinkGiftCard != null) {
            mainFrag = MainGiftCardsFragment.newInstance(webLinkGiftCard);
        } else {
            mainFrag = MainGiftCardsFragment.newInstance();
        }
        showFragment(mainFrag, true);
    }

    public void startSendGiftCard() {
        showFragment(SendGiftCardFragment.newInstance(), true);
    }

    public void startRedeemGiftCard() {
        showFragment(RedeemGiftCardFragment.newInstance(), true);
    }

    public void showCompleteGiftCardRedeemFragment(String formattedBalance) {
        showFragment(CompleteGiftCreditRedeemFragment.newInstance(formattedBalance), true);
    }

    public void showCompleteGiftCardPurchaseFragment(String name, String email, int giftCreditAmount) {
        showFragment(CompleteGiftCardPurchaseFragment.newInstance(name, email, giftCreditAmount), true);
        AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv("sub_event", "gift_card_purchase_complete"));
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
