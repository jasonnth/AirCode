package com.airbnb.paris;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.internal.AnalyticsEvents;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutParamsStyleApplier.kt */
public final class LayoutParamsStyleApplier extends StyleApplier<LayoutParamsStyleApplier, View> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static int NOT_SET = -10;

    /* compiled from: LayoutParamsStyleApplier.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final int getNOT_SET() {
            return LayoutParamsStyleApplier.NOT_SET;
        }

        public final int ifSetElse(int value, int ifNotSet) {
            return value != getNOT_SET() ? value : ifNotSet;
        }

        public final boolean isAnySet(int... values) {
            Intrinsics.checkParameterIsNotNull(values, "values");
            for (int value : values) {
                if (value != getNOT_SET()) {
                    return true;
                }
            }
            return false;
        }
    }

    /* compiled from: LayoutParamsStyleApplier.kt */
    public enum Option implements com.airbnb.paris.Style.Config.Option {
        IgnoreLayoutWidthAndHeight
    }

    public LayoutParamsStyleApplier(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        int[] iArr = R.styleable.Paris_LayoutParams;
        Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.Paris_LayoutParams");
        return iArr;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        Intrinsics.checkParameterIsNotNull(a, "a");
        boolean ignoreLayoutWidthAndHeight = style.hasOption(Option.IgnoreLayoutWidthAndHeight);
        int width = Companion.getNOT_SET();
        int height = Companion.getNOT_SET();
        int margin = Companion.getNOT_SET();
        int marginBottom = Companion.getNOT_SET();
        int marginLeft = Companion.getNOT_SET();
        int marginRight = Companion.getNOT_SET();
        int marginTop = Companion.getNOT_SET();
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_width) && !ignoreLayoutWidthAndHeight) {
            width = a.getLayoutDimension(R.styleable.Paris_LayoutParams_android_layout_width, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_height) && !ignoreLayoutWidthAndHeight) {
            height = a.getLayoutDimension(R.styleable.Paris_LayoutParams_android_layout_height, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_margin)) {
            margin = a.getDimensionPixelSize(R.styleable.Paris_LayoutParams_android_layout_margin, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_marginBottom)) {
            marginBottom = a.getDimensionPixelSize(R.styleable.Paris_LayoutParams_android_layout_marginBottom, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_marginLeft)) {
            marginLeft = a.getDimensionPixelSize(R.styleable.Paris_LayoutParams_android_layout_marginLeft, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_marginRight)) {
            marginRight = a.getDimensionPixelSize(R.styleable.Paris_LayoutParams_android_layout_marginRight, 0);
        }
        if (a.hasValue(R.styleable.Paris_LayoutParams_android_layout_marginTop)) {
            marginTop = a.getDimensionPixelSize(R.styleable.Paris_LayoutParams_android_layout_marginTop, 0);
        }
        if ((width != Companion.getNOT_SET()) ^ (height != Companion.getNOT_SET())) {
            throw new IllegalArgumentException("Width and height must either both be set, or not be set at all. It can't be one and not the other.");
        }
        boolean isWidthHeightSet = width != Companion.getNOT_SET();
        boolean isMarginSet = Companion.isAnySet(margin, marginBottom, marginLeft, marginRight, marginTop);
        if (isWidthHeightSet) {
            LayoutParams params = getView().getLayoutParams();
            if (params == null) {
                params = isMarginSet ? new MarginLayoutParams(width, height) : new LayoutParams(width, height);
            } else {
                params.width = width;
                params.height = height;
            }
            getView().setLayoutParams(params);
        }
        if (isMarginSet) {
            LayoutParams layoutParams = getView().getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new MarginLayoutParams(-2, -2);
            }
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            MarginLayoutParams marginParams = (MarginLayoutParams) layoutParams;
            if (margin != Companion.getNOT_SET()) {
                marginParams.setMargins(margin, margin, margin, margin);
            }
            marginParams.bottomMargin = Companion.ifSetElse(marginBottom, marginParams.bottomMargin);
            marginParams.leftMargin = Companion.ifSetElse(marginLeft, marginParams.leftMargin);
            marginParams.rightMargin = Companion.ifSetElse(marginRight, marginParams.rightMargin);
            marginParams.topMargin = Companion.ifSetElse(marginTop, marginParams.topMargin);
            getView().setLayoutParams(marginParams);
        }
    }
}
