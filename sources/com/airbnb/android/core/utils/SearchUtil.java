package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.utils.Strap;

public class SearchUtil {
    public static final int MAX_EXPERIENCES_COUNT = 100;
    public static final int MAX_HOMES_COUNT = 300;

    @Deprecated
    public static boolean shouldShowTotalPrice(Listing listing) {
        return shouldShowTotalPrice(listing.getPricingQuote());
    }

    @Deprecated
    public static boolean shouldShowTotalPrice(PricingQuote pricingQuote) {
        return (!SearchPricingUtil.isTotalPricingEnabled() || pricingQuote == null || pricingQuote.getPrice() == null) ? false : true;
    }

    @Deprecated
    public static String getPriceTagText(Listing listing) {
        int priceToUse = listing.getPriceNative();
        if (shouldShowTotalPrice(listing)) {
            priceToUse = listing.getPricingQuote().getPrice().getTotal().getAmount().intValue();
        }
        return CoreApplication.instance().component().currencyHelper().formatNativeCurrency((double) priceToUse, true);
    }

    public static String getPriceTagText(PricingQuote pricingQuote, boolean allowTotalPrice) {
        if ((allowTotalPrice || SearchPricingUtil.isTotalPricingEnabled()) && pricingQuote.hasTotalPrice()) {
            return pricingQuote.getTotalPrice().formattedForDisplay();
        }
        if (SearchPricingUtil.isIncludingServiceFeeRequired() && pricingQuote.hasRateWithServiceFee()) {
            return pricingQuote.getRateWithServiceFee().formattedForDisplay();
        }
        if (pricingQuote.hasCanonicalRate()) {
            return pricingQuote.getRateAsGuestCanonical().formattedForDisplay();
        }
        return pricingQuote.getRate().formattedForDisplay();
    }

    public static String getPriceTagText(PricingQuote pricingQuote) {
        return getPriceTagText(pricingQuote, false);
    }

    public static String getSinglelinePriceRateTypeText(Context context, PricingQuote pricingQuote, boolean allowTotalPrice) {
        if (pricingQuote.getRateType() == null) {
            return "";
        }
        String currency = pricingQuote.getRate().getCurrency();
        if (pricingQuote.hasTotalPrice()) {
            if (SearchPricingUtil.isTotalPricingEnabled()) {
                return context.getString(C0716R.string.listing_card_currency_total, new Object[]{currency});
            } else if (allowTotalPrice) {
                return context.getString(C0716R.string.listing_card_total);
            }
        }
        switch (pricingQuote.getRateType()) {
            case Nightly:
                if (!SearchPricingUtil.isTotalPricingEnabled() && !SearchPricingUtil.isIncludingServiceFeeRequired()) {
                    return "";
                }
                return context.getString(C0716R.string.listing_card_per_night, new Object[]{currency});
            case Monthly:
                return context.getString(C0716R.string.listing_card_per_month, new Object[]{currency});
            case Total:
                return context.getString(C0716R.string.listing_card_total);
            default:
                throw new IllegalArgumentException("Don't know how to display rate type " + pricingQuote.getRateType());
        }
    }

    public static Strap addUniqueSearchIdIfPresent(Strap params) {
        return params;
    }

    public static QueryStrap addSupportedUrgencyTypesToRequestStrap(QueryStrap strap) {
        for (UrgencyMessageType type : UrgencyMessageType.values()) {
            strap.mo7895kv("urgency_commitment_supported_types[]", type.getServerKey());
        }
        return strap;
    }
}
