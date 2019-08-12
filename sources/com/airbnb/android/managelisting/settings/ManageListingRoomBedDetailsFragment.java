package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
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
import com.airbnb.android.listing.adapters.SingleRoomBedDetailsController;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ManageListingRoomBedDetailsFragment extends ManageListingBaseFragment {
    private static final String ARG_ROOM_NUMBER = "arg_room_number";
    private static final int ROOM_ID_UNKNOWN = -1;
    private SingleRoomBedDetailsController epoxyController;
    final RequestListener<ListingRoomsResponse> fetchRoomsListener = new C0699RL().onResponse(ManageListingRoomBedDetailsFragment$$Lambda$5.lambdaFactory$(this)).onError(ManageListingRoomBedDetailsFragment$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<ListingRoomResponse> newRoomListener = new C0699RL().onResponse(ManageListingRoomBedDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingRoomBedDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirRecyclerView recyclerView;
    private long roomId;
    private int roomNumber;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    public final NonResubscribableRequestListener<AirBatchResponse> updateRoomListener = new C0699RL().onResponse(ManageListingRoomBedDetailsFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingRoomBedDetailsFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();

    public static ManageListingRoomBedDetailsFragment forRoom(int roomNumber2) {
        return (ManageListingRoomBedDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingRoomBedDetailsFragment()).putInt(ARG_ROOM_NUMBER, roomNumber2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.roomNumber = getArguments().getInt(ARG_ROOM_NUMBER);
        ListingRoom room = this.controller.getRoomByNumber(this.roomNumber);
        this.roomId = room == null ? -1 : room.getId();
        this.epoxyController = new SingleRoomBedDetailsController(getContext(), this.roomNumber, room, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        if (this.roomId != -1) {
            updateRoom();
        } else {
            createNewRoom();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.epoxyController.hasChanged();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingRoomBedDetails;
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
        new ListingRoomsRequest(this.controller.getListingId()).withListener((Observer) this.fetchRoomsListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment, ListingRoomResponse response) {
        manageListingRoomBedDetailsFragment.roomId = response.listingRoom.getId();
        manageListingRoomBedDetailsFragment.updateRoom();
    }

    static /* synthetic */ void lambda$new$1(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingRoomBedDetailsFragment.getView(), e);
        manageListingRoomBedDetailsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$3(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingRoomBedDetailsFragment.getView(), e);
        manageListingRoomBedDetailsFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$4(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment, ListingRoomsResponse response) {
        manageListingRoomBedDetailsFragment.saveButton.setState(State.Success);
        manageListingRoomBedDetailsFragment.controller.setListingRooms(response.listingRooms);
        manageListingRoomBedDetailsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$6(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment, AirRequestNetworkException error) {
        manageListingRoomBedDetailsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingRoomBedDetailsFragment.getView(), (NetworkException) error, ManageListingRoomBedDetailsFragment$$Lambda$7.lambdaFactory$(manageListingRoomBedDetailsFragment));
    }
}
