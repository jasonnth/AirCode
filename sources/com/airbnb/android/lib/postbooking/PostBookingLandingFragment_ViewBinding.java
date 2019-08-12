package com.airbnb.android.lib.postbooking;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.HeroMarquee;

public class PostBookingLandingFragment_ViewBinding implements Unbinder {
    private PostBookingLandingFragment target;

    public PostBookingLandingFragment_ViewBinding(PostBookingLandingFragment target2, View source) {
        this.target = target2;
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.post_booking_hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        PostBookingLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.heroMarquee = null;
    }
}
