package com.airbnb.android.explore.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class MTLocationChinaFragment_ViewBinding implements Unbinder {
    private MTLocationChinaFragment target;

    public MTLocationChinaFragment_ViewBinding(MTLocationChinaFragment target2, View source) {
        this.target = target2;
        target2.searchEditText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.search_edit_text, "field 'searchEditText'", AirEditTextView.class);
        target2.searchOptionsList = (RecyclerView) Utils.findRequiredViewAsType(source, C0857R.C0859id.recycler_view, "field 'searchOptionsList'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0857R.C0859id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        MTLocationChinaFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.searchEditText = null;
        target2.searchOptionsList = null;
        target2.toolbar = null;
    }
}
