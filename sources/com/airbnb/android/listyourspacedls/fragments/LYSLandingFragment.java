package com.airbnb.android.listyourspacedls.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Space;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.listing.LYSCollection;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.jitney.event.logging.LysLandingPagesType.p140v1.C2377LysLandingPagesType;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.ImpactMarquee;
import com.airbnb.p027n2.components.StandardButtonRow;
import com.airbnb.p027n2.components.StandardButtonRow.ButtonStyle;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ValueAnimatorFactory;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class LYSLandingFragment extends AirFragment {
    private static final int PUBLISH_PAGE_DELAY_MS = 450;
    private static final int SCROLL_DURATION_MS = 800;
    private LYSDataController controller;
    @State
    boolean hasAutoShownPublishAnimation;
    @BindView
    StandardButtonRow landingBasicsRow;
    @BindView
    StandardButtonRow landingBookingRow;
    @BindView
    StandardButtonRow landingMarketingRow;
    @BindView
    ImpactMarquee landingMarquee;
    LYSJitneyLogger lysJitneyLogger;
    @BindView
    AirButton preview;
    @State
    LYSCollection previousMaxReachedCollection;
    @BindView
    AirButton publish;
    @BindView
    VerboseScrollView scrollView;
    private final Animator scrollingAnimator = ValueAnimatorFactory.ofFloat(0.0f, 1.0f).setDuration(800).setAccelerateDecelerateInterpolator().addListener(LYSLandingFragment$$Lambda$1.lambdaFactory$(this)).build();
    @BindView
    Space space;
    @BindView
    AirToolbar toolbar;

    public static LYSLandingFragment newInstance() {
        return new LYSLandingFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.previousMaxReachedCollection = this.controller.getMaxReachedStep().lysCollection;
            this.hasAutoShownPublishAnimation = this.controller.isReadyToPublish();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_landing, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setUpCollectionStatus();
        return view;
    }

    public void onDestroyView() {
        this.scrollingAnimator.cancel();
        super.onDestroyView();
    }

    public void setController(LYSDataController controller2) {
        this.controller = controller2;
    }

    private void setUpCollectionStatus() {
        boolean z;
        C2377LysLandingPagesType page;
        boolean z2;
        boolean hasTransitionedToNewCollection;
        if (this.controller.isListingCreated()) {
            this.preview.setVisibility(0);
            this.space.setVisibility(0);
        }
        LYSCollection maxReachedCollection = this.controller.getMaxReachedStep().lysCollection;
        boolean isPhotoStepCompleted = LYSStep.isPhotoStepCompleted(this.controller.getListing());
        int marketingTextRes = isPhotoStepCompleted ? C7251R.string.lys_dls_landing_collection_status_edit : C7251R.string.lys_dls_landing_collection_status_add_photos;
        switch (maxReachedCollection) {
            case Basics:
                setupRow(this.landingBasicsRow, true, true, C7251R.string.lys_dls_landing_collection_status_continue);
                setupRow(this.landingMarketingRow, false, true, C7251R.string.lys_dls_landing_collection_status_locked);
                setupRow(this.landingBookingRow, false, true, C7251R.string.lys_dls_landing_collection_status_locked);
                page = C2377LysLandingPagesType.Step1;
                break;
            case Marketing:
                setupRow(this.landingBasicsRow, true, false, C7251R.string.lys_dls_landing_collection_status_edit);
                setupRow(this.landingMarketingRow, true, true, C7251R.string.lys_dls_landing_collection_status_continue);
                setupRow(this.landingBookingRow, false, true, C7251R.string.lys_dls_landing_collection_status_locked);
                page = C2377LysLandingPagesType.Step2;
                break;
            case Booking:
                setupRow(this.landingBasicsRow, true, false, C7251R.string.lys_dls_landing_collection_status_edit);
                StandardButtonRow standardButtonRow = this.landingMarketingRow;
                if (!isPhotoStepCompleted) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                setupRow(standardButtonRow, true, z2, marketingTextRes);
                setupRow(this.landingBookingRow, true, true, C7251R.string.lys_dls_landing_collection_status_continue);
                page = C2377LysLandingPagesType.Step3;
                break;
            case Completion:
                setupRow(this.landingBasicsRow, true, false, C7251R.string.lys_dls_landing_collection_status_edit);
                StandardButtonRow standardButtonRow2 = this.landingMarketingRow;
                if (!isPhotoStepCompleted) {
                    z = true;
                } else {
                    z = false;
                }
                setupRow(standardButtonRow2, true, z, marketingTextRes);
                setupRow(this.landingBookingRow, true, false, C7251R.string.lys_dls_landing_collection_status_edit);
                page = C2377LysLandingPagesType.ReadyToPublish;
                break;
            default:
                C2377LysLandingPagesType lysLandingPagesType = C2377LysLandingPagesType.Step1;
                throw new UnhandledStateException(maxReachedCollection);
        }
        logLandingPageView(page);
        boolean readyToPublish = this.controller.isReadyToPublish();
        ViewLibUtils.setVisibleIf(this.publish, readyToPublish);
        if (readyToPublish && !this.hasAutoShownPublishAnimation) {
            this.hasAutoShownPublishAnimation = true;
            new Handler().postDelayed(LYSLandingFragment$$Lambda$2.lambdaFactory$(this), 450);
        }
        if (this.previousMaxReachedCollection != maxReachedCollection) {
            hasTransitionedToNewCollection = true;
        } else {
            hasTransitionedToNewCollection = false;
        }
        this.previousMaxReachedCollection = maxReachedCollection;
        if (maxReachedCollection != LYSCollection.last() && hasTransitionedToNewCollection) {
            this.scrollingAnimator.setStartDelay(getFragmentAnimationDurationMs());
            this.scrollingAnimator.start();
        }
    }

    static /* synthetic */ void lambda$setUpCollectionStatus$1(LYSLandingFragment lYSLandingFragment) {
        if (lYSLandingFragment.isResumed()) {
            lYSLandingFragment.controller.showPublishFragment();
        }
    }

    @OnClick
    public void onClickBasicsRow() {
        this.controller.firstStep(LYSCollection.Basics);
    }

    @OnClick
    public void onClickMarketingRow() {
        this.controller.firstStep(LYSCollection.Marketing);
    }

    @OnClick
    public void onClickBookingRow() {
        this.controller.firstStep(LYSCollection.Booking);
    }

    @OnClick
    public void onClickPreview() {
        startActivity(P3ActivityIntents.withListing(getActivity(), this.controller.getListing(), P3Arguments.FROM_MANAGE_LISTING_OR_LYS));
    }

    @OnClick
    public void onClickPublish() {
        this.controller.showPublishFragment();
    }

    private long getFragmentAnimationDurationMs() {
        View view = getView();
        if (view == null) {
            return 0;
        }
        Animation fragmentAnimation = view.getAnimation();
        if (fragmentAnimation != null) {
            return fragmentAnimation.getDuration();
        }
        return 0;
    }

    private static void setupRow(StandardButtonRow row, boolean enabled, boolean isPrimary, int stringRes) {
        row.setButtonText(stringRes);
        row.setButtonStyle(isPrimary ? ButtonStyle.Primary : ButtonStyle.Secondary);
        row.setEnabled(enabled);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSLanding;
    }

    private void logLandingPageView(C2377LysLandingPagesType page) {
        long userId = 0;
        long listingId = 0;
        if (this.mAccountManager.getCurrentUser() != null) {
            userId = this.mAccountManager.getCurrentUser().getId();
        }
        if (this.controller.isListingCreated()) {
            listingId = this.controller.getListing().getId();
        }
        this.lysJitneyLogger.logLandingPageView(Long.valueOf(userId), Long.valueOf(listingId), page);
    }
}
