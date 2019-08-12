package com.airbnb.android.listing;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.listing.adapters.BasePriceAdapter;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment;
import com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment;

public interface ListingGraph extends BaseGraph {
    void inject(BasePriceAdapter basePriceAdapter);

    void inject(LongTermDiscountsAdapter longTermDiscountsAdapter);

    void inject(NightlyPriceAdapter nightlyPriceAdapter);

    void inject(AddressAutoCompleteFragment addressAutoCompleteFragment);

    void inject(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment);
}
