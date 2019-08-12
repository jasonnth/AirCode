package com.airbnb.android.places.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;

public class PickAddToPlansActivity_ViewBinding implements Unbinder {
    private PickAddToPlansActivity target;
    private View view2131755295;

    public PickAddToPlansActivity_ViewBinding(PickAddToPlansActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public PickAddToPlansActivity_ViewBinding(final PickAddToPlansActivity target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C7627R.C7629id.scrim, "method 'onScrimClicked'");
        this.view2131755295 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onScrimClicked();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755295.setOnClickListener(null);
        this.view2131755295 = null;
    }
}
