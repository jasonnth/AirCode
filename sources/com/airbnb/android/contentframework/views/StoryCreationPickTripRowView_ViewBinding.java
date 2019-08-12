package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCreationPickTripRowView_ViewBinding implements Unbinder {
    private StoryCreationPickTripRowView target;

    public StoryCreationPickTripRowView_ViewBinding(StoryCreationPickTripRowView target2) {
        this(target2, target2);
    }

    public StoryCreationPickTripRowView_ViewBinding(StoryCreationPickTripRowView target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.image, "field 'image'", AirImageView.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.subtitle, "field 'subtitle'", AirTextView.class);
    }

    public void unbind() {
        StoryCreationPickTripRowView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.title = null;
        target2.subtitle = null;
    }
}
