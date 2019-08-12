package com.airbnb.p027n2.epoxy;

import android.view.View;
import com.airbnb.p027n2.interfaces.DividerView;

/* renamed from: com.airbnb.n2.epoxy.DefaultDividerBaseModel */
public abstract class DefaultDividerBaseModel<T extends View & DividerView> extends AirEpoxyModel<T> {
    public int getDividerViewType() {
        return 0;
    }
}
