package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.HeroMarquee;

public class LYSPublishFragment_ViewBinding implements Unbinder {
    private LYSPublishFragment target;

    public LYSPublishFragment_ViewBinding(LYSPublishFragment target2, View source) {
        this.target = target2;
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C7251R.C7253id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
        target2.tipView = (TipView) Utils.findRequiredViewAsType(source, C7251R.C7253id.tip, "field 'tipView'", TipView.class);
    }

    public void unbind() {
        LYSPublishFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.heroMarquee = null;
        target2.tipView = null;
    }
}
