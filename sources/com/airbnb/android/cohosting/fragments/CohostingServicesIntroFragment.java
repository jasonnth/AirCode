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
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BulletTextRow;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import java.util.ArrayList;

public class CohostingServicesIntroFragment extends AirFragment implements OnBackListener {
    @BindView
    AirTextView constrainsText;
    @BindView
    View divider;
    @BindView
    AirTextView explanationSubtitle;
    @BindView
    SimpleTextRow guestsText;
    @BindView
    BulletTextRow interactionTextForCohost;
    @BindView
    BulletTextRow interactionTextForListingAdmin;
    @State
    Listing listing;
    @State
    ArrayList<ListingManager> listingManagers;
    CohostingManagementJitneyLogger logger;
    @State
    C1958CohostingSourceFlow sourceFlow;
    @BindView
    AirTextView termsText;
    @BindView
    DocumentMarquee titleMarquee;
    @BindView
    AirToolbar toolbar;

    public static CohostingServicesIntroFragment create(Listing listing2, ArrayList<ListingManager> listingManagers2, C1958CohostingSourceFlow flow) {
        return (CohostingServicesIntroFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingServicesIntroFragment()).putParcelable("listing", listing2)).putParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS, listingManagers2)).putSerializable(CohostingIntents.INTENT_EXTRA_SOURCE_FLOW_TO_INVITE_PAGE, flow)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_services_intro, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.listing = (Listing) getArguments().getParcelable("listing");
        this.listingManagers = getArguments().getParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS);
        this.sourceFlow = (C1958CohostingSourceFlow) getArguments().getSerializable(CohostingIntents.INTENT_EXTRA_SOURCE_FLOW_TO_INVITE_PAGE);
        view.setBackgroundColor(-1);
        if (!CohostingUtil.isCurrentUserListingAdmin(this.listingManagers, this.mAccountManager.getCurrentUserId())) {
            this.explanationSubtitle.setText(C5658R.string.cohosting_cohosts_explanation_function_title_for_cohost);
            this.interactionTextForListingAdmin.setVisibility(8);
            this.interactionTextForCohost.setVisibility(0);
            this.divider.setVisibility(0);
            this.constrainsText.setVisibility(8);
            this.guestsText.setVisibility(8);
        }
        CohostingUtil.setupTermsText(getContext(), this.termsText, C5658R.string.cohosting_service_intro_terms);
        this.logger.logCohostsServiceInfoPageImpression(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), this.sourceFlow);
        this.toolbar.setNavigationOnClickListener(CohostingServicesIntroFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    @OnClick
    public void showMoreInformation() {
        CohostingUtil.goToHelpCenterLink(getContext(), CohostingUtil.isCurrentUserListingAdmin(this.listingManagers, this.mAccountManager.getCurrentUserId()) ? HelpCenterArticle.COHOSTING_SERVICE_LISTING_ADMIN : HelpCenterArticle.COHOSTING_SERVICE_COHOST);
    }

    public boolean onBackPressed() {
        this.logger.logWhatCanCohostsDoModalDismissed(CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers), this.sourceFlow);
        getFragmentManager().popBackStack();
        return true;
    }
}
