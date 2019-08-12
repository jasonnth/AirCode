package com.airbnb.android.core.utils;

import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.photopicker.PhotoPicker.Builder;

public class AirPhotoPicker {
    public static Builder builder() {
        return PhotoPicker.builder().setApplicationId(BuildHelper.applicationId());
    }
}
