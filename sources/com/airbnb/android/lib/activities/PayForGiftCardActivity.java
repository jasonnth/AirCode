package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class PayForGiftCardActivity extends SubmitPaymentActivity {
    private static final String EVENT_GIFT_CARD_CHECKOUT_COMPLETED = "gift_card_checkout_completed";
    public static final String EXTRA_GIFT_AMOUNT = "gift_amount";
    public static final String EXTRA_USER_EMAIL = "user_email";
    public static final String EXTRA_USER_NAME = "user_name";

    public static Intent forGiftCardAmount(Context context, long paymentId, String name, String email, int amount) {
        return forPaymentId(context, paymentId).setClass(context, PayForGiftCardActivity.class).toIntent().putExtra("user_name", name).putExtra(EXTRA_USER_EMAIL, email).putExtra(EXTRA_GIFT_AMOUNT, amount);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPaymentSuccess() {
        Intent intent = getIntent();
        int amount = intent.getIntExtra(EXTRA_GIFT_AMOUNT, 0);
        AirbnbEventLogger.track(EVENT_GIFT_CARD_CHECKOUT_COMPLETED, Strap.make().mo11637kv(GiftCardsActivity.EVENT_DATA_PARAM_GIFT_AMOUNT, amount));
        Intent resultIntent = new Intent();
        resultIntent.putExtra("user_name", intent.getStringExtra("user_name"));
        resultIntent.putExtra(EXTRA_USER_EMAIL, intent.getStringExtra(EXTRA_USER_EMAIL));
        resultIntent.putExtra(EXTRA_GIFT_AMOUNT, amount);
        setResult(-1, resultIntent);
        finish();
    }
}
