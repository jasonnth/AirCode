package com.airbnb.epoxy;

import android.view.View;
import com.airbnb.epoxy.EpoxyModel;

public interface OnModelLongClickListener<T extends EpoxyModel<?>, V> {
    boolean onLongClick(T t, V v, View view, int i);
}
