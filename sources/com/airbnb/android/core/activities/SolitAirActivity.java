package com.airbnb.android.core.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.Toolbar;
import android.view.ViewGroup;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.interfaces.LoaderFrameInterface;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.views.LoaderFrame;

public class SolitAirActivity extends AirActivity implements LoaderFrameInterface {
    private static final String ARG_BUNDLE = "bundle";
    protected LoaderFrame mLoaderFrame;
    protected ViewGroup root;
    protected Toolbar toolbar;

    public static Intent intentForBundle(Context context, Class<?> cls, Bundle bundle) {
        return new Intent(context, cls).putExtra("bundle", bundle);
    }

    public Bundle getBundleFromIntent() {
        return getIntent() != null ? getIntent().getBundleExtra("bundle") : new Bundle(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            for (int feature : getWindowFeatures()) {
                supportRequestWindowFeature(feature);
            }
        } catch (Exception e) {
        }
        setContentView(getLayoutId());
        this.toolbar = (Toolbar) findViewById(C0716R.C0718id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
            if (!MiscUtils.getBooleanFromAttribute(this, C0716R.attr.hasToolbar)) {
                this.toolbar.setVisibility(8);
            }
        }
        this.mLoaderFrame = (LoaderFrame) findViewById(C0716R.C0718id.loading_overlay);
        this.root = (ViewGroup) findViewById(C0716R.C0718id.root);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0716R.layout.activity_single_fragment;
    }

    /* access modifiers changed from: protected */
    public int[] getWindowFeatures() {
        return new int[0];
    }

    /* access modifiers changed from: protected */
    public void showFragment(Fragment fragment, boolean addToBackStack, String tag) {
        showFragment(fragment, C0716R.C0718id.content_container, FragmentTransitionType.SlideInFromSide, addToBackStack, tag);
    }

    /* access modifiers changed from: protected */
    public Fragment findFragment() {
        return getSupportFragmentManager().findFragmentById(C0716R.C0718id.content_container);
    }

    /* access modifiers changed from: protected */
    public void showFragment(Fragment fragment, boolean addToBackStack) {
        showFragment(fragment, addToBackStack, null);
    }

    /* access modifiers changed from: protected */
    public LoaderFrame getLoaderFrame() {
        return this.mLoaderFrame;
    }

    public void showLoader(boolean show) {
        LoaderFrame loaderFrame = getLoaderFrame();
        if (show) {
            loaderFrame.setVisibility(0);
            loaderFrame.startAnimation();
            return;
        }
        loaderFrame.finish();
    }

    public void setLoaderFrameBackground(int color) {
        this.mLoaderFrame.setBackgroundColor(getResources().getColor(color));
    }

    public void hideToolbar() {
        this.toolbar.setVisibility(8);
    }

    public void showToolbar() {
        this.toolbar.setVisibility(0);
    }
}
