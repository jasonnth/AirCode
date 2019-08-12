package com.airbnb.android.collections.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.collections.C5698R;
import com.airbnb.android.collections.adapters.SelectInvitationListingPickerController;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.requests.HomesCollectionsApplicationsRequest;
import com.airbnb.android.core.responses.HomesCollectionsApplicationsResponse;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.Observer;

public class SelectInvitationListingPickerFragment extends AirFragment {
    private static final String APPLICATIONS_ARG = "applications_to_show";
    private static final String CLOSE_TO_PASS_ARG = "close_to_pass_arg";
    private SelectInvitationListingPickerController controller;
    @State
    ArrayList<HomeCollectionApplication> listings;
    final RequestListener<HomesCollectionsApplicationsResponse> listingsListener = new C0699RL().onResponse(SelectInvitationListingPickerFragment$$Lambda$1.lambdaFactory$(this)).onError(SelectInvitationListingPickerFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Intent intentForModal(Context context) {
        return ModalActivity.intentForFragment(context, create(null, false));
    }

    public static Intent intentForModal(Context context, List<HomeCollectionApplication> applications, boolean closeToPassSelect) {
        return ModalActivity.intentForFragment(context, create(applications, closeToPassSelect));
    }

    public static AirFragment create(List<HomeCollectionApplication> applications, boolean closeToPassSelect) {
        SelectInvitationListingPickerFragment fragment = new SelectInvitationListingPickerFragment();
        Bundle bundle = new Bundle();
        if (applications != null) {
            bundle.putParcelableArrayList(APPLICATIONS_ARG, new ArrayList(applications));
        }
        bundle.putBoolean(CLOSE_TO_PASS_ARG, closeToPassSelect);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controller = new SelectInvitationListingPickerController(getContext(), this.mAccountManager.getCurrentUser().getFirstName(), SelectInvitationListingPickerFragment$$Lambda$5.lambdaFactory$(this));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5698R.layout.fragment_invitation_listing_picker, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.controller.getAdapter());
        if (this.listings == null && getArguments() != null) {
            this.listings = getArguments().getParcelableArrayList(APPLICATIONS_ARG);
        }
        this.controller.setData(this.listings, Boolean.valueOf(getArguments().getBoolean(CLOSE_TO_PASS_ARG)));
        if (this.listings == null && savedInstanceState == null) {
            makeListingsRequest();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void handleOnListingClicked(HomeCollectionApplication application) {
        if (!FeatureToggles.showCloseToPassModal() || application.getStatus() != 7) {
            startActivity(WebViewIntentBuilder.newBuilder(getContext(), getString(C5698R.string.homes_collections_application_earlyaccess_steps_url, String.valueOf(application.getListing().getId()))).authenticate().toIntent());
            return;
        }
        startActivity(HomeActivityIntents.intentForListingFixItReport(getContext(), application.getId(), application.getListing().getName()));
    }

    /* access modifiers changed from: private */
    public void makeListingsRequest() {
        HomesCollectionsApplicationsRequest.forListings(this.mAccountManager.getCurrentUserId()).withListener((Observer) this.listingsListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleListingsResponse(HomesCollectionsApplicationsResponse response) {
        if (ListUtils.isNullOrEmpty(response.applications)) {
            getActivity().finish();
            return;
        }
        if (FeatureToggles.showCloseToPassModal()) {
            List<HomeCollectionApplication> closeToPassListings = FluentIterable.from((Iterable<E>) response.applications).filter(SelectInvitationListingPickerFragment$$Lambda$6.lambdaFactory$()).toList();
            if (!ListUtils.isEmpty((Collection<?>) closeToPassListings)) {
                this.listings = new ArrayList<>(closeToPassListings);
                this.controller.setData(this.listings, Boolean.valueOf(true));
                return;
            }
        }
        this.listings = new ArrayList<>(response.applications);
        this.controller.setData(this.listings, Boolean.valueOf(false));
    }

    static /* synthetic */ boolean lambda$handleListingsResponse$2(HomeCollectionApplication application) {
        return application.getStatus() == 7;
    }
}
