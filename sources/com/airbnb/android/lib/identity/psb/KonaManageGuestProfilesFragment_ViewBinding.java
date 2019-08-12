package com.airbnb.android.lib.identity.psb;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.primitives.AirTextView;

public class KonaManageGuestProfilesFragment_ViewBinding implements Unbinder {
    private KonaManageGuestProfilesFragment target;

    public KonaManageGuestProfilesFragment_ViewBinding(KonaManageGuestProfilesFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.footerNote = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.footer, "field 'footerNote'", AirTextView.class);
        target2.navigationView = (BookingNavigationView) Utils.findOptionalViewAsType(source, C0880R.C0882id.nav_view, "field 'navigationView'", BookingNavigationView.class);
    }

    public void unbind() {
        KonaManageGuestProfilesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.footerNote = null;
        target2.navigationView = null;
    }
}
