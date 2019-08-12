package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.LegacyBedType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Listener;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Mode;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.constants.LYSConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSRoomsAndGuestsFragment extends LYSBaseFragment {
    LYSJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void bedDetails() {
        }

        public void logBathroomsSelectNum(float numBathrooms) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBathroomsSelectNumEvent(String.valueOf(numBathrooms), Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }

        public void logBathroomsSelectType(BathroomType bathroomType) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBathroomsSelectTypeEvent(bathroomType.serverKey, Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }

        public void logBedroomsNumRooms(int numRooms) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBedroomsNumRoomsEvent(String.valueOf(numRooms), Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }

        public void logBedroomsNumBeds(int numBeds) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBedroomsNumBedsEvent(String.valueOf(numBeds), Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }

        public void logBedroomsSelectBedType(LegacyBedType bedType) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBedroomsSelectBedTypeEvent(bedType.serverDescKey, Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }

        public void logBedroomsSelectNumGuests(int numGuests) {
            LYSRoomsAndGuestsFragment.this.jitneyLogger.logBedroomsSelectNumGuestsEvent(String.valueOf(numGuests), Long.valueOf(LYSRoomsAndGuestsFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSRoomsAndGuestsFragment.this.controller.getListing().getId()));
        }
    };
    @BindView
    RecyclerView recyclerView;
    private RoomsAndGuestsAdapter roomsAndGuestsAdapter;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateRoomsAndGuestsListener = new C0699RL().onResponse(LYSRoomsAndGuestsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSRoomsAndGuestsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSRoomsAndGuestsFragment$$Lambda$3.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment, SimpleListingResponse response) {
        lYSRoomsAndGuestsFragment.controller.setListing(response.listing);
        lYSRoomsAndGuestsFragment.navigateInFlow(getStepFromMode(lYSRoomsAndGuestsFragment.getMode()));
    }

    public static Fragment newInstance(Mode adapterMode) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new LYSRoomsAndGuestsFragment()).putSerializable(LYSConstants.ARG_ADAPTER_MODE, adapterMode)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.roomsAndGuestsAdapter = new RoomsAndGuestsAdapter((Mode) getArguments().getSerializable(LYSConstants.ARG_ADAPTER_MODE), getContext(), this.controller.getListing(), this.controller.getBedDetails(), savedInstanceState, this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.roomsAndGuestsAdapter);
        if (getMode() == Mode.BathroomsOnly) {
            showTip(C7251R.string.lys_dls_bathrooms_tip, null);
        }
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.roomsAndGuestsAdapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateRoomsAndGuests();
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        updateRoomsAndGuests();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.roomsAndGuestsAdapter.hasChanged(this.controller.getListing());
    }

    private void updateListing(Strap strap) {
        if (strap.containsKey(ListingRequestConstants.JSON_BEDROOMS_KEY)) {
            this.controller.getListing().setBedrooms(Integer.parseInt(strap.getString(ListingRequestConstants.JSON_BEDROOMS_KEY)));
        }
        if (strap.containsKey(ListingRequestConstants.JSON_BEDS_KEY)) {
            this.controller.getListing().setBedCount(Integer.parseInt(strap.getString(ListingRequestConstants.JSON_BEDS_KEY)));
        }
        if (strap.containsKey(ListingRequestConstants.JSON_BED_TYPE_CATEGORY_KEY)) {
            this.controller.getListing().setBedTypeCategory(strap.getString(ListingRequestConstants.JSON_BED_TYPE_CATEGORY_KEY));
        }
        if (strap.containsKey(ListingRequestConstants.JSON_PERSON_CAPACITY_KEY)) {
            this.controller.getListing().setPersonCapacity(Integer.parseInt(strap.getString(ListingRequestConstants.JSON_PERSON_CAPACITY_KEY)));
        }
        if (strap.containsKey(ListingRequestConstants.JSON_BATHROOMS_KEY)) {
            this.controller.getListing().setBathrooms(Float.parseFloat(strap.getString(ListingRequestConstants.JSON_BATHROOMS_KEY)));
        }
        if (strap.containsKey(ListingRequestConstants.JSON_BATHROOM_TYPE_KEY)) {
            this.controller.getListing().setBathroomType(strap.getString(ListingRequestConstants.JSON_BATHROOM_TYPE_KEY));
        }
    }

    private void updateRoomsAndGuests() {
        if (!this.controller.isListingCreated()) {
            updateLocalData();
            navigateInFlow(getStepFromMode(getMode()));
        } else if (canSaveChanges()) {
            sendUpdateRoomsAndGuestsRequest();
        } else {
            navigateInFlow(getStepFromMode(getMode()));
        }
    }

    private void sendUpdateRoomsAndGuestsRequest() {
        setLoading(this.roomsAndGuestsAdapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), this.roomsAndGuestsAdapter.getSettings().mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, this.controller.getMaxReachedStep().stepId)).withListener((Observer) this.updateRoomsAndGuestsListener).execute(this.requestManager);
    }

    private void updateLocalData() {
        updateListing(this.roomsAndGuestsAdapter.getSettings());
        this.controller.setListing(this.controller.getListing());
    }

    private Mode getMode() {
        return (Mode) getArguments().getSerializable(LYSConstants.ARG_ADAPTER_MODE);
    }

    private static LYSStep getStepFromMode(Mode mode) {
        switch (mode) {
            case NonBathrooms:
                return LYSStep.RoomsAndGuests;
            case BathroomsOnly:
                return LYSStep.Bathrooms;
            default:
                throw new UnhandledStateException(mode);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return getMode() == Mode.BathroomsOnly ? NavigationTag.LYSBathrooms : NavigationTag.LYSBedrooms;
    }
}
