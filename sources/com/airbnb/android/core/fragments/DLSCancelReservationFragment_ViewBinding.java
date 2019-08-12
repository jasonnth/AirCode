package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.components.LoadingView;

public class DLSCancelReservationFragment_ViewBinding implements Unbinder {
    private DLSCancelReservationFragment target;

    public DLSCancelReservationFragment_ViewBinding(DLSCancelReservationFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0716R.C0718id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        DLSCancelReservationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.heroMarquee = null;
        target2.loadingView = null;
    }
}
