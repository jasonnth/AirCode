package com.airbnb.android.checkin.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.checkin.GuestCheckInJitneyLogger;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.requests.GetCheckInGuideListRequest;
import com.airbnb.android.core.requests.GetCheckInGuideRequest;
import com.airbnb.android.core.responses.CheckInGuideListResponse;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import p032rx.Completable;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class CheckInDataSyncService extends Service {
    private static final String TAG = "CheckInDataSyncService";
    CheckInDataTableOpenHelper dbHelper;
    private AtomicInteger guideCounter;
    private final NonResubscribableRequestListener<CheckInGuideResponse> guideListener = new C0699RL().onResponse(CheckInDataSyncService$$Lambda$3.lambdaFactory$(this)).onComplete(CheckInDataSyncService$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
    private final NonResubscribableRequestListener<CheckInGuideListResponse> guidesListListener = new C0699RL().onResponse(CheckInDataSyncService$$Lambda$1.lambdaFactory$(this)).onError(CheckInDataSyncService$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    GuestCheckInJitneyLogger jitneyLogger;
    AirbnbAccountManager mAccountManager;

    public void onCreate() {
        super.onCreate();
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (FeatureToggles.isGuestCheckInGuideOfflineEnabled()) {
            if (!this.mAccountManager.isCurrentUserAuthorized()) {
                Log.d(TAG, "Exist before syncing due to user being logged out.");
            } else {
                this.guideCounter = new AtomicInteger();
                fetchCheckInGuides();
            }
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void fetchCheckInGuides() {
        new GetCheckInGuideListRequest().withListener((Observer) this.guidesListListener).skipCache().execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void handleUpdatedCheckInGuides(ArrayList<CheckInGuide> guides) {
        if (ListUtils.isEmpty((Collection<?>) guides)) {
            this.dbHelper.clearAll();
            stopSelf();
            return;
        }
        Observable.fromCallable(CheckInDataSyncService$$Lambda$5.lambdaFactory$(this)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(CheckInDataSyncService$$Lambda$6.lambdaFactory$(this, guides));
    }

    /* access modifiers changed from: private */
    public void diffRemoteAndLocalGuides(ArrayList<CheckInGuide> remoteGuides, List<CheckInGuideData> localGuides) {
        for (CheckInGuideData checkInGuideData : FluentIterable.from((Iterable<E>) localGuides).filter(CheckInDataSyncService$$Lambda$7.lambdaFactory$(remoteGuides)).toList()) {
            Log.d(TAG, "Removing guide from database for listingId: " + checkInGuideData.listing_id());
            Completable.fromAction(CheckInDataSyncService$$Lambda$8.lambdaFactory$(this, checkInGuideData)).subscribeOn(Schedulers.m4048io()).subscribe();
        }
        List<CheckInGuide> guidesToFetch = FluentIterable.from((Iterable<E>) remoteGuides).filter(CheckInDataSyncService$$Lambda$9.lambdaFactory$(localGuides)).toList();
        if (guidesToFetch.isEmpty()) {
            Log.d(TAG, "No check-in guides to fetch for offlice storage.");
            stopSelf();
            return;
        }
        this.guideCounter.getAndAdd(guidesToFetch.size());
        for (CheckInGuide toFetch : guidesToFetch) {
            Log.d(TAG, "Fetching guide for database for listingId: " + toFetch.getListingId());
            GetCheckInGuideRequest.forListingId(toFetch.getListingId(), LocaleUtil.getDeviceLanguage(this)).withListener((Observer) this.guideListener).skipCache().execute(NetworkUtil.singleFireExecutor());
        }
    }

    /* access modifiers changed from: private */
    public void handleUpdatedCheckGuide(CheckInGuide guide) {
        Log.d(TAG, "Inserting guide to database for listingId: " + guide.getListingId());
        Completable.fromAction(CheckInDataSyncService$$Lambda$10.lambdaFactory$(this, guide)).subscribeOn(Schedulers.m4048io()).subscribe();
        if (guide.getSteps() != null) {
            for (CheckInStep checkinStep : guide.getSteps()) {
                fetchGuideImage(checkinStep.getPictureUrl());
            }
        }
        this.jitneyLogger.logCheckinGuideGuestSuccessfulLoadingOfflineEvent(guide.getListingId(), guide.getVisibleStartingAt());
    }

    /* access modifiers changed from: private */
    public void checkAndStopIfFinished() {
        this.guideCounter.getAndDecrement();
        if (this.guideCounter.get() <= 0) {
            Log.d(TAG, "Fetching and storing of check-in guides complete.");
            stopSelf();
        }
    }

    /* access modifiers changed from: private */
    public void handleNetworkError(AirRequestNetworkException onError) {
        C0715L.m1198w(TAG, "Could not fetch check-in guides for offline syncing.");
        BugsnagWrapper.notify((Throwable) onError);
        stopSelf();
    }

    private void fetchGuideImage(String imageUrl) {
        if (imageUrl != null) {
            AirImageView.getImageBackground(this, imageUrl);
        }
    }

    /* access modifiers changed from: private */
    public static boolean isMissingOrOutdated(List<CheckInGuideData> guideDataList, CheckInGuide guide) {
        CheckInGuideData guideData = (CheckInGuideData) FluentIterable.from((Iterable<E>) guideDataList).firstMatch(CheckInDataSyncService$$Lambda$11.lambdaFactory$(guide)).orNull();
        if (guideData != null && Objects.equal(guideData.updated_at(), guide.getUpdatedAt())) {
            return false;
        }
        return true;
    }

    static /* synthetic */ boolean lambda$isMissingOrOutdated$9(CheckInGuide guide, CheckInGuideData guideDataItem) {
        return guideDataItem.listing_id() == guide.getListingId();
    }

    /* access modifiers changed from: private */
    public static boolean isMissingRemoteGuide(List<CheckInGuide> guides, long listingId) {
        return !FluentIterable.from((Iterable<E>) guides).anyMatch(CheckInDataSyncService$$Lambda$12.lambdaFactory$(listingId));
    }

    static /* synthetic */ boolean lambda$isMissingRemoteGuide$10(long listingId, CheckInGuide guide) {
        return guide.getListingId() == listingId;
    }
}
