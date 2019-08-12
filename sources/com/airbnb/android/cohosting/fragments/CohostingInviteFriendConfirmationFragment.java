package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.utils.CohostingLoggingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.p027n2.components.SheetMarquee;
import icepick.State;
import java.util.ArrayList;

public class CohostingInviteFriendConfirmationFragment extends AirFragment implements OnBackListener {
    public static final String ARG_EMAIL = "email";
    @State
    Listing listing;
    @State
    ArrayList<ListingManager> listingManagers;
    CohostingManagementJitneyLogger logger;
    @BindView
    SheetMarquee marquee;

    public static CohostingInviteFriendConfirmationFragment newInstance(String email, Listing listing2, ArrayList<ListingManager> listingManagers2) {
        return (CohostingInviteFriendConfirmationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingInviteFriendConfirmationFragment()).putString("email", email)).putParcelable("listing", listing2)).putParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS, listingManagers2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_invite_friend_confirmation, container, false);
        bindViews(view);
        this.listing = (Listing) getArguments().getParcelable("listing");
        this.listingManagers = getArguments().getParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS);
        this.logger.logInviteFriendConfirmationImpression(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), C1958CohostingSourceFlow.PostListYourSpace);
        this.marquee.setSubtitle(getString(C5658R.string.cohosting_invite_friend_confirmation_explanation, getArguments().getString("email")));
        getAirActivity().setOnBackPressedListener(this);
        return view;
    }

    @OnClick
    public void onClickOkay() {
        this.logger.logInviteFriendConfirmationClick(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), C1958CohostingSourceFlow.PostListYourSpace);
        getActivity().finish();
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }
}
