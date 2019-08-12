package com.airbnb.p027n2.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: com.airbnb.n2.utils.SnackbarDodger */
public class SnackbarDodger extends Behavior<View> {
    public SnackbarDodger() {
    }

    public SnackbarDodger(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof SnackbarLayout;
    }

    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (!layoutDependsOn(parent, child, dependency)) {
            return false;
        }
        child.setTranslationY(Math.min(0.0f, dependency.getTranslationY() - ((float) dependency.getHeight())));
        return true;
    }

    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(0.0f);
    }
}
