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
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.AmenitiesAdapter;
import com.airbnb.android.listing.constants.AmenityGroupings;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.constants.LYSConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSAmenitiesFragment extends LYSBaseFragment {
    private static final AmenityGroup AMENITIES_CORE = new AmenityGroup(AmenityGroupings.AMENITIES_CORE_LYS);
    private static final AmenityGroup AMENITIES_HOME_SAFETY = new AmenityGroup(C7251R.string.lys_dls_amenities_home_safety_section, AmenityGroupings.AMENITIES_HOME_SAFETY);
    private static final AmenityGroup AMENITIES_SPACES = new AmenityGroup(AmenityGroupings.AMENITIES_SPACES);
    private AmenitiesAdapter amenitiesAdapter;
    private Mode mode;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSAmenitiesFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSAmenitiesFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSAmenitiesFragment$$Lambda$3.lambdaFactory$(this)).build();

    public enum Mode {
        ListYourSpace,
        SpacesOnly
    }

    static /* synthetic */ void lambda$new$0(LYSAmenitiesFragment lYSAmenitiesFragment, SimpleListingResponse response) {
        lYSAmenitiesFragment.controller.setListing(response.listing);
        lYSAmenitiesFragment.navigateInFlow(getStepFromMode(lYSAmenitiesFragment.mode));
    }

    public static Fragment newInstance(Mode adapterMode) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new LYSAmenitiesFragment()).putSerializable(LYSConstants.ARG_ADAPTER_MODE, adapterMode)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mode = (Mode) getArguments().getSerializable(LYSConstants.ARG_ADAPTER_MODE);
        switch (this.mode) {
            case ListYourSpace:
                this.amenitiesAdapter = new AmenitiesAdapter(C7251R.string.lys_dls_amenities_title, this.controller.getListing(), savedInstanceState, AMENITIES_CORE, AMENITIES_HOME_SAFETY);
                return;
            case SpacesOnly:
                this.amenitiesAdapter = new AmenitiesAdapter(C7251R.string.lys_dls_spaces_title, this.controller.getListing(), savedInstanceState, AMENITIES_SPACES);
                return;
            default:
                throw new UnhandledStateException(this.mode);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.amenitiesAdapter);
        showTip(getTipTextFromMode(this.mode), LYSAmenitiesFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.amenitiesAdapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (canSaveChanges()) {
            updateAmenities();
        } else {
            navigateInFlow(getStepFromMode(this.mode));
        }
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        if (canSaveChanges()) {
            updateAmenities();
        } else {
            updateLastStepId(getStepFromMode(this.mode));
        }
    }

    private void updateAmenities() {
        setLoading(this.amenitiesAdapter);
        UpdateListingRequest.forAmenitiesIdsLYS(this.controller.getListing().getId(), this.amenitiesAdapter.getSelectedAmenities(this.controller.getListing()), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.amenitiesAdapter.hasChanged(this.controller.getListing());
    }

    /* access modifiers changed from: private */
    public void showTipPage() {
        switch (this.mode) {
            case ListYourSpace:
                this.controller.showTipModal(C7251R.string.lys_dls_amenities_tip_title, C7251R.string.lys_dls_amenities_tip_text, NavigationTag.LYSAmenitiesTip);
                return;
            case SpacesOnly:
                this.controller.showTipModal(C7251R.string.lys_dls_spaces_tip_title, C7251R.string.lys_dls_spaces_tip_text, NavigationTag.LYSSpacesTip);
                return;
            default:
                throw new UnhandledStateException(this.mode);
        }
    }

    private static int getTipTextFromMode(Mode mode2) {
        switch (mode2) {
            case ListYourSpace:
                return C7251R.string.lys_dls_amenities_tip;
            case SpacesOnly:
                return C7251R.string.lys_dls_spaces_tip;
            default:
                throw new UnhandledStateException(mode2);
        }
    }

    private static LYSStep getStepFromMode(Mode mode2) {
        switch (mode2) {
            case ListYourSpace:
                return LYSStep.Amenities;
            case SpacesOnly:
                return LYSStep.Spaces;
            default:
                throw new UnhandledStateException(mode2);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.mode == Mode.SpacesOnly ? NavigationTag.LYSSpaces : NavigationTag.LYSAmenities;
    }
}
