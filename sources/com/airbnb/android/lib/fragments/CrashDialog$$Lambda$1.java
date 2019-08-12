package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.utils.MiscUtils;

final /* synthetic */ class CrashDialog$$Lambda$1 implements OnLongClickListener {
    private final CrashDialog arg$1;
    private final String arg$2;

    private CrashDialog$$Lambda$1(CrashDialog crashDialog, String str) {
        this.arg$1 = crashDialog;
        this.arg$2 = str;
    }

    public static OnLongClickListener lambdaFactory$(CrashDialog crashDialog, String str) {
        return new CrashDialog$$Lambda$1(crashDialog, str);
    }

    public boolean onLongClick(View view) {
        return MiscUtils.copyToClipboard(this.arg$1.getActivity(), this.arg$2);
    }
}
