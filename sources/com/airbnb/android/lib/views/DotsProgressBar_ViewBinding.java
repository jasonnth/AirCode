package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.lib.C0880R;

public class DotsProgressBar_ViewBinding implements Unbinder {
    public DotsProgressBar_ViewBinding(DotsProgressBar target) {
        this(target, target.getContext());
    }

    @Deprecated
    public DotsProgressBar_ViewBinding(DotsProgressBar target, View source) {
        this(target, source.getContext());
    }

    public DotsProgressBar_ViewBinding(DotsProgressBar target, Context context) {
        Resources res = context.getResources();
        target.dotMargin = res.getDimensionPixelSize(C0880R.dimen.dot_progress_bar_dot_margin);
        target.strokeWidth = res.getDimensionPixelSize(C0880R.dimen.dot_progress_bar_stroke_width);
    }

    public void unbind() {
    }
}
