package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class SeeAllStoriesCard_ViewBinding implements Unbinder {
    private SeeAllStoriesCard target;

    public SeeAllStoriesCard_ViewBinding(SeeAllStoriesCard target2) {
        this(target2, target2);
    }

    public SeeAllStoriesCard_ViewBinding(SeeAllStoriesCard target2, View source) {
        this.target = target2;
        target2.photoView = (HaloImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.see_all_photo, "field 'photoView'", HaloImageView.class);
        target2.searchTerm = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.search_term, "field 'searchTerm'", AirTextView.class);
        target2.seeAllLabel = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.see_all_label, "field 'seeAllLabel'", AirTextView.class);
    }

    public void unbind() {
        SeeAllStoriesCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.photoView = null;
        target2.searchTerm = null;
        target2.seeAllLabel = null;
    }
}
