package com.airbnb.android.utils;

import android.net.Uri;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityBuilder;

public class CropUtil {
    public static ActivityBuilder square(Uri source) {
        return CropImage.activity(source).setAspectRatio(1, 1).setFixAspectRatio(true).setAllowFlipping(false);
    }
}
