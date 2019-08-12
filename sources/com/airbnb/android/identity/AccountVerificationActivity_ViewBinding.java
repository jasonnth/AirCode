package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SheetProgressBar;

public class AccountVerificationActivity_ViewBinding implements Unbinder {
    private AccountVerificationActivity target;

    public AccountVerificationActivity_ViewBinding(AccountVerificationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public AccountVerificationActivity_ViewBinding(AccountVerificationActivity target2, View source) {
        this.target = target2;
        target2.progressBar = (SheetProgressBar) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_progress_bar, "field 'progressBar'", SheetProgressBar.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6533R.C6535id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_loader_row, "field 'refreshLoader'", RefreshLoader.class);
    }

    public void unbind() {
        AccountVerificationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.progressBar = null;
        target2.toolbar = null;
        target2.refreshLoader = null;
    }
}
