package com.airbnb.epoxy;

import android.view.View;
import android.view.View.OnClickListener;

public class SimpleEpoxyModel extends EpoxyModel<View> {
    private final int layoutRes;
    private OnClickListener onClickListener;
    private int spanCount = 1;

    public SimpleEpoxyModel(int layoutRes2) {
        this.layoutRes = layoutRes2;
    }

    public SimpleEpoxyModel onClick(OnClickListener listener) {
        this.onClickListener = listener;
        return this;
    }

    public SimpleEpoxyModel span(int span) {
        this.spanCount = span;
        return this;
    }

    public void bind(View view) {
        super.bind(view);
        view.setOnClickListener(this.onClickListener);
        view.setClickable(this.onClickListener != null);
    }

    public void unbind(View view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return this.layoutRes;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return this.spanCount;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleEpoxyModel) || !super.equals(o)) {
            return false;
        }
        SimpleEpoxyModel that = (SimpleEpoxyModel) o;
        if (this.layoutRes != that.layoutRes || this.spanCount != that.spanCount) {
            return false;
        }
        if (this.onClickListener != null) {
            z = this.onClickListener.equals(that.onClickListener);
        } else if (that.onClickListener != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((super.hashCode() * 31) + this.layoutRes) * 31) + (this.onClickListener != null ? this.onClickListener.hashCode() : 0)) * 31) + this.spanCount;
    }
}
