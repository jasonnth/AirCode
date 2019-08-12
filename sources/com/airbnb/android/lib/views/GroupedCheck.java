package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.lib.C0880R;

public class GroupedCheck extends GroupedCompoundButton {
    public GroupedCheck(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GroupedCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupedCheck(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public int getCompoundButtonLayoutRes() {
        return C0880R.layout.grouped_check;
    }
}
