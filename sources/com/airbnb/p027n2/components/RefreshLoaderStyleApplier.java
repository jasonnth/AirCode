package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.RefreshLoaderStyleApplier */
public final class RefreshLoaderStyleApplier extends StyleApplier<RefreshLoaderStyleApplier, RefreshLoader> {
    public RefreshLoaderStyleApplier(RefreshLoader view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_RefreshLoader;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((RefreshLoader) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_RefreshLoader_n2_loaderStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_RefreshLoader_n2_loaderStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((RefreshLoader) getView()).loadingView).apply(subStyle);
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }

    public RefreshLoaderStyleApplier applyInverse() {
        return (RefreshLoaderStyleApplier) apply(R.style.n2_RefreshLoader_Inverse);
    }

    public RefreshLoaderStyleApplier applyCarousel() {
        return (RefreshLoaderStyleApplier) apply(R.style.n2_RefreshLoader_Carousel);
    }
}
