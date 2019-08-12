package com.airbnb.android.listing.utils;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.utils.AirPhotoPicker;

public class ListingPhotoPickerUtil {
    private static final int MAX_PHOTO_SIZE_BYTES = 2048;

    public static Intent createPickerIntent(Context context) {
        return AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(0).create(context);
    }
}
