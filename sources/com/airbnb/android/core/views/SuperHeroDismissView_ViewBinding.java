package com.airbnb.android.core.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class SuperHeroDismissView_ViewBinding implements Unbinder {
    private SuperHeroDismissView target;

    public SuperHeroDismissView_ViewBinding(SuperHeroDismissView target2) {
        this(target2, target2);
    }

    public SuperHeroDismissView_ViewBinding(SuperHeroDismissView target2, View source) {
        this.target = target2;
        target2.dismissChevron = Utils.findRequiredView(source, C0716R.C0718id.super_hero_dismiss_chevron, "field 'dismissChevron'");
    }

    public void unbind() {
        SuperHeroDismissView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.dismissChevron = null;
    }
}
