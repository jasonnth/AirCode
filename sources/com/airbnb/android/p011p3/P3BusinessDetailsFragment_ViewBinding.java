package com.airbnb.android.p011p3;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.SimpleTextRow;

/* renamed from: com.airbnb.android.p3.P3BusinessDetailsFragment_ViewBinding */
public class P3BusinessDetailsFragment_ViewBinding implements Unbinder {
    private P3BusinessDetailsFragment target;

    public P3BusinessDetailsFragment_ViewBinding(P3BusinessDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C7532R.C7534id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.businessDetails = (LinearLayout) Utils.findRequiredViewAsType(source, C7532R.C7534id.business_details, "field 'businessDetails'", LinearLayout.class);
        target2.businessDetailsText = (SimpleTextRow) Utils.findRequiredViewAsType(source, C7532R.C7534id.business_details_text, "field 'businessDetailsText'", SimpleTextRow.class);
    }

    public void unbind() {
        P3BusinessDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.loadingView = null;
        target2.businessDetails = null;
        target2.businessDetailsText = null;
    }
}
