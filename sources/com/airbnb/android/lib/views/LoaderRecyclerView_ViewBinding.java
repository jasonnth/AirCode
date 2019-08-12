package com.airbnb.android.lib.views;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public class LoaderRecyclerView_ViewBinding implements Unbinder {
    private LoaderRecyclerView target;

    public LoaderRecyclerView_ViewBinding(LoaderRecyclerView target2) {
        this(target2, target2);
    }

    public LoaderRecyclerView_ViewBinding(LoaderRecyclerView target2, View source) {
        this.target = target2;
        target2.mEmptyResults = (EmptyResults) Utils.findRequiredViewAsType(source, C0880R.C0882id.empty_results, "field 'mEmptyResults'", EmptyResults.class);
        target2.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        target2.mLoaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.loader_frame, "field 'mLoaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        LoaderRecyclerView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mEmptyResults = null;
        target2.mRecyclerView = null;
        target2.mLoaderFrame = null;
    }
}
