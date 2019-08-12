package com.airbnb.android.managelisting.settings;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class ManageListingInstantBookUpsellFragment_ViewBinding implements Unbinder {
    private ManageListingInstantBookUpsellFragment target;

    public ManageListingInstantBookUpsellFragment_ViewBinding(ManageListingInstantBookUpsellFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7368R.C7370id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C7368R.C7370id.footer, "field 'footer'", FixedDualActionFooter.class);
    }

    public void unbind() {
        ManageListingInstantBookUpsellFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.footer = null;
    }
}
