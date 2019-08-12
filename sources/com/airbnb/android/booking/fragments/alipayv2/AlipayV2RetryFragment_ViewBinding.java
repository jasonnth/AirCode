package com.airbnb.android.booking.fragments.alipayv2;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.components.HeroMarquee;

public class AlipayV2RetryFragment_ViewBinding implements Unbinder {
    private AlipayV2RetryFragment target;

    public AlipayV2RetryFragment_ViewBinding(AlipayV2RetryFragment target2, View source) {
        this.target = target2;
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        AlipayV2RetryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.heroMarquee = null;
    }
}
