package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ContentGroup implements AnimationListener, DrawingContent, PathContent {
    private static final String TAG = ContentGroup.class.getSimpleName();
    private final List<Content> contents = new ArrayList();
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private final String name;
    private final Path path = new Path();
    private List<PathContent> pathContents;
    private final RectF rect = new RectF();
    private TransformKeyframeAnimation transformAnimation;

    ContentGroup(LottieDrawable lottieDrawable2, BaseLayer layer, ShapeGroup shapeGroup) {
        this.name = shapeGroup.getName();
        this.lottieDrawable = lottieDrawable2;
        List<Object> items = shapeGroup.getItems();
        if (!items.isEmpty()) {
            Object potentialTransform = items.get(items.size() - 1);
            if (potentialTransform instanceof AnimatableTransform) {
                this.transformAnimation = ((AnimatableTransform) potentialTransform).createAnimation();
                this.transformAnimation.addAnimationsToLayer(layer);
                this.transformAnimation.addListener(this);
            }
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);
                if (item instanceof ShapeFill) {
                    this.contents.add(new FillContent(lottieDrawable2, layer, (ShapeFill) item));
                } else if (item instanceof GradientFill) {
                    this.contents.add(new GradientFillContent(lottieDrawable2, layer, (GradientFill) item));
                } else if (item instanceof ShapeStroke) {
                    this.contents.add(new StrokeContent(lottieDrawable2, layer, (ShapeStroke) item));
                } else if (item instanceof GradientStroke) {
                    this.contents.add(new GradientStrokeContent(lottieDrawable2, layer, (GradientStroke) item));
                } else if (item instanceof ShapeGroup) {
                    this.contents.add(new ContentGroup(lottieDrawable2, layer, (ShapeGroup) item));
                } else if (item instanceof RectangleShape) {
                    this.contents.add(new RectangleContent(lottieDrawable2, layer, (RectangleShape) item));
                } else if (item instanceof CircleShape) {
                    this.contents.add(new EllipseContent(lottieDrawable2, layer, (CircleShape) item));
                } else if (item instanceof ShapePath) {
                    this.contents.add(new ShapeContent(lottieDrawable2, layer, (ShapePath) item));
                } else if (item instanceof PolystarShape) {
                    this.contents.add(new PolystarContent(lottieDrawable2, layer, (PolystarShape) item));
                } else if (item instanceof ShapeTrimPath) {
                    this.contents.add(new TrimPathContent(layer, (ShapeTrimPath) item));
                } else if (item instanceof MergePaths) {
                    if (lottieDrawable2.enableMergePathsForKitKatAndAbove()) {
                        this.contents.add(new MergePathsContent((MergePaths) item));
                    } else {
                        Log.w(TAG, "Animation contains merge paths but they are disabled.");
                    }
                }
            }
            List<Content> contentsToRemove = new ArrayList<>();
            MergePathsContent currentMergePathsContent = null;
            for (int i2 = this.contents.size() - 1; i2 >= 0; i2--) {
                Content content = (Content) this.contents.get(i2);
                if (content instanceof MergePathsContent) {
                    currentMergePathsContent = (MergePathsContent) content;
                }
                if (!(currentMergePathsContent == null || content == currentMergePathsContent)) {
                    currentMergePathsContent.addContentIfNeeded(content);
                    contentsToRemove.add(content);
                }
            }
            Iterator<Content> it = this.contents.iterator();
            while (it.hasNext()) {
                if (contentsToRemove.contains((Content) it.next())) {
                    it.remove();
                }
            }
        }
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public String getName() {
        return this.name;
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        for (int i = 0; i < this.contents.size(); i++) {
            Content content = (Content) this.contents.get(i);
            if (content instanceof DrawingContent) {
                DrawingContent drawingContent = (DrawingContent) content;
                if (contentName == null || contentName.equals(content.getName())) {
                    drawingContent.addColorFilter(layerName, null, colorFilter);
                } else {
                    drawingContent.addColorFilter(layerName, contentName, colorFilter);
                }
            }
        }
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        List<Content> myContentsBefore = new ArrayList<>(contentsBefore.size() + this.contents.size());
        myContentsBefore.addAll(contentsBefore);
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            Content content = (Content) this.contents.get(i);
            content.setContents(myContentsBefore, this.contents.subList(0, i));
            myContentsBefore.add(content);
        }
    }

    /* access modifiers changed from: 0000 */
    public List<PathContent> getPathList() {
        if (this.pathContents == null) {
            this.pathContents = new ArrayList();
            for (int i = 0; i < this.contents.size(); i++) {
                Content content = (Content) this.contents.get(i);
                if (content instanceof PathContent) {
                    this.pathContents.add((PathContent) content);
                }
            }
        }
        return this.pathContents;
    }

    /* access modifiers changed from: 0000 */
    public Matrix getTransformationMatrix() {
        if (this.transformAnimation != null) {
            return this.transformAnimation.getMatrix();
        }
        this.matrix.reset();
        return this.matrix;
    }

    public Path getPath() {
        this.matrix.reset();
        if (this.transformAnimation != null) {
            this.matrix.set(this.transformAnimation.getMatrix());
        }
        this.path.reset();
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            Content content = (Content) this.contents.get(i);
            if (content instanceof PathContent) {
                this.path.addPath(((PathContent) content).getPath(), this.matrix);
            }
        }
        return this.path;
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int alpha;
        this.matrix.set(parentMatrix);
        if (this.transformAnimation != null) {
            this.matrix.preConcat(this.transformAnimation.getMatrix());
            alpha = (int) ((((((float) ((Integer) this.transformAnimation.getOpacity().getValue()).intValue()) / 100.0f) * ((float) parentAlpha)) / 255.0f) * 255.0f);
        } else {
            alpha = parentAlpha;
        }
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            Object content = this.contents.get(i);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).draw(canvas, this.matrix, alpha);
            }
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        this.matrix.set(parentMatrix);
        if (this.transformAnimation != null) {
            this.matrix.preConcat(this.transformAnimation.getMatrix());
        }
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            Content content = (Content) this.contents.get(i);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).getBounds(this.rect, this.matrix);
                if (outBounds.isEmpty()) {
                    outBounds.set(this.rect);
                } else {
                    outBounds.set(Math.min(outBounds.left, this.rect.left), Math.min(outBounds.top, this.rect.top), Math.max(outBounds.right, this.rect.right), Math.max(outBounds.bottom, this.rect.bottom));
                }
            }
        }
    }
}
