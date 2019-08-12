package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.fragments.CohostingInvitationErrorFragment.ErrorType;
import com.airbnb.android.core.viewcomponents.models.HeroMarqueeEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostingInvitationErrorEpoxyController extends AirEpoxyController {
    private final Context context;
    HeroMarqueeEpoxyModel_ marquee;
    private final ErrorType type;

    public CohostingInvitationErrorEpoxyController(Context context2, ErrorType type2) {
        this.context = context2;
        this.type = type2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marquee.backgroundDrawableRes(C5658R.color.n2_error_color).iconDrawableRes(C5658R.C5659drawable.n2_error_icon_white).title(this.context.getString(C5658R.string.cohosting_invitation_error_title)).caption(this.context.getString(this.type.stringRes)).addTo(this);
    }
}
