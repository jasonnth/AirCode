package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.util.Base64;
import android.util.SparseArray;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RNSVGSvgViewShadowNode extends LayoutShadowNode {
    private static final Map<String, RNSVGBrush> mDefinedBrushes = new HashMap();
    private static final Map<String, RNSVGVirtualNode> mDefinedClipPaths = new HashMap();
    private static final Map<String, RNSVGVirtualNode> mDefinedTemplates = new HashMap();
    private static final SparseArray<RNSVGSvgViewShadowNode> mTagToShadowNode = new SparseArray<>();
    private boolean mResponsible = false;

    public static RNSVGSvgViewShadowNode getShadowNodeByTag(int tag) {
        return (RNSVGSvgViewShadowNode) mTagToShadowNode.get(tag);
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return true;
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiUpdater) {
        super.onCollectExtraUpdates(uiUpdater);
        uiUpdater.enqueueUpdateExtraData(getReactTag(), drawOutput());
    }

    public void setReactTag(int reactTag) {
        super.setReactTag(reactTag);
        mTagToShadowNode.put(getReactTag(), this);
    }

    public Object drawOutput() {
        Bitmap bitmap = Bitmap.createBitmap((int) getLayoutWidth(), (int) getLayoutHeight(), Config.ARGB_8888);
        drawChildren(new Canvas(bitmap));
        return bitmap;
    }

    private void drawChildren(Canvas canvas) {
        canvas.drawColor(0, Mode.CLEAR);
        Paint paint = new Paint();
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof RNSVGVirtualNode) {
                RNSVGVirtualNode child = (RNSVGVirtualNode) getChildAt(i);
                child.setupDimensions(canvas);
                child.saveDefinition();
                child.draw(canvas, paint, 1.0f);
                child.markUpdateSeen();
                if (child.isResponsible() && !this.mResponsible) {
                    this.mResponsible = true;
                }
            }
        }
    }

    public String getBase64() {
        Bitmap bitmap = Bitmap.createBitmap((int) getLayoutWidth(), (int) getLayoutHeight(), Config.ARGB_8888);
        drawChildren(new Canvas(bitmap));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, stream);
        bitmap.recycle();
        return Base64.encodeToString(stream.toByteArray(), 0);
    }

    public void enableTouchEvents() {
        if (!this.mResponsible) {
            this.mResponsible = true;
        }
    }

    public int hitTest(Point point) {
        if (!this.mResponsible) {
            return -1;
        }
        int viewTag = -1;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            if (getChildAt(i) instanceof RNSVGVirtualNode) {
                viewTag = ((RNSVGVirtualNode) getChildAt(i)).hitTest(point);
                if (viewTag != -1) {
                    return viewTag;
                }
            }
        }
        return viewTag;
    }

    public void defineClipPath(RNSVGVirtualNode clipPath, String clipPathRef) {
        mDefinedClipPaths.put(clipPathRef, clipPath);
    }

    public RNSVGVirtualNode getDefinedClipPath(String clipPathRef) {
        return (RNSVGVirtualNode) mDefinedClipPaths.get(clipPathRef);
    }

    public void defineTemplate(RNSVGVirtualNode template, String templateRef) {
        mDefinedTemplates.put(templateRef, template);
    }

    public RNSVGVirtualNode getDefinedTemplate(String templateRef) {
        return (RNSVGVirtualNode) mDefinedTemplates.get(templateRef);
    }

    public void defineBrush(RNSVGBrush brush, String brushRef) {
        mDefinedBrushes.put(brushRef, brush);
    }

    public RNSVGBrush getDefinedBrush(String brushRef) {
        return (RNSVGBrush) mDefinedBrushes.get(brushRef);
    }

    public void finalize() {
        mTagToShadowNode.remove(getReactTag());
    }
}
