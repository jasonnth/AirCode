package com.airbnb.android.core.views;

import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class SearchSuggestionPopularView_ViewBinding implements Unbinder {
    private SearchSuggestionPopularView target;

    public SearchSuggestionPopularView_ViewBinding(SearchSuggestionPopularView target2) {
        this(target2, target2);
    }

    public SearchSuggestionPopularView_ViewBinding(SearchSuggestionPopularView target2, View source) {
        this.target = target2;
        target2.tabLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.tab_layout, "field 'tabLayout'", LinearLayout.class);
        target2.gridLayout = (GridLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.grid_layout, "field 'gridLayout'", GridLayout.class);
    }

    public void unbind() {
        SearchSuggestionPopularView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tabLayout = null;
        target2.gridLayout = null;
    }
}
