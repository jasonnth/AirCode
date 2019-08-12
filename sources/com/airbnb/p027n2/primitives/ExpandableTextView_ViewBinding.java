package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.ExpandableTextView_ViewBinding */
public class ExpandableTextView_ViewBinding implements Unbinder {
    @Deprecated
    public ExpandableTextView_ViewBinding(ExpandableTextView target, View source) {
        this(target, source.getContext());
    }

    public ExpandableTextView_ViewBinding(ExpandableTextView target, Context context) {
        target.readMoreTextColor = ContextCompat.getColor(context, R.color.n2_babu);
    }

    public void unbind() {
    }
}
