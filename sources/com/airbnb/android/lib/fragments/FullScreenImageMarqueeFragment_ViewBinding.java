package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.FullScreenImageMarquee;

public class FullScreenImageMarqueeFragment_ViewBinding implements Unbinder {
    private FullScreenImageMarqueeFragment target;

    public FullScreenImageMarqueeFragment_ViewBinding(FullScreenImageMarqueeFragment target2, View source) {
        this.target = target2;
        target2.fullScreenImageMarquee = (FullScreenImageMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.full_screen_image_marquee, "field 'fullScreenImageMarquee'", FullScreenImageMarquee.class);
    }

    public void unbind() {
        FullScreenImageMarqueeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.fullScreenImageMarquee = null;
    }
}
