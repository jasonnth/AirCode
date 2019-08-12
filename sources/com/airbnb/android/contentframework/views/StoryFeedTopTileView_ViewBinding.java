package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryFeedTopTileView_ViewBinding implements Unbinder {
    private StoryFeedTopTileView target;

    public StoryFeedTopTileView_ViewBinding(StoryFeedTopTileView target2) {
        this(target2, target2);
    }

    public StoryFeedTopTileView_ViewBinding(StoryFeedTopTileView target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.image, "field 'image'", AirImageView.class);
        target2.mainText = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.main_text, "field 'mainText'", AirTextView.class);
        target2.secondaryText = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.secondary_text, "field 'secondaryText'", AirTextView.class);
    }

    public void unbind() {
        StoryFeedTopTileView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.mainText = null;
        target2.secondaryText = null;
    }
}
