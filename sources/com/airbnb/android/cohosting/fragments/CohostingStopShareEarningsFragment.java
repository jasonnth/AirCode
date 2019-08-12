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
import com.airbnb.android.cohosting.adapters.CohostingStopShareEarningsAdapter;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.DeleteCohostingContractRequest;
import com.airbnb.android.core.responses.DeleteCohostingContractResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class CohostingStopShareEarningsFragment extends CohostManagementBaseFragment {
    @State
    ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<DeleteCohostingContractResponse> setCohostingContractListener = new C0699RL().onResponse(CohostingStopShareEarningsFragment$$Lambda$1.lambdaFactory$(this)).onError(CohostingStopShareEarningsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton stopButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment, DeleteCohostingContractResponse response) {
        cohostingStopShareEarningsFragment.stopButton.setState(AirButton.State.Success);
        cohostingStopShareEarningsFragment.controller.removeCohostingContract(cohostingStopShareEarningsFragment.listingManager.getId());
        cohostingStopShareEarningsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment, AirRequestNetworkException e) {
        cohostingStopShareEarningsFragment.stopButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cohostingStopShareEarningsFragment.getView(), e);
    }

    public static CohostingStopShareEarningsFragment create(String listingManagerId) {
        return (CohostingStopShareEarningsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingStopShareEarningsFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, listingManagerId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_stop_share_earnings, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
        }
        if (this.listingManager != null) {
            z = true;
        } else {
            z = false;
        }
        Check.state(z, "Listing manager can not be null");
        this.recyclerView.setAdapter(new CohostingStopShareEarningsAdapter(getContext(), this.listingManager));
        this.logger.logStopShareEarningsModalImpression(this.controller.getCohostingContext(), this.listingManager);
        return view;
    }

    @OnClick
    public void RemoveContract() {
        this.stopButton.setState(AirButton.State.Loading);
        new DeleteCohostingContractRequest(this.listingManager.getContract().getId()).withListener((Observer) this.setCohostingContractListener).execute(this.requestManager);
        this.logger.logStopShareEarningsButtonClicked(this.controller.getCohostingContext(), this.listingManager);
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        this.logger.logStopShareEarningsModalDismissed(this.controller.getCohostingContext(), this.listingManager);
        return super.onBackPressed();
    }

    public boolean canSaveChanges() {
        return false;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
