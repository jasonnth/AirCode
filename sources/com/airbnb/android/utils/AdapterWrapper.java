package com.airbnb.android.utils;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class AdapterWrapper<TDataModel> extends BaseAdapter {
    private ArrayAdapter<TDataModel> wrapped = null;

    public AdapterWrapper(ArrayAdapter<TDataModel> wrapped2) {
        this.wrapped = wrapped2;
        wrapped2.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                AdapterWrapper.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                AdapterWrapper.this.notifyDataSetInvalidated();
            }
        });
    }

    public Object getItem(int position) {
        return this.wrapped.getItem(position);
    }

    public int getCount() {
        return this.wrapped.getCount();
    }

    public int getViewTypeCount() {
        return this.wrapped.getViewTypeCount();
    }

    public int getItemViewType(int position) {
        return this.wrapped.getItemViewType(position);
    }

    public boolean areAllItemsEnabled() {
        return this.wrapped.areAllItemsEnabled();
    }

    public boolean isEnabled(int position) {
        return this.wrapped.isEnabled(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return this.wrapped.getView(position, convertView, parent);
    }

    public long getItemId(int position) {
        return this.wrapped.getItemId(position);
    }

    /* access modifiers changed from: protected */
    public ArrayAdapter<TDataModel> getWrappedAdapter() {
        return this.wrapped;
    }
}
