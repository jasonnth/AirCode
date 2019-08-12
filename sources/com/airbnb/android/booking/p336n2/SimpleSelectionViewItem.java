package com.airbnb.android.booking.p336n2;

import android.content.Context;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

/* renamed from: com.airbnb.android.booking.n2.SimpleSelectionViewItem */
public class SimpleSelectionViewItem<T> implements SelectionViewItemPresenter {
    private final T data;
    private final String displayText;

    public SimpleSelectionViewItem(String displayText2, T data2) {
        this.displayText = displayText2;
        this.data = data2;
    }

    public String getDisplayText(Context context) {
        return this.displayText;
    }

    public Object getData() {
        return this.data;
    }
}
