package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.BaseComponentStyleApplier */
public final class BaseComponentStyleApplier extends StyleApplier<BaseComponentStyleApplier, BaseComponent> {
    public BaseComponentStyleApplier(BaseComponent view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_BaseComponent;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((BaseComponent) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_BaseComponent_n2_paddingVertical)) {
            ((BaseComponent) getView()).setPaddingVertical(a.getDimensionPixelSize(R.styleable.n2_BaseComponent_n2_paddingVertical, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }
}
