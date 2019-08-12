package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ManageListingDataController_MembersInjector implements MembersInjector<ManageListingDataController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ManageListingDataController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<PhotoUploadManager> uploadManagerProvider;

    public ManageListingDataController_MembersInjector(Provider<PhotoUploadManager> uploadManagerProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || uploadManagerProvider2 != null) {
            this.uploadManagerProvider = uploadManagerProvider2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ManageListingDataController> create(Provider<PhotoUploadManager> uploadManagerProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new ManageListingDataController_MembersInjector(uploadManagerProvider2, accountManagerProvider2);
    }

    public void injectMembers(ManageListingDataController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.uploadManager = (PhotoUploadManager) this.uploadManagerProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectUploadManager(ManageListingDataController instance, Provider<PhotoUploadManager> uploadManagerProvider2) {
        instance.uploadManager = (PhotoUploadManager) uploadManagerProvider2.get();
    }

    public static void injectAccountManager(ManageListingDataController instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
