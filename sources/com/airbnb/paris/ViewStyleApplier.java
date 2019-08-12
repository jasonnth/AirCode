package com.airbnb.paris;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Build.VERSION;
import android.view.View;
import com.facebook.internal.AnalyticsEvents;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewStyleApplier.kt */
public final class ViewStyleApplier extends StyleApplier<ViewStyleApplier, View> {
    public ViewStyleApplier(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        int[] iArr = R.styleable.Paris_View;
        Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.Paris_View");
        return iArr;
    }

    /* access modifiers changed from: protected */
    public void applyDependencies(Style style) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        new LayoutParamsStyleApplier(getView()).apply(style);
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        StateListAnimator stateListAnimator;
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        Intrinsics.checkParameterIsNotNull(a, "a");
        if (a.hasValue(R.styleable.Paris_View_android_background)) {
            getView().setBackground(StyleUtils.getDrawable(getView().getContext(), a, R.styleable.Paris_View_android_background));
        }
        if (a.hasValue(R.styleable.Paris_View_android_minWidth)) {
            getView().setMinimumWidth(a.getDimensionPixelSize(R.styleable.Paris_View_android_minWidth, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_padding)) {
            ViewExtensionsKt.setPadding(getView(), a.getDimensionPixelSize(R.styleable.Paris_View_android_padding, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_paddingBottom)) {
            ViewExtensionsKt.setPaddingBottom(getView(), a.getDimensionPixelSize(R.styleable.Paris_View_android_paddingBottom, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_paddingLeft)) {
            ViewExtensionsKt.setPaddingLeft(getView(), a.getDimensionPixelSize(R.styleable.Paris_View_android_paddingLeft, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_paddingRight)) {
            ViewExtensionsKt.setPaddingRight(getView(), a.getDimensionPixelSize(R.styleable.Paris_View_android_paddingRight, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_paddingTop)) {
            ViewExtensionsKt.setPaddingTop(getView(), a.getDimensionPixelSize(R.styleable.Paris_View_android_paddingTop, -1));
        }
        if (a.hasValue(R.styleable.Paris_View_android_stateListAnimator) && VERSION.SDK_INT >= 21) {
            int resourceId = a.getResourceId(R.styleable.Paris_View_android_stateListAnimator, 0);
            View view = getView();
            if (resourceId != 0) {
                stateListAnimator = AnimatorInflater.loadStateListAnimator(getView().getContext(), resourceId);
            } else {
                stateListAnimator = null;
            }
            view.setStateListAnimator(stateListAnimator);
        }
    }
}
