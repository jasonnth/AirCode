package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingLocalLawsFragment_ViewBinding implements Unbinder {
    private ManageListingLocalLawsFragment target;

    public ManageListingLocalLawsFragment_ViewBinding(ManageListingLocalLawsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.webView = (AirWebView) Utils.findRequiredViewAsType(source, C7368R.C7370id.web_view, "field 'webView'", AirWebView.class);
    }

    public void unbind() {
        ManageListingLocalLawsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.webView = null;
    }
}
