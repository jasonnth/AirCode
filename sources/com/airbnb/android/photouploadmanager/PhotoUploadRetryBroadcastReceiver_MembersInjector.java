package com.airbnb.android.photouploadmanager;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class PhotoUploadRetryBroadcastReceiver_MembersInjector implements MembersInjector<PhotoUploadRetryBroadcastReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PhotoUploadRetryBroadcastReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<PhotoUploadManager> photoUploadManagerProvider;

    public PhotoUploadRetryBroadcastReceiver_MembersInjector(Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        if ($assertionsDisabled || photoUploadManagerProvider2 != null) {
            this.photoUploadManagerProvider = photoUploadManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PhotoUploadRetryBroadcastReceiver> create(Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        return new PhotoUploadRetryBroadcastReceiver_MembersInjector(photoUploadManagerProvider2);
    }

    public void injectMembers(PhotoUploadRetryBroadcastReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.photoUploadManager = (PhotoUploadManager) this.photoUploadManagerProvider.get();
    }

    public static void injectPhotoUploadManager(PhotoUploadRetryBroadcastReceiver instance, Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        instance.photoUploadManager = (PhotoUploadManager) photoUploadManagerProvider2.get();
    }
}
