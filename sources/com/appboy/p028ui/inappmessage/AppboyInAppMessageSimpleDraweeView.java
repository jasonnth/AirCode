package com.appboy.p028ui.inappmessage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.support.AppboyLogger;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageSimpleDraweeView */
public class AppboyInAppMessageSimpleDraweeView extends SimpleDraweeView implements IInAppMessageImageView {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageSimpleDraweeView.class.getName()});
    private Path mClipPath = new Path();
    private float[] mInAppRadii;
    private RectF mRect = new RectF();

    public AppboyInAppMessageSimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCornersRadiiPx(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        this.mInAppRadii = new float[]{topLeft, topLeft, topRight, topRight, bottomLeft, bottomLeft, bottomRight, bottomRight};
    }

    public void setCornersRadiusPx(float cornersRadius) {
        setCornersRadiiPx(cornersRadius, cornersRadius, cornersRadius, cornersRadius);
    }

    public void setInAppMessageImageCropType(CropType cropType) {
        if (cropType.equals(CropType.FIT_CENTER)) {
            ((GenericDraweeHierarchy) getHierarchy()).setActualImageScaleType(ScaleType.FIT_CENTER);
        } else if (cropType.equals(CropType.CENTER_CROP)) {
            ((GenericDraweeHierarchy) getHierarchy()).setActualImageScaleType(ScaleType.CENTER_CROP);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        clipCanvasToPath(canvas, getWidth(), getHeight());
        super.onDraw(canvas);
    }

    /* access modifiers changed from: 0000 */
    public boolean clipCanvasToPath(Canvas canvas, int widthPx, int heightPx) {
        if (this.mInAppRadii == null) {
            return false;
        }
        try {
            this.mClipPath.reset();
            this.mRect.set(0.0f, 0.0f, (float) widthPx, (float) heightPx);
            this.mClipPath.addRoundRect(this.mRect, this.mInAppRadii, Direction.CW);
            canvas.clipPath(this.mClipPath);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Encountered exception while trying to clip in-app message image", e);
            return false;
        }
    }
}
