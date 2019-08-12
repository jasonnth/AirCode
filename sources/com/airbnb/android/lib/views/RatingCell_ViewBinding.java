package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class RatingCell_ViewBinding implements Unbinder {
    private RatingCell target;

    public RatingCell_ViewBinding(RatingCell target2) {
        this(target2, target2);
    }

    public RatingCell_ViewBinding(RatingCell target2, View source) {
        this.target = target2;
        target2.titleTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_rating, "field 'titleTextView'", TextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.stars_rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.ratingText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.stars_rating_text, "field 'ratingText'", TextView.class);
        target2.ratingBarContainer = Utils.findRequiredView(source, C0880R.C0882id.stars_rating_container, "field 'ratingBarContainer'");
        target2.divider = Utils.findRequiredView(source, C0880R.C0882id.divider, "field 'divider'");
    }

    public void unbind() {
        RatingCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.ratingBar = null;
        target2.ratingText = null;
        target2.ratingBarContainer = null;
        target2.divider = null;
    }
}
