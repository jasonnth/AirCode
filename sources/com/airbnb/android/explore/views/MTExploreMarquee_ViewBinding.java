package com.airbnb.android.explore.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.SlidingTabLayout;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class MTExploreMarquee_ViewBinding implements Unbinder {
    private MTExploreMarquee target;

    public MTExploreMarquee_ViewBinding(MTExploreMarquee target2) {
        this(target2, target2);
    }

    public MTExploreMarquee_ViewBinding(MTExploreMarquee target2, View source) {
        this.target = target2;
        target2.tabLayout = (SlidingTabLayout) Utils.findRequiredViewAsType(source, C0857R.C0859id.tabs, "field 'tabLayout'", SlidingTabLayout.class);
        target2.searchBar = (MTTripsSearchInterface) Utils.findRequiredViewAsType(source, C0857R.C0859id.mt_trips_search_view, "field 'searchBar'", MTTripsSearchInterface.class);
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0857R.C0859id.jellyfish_background, "field 'jellyfishView'", JellyfishView.class);
        target2.bottomDivider = Utils.findRequiredView(source, C0857R.C0859id.bottom_divider, "field 'bottomDivider'");
        Context context = source.getContext();
        Resources res = context.getResources();
        target2.babuColor = ContextCompat.getColor(context, C0857R.color.n2_babu);
        target2.foggyColor = ContextCompat.getColor(context, C0857R.color.n2_foggy);
        target2.whiteColor = ContextCompat.getColor(context, C0857R.color.white);
        target2.white80color = ContextCompat.getColor(context, C0857R.color.n2_white_80);
        target2.exploreMarqueeNegativeMargin = res.getDimensionPixelSize(C0857R.dimen.explore_marquee_bottom_negative);
    }

    public void unbind() {
        MTExploreMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tabLayout = null;
        target2.searchBar = null;
        target2.jellyfishView = null;
        target2.bottomDivider = null;
    }
}
