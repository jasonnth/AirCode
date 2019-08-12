package com.jumio.commons.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import org.spongycastle.asn1.eac.EACTags;

public class PixelConverterUtil {
    private static final float DEFAULT = 0.56f;
    private static final float HDPI = 0.5f;
    private static final float HDPI_FONT = 0.5f;
    private static final float MDPI = 0.5f;
    private static final float MDPI_FONT = 0.67f;
    private static final float XHDPI = 0.56f;
    private static final float XHDPI_FONT = 0.56f;
    private static final float XXHDPI = 0.56f;
    private static final float XXHDPI_FONT = 0.56f;

    public static int getPxFromPt(Context context, int pt) {
        return getPxFromPt(context, pt, false);
    }

    public static int getPxFromPt(Context context, int pt, boolean font) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        switch (displayMetrics.densityDpi) {
            case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
            case 160:
                return font ? (int) TypedValue.applyDimension(3, ((float) pt) * MDPI_FONT, displayMetrics) : (int) TypedValue.applyDimension(3, ((float) pt) * 0.5f, displayMetrics);
            case 240:
                return font ? (int) TypedValue.applyDimension(3, ((float) pt) * 0.5f, displayMetrics) : (int) TypedValue.applyDimension(3, ((float) pt) * 0.5f, displayMetrics);
            case 320:
                return font ? (int) TypedValue.applyDimension(3, ((float) pt) * 0.56f, displayMetrics) : (int) TypedValue.applyDimension(3, ((float) pt) * 0.56f, displayMetrics);
            case 480:
                return font ? (int) TypedValue.applyDimension(3, ((float) pt) * 0.56f, displayMetrics) : (int) TypedValue.applyDimension(3, ((float) pt) * 0.56f, displayMetrics);
            default:
                return (int) TypedValue.applyDimension(3, ((float) pt) * 0.56f, displayMetrics);
        }
    }
}
