package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.p000v4.util.LongSparseArray;
import java.util.ArrayList;
import java.util.List;

class CompositionLayer extends BaseLayer {
    private final List<BaseLayer> layers = new ArrayList();
    private final RectF rect = new RectF();

    CompositionLayer(LottieDrawable lottieDrawable, Layer layerModel, List<Layer> layerModels, LottieComposition composition) {
        Layer lm;
        super(lottieDrawable, layerModel);
        LongSparseArray<BaseLayer> layerMap = new LongSparseArray<>(composition.getLayers().size());
        BaseLayer mattedLayer = null;
        for (int i = layerModels.size() - 1; i >= 0; i--) {
            BaseLayer layer = BaseLayer.forModel((Layer) layerModels.get(i), lottieDrawable, composition);
            if (layer != null) {
                layerMap.put(layer.getLayerModel().getId(), layer);
                if (mattedLayer == null) {
                    this.layers.add(0, layer);
                    switch (lm.getMatteType()) {
                        case Add:
                        case Invert:
                            mattedLayer = layer;
                            break;
                    }
                } else {
                    mattedLayer.setMatteLayer(layer);
                    mattedLayer = null;
                }
            }
        }
        for (int i2 = 0; i2 < layerMap.size(); i2++) {
            BaseLayer layerView = (BaseLayer) layerMap.get(layerMap.keyAt(i2));
            BaseLayer parentLayer = (BaseLayer) layerMap.get(layerView.getLayerModel().getParentId());
            if (parentLayer != null) {
                layerView.setParentLayer(parentLayer);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            ((BaseLayer) this.layers.get(i)).draw(canvas, parentMatrix, parentAlpha);
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        super.getBounds(outBounds, parentMatrix);
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            ((BaseLayer) this.layers.get(i)).getBounds(this.rect, this.boundsMatrix);
            if (outBounds.isEmpty()) {
                outBounds.set(this.rect);
            } else {
                outBounds.set(Math.min(outBounds.left, this.rect.left), Math.min(outBounds.top, this.rect.top), Math.max(outBounds.right, this.rect.right), Math.max(outBounds.bottom, this.rect.bottom));
            }
        }
    }

    public void setProgress(float progress) {
        super.setProgress(progress);
        float progress2 = progress - this.layerModel.getStartProgress();
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            ((BaseLayer) this.layers.get(i)).setProgress(progress2);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMasks() {
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            BaseLayer layer = (BaseLayer) this.layers.get(i);
            if ((layer instanceof ShapeLayer) && layer.hasMasksOnThisLayer()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasMatte() {
        if (hasMatteOnThisLayer()) {
            return true;
        }
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            if (((BaseLayer) this.layers.get(i)).hasMatteOnThisLayer()) {
                return true;
            }
        }
        return false;
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        for (int i = 0; i < this.layers.size(); i++) {
            BaseLayer layer = (BaseLayer) this.layers.get(i);
            String name = layer.getLayerModel().getName();
            if (layerName == null) {
                layer.addColorFilter(null, null, colorFilter);
            } else if (name.equals(layerName)) {
                layer.addColorFilter(layerName, contentName, colorFilter);
            }
        }
    }
}
