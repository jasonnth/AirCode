package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.StarRatingSummary_ViewBinding */
public class StarRatingSummary_ViewBinding implements Unbinder {
    private StarRatingSummary target;

    public StarRatingSummary_ViewBinding(StarRatingSummary target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_stars, "field 'ratingBar'", RatingBar.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        StarRatingSummary target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.ratingBar = null;
        target2.divider = null;
    }
}
