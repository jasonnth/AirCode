package com.airbnb.android.contentframework.views;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryProductLinkElementView_ViewBinding implements Unbinder {
    private StoryProductLinkElementView target;

    public StoryProductLinkElementView_ViewBinding(StoryProductLinkElementView target2) {
        this(target2, target2);
    }

    public StoryProductLinkElementView_ViewBinding(StoryProductLinkElementView target2, View source) {
        this.target = target2;
        target2.header = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.header, "field 'header'", AirTextView.class);
        target2.topDivider = Utils.findRequiredView(source, C5709R.C5711id.top_divider, "field 'topDivider'");
        target2.thumbnailImage = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.image, "field 'thumbnailImage'", AirImageView.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.subtitle, "field 'subtitle'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, C5709R.C5711id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.wishlistButton = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.wishlist, "field 'wishlistButton'", AirImageView.class);
    }

    public void unbind() {
        StoryProductLinkElementView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.header = null;
        target2.topDivider = null;
        target2.thumbnailImage = null;
        target2.title = null;
        target2.subtitle = null;
        target2.ratingBar = null;
        target2.wishlistButton = null;
    }
}
