package com.airbnb.android.core.controllers;

import android.app.Activity;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.utils.NavigationUtils;

public class NavigationController {
    protected final Activity activity;
    protected final FragmentManager fragmentManager;

    protected NavigationController(Activity activity2, FragmentManager fragmentManager2) {
        this.activity = activity2;
        this.fragmentManager = fragmentManager2;
    }

    /* access modifiers changed from: protected */
    public void transitionTo(Fragment newFragment) {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, newFragment, C0716R.C0718id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    /* access modifiers changed from: protected */
    public void transitionTo(Fragment newFragment, String tag) {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, newFragment, C0716R.C0718id.content_container, FragmentTransitionType.SlideInFromSide, true, tag);
    }

    /* access modifiers changed from: protected */
    public void modalTransitionTo(Fragment newFragment) {
        NavigationUtils.showModal(this.fragmentManager, this.activity, newFragment, C0716R.C0718id.content_container, C0716R.C0718id.modal_container, true);
    }

    public Fragment getCurrentFragment() {
        return this.fragmentManager.findFragmentById(C0716R.C0718id.content_container);
    }

    public Fragment getCurrentModalFragment() {
        return this.fragmentManager.findFragmentById(C0716R.C0718id.modal_container);
    }
}
