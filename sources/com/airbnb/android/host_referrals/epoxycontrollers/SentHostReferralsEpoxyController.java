package com.airbnb.android.host_referrals.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.core.models.Referree;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.android.referrals.views.EarnedReferralRow_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.Iterator;

public class SentHostReferralsEpoxyController extends AirEpoxyController {
    private final Context context;
    SubsectionDividerEpoxyModel_ divider;
    private final ArrayList<Referree> referrees;
    private final HostReferralReferrerInfo referrerInfo;
    MicroSectionHeaderEpoxyModel_ sectionHeaderEpoxyModel;
    ListSpacerEpoxyModel_ space;

    public SentHostReferralsEpoxyController(Context context2, ArrayList<Referree> referrees2, HostReferralReferrerInfo referrerInfo2) {
        this.context = context2;
        this.referrees = referrees2;
        this.referrerInfo = referrerInfo2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.sectionHeaderEpoxyModel.title(this.context.getString(C6405R.string.host_referral_your_earnings, new Object[]{this.referrerInfo.getReferralTotalEarnings().formattedForDisplay()})).description(this.context.getString(C6405R.string.host_referral_earnings_subtitle)).addTo(this);
        this.space.spaceHeightRes(C6405R.dimen.n2_vertical_padding_medium).addTo(this);
        this.divider.addTo(this);
        addReferreeEpoxyModels();
    }

    private void addReferreeEpoxyModels() {
        Iterator it = this.referrees.iterator();
        while (it.hasNext()) {
            Referree referree = (Referree) it.next();
            new EarnedReferralRow_().m1335id(referree.getId()).imageUrl(referree.getReferredUserProfilePhotoUrl()).name(referree.getBestDisplayName()).status(this.context.getString(getStatusStringRes(referree.getStatus()))).addTo(this);
        }
    }

    public int getStatusStringRes(String status) {
        char c = 65535;
        switch (status.hashCode()) {
            case -1633223147:
                if (status.equals(Referree.STATUS_REWARD_EXPIRED)) {
                    c = 4;
                    break;
                }
                break;
            case -1102508611:
                if (status.equals(Referree.STATUS_LISTED)) {
                    c = 2;
                    break;
                }
                break;
            case -577793850:
                if (status.equals(Referree.STATUS_STARTED_LISTING)) {
                    c = 1;
                    break;
                }
                break;
            case -217464898:
                if (status.equals(Referree.STATUS_REWARD_LIMIT_REACHED)) {
                    c = 3;
                    break;
                }
                break;
            case 1027649119:
                if (status.equals(Referree.STATUS_BOOKING_COMPLETE)) {
                    c = 5;
                    break;
                }
                break;
            case 1585461902:
                if (status.equals(Referree.STATUS_BOOKING_BELOW_MIN_COST)) {
                    c = 6;
                    break;
                }
                break;
            case 1960030843:
                if (status.equals(Referree.STATUS_INVITED)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return C6405R.string.host_referrals_pending_status;
            case 1:
                return C6405R.string.host_referrals_started_status;
            case 2:
                return C6405R.string.host_referrals_listed_status;
            case 3:
                return C6405R.string.host_referrals_limit_reached_status;
            case 4:
                return C6405R.string.host_referrals_invitation_expired_status;
            case 5:
                return C6405R.string.host_referrals_booking_complete_status;
            case 6:
                return C6405R.string.host_referrals_booking_didnt_meet_min_cost_status;
            default:
                return C6405R.string.host_referrals_unknown_status;
        }
    }
}
