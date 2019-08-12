package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class ManageListingTextRowView_ViewBinding implements Unbinder {
    private ManageListingTextRowView target;

    public ManageListingTextRowView_ViewBinding(ManageListingTextRowView target2) {
        this(target2, target2);
    }

    public ManageListingTextRowView_ViewBinding(ManageListingTextRowView target2, View source) {
        this.target = target2;
        target2.titleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_title, "field 'titleRow'", AirTextView.class);
        target2.subtitleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_description, "field 'subtitleRow'", AirTextView.class);
        target2.colorizedIconView = (ColorizedIconView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_check_mark, "field 'colorizedIconView'", ColorizedIconView.class);
        target2.dividerView = Utils.findRequiredView(source, C0880R.C0882id.row_divider, "field 'dividerView'");
        target2.upsellView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.upsell_view, "field 'upsellView'", AirTextView.class);
    }

    public void unbind() {
        ManageListingTextRowView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleRow = null;
        target2.subtitleRow = null;
        target2.colorizedIconView = null;
        target2.dividerView = null;
        target2.upsellView = null;
    }
}
