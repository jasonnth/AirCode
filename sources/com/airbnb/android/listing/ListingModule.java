package com.airbnb.android.listing;

import android.content.Context;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.listing.logging.LYSAddressAutoCompleteLogger;
import com.airbnb.android.listing.logging.WhatsMyPlaceWorthLogger;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;

public class ListingModule {
    public static PhotoUploadManager photoUploadManager(Context context) {
        return new PhotoUploadManager(context);
    }

    public static WhatsMyPlaceWorthLogger provideWhatsMyPlaceWorthLogger(LoggingContextFactory loggingContextFactory) {
        return new WhatsMyPlaceWorthLogger(loggingContextFactory);
    }

    public static LYSAddressAutoCompleteLogger provideAddressAutoCompleteLogger(LoggingContextFactory loggingContextFactory) {
        return new LYSAddressAutoCompleteLogger(loggingContextFactory);
    }
}
