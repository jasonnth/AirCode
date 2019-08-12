package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostsInfoDialog$$Lambda$1 implements Action1 {
    private final HostsInfoDialog arg$1;

    private HostsInfoDialog$$Lambda$1(HostsInfoDialog hostsInfoDialog) {
        this.arg$1 = hostsInfoDialog;
    }

    public static Action1 lambdaFactory$(HostsInfoDialog hostsInfoDialog) {
        return new HostsInfoDialog$$Lambda$1(hostsInfoDialog);
    }

    public void call(Object obj) {
        this.arg$1.hostsAdapter.setPrimaryHost(((UserResponse) obj).user);
    }
}
