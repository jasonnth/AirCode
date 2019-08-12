package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.p3.HomeTourGalleryFragment_ViewBinding */
public class HomeTourGalleryFragment_ViewBinding implements Unbinder {
    private HomeTourGalleryFragment target;

    public HomeTourGalleryFragment_ViewBinding(HomeTourGalleryFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7532R.C7534id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
    }

    public void unbind() {
        HomeTourGalleryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
