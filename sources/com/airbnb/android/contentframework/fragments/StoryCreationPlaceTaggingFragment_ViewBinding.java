package com.airbnb.android.contentframework.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;

public class StoryCreationPlaceTaggingFragment_ViewBinding implements Unbinder {
    private StoryCreationPlaceTaggingFragment target;

    public StoryCreationPlaceTaggingFragment_ViewBinding(StoryCreationPlaceTaggingFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5709R.C5711id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.inputMarquee = (InputMarquee) Utils.findRequiredViewAsType(source, C5709R.C5711id.input_marquee, "field 'inputMarquee'", InputMarquee.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5709R.C5711id.search_recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        StoryCreationPlaceTaggingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.inputMarquee = null;
        target2.recyclerView = null;
    }
}
