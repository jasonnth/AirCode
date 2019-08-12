package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;

public class PhotoMenuUtil {

    private enum Action {
        Retry(C7213R.string.manage_listing_photo_upload_retry),
        Remove(C7213R.string.manage_listing_photo_upload_remove);
        
        public final int optionStringRes;

        private Action(int optionStringRes2) {
            this.optionStringRes = optionStringRes2;
        }
    }

    public static void showFailedMenu(Context context, PhotoUploadManager photoUploadManager, long offlineId) {
        OptionsMenuFactory.forItems(context, (T[]) Action.values()).setTitleResTransformer(PhotoMenuUtil$$Lambda$1.lambdaFactory$()).setListener(PhotoMenuUtil$$Lambda$2.lambdaFactory$(photoUploadManager, offlineId)).buildAndShow();
    }

    /* access modifiers changed from: private */
    public static void actionSelected(PhotoUploadManager photoUploadManager, Action action, long offlineId) {
        switch (action) {
            case Retry:
                photoUploadManager.retryFailedUpload(offlineId);
                return;
            case Remove:
                photoUploadManager.cancelFailedPhotoUpload(offlineId);
                return;
            default:
                throw new UnhandledStateException(action);
        }
    }
}
