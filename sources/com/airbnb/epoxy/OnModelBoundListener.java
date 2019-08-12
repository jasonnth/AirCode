package com.airbnb.epoxy;

import com.airbnb.epoxy.EpoxyModel;

public interface OnModelBoundListener<T extends EpoxyModel<?>, V> {
    void onModelBound(T t, V v, int i);
}
