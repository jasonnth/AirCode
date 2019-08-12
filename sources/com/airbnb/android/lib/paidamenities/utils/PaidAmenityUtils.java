package com.airbnb.android.lib.paidamenities.utils;

import android.content.Context;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.lib.C0880R;

public class PaidAmenityUtils {
    public static String getFormattedPrice(Context context, CurrencyAmount currencyAmount) {
        if (currencyAmount.isZero()) {
            return context.getResources().getString(C0880R.string.paid_amenities_free);
        }
        return currencyAmount.formattedForDisplay();
    }
}
