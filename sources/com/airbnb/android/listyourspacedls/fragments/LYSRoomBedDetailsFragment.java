package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.BedType;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateEmptyListingRoomRequest;
import com.airbnb.android.core.requests.ListingRoomsRequest;
import com.airbnb.android.core.requests.UpdateBedTypeRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingRoomResponse;
import com.airbnb.android.core.responses.ListingRoomsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.SingleRoomBedDetailsController;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class LYSRoomBedDetailsFragment extends LYSBaseFragment {
    private static final String ARG_ROOM_NUMBER = "arg_room_number";
    private static final int ROOM_ID_UNKNOWN = -1;
    private SingleRoomBedDetailsController epoxyController;
    final RequestListener<ListingRoomsResponse> fetchRoomsListener = new C0699RL().onResponse(LYSRoomBedDetailsFragment$$Lambda$5.lambdaFactory$(this)).onError(LYSRoomBedDetailsFragment$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<ListingRoomResponse> newRoomListener = new C0699RL().onResponse(LYSRoomBedDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSRoomBedDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirRecyclerView recyclerView;
    private ListingRoom room;
    private long roomId;
    private int roomNumber;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    public final NonResubscribableRequestListener<AirBatchResponse> updateRoomListener = new C0699RL().onResponse(LYSRoomBedDetailsFragment$$Lambda$3.lambdaFactory$(this)).onError(LYSRoomBedDetailsFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();

    public static Fragment newInstance(int roomNumber2) {
        return ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new LYSRoomBedDetailsFragment()).putInt(ARG_ROOM_NUMBER, roomNumber2)).putBoolean("within_flow", true)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.roomNumber = getArguments().getInt(ARG_ROOM_NUMBER);
        this.room = this.controller.getRoomByNumber(this.roomNumber);
        this.roomId = this.room != null ? this.room.getId() : -1;
        this.epoxyController = new SingleRoomBedDetailsController(getContext(), this.roomNumber, this.room, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.fragment_listing_recycler_view_with_save_and_x, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(false);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        return view;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateRoomBedDetails();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    @OnClick
    public void onClickSave() {
        this.userAction = UserAction.GoToNext;
        updateRoomBedDetails();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.epoxyController.hasChanged();
    }

    private void updateRoomBedDetails() {
        if (!this.controller.isListingCreated()) {
            updateLocalData();
            navigateInFlow(LYSStep.BedDetails);
        } else if (canSaveChanges()) {
            executeUpdateRoomRequest();
        } else {
            navigateInFlow(LYSStep.BedDetails);
        }
    }

    private void updateLocalData() {
        this.controller.setBedsForRoom(this.roomNumber, this.epoxyController.getNonemptyBeds());
    }

    private void executeUpdateRoomRequest() {
        if (this.roomId == -1) {
            createNewRoom();
        } else {
            updateRoom();
        }
    }

    private void createNewRoom() {
        new CreateEmptyListingRoomRequest(this.controller.getListing().getId(), this.roomNumber).withListener((Observer) this.newRoomListener).execute(this.requestManager);
    }

    private void updateRoom() {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        for (BedType changedType : this.epoxyController.getChanges()) {
            requests.add(new UpdateBedTypeRequest(this.controller.getListing().getId(), this.roomId, changedType.getType().serverDescKey, changedType.getQuantity()));
        }
        new AirBatchRequest(requests, this.updateRoomListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void fetchRoomsData() {
        new ListingRoomsRequest(this.controller.getListing().getId()).withListener((Observer) this.fetchRoomsListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment, ListingRoomResponse response) {
        lYSRoomBedDetailsFragment.roomId = response.listingRoom.getId();
        lYSRoomBedDetailsFragment.updateRoom();
    }

    static /* synthetic */ void lambda$new$1(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(lYSRoomBedDetailsFragment.getView(), e);
        lYSRoomBedDetailsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$3(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(lYSRoomBedDetailsFragment.getView(), e);
        lYSRoomBedDetailsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$4(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment, ListingRoomsResponse response) {
        lYSRoomBedDetailsFragment.saveButton.setState(State.Success);
        lYSRoomBedDetailsFragment.controller.setBedDetails(response.listingRooms);
        lYSRoomBedDetailsFragment.navigateInFlow(LYSStep.BedDetails);
    }

    static /* synthetic */ void lambda$new$6(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment, AirRequestNetworkException error) {
        lYSRoomBedDetailsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(lYSRoomBedDetailsFragment.getView(), (NetworkException) error, LYSRoomBedDetailsFragment$$Lambda$7.lambdaFactory$(lYSRoomBedDetailsFragment));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSRoomBedDetails;
    }
}
