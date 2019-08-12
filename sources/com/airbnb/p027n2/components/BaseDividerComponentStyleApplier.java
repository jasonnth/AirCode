package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.BaseDividerComponentStyleApplier */
public final class BaseDividerComponentStyleApplier extends StyleApplier<BaseDividerComponentStyleApplier, BaseDividerComponent> {
    public BaseDividerComponentStyleApplier(BaseDividerComponent view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_BaseDividerComponent;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((BaseDividerComponent) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_BaseDividerComponent_n2_showDivider)) {
            ((BaseDividerComponent) getView()).showDivider(a.getBoolean(R.styleable.n2_BaseDividerComponent_n2_showDivider, false));
        }
        if (a.hasValue(R.styleable.n2_BaseDividerComponent_n2_dividerColor)) {
            ((BaseDividerComponent) getView()).setDividerColor(a.getColor(R.styleable.n2_BaseDividerComponent_n2_dividerColor, -1));
        }
        if (a.hasValue(R.styleable.n2_BaseDividerComponent_n2_dividerHeight)) {
            ((BaseDividerComponent) getView()).setDividerHeight(a.getDimensionPixelSize(R.styleable.n2_BaseDividerComponent_n2_dividerHeight, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseComponentStyleApplier((BaseComponent) getView()).apply(style);
    }
}
