package com.airbnb.android.listing.logging;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSAutocompleteAddressSuggestionSelectEvent.Builder;

public class LYSAddressAutoCompleteLogger extends BaseLogger {
    public LYSAddressAutoCompleteLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logSelectAutocompleteAddress(Long listingId) {
        publish(new Builder(context(), listingId));
    }
}
