package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class StoryHeaderRowView_ViewBinding implements Unbinder {
    private StoryHeaderRowView target;

    public StoryHeaderRowView_ViewBinding(StoryHeaderRowView target2) {
        this(target2, target2);
    }

    public StoryHeaderRowView_ViewBinding(StoryHeaderRowView target2, View source) {
        this.target = target2;
        target2.authorImage = (HaloImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.author_image, "field 'authorImage'", HaloImageView.class);
        target2.authorName = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.author_name, "field 'authorName'", AirTextView.class);
        target2.affiliateText = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.affiliate_text, "field 'affiliateText'", AirTextView.class);
    }

    public void unbind() {
        StoryHeaderRowView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.authorImage = null;
        target2.authorName = null;
        target2.affiliateText = null;
    }
}
