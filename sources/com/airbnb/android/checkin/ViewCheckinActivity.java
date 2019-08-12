package com.airbnb.android.checkin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.checkin.data.CheckInDataTableOpenHelper;
import com.airbnb.android.checkin.data.CheckInGuideData;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.CheckinIntents;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.models.CheckinArguments;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.airbnb.android.core.requests.GetCheckInGuideExampleRequest;
import com.airbnb.android.core.requests.GetCheckInGuideRequest;
import com.airbnb.android.core.responses.CheckInGuideExamplesResponse;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.components.RefreshLoader;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Completable;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class ViewCheckinActivity extends AirActivity {
    public static final int STARTING_STEP_INTRO_SCREEN = -1;
    private static final String TAG = "ViewCheckinActivity";
    @State
    CheckInGuide checkinGuide;
    private List<CheckInStep> checkinSteps;
    private Subscription databaseSubscription;
    CheckInDataTableOpenHelper dbHelper;
    final RequestListener<CheckInGuideExamplesResponse> getExampleGuideListener = new C0699RL().onResponse(ViewCheckinActivity$$Lambda$3.lambdaFactory$(this)).onError(ViewCheckinActivity$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<CheckInGuideResponse> getGuideListener = new C0699RL().onResponse(ViewCheckinActivity$$Lambda$1.lambdaFactory$(this)).onError(ViewCheckinActivity$$Lambda$2.lambdaFactory$(this)).build();
    private List<String> imageUrls;
    GuestCheckInJitneyLogger jitneyLogger;
    @BindView
    RefreshLoader loader;
    private CheckinStepPagerFragment pagerFragment;
    @State
    boolean showLocalizedGuide;

    static /* synthetic */ void lambda$new$2(ViewCheckinActivity viewCheckinActivity, AirRequestNetworkException e) {
        viewCheckinActivity.loader.setVisibility(8);
        if (viewCheckinActivity.checkinGuide == null) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(viewCheckinActivity.findViewById(C5618R.C5620id.root_container), (NetworkException) e, ViewCheckinActivity$$Lambda$15.lambdaFactory$(viewCheckinActivity));
        }
    }

    static /* synthetic */ void lambda$new$5(ViewCheckinActivity viewCheckinActivity, AirRequestNetworkException e) {
        viewCheckinActivity.loader.setVisibility(8);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(viewCheckinActivity.findViewById(C5618R.C5620id.root_container), (NetworkException) e, ViewCheckinActivity$$Lambda$14.lambdaFactory$(viewCheckinActivity));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5618R.layout.activity_view_checkin);
        ButterKnife.bind((Activity) this);
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
        if (this.checkinGuide != null) {
            showPagerForGuide();
        } else if (BuildHelper.isFuture() && getListingId() == 0) {
            this.checkinGuide = CheckInGuide.getSampleCheckinGuide();
            showPagerForGuide();
        } else if (!this.requestManager.hasRequest((BaseRequestListener<T>) this.getGuideListener, GetCheckInGuideRequest.class)) {
            fetchGuide();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.databaseSubscription != null && !this.databaseSubscription.isUnsubscribed()) {
            this.databaseSubscription.unsubscribe();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1115 && resultCode == -1) {
            String imageUri = data.getStringExtra(ImageViewerActivity.ARG_RETURN_CURRENT_URL);
            if (imageUri != null) {
                this.pagerFragment.goToStep(ListUtils.firstIndexOf(this.checkinSteps, ViewCheckinActivity$$Lambda$5.lambdaFactory$(imageUri)));
            }
        }
    }

    public void showFullScreenImageViewer(CheckInStep step) {
        startActivityForResult(ImageViewerActivity.newIntent(this, this.imageUrls, this.imageUrls.indexOf(step.getPictureUrl()), getListingId()), ImageViewerActivity.REQUEST_CODE);
    }

    public void setShowTranslatedNote(boolean showTranslatedNote) {
        this.showLocalizedGuide = showTranslatedNote;
    }

    public CheckInGuide getGuide() {
        return this.checkinGuide;
    }

    private long getListingId() {
        if (getIntent().getExtras().containsKey(ReactExposedActivityParamsConstants.KEY_ARGUMENT)) {
            return ((CheckinArguments) getIntent().getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT)).listingId();
        }
        return getIntent().getLongExtra(CheckinIntents.INTENT_EXTRA_CHECKIN_LISTING_ID, -1);
    }

    private int getStartingStepNumber() {
        return getIntent().getExtras().getInt(CheckinIntents.INTENT_EXTRA_STARTING_STEP, -1);
    }

    private boolean isForSample() {
        return getIntent().getBooleanExtra(CheckinIntents.INTENT_EXTRA_SAMPLE, false);
    }

    private boolean isForPreview() {
        return getIntent().getBooleanExtra(CheckinIntents.INTENT_EXTRA_PREVIEW, false);
    }

    /* access modifiers changed from: private */
    public void fetchGuide() {
        this.loader.setVisibility(0);
        if (isForSample()) {
            GetCheckInGuideExampleRequest.forListingId(getListingId(), LocaleUtil.getDeviceLanguage(this)).withListener((Observer) this.getExampleGuideListener).execute(this.requestManager);
        } else if (isForPreview() || !FeatureToggles.isGuestCheckInGuideOfflineEnabled()) {
            getGuideFromServer();
        } else {
            getGuideFromDatabase();
        }
    }

    private void getGuideFromDatabase() {
        this.databaseSubscription = Observable.fromCallable(ViewCheckinActivity$$Lambda$6.lambdaFactory$(this)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).map(ViewCheckinActivity$$Lambda$7.lambdaFactory$()).subscribe(ViewCheckinActivity$$Lambda$8.lambdaFactory$(this), ViewCheckinActivity$$Lambda$9.lambdaFactory$(this), ViewCheckinActivity$$Lambda$10.lambdaFactory$(this));
    }

    static /* synthetic */ CheckInGuide lambda$getGuideFromDatabase$8(CheckInGuideData result) {
        if (result != null) {
            return result.check_in_guide();
        }
        return null;
    }

    static /* synthetic */ void lambda$getGuideFromDatabase$9(ViewCheckinActivity viewCheckinActivity, CheckInGuide result) {
        viewCheckinActivity.checkinGuide = result;
        if (viewCheckinActivity.checkinGuide != null) {
            viewCheckinActivity.showPagerForGuide();
        }
    }

    /* access modifiers changed from: private */
    public void handleDatabaseGuideNotFound(Throwable error) {
        C0715L.m1198w(TAG, "Failed to fetch check-in guide from database.");
        BugsnagWrapper.notify(error);
    }

    /* access modifiers changed from: private */
    public void getGuideFromServer() {
        GetCheckInGuideRequest.forListingId(getListingId(), LocaleUtil.getDeviceLanguage(this)).withListener((Observer) this.getGuideListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleGuideServerResponse(CheckInGuide guide) {
        if (this.checkinGuide == null) {
            this.checkinGuide = guide;
            showPagerForGuide();
        } else if (!Objects.equal(this.checkinGuide.getUpdatedAt(), guide.getUpdatedAt())) {
            this.checkinGuide = guide;
            showPagerForGuide();
            this.pagerFragment.setCheckinGuide(this.checkinGuide);
        }
        if (guide != null) {
            Completable.fromAction(ViewCheckinActivity$$Lambda$11.lambdaFactory$(this, guide)).subscribeOn(Schedulers.m4048io()).subscribe();
        }
    }

    private void showPagerForGuide() {
        this.loader.setVisibility(8);
        initPhotoInformation(this.checkinGuide.getSteps());
        this.pagerFragment = (CheckinStepPagerFragment) getSupportFragmentManager().findFragmentByTag(CheckinStepPagerFragment.TAG);
        if (this.pagerFragment == null) {
            this.pagerFragment = getPagerFragment();
            showFragment(this.pagerFragment, C5618R.C5620id.content_container, FragmentTransitionType.None, false, CheckinStepPagerFragment.TAG);
        }
    }

    private void initPhotoInformation(List<CheckInStep> steps) {
        this.checkinSteps = new ArrayList(steps);
        this.imageUrls = FluentIterable.from((Iterable<E>) steps).filter(ViewCheckinActivity$$Lambda$12.lambdaFactory$()).transform(ViewCheckinActivity$$Lambda$13.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$initPhotoInformation$11(CheckInStep step) {
        return step != null && !TextUtils.isEmpty(step.getPictureUrl());
    }

    private CheckinStepPagerFragment getPagerFragment() {
        if (isForSample()) {
            return CheckinStepPagerFragment.forCheckinGuideExample(this.checkinGuide, getStartingStepNumber());
        }
        if (isForPreview()) {
            return CheckinStepPagerFragment.forCheckinGuidePreview(this.checkinGuide, getStartingStepNumber());
        }
        return CheckinStepPagerFragment.forCheckinGuide(this.checkinGuide, getStartingStepNumber());
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
