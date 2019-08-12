package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.CityRegistrationIconActionRowStyleApplier */
public final class CityRegistrationIconActionRowStyleApplier extends StyleApplier<CityRegistrationIconActionRowStyleApplier, CityRegistrationIconActionRow> {
    public CityRegistrationIconActionRowStyleApplier(CityRegistrationIconActionRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_CityRegistrationIconActionRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((CityRegistrationIconActionRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_CityRegistrationIconActionRow_n2_titleText)) {
            ((CityRegistrationIconActionRow) getView()).setTitle(a.getText(R.styleable.n2_CityRegistrationIconActionRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_CityRegistrationIconActionRow_n2_subtitleText)) {
            ((CityRegistrationIconActionRow) getView()).setSubtitle(a.getText(R.styleable.n2_CityRegistrationIconActionRow_n2_subtitleText));
        }
        if (a.hasValue(R.styleable.n2_CityRegistrationIconActionRow_n2_actionText)) {
            ((CityRegistrationIconActionRow) getView()).setAction(a.getText(R.styleable.n2_CityRegistrationIconActionRow_n2_actionText));
        }
        if (a.hasValue(R.styleable.n2_CityRegistrationIconActionRow_n2_image)) {
            ((CityRegistrationIconActionRow) getView()).setIconRes(a.getResourceId(R.styleable.n2_CityRegistrationIconActionRow_n2_image, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
