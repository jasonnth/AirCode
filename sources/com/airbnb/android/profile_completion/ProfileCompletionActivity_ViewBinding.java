package com.airbnb.android.profile_completion;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;

public class ProfileCompletionActivity_ViewBinding implements Unbinder {
    private ProfileCompletionActivity target;

    public ProfileCompletionActivity_ViewBinding(ProfileCompletionActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ProfileCompletionActivity_ViewBinding(ProfileCompletionActivity target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7646R.C7648id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7646R.C7648id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C7646R.C7648id.loader_frame, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        ProfileCompletionActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.loaderFrame = null;
    }
}
