package com.airbnb.android.lib.fragments;

import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class SwitchAccountDialogFragment$SwitchAccountAdapter$$Lambda$1 implements OnClickListener {
    private final SwitchAccountAdapter arg$1;

    private SwitchAccountDialogFragment$SwitchAccountAdapter$$Lambda$1(SwitchAccountAdapter switchAccountAdapter) {
        this.arg$1 = switchAccountAdapter;
    }

    public static OnClickListener lambdaFactory$(SwitchAccountAdapter switchAccountAdapter) {
        return new SwitchAccountDialogFragment$SwitchAccountAdapter$$Lambda$1(switchAccountAdapter);
    }

    public void onClick(View view) {
        ZenDialog.builder().withBodyText("This puts you in a logged-out state, without removing this account from the account switcher. This feature is only enabled on pre-release builds.").withSingleButton(C0880R.string.okay, 0, (Fragment) null).create().show(SwitchAccountDialogFragment.this.getFragmentManager(), (String) null);
    }
}
