package com.airbnb.android.photopicker;

import android.content.Intent;
import android.os.Build.VERSION;

public class GalleryUtils {
    static Intent createGalleryIntent(boolean allowMultiple) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        if (allowMultiple && VERSION.SDK_INT > 18) {
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", allowMultiple);
        }
        return intent;
    }
}
