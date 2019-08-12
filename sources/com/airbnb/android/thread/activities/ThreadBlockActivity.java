package com.airbnb.android.thread.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.UserBlock;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.thread.C1729R;
import com.airbnb.android.thread.controllers.ThreadBlockController;
import com.airbnb.android.thread.fragments.ThreadBlockInfoFragment;
import com.airbnb.android.thread.fragments.ThreadBlockInfoFragment.InfoType;
import com.airbnb.android.thread.fragments.ThreadBlockReasonFragment;
import icepick.State;

public class ThreadBlockActivity extends AirActivity implements ThreadBlockController {
    @State
    Thread thread;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1729R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.thread = (Thread) getIntent().getParcelableExtra(ThreadBlockActivityIntents.ARG_THREAD);
        }
        showFragment(ThreadBlockReasonFragment.create(), FragmentTransitionType.SlideFromBottom);
    }

    private void showFragment(Fragment fragment, FragmentTransitionType transitionType) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, fragment, C1729R.C1731id.content_container, transitionType, true);
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            finish(0);
        }
    }

    public void finishOk() {
        finish(-1);
    }

    private void finish(int resultCode) {
        Intent data = new Intent();
        data.putExtra(ThreadBlockActivityIntents.ARG_THREAD, this.thread);
        setResult(resultCode, data);
        finish();
    }

    public Thread getThread() {
        return this.thread;
    }

    public void showThreadBlockFragment() {
        if (this.thread.isBlockable()) {
            showFragment(ThreadBlockInfoFragment.create(InfoType.InitialBlockConfirm, this.thread.getOtherUser().getName()), FragmentTransitionType.SlideInFromSide);
        } else {
            showFragment(ThreadBlockInfoFragment.create(InfoType.ContactUs, this.thread.getOtherUser().getName()), FragmentTransitionType.SlideInFromSide);
        }
    }

    public void showThreadBlockConfirmFragment(UserBlock userBlock) {
        this.thread.setBlock(userBlock);
        showFragment(ThreadBlockInfoFragment.create(InfoType.FinalBlockConfirm, this.thread.getOtherUser().getName()), FragmentTransitionType.SlideInFromSide);
    }

    public void showFlagUserConfirmFragment() {
        showFragment(ThreadBlockInfoFragment.create(InfoType.FlagUserConfirm, this.thread.getOtherUser().getName()), FragmentTransitionType.SlideInFromSide);
    }
}
