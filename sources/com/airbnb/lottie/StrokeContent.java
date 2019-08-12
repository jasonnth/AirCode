package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;

class StrokeContent extends BaseStrokeContent {
    private final KeyframeAnimation<Integer> colorAnimation;
    private final String name;

    StrokeContent(LottieDrawable lottieDrawable, BaseLayer layer, ShapeStroke stroke) {
        super(lottieDrawable, layer, stroke.getCapType().toPaintCap(), stroke.getJoinType().toPaintJoin(), stroke.getOpacity(), stroke.getWidth(), stroke.getLineDashPattern(), stroke.getDashOffset());
        this.name = stroke.getName();
        this.colorAnimation = stroke.getColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.paint.setColor(((Integer) this.colorAnimation.getValue()).intValue());
        super.draw(canvas, parentMatrix, parentAlpha);
    }

    public String getName() {
        return this.name;
    }
}
