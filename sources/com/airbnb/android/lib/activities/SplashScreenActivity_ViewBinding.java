package com.airbnb.android.lib.activities;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public class SplashScreenActivity_ViewBinding implements Unbinder {
    private SplashScreenActivity target;

    public SplashScreenActivity_ViewBinding(SplashScreenActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public SplashScreenActivity_ViewBinding(SplashScreenActivity target2, View source) {
        this.target = target2;
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.splash_screen_loader_frame, "field 'loaderFrame'", LoaderFrame.class);
        target2.cblSplashScreen = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.cbl_splash_screen, "field 'cblSplashScreen'", FrameLayout.class);
    }

    public void unbind() {
        SplashScreenActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderFrame = null;
        target2.cblSplashScreen = null;
    }
}
