package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import android.view.View;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.FtueAnalytics;
import com.airbnb.android.lib.fragments.AppIntroFtueFragment;
import com.airbnb.android.lib.fragments.ViewPagerFtueFragment;

@Deprecated
public class AppIntroFtueActivity extends ViewPagerFtueBaseActivity {
    private static final int NUM_FTUE_PAGES = 4;
    private boolean mUseSlideAnim = true;

    public static Intent intentForDefault(Context c) {
        return new Intent(c, AppIntroFtueActivity.class);
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FtueAnalytics.trackAppIntroImpression();
        if (this.accountManager.hasCurrentUser()) {
            this.mStickyButton.setVisibility(8);
        } else {
            this.mStickyButton.setTitle(C0880R.string.sign_up);
            Intent signinActivityIntent = LoginActivityIntents.intent(this);
            signinActivityIntent.addFlags(131072);
            this.mStickyButton.setOnClickListener(AppIntroFtueActivity$$Lambda$1.lambdaFactory$(this, signinActivityIntent));
            showSecondStickyButton(C0880R.string.sign_in, AppIntroFtueActivity$$Lambda$2.lambdaFactory$(this, signinActivityIntent));
        }
        darkenFtuePics(true);
        addActionBarSkipButton();
    }

    static /* synthetic */ void lambda$onCreate$0(AppIntroFtueActivity appIntroFtueActivity, Intent signinActivityIntent, View v) {
        FtueAnalytics.trackAppIntroSignin(appIntroFtueActivity.mCurrPage);
        appIntroFtueActivity.startActivity(signinActivityIntent);
        appIntroFtueActivity.finish();
    }

    static /* synthetic */ void lambda$onCreate$1(AppIntroFtueActivity appIntroFtueActivity, Intent signinActivityIntent, View v) {
        FtueAnalytics.trackAppIntroSignup(appIntroFtueActivity.mCurrPage);
        appIntroFtueActivity.startActivity(signinActivityIntent);
        appIntroFtueActivity.finish();
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        FtueAnalytics.trackAppIntroUp(this.mCurrPage);
        super.onHomeActionPressed();
    }

    /* access modifiers changed from: protected */
    public int getLogoRes() {
        return C0880R.C0881drawable.airbnb_logo_white;
    }

    public ViewPagerFtueFragment forStep(int step) {
        ViewPagerFtueFragment newInstance;
        FragmentManager fragMgr = getSupportFragmentManager();
        switch (step) {
            case 0:
                ViewPagerFtueFragment findFragment = ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_howitworks_1, 0, this.mUseSlideAnim, C0880R.color.c_rausch);
                if (!this.mUseSlideAnim) {
                    return findFragment;
                }
                this.mUseSlideAnim = false;
                return findFragment;
            case 1:
                return ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_howitworks_2, 0, false, C0880R.color.c_beach);
            case 2:
                return ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_howitworks_3, 0, false, C0880R.color.c_tirol);
            case 3:
                if (this.accountManager.hasCurrentUser()) {
                    newInstance = ViewPagerFtueFragment.findFragment(fragMgr, C0880R.string.ftue_howitworks_4, 0, false, C0880R.color.c_kazan);
                } else {
                    newInstance = AppIntroFtueFragment.newInstance(this, C0880R.string.ftue_howitworks_4, 0, false, C0880R.color.c_kazan);
                }
                return newInstance;
            default:
                return null;
        }
    }

    public int getNumPages() {
        return 4;
    }

    public int[] getBackgroundImageResIds() {
        return new int[]{C0880R.C0881drawable.appintro1, C0880R.C0881drawable.appintro2, C0880R.C0881drawable.appintro3, C0880R.C0881drawable.appintro4};
    }

    public boolean isParallaxMode() {
        return false;
    }

    private void addActionBarSkipButton() {
        View skip = getLayoutInflater().inflate(C0880R.layout.skip_actionbar_text, getToolbar(), true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        skip.setOnClickListener(AppIntroFtueActivity$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$addActionBarSkipButton$2(AppIntroFtueActivity appIntroFtueActivity, View v) {
        FtueAnalytics.trackAppIntroSkip(appIntroFtueActivity.mCurrPage);
        appIntroFtueActivity.finish();
    }
}
