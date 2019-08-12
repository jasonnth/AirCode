package com.facebook.react.views.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import com.facebook.react.common.annotations.VisibleForTesting;

public class ReactPicker extends Spinner {
    private int mMode = 0;
    /* access modifiers changed from: private */
    public OnSelectListener mOnSelectListener;
    private Integer mPrimaryColor;
    private Integer mStagedSelection;
    /* access modifiers changed from: private */
    public boolean mSuppressNextEvent;
    private final Runnable measureAndLayout = new Runnable() {
        public void run() {
            ReactPicker.this.measure(MeasureSpec.makeMeasureSpec(ReactPicker.this.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
            ReactPicker.this.layout(ReactPicker.this.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
        }
    };

    public interface OnSelectListener {
        void onItemSelected(int i);
    }

    public ReactPicker(Context context) {
        super(context);
    }

    public ReactPicker(Context context, int mode) {
        super(context, mode);
        this.mMode = mode;
    }

    public ReactPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ReactPicker(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle, mode);
        this.mMode = mode;
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        if (getOnItemSelectedListener() == null) {
            this.mSuppressNextEvent = true;
            setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    if (!ReactPicker.this.mSuppressNextEvent && ReactPicker.this.mOnSelectListener != null) {
                        ReactPicker.this.mOnSelectListener.onItemSelected(position);
                    }
                    ReactPicker.this.mSuppressNextEvent = false;
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (!ReactPicker.this.mSuppressNextEvent && ReactPicker.this.mOnSelectListener != null) {
                        ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                    }
                    ReactPicker.this.mSuppressNextEvent = false;
                }
            });
        }
        this.mOnSelectListener = onSelectListener;
    }

    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    public void setStagedSelection(int selection) {
        this.mStagedSelection = Integer.valueOf(selection);
    }

    public void updateStagedSelection() {
        if (this.mStagedSelection != null) {
            setSelectionWithSuppressEvent(this.mStagedSelection.intValue());
            this.mStagedSelection = null;
        }
    }

    private void setSelectionWithSuppressEvent(int position) {
        if (position != getSelectedItemPosition()) {
            this.mSuppressNextEvent = true;
            setSelection(position);
        }
    }

    public Integer getPrimaryColor() {
        return this.mPrimaryColor;
    }

    public void setPrimaryColor(Integer primaryColor) {
        this.mPrimaryColor = primaryColor;
    }

    @VisibleForTesting
    public int getMode() {
        return this.mMode;
    }
}
