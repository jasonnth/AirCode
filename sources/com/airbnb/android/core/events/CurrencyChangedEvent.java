package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Currency;

public class CurrencyChangedEvent {
    public final Currency currency;

    public CurrencyChangedEvent(Currency currency2) {
        this.currency = currency2;
    }
}
