package com.airbnb.android.lib.fragments.inbox;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class InboxSearchFragment_ViewBinding implements Unbinder {
    private InboxSearchFragment target;

    public InboxSearchFragment_ViewBinding(InboxSearchFragment target2, View source) {
        this.target = target2;
        target2.searchResultsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.search_recycler_view, "field 'searchResultsRecyclerView'", RecyclerView.class);
    }

    public void unbind() {
        InboxSearchFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.searchResultsRecyclerView = null;
    }
}
