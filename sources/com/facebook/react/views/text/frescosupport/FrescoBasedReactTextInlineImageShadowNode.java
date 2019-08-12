package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.TextInlineImageSpan;
import java.util.Locale;

public class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private float mHeight = Float.NaN;
    private Uri mUri;
    private float mWidth = Float.NaN;

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder draweeControllerBuilder, Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    @ReactProp(name = "src")
    public void setSource(ReadableArray sources) {
        String source = (sources == null || sources.size() == 0) ? null : sources.getMap(0).getString("uri");
        Uri uri = null;
        if (source != null) {
            try {
                uri = Uri.parse(source);
                if (uri.getScheme() == null) {
                    uri = null;
                }
            } catch (Exception e) {
            }
            if (uri == null) {
                uri = getResourceDrawableUri(getThemedContext(), source);
            }
        }
        if (uri != this.mUri) {
            markUpdated();
        }
        this.mUri = uri;
    }

    public void setWidth(float width) {
        this.mWidth = width;
    }

    public void setHeight(float height) {
        this.mHeight = height;
    }

    public Uri getUri() {
        return this.mUri;
    }

    private static Uri getResourceDrawableUri(Context context, String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(name.toLowerCase(Locale.getDefault()).replace("-", "_"), "drawable", context.getPackageName()))).build();
    }

    public boolean isVirtual() {
        return true;
    }

    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(getThemedContext().getResources(), (int) Math.ceil((double) this.mWidth), (int) Math.ceil((double) this.mHeight), getUri(), getDraweeControllerBuilder(), getCallerContext());
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        return this.mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }
}
