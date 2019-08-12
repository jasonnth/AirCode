package com.airbnb.android.explore.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class ExplorePlacesFiltersFragment_ViewBinding implements Unbinder {
    private ExplorePlacesFiltersFragment target;
    private View view2131755299;

    public ExplorePlacesFiltersFragment_ViewBinding(final ExplorePlacesFiltersFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0857R.C0859id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0857R.C0859id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0857R.C0859id.search_button, "field 'searchButton' and method 'onViewPlacesClicked'");
        target2.searchButton = (FixedActionFooter) Utils.castView(view, C0857R.C0859id.search_button, "field 'searchButton'", FixedActionFooter.class);
        this.view2131755299 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onViewPlacesClicked();
            }
        });
    }

    public void unbind() {
        ExplorePlacesFiltersFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.searchButton = null;
        this.view2131755299.setOnClickListener(null);
        this.view2131755299 = null;
    }
}
