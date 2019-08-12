package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostingInvitationExpiredEpoxyController extends AirEpoxyController {
    private final Context context;
    private final CohostInvitation invitation;
    DocumentMarqueeEpoxyModel_ marquee;

    public CohostingInvitationExpiredEpoxyController(Context context2, CohostInvitation invitation2) {
        this.context = context2;
        this.invitation = invitation2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marquee.titleText((CharSequence) this.context.getString(C5658R.string.cohosting_invitation_error_expired, new Object[]{this.invitation.getInviter().getFirstName()})).captionRes(C5658R.string.cohosting_invitation_error_expired_explanation).addTo(this);
    }
}
