package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CohostInvitationsRequest;
import com.airbnb.android.core.requests.ListingManagersRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CohostInvitationsResponse;
import com.airbnb.android.core.responses.ListingManagersResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.utils.SpannableParagraphBuilder;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p032rx.Observer;

public class LYSPublishFragment extends AirFragment {
    CalendarStore calendarStore;
    protected CalendarStoreListener calendarStoreListener = new CalendarStoreListener() {
        public void onResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> longSparseArray, AirDate startDate, AirDate endDate) {
            LYSPublishFragment.this.controller.showOpaqueLoader(false);
            LYSPublishFragment.this.displayPublishDetails(LYSPublishFragment.this.getFirstHostingDate((CalendarDays) calendarDaysByListingId.get(LYSPublishFragment.this.controller.getListing().getId())));
        }

        public void onError(NetworkException e) {
            LYSPublishFragment.this.controller.showOpaqueLoader(false);
            LYSPublishFragment.this.displayPublishDetails(null);
        }
    };
    /* access modifiers changed from: private */
    public LYSDataController controller;
    @BindView
    HeroMarquee heroMarquee;
    LYSJitneyLogger jitneyLogger;
    ListingPromoController listingPromoController;
    final RequestListener<SimpleListingResponse> publishListingRequestListener = new C0699RL().onResponse(LYSPublishFragment$$Lambda$2.lambdaFactory$(this)).onError(LYSPublishFragment$$Lambda$3.lambdaFactory$(this)).onComplete(LYSPublishFragment$$Lambda$4.lambdaFactory$(this)).build();
    public final NonResubscribableRequestListener<AirBatchResponse> publishListingWithCohostUpsellBatchRequestListener = new C0699RL().onResponse(LYSPublishFragment$$Lambda$1.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    TipView tipView;

    static /* synthetic */ void lambda$new$0(LYSPublishFragment lYSPublishFragment, AirBatchResponse response) {
        Listing listing = ((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing;
        List<ListingManager> listingManagers = ((ListingManagersResponse) response.singleResponse(ListingManagersResponse.class)).listingManagers;
        List<CohostInvitation> cohostInvitations = ((CohostInvitationsResponse) response.singleResponse(CohostInvitationsResponse.class)).cohostInvitations;
        if (listingManagers.size() == 1 && cohostInvitations.size() == 0 && Experiments.showPostLYSCohostingUpsell()) {
            lYSPublishFragment.startActivity(CohostingIntents.intentForCohostUpsell(lYSPublishFragment.getContext(), listing, (ListingManager) listingManagers.get(0)));
        }
    }

    static /* synthetic */ void lambda$new$1(LYSPublishFragment lYSPublishFragment, SimpleListingResponse response) {
        lYSPublishFragment.controller.setListing(response.listing);
        lYSPublishFragment.listingPromoController.refreshListingsInfo();
        lYSPublishFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$2(LYSPublishFragment lYSPublishFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(lYSPublishFragment.getView(), e);
        lYSPublishFragment.heroMarquee.setFirstButtonState(State.Normal);
    }

    static /* synthetic */ void lambda$new$3(LYSPublishFragment lYSPublishFragment, Boolean success) {
        lYSPublishFragment.heroMarquee.setSecondButtonEnabled(true);
        lYSPublishFragment.jitneyLogger.logPublishListingEvent(success.booleanValue(), Long.valueOf(lYSPublishFragment.controller.getListing().getId()));
    }

    public static Fragment newInstance() {
        return new LYSPublishFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_publish, parent, false);
        bindViews(view);
        getAirActivity().setOnBackPressedListener(LYSPublishFragment$$Lambda$5.lambdaFactory$(this));
        this.calendarStoreListener.setEnabled(true);
        getOrReloadCalendar();
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        getAirActivity().setOnBackPressedListener(null);
    }

    public void onPause() {
        super.onPause();
        this.calendarStoreListener.setEnabled(false);
    }

    public void setController(LYSDataController controller2) {
        this.controller = controller2;
    }

    /* access modifiers changed from: private */
    public void showTipModal() {
        this.controller.showTipModal(C7251R.string.lys_dls_publish_step_tip_title, new SpannableParagraphBuilder(getContext()).append(C7251R.string.lys_dls_publish_step_tip_text_title_1, C7251R.string.lys_dls_publish_step_tip_text_paragraph_1).append(C7251R.string.lys_dls_publish_step_tip_text_title_2, C7251R.string.lys_dls_publish_step_tip_text_paragraph_2).append(C7251R.string.lys_dls_publish_step_tip_text_title_3, C7251R.string.lys_dls_publish_step_tip_text_paragraph_3).build(), NavigationTag.LYSPublishTip);
    }

    /* access modifiers changed from: private */
    public void displayPublishDetails(AirDate firstAvailableDate) {
        this.heroMarquee.setCaption((CharSequence) generatePublishDateString(firstAvailableDate));
        this.heroMarquee.setFirstButtonClickListener(LYSPublishFragment$$Lambda$6.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonClickListener(LYSPublishFragment$$Lambda$7.lambdaFactory$(this));
        this.tipView.setTipTextRes(C7251R.string.lys_dls_publish_step_tip);
        this.tipView.setTipClickListener(LYSPublishFragment$$Lambda$8.lambdaFactory$(this));
    }

    private String generatePublishDateString(AirDate firstHostingDate) {
        if (firstHostingDate == null) {
            return getContext().getString(C7251R.string.lys_dls_publish_step_caption_no_calendar);
        }
        String dateString = firstHostingDate.formatDate(getContext().getString(C7251R.string.mdy_format_full));
        return getContext().getString(C7251R.string.lys_dls_publish_step_caption, new Object[]{dateString});
    }

    private void getOrReloadCalendar() {
        this.controller.showOpaqueLoader(true);
        AirDate startDate = AirDate.today().getFirstDayOfMonth();
        this.calendarStore.getDaysForListingIds(Collections.singleton(Long.valueOf(this.controller.getListing().getId())), startDate, startDate.plusYears(1).getLastDayOfMonth(), this.controller.shouldReloadCalendar(), this.calendarStoreListener, true);
        this.controller.setShouldReloadCalendar(false);
    }

    /* access modifiers changed from: private */
    public AirDate getFirstHostingDate(CalendarDays calendarDays) {
        return calendarDays.getFirstAvailableDateFrom(AirDate.today().plusDays(this.controller.getCalendarRule().getAdvanceNotice().getNumDays()));
    }

    /* access modifiers changed from: private */
    public void publishListing() {
        this.heroMarquee.setFirstButtonState(State.Loading);
        this.heroMarquee.setSecondButtonEnabled(false);
        new AirBatchRequest(Arrays.asList(new BaseRequestV2[]{UpdateListingRequest.forFieldLYS(this.controller.getListing().getId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(true)).withListener((Observer) this.publishListingRequestListener), (ListingManagersRequest) ListingManagersRequest.forListing(this.controller.getListing().getId()).skipCache(), (CohostInvitationsRequest) CohostInvitationsRequest.forListing(this.controller.getListing().getId(), this.mAccountManager.getCurrentUserId()).skipCache()}), this.publishListingWithCohostUpsellBatchRequestListener).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void showLanding() {
        this.jitneyLogger.logPublishMakeChanges(Long.valueOf(this.controller.getListing().getId()));
        this.controller.returnToLanding();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSPublish;
    }
}
