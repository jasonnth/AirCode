package com.facebook.react.views.image;

import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

public class ImageResizeMode {
    public static ScaleType toScaleType(String resizeModeValue) {
        if ("contain".equals(resizeModeValue)) {
            return ScaleType.FIT_CENTER;
        }
        if (PlaceFields.COVER.equals(resizeModeValue)) {
            return ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(resizeModeValue)) {
            return ScaleType.FIT_XY;
        }
        if ("center".equals(resizeModeValue)) {
            return ScaleType.CENTER_INSIDE;
        }
        if (resizeModeValue == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + resizeModeValue + "'");
    }

    public static ScaleType defaultValue() {
        return ScaleType.CENTER_CROP;
    }
}
