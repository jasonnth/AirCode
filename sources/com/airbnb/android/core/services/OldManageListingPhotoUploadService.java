package com.airbnb.android.core.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.events.ListingEvent.ListingUpdatedEvent;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.DeprecatedDeletingPhotoUploadRequest;
import com.airbnb.android.core.responses.DeprecatedPhotoUploadResponse;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoUploadNotificationUtil;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.List;

public class OldManageListingPhotoUploadService extends IntentService {
    private static final String INTENT_EXTRA_LISTING_ID = "listing_id";
    private static final String INTENT_EXTRA_PHOTO_PATH = "photo_path";
    private static final int NOTIFICATION_ID = 42;
    private final NonResubscribableRequestListener<DeprecatedPhotoUploadResponse> listener = new NonResubscribableRequestListener<DeprecatedPhotoUploadResponse>() {
        public void onResponse(DeprecatedPhotoUploadResponse response) {
            OldManageListingPhotoUploadService.this.stopForeground(true);
            OldManageListingPhotoUploadService.this.mListing = (Listing) response.listings.get(0);
            synchronized (OldManageListingPhotoUploadService.this.mLock) {
                OldManageListingPhotoUploadService.this.mLock.notifyAll();
            }
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            OldManageListingPhotoUploadService.this.stopForeground(true);
            OldManageListingPhotoUploadService.this.mListing = null;
            synchronized (OldManageListingPhotoUploadService.this.mLock) {
                OldManageListingPhotoUploadService.this.mLock.notifyAll();
            }
        }
    };
    AirbnbApi mAirbnbApi;
    Bus mBus;
    private final Handler mHandler = new Handler();
    ImageUtils mImageUtils;
    /* access modifiers changed from: private */
    public Listing mListing;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();

    public class OldManageListingPhotoUploadEvent {
        public final int mCount;
        public final Listing mListing;

        public OldManageListingPhotoUploadEvent(Listing listing, int count) {
            this.mListing = listing;
            this.mCount = count;
        }
    }

    public class OldManageListingPhotoUploadStartedEvent {
        public OldManageListingPhotoUploadStartedEvent() {
        }
    }

    public static Intent intentForUpload(Context context, List<String> files, long listingId) {
        return new Intent(context, OldManageListingPhotoUploadService.class).putStringArrayListExtra("photo_path", new ArrayList(files)).putExtra("listing_id", listingId);
    }

    public static Intent intentForUpload(Context context, String file, long id) {
        List<String> fileList = new ArrayList<>();
        fileList.add(file);
        return intentForUpload(context, fileList, id);
    }

    public OldManageListingPhotoUploadService() {
        super("OldManageListingPhotoUploadService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        ((CoreGraph) CoreApplication.instance(this).component()).inject(this);
        List<String> filePaths = intent.getStringArrayListExtra("photo_path");
        long listingId = intent.getLongExtra("listing_id", -1);
        if (TextUtils.isEmpty((CharSequence) filePaths.get(0)) || listingId < 0) {
            throw new IllegalArgumentException();
        }
        this.mHandler.post(OldManageListingPhotoUploadService$$Lambda$1.lambdaFactory$(this));
        startForeground(42, PhotoUploadNotificationUtil.forUploading(this, (String) filePaths.get(0)));
        DeprecatedDeletingPhotoUploadRequest.createRequest(listingId, filePaths).withListener(this.listener).execute(NetworkUtil.singleFireExecutor());
        synchronized (this.mLock) {
            try {
                this.mLock.wait();
            } catch (InterruptedException e) {
            }
        }
        this.mHandler.post(OldManageListingPhotoUploadService$$Lambda$2.lambdaFactory$(this, filePaths));
    }

    static /* synthetic */ void lambda$onHandleIntent$1(OldManageListingPhotoUploadService oldManageListingPhotoUploadService, List filePaths) {
        oldManageListingPhotoUploadService.mBus.post(new OldManageListingPhotoUploadEvent(oldManageListingPhotoUploadService.mListing, filePaths.size()));
        if (oldManageListingPhotoUploadService.mListing != null) {
            oldManageListingPhotoUploadService.mBus.post(new ListingUpdatedEvent(oldManageListingPhotoUploadService.mListing));
        }
    }
}
