package com.airbnb.android.cityregistration.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SheetProgressBar;

public class CityRegistrationActivity_ViewBinding implements Unbinder {
    private CityRegistrationActivity target;

    public CityRegistrationActivity_ViewBinding(CityRegistrationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public CityRegistrationActivity_ViewBinding(CityRegistrationActivity target2, View source) {
        this.target = target2;
        target2.progressBar = (SheetProgressBar) Utils.findRequiredViewAsType(source, C5630R.C5632id.sheet_progress_bar, "field 'progressBar'", SheetProgressBar.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C5630R.C5632id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
    }

    public void unbind() {
        CityRegistrationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.progressBar = null;
        target2.refreshLoader = null;
    }
}
