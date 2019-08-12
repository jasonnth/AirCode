package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGUseShadowNode extends RNSVGPathShadowNode {
    private String mHeight;
    private String mHref;
    private String mWidth;

    @ReactProp(name = "href")
    public void setHref(String href) {
        this.mHref = href;
        markUpdated();
    }

    @ReactProp(name = "width")
    public void setWidth(String width) {
        this.mWidth = width;
        markUpdated();
    }

    @ReactProp(name = "height")
    public void setHeight(String height) {
        this.mHeight = height;
        markUpdated();
    }

    public String getWidth() {
        return this.mWidth;
    }

    public String getHeight() {
        return this.mHeight;
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        RNSVGVirtualNode template = getSvgShadowNode().getDefinedTemplate(this.mHref);
        if (template != null) {
            int count = saveAndSetupCanvas(canvas);
            clip(canvas, paint);
            template.mergeProperties(this, this.mOwnedPropList);
            template.draw(canvas, paint, this.mOpacity * opacity);
            template.resetProperties();
            restoreCanvas(canvas, count);
            markUpdateSeen();
            return;
        }
        FLog.m1847w(ReactConstants.TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
    }
}
