package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.GiftCard;

public final class LoginActivityIntents {
    public static final String EXTRA_TOAST_MSG = "toast_msg";
    public static final String EXTRA_WEB_LINK_GIFT_CARD = "web_link_gift_card";

    private LoginActivityIntents() {
    }

    public static Intent intent(Context context) {
        return new Intent(context, Activities.login());
    }

    public static Intent intentWithToast(Context context, int toastStringRes) {
        return intent(context).putExtra(EXTRA_TOAST_MSG, context.getString(toastStringRes));
    }

    public static Intent intentWithGiftCardRedemption(Context context, GiftCard webLinkGiftCard) {
        return intent(context).putExtra("web_link_gift_card", webLinkGiftCard);
    }
}
