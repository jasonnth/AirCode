package com.airbnb.android.referrals;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.models.Referree;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.referrals.ReferralsComponent.Builder;
import com.airbnb.android.referrals.analytics.ReferralsAnalytics;
import com.airbnb.android.referrals.views.EarnedReferralRow_;
import com.airbnb.android.referrals.views.PendingReferralRow_;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;

public class SentReferralsFragment extends AirFragment {
    private static final String EARNED_REFERRALS_KEY = "earned_referrals_key";
    private static final String PENDING_REFERRALS_KEY = "pending_referrals_key";
    private static final String REFERRAL_STATUS_KEY = "referral_status_key";
    CurrencyFormatter currencyFormatter;
    @State
    ArrayList<Referree> earnedReferrees;
    @State
    ArrayList<Referree> pendingReferrees;
    @BindView
    RecyclerView recyclerView;
    @State
    ReferralStatusForMobile referralStatus;
    @BindView
    ViewGroup root;
    @BindView
    AirToolbar toolbar;

    public class SentReferralsAdapter extends AirEpoxyAdapter {
        private final SubsectionDividerEpoxyModel_ divider = new SubsectionDividerEpoxyModel_();
        private final MicroSectionHeaderEpoxyModel_ header = new MicroSectionHeaderEpoxyModel_().showDivider(false);
        private final MicroSectionHeaderEpoxyModel_ pendingHeader = new MicroSectionHeaderEpoxyModel_().titleRes(C1532R.string.pending).showDivider(true);
        private final ListSpacerEpoxyModel_ space = new ListSpacerEpoxyModel_().spaceHeight(128);
        private final ToolbarSpacerEpoxyModel_ toolbarSpacer = new ToolbarSpacerEpoxyModel_();

        public SentReferralsAdapter() {
            addModel(this.toolbarSpacer);
            setupHeader();
            addModel(this.header);
            addModel(this.space);
            addEarnedReferralRowModels();
            addModel(this.divider);
            addModel(this.pendingHeader);
            addPendingReferralRowModels();
        }

        private void setupHeader() {
            this.header.title(SentReferralsFragment.this.getString(C1532R.string.referrals_referral_credit_header, SentReferralsFragment.this.referralStatus.getAvailableCreditLocalized()));
            if (SentReferralsFragment.this.referralStatus.getAvailableCreditUSD() != 0) {
                this.header.description(SentReferralsFragment.this.getString(C1532R.string.referrals_available_credit_information, SentReferralsFragment.this.referralStatus.getAvailableCreditMinTripValueLocalizedRequirement(), SentReferralsFragment.this.referralStatus.getAvailableCreditExpiration().formatDate(DateFormat.getMediumDateFormat(SentReferralsFragment.this.getContext()))));
            } else if (SentReferralsFragment.this.earnedReferrees.isEmpty()) {
                this.header.description(SentReferralsFragment.this.getString(C1532R.string.referrals_zero_available_credit_start_referring));
            } else {
                this.header.description(SentReferralsFragment.this.getString(C1532R.string.referrals_zero_available_credit_keep_referring));
            }
        }

        private void addPendingReferralRowModels() {
            Iterator it = SentReferralsFragment.this.pendingReferrees.iterator();
            while (it.hasNext()) {
                Referree referree = (Referree) it.next();
                PendingReferralRow_ pendingReferralRow = new PendingReferralRow_();
                pendingReferralRow.imageUrl(referree.getReferredUserPhotoUrl());
                pendingReferralRow.status(SentReferralsFragment.this.getString(referree.hasJoined() ? C1532R.string.referrals_signed_up_but_no_trip : C1532R.string.referrals_hasnt_signed_up_yet, referree.getBestDisplayName()));
                addModel(pendingReferralRow);
            }
        }

        private void addEarnedReferralRowModels() {
            if (!SentReferralsFragment.this.earnedReferrees.isEmpty()) {
                addModel(this.divider);
                Iterator it = SentReferralsFragment.this.earnedReferrees.iterator();
                while (it.hasNext()) {
                    Referree referree = (Referree) it.next();
                    EarnedReferralRow_ earnedReferralRow = new EarnedReferralRow_();
                    earnedReferralRow.imageUrl(referree.getReferredUserPhotoUrl());
                    earnedReferralRow.name(referree.getBestDisplayName());
                    earnedReferralRow.status(SentReferralsFragment.this.getString(referree.hasHosted() ? C1532R.string.referrals_became_a_host : C1532R.string.referrals_booked_a_trip));
                    earnedReferralRow.amountEarned(String.valueOf(SentReferralsFragment.this.currencyFormatter.formatNativeCurrency(referree.getLocalizedCreditEarned(), true)));
                    addModel(earnedReferralRow);
                }
                addModel(this.space);
            }
        }
    }

    public static SentReferralsFragment newInstance(ReferralStatusForMobile referralStatus2, ArrayList<Referree> pending, ArrayList<Referree> earned) {
        return (SentReferralsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SentReferralsFragment()).putParcelable(REFERRAL_STATUS_KEY, referralStatus2)).putParcelableArrayList(PENDING_REFERRALS_KEY, pending)).putParcelableArrayList(EARNED_REFERRALS_KEY, earned)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ReferralsBindings) CoreApplication.instance(getContext()).componentProvider()).referralsComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            ReferralsAnalytics.trackPastInvitesImpression();
            this.referralStatus = (ReferralStatusForMobile) getArguments().getParcelable(REFERRAL_STATUS_KEY);
            this.pendingReferrees = getArguments().getParcelableArrayList(PENDING_REFERRALS_KEY);
            this.earnedReferrees = getArguments().getParcelableArrayList(EARNED_REFERRALS_KEY);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1532R.layout.fragment_referrals, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(1);
        this.recyclerView.setAdapter(new SentReferralsAdapter());
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.SentReferrals;
    }
}
