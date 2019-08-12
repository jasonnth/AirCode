package com.devbrackets.android.exomedia.core.video.scale;

import android.graphics.Point;
import android.util.Log;
import android.view.TextureView;
import java.lang.ref.WeakReference;

public class MatrixManager {
    protected int currentRotation = 0;
    protected ScaleType currentScaleType = ScaleType.FIT_CENTER;
    protected Point intrinsicVideoSize = new Point(0, 0);
    protected WeakReference<TextureView> requestedModificationView = new WeakReference<>(null);
    protected Integer requestedRotation = null;
    protected ScaleType requestedScaleType = null;

    public boolean ready() {
        return this.intrinsicVideoSize.x > 0 && this.intrinsicVideoSize.y > 0;
    }

    public void setIntrinsicVideoSize(int width, int height) {
        int i;
        boolean currentWidthHeightSwapped = true;
        if ((this.currentRotation / 90) % 2 != 1) {
            currentWidthHeightSwapped = false;
        }
        Point point = this.intrinsicVideoSize;
        if (currentWidthHeightSwapped) {
            i = height;
        } else {
            i = width;
        }
        point.x = i;
        Point point2 = this.intrinsicVideoSize;
        if (!currentWidthHeightSwapped) {
            width = height;
        }
        point2.y = width;
        if (ready()) {
            applyRequestedModifications();
        }
    }

    public ScaleType getCurrentScaleType() {
        return this.requestedScaleType != null ? this.requestedScaleType : this.currentScaleType;
    }

    public void rotate(TextureView view, int rotation) {
        boolean swapWidthHeight;
        boolean currentWidthHeightSwapped;
        if (!ready()) {
            this.requestedRotation = Integer.valueOf(rotation);
            this.requestedModificationView = new WeakReference<>(view);
            return;
        }
        if ((rotation / 90) % 2 == 1) {
            swapWidthHeight = true;
        } else {
            swapWidthHeight = false;
        }
        if ((this.currentRotation / 90) % 2 == 1) {
            currentWidthHeightSwapped = true;
        } else {
            currentWidthHeightSwapped = false;
        }
        if (swapWidthHeight != currentWidthHeightSwapped) {
            int tempX = this.intrinsicVideoSize.x;
            this.intrinsicVideoSize.x = this.intrinsicVideoSize.y;
            this.intrinsicVideoSize.y = tempX;
            scale(view, this.currentScaleType);
        }
        this.currentRotation = rotation;
        view.setRotation((float) rotation);
    }

    public boolean scale(TextureView view, ScaleType scaleType) {
        if (!ready()) {
            this.requestedScaleType = scaleType;
            this.requestedModificationView = new WeakReference<>(view);
            return false;
        } else if (view.getHeight() == 0 || view.getWidth() == 0) {
            Log.d("MatrixManager", "Unable to apply scale with a view size of (" + view.getWidth() + ", " + view.getHeight() + ")");
            return false;
        } else {
            this.currentScaleType = scaleType;
            switch (scaleType) {
                case CENTER:
                    applyCenter(view);
                    break;
                case CENTER_CROP:
                    applyCenterCrop(view);
                    break;
                case CENTER_INSIDE:
                    applyCenterInside(view);
                    break;
                case FIT_CENTER:
                    applyFitCenter(view);
                    break;
                case NONE:
                    setScale(view, 1.0f, 1.0f);
                    break;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void applyCenter(TextureView view) {
        setScale(view, ((float) this.intrinsicVideoSize.x) / ((float) view.getWidth()), ((float) this.intrinsicVideoSize.y) / ((float) view.getHeight()));
    }

    /* access modifiers changed from: protected */
    public void applyCenterCrop(TextureView view) {
        float xScale = ((float) view.getWidth()) / ((float) this.intrinsicVideoSize.x);
        float yScale = ((float) view.getHeight()) / ((float) this.intrinsicVideoSize.y);
        float scale = Math.max(xScale, yScale);
        setScale(view, scale / xScale, scale / yScale);
    }

    /* access modifiers changed from: protected */
    public void applyCenterInside(TextureView view) {
        if (this.intrinsicVideoSize.x > view.getWidth() || this.intrinsicVideoSize.y > view.getHeight()) {
            applyFitCenter(view);
        } else {
            applyCenter(view);
        }
    }

    /* access modifiers changed from: protected */
    public void applyFitCenter(TextureView view) {
        float xScale = ((float) view.getWidth()) / ((float) this.intrinsicVideoSize.x);
        float yScale = ((float) view.getHeight()) / ((float) this.intrinsicVideoSize.y);
        float scale = Math.min(xScale, yScale);
        setScale(view, scale / xScale, scale / yScale);
    }

    /* access modifiers changed from: protected */
    public void setScale(TextureView view, float xScale, float yScale) {
        boolean currentWidthHeightSwapped = true;
        if ((this.currentRotation / 90) % 2 != 1) {
            currentWidthHeightSwapped = false;
        }
        if (currentWidthHeightSwapped) {
            float scaleTemp = xScale;
            xScale = (((float) view.getHeight()) * yScale) / ((float) view.getWidth());
            yScale = (((float) view.getWidth()) * scaleTemp) / ((float) view.getHeight());
        }
        view.setScaleX(xScale);
        view.setScaleY(yScale);
    }

    /* access modifiers changed from: protected */
    public void applyRequestedModifications() {
        TextureView view = (TextureView) this.requestedModificationView.get();
        if (view != null) {
            if (this.requestedRotation != null) {
                rotate(view, this.requestedRotation.intValue());
                this.requestedRotation = null;
            }
            if (this.requestedScaleType != null) {
                scale(view, this.requestedScaleType);
                this.requestedScaleType = null;
            }
        }
        this.requestedModificationView = new WeakReference<>(null);
    }
}
