package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.RefreshLoader;

public class DlsManageListingActivity_ViewBinding implements Unbinder {
    private DlsManageListingActivity target;

    public DlsManageListingActivity_ViewBinding(DlsManageListingActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public DlsManageListingActivity_ViewBinding(DlsManageListingActivity target2, View source) {
        this.target = target2;
        target2.fullLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C7368R.C7370id.loading_row, "field 'fullLoader'", RefreshLoader.class);
    }

    public void unbind() {
        DlsManageListingActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.fullLoader = null;
    }
}
