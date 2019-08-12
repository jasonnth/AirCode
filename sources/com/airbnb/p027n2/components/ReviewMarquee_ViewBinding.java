package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ReviewMarquee_ViewBinding */
public class ReviewMarquee_ViewBinding implements Unbinder {
    private ReviewMarquee target;

    public ReviewMarquee_ViewBinding(ReviewMarquee target2, View source) {
        this.target = target2;
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ReviewMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.ratingBar = null;
        target2.divider = null;
    }
}
