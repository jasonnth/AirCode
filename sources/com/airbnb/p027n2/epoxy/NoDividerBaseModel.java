package com.airbnb.p027n2.epoxy;

import android.view.View;

/* renamed from: com.airbnb.n2.epoxy.NoDividerBaseModel */
public abstract class NoDividerBaseModel<T extends View> extends AirEpoxyModel<T> {
    public int getDividerViewType() {
        return -1;
    }
}
