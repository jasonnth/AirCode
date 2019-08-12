package com.airbnb.android.core.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class SearchSuggestionRecentView_ViewBinding implements Unbinder {
    private SearchSuggestionRecentView target;

    public SearchSuggestionRecentView_ViewBinding(SearchSuggestionRecentView target2) {
        this(target2, target2);
    }

    public SearchSuggestionRecentView_ViewBinding(SearchSuggestionRecentView target2, View source) {
        this.target = target2;
        target2.itemContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.item_container, "field 'itemContainer'", LinearLayout.class);
    }

    public void unbind() {
        SearchSuggestionRecentView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.itemContainer = null;
    }
}
