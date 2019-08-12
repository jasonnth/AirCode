package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class BaseLayer implements AnimationListener, DrawingContent {
    private final List<BaseKeyframeAnimation<?, ?>> animations = new ArrayList();
    final Matrix boundsMatrix = new Matrix();
    private final Paint clearPaint = new Paint();
    private final Paint contentPaint = new Paint(1);
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect = new RectF();
    private final Paint maskPaint = new Paint(1);
    private final Matrix matrix = new Matrix();
    private final RectF matteBoundsRect = new RectF();
    private BaseLayer matteLayer;
    private final Paint mattePaint = new Paint(1);
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    private final Path path = new Path();
    private final RectF rect = new RectF();
    private final RectF tempMaskBoundsRect = new RectF();
    final TransformKeyframeAnimation transform;
    private boolean visible = true;

    /* access modifiers changed from: 0000 */
    public abstract void drawLayer(Canvas canvas, Matrix matrix2, int i);

    static BaseLayer forModel(Layer layerModel2, LottieDrawable drawable, LottieComposition composition) {
        switch (layerModel2.getLayerType()) {
            case Shape:
                return new ShapeLayer(drawable, layerModel2);
            case PreComp:
                return new CompositionLayer(drawable, layerModel2, composition.getPrecomps(layerModel2.getRefId()), composition);
            case Solid:
                return new SolidLayer(drawable, layerModel2);
            case Image:
                return new ImageLayer(drawable, layerModel2, composition.getDpScale());
            case Null:
                return new NullLayer(drawable, layerModel2);
            default:
                Log.w("LOTTIE", "Unknown layer type " + layerModel2.getLayerType());
                return null;
        }
    }

    BaseLayer(LottieDrawable lottieDrawable2, Layer layerModel2) {
        this.lottieDrawable = lottieDrawable2;
        this.layerModel = layerModel2;
        this.clearPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.maskPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        if (layerModel2.getMatteType() == MatteType.Invert) {
            this.mattePaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        } else {
            this.mattePaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        }
        this.transform = layerModel2.getTransform().createAnimation();
        this.transform.addListener(this);
        this.transform.addAnimationsToLayer(this);
        if (layerModel2.getMasks() != null && !layerModel2.getMasks().isEmpty()) {
            this.mask = new MaskKeyframeAnimation(layerModel2.getMasks());
            for (BaseKeyframeAnimation<?, Path> animation : this.mask.getMaskAnimations()) {
                addAnimation(animation);
                animation.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    public void onValueChanged() {
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    /* access modifiers changed from: 0000 */
    public void setMatteLayer(BaseLayer matteLayer2) {
        this.matteLayer = matteLayer2;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    /* access modifiers changed from: 0000 */
    public void setParentLayer(BaseLayer parentLayer2) {
        this.parentLayer = parentLayer2;
    }

    private void setupInOutAnimations() {
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            final FloatKeyframeAnimation inOutAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            inOutAnimation.setIsDiscrete();
            inOutAnimation.addUpdateListener(new AnimationListener() {
                public void onValueChanged() {
                    BaseLayer.this.setVisible(((Float) inOutAnimation.getValue()).floatValue() == 1.0f);
                }
            });
            setVisible(((Float) inOutAnimation.getValue()).floatValue() == 1.0f);
            addAnimation(inOutAnimation);
            return;
        }
        setVisible(true);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public void addAnimation(BaseKeyframeAnimation<?, ?> newAnimation) {
        if (!(newAnimation instanceof StaticKeyframeAnimation)) {
            this.animations.add(newAnimation);
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        this.boundsMatrix.set(parentMatrix);
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        if (this.visible) {
            buildParentLayerListIfNeeded();
            this.matrix.reset();
            this.matrix.set(parentMatrix);
            for (int i = this.parentLayers.size() - 1; i >= 0; i--) {
                this.matrix.preConcat(((BaseLayer) this.parentLayers.get(i)).transform.getMatrix());
            }
            int alpha = (int) (((((float) ((Integer) this.transform.getOpacity().getValue()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f);
            if (hasMatteOnThisLayer() || hasMasksOnThisLayer()) {
                this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
                getBounds(this.rect, this.matrix);
                intersectBoundsWithMatte(this.rect, this.matrix);
                this.matrix.preConcat(this.transform.getMatrix());
                intersectBoundsWithMask(this.rect, this.matrix);
                this.rect.set(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
                canvas.saveLayer(this.rect, this.contentPaint, 31);
                clearCanvas(canvas);
                drawLayer(canvas, this.matrix, alpha);
                if (hasMasksOnThisLayer()) {
                    applyMasks(canvas, this.matrix);
                }
                if (hasMatteOnThisLayer()) {
                    canvas.saveLayer(this.rect, this.mattePaint, 19);
                    clearCanvas(canvas);
                    this.matteLayer.draw(canvas, parentMatrix, alpha);
                    canvas.restore();
                }
                canvas.restore();
                return;
            }
            this.matrix.preConcat(this.transform.getMatrix());
            drawLayer(canvas, this.matrix, alpha);
        }
    }

    private void clearCanvas(Canvas canvas) {
        canvas.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, 1.0f + this.rect.bottom, this.clearPaint);
    }

    private void intersectBoundsWithMask(RectF rect2, Matrix matrix2) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            int i = 0;
            while (i < size) {
                Mask mask2 = (Mask) this.mask.getMasks().get(i);
                this.path.set((Path) ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).getValue());
                this.path.transform(matrix2);
                switch (mask2.getMaskMode()) {
                    case MaskModeSubtract:
                        return;
                    default:
                        this.path.computeBounds(this.tempMaskBoundsRect, false);
                        if (i == 0) {
                            this.maskBoundsRect.set(this.tempMaskBoundsRect);
                        } else {
                            this.maskBoundsRect.set(Math.min(this.maskBoundsRect.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                        }
                        i++;
                }
            }
            rect2.set(Math.max(rect2.left, this.maskBoundsRect.left), Math.max(rect2.top, this.maskBoundsRect.top), Math.min(rect2.right, this.maskBoundsRect.right), Math.min(rect2.bottom, this.maskBoundsRect.bottom));
        }
    }

    private void intersectBoundsWithMatte(RectF rect2, Matrix matrix2) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != MatteType.Invert) {
            this.matteLayer.getBounds(this.matteBoundsRect, matrix2);
            rect2.set(Math.max(rect2.left, this.matteBoundsRect.left), Math.max(rect2.top, this.matteBoundsRect.top), Math.min(rect2.right, this.matteBoundsRect.right), Math.min(rect2.bottom, this.matteBoundsRect.bottom));
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix2) {
        canvas.saveLayer(this.rect, this.maskPaint, 19);
        clearCanvas(canvas);
        int size = this.mask.getMasks().size();
        for (int i = 0; i < size; i++) {
            Mask mask2 = (Mask) this.mask.getMasks().get(i);
            this.path.set((Path) ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).getValue());
            this.path.transform(matrix2);
            switch (mask2.getMaskMode()) {
                case MaskModeSubtract:
                    this.path.setFillType(FillType.INVERSE_WINDING);
                    break;
                default:
                    this.path.setFillType(FillType.WINDING);
                    break;
            }
            canvas.drawPath(this.path, this.contentPaint);
        }
        canvas.restore();
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMasksOnThisLayer() {
        return this.mask != null && !this.mask.getMaskAnimations().isEmpty();
    }

    /* access modifiers changed from: private */
    public void setVisible(boolean visible2) {
        if (visible2 != this.visible) {
            this.visible = visible2;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setProgress(float progress) {
        if (this.matteLayer != null) {
            this.matteLayer.setProgress(progress);
        }
        for (int i = 0; i < this.animations.size(); i++) {
            ((BaseKeyframeAnimation) this.animations.get(i)).setProgress(progress);
        }
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers == null) {
            if (this.parentLayer == null) {
                this.parentLayers = Collections.emptyList();
                return;
            }
            this.parentLayers = new ArrayList();
            for (BaseLayer layer = this.parentLayer; layer != null; layer = layer.parentLayer) {
                this.parentLayers.add(layer);
            }
        }
    }

    public String getName() {
        return this.layerModel.getName();
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
    }
}
