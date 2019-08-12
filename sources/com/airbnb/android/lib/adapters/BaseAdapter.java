package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.ButterKnife;
import java.util.List;

public abstract class BaseAdapter<T extends ViewHolder> extends Adapter<T> {
    protected final Context context;
    protected final boolean tabletUI;

    public static abstract class Row {
        public abstract int getViewType();

        public int getHeaderId() {
            return -1;
        }
    }

    public static abstract class RowViewHolder extends ViewHolder {
        public abstract void bind(List<Row> list, int i);

        public RowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BaseAdapter(Context context2, boolean tabletUI2) {
        this.context = context2;
        this.tabletUI = tabletUI2;
    }
}
