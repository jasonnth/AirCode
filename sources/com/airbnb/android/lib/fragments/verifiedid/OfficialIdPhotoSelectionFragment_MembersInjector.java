package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.utils.ImageUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OfficialIdPhotoSelectionFragment_MembersInjector implements MembersInjector<OfficialIdPhotoSelectionFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!OfficialIdPhotoSelectionFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ImageUtils> mImageUtilsProvider;

    public OfficialIdPhotoSelectionFragment_MembersInjector(Provider<ImageUtils> mImageUtilsProvider2) {
        if ($assertionsDisabled || mImageUtilsProvider2 != null) {
            this.mImageUtilsProvider = mImageUtilsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<OfficialIdPhotoSelectionFragment> create(Provider<ImageUtils> mImageUtilsProvider2) {
        return new OfficialIdPhotoSelectionFragment_MembersInjector(mImageUtilsProvider2);
    }

    public void injectMembers(OfficialIdPhotoSelectionFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mImageUtils = (ImageUtils) this.mImageUtilsProvider.get();
    }

    public static void injectMImageUtils(OfficialIdPhotoSelectionFragment instance, Provider<ImageUtils> mImageUtilsProvider2) {
        instance.mImageUtils = (ImageUtils) mImageUtilsProvider2.get();
    }
}
