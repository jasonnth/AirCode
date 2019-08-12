package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.HomeStarRatingBreakdown_ViewBinding */
public class HomeStarRatingBreakdown_ViewBinding implements Unbinder {
    private HomeStarRatingBreakdown target;

    public HomeStarRatingBreakdown_ViewBinding(HomeStarRatingBreakdown target2, View source) {
        this.target = target2;
        target2.starsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.stars_container, "field 'starsContainer'", LinearLayout.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        HomeStarRatingBreakdown target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.starsContainer = null;
        target2.divider = null;
    }
}
