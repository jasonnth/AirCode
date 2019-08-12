package com.airbnb.p027n2.utils;

import android.content.Context;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.utils.PillDrawableFactory_ViewBinding */
public class PillDrawableFactory_ViewBinding implements Unbinder {
    @Deprecated
    public PillDrawableFactory_ViewBinding(PillDrawableFactory target, View source) {
        this(target, source.getContext());
    }

    public PillDrawableFactory_ViewBinding(PillDrawableFactory target, Context context) {
        target.preLollipopBorderWidth = context.getResources().getDimensionPixelSize(R.dimen.n2_pill_default_elevation_border_width);
    }

    public void unbind() {
    }
}
