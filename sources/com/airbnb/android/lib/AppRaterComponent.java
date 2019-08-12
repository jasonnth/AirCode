package com.airbnb.android.lib;

import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.apprater.AppRaterDialogFragment;
import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface AppRaterComponent {

    public interface Builder {
        AppRaterComponent build();
    }

    AppRaterController appRaterController();

    void inject(AppRaterDialogFragment appRaterDialogFragment);
}
