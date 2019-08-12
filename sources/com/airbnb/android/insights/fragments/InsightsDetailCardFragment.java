package com.airbnb.android.insights.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.constants.ManageListingArgConstants;
import com.airbnb.android.core.enums.ManageListingPriceType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.ActionCardCopy;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Insight.ConversionType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.hostcalendar.fragments.ModalSingleCalendarFragment;
import com.airbnb.android.hostcalendar.fragments.MultiDayPriceTipsFragment;
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.insights.InsightEpoxyModel.LoadingState;
import com.airbnb.android.insights.InsightsActivity;
import com.airbnb.android.insights.InsightsDataController;
import com.airbnb.android.insights.InsightsDataController.InsightsStateChangeListener;
import com.airbnb.android.insights.fragments.details.InsightsDiscountsFragment;
import com.airbnb.android.insights.fragments.details.InsightsDiscountsFragment.LengthOfStayListener;
import com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment;
import com.airbnb.android.listing.fragments.TipFragment;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.animation.ExpandAnimation;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class InsightsDetailCardFragment extends AirFragment implements InsightsStateChangeListener, LengthOfStayListener {
    private static final String ARG_INSIGHT = "insight";
    private static final int TOGGLE_SAVE_BUTTON_DURATION_MILLIS = 300;
    @BindView
    AirButton actionButton;
    @State
    boolean addedMainFragment;
    @BindView
    CoordinatorLayout container;
    private InsightsDataController dataController;
    @BindView
    TextRow explanationTextRow;
    @BindView
    AirButton finishButton;
    @BindView
    FrameLayout fragmentHolder;
    @BindView
    LinearLayout infoContainer;
    @State
    boolean infoHidden;
    @BindView
    LoadingView loadingView;
    @State
    LoadingState state = LoadingState.DEFAULT;
    private Insight story;
    @BindView
    AirButton undoButton;

    public static InsightsDetailCardFragment newInstance(Insight insight) {
        return (InsightsDetailCardFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsDetailCardFragment()).putParcelable(ARG_INSIGHT, insight)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        View v = inflater.inflate(C6552R.layout.fragment_insights_detail_card, container2, false);
        bindViews(v);
        this.story = (Insight) getArguments().getParcelable(ARG_INSIGHT);
        this.dataController = ((InsightsActivity) getActivity()).getDataController();
        this.dataController.addListener(this);
        init();
        if (this.infoHidden) {
            this.infoContainer.post(InsightsDetailCardFragment$$Lambda$1.lambdaFactory$(this));
        }
        return v;
    }

    public void onResume() {
        super.onResume();
        this.dataController.addListener(this);
    }

    public Listing getListing() {
        return this.story.getListing();
    }

    public Insight getInsight() {
        return this.story;
    }

    private void init() {
        if (!this.addedMainFragment) {
            setMainContent();
        }
        setFooterContent();
    }

    @OnClick
    public void onActionButtonClicked() {
        this.dataController.handleUpdateRequest(this.story);
    }

    @OnClick
    public void onUndoButtonClicked() {
        this.dataController.sendUndoRequest(this.story);
    }

    @OnClick
    public void onFinishButtonClicked() {
        ((InsightsParentFragment) getParentFragment()).returnToCarousel();
    }

    private void setFooterContent() {
        ActionCardCopy copy = this.story.getCopies();
        String explanationText = copy.getFollowupTitle();
        switch (this.state) {
            case DEFAULT:
                this.actionButton.setVisibility(0);
                this.undoButton.setVisibility(8);
                this.finishButton.setVisibility(8);
                this.actionButton.setState(AirButton.State.Normal);
                this.undoButton.setState(AirButton.State.Normal);
                this.actionButton.setText(copy.getFollowupCta());
                break;
            case PRIMARY_ACTION_LOADING:
                int actionButtonWidth = this.actionButton.getWidth();
                this.actionButton.setState(AirButton.State.Loading);
                this.actionButton.setWidth(actionButtonWidth);
                break;
            case UNDO_ACTION_LOADING:
                int undoButtonWidth = this.undoButton.getWidth();
                this.undoButton.setState(AirButton.State.Loading);
                this.undoButton.setWidth(undoButtonWidth);
                explanationText = copy.getConfirmationTitle();
                break;
            case DONE:
                this.actionButton.setVisibility(8);
                this.undoButton.setVisibility(0);
                this.finishButton.setVisibility(0);
                this.undoButton.setState(AirButton.State.Normal);
                explanationText = copy.getConfirmationTitle();
                break;
            default:
                throw new IllegalStateException("State can not be " + this.state);
        }
        this.explanationTextRow.setText((CharSequence) explanationText);
    }

    public void onStateChange(LoadingState newState, Insight insight, boolean hasError) {
        handleUnblockNightsStateChange(newState, insight);
        handleCalendarDayChange(insight);
        handleSetBasePriceChange(newState, insight, hasError);
        handleDiscountsChange(newState, insight, hasError);
        this.state = newState;
        setFooterContent();
    }

    private void handleCalendarDayChange(Insight insight) {
        if (insight.getStoryConversionType() == ConversionType.SetPricingTipForMonth) {
            int month = insight.getConversionPayload().getMonth().getMonthOfYear();
            long listingId = insight.getListingId();
            if (this.dataController.hasCalendarDays(month, listingId) && !this.addedMainFragment) {
                MultiDayPriceTipsFragment priceTipFragment = MultiDayPriceTipsFragment.newInstance(this.dataController.getCalendarDays(month, listingId), false, true);
                priceTipFragment.setOnBackListener((OnBackListener) getParentFragment());
                setMainFragment(priceTipFragment);
            }
            this.loadingView.setVisibility(8);
        }
    }

    private void handleUnblockNightsStateChange(LoadingState newState, Insight insight) {
        if (insight.getStoryConversionType() == ConversionType.UnblockNightsForDateRange && !newState.isLoading()) {
            ((ModalSingleCalendarFragment) getChildFragmentManager().findFragmentById(C6552R.C6554id.fragment_holder)).resetCalendarCacheAndRedraw();
        }
    }

    private void handleSetBasePriceChange(LoadingState newState, Insight insight, boolean hasError) {
        if (insight.getStoryConversionType() == ConversionType.SetBasePrice && !newState.isLoading()) {
            if (hasError) {
                this.loadingView.setVisibility(8);
            } else {
                setNightlyPriceFragment(insight);
            }
        }
    }

    private void handleDiscountsChange(LoadingState newState, Insight insight, boolean hasError) {
        if (insight.getStoryConversionType() == ConversionType.SetWeeklyDiscount && !newState.isLoading()) {
            if (hasError) {
                this.loadingView.setVisibility(8);
            } else {
                setDiscountsFragment(insight);
            }
        }
    }

    public void showModal(Fragment fragment) {
        ((InsightsParentFragment) getParentFragment()).showModal(fragment);
    }

    public void showSnackbarError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.container, e);
    }

    public void onPause() {
        this.dataController.removeListener(this);
        ((InsightsActivity) getActivity()).setToolbarVisible(false);
        super.onPause();
    }

    public void setMainContent() {
        Listing listing = this.story.getListing();
        Insight insight = getInsight();
        InsightsActivity insightsActivity = (InsightsActivity) getActivity();
        this.loadingView.setVisibility(0);
        switch (this.story.getStoryConversionType()) {
            case UnblockNightsForDateRange:
                setCalendarFragment(listing);
                return;
            case SetSmartPricingMinPrice:
                Fragment editPriceFragment = Fragments.editPrice();
                setActivityToolbar(insightsActivity);
                editPriceFragment.setArguments(((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putParcelable("listing", listing)).putInt(ManageListingArgConstants.ARG_EXISTING_PRICE, 0)).putInt(ManageListingArgConstants.ARG_SUGGESTED_PRICE, this.story.getConversionPayload().getIntegerValue())).putInt(ManageListingArgConstants.ARG_PRICE_TYPE, ManageListingPriceType.DemandBasedMinimum.ordinal())).putBoolean(ManageListingArgConstants.ARG_FOR_ACTION_CARD, true)).toBundle());
                setFragmentHolderBackgroundColor(C6552R.color.c_foggy_white);
                setMainFragment(editPriceFragment);
                return;
            case SetWeeklyDiscount:
                if (insight.getDynamicPricingControl() == null || this.dataController.getAveragePrices(listing.getId()) == null) {
                    this.dataController.fetchPricingControlAndAveragePrices(insight);
                    return;
                } else {
                    setDiscountsFragment(insight);
                    return;
                }
            case SetPricingTipForMonth:
                this.dataController.fetchCalendarDays(getInsight(), false);
                return;
            case SetSmartPromotion:
                setCalendarFragment(listing);
                return;
            case SetBasePrice:
                if (insight.getDynamicPricingControl() == null) {
                    this.dataController.fetchPricingControl(insight, false);
                    return;
                } else {
                    setNightlyPriceFragment(insight);
                    return;
                }
            default:
                throw new IllegalStateException("Story type can not be " + this.story.getStoryType());
        }
    }

    private void setNightlyPriceFragment(Insight insight) {
        setMainFragment(InsightsNightlyPriceFragment.newInstance(insight.getListing(), insight.getDynamicPricingControl()));
    }

    public void setDiscountsFragment(Insight insight) {
        setMainFragment(InsightsDiscountsFragment.newInstance(insight.getListing(), insight.getDynamicPricingControl(), this.dataController.getAveragePrices(insight.getListingId())));
    }

    public void showLengthOfStayDiscountLearnMore() {
        showModal(TipFragment.builder(getContext(), NavigationTag.ManageListingAboutLengthOfStayDiscounts).addTitleRes(C6552R.string.manage_listing_about_length_of_stay_discount_title).addTextRes(C6552R.string.manage_listing_about_length_of_stay_discount_info).build());
    }

    private void setMainFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(C6552R.C6554id.fragment_holder, fragment).commit();
        this.loadingView.setVisibility(8);
        this.addedMainFragment = true;
    }

    private void setCalendarFragment(Listing listing) {
        ModalSingleCalendarFragment calendarFragment = ModalSingleCalendarFragment.newInstance(listing.getId(), listing.getName());
        calendarFragment.setCustomOnBackListener((OnBackListener) getParentFragment());
        setFragmentHolderBackgroundColor(C6552R.color.white);
        setMainFragment(calendarFragment);
    }

    private void setActivityToolbar(InsightsActivity insightsActivity) {
        insightsActivity.setActivityToolbar();
        this.container.postDelayed(InsightsDetailCardFragment$$Lambda$2.lambdaFactory$(insightsActivity), (long) getResources().getInteger(C6552R.integer.fragment_transition_duration_long_ms));
    }

    static /* synthetic */ void lambda$setActivityToolbar$1(InsightsActivity insightsActivity) {
        insightsActivity.setToolbarVisible(true);
        insightsActivity.getToolbar().setTheme(2);
    }

    private void setFragmentHolderBackgroundColor(int color) {
        this.fragmentHolder.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    public void toggleInfoContainer(boolean hideInfo) {
        this.infoHidden = hideInfo;
        float infoHeight = hideInfo ? (float) this.infoContainer.getHeight() : this.infoContainer.getTranslationY();
        int fragmentHeight = this.fragmentHolder.getMeasuredHeight();
        if (!hideInfo) {
            infoHeight = -infoHeight;
        }
        int infoDelta = (int) infoHeight;
        ExpandAnimation expandAnimation = new ExpandAnimation(this.fragmentHolder, fragmentHeight, fragmentHeight + infoDelta);
        expandAnimation.setDuration(300).setInterpolator(new LinearInterpolator());
        expandAnimation.start();
        this.infoContainer.animate().translationYBy((float) infoDelta).setInterpolator(new LinearInterpolator()).setDuration(300);
    }
}
