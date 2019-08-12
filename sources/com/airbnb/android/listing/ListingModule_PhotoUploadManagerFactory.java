package com.airbnb.android.listing;

import android.content.Context;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ListingModule_PhotoUploadManagerFactory implements Factory<PhotoUploadManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingModule_PhotoUploadManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public ListingModule_PhotoUploadManagerFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public PhotoUploadManager get() {
        return (PhotoUploadManager) Preconditions.checkNotNull(ListingModule.photoUploadManager((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PhotoUploadManager> create(Provider<Context> contextProvider2) {
        return new ListingModule_PhotoUploadManagerFactory(contextProvider2);
    }
}
