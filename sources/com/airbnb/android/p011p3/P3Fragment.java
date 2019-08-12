package com.airbnb.android.p011p3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.requests.ReferralStatusForMobileRequest;
import com.airbnb.android.core.responses.ReferralStatusForMobileResponse;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.views.WishListIcon;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListSnackBarHelper;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.p011p3.P3Component.Builder;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.RecyclerViewUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.animation.SimpleTransitionListener;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.AirToolbar.MenuTransitionNameCallback;
import com.airbnb.p027n2.components.HomeMarquee;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Stopwatch;
import icepick.State;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.P3Fragment */
public class P3Fragment extends P3BaseFragment implements MenuTransitionNameCallback {
    private static final int MODEL_BUILD_DELAY_MS = 300;
    private static final int REQUEST_CODE_SIGN_IN_TO_BOOK = 102;
    @BindView
    ExploreBookButton bookButton;
    BusinessTravelAccountManager businessTravelAccountManager;
    @BindView
    CoordinatorLayout coordinatorLayout;
    private BaseP3EpoxyController epoxyController;
    @State
    String firstVerificationStep;
    @BindView
    AirRecyclerView recyclerView;
    final RequestListener<ReferralStatusForMobileResponse> referralStatusListener = new C0699RL().onResponse(P3Fragment$$Lambda$1.lambdaFactory$(this)).onError(P3Fragment$$Lambda$2.lambdaFactory$(this)).build();
    private Stopwatch stopWatch;
    @BindView
    AirToolbar toolbar;
    private TransitionListener transitionListener;
    WishListManager wishListManager;

    public static P3Fragment newInstance() {
        return new P3Fragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.stopWatch = Stopwatch.createUnstarted();
        if (!(savedInstanceState != null || this.controller == null || this.controller.getState() == null)) {
            this.firstVerificationStep = this.controller.getState().firstVerificationStep();
        }
        ((Builder) ((P3Bindings) CoreApplication.instance(getActivity()).componentProvider()).p3ComponentProvider().get()).build().inject(this);
        if (DebugSettings.SELECT_PDP.isEnabled()) {
            this.epoxyController = new SelectPDPEpoxyController(getContext(), this.controller, this.businessTravelAccountManager, savedInstanceState);
        } else {
            this.epoxyController = new P3EpoxyController(getContext(), this.controller, this.businessTravelAccountManager, savedInstanceState);
        }
    }

    public void onResume() {
        super.onResume();
        this.stopWatch.start();
    }

    public void onPause() {
        super.onPause();
        this.stopWatch.stop();
    }

    public void onDestroyView() {
        WishListSnackBarHelper.unregister(this);
        super.onDestroyView();
    }

    public void onDestroy() {
        this.controller.getAnalytics().trackViewDuration(this.stopWatch.elapsed(TimeUnit.MILLISECONDS));
        super.onDestroy();
        this.controller.getAnalytics().trackScrollToSection(this.epoxyController.getScrollToModelTag());
    }

    @SuppressLint({"NewApi"})
    public void onAttach(Context context) {
        super.onAttach(context);
        if (AndroidVersion.isAtLeastLollipop() && getActivity().getWindow().getReturnTransition() != null) {
            this.transitionListener = new SimpleTransitionListener() {
                public void onTransitionStart(Transition transition) {
                    for (int i = 0; i < P3Fragment.this.recyclerView.getChildCount(); i++) {
                        View child = P3Fragment.this.recyclerView.getChildAt(i);
                        if (child instanceof HomeMarquee) {
                            ((HomeMarquee) child).cancelAutoScrolling();
                        }
                    }
                }
            };
            getActivity().getWindow().getReturnTransition().addListener(this.transitionListener);
        }
    }

    @SuppressLint({"NewApi"})
    public void onDetach() {
        super.onDetach();
        if (AndroidVersion.isAtLeastLollipop()) {
            getActivity().getWindow().getEnterTransition().removeListener(this.transitionListener);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_p3, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setMenuTransitionNameCallback(this);
        setHasOptionsMenu(true);
        RecyclerViewUtils.setTouchSlopForNestedScrolling(this.recyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setEpoxyController(this.epoxyController);
        fetchReferralCredit();
        setupBookButton();
        WishListSnackBarHelper.registerAndShowWithView(this, this.coordinatorLayout, this.wishListManager);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.epoxyController.requestModelBuild();
    }

    public View getViewToUseForSnackbar() {
        return this.coordinatorLayout;
    }

    private void setupBookButton() {
        boolean hasListing;
        OnClickListener onClickListener;
        boolean hideBookButton = false;
        P3State state = this.controller.getState();
        if (state.listing() != null) {
            hasListing = true;
        } else {
            hasListing = false;
        }
        if (!hasListing || state.isHostPreview()) {
            hideBookButton = true;
        }
        ViewLibUtils.setGoneIf(this.bookButton.getView(), hideBookButton);
        if (!hideBookButton) {
            if (!state.hasDates() && !ChinaUtils.enableExploreBookButtonOptimization(getContext())) {
                this.bookButton.setText(C7532R.string.check_availability);
            } else if (state.listing().getSpecialOffer() != null) {
                this.bookButton.setText(C7532R.string.complete_booking);
            } else if (state.pricingQuote() == null || !state.pricingQuote().isInstantBookable()) {
                if (Experiments.useRequestApprovalCopy()) {
                    this.bookButton.setText(C7532R.string.request_approval_rtb_cta);
                } else if (Experiments.useRequestReservationCopy()) {
                    this.bookButton.setText(C7532R.string.request_reservation_rtb_cta);
                } else {
                    this.bookButton.setText(C7532R.string.request_to_book_rtb_cta);
                }
                this.bookButton.setDrawableLeft(null);
            } else {
                this.bookButton.setText(C7532R.string.book);
            }
            Listing listing = state.listing();
            if (listing != null) {
                this.bookButton.configureListingDetails(listing);
                ExploreBookButton exploreBookButton = this.bookButton;
                if (this.bookButton.isShowingRating()) {
                    onClickListener = P3Fragment$$Lambda$3.lambdaFactory$(this);
                } else {
                    onClickListener = null;
                }
                exploreBookButton.setReviewClickListener(onClickListener);
            }
            PricingQuote pricingQuote = state.pricingQuote();
            if (pricingQuote != null) {
                this.bookButton.configurePricingDetails(pricingQuote);
            }
            this.bookButton.setContactHostButtonListener(P3Fragment$$Lambda$4.lambdaFactory$(this));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == -1) {
            checkPreconditionsAndGoToP4();
        }
    }

    private void fetchReferralCredit() {
        ReferralStatusForMobileRequest.newInstance(this.mAccountManager.getCurrentUserId()).withListener((Observer) this.referralStatusListener).execute(this.requestManager);
    }

    public void updateReferralCredit(ReferralStatusForMobile referralStatus) {
        if (referralStatus.getAvailableCreditUSD() <= 0) {
            this.bookButton.setReferralCredit("");
        } else if (referralStatus.getAvailableCreditExpiration() != null) {
            this.bookButton.setReferralCredit(referralStatus.getAvailableCreditLocalized());
            this.bookButton.setReferralCreditClickListener(P3Fragment$$Lambda$5.lambdaFactory$(this, referralStatus));
        } else {
            BugsnagWrapper.notify((Throwable) new RuntimeException("Invalid referralStatus on P3Fragment with credit: " + referralStatus.getAvailableCreditUSD() + " and a null expiration this should never happen!"));
            this.bookButton.setReferralCredit("");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void checkPreconditionsAndGoToP4() {
        long listingId = this.controller.getState().listingId();
        if (this.controller.getState().hasDates()) {
            this.controller.getAnalytics().trackBookItButtonClick(listingId);
            getActivity().startActivity(P3BookingIntents.intentToBooking(this.controller, getActivity()));
        } else if (ChinaUtils.enableExploreBookButtonOptimization(getContext())) {
            this.controller.getAnalytics().trackBookItButtonClickWithoutDates(listingId);
            this.controller.onItemClick(20);
        } else {
            this.controller.getAnalytics().trackCheckAvailabilityButtonClick(listingId);
            this.controller.onItemClick(12);
        }
    }

    public void onIdentityForBookingCompleted() {
        checkPreconditionsAndGoToP4();
    }

    public void onListingLoaded() {
        this.epoxyController.requestDelayedModelBuild(300);
        setupBookButton();
        getActivity().supportInvalidateOptionsMenu();
        if (this.firstVerificationStep != null) {
            this.controller.launchIdentityForBooking();
            this.firstVerificationStep = null;
        }
    }

    public void onPricingQuoteLoaded() {
        this.epoxyController.requestDelayedModelBuild(300);
        setupBookButton();
    }

    public void onStateChanged() {
        this.epoxyController.requestDelayedModelBuild(300);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.controller != null) {
            Listing listing = this.controller.getState().listing();
            boolean hasListing = listing != null;
            MenuItem wishListItem = menu.findItem(C7532R.C7534id.menu_wish_list);
            if (wishListItem != null) {
                wishListItem.setVisible(hasListing);
                if (hasListing) {
                    ((WishListIcon) wishListItem.getActionView()).initIfNeeded(WishListableData.forHome(listing).source(C2813WishlistSource.HomeDetail).searchSessionId(this.controller.getState().searchSessionId()).guestDetails(this.controller.getState().guestDetails()).checkIn(this.controller.getState().checkInDate()).checkOut(this.controller.getState().checkOutDate()).allowAutoAdd(true).build());
                }
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7532R.C7534id.menu_share) {
            return super.onOptionsItemSelected(item);
        }
        Listing listing = this.controller.getState().listing();
        if (listing != null) {
            this.controller.getAnalytics().trackShareClick();
            startActivity(ShareActivityIntents.newIntentForListing(getActivity(), listing, this.controller.getState().checkInDate(), this.controller.getState().checkOutDate(), this.controller.getState().guestDetails()));
        }
        return true;
    }

    public void onPicturePositionChanged(int position) {
        View firstView = this.recyclerView.getLayoutManager().findViewByPosition(0);
        if (firstView != null) {
            if (!(firstView instanceof HomeMarquee)) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Expected a HomeMarquee to be the first view. Got " + firstView.getClass().getSimpleName()));
            } else {
                ((HomeMarquee) firstView).scrollToCarouselPosition(position);
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Listing;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.controller.getState().listingId());
    }

    public boolean shouldShowAsDialog(Context context) {
        return false;
    }

    public String getTransitionNameForMenuItem(int resId) {
        boolean isStupidSamsungDevice = AndroidVersion.isSamsung() && AndroidVersion.isAtLeastNougat();
        if (resId != C7532R.C7534id.menu_wish_list || isStupidSamsungDevice) {
            return null;
        }
        return TransitionName.toString("listing", this.controller.getState().listingId(), "wishListHeart");
    }
}
