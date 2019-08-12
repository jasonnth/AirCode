package com.airbnb.paris;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.paris.Style.Config;
import com.airbnb.paris.Style.Config.Option;
import com.airbnb.paris.Style.DebugListener;
import com.airbnb.paris.StyleApplier;
import com.facebook.internal.AnalyticsEvents;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StyleApplier.kt */
public abstract class StyleApplier<S extends StyleApplier<? extends S, ? extends T>, T extends View> {
    private Config config = Config.Companion.builder().build();
    private final T view;

    public StyleApplier(T view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        this.view = view2;
    }

    public final T getView() {
        return this.view;
    }

    public final S addOption(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "option");
        this.config = this.config.toBuilder().addOption(option).build();
        if (this != null) {
            return this;
        }
        throw new TypeCastException("null cannot be cast to non-null type S");
    }

    public final S apply(AttributeSet attributeSet) {
        if (attributeSet != null) {
            apply(new Style(attributeSet, this.config));
        }
        if (this != null) {
            return this;
        }
        throw new TypeCastException("null cannot be cast to non-null type S");
    }

    public final S apply(int styleRes) {
        return apply(new Style(styleRes, this.config));
    }

    public S apply(Style style) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        if (style.getAttributeSet() == null) {
            applyParent(style);
        }
        applyDependencies(style);
        int[] attributes = attributes();
        if (attributes != null) {
            Context context = this.view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
            TypedArrayWrapper typedArray = style.obtainStyledAttributes(context, attributes);
            DebugListener debugListener = style.getDebugListener();
            if (debugListener != null) {
                debugListener.beforeTypedArrayProcessed(style, typedArray);
            }
            if (typedArray != null) {
                processAttributes(style, typedArray);
                typedArray.recycle();
            }
        }
        if (this != null) {
            return this;
        }
        throw new TypeCastException("null cannot be cast to non-null type S");
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
    }

    /* access modifiers changed from: protected */
    public void applyDependencies(Style style) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        Intrinsics.checkParameterIsNotNull(a, "a");
    }
}
