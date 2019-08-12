package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.adapters.AmenitiesAdapter;
import com.airbnb.android.listing.constants.AmenityGroupings;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingAmenitiesFragment extends ManageListingBaseFragment {
    private static final AmenityGroup AMENITY_GROUP_CORE = new AmenityGroup(AmenityGroupings.AMENITIES_CORE_ML);
    private static final AmenityGroup AMENITY_GROUP_HOME_SAFETY = new AmenityGroup(C7368R.string.manage_listing_amenities_home_safety_section_header, AmenityGroupings.AMENITIES_HOME_SAFETY);
    private static final AmenityGroup AMENITY_GROUP_SPACES = new AmenityGroup(C7368R.string.manage_listing_amenities_spaces_section_header, AmenityGroupings.AMENITIES_SPACES);
    private static final String ARG_AMENITY_GROUP = "amenity_group";
    private static final String ARG_AMENITY_GROUP_TITLE_RES = "amenity_group_title_res";
    private static final String ARG_IS_MODAL = "is_modal";
    private AmenitiesAdapter adapter;
    private boolean isModal;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingAmenitiesFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingAmenitiesFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingAmenitiesFragment createForAllAmenities() {
        return new ManageListingAmenitiesFragment();
    }

    public static ManageListingAmenitiesFragment create(int titleRes, AmenityGroup amenityGroup, boolean isModal2) {
        return (ManageListingAmenitiesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingAmenitiesFragment()).putInt(ARG_AMENITY_GROUP_TITLE_RES, titleRes)).putBoolean(ARG_IS_MODAL, isModal2)).putParcelable(ARG_AMENITY_GROUP, amenityGroup)).build();
    }

    static /* synthetic */ void lambda$new$0(ManageListingAmenitiesFragment manageListingAmenitiesFragment, SimpleListingResponse response) {
        manageListingAmenitiesFragment.saveButton.setState(State.Success);
        manageListingAmenitiesFragment.controller.setListing(response.listing);
        manageListingAmenitiesFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingAmenitiesFragment manageListingAmenitiesFragment, AirRequestNetworkException e) {
        manageListingAmenitiesFragment.adapter.setInputEnabled(true);
        manageListingAmenitiesFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingAmenitiesFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isModal = getArguments() != null && getArguments().containsKey(ARG_IS_MODAL) && getArguments().getBoolean(ARG_IS_MODAL);
        if (getArguments() == null || !getArguments().containsKey(ARG_AMENITY_GROUP)) {
            this.adapter = new AmenitiesAdapter(C7368R.string.lys_dls_amenities_title, this.controller.getListing(), savedInstanceState, AMENITY_GROUP_CORE, AMENITY_GROUP_SPACES, AMENITY_GROUP_HOME_SAFETY);
            return;
        }
        this.adapter = new AmenitiesAdapter(getArguments().getInt(ARG_AMENITY_GROUP_TITLE_RES), this.controller.getListing(), savedInstanceState, (AmenityGroup) getArguments().getParcelable(ARG_AMENITY_GROUP));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.isModal) {
            this.toolbar.setNavigationIcon(2);
        }
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forAmenitiesIds(this.controller.getListing().getId(), this.adapter.getSelectedAmenities(this.controller.getListing())).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingAmenities;
    }
}
