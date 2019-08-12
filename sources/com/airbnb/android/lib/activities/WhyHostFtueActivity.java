package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import android.view.View;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.FtueAnalytics;
import com.airbnb.android.lib.fragments.ViewPagerFtueFragment;
import com.airbnb.android.lib.listyourspace.LYSAnalytics;

public class WhyHostFtueActivity extends ViewPagerFtueBaseActivity {
    private static final int NUM_FTUE_PAGES = 4;
    private boolean mUseSlideAnim = true;

    public static Intent intentForDefault(Context c) {
        return new Intent(c, WhyHostFtueActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mStickyButton.setTitle(C0880R.string.ftue_action_lys);
        this.mStickyButton.setOnClickListener(WhyHostFtueActivity$$Lambda$1.lambdaFactory$(this));
        darkenFtuePics(true);
    }

    static /* synthetic */ void lambda$onCreate$0(WhyHostFtueActivity whyHostFtueActivity, View v) {
        if (v.getVisibility() == 0) {
            FtueAnalytics.trackWhyHostLYS();
            whyHostFtueActivity.finish();
            LYSAnalytics.trackAction("why_host", "enter_lys", null);
            whyHostFtueActivity.startActivity(ListYourSpaceIntents.intentForNewListing(whyHostFtueActivity, "why_host", null));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        FtueAnalytics.trackWhyHostImpression();
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        FtueAnalytics.trackWhyHostUp(this.mCurrPage);
        super.onHomeActionPressed();
    }

    public ViewPagerFtueFragment forStep(int step) {
        FragmentManager fragMgr = getSupportFragmentManager();
        switch (step) {
            case 0:
                ViewPagerFtueFragment findFragment = ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_whyhost_1, 0, this.mUseSlideAnim, C0880R.color.c_rausch);
                if (!this.mUseSlideAnim) {
                    return findFragment;
                }
                this.mUseSlideAnim = false;
                return findFragment;
            case 1:
                return ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_whyhost_2, 0, false, C0880R.color.c_beach);
            case 2:
                return ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_whyhost_3, 0, false, C0880R.color.c_tirol);
            case 3:
                return ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_whyhost_4, 0, false, C0880R.color.c_kazan);
            default:
                return null;
        }
    }

    public int getNumPages() {
        return 4;
    }

    public int[] getBackgroundImageResIds() {
        return new int[]{C0880R.C0881drawable.whyhost1, C0880R.C0881drawable.whyhost2, C0880R.C0881drawable.whyhost3, C0880R.C0881drawable.whyhost4};
    }

    public boolean isParallaxMode() {
        return false;
    }
}
