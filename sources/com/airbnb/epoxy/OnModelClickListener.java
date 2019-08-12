package com.airbnb.epoxy;

import android.view.View;
import com.airbnb.epoxy.EpoxyModel;

public interface OnModelClickListener<T extends EpoxyModel<?>, V> {
    void onClick(T t, V v, View view, int i);
}
