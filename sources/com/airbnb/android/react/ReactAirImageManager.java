package com.airbnb.android.react;

import android.graphics.PorterDuff.Mode;
import android.widget.ImageView.ScaleType;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

class ReactAirImageManager extends SimpleViewManager<ReactAirImageView> {
    private static final String REACT_CLASS = "RCTImageView";

    ReactAirImageManager() {
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactAirImageView createViewInstance(ThemedReactContext context) {
        return new ReactAirImageView(context, null);
    }

    @ReactProp(name = "src")
    public void setSource(ReactAirImageView view, ReadableArray sources) {
        view.setSource(sources);
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReactAirImageView view, ReadableMap headers) {
        view.setHeaders(headers);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactAirImageView view, String source) {
        view.setLoadingIndicatorSource(source);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "borderColor")
    public void setBorderColor(ReactAirImageView view, int borderColor) {
        view.setBorderColor(-65536);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "overlayColor")
    public void setOverlayColor(ReactAirImageView view, int overlayColor) {
        view.setOverlayColor(overlayColor);
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ReactAirImageView view, float borderWidth) {
        view.setBorderWidth(borderWidth);
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ReactAirImageView view, float borderRadius) {
        view.setBorderRadius(borderRadius);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactAirImageView view, String resizeMode) {
        view.setScaleType(toScaleType(resizeMode));
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ReactAirImageView view, Integer tintColor) {
        if (tintColor == null) {
            view.clearColorFilter();
        } else {
            view.setColorFilter(tintColor.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactAirImageView view, boolean enabled) {
        view.setProgressiveRenderingEnabled(enabled);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactAirImageView view, int durationMs) {
        view.setFadeDuration(durationMs);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactAirImageView view, boolean shouldNotifyLoadEvents) {
        view.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactAirImageView view) {
        super.onAfterUpdateTransaction(view);
        view.maybeUpdateView();
    }

    public static ScaleType toScaleType(String resizeModeValue) {
        if ("contain".equals(resizeModeValue)) {
            return ScaleType.FIT_CENTER;
        }
        if (PlaceFields.COVER.equals(resizeModeValue)) {
            return ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(resizeModeValue)) {
            return ScaleType.FIT_XY;
        }
        if ("center".equals(resizeModeValue)) {
            return ScaleType.CENTER_INSIDE;
        }
        if (resizeModeValue == null) {
            return ScaleType.CENTER_CROP;
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + resizeModeValue + "'");
    }
}
