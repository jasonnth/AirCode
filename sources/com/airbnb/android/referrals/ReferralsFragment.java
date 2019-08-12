package com.airbnb.android.referrals;

import android.os.Bundle;
import butterknife.BindView;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.referrals.ReferralsComponent.Builder;
import com.airbnb.android.referrals.adapters.ReferralModulesController;
import com.airbnb.android.referrals.adapters.ReferralModulesController.ReferralsFragmentInterface;
import com.airbnb.android.referrals.analytics.ReferralsAnalytics;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.flipboard.bottomsheet.BottomSheetLayout;
import icepick.State;

public class ReferralsFragment extends AirFragment implements ReferralsFragmentInterface {
    private static final String ENTRY_POINT_KEY = "entry_point_key";
    private static final String REFERRAL_STATUS_KEY = "referral_status_key";
    AppRaterController appRaterController;
    @State
    String entryPoint;
    @BindView
    AirRecyclerView recyclerView;
    private ReferralModulesController referralModulesController;
    @State
    ReferralStatusForMobile referralStatus;
    @BindView
    BottomSheetLayout rootBottomSheetLayout;
    @BindView
    AirToolbar toolbar;

    public static ReferralsFragment newInstance(ReferralStatusForMobile referralStatus2, String entryPoint2) {
        return (ReferralsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReferralsFragment()).putParcelable(REFERRAL_STATUS_KEY, referralStatus2)).putString(ENTRY_POINT_KEY, entryPoint2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ReferralsBindings) CoreApplication.instance(getContext()).componentProvider()).referralsComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            this.referralStatus = (ReferralStatusForMobile) getArguments().getParcelable(REFERRAL_STATUS_KEY);
            this.entryPoint = getArguments().getString(ENTRY_POINT_KEY, "");
        }
        this.referralModulesController = new ReferralModulesController(getContext(), this.referralStatus, this.entryPoint);
        this.referralModulesController.setListener(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
        if (r4.equals(com.airbnb.android.core.intents.ReferralsIntents.ENTRY_POINT_POST_REVIEW) != false) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r7, android.view.ViewGroup r8, android.os.Bundle r9) {
        /*
            r6 = this;
            r3 = 1
            r1 = 0
            int r2 = com.airbnb.android.referrals.C1532R.layout.fragment_referrals
            android.view.View r0 = r7.inflate(r2, r8, r1)
            r6.bindViews(r0)
            com.airbnb.n2.components.AirToolbar r2 = r6.toolbar
            r6.setToolbar(r2)
            java.lang.String r4 = r6.entryPoint
            r2 = -1
            int r5 = r4.hashCode()
            switch(r5) {
                case -1417464935: goto L_0x0035;
                case 644573143: goto L_0x002b;
                default: goto L_0x001a;
            }
        L_0x001a:
            r1 = r2
        L_0x001b:
            switch(r1) {
                case 0: goto L_0x0040;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.airbnb.n2.components.AirToolbar r1 = r6.toolbar
            r1.setNavigationIcon(r3)
        L_0x0023:
            com.airbnb.n2.collections.AirRecyclerView r1 = r6.recyclerView
            com.airbnb.android.referrals.adapters.ReferralModulesController r2 = r6.referralModulesController
            r1.setEpoxyControllerAndBuildModels(r2)
            return r0
        L_0x002b:
            java.lang.String r5 = "post_review"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x001a
            goto L_0x001b
        L_0x0035:
            java.lang.String r1 = "airnav"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x001a
            r1 = r3
            goto L_0x001b
        L_0x0040:
            com.airbnb.n2.components.AirToolbar r1 = r6.toolbar
            r2 = 2
            r1.setNavigationIcon(r2)
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.referrals.ReferralsFragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    public void onShareYourLinkClicked() {
        this.appRaterController.incrementSignificantEvent();
        ReferralsAnalytics.trackShareLinkClick();
        SharingManager sharingManager = new SharingManager(getActivity(), this.referralStatus.getLink(), SharingManager.REFERRALS);
        String userName = this.mAccountManager.getCurrentUser().getFirstName();
        sharingManager.withTitle(getString(C1532R.string.referral_new_title, this.referralStatus.getOfferReceiverCreditLocalized())).withBodyNoSharingURL(getString(C1532R.string.referral_new_body, this.referralStatus.getOfferReceiverCreditLocalized())).withDescription(getString(C1532R.string.referral_new_description, userName)).share(this.rootBottomSheetLayout);
    }

    public void onTravelCreditClicked() {
        ReferralsAnalytics.trackPastInvitesClick();
        ((ReferralsActivity) getActivity()).onSentReferralsClicked();
    }

    public void onTermsClicked() {
        ReferralsAnalytics.trackTermsClick();
        getActivity().startActivity(WebViewIntentBuilder.newBuilder(getContext(), getString(C1532R.string.tos_url_referrals)).title(C1532R.string.terms_and_conditions).toIntent());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Referrals;
    }
}
