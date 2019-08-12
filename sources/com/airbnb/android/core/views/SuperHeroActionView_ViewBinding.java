package com.airbnb.android.core.views;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class SuperHeroActionView_ViewBinding implements Unbinder {
    private SuperHeroActionView target;

    public SuperHeroActionView_ViewBinding(SuperHeroActionView target2) {
        this(target2, target2);
    }

    public SuperHeroActionView_ViewBinding(SuperHeroActionView target2, View source) {
        this.target = target2;
        target2.bubble = (Button) Utils.findRequiredViewAsType(source, C0716R.C0718id.super_hero_action_bubble, "field 'bubble'", Button.class);
    }

    public void unbind() {
        SuperHeroActionView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.bubble = null;
    }
}
