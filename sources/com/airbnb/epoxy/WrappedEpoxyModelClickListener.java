package com.airbnb.epoxy;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.epoxy.EpoxyModel;

public class WrappedEpoxyModelClickListener<T extends EpoxyModel<?>, V> implements OnClickListener, OnLongClickListener {
    private EpoxyViewHolder holder;
    private final T model;
    private V object;
    private final OnModelClickListener<T, V> originalClickListener;
    private final OnModelLongClickListener<T, V> originalLongClickListener;

    public WrappedEpoxyModelClickListener(T model2, OnModelClickListener<T, V> clickListener) {
        if (clickListener == null) {
            throw new IllegalArgumentException("Click listener cannot be null");
        }
        this.model = model2;
        this.originalClickListener = clickListener;
        this.originalLongClickListener = null;
    }

    public WrappedEpoxyModelClickListener(T model2, OnModelLongClickListener<T, V> clickListener) {
        if (clickListener == null) {
            throw new IllegalArgumentException("Click listener cannot be null");
        }
        this.model = model2;
        this.originalLongClickListener = clickListener;
        this.originalClickListener = null;
    }

    public void bind(EpoxyViewHolder holder2, V object2) {
        this.holder = holder2;
        this.object = object2;
    }

    public void onClick(View v) {
        if (this.holder == null) {
            throw new IllegalStateException("Holder was not bound");
        } else if (this.object == null) {
            throw new IllegalStateException("Object was not bound");
        } else if (this.originalClickListener == null) {
            throw new IllegalStateException("Long click listener was set.");
        } else {
            this.originalClickListener.onClick(this.model, this.object, v, this.holder.getAdapterPosition());
        }
    }

    public boolean onLongClick(View v) {
        if (this.holder == null) {
            throw new IllegalStateException("Holder was not bound");
        } else if (this.object == null) {
            throw new IllegalStateException("Object was not bound");
        } else if (this.originalLongClickListener != null) {
            return this.originalLongClickListener.onLongClick(this.model, this.object, v, this.holder.getAdapterPosition());
        } else {
            throw new IllegalStateException("Normal click listener was set.");
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WrappedEpoxyModelClickListener)) {
            return false;
        }
        WrappedEpoxyModelClickListener<?, ?> that = (WrappedEpoxyModelClickListener) o;
        if (this.originalClickListener == null ? that.originalClickListener != null : !this.originalClickListener.equals(that.originalClickListener)) {
            return false;
        }
        if (this.originalLongClickListener != null) {
            return this.originalLongClickListener.equals(that.originalLongClickListener);
        }
        if (that.originalLongClickListener != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.originalClickListener != null) {
            result = this.originalClickListener.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.originalLongClickListener != null) {
            i = this.originalLongClickListener.hashCode();
        }
        return i2 + i;
    }
}
