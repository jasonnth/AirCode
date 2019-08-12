package com.airbnb.android.core.p009p3.interfaces;

import com.airbnb.android.core.enums.ContactHostAction;
import com.airbnb.android.core.models.PricingQuote;

/* renamed from: com.airbnb.android.core.p3.interfaces.ContactHostDataChangeListener */
public interface ContactHostDataChangeListener {
    void onActionCompleted(ContactHostAction contactHostAction);

    void onPricingQuoteLoaded(PricingQuote pricingQuote);
}
