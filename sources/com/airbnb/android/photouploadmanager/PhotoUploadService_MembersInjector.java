package com.airbnb.android.photouploadmanager;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class PhotoUploadService_MembersInjector implements MembersInjector<PhotoUploadService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PhotoUploadService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<PhotoUploadManager> photoUploadManagerProvider;

    public PhotoUploadService_MembersInjector(Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        if ($assertionsDisabled || photoUploadManagerProvider2 != null) {
            this.photoUploadManagerProvider = photoUploadManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PhotoUploadService> create(Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        return new PhotoUploadService_MembersInjector(photoUploadManagerProvider2);
    }

    public void injectMembers(PhotoUploadService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.photoUploadManager = (PhotoUploadManager) this.photoUploadManagerProvider.get();
    }

    public static void injectPhotoUploadManager(PhotoUploadService instance, Provider<PhotoUploadManager> photoUploadManagerProvider2) {
        instance.photoUploadManager = (PhotoUploadManager) photoUploadManagerProvider2.get();
    }
}
