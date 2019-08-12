package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.HeroMarquee;

public class HeroMarqueeFragment_ViewBinding implements Unbinder {
    private HeroMarqueeFragment target;

    public HeroMarqueeFragment_ViewBinding(HeroMarqueeFragment target2, View source) {
        this.target = target2;
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        HeroMarqueeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.heroMarquee = null;
    }
}
