package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.GuestStarRatingBreakdown_ViewBinding */
public class GuestStarRatingBreakdown_ViewBinding implements Unbinder {
    private GuestStarRatingBreakdown target;

    public GuestStarRatingBreakdown_ViewBinding(GuestStarRatingBreakdown target2, View source) {
        this.target = target2;
        target2.starsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.stars_container, "field 'starsContainer'", LinearLayout.class);
    }

    public void unbind() {
        GuestStarRatingBreakdown target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.starsContainer = null;
    }
}
