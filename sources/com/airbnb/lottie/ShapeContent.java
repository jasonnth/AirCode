package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.Path.FillType;
import java.util.List;

class ShapeContent implements AnimationListener, PathContent {
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, Path> shapeAnimation;
    private TrimPathContent trimPath;

    ShapeContent(LottieDrawable lottieDrawable2, BaseLayer layer, ShapePath shape) {
        this.name = shape.getName();
        this.lottieDrawable = lottieDrawable2;
        this.shapeAnimation = shape.getShapePath().createAnimation();
        layer.addAnimation(this.shapeAnimation);
        this.shapeAnimation.addUpdateListener(this);
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = (Content) contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == Type.Simultaneously) {
                this.trimPath = (TrimPathContent) content;
                this.trimPath.addListener(this);
            }
        }
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        this.path.set((Path) this.shapeAnimation.getValue());
        this.path.setFillType(FillType.EVEN_ODD);
        Utils.applyTrimPathIfNeeded(this.path, this.trimPath);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }
}
