package com.airbnb.android.lib.tasks;

import com.airbnb.android.core.utils.ImageUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LocalBitmapForDisplayScalingTask_MembersInjector implements MembersInjector<LocalBitmapForDisplayScalingTask> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocalBitmapForDisplayScalingTask_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ImageUtils> mImageUtilsProvider;

    public LocalBitmapForDisplayScalingTask_MembersInjector(Provider<ImageUtils> mImageUtilsProvider2) {
        if ($assertionsDisabled || mImageUtilsProvider2 != null) {
            this.mImageUtilsProvider = mImageUtilsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<LocalBitmapForDisplayScalingTask> create(Provider<ImageUtils> mImageUtilsProvider2) {
        return new LocalBitmapForDisplayScalingTask_MembersInjector(mImageUtilsProvider2);
    }

    public void injectMembers(LocalBitmapForDisplayScalingTask instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mImageUtils = (ImageUtils) this.mImageUtilsProvider.get();
    }

    public static void injectMImageUtils(LocalBitmapForDisplayScalingTask instance, Provider<ImageUtils> mImageUtilsProvider2) {
        instance.mImageUtils = (ImageUtils) mImageUtilsProvider2.get();
    }
}
