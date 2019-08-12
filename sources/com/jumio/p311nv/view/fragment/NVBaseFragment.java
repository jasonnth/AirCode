package com.jumio.p311nv.view.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.p311nv.C4430R;
import com.jumio.sdk.view.fragment.BaseFragment;

/* renamed from: com.jumio.nv.view.fragment.NVBaseFragment */
public abstract class NVBaseFragment<P extends Presenter> extends BaseFragment<P, INetverifyFragmentCallback> {
    public abstract void handleOrientationChange(boolean z, boolean z2);

    public void onViewCreated(View view, Bundle bundle) {
        boolean z;
        super.onViewCreated(view, bundle);
        if (((INetverifyFragmentCallback) this.callback).getRotationManager().isScreenPortrait() || ((INetverifyFragmentCallback) this.callback).getRotationManager().isTablet()) {
            z = true;
        } else {
            z = false;
        }
        handleOrientationChange(z, false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        handleOrientationChange(configuration.orientation == 1 || ((INetverifyFragmentCallback) this.callback).getRotationManager().isTablet(), true);
    }

    /* access modifiers changed from: protected */
    public void setActionbarSubTitle(String str) {
        Activity activity = getActivity();
        if (activity != null) {
            TextView textView = (TextView) activity.findViewById(C4430R.C4432id.toolbar_subtitle);
            if (str != null) {
                textView.setVisibility(0);
                textView.setText(str);
                return;
            }
            textView.setVisibility(8);
        }
    }
}
