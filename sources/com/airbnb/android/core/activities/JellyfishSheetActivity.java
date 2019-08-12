package com.airbnb.android.core.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public abstract class JellyfishSheetActivity extends AirActivity {
    @BindView
    protected JellyfishView jellyfishView;
    @BindView
    protected LoaderFrame loaderFrame;
    @BindView
    protected ViewGroup rootView;
    @BindView
    protected AirToolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0716R.layout.activity_jellyfish_sheet);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.jellyfishView.setPalette(1);
        this.toolbar.setNavigationIcon(2);
    }

    public void showFragment(Fragment fragment, boolean addToBackStack) {
        showFragment(fragment, C0716R.C0718id.content_container, FragmentTransitionType.SlideFromBottom, addToBackStack);
    }

    public void setTheme(int resid) {
        super.setTheme(C0716R.C0719style.Theme_Airbnb_TransparentActionBarDarkText);
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        if (useHomeAsBack()) {
            onBackPressed();
        } else {
            super.onHomeActionPressed();
        }
    }

    public boolean useHomeAsBack() {
        return false;
    }

    public void startLoader() {
        this.loaderFrame.startAnimation();
    }

    public void stopLoader() {
        this.loaderFrame.finishImmediate();
    }
}
