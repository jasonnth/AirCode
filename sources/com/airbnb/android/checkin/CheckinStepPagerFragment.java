package com.airbnb.android.checkin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.requests.SendCheckedInNotificationRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;
import p032rx.Observer;

public class CheckinStepPagerFragment extends AirFragment {
    private static final String ARG_CHECKIN_GUIDE = "arg_checkin_guide";
    private static final String ARG_IS_EXAMPLE = "arg_is_example";
    private static final String ARG_IS_PREVIEW = "arg_is_preview";
    private static final String ARG_STARTING_STEP = "arg_starting_step";
    public static final String TAG = CheckinStepPagerFragment.class.getSimpleName();
    @BindView
    FixedActionFooter actionFooter;
    private CheckinPagerAdapter adapter;
    final RequestListener<BaseResponse> checkInNotificationListener = new C0699RL().onResponse(CheckinStepPagerFragment$$Lambda$1.lambdaFactory$(this)).onError(CheckinStepPagerFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    int currPosition;
    @BindView
    TabLayout dotsIndicator;
    @State
    CheckInGuide guide;
    GuestCheckInJitneyLogger jitneyLogger;
    private final OnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener() {
        public void onPageSelected(int position) {
            CheckinStepPagerFragment.this.currPosition = position;
            CheckinStepPagerFragment.this.updateHeaderAndFooter(position);
        }
    };
    @BindView
    ViewPager stepPager;
    @BindView
    AirToolbar toolbar;

    public static CheckinStepPagerFragment forCheckinGuidePreview(CheckInGuide guide2, int startingStepNumber) {
        return (CheckinStepPagerFragment) ((FragmentBundleBuilder) forCheckinGuideBuilder(guide2, startingStepNumber).putBoolean(ARG_IS_PREVIEW, true)).build();
    }

    public static CheckinStepPagerFragment forCheckinGuideExample(CheckInGuide guide2, int startingStepNumber) {
        return (CheckinStepPagerFragment) ((FragmentBundleBuilder) forCheckinGuideBuilder(guide2, startingStepNumber).putBoolean(ARG_IS_EXAMPLE, true)).build();
    }

    public static CheckinStepPagerFragment forCheckinGuide(CheckInGuide guide2, int startingStepNumber) {
        return (CheckinStepPagerFragment) forCheckinGuideBuilder(guide2, startingStepNumber).build();
    }

    private static FragmentBundleBuilder<CheckinStepPagerFragment> forCheckinGuideBuilder(CheckInGuide guide2, int startingStepNumber) {
        return (FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CheckinStepPagerFragment()).putParcelable(ARG_CHECKIN_GUIDE, guide2)).putInt(ARG_STARTING_STEP, startingStepNumber);
    }

    private int getStartingStepNumber() {
        return getArguments().getInt(ARG_STARTING_STEP);
    }

    private boolean isExampleGuide() {
        return getArguments().getBoolean(ARG_IS_EXAMPLE);
    }

    private boolean isPreviewGuide() {
        return getArguments().getBoolean(ARG_IS_PREVIEW);
    }

    private boolean isForGuest() {
        return !isExampleGuide() && !isPreviewGuide();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            this.guide = (CheckInGuide) getArguments().getParcelable(ARG_CHECKIN_GUIDE);
        }
        this.adapter = new CheckinPagerAdapter(getChildFragmentManager(), this.guide, isForGuest());
        if (savedInstanceState == null) {
            int startingStepNumber = getStartingStepNumber();
            if (startingStepNumber != -1) {
                this.currPosition = Math.min(startingStepNumber, this.guide.getSteps().size() - 1) + 1;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5618R.layout.fragment_check_in_step_pager, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.stepPager.addOnPageChangeListener(this.pageChangeListener);
        this.stepPager.setAdapter(this.adapter);
        this.dotsIndicator.setupWithViewPager(this.stepPager);
        goToPosition(this.currPosition);
        return view;
    }

    public void setCheckinGuide(CheckInGuide guide2) {
        this.guide = guide2;
        this.adapter.setGuide(guide2);
    }

    public void goToStep(int stepNumber) {
        goToPosition(stepNumber + 1);
    }

    private void goToPosition(int position) {
        this.currPosition = position;
        this.stepPager.setCurrentItem(this.currPosition, false);
        updateHeaderAndFooter(this.currPosition);
    }

    /* access modifiers changed from: private */
    public void updateHeaderAndFooter(int position) {
        boolean isIntroScreen;
        boolean isFinalActionScreen;
        boolean z;
        boolean z2 = true;
        if (position == 0) {
            isIntroScreen = true;
        } else {
            isIntroScreen = false;
        }
        if (position > this.guide.getSteps().size()) {
            isFinalActionScreen = true;
        } else {
            isFinalActionScreen = false;
        }
        this.toolbar.setForegroundColor(ContextCompat.getColor(getContext(), (isIntroScreen || isFinalActionScreen) ? C5618R.color.black : C5618R.color.white));
        if (isIntroScreen) {
            this.actionFooter.setVisibility(0);
            this.actionFooter.setButtonText(C5618R.string.view_check_in_guide_get_started_button);
            this.actionFooter.setButtonOnClickListener(CheckinStepPagerFragment$$Lambda$3.lambdaFactory$(this));
            FixedActionFooter fixedActionFooter = this.actionFooter;
            if (!this.guide.isVisible() || this.guide.getSteps().isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            fixedActionFooter.setButtonEnabled(z);
        } else if (!isFinalActionScreen || this.guide.hasCheckedInAlready()) {
            this.actionFooter.setVisibility(8);
        } else {
            this.actionFooter.setVisibility(0);
            this.actionFooter.setButtonText(C5618R.string.check_in_final_screen_check_in_action);
            this.actionFooter.setButtonOnClickListener(CheckinStepPagerFragment$$Lambda$4.lambdaFactory$(this));
            this.actionFooter.setButtonEnabled(AirDate.today().isSameDay(this.guide.getReservation().getStartDate()));
        }
        TabLayout tabLayout = this.dotsIndicator;
        if (this.actionFooter.getVisibility() == 0) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(tabLayout, z2);
    }

    static /* synthetic */ void lambda$updateHeaderAndFooter$0(CheckinStepPagerFragment checkinStepPagerFragment, View v) {
        checkinStepPagerFragment.stepPager.setCurrentItem(1, true);
        checkinStepPagerFragment.jitneyLogger.logCheckinGuideGuestGetStartedEvent(checkinStepPagerFragment.guide.getListingId());
    }

    static /* synthetic */ void lambda$new$2(CheckinStepPagerFragment checkinStepPagerFragment, BaseResponse response) {
        checkinStepPagerFragment.actionFooter.setButtonLoading(false);
        checkinStepPagerFragment.guide.setCheckedIn();
        checkinStepPagerFragment.adapter.setGuide(checkinStepPagerFragment.guide);
        checkinStepPagerFragment.updateHeaderAndFooter(checkinStepPagerFragment.currPosition);
        checkinStepPagerFragment.jitneyLogger.logCheckinGuideGuestCheckinOkEvent(checkinStepPagerFragment.guide.getListingId());
    }

    static /* synthetic */ void lambda$new$4(CheckinStepPagerFragment checkinStepPagerFragment, AirRequestNetworkException e) {
        checkinStepPagerFragment.actionFooter.setButtonLoading(false);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(checkinStepPagerFragment.getView(), (NetworkException) e, CheckinStepPagerFragment$$Lambda$5.lambdaFactory$(checkinStepPagerFragment));
    }

    /* access modifiers changed from: private */
    public void sendCheckInNotification() {
        this.actionFooter.setButtonLoading(true);
        SendCheckedInNotificationRequest.forConfirmationCode(this.guide.getReservation().getConfirmationCode()).withListener((Observer) this.checkInNotificationListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        if (isExampleGuide()) {
            return NavigationTag.ManageListingCheckinGuideExample;
        }
        if (isPreviewGuide()) {
            return NavigationTag.ManageListingCheckinGuidePreview;
        }
        return NavigationTag.CheckinGuideGuestView;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.guide.getListingId());
    }
}
