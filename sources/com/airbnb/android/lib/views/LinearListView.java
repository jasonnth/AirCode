package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.airbnb.android.lib.C0880R;

public class LinearListView extends IcsLinearLayout {
    private static final int LinearListView_dividerThickness = 1;
    private static final int LinearListView_entries = 0;
    private static final int[] R_styleable_LinearListView = {16842930, C0880R.attr.dividerThickness};
    /* access modifiers changed from: private */
    public ListAdapter mAdapter;
    private boolean mAreAllItemsSelectable;
    private final DataSetObserver mDataObserver;
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;

    private class InternalOnClickListener implements OnClickListener {
        final int mPosition;

        public InternalOnClickListener(int position) {
            this.mPosition = position;
        }

        public void onClick(View v) {
            if (LinearListView.this.mOnItemClickListener != null && LinearListView.this.mAdapter != null) {
                LinearListView.this.mOnItemClickListener.onItemClick(LinearListView.this, v, this.mPosition, LinearListView.this.mAdapter.getItemId(this.mPosition));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LinearListView linearListView, View view, int i, long j);
    }

    public LinearListView(Context context) {
        this(context, null);
    }

    public LinearListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDataObserver = new DataSetObserver() {
            public void onChanged() {
                LinearListView.this.setupChildren();
            }

            public void onInvalidated() {
                LinearListView.this.setupChildren();
            }
        };
        TypedArray a = context.obtainStyledAttributes(attrs, R_styleable_LinearListView);
        int thickness = a.getDimensionPixelSize(1, 0);
        if (thickness != 0) {
            setDividerThickness(thickness);
        }
        CharSequence[] entries = a.getTextArray(0);
        if (entries != null) {
            setAdapter(new ArrayAdapter(context, 17367043, entries));
        }
        a.recycle();
    }

    public void setOrientation(int orientation) {
        if (orientation != getOrientation()) {
            int tmp = this.mDividerHeight;
            this.mDividerHeight = this.mDividerWidth;
            this.mDividerWidth = tmp;
        }
        super.setOrientation(orientation);
    }

    public void setDividerThickness(int thickness) {
        if (getOrientation() == 1) {
            this.mDividerHeight = thickness;
        } else {
            this.mDividerWidth = thickness;
        }
        requestLayout();
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(ListAdapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataObserver);
        }
        this.mAdapter = adapter;
        if (this.mAdapter != null) {
            this.mAdapter.registerDataSetObserver(this.mDataObserver);
            this.mAreAllItemsSelectable = this.mAdapter.areAllItemsEnabled();
        }
        setupChildren();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private void updateEmptyStatus(boolean empty) {
        if (empty) {
            setVisibility(0);
        } else {
            setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void setupChildren() {
        removeAllViews();
        updateEmptyStatus(this.mAdapter == null || this.mAdapter.isEmpty());
        if (this.mAdapter != null) {
            for (int i = 0; i < this.mAdapter.getCount(); i++) {
                View child = this.mAdapter.getView(i, null, this);
                if (this.mAreAllItemsSelectable || this.mAdapter.isEnabled(i)) {
                    child.setOnClickListener(new InternalOnClickListener(i));
                }
                addViewInLayout(child, -1, child.getLayoutParams(), true);
            }
        }
    }
}
