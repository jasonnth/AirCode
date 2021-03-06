package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.LightBitmapDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.RoundedLightBitmapDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.RoundingParams.RoundingMethod;

public class WrappingUtils {
    private static final Drawable sEmptyDrawable = new ColorDrawable(0);

    static Drawable maybeWrapWithScaleType(Drawable drawable, ScaleType scaleType) {
        return maybeWrapWithScaleType(drawable, scaleType, null);
    }

    static Drawable maybeWrapWithScaleType(Drawable drawable, ScaleType scaleType, PointF focusPoint) {
        if (drawable == null || scaleType == null) {
            return drawable;
        }
        ScaleTypeDrawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scaleType);
        if (focusPoint == null) {
            return scaleTypeDrawable;
        }
        scaleTypeDrawable.setFocusPoint(focusPoint);
        return scaleTypeDrawable;
    }

    static Drawable maybeWrapWithMatrix(Drawable drawable, Matrix matrix) {
        return (drawable == null || matrix == null) ? drawable : new MatrixDrawable(drawable, matrix);
    }

    static ScaleTypeDrawable wrapChildWithScaleType(DrawableParent parent, ScaleType scaleType) {
        Drawable child = maybeWrapWithScaleType(parent.setDrawable(sEmptyDrawable), scaleType);
        parent.setDrawable(child);
        Preconditions.checkNotNull(child, "Parent has no child drawable!");
        return (ScaleTypeDrawable) child;
    }

    static void updateOverlayColorRounding(DrawableParent parent, RoundingParams roundingParams) {
        Drawable child = parent.getDrawable();
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.OVERLAY_COLOR) {
            if (child instanceof RoundedCornersDrawable) {
                parent.setDrawable(((RoundedCornersDrawable) child).setCurrent(sEmptyDrawable));
                sEmptyDrawable.setCallback(null);
            }
        } else if (child instanceof RoundedCornersDrawable) {
            RoundedCornersDrawable roundedCornersDrawable = (RoundedCornersDrawable) child;
            applyRoundingParams(roundedCornersDrawable, roundingParams);
            roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        } else {
            parent.setDrawable(maybeWrapWithRoundedOverlayColor(parent.setDrawable(sEmptyDrawable), roundingParams));
        }
    }

    static void updateLeafRounding(DrawableParent parent, RoundingParams roundingParams, Resources resources) {
        DrawableParent parent2 = findDrawableParentForLeaf(parent);
        Drawable child = parent2.getDrawable();
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.BITMAP_ONLY) {
            if (child instanceof Rounded) {
                resetRoundingParams((Rounded) child);
            }
        } else if (child instanceof Rounded) {
            applyRoundingParams((Rounded) child, roundingParams);
        } else if (child != null) {
            parent2.setDrawable(sEmptyDrawable);
            parent2.setDrawable(applyLeafRounding(child, roundingParams, resources));
        }
    }

    static Drawable maybeWrapWithRoundedOverlayColor(Drawable drawable, RoundingParams roundingParams) {
        if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.OVERLAY_COLOR) {
            return drawable;
        }
        RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
        applyRoundingParams(roundedCornersDrawable, roundingParams);
        roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        return roundedCornersDrawable;
    }

    static Drawable maybeApplyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.BITMAP_ONLY) {
            return drawable;
        }
        if (!(drawable instanceof ForwardingDrawable)) {
            return applyLeafRounding(drawable, roundingParams, resources);
        }
        DrawableParent parent = findDrawableParentForLeaf((ForwardingDrawable) drawable);
        parent.setDrawable(applyLeafRounding(parent.setDrawable(sEmptyDrawable), roundingParams, resources));
        return drawable;
    }

    private static Drawable applyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            RoundedBitmapDrawable roundedBitmapDrawable = new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            applyRoundingParams(roundedBitmapDrawable, roundingParams);
            return roundedBitmapDrawable;
        } else if (drawable instanceof LightBitmapDrawable) {
            LightBitmapDrawable lightBitmapDrawable = (LightBitmapDrawable) drawable;
            RoundedLightBitmapDrawable roundedBitmapDrawable2 = new RoundedLightBitmapDrawable(resources, lightBitmapDrawable.getBitmap(), lightBitmapDrawable.getPaint());
            applyRoundingParams(roundedBitmapDrawable2, roundingParams);
            return roundedBitmapDrawable2;
        } else if (!(drawable instanceof ColorDrawable) || VERSION.SDK_INT < 11) {
            return drawable;
        } else {
            RoundedColorDrawable roundedColorDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable) drawable);
            applyRoundingParams(roundedColorDrawable, roundingParams);
            return roundedColorDrawable;
        }
    }

    static void applyRoundingParams(Rounded rounded, RoundingParams roundingParams) {
        rounded.setCircle(roundingParams.getRoundAsCircle());
        rounded.setRadii(roundingParams.getCornersRadii());
        rounded.setBorder(roundingParams.getBorderColor(), roundingParams.getBorderWidth());
        rounded.setPadding(roundingParams.getPadding());
    }

    static void resetRoundingParams(Rounded rounded) {
        rounded.setCircle(false);
        rounded.setRadius(0.0f);
        rounded.setBorder(0, 0.0f);
        rounded.setPadding(0.0f);
    }

    static DrawableParent findDrawableParentForLeaf(DrawableParent parent) {
        while (true) {
            Drawable child = parent.getDrawable();
            if (child == parent || !(child instanceof DrawableParent)) {
                return parent;
            }
            parent = (DrawableParent) child;
        }
        return parent;
    }
}
