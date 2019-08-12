package com.airbnb.android.core.services;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.utils.ImageUtils;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OldManageListingPhotoUploadService_MembersInjector implements MembersInjector<OldManageListingPhotoUploadService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!OldManageListingPhotoUploadService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<Bus> mBusProvider;
    private final Provider<ImageUtils> mImageUtilsProvider;

    public OldManageListingPhotoUploadService_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<ImageUtils> mImageUtilsProvider2, Provider<Bus> mBusProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            if ($assertionsDisabled || mImageUtilsProvider2 != null) {
                this.mImageUtilsProvider = mImageUtilsProvider2;
                if ($assertionsDisabled || mBusProvider2 != null) {
                    this.mBusProvider = mBusProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<OldManageListingPhotoUploadService> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<ImageUtils> mImageUtilsProvider2, Provider<Bus> mBusProvider2) {
        return new OldManageListingPhotoUploadService_MembersInjector(mAirbnbApiProvider2, mImageUtilsProvider2, mBusProvider2);
    }

    public void injectMembers(OldManageListingPhotoUploadService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
        instance.mImageUtils = (ImageUtils) this.mImageUtilsProvider.get();
        instance.mBus = (Bus) this.mBusProvider.get();
    }

    public static void injectMAirbnbApi(OldManageListingPhotoUploadService instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }

    public static void injectMImageUtils(OldManageListingPhotoUploadService instance, Provider<ImageUtils> mImageUtilsProvider2) {
        instance.mImageUtils = (ImageUtils) mImageUtilsProvider2.get();
    }

    public static void injectMBus(OldManageListingPhotoUploadService instance, Provider<Bus> mBusProvider2) {
        instance.mBus = (Bus) mBusProvider2.get();
    }
}
