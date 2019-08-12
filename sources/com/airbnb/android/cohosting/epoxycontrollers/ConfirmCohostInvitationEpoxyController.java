package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.models.HeroMarqueeEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class ConfirmCohostInvitationEpoxyController extends AirEpoxyController {
    HeroMarqueeEpoxyModel_ confirmationSection;
    private final Context context;
    private final CohostInvitation invitation;
    private final Listener listener;

    public interface Listener {
        void transitionToManageListingPickerPage();
    }

    public ConfirmCohostInvitationEpoxyController(Context context2, Listener listener2, CohostInvitation invitation2) {
        this.context = context2;
        this.listener = listener2;
        this.invitation = invitation2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupConfirmationSection();
    }

    private void setupConfirmationSection() {
        Listing listing = this.invitation.getListing();
        this.confirmationSection.title(this.context.getString(C5658R.string.cohosting_invitation_accepted_page_congrats, new Object[]{this.invitation.getInviter().getFirstName()})).caption(TextUtils.join("\n", new String[]{listing.getNameOrPlaceholderName(), listing.getAddress()})).themeColorRes(C5658R.color.n2_babu).iconDrawableRes(C5658R.C5659drawable.belo_white_00).secondButtonTextRes(C5658R.string.cohosting_invitation_accepted_page_ok_button).secondButtonClickListener(ConfirmCohostInvitationEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
    }
}
