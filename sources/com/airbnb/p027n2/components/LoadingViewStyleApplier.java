package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.LoadingViewStyleApplier */
public final class LoadingViewStyleApplier extends StyleApplier<LoadingViewStyleApplier, LoadingView> {
    public LoadingViewStyleApplier(LoadingView view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_LoadingView;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources res = ((LoadingView) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_LoadingView_n2_color)) {
            ((LoadingView) getView()).setColor(a.getColor(R.styleable.n2_LoadingView_n2_color, -1));
        } else {
            ((LoadingView) getView()).setColor(res.getColor(R.color.n2_babu));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }
}
