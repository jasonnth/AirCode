package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

class FillContent implements AnimationListener, DrawingContent {
    private final KeyframeAnimation<Integer> colorAnimation;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final KeyframeAnimation<Integer> opacityAnimation;
    private final Paint paint = new Paint(1);
    private final Path path = new Path();
    private final List<PathContent> paths = new ArrayList();

    FillContent(LottieDrawable lottieDrawable2, BaseLayer layer, ShapeFill fill) {
        this.name = fill.getName();
        this.lottieDrawable = lottieDrawable2;
        if (fill.getColor() == null || fill.getOpacity() == null) {
            this.colorAnimation = null;
            this.opacityAnimation = null;
            return;
        }
        this.path.setFillType(fill.getFillType());
        this.colorAnimation = fill.getColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
        this.opacityAnimation = fill.getOpacity().createAnimation();
        this.opacityAnimation.addUpdateListener(this);
        layer.addAnimation(this.opacityAnimation);
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

    public String getName() {
        return this.name;
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.paint.setColor(((Integer) this.colorAnimation.getValue()).intValue());
        this.paint.setAlpha((int) (((((float) ((Integer) this.opacityAnimation.getValue()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f));
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(((PathContent) this.paths.get(i)).getPath(), parentMatrix);
        }
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
}
