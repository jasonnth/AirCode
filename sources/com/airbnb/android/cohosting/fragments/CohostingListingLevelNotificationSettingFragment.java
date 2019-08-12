package com.airbnb.android.cohosting.fragments;

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
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingListingLevelNotificationEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.UpdateCohostingNotificationRequest;
import com.airbnb.android.core.responses.UpdateCohostingNotificationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class CohostingListingLevelNotificationSettingFragment extends CohostManagementBaseFragment {
    private CohostingListingLevelNotificationEpoxyController epoxyController;
    @State
    ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<UpdateCohostingNotificationResponse> updateCohostingNotificationListener = new C0699RL().onResponse(CohostingListingLevelNotificationSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(CohostingListingLevelNotificationSettingFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment, UpdateCohostingNotificationResponse response) {
        cohostingListingLevelNotificationSettingFragment.saveButton.setState(AirButton.State.Success);
        cohostingListingLevelNotificationSettingFragment.controller.updateMuteType(response.cohostingNotification);
        cohostingListingLevelNotificationSettingFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment, AirRequestNetworkException e) {
        cohostingListingLevelNotificationSettingFragment.saveButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cohostingListingLevelNotificationSettingFragment.getView(), e);
    }

    public static CohostingListingLevelNotificationSettingFragment create(String managerId) {
        return (CohostingListingLevelNotificationSettingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingListingLevelNotificationSettingFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, managerId)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new CohostingListingLevelNotificationEpoxyController(this.controller);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
        }
        Check.state(this.listingManager != null, "Listing manager can not be null");
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.logger.logNotificationSettingPageImpression(this.controller.getCohostingContext(), this.listingManager);
        return view;
    }

    @OnClick
    public void updateNotification() {
        this.saveButton.setState(AirButton.State.Loading);
        MuteType muteType = this.epoxyController.getMuteType();
        this.logger.logSaveNotificationSettingButtonClicked(this.controller.getCohostingContext(), this.listingManager, muteType);
        new UpdateCohostingNotificationRequest(this.controller.getListingId(), muteType).withListener((Observer) this.updateCohostingNotificationListener).execute(this.requestManager);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    public boolean canSaveChanges() {
        return this.epoxyController.hasChanged();
    }
}
