package com.airbnb.android.referrals.adapters;

import android.content.Context;
import com.airbnb.android.core.models.GrayUser;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;

public class ReferralsPostReviewController extends AirEpoxyController {
    private final InviteClickListener clickListener;
    private final Context context;
    SubsectionDividerEpoxyModel_ divider;
    private final ArrayList<GrayUser> grayUsers;
    InviteMarqueeEpoxyModel_ header;
    private final ReferralStatusForMobile referralStatus;

    public interface InviteClickListener {
        void inviteButtonClicked(int i, GrayUser grayUser);
    }

    public ReferralsPostReviewController(Context context2, ReferralStatusForMobile referralStatus2, ArrayList<GrayUser> grayUsers2, InviteClickListener clickListener2) {
        this.context = context2;
        this.referralStatus = referralStatus2;
        this.grayUsers = grayUsers2;
        this.clickListener = clickListener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        String terms = this.context.getString(C1532R.string.terms_and_conditions);
        this.header.titleText(this.context.getString(C1532R.string.referrals_one_click_title)).subtitleText(this.context.getString(C1532R.string.referrals_one_click_details, new Object[]{this.referralStatus.getOfferSenderCreditLocalized()}) + " " + terms).subtitleLinkText(terms).subtitleLinkClickListener(ReferralsPostReviewController$$Lambda$1.lambdaFactory$(this, terms));
        this.divider.addTo(this);
        for (int i = 0; i < this.grayUsers.size(); i++) {
            GrayUser grayUser = (GrayUser) this.grayUsers.get(i);
            new InviteRowEpoxyModel_().m1323id((long) i).photoUri(grayUser.getProfile_pic_url()).titleText(grayUser.getName()).descriptionText(grayUser.getEmail()).buttonRes(C1532R.string.referrals_send_invite).clickListener(ReferralsPostReviewController$$Lambda$2.lambdaFactory$(this, i, grayUser)).addTo(this);
        }
    }

    public void addGrayUser(GrayUser grayUser, int index) {
        this.grayUsers.add(index, grayUser);
    }

    public void removeGrayUser(GrayUser grayUser) {
        this.grayUsers.remove(grayUser);
    }
}
