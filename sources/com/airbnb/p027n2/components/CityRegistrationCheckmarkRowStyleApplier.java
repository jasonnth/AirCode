package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.CityRegistrationCheckmarkRowStyleApplier */
public final class CityRegistrationCheckmarkRowStyleApplier extends StyleApplier<CityRegistrationCheckmarkRowStyleApplier, CityRegistrationCheckmarkRow> {
    public CityRegistrationCheckmarkRowStyleApplier(CityRegistrationCheckmarkRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_CityRegistrationCheckmarkRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources res = ((CityRegistrationCheckmarkRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_CityRegistrationCheckmarkRow_n2_titleText)) {
            ((CityRegistrationCheckmarkRow) getView()).setTitle(a.getText(R.styleable.n2_CityRegistrationCheckmarkRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_CityRegistrationCheckmarkRow_n2_subtitleText)) {
            ((CityRegistrationCheckmarkRow) getView()).setSubtitle(a.getText(R.styleable.n2_CityRegistrationCheckmarkRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_CityRegistrationCheckmarkRow_n2_image)) {
            ((CityRegistrationCheckmarkRow) getView()).setIcon(a.getDrawable(R.styleable.n2_CityRegistrationCheckmarkRow_n2_image));
        } else {
            ((CityRegistrationCheckmarkRow) getView()).setIcon(res.getDrawable(R.drawable.n2_icon_circle_checkmark_babu));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
