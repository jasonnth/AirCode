package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

abstract class BaseStrokeContent implements AnimationListener, DrawingContent {
    private final List<BaseKeyframeAnimation<?, Float>> dashPatternAnimations;
    private final BaseKeyframeAnimation<?, Float> dashPatternOffsetAnimation;
    private final float[] dashPatternValues;
    private final LottieDrawable lottieDrawable;
    private final BaseKeyframeAnimation<?, Integer> opacityAnimation;
    final Paint paint = new Paint(1);
    private final Path path = new Path();
    private final List<PathGroup> pathGroups = new ArrayList();

    /* renamed from: pm */
    private final PathMeasure f2700pm = new PathMeasure();
    private final RectF rect = new RectF();
    private final Path trimPathPath = new Path();
    private final BaseKeyframeAnimation<?, Float> widthAnimation;

    private static final class PathGroup {
        /* access modifiers changed from: private */
        public final List<PathContent> paths;
        /* access modifiers changed from: private */
        public final TrimPathContent trimPath;

        private PathGroup(TrimPathContent trimPath2) {
            this.paths = new ArrayList();
            this.trimPath = trimPath2;
        }
    }

    BaseStrokeContent(LottieDrawable lottieDrawable2, BaseLayer layer, Cap cap, Join join, AnimatableIntegerValue opacity, AnimatableFloatValue width, List<AnimatableFloatValue> dashPattern, AnimatableFloatValue offset) {
        this.lottieDrawable = lottieDrawable2;
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeCap(cap);
        this.paint.setStrokeJoin(join);
        this.opacityAnimation = opacity.createAnimation();
        this.widthAnimation = width.createAnimation();
        if (offset == null) {
            this.dashPatternOffsetAnimation = null;
        } else {
            this.dashPatternOffsetAnimation = offset.createAnimation();
        }
        this.dashPatternAnimations = new ArrayList(dashPattern.size());
        this.dashPatternValues = new float[dashPattern.size()];
        for (int i = 0; i < dashPattern.size(); i++) {
            this.dashPatternAnimations.add(((AnimatableFloatValue) dashPattern.get(i)).createAnimation());
        }
        layer.addAnimation(this.opacityAnimation);
        layer.addAnimation(this.widthAnimation);
        for (int i2 = 0; i2 < this.dashPatternAnimations.size(); i2++) {
            layer.addAnimation((BaseKeyframeAnimation) this.dashPatternAnimations.get(i2));
        }
        if (this.dashPatternOffsetAnimation != null) {
            layer.addAnimation(this.dashPatternOffsetAnimation);
        }
        this.opacityAnimation.addUpdateListener(this);
        this.widthAnimation.addUpdateListener(this);
        for (int i3 = 0; i3 < dashPattern.size(); i3++) {
            ((BaseKeyframeAnimation) this.dashPatternAnimations.get(i3)).addUpdateListener(this);
        }
        if (this.dashPatternOffsetAnimation != null) {
            this.dashPatternOffsetAnimation.addUpdateListener(this);
        }
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        TrimPathContent trimPathContentBefore = null;
        for (int i = contentsBefore.size() - 1; i >= 0; i--) {
            Content content = (Content) contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == Type.Individually) {
                trimPathContentBefore = (TrimPathContent) content;
            }
        }
        if (trimPathContentBefore != null) {
            trimPathContentBefore.addListener(this);
        }
        PathGroup currentPathGroup = null;
        for (int i2 = contentsAfter.size() - 1; i2 >= 0; i2--) {
            Content content2 = (Content) contentsAfter.get(i2);
            if ((content2 instanceof TrimPathContent) && ((TrimPathContent) content2).getType() == Type.Individually) {
                if (currentPathGroup != null) {
                    this.pathGroups.add(currentPathGroup);
                }
                currentPathGroup = new PathGroup((TrimPathContent) content2);
                ((TrimPathContent) content2).addListener(this);
            } else if (content2 instanceof PathContent) {
                if (currentPathGroup == null) {
                    currentPathGroup = new PathGroup(trimPathContentBefore);
                }
                currentPathGroup.paths.add((PathContent) content2);
            }
        }
        if (currentPathGroup != null) {
            this.pathGroups.add(currentPathGroup);
        }
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.paint.setAlpha((int) (((((float) ((Integer) this.opacityAnimation.getValue()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f));
        this.paint.setStrokeWidth(((Float) this.widthAnimation.getValue()).floatValue() * Utils.getScale(parentMatrix));
        if (this.paint.getStrokeWidth() > 0.0f) {
            applyDashPatternIfNeeded(parentMatrix);
            for (int i = 0; i < this.pathGroups.size(); i++) {
                PathGroup pathGroup = (PathGroup) this.pathGroups.get(i);
                if (pathGroup.trimPath != null) {
                    applyTrimPath(canvas, pathGroup, parentMatrix);
                } else {
                    this.path.reset();
                    for (int j = pathGroup.paths.size() - 1; j >= 0; j--) {
                        this.path.addPath(((PathContent) pathGroup.paths.get(j)).getPath(), parentMatrix);
                    }
                    canvas.drawPath(this.path, this.paint);
                }
            }
        }
    }

    private void applyTrimPath(Canvas canvas, PathGroup pathGroup, Matrix parentMatrix) {
        float startValue;
        float endValue;
        float startValue2;
        if (pathGroup.trimPath != null) {
            this.path.reset();
            for (int j = pathGroup.paths.size() - 1; j >= 0; j--) {
                this.path.addPath(((PathContent) pathGroup.paths.get(j)).getPath(), parentMatrix);
            }
            this.f2700pm.setPath(this.path, false);
            float totalLength = this.f2700pm.getLength();
            while (this.f2700pm.nextContour()) {
                totalLength += this.f2700pm.getLength();
            }
            float offsetLength = (((Float) pathGroup.trimPath.getOffset().getValue()).floatValue() * totalLength) / 360.0f;
            float startLength = ((((Float) pathGroup.trimPath.getStart().getValue()).floatValue() * totalLength) / 100.0f) + offsetLength;
            float endLength = ((((Float) pathGroup.trimPath.getEnd().getValue()).floatValue() * totalLength) / 100.0f) + offsetLength;
            float currentLength = 0.0f;
            for (int j2 = pathGroup.paths.size() - 1; j2 >= 0; j2--) {
                this.trimPathPath.set(((PathContent) pathGroup.paths.get(j2)).getPath());
                this.trimPathPath.transform(parentMatrix);
                this.f2700pm.setPath(this.trimPathPath, false);
                float length = this.f2700pm.getLength();
                if (endLength > totalLength && endLength - totalLength < currentLength + length && currentLength < endLength - totalLength) {
                    if (startLength > totalLength) {
                        startValue2 = (startLength - totalLength) / length;
                    } else {
                        startValue2 = 0.0f;
                    }
                    Utils.applyTrimPathIfNeeded(this.trimPathPath, startValue2, Math.min((endLength - totalLength) / length, 1.0f), 0.0f);
                    canvas.drawPath(this.trimPathPath, this.paint);
                } else if (currentLength + length >= startLength && currentLength <= endLength) {
                    if (currentLength + length > endLength || startLength >= currentLength) {
                        if (startLength < currentLength) {
                            startValue = 0.0f;
                        } else {
                            startValue = (startLength - currentLength) / length;
                        }
                        if (endLength > currentLength + length) {
                            endValue = 1.0f;
                        } else {
                            endValue = (endLength - currentLength) / length;
                        }
                        Utils.applyTrimPathIfNeeded(this.trimPathPath, startValue, endValue, 0.0f);
                        canvas.drawPath(this.trimPathPath, this.paint);
                    } else {
                        canvas.drawPath(this.trimPathPath, this.paint);
                    }
                }
                currentLength += length;
            }
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        this.path.reset();
        for (int i = 0; i < this.pathGroups.size(); i++) {
            PathGroup pathGroup = (PathGroup) this.pathGroups.get(i);
            for (int j = 0; j < pathGroup.paths.size(); j++) {
                this.path.addPath(((PathContent) pathGroup.paths.get(i)).getPath(), parentMatrix);
            }
        }
        this.path.computeBounds(this.rect, false);
        float width = ((Float) this.widthAnimation.getValue()).floatValue();
        this.rect.set(this.rect.left - (width / 2.0f), this.rect.top - (width / 2.0f), this.rect.right + (width / 2.0f), this.rect.bottom + (width / 2.0f));
        outBounds.set(this.rect);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
    }

    private void applyDashPatternIfNeeded(Matrix parentMatrix) {
        if (!this.dashPatternAnimations.isEmpty()) {
            float scale = Utils.getScale(parentMatrix);
            for (int i = 0; i < this.dashPatternAnimations.size(); i++) {
                this.dashPatternValues[i] = ((Float) ((BaseKeyframeAnimation) this.dashPatternAnimations.get(i)).getValue()).floatValue();
                if (i % 2 == 0) {
                    if (this.dashPatternValues[i] < 1.0f) {
                        this.dashPatternValues[i] = 1.0f;
                    }
                } else if (this.dashPatternValues[i] < 0.1f) {
                    this.dashPatternValues[i] = 0.1f;
                }
                float[] fArr = this.dashPatternValues;
                fArr[i] = fArr[i] * scale;
            }
            this.paint.setPathEffect(new DashPathEffect(this.dashPatternValues, this.dashPatternOffsetAnimation == null ? 0.0f : ((Float) this.dashPatternOffsetAnimation.getValue()).floatValue()));
        }
    }
}
