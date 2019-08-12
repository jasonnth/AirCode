package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.activities.CohostUpsellActivity;
import com.airbnb.android.cohosting.epoxycontrollers.CohostUpsellEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostUpsellEpoxyController.Listener;
import com.airbnb.android.cohosting.utils.CohostingLoggingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;

public class CohostUpsellFragment extends AirFragment implements OnBackListener {
    CohostingManagementJitneyLogger cohostingManagementJitneyLogger;
    private final Listener listener = new Listener() {
        public void inviteFriend() {
            CohostUpsellFragment.this.getActivity().startActivityForResult(ModalActivity.intentForFragment(CohostUpsellFragment.this.getContext(), CohostingInviteFriendWithFeeOptionFragment.create(CohostUpsellFragment.this.listing, new ArrayList(Collections.singletonList(CohostUpsellFragment.this.listingManager)), new ArrayList(), C1958CohostingSourceFlow.PostListYourSpace)), 1002);
        }

        public void showCohostingServiceIntro() {
            ((CohostUpsellActivity) CohostUpsellFragment.this.getActivity()).showModal(CohostingServicesIntroFragment.create(CohostUpsellFragment.this.listing, new ArrayList(Collections.singletonList(CohostUpsellFragment.this.listingManager)), C1958CohostingSourceFlow.PostListYourSpace));
        }
    };
    @State
    Listing listing;
    @State
    ListingManager listingManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostUpsellFragment newInstance(Listing listing2, ListingManager listingManager2) {
        return (CohostUpsellFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostUpsellFragment()).putParcelable("listing", listing2)).putParcelable(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER, listingManager2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohost_upsell, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.listingManager = (ListingManager) getArguments().getParcelable(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER);
        this.listing = (Listing) getArguments().getParcelable("listing");
        getAirActivity().setOnBackPressedListener(this);
        this.recyclerView.setAdapter(new CohostUpsellEpoxyController(getContext(), this.listener, getLoggingContext()).getAdapter());
        this.cohostingManagementJitneyLogger.logInviteAFriendIntroPageImpression(getLoggingContext(), C1958CohostingSourceFlow.PostListYourSpace);
        return view;
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }

    private C1951CohostingContext getLoggingContext() {
        return CohostingLoggingUtil.getCohostingContext(this.listing, Collections.singletonList(this.listingManager));
    }
}
