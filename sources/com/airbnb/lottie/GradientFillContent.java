package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.p000v4.util.LongSparseArray;
import java.util.ArrayList;
import java.util.List;

class GradientFillContent implements AnimationListener, DrawingContent {
    private final RectF boundsRect = new RectF();
    private final int cacheSteps;
    private final KeyframeAnimation<GradientColor> colorAnimation;
    private final KeyframeAnimation<PointF> endPointAnimation;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final KeyframeAnimation<Integer> opacityAnimation;
    private final Paint paint = new Paint(1);
    private final Path path = new Path();
    private final List<PathContent> paths = new ArrayList();
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final KeyframeAnimation<PointF> startPointAnimation;
    private final GradientType type;

    GradientFillContent(LottieDrawable lottieDrawable2, BaseLayer layer, GradientFill fill) {
        this.name = fill.getName();
        this.lottieDrawable = lottieDrawable2;
        this.type = fill.getGradientType();
        this.path.setFillType(fill.getFillType());
        this.cacheSteps = (int) (lottieDrawable2.getComposition().getDuration() / 32);
        this.colorAnimation = fill.getGradientColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
        this.opacityAnimation = fill.getOpacity().createAnimation();
        this.opacityAnimation.addUpdateListener(this);
        layer.addAnimation(this.opacityAnimation);
        this.startPointAnimation = fill.getStartPoint().createAnimation();
        this.startPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.startPointAnimation);
        this.endPointAnimation = fill.getEndPoint().createAnimation();
        this.endPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.endPointAnimation);
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> contentsAfter) {
        for (int i = 0; i < contentsAfter.size(); i++) {
            Content content = (Content) contentsAfter.get(i);
            if (content instanceof PathContent) {
                this.paths.add((PathContent) content);
            }
        }
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(((PathContent) this.paths.get(i)).getPath(), parentMatrix);
        }
        this.path.computeBounds(this.boundsRect, false);
        if (this.type == GradientType.Linear) {
            this.paint.setShader(getLinearGradient());
        } else {
            this.paint.setShader(getRadialGradient());
        }
        this.paint.setAlpha((int) (((((float) ((Integer) this.opacityAnimation.getValue()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f));
        canvas.drawPath(this.path, this.paint);
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(((PathContent) this.paths.get(i)).getPath(), parentMatrix);
        }
        this.path.computeBounds(outBounds, false);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
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
