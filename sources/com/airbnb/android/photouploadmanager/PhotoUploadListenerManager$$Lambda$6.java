package com.airbnb.android.photouploadmanager;

import p032rx.functions.Action1;

final /* synthetic */ class PhotoUploadListenerManager$$Lambda$6 implements Runnable {
    private final PhotoUploadListenerManager arg$1;
    private final String arg$2;
    private final Action1 arg$3;

    private PhotoUploadListenerManager$$Lambda$6(PhotoUploadListenerManager photoUploadListenerManager, String str, Action1 action1) {
        this.arg$1 = photoUploadListenerManager;
        this.arg$2 = str;
        this.arg$3 = action1;
    }

    public static Runnable lambdaFactory$(PhotoUploadListenerManager photoUploadListenerManager, String str, Action1 action1) {
        return new PhotoUploadListenerManager$$Lambda$6(photoUploadListenerManager, str, action1);
    }

    public void run() {
        this.arg$1.notifyTargetListenersOnMainThread(this.arg$2, this.arg$3);
    }
}
