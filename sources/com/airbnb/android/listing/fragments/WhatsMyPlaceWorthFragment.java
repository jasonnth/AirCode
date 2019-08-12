package com.airbnb.android.listing.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.WhatsMyPlaceWorth;
import com.airbnb.android.core.requests.LYSCreateListingRequest;
import com.airbnb.android.core.requests.listing.WhatsMyPlaceWorthRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.responses.listing.WhatsMyPlaceWorthResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.IdUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.ListingGraph;
import com.airbnb.android.listing.controllers.WhatsMyPlaceWorthEpoxyController;
import com.airbnb.android.listing.controllers.WhatsMyPlaceWorthEpoxyController.Listener;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment.Builder;
import com.airbnb.android.listing.logging.WhatsMyPlaceWorthLogger;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import p032rx.Observer;

public class WhatsMyPlaceWorthFragment extends AirFragment {
    private static final int REQUEST_CODE_ADDRESS_AUTOCOMPLETE = 200;
    @State
    AirAddress address;
    @State
    int capacity;
    private WhatsMyPlaceWorthEpoxyController controller;
    public final NonResubscribableRequestListener<SimpleListingResponse> createListingRequestListener = new C0699RL().onResponse(WhatsMyPlaceWorthFragment$$Lambda$3.lambdaFactory$(this)).onError(WhatsMyPlaceWorthFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    AirTextView disclaimer;
    public final NonResubscribableRequestListener<WhatsMyPlaceWorthResponse> estimateRequestListener = new C0699RL().onResponse(WhatsMyPlaceWorthFragment$$Lambda$1.lambdaFactory$(this)).onError(WhatsMyPlaceWorthFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @State
    WhatsMyPlaceWorth estimatedValue;
    @BindView
    FixedFlowActionFooter footer;
    private final Listener listener = new Listener() {
        public void addressRequested() {
            WhatsMyPlaceWorthFragment.this.startActivityForResult(new Builder(WhatsMyPlaceWorthFragment.this.getContext(), NavigationTag.WhatsMyPlaceWorthAddress).setCityOnly().build(), 200);
        }

        public void capacityRequested() {
            OptionsMenuFactory.forIntRange(WhatsMyPlaceWorthFragment.this.getContext(), 1, 16).setTitleTransformer(WhatsMyPlaceWorthFragment$1$$Lambda$1.lambdaFactory$(this)).setListener(WhatsMyPlaceWorthFragment$1$$Lambda$2.lambdaFactory$(this)).buildAndShow();
        }

        static /* synthetic */ void lambda$capacityRequested$1(C72411 r2, Integer capacity) {
            WhatsMyPlaceWorthFragment.this.setCapacity(capacity.intValue());
            WhatsMyPlaceWorthFragment.this.updateEstimate();
        }

        public void placeTypeRequested() {
            OptionsMenuFactory.forItems(WhatsMyPlaceWorthFragment.this.getContext(), (T[]) SpaceType.values()).setTitleResTransformer(WhatsMyPlaceWorthFragment$1$$Lambda$3.lambdaFactory$()).setListener(WhatsMyPlaceWorthFragment$1$$Lambda$4.lambdaFactory$(this)).buildAndShow();
        }

        static /* synthetic */ void lambda$placeTypeRequested$3(C72411 r1, SpaceType spaceType) {
            WhatsMyPlaceWorthFragment.this.setSpaceType(spaceType);
            WhatsMyPlaceWorthFragment.this.updateEstimate();
        }
    };
    @State
    boolean loadingEstimate;
    WhatsMyPlaceWorthLogger logger;
    @BindView
    AirRecyclerView recyclerView;
    @State
    SpaceType spaceType;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_whats_my_place_worth, parent, false);
        bindViews(view);
        ((ListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.spaceType == null) {
            this.spaceType = SpaceType.EntireHome;
            this.capacity = 1;
        }
        this.footer.setButtonOnClickListener(WhatsMyPlaceWorthFragment$$Lambda$5.lambdaFactory$(this));
        this.controller = new WhatsMyPlaceWorthEpoxyController(getContext(), this.listener);
        updateView();
        this.recyclerView.setAdapter(this.controller.getAdapter());
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == -1 && data != null && data.hasExtra("address")) {
            setAddress((AirAddress) data.getParcelableExtra("address"));
            updateEstimate();
        }
    }

    private void updateView() {
        this.controller.setData(this.address, this.spaceType, Integer.valueOf(this.capacity));
        if (this.loadingEstimate) {
            this.footer.setSubtitle(C7213R.string.wmpw_earnings_per_week_loading);
        } else if (this.estimatedValue == null) {
            this.footer.setSubtitle(C7213R.string.wmpw_enter_your_address);
        } else {
            this.footer.setSubtitle((CharSequence) getEarningsText(this.estimatedValue));
        }
    }

    @OnClick
    public void toggleDisclaimerVisibility() {
        ViewUtils.setVisibleIf((View) this.disclaimer, this.disclaimer.getVisibility() != 0);
    }

    /* access modifiers changed from: private */
    public void startListing() {
        if (this.address == null) {
            transitionToListYourSpace(-1, "get_started_no_listing");
            return;
        }
        this.footer.setButtonLoading(true);
        LYSCreateListingRequest.forLYSDLS(createListing()).withListener((Observer) this.createListingRequestListener).execute(this.requestManager);
    }

    private Listing createListing() {
        Listing listing = AirAddressUtil.setListingAddress(new Listing(), (AirAddress) Check.notNull(this.address));
        listing.setPropertyType(PropertyType.House);
        listing.setRoomType(fromSpaceType(this.spaceType).key);
        listing.setBathrooms(1.0f);
        listing.setBathroomType(BathroomType.PrivateBathroom);
        listing.setBedrooms(1);
        listing.setBedCount(1);
        listing.setPersonCapacity(this.capacity);
        return listing;
    }

    /* access modifiers changed from: private */
    public void transitionToListYourSpace(long listingId, String target) {
        this.logger.logContinueEvent(IdUtils.isValidId(listingId));
        startActivity(ListYourSpaceIntents.intentForInProgressListing(getContext(), listingId, "whats_my_place_worth", target));
        getAirActivity().finish();
    }

    /* access modifiers changed from: private */
    public void showCreateListingError(NetworkException e) {
        this.footer.setButtonLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
        updateView();
    }

    private void setAddress(AirAddress address2) {
        this.address = address2;
        this.logger.selectCityEvent(address2.city() + " " + address2.country());
        updateView();
    }

    /* access modifiers changed from: private */
    public void setSpaceType(SpaceType spaceType2) {
        this.spaceType = spaceType2;
        this.logger.selectRoomType(fromSpaceType(spaceType2));
        updateView();
    }

    /* access modifiers changed from: private */
    public void setCapacity(int capacity2) {
        this.capacity = capacity2;
        this.logger.selectCapacityEvent(capacity2);
        updateView();
    }

    /* access modifiers changed from: private */
    public void updateEstimate() {
        this.estimatedValue = null;
        if (!(this.address == null || this.address.longitude() == null || this.address.latitude() == null)) {
            this.loadingEstimate = true;
            WhatsMyPlaceWorthRequest.forWeek(this.capacity, this.address.latitude().doubleValue(), this.address.longitude().doubleValue()).withListener((Observer) this.estimateRequestListener).execute(this.requestManager);
        }
        updateView();
    }

    /* access modifiers changed from: private */
    public void handleEstimateFetchError(NetworkException exception) {
        this.loadingEstimate = false;
        NetworkUtil.tryShowErrorWithSnackbar(getView(), exception);
        updateView();
    }

    /* access modifiers changed from: private */
    public void setEstimatedValue(WhatsMyPlaceWorthResponse response) {
        this.loadingEstimate = false;
        this.estimatedValue = (WhatsMyPlaceWorth) Check.notNull(response.forType(fromSpaceType(this.spaceType)));
        updateView();
    }

    private Spannable getEarningsText(WhatsMyPlaceWorth estimatedValue2) {
        String amountText = estimatedValue2.getLocalizedPriceFormatted();
        return SpannableUtils.makeBoldedSubString(getContext().getString(C7213R.string.wmpw_earnings_per_week, new Object[]{amountText}), getContext(), amountText);
    }

    private C6120RoomType fromSpaceType(SpaceType spaceType2) {
        switch (spaceType2) {
            case EntireHome:
                return C6120RoomType.EntireHome;
            case PrivateRoom:
                return C6120RoomType.PrivateRoom;
            case SharedSpace:
                return C6120RoomType.SharedRoom;
            default:
                throw new UnhandledStateException(spaceType2);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WhatsMyPlaceWorth;
    }
}
