package com.airbnb.android.photouploadmanager;

import com.google.common.base.Function;

final /* synthetic */ class PhotoUploadManager$$Lambda$7 implements Function {
    private static final PhotoUploadManager$$Lambda$7 instance = new PhotoUploadManager$$Lambda$7();

    private PhotoUploadManager$$Lambda$7() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((PhotoUploadTransaction) obj).offlineId);
    }
}
