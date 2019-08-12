package com.airbnb.android.listyourspacedls;

import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LYSDataController_MembersInjector implements MembersInjector<LYSDataController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LYSDataController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<LYSJitneyLogger> jitneyLoggerProvider;
    private final Provider<PhotoUploadManager> uploadManagerProvider;

    public LYSDataController_MembersInjector(Provider<PhotoUploadManager> uploadManagerProvider2, Provider<LYSJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || uploadManagerProvider2 != null) {
            this.uploadManagerProvider = uploadManagerProvider2;
            if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                this.jitneyLoggerProvider = jitneyLoggerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<LYSDataController> create(Provider<PhotoUploadManager> uploadManagerProvider2, Provider<LYSJitneyLogger> jitneyLoggerProvider2) {
        return new LYSDataController_MembersInjector(uploadManagerProvider2, jitneyLoggerProvider2);
    }

    public void injectMembers(LYSDataController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.uploadManager = (PhotoUploadManager) this.uploadManagerProvider.get();
        instance.jitneyLogger = (LYSJitneyLogger) this.jitneyLoggerProvider.get();
    }

    public static void injectUploadManager(LYSDataController instance, Provider<PhotoUploadManager> uploadManagerProvider2) {
        instance.uploadManager = (PhotoUploadManager) uploadManagerProvider2.get();
    }

    public static void injectJitneyLogger(LYSDataController instance, Provider<LYSJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (LYSJitneyLogger) jitneyLoggerProvider2.get();
    }
}
