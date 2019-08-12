package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.p000v4.util.LongSparseArray;
import java.util.List;

public class GradientStrokeContent extends BaseStrokeContent {
    private final RectF boundsRect = new RectF();
    private final int cacheSteps;
    private final KeyframeAnimation<GradientColor> colorAnimation;
    private final KeyframeAnimation<PointF> endPointAnimation;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final String name;
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final KeyframeAnimation<PointF> startPointAnimation;
    private final GradientType type;

    public /* bridge */ /* synthetic */ void getBounds(RectF rectF, Matrix matrix) {
        super.getBounds(rectF, matrix);
    }

    public /* bridge */ /* synthetic */ void onValueChanged() {
        super.onValueChanged();
    }

    public /* bridge */ /* synthetic */ void setContents(List list, List list2) {
        super.setContents(list, list2);
    }

    GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer layer, GradientStroke stroke) {
        super(lottieDrawable, layer, stroke.getCapType().toPaintCap(), stroke.getJoinType().toPaintJoin(), stroke.getOpacity(), stroke.getWidth(), stroke.getLineDashPattern(), stroke.getDashOffset());
        this.name = stroke.getName();
        this.type = stroke.getGradientType();
        this.cacheSteps = (int) (lottieDrawable.getComposition().getDuration() / 32);
        this.colorAnimation = stroke.getGradientColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
        this.startPointAnimation = stroke.getStartPoint().createAnimation();
        this.startPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.startPointAnimation);
        this.endPointAnimation = stroke.getEndPoint().createAnimation();
        this.endPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.endPointAnimation);
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        getBounds(this.boundsRect, parentMatrix);
        if (this.type == GradientType.Linear) {
            this.paint.setShader(getLinearGradient());
        } else {
            this.paint.setShader(getRadialGradient());
        }
        super.draw(canvas, parentMatrix, parentAlpha);
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
    }

    public String getName() {
        return this.name;
    }

    private LinearGradient getLinearGradient() {
        int gradientHash = getGradientHash();
        LinearGradient gradient = (LinearGradient) this.linearGradientCache.get((long) gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = (PointF) this.startPointAnimation.getValue();
        PointF endPoint = (PointF) this.endPointAnimation.getValue();
        GradientColor gradientColor = (GradientColor) this.colorAnimation.getValue();
        LinearGradient gradient2 = new LinearGradient((float) ((int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + startPoint.x)), (float) ((int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + startPoint.y)), (float) ((int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + endPoint.x)), (float) ((int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + endPoint.y)), gradientColor.getColors(), gradientColor.getPositions(), TileMode.CLAMP);
        this.linearGradientCache.put((long) gradientHash, gradient2);
        return gradient2;
    }

    private RadialGradient getRadialGradient() {
        int gradientHash = getGradientHash();
        RadialGradient gradient = (RadialGradient) this.radialGradientCache.get((long) gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = (PointF) this.startPointAnimation.getValue();
        PointF endPoint = (PointF) this.endPointAnimation.getValue();
        GradientColor gradientColor = (GradientColor) this.colorAnimation.getValue();
        int x0 = (int) (this.boundsRect.left + (this.boundsRect.width() / 2.0f) + startPoint.x);
        int y0 = (int) (this.boundsRect.top + (this.boundsRect.height() / 2.0f) + startPoint.y);
        float r = (float) Math.hypot((double) (((int) ((this.boundsRect.left + (this.boundsRect.width() / 2.0f)) + endPoint.x)) - x0), (double) (((int) ((this.boundsRect.top + (this.boundsRect.height() / 2.0f)) + endPoint.y)) - y0));
        RadialGradient gradient2 = new RadialGradient((float) x0, (float) y0, r, gradientColor.getColors(), gradientColor.getPositions(), TileMode.CLAMP);
        this.radialGradientCache.put((long) gradientHash, gradient2);
        return gradient2;
    }

    private int getGradientHash() {
        int startPointProgress = Math.round(this.startPointAnimation.getProgress() * ((float) this.cacheSteps));
        int endPointProgress = Math.round(this.endPointAnimation.getProgress() * ((float) this.cacheSteps));
        return startPointProgress * 527 * 31 * endPointProgress * 31 * Math.round(this.colorAnimation.getProgress() * ((float) this.cacheSteps));
    }
}
