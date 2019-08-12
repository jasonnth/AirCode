package com.airbnb.android.lib.adapters.viewholders;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class BindableViewHolder<T> extends ViewHolder {
    protected Context context;

    public abstract void bind(T t);

    public BindableViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();
    }

    public BindableViewHolder(ViewGroup parent, int layoutId) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        ButterKnife.bind(this, this.itemView);
        this.context = this.itemView.getContext();
    }

    public void recycle() {
    }
}
