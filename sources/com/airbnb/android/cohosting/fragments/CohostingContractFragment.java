package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.adapters.CohostingContractAdapter;
import com.airbnb.android.cohosting.adapters.CohostingContractAdapter.Listener;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.CohostingContractRequest;
import com.airbnb.android.core.responses.CohostingContractResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;
import icepick.State;
import p032rx.Observer;

public class CohostingContractFragment extends CohostManagementBaseFragment {
    private CohostingContractAdapter adapter;
    final RequestListener<CohostingContractResponse> cohostingContractListener = new C0699RL().onResponse(CohostingContractFragment$$Lambda$2.lambdaFactory$(this)).onError(CohostingContractFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    CohostingContract contract;
    private final Listener listener = CohostingContractFragment$$Lambda$1.lambdaFactory$(this);
    private ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    RefreshLoader refreshLoader;
    @BindView
    AirToolbar toolbar;

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    static /* synthetic */ void lambda$new$0(CohostingContractFragment cohostingContractFragment, CohostingContractResponse response) {
        cohostingContractFragment.refreshLoader.setVisibility(8);
        cohostingContractFragment.contract = response.cohostingContract;
        cohostingContractFragment.adapter.update(cohostingContractFragment.contract);
        cohostingContractFragment.logImpression();
    }

    static /* synthetic */ void lambda$new$1(CohostingContractFragment cohostingContractFragment, AirRequestNetworkException e) {
        cohostingContractFragment.refreshLoader.setVisibility(8);
        NetworkUtil.tryShowErrorWithSnackbar(cohostingContractFragment.getView(), e);
    }

    public static CohostingContractFragment create(long contractId, String managerId) {
        return (CohostingContractFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingContractFragment()).putLong(CohostingConstants.CONRACT_ID_FIELD, contractId)).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, managerId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_contract, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.contract = null;
            fetchContract();
            this.refreshLoader.setVisibility(0);
        }
        this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
        this.adapter = new CohostingContractAdapter(getContext(), this.contract, this.controller, this.listener);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    private void fetchContract() {
        new CohostingContractRequest(getArguments().getLong(CohostingConstants.CONRACT_ID_FIELD)).withListener((Observer) this.cohostingContractListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void logImpression() {
        this.logger.logContractDetailPageImpression(this.controller.getCohostingContext(), this.listingManager);
    }
}
