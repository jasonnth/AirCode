package com.airbnb.android.core.interfaces;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

public interface AirAbsListView {
    void addFooterView(View view);

    void addHeaderView(View view);

    AbsListView getAbsListView();

    ListAdapter getAdapter();

    int getFooterViewsCount();

    int getHeaderViewsCount();

    boolean removeFooterView(View view);

    boolean removeHeaderView(View view);

    void setAdapter(ListAdapter listAdapter);

    void setOnItemClickListener(OnItemClickListener onItemClickListener);
}
