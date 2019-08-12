package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.InfiniteDotIndicatorStyleApplier */
public final class InfiniteDotIndicatorStyleApplier extends StyleApplier<InfiniteDotIndicatorStyleApplier, InfiniteDotIndicator> {
    public InfiniteDotIndicatorStyleApplier(InfiniteDotIndicator view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_InfiniteDotIndicator;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources res = ((InfiniteDotIndicator) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_InfiniteDotIndicator_n2_activeCircleColor)) {
            ((InfiniteDotIndicator) getView()).setActiveDotColor(a.getColor(R.styleable.n2_InfiniteDotIndicator_n2_activeCircleColor, -1));
        } else {
            ((InfiniteDotIndicator) getView()).setActiveDotColor(res.getColor(R.color.n2_babu));
        }
        if (a.hasValue(R.styleable.n2_InfiniteDotIndicator_n2_inactiveCircleColor)) {
            ((InfiniteDotIndicator) getView()).setInactiveDotColor(a.getColor(R.styleable.n2_InfiniteDotIndicator_n2_inactiveCircleColor, -1));
        } else {
            ((InfiniteDotIndicator) getView()).setInactiveDotColor(res.getColor(R.color.n2_hof_40));
        }
        if (a.hasValue(R.styleable.n2_InfiniteDotIndicator_n2_numCirclesShown)) {
            ((InfiniteDotIndicator) getView()).setLargeDotCount(a.getInt(R.styleable.n2_InfiniteDotIndicator_n2_numCirclesShown, -1));
        } else {
            ((InfiniteDotIndicator) getView()).setLargeDotCount(res.getInteger(R.integer.n2_infinite_dot_indicator_default_dot_count));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }
}
